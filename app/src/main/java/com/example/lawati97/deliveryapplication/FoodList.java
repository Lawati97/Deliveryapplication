package com.example.lawati97.deliveryapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class FoodList extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference foodList;

    String menuId="";
//    public ImageView food_image;
//    food_image = (ImageView)findViewById(R.id.menu_image);

    FirebaseRecyclerAdapter<FoodModel,FoodViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);
        
        //Firebase
        database = FirebaseDatabase.getInstance();
        foodList = database.getReference("Menu");
        //DatabaseReference menuRef = menuRef.child("Menu").child("Image");


        recyclerView = (RecyclerView)findViewById(R.id.recycler_menu);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Get Intent
        if(getIntent() != null)
            menuId = getIntent().getStringExtra("MenuId");
        if(!menuId.isEmpty() && menuId != null){
            loadListFood(menuId);
        }

    }
//    FoodModel model;
//    private void loadListFood(String menuId){
//        foodList.orderByChild("MenuId").equalTo(menuId);
//        Picasso.with(getBaseContext()).load(model.getImage())
//                .into(food_image);
//
//    }
    private void loadListFood(String menuId) {
        adapter = new FirebaseRecyclerAdapter<FoodModel, FoodViewHolder>(FoodModel.class,
                R.layout.food_item,
                FoodViewHolder.class,
                foodList.orderByChild("MenuId").equalTo(menuId)
                ) {
            @Override
            protected void populateViewHolder(FoodViewHolder viewHolder, FoodModel model, int position) {
                viewHolder.food_name.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage())
                        .into(viewHolder.food_image);

                final FoodModel local = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Toast.makeText(FoodList.this,""+local.getName(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };

        //Set Adapter
        recyclerView.setAdapter(adapter);
    }
}
