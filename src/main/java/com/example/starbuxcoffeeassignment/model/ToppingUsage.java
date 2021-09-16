package com.example.starbuxcoffeeassignment.model;

public class ToppingUsage {
    private String productName;
    private String toppingName;
    private Long count;

    public ToppingUsage(String productName, String toppingName, Long count) {
        this.productName = productName;
        this.toppingName = toppingName;
        this.count = count;
    }


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getToppingName() {
        return toppingName;
    }

    public void setToppingName(String toppingName) {
        this.toppingName = toppingName;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
