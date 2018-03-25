package com.example.lawati97.deliveryapplication;

/**
 * Created by Xavier on 3/24/2018.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;


public class FilteredResList extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    Query FrestaurantList;

    FirebaseRecyclerAdapter<RestaurantModel,RestaurantViewHolder> adapter;
    String Ethnic = "Ethnic";
    String FF = "Fast Food";
    String FC = "Fast Casual";
    String CD = "Casual Dining";
    String FD = "Fine Dining";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list1);

        //Firebase
        database = FirebaseDatabase.getInstance();

        Bundle b = getIntent().getExtras();
        String value = "";
        if(b != null) {
            value = b.getString("Category");
        }

        if(value.equals(Ethnic)){
            FrestaurantList = database.getReference("Food").orderByChild("Category").equalTo("01");
        }

        if(value.equals(FF)){
            FrestaurantList = database.getReference("Food").orderByChild("Category").equalTo("02");
        }

        if(value.equals(FC)){
            FrestaurantList = database.getReference("Food").orderByChild("Category").equalTo("03");
        }

        if(value.equals(CD)){
            FrestaurantList = database.getReference("Food").orderByChild("Category").equalTo("04");
        }

        if(value.equals(FD)){
            FrestaurantList = database.getReference("Food").orderByChild("Category").equalTo("05");
        }

        //Load restaurants
        recyclerView = (RecyclerView)findViewById(R.id.recycler_food);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        loadRestaurants();

    }

    private void loadRestaurants() {
        FirebaseRecyclerAdapter<RestaurantModel,RestaurantViewHolder> adapter = new FirebaseRecyclerAdapter<RestaurantModel, RestaurantViewHolder>(RestaurantModel.class,R.layout.food_menus,RestaurantViewHolder.class,FrestaurantList) {
            @Override
            protected void populateViewHolder(RestaurantViewHolder viewHolder, RestaurantModel model, int position) {
                viewHolder.txtRestaurantName.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage())
                        .into(viewHolder.imageView);
                final RestaurantModel clickItem = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Toast.makeText(FilteredResList.this, ""+clickItem.getName(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);
    }
}
