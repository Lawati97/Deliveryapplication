package com.example.lawati97.deliveryapplication;

import android.content.Intent;
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

public class FilteredResList extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference foodList;

    String categoryId="";
//    public ImageView food_image;
//    food_image = (ImageView)findViewById(R.id.menu_image);

    FirebaseRecyclerAdapter<RestaurantModel,RestaurantViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);

        //Firebase
        database = FirebaseDatabase.getInstance();
        foodList = database.getReference("Food");
        //DatabaseReference menuRef = menuRef.child("Menu").child("Image");


        recyclerView = (RecyclerView)findViewById(R.id.recycler_food);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Get Intent
        if(getIntent() != null)
            categoryId = getIntent().getStringExtra("CatId");
        if(!categoryId.isEmpty() && categoryId != null){
            loadListFood(categoryId);
        }

    }
    //    FoodModel model;
//    private void loadListFood(String menuId){
//        foodList.orderByChild("MenuId").equalTo(menuId);
//        Picasso.with(getBaseContext()).load(model.getImage())
//                .into(food_image);
//
//    }
    private void loadListFood(String categoryId) {
        adapter = new FirebaseRecyclerAdapter<RestaurantModel, RestaurantViewHolder>(RestaurantModel.class,
                R.layout.food_menus,
                RestaurantViewHolder.class,
                foodList.orderByChild("CatId").equalTo(categoryId)
        ) {
            @Override
            protected void populateViewHolder(RestaurantViewHolder viewHolder, RestaurantModel model, int position) {
                viewHolder.txtRestaurantName.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage())
                        .into(viewHolder.imageView);

                final RestaurantModel local = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent foodList = new Intent(FilteredResList.this,FoodList.class);
                        foodList.putExtra("MenuId",adapter.getRef(position).getKey());
                        startActivity(foodList);

                        //Toast.makeText(FilteredResList.this,""+local.getName(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };

        //Set Adapter
        recyclerView.setAdapter(adapter);
    }
}
