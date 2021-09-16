package com.example.starbuxcoffeeassignment.service;

import com.example.starbuxcoffeeassignment.entity.Order;
import com.example.starbuxcoffeeassignment.entity.OrderProductDetail;
import com.example.starbuxcoffeeassignment.entity.ProductToppingDetail;

public interface OrderService {
    Order createOrderService(Order order) throws Exception;
    void deleteOrderService(long id) ;
    Order updateOrderProductService(OrderProductDetail orderProductDetail, long orderId, long orderProductId) throws Exception;
    Order updateProductToppingService(ProductToppingDetail productToppingDetail, long orderId, long orderProductId, long productToppingId) throws Exception;
    Order deleteOrderProductService(long orderId, long orderProductId) throws Exception;
    Order deleteProductToppingService(long orderId, long orderProductId, long productToppingId) throws Exception;
    Order addOrderProductService(OrderProductDetail order, long orderId) throws Exception;
    Order addProductToppingService(ProductToppingDetail orderProductDetail, long orderId, long orderProductId) throws Exception;
}
