package com.example.lawati97.deliveryapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

public class Category extends AppCompatActivity {


    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    Query category;

    FirebaseRecyclerAdapter<RestaurantModel,RestaurantViewHolder> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);

        //Firebase
        database = FirebaseDatabase.getInstance();
        category = database.getReference("Category");

        //Load restaurants
        recyclerView = (RecyclerView)findViewById(R.id.recycler_food);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        loadRestaurants();

    }

    private void loadRestaurants() {
        //adapter = new FirebaseRecyclerAdapter<RestaurantModel,RestaurantViewHolder>
        adapter = new FirebaseRecyclerAdapter<RestaurantModel, RestaurantViewHolder>(RestaurantModel.class,R.layout.food_menus,RestaurantViewHolder.class,category) {
            @Override
            protected void populateViewHolder(RestaurantViewHolder viewHolder, RestaurantModel model, int position) {
                viewHolder.txtRestaurantName.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage())
                        .into(viewHolder.imageView);
                final RestaurantModel clickItem = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent fResList = new Intent(Category.this,FilteredResList.class);
                        fResList.putExtra("CatId",adapter.getRef(position).getKey());
                        startActivity(fResList);

                       //Toast.makeText(Category.this, ""+clickItem.getName(), Toast.LENGTH_SHORT).show();

//                        Intent intent = new Intent(Category.this, FilteredResList.class);
//                        final String name = clickItem.getName();
//                        intent.putExtra("Category", name);
//                        Category.this.startActivity(intent);
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);
    }
}

