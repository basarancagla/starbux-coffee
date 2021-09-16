package com.example.starbuxcoffeeassignment.implementation;

import com.example.starbuxcoffeeassignment.entity.*;
import com.example.starbuxcoffeeassignment.repository.*;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class OrderServiceImplTest {

    @InjectMocks
    OrderServiceImpl orderServiceImpl;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderProductDetailRepository orderProductDetailRepository;

    @Mock
    private ProductToppingDetailRepository productToppingDetailRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ToppingRepository toppingRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private OrderServiceImpl orderService;


    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createOrder() throws Exception {
        Gson gson = new Gson();
        Order order = gson.fromJson(createJson, Order.class);

        Optional<Product> product = Optional.of(new Product("Coffe", 10.0, 10, new Date()));
        when(productRepository.findById(any())).thenReturn(product);

        Optional<Topping> topping = Optional.of(new Topping("Milk", 10.0, 10, new Date()));
        when(toppingRepository.findById(any())).thenReturn(topping);

        Optional<User> user = Optional.of(new User("Cagla","123", true, 10, new Date()));
        when(userRepository.findById(any())).thenReturn(user);

        when(userRepository.save(any())).thenReturn(order);


        Order response = orderServiceImpl.createOrderService(order);


        assertEquals(Double.valueOf(160),(Double) response.getOriginalAmount());
    }



    @Test
    public void updateOrder() throws Exception {
        Gson gson = new Gson();
        Gson gsonCreate = new Gson();

        Optional<Product> product = Optional.of(new Product("Coffe", 10.0, 10, new Date()));
        when(productRepository.findById(any())).thenReturn(product);

        Optional<Topping> topping = Optional.of(new Topping("Milk", 15.0, 10, new Date()));
        when(toppingRepository.findById(any())).thenReturn(topping);

        Optional<User> user = Optional.of(new User("Cagla","123", true, 10, new Date()));
        when(userRepository.findById(any())).thenReturn(user);

        Order  orderCreate = gsonCreate.fromJson(createJson, (Type) Order.class);
        when(orderRepository.findById(any())).thenReturn(Optional.of(orderCreate));
        when(orderProductDetailRepository.findById(any())).thenReturn(orderCreate.getOrderProductDetail().stream().findFirst());
        when(productToppingDetailRepository.findById(any())).thenReturn(orderCreate.getOrderProductDetail().get(0).getProductToppingDetail().stream().findFirst());
        when(userRepository.save(any())).thenReturn(orderCreate);
        //when(orderServiceImpl.getUserId()).thenReturn(1L);
        // add new product
        OrderProductDetail orderProductDetail = gson.fromJson(addOrderProductJson, OrderProductDetail.class);
        Order response = orderServiceImpl.addOrderProductService(orderProductDetail,1);

        assertEquals(Double.valueOf(240),(Double) response.getOriginalAmount());

        // add new topping to product
        ProductToppingDetail productToppingDetail = gson.fromJson(addProductToppingJson, ProductToppingDetail.class);
        response = orderServiceImpl.addProductToppingService(productToppingDetail,1,2);

        assertEquals(Double.valueOf(320),(Double) response.getOriginalAmount());

        // update product
        orderProductDetail = gson.fromJson(updateProductOrderJson, OrderProductDetail.class);
        response = orderServiceImpl.updateOrderProductService(orderProductDetail,1,2);

        assertEquals(Double.valueOf(320),(Double) response.getOriginalAmount());

        // update topping
        productToppingDetail = gson.fromJson(updateProductToppingJson, ProductToppingDetail.class);
        response = orderServiceImpl.updateProductToppingService(productToppingDetail,1,2,2);

        assertEquals(Double.valueOf(350),(Double) response.getOriginalAmount());

        // delete product
        response = orderServiceImpl.deleteOrderProductService(1,1);

        assertEquals(Double.valueOf(350),(Double) response.getOriginalAmount());

        // delete topping
        response = orderServiceImpl.deleteProductToppingService(1,2,2);

        assertEquals(Double.valueOf(350),(Double) response.getOriginalAmount());

    }


    @Test
    public void deleteOrder() throws Exception {
        orderServiceImpl.deleteOrderService(1);

    }



    private String createJson ="{\n" +
            "    \"createUser\": 1,\n" +
            "    \"createTime\": \"2012-04-23T18:25:43.511Z\",\n" +
            "    \"originalAmount\": 0,\n" +
            "    \"discountAmount\": 0,\n" +
            "    \"discountedAmount\": 0,\n" +
            "    \"orderProductDetail\": [\n" +
            "        {\n" +
            "            \"productId\": 3,\n" +
            "            \"quantity\": 2,\n" +
            "            \"amount\": 0,\n" +
            "            \"productToppingDetail\": [\n" +
            "                {\n" +
            "                    \"toppingId\": 1,\n" +
            "                    \"quantity\": 1\n" +
            "                },\n" +
            "                {\n" +
            "                    \"toppingId\": 2,\n" +
            "                    \"quantity\": 1\n" +
            "                }\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"productId\": 2,\n" +
            "            \"quantity\": 2,\n" +
            "            \"amount\": 0,\n" +
            "            \"productToppingDetail\": [\n" +
            "                {\n" +
            "                    \"toppingId\": 1,\n" +
            "                    \"quantity\": 1\n" +
            "                },\n" +
            "                {\n" +
            "                    \"toppingId\": 2,\n" +
            "                    \"quantity\": 1\n" +
            "                }\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"productId\": 1,\n" +
            "            \"quantity\": 2,\n" +
            "            \"amount\": 0,\n" +
            "            \"productToppingDetail\": [\n" +
            "                {\n" +
            "                    \"toppingId\": 1,\n" +
            "                    \"quantity\": 1\n" +
            "                },\n" +
            "                {\n" +
            "                    \"toppingId\": 2,\n" +
            "                    \"quantity\": 1\n" +
            "                }\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"productId\": 4,\n" +
            "            \"quantity\": 2,\n" +
            "            \"amount\": 0,\n" +
            "            \"productToppingDetail\": [\n" +
            "                {\n" +
            "                    \"toppingId\": 1,\n" +
            "                    \"quantity\": 1\n" +
            "                },\n" +
            "                {\n" +
            "                    \"toppingId\": 2,\n" +
            "                    \"quantity\": 1\n" +
            "                }\n" +
            "            ]\n" +
            "        }\n" +
            "    ]\n" +
            "}";

    private String addOrderProductJson = "{\n" +
            "            \"productId\": 4,\n" +
            "            \"quantity\": 1,\n" +
            "            \"amount\": 0,\n" +
            "            \"productToppingDetail\": [\n" +
            "                {\n" +
            "                    \"toppingId\": 1,\n" +
            "                    \"quantity\": 1\n" +
            "                },\n" +
            "                {\n" +
            "                    \"toppingId\": 2,\n" +
            "                    \"quantity\": 1\n" +
            "                }\n" +
            "            ]\n" +
            "  }";

    private String addProductToppingJson = " {\n" +
            "                    \"toppingId\": 1,\n" +
            "                    \"quantity\": 1\n" +
            "                }";

    private String updateProductOrderJson = "{          \n" +
            "            \"productId\": 2,\n" +
            "            \"quantity\": 2,\n" +
            "            \"amount\": 0,\n" +
            "            \"productToppingDetail\": [\n" +
            "                {\n" +
            "                    \"toppingId\": 1,\n" +
            "                    \"quantity\": 1\n" +
            "                },\n" +
            "                {\n" +
            "                    \"toppingId\": 2,\n" +
            "                    \"quantity\": 1\n" +
            "                }\n" +
            "            ]\n" +
            "}";

    private String updateProductToppingJson = "{\n" +
            "                    \"toppingId\": 2,\n" +
            "                    \"quantity\": 2\n" +
            "                }";


}

