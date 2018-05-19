package com.ntvi.bkshop.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
import android.widget.ViewFlipper;
import android.content.Intent;

import com.ntvi.bkshop.adapter.MyPagerAdapter;

import com.ntvi.bkshop.R;

public class MainActivity extends AppCompatActivity {

    enum tab_count {HOME,CATE,CART};

    ViewFlipper viewFlipper;

    DrawerLayout drawerLayout;

    ViewPager viewPager;

    TabLayout tabLayout;

    android.support.v7.widget.Toolbar  toolbar_top;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        init();

        ToolBarAction();

        tab_setting();

    }
    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    private void init(){

        viewPager =(ViewPager)findViewById(R.id.viewPager_main) ;

        tabLayout=(TabLayout)findViewById(R.id.tabLayout_main);

        toolbar_top= (Toolbar)findViewById(R.id.toolbar_main);


        viewFlipper=(ViewFlipper)findViewById(R.id.viewflipper_main);

        drawerLayout=(DrawerLayout)findViewById(R.id.drawerlayout_main);

    }

    private void tab_setting(){
        FragmentManager manager = getSupportFragmentManager();
        MyPagerAdapter adapter = new MyPagerAdapter(manager);
        viewPager.setAdapter(adapter);
        viewPager.setPageTransformer(false,null);
        //Toast.makeText(getApplicationContext(),String.valueOf(adapter.getCount()),Toast.LENGTH_SHORT).show();
        tabLayout.setupWithViewPager(viewPager,true);
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
        tabLayout.setTabTextColors(R.color.grey_color,R.color.green_color);
        //tabLayout.setupWithViewPager(viewPager,true);
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
                        Toast.makeText(getApplicationContext(), "User profile", Toast.LENGTH_SHORT).show();
                        return true;
                }

                return true;
            }
        });
    }
}
