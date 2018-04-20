package com.example.lawati97.deliveryapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class userInfo extends AppCompatActivity implements activity_user_info{
    private Button submit;
    private EditText name,address,specialInst;
    String nname,aaddress,sspecialInst,order;
    DatabaseReference databaseOrder;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        submit = (Button) findViewById(R.id.submitUser);
        submit.setOnClickListener((View.OnClickListener) this);
        name = (EditText) findViewById(R.id.userName);
        address = (EditText) findViewById(R.id.enterAddress);
        specialInst = (EditText) findViewById(R.id.enterInstruction);

        //Firebase
        database = FirebaseDatabase.getInstance();
       // getIncomingIntent();
    }

    private void getIncomingIntent() {

            if (getIntent().hasExtra("Order")) {
                order = getIntent().getStringExtra("Order");
               }
    }
    @Override
    public void onClick(View view){
        if(view == submit){
            nname = name.getText().toString().trim();
            aaddress = address.getText().toString().trim();
            sspecialInst = specialInst.getText().toString().trim();

            if (TextUtils.isEmpty(nname)) {
                Toast.makeText(userInfo.this, "Please enter your name", Toast.LENGTH_SHORT).show();
                return;

            }
            if (TextUtils.isEmpty(aaddress)) {
                Toast.makeText(userInfo.this, "Please enter the your address", Toast.LENGTH_SHORT).show();
                return;

            }
            //AllPased
            saveDataToFirebase();
            Toast.makeText(userInfo.this, "Order is now complete", Toast.LENGTH_SHORT).show();
            finish();
            userInfo.this.startActivity(new Intent(userInfo.this, Homepage.class));

        }

    }

    private void saveDataToFirebase() {
        String id = databaseOrder.push().getKey();
        userInfoSetGet suggestRest = new userInfoSetGet(id,nname,aaddress,sspecialInst,order);
        databaseOrder.child(id).setValue(suggestRest);
    }
}
