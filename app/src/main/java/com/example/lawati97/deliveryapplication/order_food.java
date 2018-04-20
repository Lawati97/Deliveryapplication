package com.example.lawati97.deliveryapplication;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class order_food extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    private Button submitButton;
    private EditText orderFd;
    String food;
    DatabaseReference databaseOrder;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    Query foodID, foodName, foodImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        databaseOrder = FirebaseDatabase.getInstance().getReference("Order");
        setContentView(R.layout.activity_order_food);
        submitButton = (Button) findViewById(R.id.submitOrder);
        submitButton.setOnClickListener(this);
        orderFd = (EditText) findViewById(R.id.editText5);

        //Firebase
        database = FirebaseDatabase.getInstance();
        getIncomingIntent();

    }

    private void getIncomingIntent() {
        if (getIntent().hasExtra("Image")) {
            String fdName = getIntent().getStringExtra("Image");
            foodID = database.getReference("Food").orderByChild(fdName).equalTo("Image");
            // foodName = foodID.orderByChild(fdName);
            // foodImage = foodName.orderByChild("Image");
            setImage(foodID);
        }
    }

    private void setImage(Query foodID) {
        ImageView photo;
        photo = findViewById(R.id.image_menu);
        Glide.with(order_food.this).load(foodImage).into(photo);
    }

    @Override
    public void onClick(View view) {
        if (view == submitButton) {
            food = orderFd.getText().toString().trim();

            if (TextUtils.isEmpty(food)) {
                Toast.makeText(order_food.this, "Please enter the food you'd like to order", Toast.LENGTH_SHORT).show();
                return;

            }
            //AllPased
            saveDataToFirebase();
            //Intent intent = new Intent(order_food.this, userInfo.class);
            //final String fFood = food;
            //intent.putExtra("Order", fFood);

            Toast.makeText(order_food.this, "Thanks for your order!", Toast.LENGTH_SHORT).show();
            finish();
           // order_food.this.startActivity(intent);
            order_food.this.startActivity(new Intent(order_food.this, userInfo.class));

        }
    }

    public void saveDataToFirebase(){
        String id = databaseOrder.push().getKey();
        Order_FoodSetGet suggestRest = new Order_FoodSetGet(id,food);
        databaseOrder.child(id).setValue(suggestRest);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}