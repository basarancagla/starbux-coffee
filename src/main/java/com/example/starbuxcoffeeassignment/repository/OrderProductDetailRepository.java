package com.example.starbuxcoffeeassignment.repository;

import com.example.starbuxcoffeeassignment.entity.OrderProductDetail;
import com.example.starbuxcoffeeassignment.model.ToppingUsage;
import com.example.starbuxcoffeeassignment.model.UserAmount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderProductDetailRepository extends JpaRepository<OrderProductDetail, Long> {

    @Query(value = "SELECT new com.example.starbuxcoffeeassignment.model.ToppingUsage(P.productName,T.toppingName,sum(OP.quantity)) " +
            "FROM OrderProductDetail OP  " +
            "LEFT JOIN OP.productToppingDetail PT " +
            "INNER JOIN Topping T ON T.id = PT.toppingId " +
            "inner join Product P ON P.id = OP.productId " +
            "GROUP BY P.productName,T.toppingName " )
    List<ToppingUsage> findMostUsedToppingOfDrinks();

}
