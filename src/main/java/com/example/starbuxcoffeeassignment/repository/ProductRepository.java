package com.example.starbuxcoffeeassignment.repository;

import com.example.starbuxcoffeeassignment.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {



}
