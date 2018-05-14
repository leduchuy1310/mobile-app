package com.ntvi.bkshop.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ntvi.bkshop.R;
import com.ntvi.bkshop.model.CatelogyRow;
import com.ntvi.bkshop.model.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CatelogyAdapter extends RecyclerView.Adapter<CatelogyAdapter.ViewHolder>{
    ArrayList<CatelogyRow> catelogyRows;
    Context context;

    public CatelogyAdapter(ArrayList<CatelogyRow> catelogyRows, Context context) {

        this.catelogyRows = catelogyRows;
        this.context = context;
    }

    public CatelogyAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemview = layoutInflater.inflate(R.layout.catelogy_row, parent, false);
        return new ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtName.setText(catelogyRows.get(position).getmName());
        Picasso.with(context).load(catelogyRows.get(position).getmImage()).into(holder.imgHinh);

        holder.recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(holder
                .recyclerView.getContext(),LinearLayoutManager.HORIZONTAL,false);
        holder.recyclerView.setLayoutManager(layoutManager);

        holder.recyclerView.setAdapter(new ProductAdapter((ArrayList<Product>) catelogyRows.get(position).getmProducts(),context.getApplicationContext()));
    }

    @Override
    public int getItemCount() {
        return catelogyRows.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtName;
        ImageView imgHinh;
        //List<Product> productList;
        RecyclerView recyclerView;
        public ViewHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txt_Catelogy_Name);
            imgHinh = itemView.findViewById(R.id.img_Catelogy_Image);
            recyclerView = itemView.findViewById(R.id.rcv_Catelogy_List);
        }
    }
}
