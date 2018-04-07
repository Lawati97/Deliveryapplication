package com.example.lawati97.deliveryapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class RestaurantList extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseRecyclerAdapter<RestaurantModel,RestaurantViewHolder> adapter;
    FirebaseDatabase database;
    DatabaseReference restaurantList;

    //FirebaseRecyclerAdapter<RestaurantModel,RestaurantViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);

        //Firebase
        database = FirebaseDatabase.getInstance();
        restaurantList = database.getReference("Food");

        //Load restaurants
        recyclerView = (RecyclerView)findViewById(R.id.recycler_food);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        loadRestaurants();

    }

    private void loadRestaurants() {
        adapter = new FirebaseRecyclerAdapter<RestaurantModel, RestaurantViewHolder>(RestaurantModel.class,R.layout.food_menus,RestaurantViewHolder.class,restaurantList) {
            @Override
            protected void populateViewHolder(RestaurantViewHolder viewHolder, RestaurantModel model, int position) {
                viewHolder.txtRestaurantName.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage())
                        .into(viewHolder.imageView);
                final RestaurantModel clickItem = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        //Get MenuId and send to new activity
                        Intent foodList = new Intent(RestaurantList.this,FoodList.class);
                        foodList.putExtra("MenuId",adapter.getRef(position).getKey());
                        startActivity(foodList);
                        //Toast.makeText(RestaurantList.this, ""+clickItem.getName(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);
    }

//    private void loadListRestaurant(){
//        adapter = new FirebaseRecyclerAdapter<RestaurantModel, RestaurantViewHolder>(RestaurantModel.class,
//                R.layout.food_menus,
//                RestaurantViewHolder.class,
//                // RestaurantList.orderByChild("MenuId")
//                ) {
//            @Override
//            protected void populateViewHolder(RestaurantViewHolder viewHolder, RestaurantModel model, int position) {
//                viewHolder.txtRestaurantName.setText(model.getName());
//                Picasso.with(getBaseContext()).load(model.getImage())
//                        .into(viewHolder.imageView);
//
//                final RestaurantModel local = model;
//                viewHolder.setItemClickListener(new ItemClickListener() {
//                    @Override
//                    public void onClick(View view, int position, boolean isLongClick) {
//                        Toast.makeText(RestaurantList.this, ""+local.getName(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        }
//
//        //Set adapter
//        recyclerView.setAdapter(adapter);
//    }
}
