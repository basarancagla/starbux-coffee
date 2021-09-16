package com.example.starbuxcoffeeassignment.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="TBL_ORDER")
public class Order {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name="original_amount")
    private double originalAmount;

    @Column(name="discount_amount")
    private double discountAmount;

    @Column(name="discounted_amount")
    private double discountedAmount;

    @Column(name="create_user")
    private Long createUser;

    @Column(name="create_time")
    private Date createTime;

    @Column(name="update_user")
    private Long updateUser;

    @Column(name="update_time")
    private Date updateTime;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_order", referencedColumnName = "id")
    private List<OrderProductDetail> orderProductDetail;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OrderProductDetail> getOrderProductDetail() {
        return orderProductDetail;
    }

    public double getOriginalAmount() {
        return originalAmount;
    }

    public void setOriginalAmount(double originalAmount) {
        this.originalAmount = originalAmount;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public double getDiscountedAmount() {
        return discountedAmount;
    }

    public void setDiscountedAmount(double discountedAmount) {
        this.discountedAmount = discountedAmount;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setOrderProductDetail(List<OrderProductDetail> orderProductDetail) {
        this.orderProductDetail = orderProductDetail;
    }

    public Long getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
