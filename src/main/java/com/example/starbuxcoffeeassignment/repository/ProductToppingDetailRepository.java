package com.example.starbuxcoffeeassignment.repository;

import com.example.starbuxcoffeeassignment.entity.ProductToppingDetail;
import com.example.starbuxcoffeeassignment.model.ToppingUsage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductToppingDetailRepository extends JpaRepository<ProductToppingDetail, Long> {

}