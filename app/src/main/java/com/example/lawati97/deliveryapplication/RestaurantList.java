package com.example.lawati97.deliveryapplication;

import android.content.Context;
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

    FirebaseDatabase database;
    DatabaseReference restaurantList;
   // private Context mContext;

    FirebaseRecyclerAdapter<RestaurantModel,RestaurantViewHolder> adapter;

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
        FirebaseRecyclerAdapter<RestaurantModel,RestaurantViewHolder> adapter;
        adapter = new FirebaseRecyclerAdapter<RestaurantModel, RestaurantViewHolder>(RestaurantModel.class, R.layout.food_menus,RestaurantViewHolder.class,restaurantList) {
            @Override
            protected void populateViewHolder(RestaurantViewHolder viewHolder, RestaurantModel model, int position) {
                viewHolder.txtRestaurantName.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage())
                        .into(viewHolder.imageView);
                RestaurantModel clickItem = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Toast.makeText(RestaurantList.this, ""+clickItem.getName(), Toast.LENGTH_SHORT).show();


                        Intent intent = new Intent(RestaurantList.this, order_food.class);
                        final String name = clickItem.getName();
                        intent.putExtra("Image", name);
                        RestaurantList.this.startActivity(intent);
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);
    }

    class Item{
        private String name;
        public void setName(String name){
            this.name = name;
        }
        public String getName(){
            return name;
        }
        public String toString(){
            return name;
        }
    }

}
