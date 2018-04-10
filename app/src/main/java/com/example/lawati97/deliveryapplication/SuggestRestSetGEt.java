package com.example.lawati97.deliveryapplication;
import android.net.Uri;
//TODO 1)connect to database 2)image upload to storage in firebase 3)write ur document for phase 4

public class SuggestRestSetGEt {


    String restaurantId;
    String restaurantNameS;
    String restaurantPhoneS;
    String restaurantCategoryS;
    String restaurantImage;


    public SuggestRestSetGEt(String restaurantId, String restaurantNameS, String restaurantPhoneS,String restaurantCategoryS){
        this.restaurantId = restaurantId;
        this.restaurantNameS = restaurantNameS;
        this.restaurantPhoneS = restaurantPhoneS;
        this.restaurantCategoryS = restaurantCategoryS;
        //this.restaurantImage = restaurantImage;
    }
    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantNameS() {
        return restaurantNameS;
    }

    public void setRestaurantNameS(String restaurantNameS) {
        this.restaurantNameS = restaurantNameS;
    }

    public String getRestaurantPhoneS() {
        return restaurantPhoneS;
    }

    public void setRestaurantPhoneS(String restaurantPhoneS) {
        this.restaurantPhoneS = restaurantPhoneS;
    }

    public String getRestaurantCategoryS() {
        return restaurantCategoryS;
    }

    public void setRestaurantCategoryS(String restaurantCategoryS) {
        this.restaurantCategoryS = restaurantCategoryS;
    }

    public String getRestaurantImage() {
        return restaurantImage;
    }

    public void setRestaurantImage(String restaurantImage) {
        this.restaurantImage = restaurantImage;
    }



}
