package com.ntvi.bkshop.activity;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManagerNonConfig;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import android.content.Intent;

import com.android.volley.toolbox.JsonArrayRequest;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.ntvi.bkshop.fragment.FragmentCatelogy;
import com.ntvi.bkshop.model.Advertisement;

import com.ntvi.bkshop.R;
import com.ntvi.bkshop.model.CatelogyRow;
import com.ntvi.bkshop.model.Product;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static int a = 0;
    ViewFlipper viewFlipper;

    DrawerLayout drawerLayout;

    ViewPager viewPager;

    TabLayout tabLayout;

    android.support.v7.widget.Toolbar  toolbar_top;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // TEST CATELOGY;
        //test();
        catelogy();
        init();

      ToolBarAction();



       ViewFlipperOnRun();
    }


    private void catelogy() {

        android.app.FragmentManager  fragmentManager = getFragmentManager();
        android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        FragmentCatelogy fragmentCatelogy = new FragmentCatelogy();
        fragmentTransaction.add(R.id.activity_main, fragmentCatelogy);
        fragmentTransaction.commit();
    }

    private void init(){

        viewPager =(ViewPager)findViewById(R.id.viewPager_main) ;

        tabLayout=(TabLayout)findViewById(R.id.tabLayout_main);

        toolbar_top= (Toolbar)findViewById(R.id.toolbar_main);


        viewFlipper=(ViewFlipper)findViewById(R.id.viewflipper_main);

        drawerLayout=(DrawerLayout)findViewById(R.id.drawerlayout_main);

    }

    private void tab_setting(){

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return new Fragment();
                }
                return null;
            }

            @Override
            public int getCount() {
                return 0;
            }
        });
        tabLayout.setupWithViewPager(viewPager);
    }
    private  void ToolBarAction(){
        toolbar_top.inflateMenu(R.menu.main_top);

        toolbar_top.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.action_search:
//                        Toast.makeText(getApplicationContext(), "Search view", Toast.LENGTH_SHORT).show();
                        Thread searchThread = new Thread(){
                        @Override
                            public void run() {
                                try {
                                    sleep(0);
                                    Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                                    startActivity(intent);
                                    finish();

                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        };
                        searchThread.start();
                        return true;
                    case R.id.action_userprofile:
                        Toast.makeText(getApplicationContext(), "User profile", Toast.LENGTH_SHORT).show();
                        return true;
                }

                return true;
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
                ImageView imageView = new ImageView(getApplicationContext());
                //load image from url to ImageView using Picasso
                Picasso.with(getApplicationContext()).load(value.image).into(imageView);
                //FIT_XY
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                //onclick to url
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse("https://www.google.com"));
                        startActivity(i);
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
        Animation animation_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_right_view_in);
        Animation animation_out=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_right_view_out);
        viewFlipper.setInAnimation(animation_in);
        viewFlipper.setOutAnimation(animation_out);
    }
}
