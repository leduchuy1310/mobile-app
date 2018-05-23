package com.ntvi.bkshop.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ntvi.bkshop.R;
import com.ntvi.bkshop.model.CategoryRow;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HomeProductAdapter extends  RecyclerView.Adapter<HomeProductAdapter.ViewHolder>{
    private ArrayList<CategoryRow> categoryRows;
    private Context context;

    public HomeProductAdapter(ArrayList<CategoryRow> categoryRows, Context context) {

        this.categoryRows = categoryRows;
        this.context = context;
    }

    @NonNull
    @Override
    public HomeProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemview = layoutInflater.inflate(R.layout.item_row_home, parent, false);
        return new HomeProductAdapter.ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeProductAdapter.ViewHolder holder, int position) {
        //holder.txtName.setText(categoryRows.get(position).getmName());
        Picasso.with(context).load(categoryRows.get(position).getmImage()).into(holder.imgHinh);
        holder.imgHinh.setScaleType(ImageButton.ScaleType.FIT_XY);
    }

    @Override
    public int getItemCount() {
        return categoryRows.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        //TextView txtName;
        ImageButton imgHinh;
        public ViewHolder(View itemView) {
            super(itemView);
            //txtName = itemView.findViewById(R.id.txt_row_home);
            imgHinh = itemView.findViewById(R.id.img_row_home);
        }
    }
}

