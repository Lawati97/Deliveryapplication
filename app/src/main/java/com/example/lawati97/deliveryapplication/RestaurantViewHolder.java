package com.example.lawati97.deliveryapplication;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.ImageView;

/**
 * Created by Joshua Sim on 3/16/2018.
 */

public class RestaurantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView restaurant_name;
    public ImageView restaurant_image;

    private ItemClickListener itemCLickListener;

    public RestaurantViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void onClick(View view) {

    }
}
