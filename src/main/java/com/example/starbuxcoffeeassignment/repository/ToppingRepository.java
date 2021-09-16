package com.example.starbuxcoffeeassignment.repository;

import com.example.starbuxcoffeeassignment.entity.Topping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToppingRepository extends JpaRepository<Topping, Long> {
}
