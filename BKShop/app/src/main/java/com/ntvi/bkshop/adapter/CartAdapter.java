package com.ntvi.bkshop.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ntvi.bkshop.R;
import com.ntvi.bkshop.model.CartItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder>{
    private ArrayList<CartItem> list_cart_item;
    private Context context;

    public CartAdapter(ArrayList<CartItem> list_cart_item, Context context) {
        this.list_cart_item = list_cart_item;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemview = layoutInflater.inflate(R.layout.cart_item, parent, false);
        return new ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemName.setText(list_cart_item.get(position).getItemName());
        holder.itemPrice.setText("Đơn giá: "+Double.toString(list_cart_item.get(position).getPrice()));
        holder.itemCount.setText(Integer.toString(list_cart_item.get(position).getCount()));
        Picasso.with(context).load(list_cart_item.get(position).getItemImage()).into(holder.itemImage);
    }

    @Override
    public int getItemCount() {
        return list_cart_item.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView itemName;
        ImageView itemImage;
        EditText itemCount;
        TextView itemPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.txtCartName);
            itemImage = itemView.findViewById(R.id.cartImage);
            itemCount = itemView.findViewById(R.id.txtCount);
            itemPrice = itemView.findViewById(R.id.txtPriceItem);
        }
    }
}
