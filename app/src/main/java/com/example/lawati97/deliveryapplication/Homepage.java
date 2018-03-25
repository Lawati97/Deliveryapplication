package com.example.lawati97.deliveryapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Homepage extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth firebaseAuth;
    private Button logout;
    private Button suggestARestaurant;
    private Button restaurant;
    private Button delivery,fav,cate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        firebaseAuth = FirebaseAuth.getInstance();

        suggestARestaurant = (Button) findViewById(R.id.SuggestARestaurant);
        suggestARestaurant.setOnClickListener(this);
        logout = (Button) findViewById(R.id.logOutFromHomePage);
        logout.setOnClickListener(this);
        restaurant = (Button) findViewById(R.id.RestaurantList);
        restaurant.setOnClickListener(this);
        delivery = (Button) findViewById(R.id.Delivery);
        delivery.setOnClickListener(this);
        fav = (Button) findViewById(R.id.Favorite);
        fav.setOnClickListener(this);
        cate = (Button) findViewById(R.id.Cate);
        cate.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
    if(view == logout){
        Toast.makeText(Homepage.this, "Signing out", Toast.LENGTH_LONG).show();
        firebaseAuth.signOut();
        Homepage.this.startActivity(new Intent(Homepage.this, LogInActivity.class));
    }
    if(view == suggestARestaurant){
        //finish();
        startActivity(new Intent(Homepage.this, SuggestARestaurant.class));
    }
    if(view == restaurant) {
        startActivity(new Intent(Homepage.this, RestaurantList.class));
    }
    if(view == delivery) {
            startActivity(new Intent(Homepage.this, Delivery.class));
        }
    if(view == fav) {
        startActivity(new Intent(Homepage.this, Fav.class));
    }
    if(view == cate) {
            startActivity(new Intent(Homepage.this, Category.class));
        }
    }
}
