package com.ntvi.bkshop.activity;

import android.net.Uri;
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

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.ntvi.bkshop.model.Advertisement;

import com.ntvi.bkshop.R;
import com.squareup.picasso.Picasso;

public class SearchActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;

    android.support.v7.widget.Toolbar  toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search);

        init();

        ToolBarAction();
    }

    private void init(){
        drawerLayout= (DrawerLayout)findViewById(R.id.drawerlayout_search);

        toolbar = (Toolbar)findViewById(R.id.toolbar_search);

    }

    private void ToolBarAction(){
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread mainThread = new Thread(){
                    @Override
                    public void run() {
                        try {
                            sleep(0);
                            Intent intent = new Intent(SearchActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };
                mainThread.start();
            }
        });
    }
}
