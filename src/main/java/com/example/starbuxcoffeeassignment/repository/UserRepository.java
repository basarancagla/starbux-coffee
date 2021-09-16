package com.example.starbuxcoffeeassignment.repository;

import com.example.starbuxcoffeeassignment.entity.Product;
import com.example.starbuxcoffeeassignment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String userName);
}
