package com.example.ibisfood.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ibisfood.Interface.ItemClickListener;
import com.example.ibisfood.Model.PostMessageModel;
import com.example.ibisfood.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtMenuNameCategory;
    public ImageView imageMenuCategory;


    private ItemClickListener itemClickListener;


    public MenuViewHolder(@NonNull View itemView) {
        super(itemView);

        txtMenuNameCategory = (TextView)itemView.findViewById(R.id.menu_name);
        imageMenuCategory = (ImageView)itemView.findViewById(R.id.menu_image);




        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {

        itemClickListener.onClick(v, getAdapterPosition(),false);
    }
}
