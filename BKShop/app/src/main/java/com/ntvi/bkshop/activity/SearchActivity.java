package com.ntvi.bkshop.activity;

import android.net.Uri;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import android.content.Intent;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.ntvi.bkshop.adapter.CategoryAdapter;
import com.ntvi.bkshop.adapter.ProductAdapter;
import com.ntvi.bkshop.model.Advertisement;

import com.ntvi.bkshop.R;
import com.ntvi.bkshop.model.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;

    EditText edtSearch;


    android.support.v7.widget.Toolbar  toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search);

        init();

        ToolBarAction();

        //LoadData();

        //final ArrayList<Product> arrayList = new ArrayList<>();

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                LoadData(edtSearch.getText().toString());
            }
        });
    }

    private void LoadData(final String search) {
        final ArrayList<Product> products = new ArrayList<>();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rcv_search);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);

        recyclerView.setLayoutManager(layoutManager);

        final ProductAdapter productAdapter = new ProductAdapter(products, this);

        recyclerView.setAdapter(productAdapter);
        DatabaseReference mData = FirebaseDatabase.getInstance().getReference("products");
        mData.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(final DataSnapshot dataSnapshot, String s) {
                DatabaseReference child = FirebaseDatabase.getInstance().getReference().child("products/"+
                        dataSnapshot.getKey());
                child.child("mProducts").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot data, String s) {
                        Product product = data.getValue(Product.class);
                        if (product.getmName().toLowerCase().contains(search.toLowerCase())) {
                            products.add(product);
                            productAdapter.notifyDataSetChanged();
                        }

                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


                //Toast.makeText(SearchActivity.this, products.size()+"", Toast.LENGTH_SHORT).show();
               // Toast.makeText(SearchActivity.this, dataSnapshot.getValue().toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void init(){
        drawerLayout= (DrawerLayout)findViewById(R.id.drawerlayout_search);

        toolbar = (Toolbar)findViewById(R.id.toolbar_search);

        edtSearch = findViewById(R.id.edt_search);

    }

    private void ToolBarAction(){
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
