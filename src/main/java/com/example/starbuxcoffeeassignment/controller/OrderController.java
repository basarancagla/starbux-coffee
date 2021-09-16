package com.example.starbuxcoffeeassignment.controller;

import com.example.starbuxcoffeeassignment.entity.Order;
import com.example.starbuxcoffeeassignment.entity.OrderProductDetail;
import com.example.starbuxcoffeeassignment.entity.ProductToppingDetail;
import com.example.starbuxcoffeeassignment.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Api( tags = "Order")
@SecurityRequirement(name = "Authorization")
public class OrderController {
    @Autowired
    OrderService orderService;

    @ApiOperation(value = "Create order")
    @PostMapping("/create-order")
    public Order createOrder(@RequestBody Order order) throws Exception {
        return orderService.createOrderService(order);
    }

    @ApiOperation(value = "Add product to order")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add-product/order/{orderId}")
    public Order addProduct(@RequestBody OrderProductDetail orderProductDetail, @PathVariable int orderId) throws Exception {
        return orderService.addOrderProductService(orderProductDetail,orderId);
    }

    @ApiOperation(value = "Add topping to product")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add-topping/order/{orderId}/product/{orderProductId}")
    public Order addTopping(@RequestBody ProductToppingDetail productToppingDetail, @PathVariable int orderId,@PathVariable int orderProductId) throws Exception {
        return orderService.addProductToppingService(productToppingDetail,orderId,orderProductId);
    }

    @ApiOperation(value = "Update product")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update-product/order/{orderId}/product/{orderProductId}")
    public Order updateOrderProduct(@RequestBody OrderProductDetail orderProductDetail, @PathVariable int orderId,@PathVariable int orderProductId) throws Exception {
        return orderService.updateOrderProductService(orderProductDetail,orderId,orderProductId);
    }

    @ApiOperation(value = "Update topping of product")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update-topping/order/{orderId}/product/{orderProductId}/topping/{productToppingId}")
    public Order updateProductTopping(@RequestBody ProductToppingDetail productToppingDetail, @PathVariable int orderId,@PathVariable int orderProductId, @PathVariable int productToppingId) throws Exception {
        return orderService.updateProductToppingService(productToppingDetail,orderId,orderProductId,productToppingId);
    }

    @ApiOperation(value = "Delete product from order")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete-product/order/{orderId}/product/{orderProductId}")
    public Order deleteOrderProduct(@PathVariable int orderId,@PathVariable int orderProductId) throws Exception {
        return orderService.deleteOrderProductService(orderId,orderProductId);
    }

    @ApiOperation(value = "Delete topping from product")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete-topping/order/{orderId}/product/{orderProductId}/topping/{productToppingId}")
    public Order deleteProductTopping(@PathVariable int orderId,@PathVariable int orderProductId, @PathVariable int productToppingId) throws Exception {
        return orderService.deleteProductToppingService(orderId,orderProductId,productToppingId);
    }

    @ApiOperation(value = "Delete order")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete-order/{id}")
    public void deleteOrder(@PathVariable int id) {
        orderService.deleteOrderService(id);
    }




}