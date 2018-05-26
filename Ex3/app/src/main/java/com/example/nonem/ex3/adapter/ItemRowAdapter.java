package com.example.nonem.ex3.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nonem.ex3.R;
import com.example.nonem.ex3.model.ItemRow;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ItemRowAdapter extends RecyclerView.Adapter<ItemRowAdapter.ViewHolder>{

    ArrayList<ItemRow> arrayList;
    Context context;

    public ItemRowAdapter(ArrayList<ItemRow> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_row,parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.with(context).load(arrayList.get(position).getmImage()).
                error(R.drawable.ic_launcher_background).
                into(holder.imageView);
        holder.txtTitle.setText(arrayList.get(position).getmTitle());
        holder.txtDate.setText(arrayList.get(position).getmDate());
        holder.txtInfo.setText(arrayList.get(position).getmInfo());
        holder.txtPrice.setText(String.valueOf(arrayList.get(position).getmPrices()));
        holder.txtAddress.setText(arrayList.get(position).getmAddress());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView txtTitle;
        TextView txtInfo;
        TextView txtDate;
        TextView txtPrice;
        TextView txtAddress;
        public ViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.img_item_avatar);
            txtTitle = itemView.findViewById(R.id.txt_item_title);
            txtDate = itemView.findViewById(R.id.txt_item_date);
            txtInfo = itemView.findViewById(R.id.txt_item_info);
            txtAddress = itemView.findViewById(R.id.txt_item_address);
            txtPrice = itemView.findViewById(R.id.txt_item_prices);
        }
    }
}
