package com.ntvi.bkshop.fragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.ntvi.bkshop.model.Advertisement;
import com.squareup.picasso.Picasso;

public class FragmentHome extends Fragment {
    ViewFlipper viewFlipper;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.home_tab, container, false);
        viewFlipper = view.findViewById(R.id.viewflipper_main);
        ViewFlipperOnRun();
        return view;
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
                ImageView imageView = new ImageView(getContext());
                //load image from url to ImageView using Picasso
                if(value != null)
                    Picasso.with(getContext()).load(value.image).into(imageView);
                //FIT_XY
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                //onclick to url
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        if(getContext()!=null)
                            i.setClass(getContext(),WebViewActivity.class);
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

