package com.example.starbuxcoffeeassignment.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="TBL_PRODUCT")
public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="product_name")
    private String productName;

    @Column(name="amount")
    private double amount;

    @Column(name="create_user")
    private int createUser;

    @Column(name="create_time")
    private Date createTime;

    public Product() {
    }

    public Product(String productName, double amount, int createUser, Date createTime) {
        this.productName = productName;
        this.amount = amount;
        this.createUser = createUser;
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getCreateUser() {
        return createUser;
    }

    public void setCreateUser(int createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
