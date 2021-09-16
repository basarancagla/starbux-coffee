package com.example.starbuxcoffeeassignment.entity;

import javax.persistence.*;

@Entity
@Table(name="TBL_PRODUCT_TOPPING_DETAIL")
public class ProductToppingDetail {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name="topping_id")
    private int toppingId;

    @Column(name="quantity")
    private int quantity;


    public ProductToppingDetail() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getToppingId() {
        return toppingId;
    }

    public void setToppingId(int toppingId) {
        this.toppingId = toppingId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
