package com.example.starbuxcoffeeassignment.implementation;

import com.example.starbuxcoffeeassignment.entity.*;
import com.example.starbuxcoffeeassignment.exception.ApiRequestException;
import com.example.starbuxcoffeeassignment.repository.*;
import com.example.starbuxcoffeeassignment.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderProductDetailRepository orderProductDetailRepository;

    @Autowired
    private ProductToppingDetailRepository productToppingDetailRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ToppingRepository toppingRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Order createOrderService(Order order) throws Exception {
        order.setUpdateUser(0L);
        order.setUpdateTime(null);
        order.setCreateUser(getUserId());
        order.setCreateTime(new Date());

        calculateAmount(order);
        orderRepository.save(order);

        return order;
    };

    @Override
    public void deleteOrderService(long id){
        orderRepository.deleteById(id);
    }

    @Override
    public Order updateOrderProductService(OrderProductDetail orderProductDetail, long orderId, long orderProductId) throws Exception {
        OrderProductDetail existingOrderProductDetail = orderProductDetailRepository.findById(orderProductId).orElseThrow(() -> new ApiRequestException("The topping in product is not found." ));

        existingOrderProductDetail.setProductId(orderProductDetail.getProductId());
        existingOrderProductDetail.setQuantity(orderProductDetail.getQuantity());

        orderProductDetailRepository.save(existingOrderProductDetail);

        return updateGetOrder(orderId);
    }

    @Override
    public Order updateProductToppingService(ProductToppingDetail productToppingDetail, long orderId, long orderProductId, long productToppingId) throws Exception {
        ProductToppingDetail existingProductToppingDetail = productToppingDetailRepository.findById(productToppingId).orElseThrow(() -> new ApiRequestException("The topping in product is not found." ));

        existingProductToppingDetail.setToppingId(productToppingDetail.getToppingId());
        existingProductToppingDetail.setQuantity(productToppingDetail.getQuantity());

        productToppingDetailRepository.save(existingProductToppingDetail);

        return updateGetOrder(orderId);
    }

    @Override
    public Order deleteOrderProductService(long orderId, long orderProductId) throws Exception {
        orderProductDetailRepository.deleteById(orderProductId);
        return updateGetOrder(orderId);
    }

    @Override
    public Order deleteProductToppingService(long orderId, long orderProductId, long productToppingId) throws Exception {
        productToppingDetailRepository.deleteById(productToppingId);
        return updateGetOrder(orderId);
    }

    @Override
    public Order addOrderProductService(OrderProductDetail orderProductDetail, long orderId) throws Exception {
        Order existingOrder = orderRepository.findById(orderId).orElseThrow(() -> new ApiRequestException("The order is not found." ));
        existingOrder.getOrderProductDetail().add(orderProductDetail);
        orderRepository.save(existingOrder);
        return updateGetOrder(orderId);
    }

    @Override
    public Order addProductToppingService(ProductToppingDetail productToppingDetail, long orderId, long orderProductId) throws Exception {
        Order existingOrder = orderRepository.findById(orderId).orElseThrow(() -> new ApiRequestException("The order is not found." ));
        OrderProductDetail existingOrderDetail = orderProductDetailRepository.findById(orderProductId).orElseThrow(() -> new ApiRequestException("The order is not found." ));

        existingOrderDetail.getProductToppingDetail().add(productToppingDetail);
        existingOrder.getOrderProductDetail().add(existingOrderDetail);
        orderRepository.save(existingOrder);
        return updateGetOrder(orderId);
    }


    private Double calculateAmount(long productId, int productQuantity, List<ProductToppingDetail> toppingDetailArrayList) throws Exception {
        double productAmount;
        double toppingAmount;


        productAmount = getProductAmountByID(productId) * productQuantity;
        toppingAmount = getToppingAmount(toppingDetailArrayList);


        return productAmount + toppingAmount;
    }

    private double getProductAmountByID(Long id) throws Exception {
        Product product= new Product();
        product = productRepository.findById(id)
                     .orElseThrow(() -> new ApiRequestException("Could not find product id: " + id));
        return product.getAmount();
    }

    private double getToppingAmount(List<ProductToppingDetail> toppingDetailArrayList) throws Exception {
        double toppingAmount=0;

        for (ProductToppingDetail toppingListItem : toppingDetailArrayList)
        {
            Topping topping= new Topping();
            topping = toppingRepository.findById((long) toppingListItem.getToppingId())
                    .orElseThrow(() -> new ApiRequestException("Could not find topping id : " + toppingListItem.getToppingId()));

            toppingAmount += topping.getAmount() * toppingListItem.getQuantity();
        }

        return  toppingAmount;

    }

    private double calculateDiscount(double originalAmount,List<OrderProductDetail> orderProductDetailList){
        double discountAmount = 0;
        double discountAmount_1 = 0;
        double discountAmount_2 = 0;

        if (originalAmount>12){
            discountAmount_1 = originalAmount * 0.25;
        }

        if(orderProductDetailList.size()>2){
            discountAmount_2 = orderProductDetailList.stream()
                                .mapToDouble(OrderProductDetail::getAmount)
                                .min()
                                .getAsDouble();
        }

        discountAmount = Math.max(discountAmount_1,discountAmount_2);

        return  discountAmount;

    }

    private Order calculateAmount(Order order) throws Exception{
        double calculatedAmount = 0;
        double sumOfCalculatedAmount = 0;
        double discountAmount = 0;

        for (OrderProductDetail productListItem : order.getOrderProductDetail())
        {
            calculatedAmount = calculateAmount(productListItem.getProductId(),productListItem.getQuantity(),productListItem.getProductToppingDetail());
            sumOfCalculatedAmount += calculatedAmount;

            productListItem.setAmount(calculatedAmount);
        }

        discountAmount = calculateDiscount(sumOfCalculatedAmount,order.getOrderProductDetail());

        order.setOriginalAmount(sumOfCalculatedAmount);
        order.setDiscountAmount(discountAmount);
        order.setDiscountedAmount(sumOfCalculatedAmount - discountAmount);

        return order;
    }

    private Order updateGetOrder(long orderId) throws Exception{
        Order order =  orderRepository.findById(orderId).orElseThrow(() -> new ApiRequestException("The order is not found." ));
        order = calculateAmount(order);

        order.setUpdateUser(getUserId());
        order.setUpdateTime(new Date());
        orderRepository.save(order);

        return order;
    }

    public Long getUserId(){
        Object principal;
        if(SecurityContextHolder.getContext().getAuthentication() == null){
            return 1L;
        }
        else{
            principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username = "";
            if (principal instanceof UserDetails) {
                username = ((UserDetails)principal).getUsername();
            } else {
                username = principal.toString();
            }

            Optional<User> user;
            user = userRepository.findByUserName(username);
            return  user.get().getId();
        }


    }

}
