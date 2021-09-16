package com.example.starbuxcoffeeassignment;

import com.example.starbuxcoffeeassignment.entity.Product;
import com.example.starbuxcoffeeassignment.entity.Topping;
import com.example.starbuxcoffeeassignment.entity.User;
import com.example.starbuxcoffeeassignment.repository.ProductRepository;
import com.example.starbuxcoffeeassignment.repository.ToppingRepository;
import com.example.starbuxcoffeeassignment.repository.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Date;

@SpringBootApplication
@EnableSwagger2
public class StarbuxCoffeeAssignmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(StarbuxCoffeeAssignmentApplication.class, args);
    }

    @Bean
    public ApplicationRunner initializer(UserRepository repository) {
        Date date = new Date();

        return args -> repository.saveAll(Arrays.asList(
                new User("Cagla","123",true,1, date ),
                new User("Bestseller","234",true,1, date ),
                new User("Mark","456",false,1, date )
        ));
    }

    @Bean
    public ApplicationRunner initializer1(ProductRepository repository) {
        Date date = new Date();

        return args -> repository.saveAll(Arrays.asList(
                new Product("Black Coffee",4,1, date ),
                new Product("Latte",5,1, date ),
                new Product("Mocha",6,1, date ),
                new Product("Tea",3,1, date )
        ));
    }

    @Bean
    public ApplicationRunner initializer2(ToppingRepository repository) {
        Date date = new Date();

        return args -> repository.saveAll(Arrays.asList(
                new Topping("Milk",2,1, date ),
                new Topping("Hazelnut syrup",3,1, date ),
                new Topping("Chocolate sauce",5,1, date ),
                new Topping("Lemon",2,1, date )
        ));
    }


}
