package com.example.lawati97.deliveryapplication;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.ImageView;

import org.w3c.dom.Text;

/**
 * Created by Joshua Sim on 3/16/2018.
 */

public class RestaurantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtRestaurantName;
    public ImageView imageView;
    public ImageView favView;
    private ItemClickListener itemClickListener;

    public RestaurantViewHolder(View itemView) {
        super(itemView);

        txtRestaurantName = (TextView)itemView.findViewById(R.id.restaurant_name);
        imageView = (ImageView)itemView.findViewById(R.id.menu_image);
        favView = (ImageView)itemView.findViewById(R.id.fav);


        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);

    }
}
