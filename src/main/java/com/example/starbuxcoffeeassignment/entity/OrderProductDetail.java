package com.example.starbuxcoffeeassignment.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="TBL_ORDER_PRODUCT_DETAIL")
public class OrderProductDetail {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name="product_id")
    private int productId;

    @Column(name="quantity")
    private int quantity;

    @Column(name="amount")
    private double amount;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_orderProduct", referencedColumnName = "id")
    private List<ProductToppingDetail> productToppingDetail;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ProductToppingDetail> getProductToppingDetail() {
        return productToppingDetail;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setProductToppingDetail(List<ProductToppingDetail> productToppingDetail) {
        this.productToppingDetail = productToppingDetail;
    }
}
