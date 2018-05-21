package com.ntvi.bkshop.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import android.content.Intent;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ntvi.bkshop.adapter.MyPagerAdapter;

import com.ntvi.bkshop.R;
import com.ntvi.bkshop.model.User;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    static int a = 0;

    String uid;

    NavigationView navigationView;

    ViewFlipper viewFlipper;

    DrawerLayout drawerLayout;

    ViewPager viewPager;

    TabLayout tabLayout;

    android.support.v7.widget.Toolbar  toolbar_top;

    CircleImageView profile_image;

    TextView txtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        uid = getIntent().getStringExtra("UID");

        init();

        ToolBarAction();

        tab_setting();

        LoadUserProfile();

        ActionSidebar();

    }
    private  void ActionSidebar(){
        getSupportActionBar();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_app_info:
                        break;
                    case R.id.action_edit_info:
                        Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                        intent.putExtra("uid", uid);
                        startActivity(intent);
                        break;
                    case R.id.action_sign_out:
                        FirebaseAuth.getInstance().signOut();
                        Intent intent1 = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent1);
                        break;

                        default:break;
                }
                return false;
            }
        });


    }
    private void LoadUserProfile(){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    public void showData (DataSnapshot dataSnapshot){

        User user = new User();
        for(DataSnapshot ds : dataSnapshot.getChildren()){
            Log.d("sad", ds.toString());
            Log.d("sas", uid);
            if ( ds.getKey().equals(uid)){
                user.setEmail(ds.getValue(User.class).getEmail());
                Toast.makeText(getApplication(),ds.getValue(User.class).toString(), Toast.LENGTH_SHORT).show();
                txtName.setText(user.getEmail());
                break;
            }
        }
    }


    private void init(){

        navigationView=(NavigationView)findViewById(R.id.navigationView);

        viewPager =(ViewPager)findViewById(R.id.viewPager_main) ;

        tabLayout=(TabLayout)findViewById(R.id.tabLayout_main);

        toolbar_top= (Toolbar)findViewById(R.id.toolbar_main);

        profile_image = (CircleImageView) findViewById(R.id.profile_image);

        txtName=navigationView.getHeaderView(0).findViewById(R.id.txtName);

        viewFlipper=(ViewFlipper)findViewById(R.id.viewflipper_main);

        drawerLayout=(DrawerLayout)findViewById(R.id.drawerlayout_main);

    }

    private void tab_setting(){

        FragmentManager manager = getSupportFragmentManager();
        MyPagerAdapter adapter = new MyPagerAdapter(manager);
        viewPager.setAdapter(adapter);
        viewPager.setPageTransformer(false,null);
        int limit = (adapter.getCount() > 1 ? adapter.getCount() - 1 : 1);
        viewPager.setOffscreenPageLimit(limit);
        //Toast.makeText(getApplicationContext(),String.valueOf(adapter.getCount()),Toast.LENGTH_SHORT).show();
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(R.color.grey_color,R.color.green_color);
        if(tabLayout != null){
            TabLayout.Tab home = tabLayout.getTabAt(0);
            TabLayout.Tab cate = tabLayout.getTabAt(1);
            TabLayout.Tab cart = tabLayout.getTabAt(2);
            if(home != null)
                home.setIcon(R.drawable.home_selector);
            if(cate != null)
                cate.setIcon(R.drawable.cate_selector);
            if(cart != null)
                cart.setIcon(R.drawable.cart_selector);
        }
        //tabLayout.setBackgroundColor(getResources().getColor(R.color.grey_color,null));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        //tabLayout.setupWithViewPager(viewPager);
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
                                    //finish();

                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        };
                        searchThread.start();
                        return true;
                    case R.id.action_userprofile:
                        drawerLayout.openDrawer(Gravity.END);
                        navigationView.setVisibility(View.VISIBLE);
                        return true;
                }

                return true;
            }
        });
    }
}
