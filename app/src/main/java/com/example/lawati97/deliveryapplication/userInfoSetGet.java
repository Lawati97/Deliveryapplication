package com.example.lawati97.deliveryapplication;

import com.google.firebase.database.DatabaseReference;

public class userInfoSetGet {

    String id;
    String name;
    String address;
    String specialIn;

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    String food;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSpecialIn() {
        return specialIn;
    }

    public void setSpecialIn(String specialIn) {
        this.specialIn = specialIn;
    }



    public userInfoSetGet(String id, String nname, String aaddress, String sspecialInst, String order) {
        this.id = id;
        this.name = nname;
        this.address = aaddress;
        this.specialIn = sspecialInst;
        this.food = order;
    }

}
