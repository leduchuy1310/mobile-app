package com.ntvi.bkshop.fragment;

import android.app.Fragment;
import android.app.FragmentContainer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ntvi.bkshop.R;
import com.ntvi.bkshop.activity.MainActivity;
import com.ntvi.bkshop.adapter.CatelogyAdapter;
import com.ntvi.bkshop.adapter.ProductAdapter;
import com.ntvi.bkshop.model.CatelogyRow;
import com.ntvi.bkshop.model.Product;

import java.util.ArrayList;

public class FragmentCatelogy extends android.support.v4.app.Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.catelogy, container, false);

        initView(view);

        return  view;
    }

    public void initView(View view) {

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rcv_Catelogy);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);


        final ArrayList<CatelogyRow> arrayList = new ArrayList<>();

        // Set Recycleview
        final CatelogyAdapter catelogyAdapter = new CatelogyAdapter(arrayList, getActivity().getApplicationContext());
        recyclerView.setAdapter(catelogyAdapter);

        // Get data

        DatabaseReference mData = FirebaseDatabase.getInstance().getReference();

        mData.child("products").addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                CatelogyRow catelogyRow =dataSnapshot.getValue(CatelogyRow.class);
                arrayList.add(catelogyRow);

                catelogyAdapter.notifyDataSetChanged();

                /* final ArrayList<Product> list = new ArrayList<>();

                DatabaseReference child = FirebaseDatabase.getInstance().getReference().child("products/"+
                        dataSnapshot.getKey()+
                        "/mProducts");

                child.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot data, String s) {
                            Product product = data.getValue(Product.class);
                            list.add(product);
                            catelogyAdapter.notifyDataSetChanged();
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

                //Toast.makeText(getActivity().getApplicationContext(), list.get(2).getmName(), Toast.LENGTH_SHORT).show();
                CatelogyRow catelogyRow =dataSnapshot.getValue(CatelogyRow.class);
               *//* arrayList.add(new CatelogyRow(catelogyRow.getmName(),
                        catelogyRow.getmImage(),
                        list));*//*
               arrayList.add(catelogyRow);*/

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

}
