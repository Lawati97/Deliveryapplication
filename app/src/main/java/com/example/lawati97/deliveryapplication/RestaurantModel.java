package com.example.lawati97.deliveryapplication;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Joshua Sim on 3/16/2018.
 */

@IgnoreExtraProperties
public class RestaurantModel {
    private String Name, Image, MenuId, Delivery;

    public RestaurantModel() {
        // Empty Constructor
    }

    public RestaurantModel(String name, String image, String menuId, String delivery) {
        Name = name;
        Image = image;
        MenuId = menuId;
        Delivery = delivery;
    }

    public String getDelivery() {return Delivery;}

    public void setDelivery(String delivery) {Delivery = delivery;}

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getMenuId() {
        return MenuId;
    }

    public void setMenuId(String menuId) {
        MenuId = menuId;
    }
}