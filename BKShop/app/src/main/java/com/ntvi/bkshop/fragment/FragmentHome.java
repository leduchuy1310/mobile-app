package com.ntvi.bkshop.fragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ntvi.bkshop.R;
import com.ntvi.bkshop.activity.WebViewActivity;
import com.ntvi.bkshop.adapter.HomeItemHotAdapter;
import com.ntvi.bkshop.adapter.HomeProductAdapter;
import com.ntvi.bkshop.model.Advertisement;
import com.ntvi.bkshop.model.CategoryRow;
import com.ntvi.bkshop.model.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FragmentHome extends Fragment {
    ViewFlipper viewFlipper;
    RecyclerView list_sp;
    RecyclerView list_hot;
    ArrayList<CategoryRow> arrayList = new ArrayList<>();
    ArrayList<Product> arrayListPro= new ArrayList<>();
    HomeProductAdapter homeProductAdapter;
    HomeItemHotAdapter homeItemHotAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.home_tab, container, false);
        init(view);
        list_sp.setHasFixedSize(true);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        list_sp.setLayoutManager(layoutManager1);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(list_hot.getContext(),LinearLayoutManager.HORIZONTAL,false);
        list_hot.setHasFixedSize(true);
        list_hot.setLayoutManager(layoutManager2);
        loadMain();
        // Set Recycleview
        homeProductAdapter = new HomeProductAdapter(arrayList,getContext());
        homeItemHotAdapter = new HomeItemHotAdapter(arrayListPro,getContext());
        list_sp.setAdapter(homeProductAdapter);
        list_hot.setAdapter(homeItemHotAdapter);
        ViewFlipperOnRun();
        return view;
    }

    private void init(View view){
        viewFlipper = view.findViewById(R.id.viewflipper_main);
        list_sp=view.findViewById(R.id.rcv_list);
        list_hot=view.findViewById(R.id.rcv_hot_list);
    }

    private void loadMain(){
        DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
        mData.child("products").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                CategoryRow categoryRow = dataSnapshot.getValue(CategoryRow.class);
                arrayList.add(categoryRow);
                int size = categoryRow.getmProducts().size();
                for(int i=0;i<size;i++)
                    arrayListPro.add(categoryRow.getmProducts().get(i));
                homeProductAdapter.notifyDataSetChanged();
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

    private  void ViewFlipperOnRun(){

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference myRef = database.getReference("advertisement");

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //get data to Object
                Advertisement value = dataSnapshot.getValue(Advertisement.class);
                //create a ImageView
                final ImageView imageView = new ImageView(getContext());
                //load image from url to ImageView using Picasso
                if(value != null)
                    Picasso.with(getContext()).load(value.getImage()).into(imageView);
                //FIT_XY
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setContentDescription(value.getUrl());
                imageView.setTag(value.getTitle());
                //onclick to url
                imageView.callOnClick();
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        if(getContext()!=null)
                            i.setClass(getContext(),WebViewActivity.class);
                        i.putExtra("URL_WEBVIEW",imageView.getContentDescription());
                        i.putExtra("TITLE_WEBVIEW",imageView.getTag().toString());
                        ActivityOptions options =
                                ActivityOptions.makeCustomAnimation(getContext(), R.anim.fade_in, R.anim.fade_out);
                        startActivity(i, options.toBundle());
                    }
                });
                //add image to viewFipper
                viewFlipper.addView(imageView);
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
        //5000 miliseconds change
        viewFlipper.setFlipInterval(5000);
        //auto start
        viewFlipper.setAutoStart(true);
        //animation right in & out
        Animation animation_in = AnimationUtils.loadAnimation(getContext(),R.anim.slide_right_view_in);
        Animation animation_out=AnimationUtils.loadAnimation(getContext(),R.anim.slide_right_view_out);
        viewFlipper.setInAnimation(animation_in);
        viewFlipper.setOutAnimation(animation_out);
    }
}

