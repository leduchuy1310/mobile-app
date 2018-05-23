package com.ntvi.bkshop.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ntvi.bkshop.R;
import com.ntvi.bkshop.activity.MainActivity;
import com.ntvi.bkshop.activity.ViewProductActivity;
import com.ntvi.bkshop.model.CartItem;
import com.ntvi.bkshop.model.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private ArrayList<Product> productList;
    private Context context;
    public ProductAdapter(ArrayList<Product> ProductList, Context context) {
        this.productList = ProductList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.category_row_item,parent,false);
        return new ViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,final int position) {
        holder.txtName.setText(productList.get(position).getmName());
        holder.txtPrices.setText(String.valueOf( productList.get(position).getmPrice()));
        holder.btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemName;
                CartItem cartItem;
                String itemImage;
                Double itemPrice;
                int itemCount;
                itemName = productList.get(position).getmName();
                itemImage = productList.get(position).getmImage();
                itemPrice = productList.get(position).getmPrice();
                itemCount = 1;
                cartItem = new CartItem(itemImage,itemName,itemPrice,itemCount);
                String uid="";
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("carts");
                FirebaseAuth auth = FirebaseAuth.getInstance();
                FirebaseUser currentUser = auth.getCurrentUser();
                if(currentUser != null){
                     uid = currentUser.getUid();
                }
                ref.child(uid).push().setValue(cartItem);
                Toast.makeText(context,"Thêm vào giỏ hàng thành công",Toast.LENGTH_SHORT).show();
            }
        });
        Picasso.with(context).load(productList.get(position).getmImage()).
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
        ImageButton btnAddCart;
        private ViewHolder(View itemView) {
            super(itemView);
           txtName = itemView.findViewById(R.id.txt_category_item_name);
           txtPrices = itemView.findViewById(R.id.txt_category_item_prices);
           imgAvatar = itemView.findViewById(R.id.img_category_item_avatar);
           btnAddCart = itemView.findViewById(R.id.btn_add_cart);
        }
    }
}
