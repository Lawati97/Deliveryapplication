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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        firebaseAuth = FirebaseAuth.getInstance();

        suggestARestaurant = (Button) findViewById(R.id.SuggestARestaurant);
        suggestARestaurant.setOnClickListener(this);
        logout = (Button) findViewById(R.id.logOutFromHomePage);
        logout.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
    if(view == logout){
        Toast.makeText(Homepage.this, "Signing out", Toast.LENGTH_LONG).show();
        firebaseAuth.signOut();
        Homepage.this.startActivity(new Intent(Homepage.this, LogInActivity.class));
    }
    if(view == suggestARestaurant){
        finish();
        Homepage.this.startActivity(new Intent(Homepage.this, SuggestARestaurant.class));
    }
    }
}
