package com.ntvi.bkshop.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ntvi.bkshop.R;
import com.ntvi.bkshop.activity.ViewProductActivity;
import com.ntvi.bkshop.model.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HomeItemHotAdapter extends RecyclerView.Adapter<HomeItemHotAdapter.ViewHolder> {

    String itemName;
    String itemImage;
    Double itemPrice;
    int itemCount;
    private ArrayList<Product> productList;
    private Context context;
    public HomeItemHotAdapter(ArrayList<Product> ProductList, Context context) {
        this.productList = ProductList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_hot_home,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        itemName = productList.get(position).getmName();
        itemImage = productList.get(position).getmImage();
        itemPrice = productList.get(position).getmPrice();
        itemCount = 1;
        holder.txtName.setText(itemName);
        holder.txtPrices.setText(String.valueOf(itemPrice));
        Picasso.with(context).load(itemImage).
                error(R.drawable.common_google_signin_btn_icon_dark).
                into(holder.imgAvatar);
        holder.imgAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewProductActivity.class);

                Bundle bundle = new Bundle();

                bundle.putSerializable("product", productList.get(position));

                intent.putExtra("product_adapter", bundle);

                context.startActivity(intent);
                //Toast.makeText(context, "xxx", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtName;
        TextView txtPrices;
        ImageView imgAvatar;
        private ViewHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txt_hot_item_name);
            txtPrices = itemView.findViewById(R.id.txt_price_hot_item);
            imgAvatar = itemView.findViewById(R.id.img_hot_item_avatar);
        }
    }
}
