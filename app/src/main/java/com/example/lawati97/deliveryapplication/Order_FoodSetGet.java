package com.example.lawati97.deliveryapplication;

public class Order_FoodSetGet {

    String orderID;
    String foodOrdered;

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getFoodOrdered() {
        return foodOrdered;
    }

    public void setFoodOrdered(String foodOrdered) {
        this.foodOrdered = foodOrdered;
    }

    public Order_FoodSetGet(String id, String food) {
    this.orderID = id;
    this.foodOrdered = food;

    }
}
