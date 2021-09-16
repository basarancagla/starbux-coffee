package com.example.starbuxcoffeeassignment.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="TBL_TOPPING")
public class Topping {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="topping_name")
    private String toppingName;

    @Column(name="amount")
    private double amount;

    @Column(name="create_user")
    private int createUser;

    @Column(name="create_time")
    private Date createTime;

    public Topping() {
    }

    public Topping(String toppingName, double amount, int createUser, Date createTime) {
        this.toppingName = toppingName;
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

    public String getToppingName() {
        return toppingName;
    }

    public void setToppingName(String toppingName) {
        this.toppingName = toppingName;
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
