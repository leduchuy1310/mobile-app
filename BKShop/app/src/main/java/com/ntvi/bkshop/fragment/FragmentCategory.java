package com.ntvi.bkshop.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ntvi.bkshop.R;
import com.ntvi.bkshop.adapter.CategoryAdapter;
import com.ntvi.bkshop.model.CategoryRow;

import java.util.ArrayList;

public class FragmentCategory extends Fragment {
    ArrayList<CategoryRow> arrayList;
    CategoryAdapter categoryAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cate_tab, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rcv_category);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>();
        // Set Recycleview
        if(getContext() !=null)
            categoryAdapter = new CategoryAdapter(arrayList,getContext());
        recyclerView.setAdapter(categoryAdapter);
        initView(view);
        return  view;
    }

    public void initView(View view) {
        // Get data
        DatabaseReference mData = FirebaseDatabase.getInstance().getReference();

        mData.child("products").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                CategoryRow categoryRow =dataSnapshot.getValue(CategoryRow.class);
                arrayList.add(categoryRow);
                categoryAdapter.notifyDataSetChanged();
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
