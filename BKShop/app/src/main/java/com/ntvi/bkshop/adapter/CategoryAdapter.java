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
import com.ntvi.bkshop.model.CategoryRow;
import com.ntvi.bkshop.model.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>{
    private ArrayList<CategoryRow> categoryRows;
    private Context context;

    public CategoryAdapter(ArrayList<CategoryRow> categoryRows, Context context) {

        this.categoryRows = categoryRows;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemview = layoutInflater.inflate(R.layout.category_row, parent, false);
        return new ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtName.setText(categoryRows.get(position).getmName());
        Picasso.with(context).load(categoryRows.get(position).getmImage()).into(holder.imgHinh);

        holder.recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(holder
                .recyclerView.getContext(),LinearLayoutManager.HORIZONTAL,false);
        holder.recyclerView.setLayoutManager(layoutManager);

        holder.recyclerView.setAdapter(new ProductAdapter((ArrayList<Product>) categoryRows.get(position).getmProducts(),context.getApplicationContext()));
    }

    @Override
    public int getItemCount() {
        return categoryRows.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtName;
        ImageView imgHinh;
        //List<Product> productList;
        RecyclerView recyclerView;
        public ViewHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txt_category_name);
            imgHinh = itemView.findViewById(R.id.img_category);
            recyclerView = itemView.findViewById(R.id.rcv_category_list);
        }
    }
}
