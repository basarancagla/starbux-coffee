package com.example.starbuxcoffeeassignment.model;

public class UserAmount {
    private String user;
    private double totalAmount;

    public UserAmount() {

    }

    public UserAmount(String user, double totalAmount) {
        this.user = user;
        this.totalAmount = totalAmount;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
