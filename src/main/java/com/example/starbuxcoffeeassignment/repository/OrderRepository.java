package com.example.starbuxcoffeeassignment.repository;

import com.example.starbuxcoffeeassignment.entity.Order;
import com.example.starbuxcoffeeassignment.model.UserAmount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "SELECT new com.example.starbuxcoffeeassignment.model.UserAmount(u.userName,SUM(o.originalAmount)) FROM Order o INNER JOIN User u ON u.id = o.createUser GROUP BY u.userName")
    List<UserAmount> findSumAmountOfUsers();
}
