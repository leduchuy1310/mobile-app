package com.ntvi.bkshop.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ntvi.bkshop.R;
import com.ntvi.bkshop.model.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    ArrayList<Product> productList;
    Context context;

    public ProductAdapter(ArrayList<Product> ProductList, Context context) {
        this.productList = ProductList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.catelogy_row_item,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtName.setText(productList.get(position).getmName());
        holder.txtPrices.setText(String.valueOf(productList.get(position).getmPrice()));
        Picasso.with(context).load(productList.get(position).getmImage()).
                error(R.drawable.common_google_signin_btn_icon_dark).
                into(holder.imgAvatar);

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtName;
        TextView txtPrices;
        ImageView imgAvatar;

        public ViewHolder(View itemView) {
            super(itemView);
           txtName = itemView.findViewById(R.id.txt_Catelogy_Item_Name);
           txtPrices = itemView.findViewById(R.id.txt_Catelogy_Item_Prices);
           imgAvatar = itemView.findViewById(R.id.img_Catelogy_Item_Avatar);
        }
    }
}
