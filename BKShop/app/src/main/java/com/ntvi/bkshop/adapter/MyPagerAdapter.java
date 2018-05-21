package com.ntvi.bkshop.adapter;

import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ntvi.bkshop.fragment.FragmentCart;
import com.ntvi.bkshop.fragment.FragmentHome;
import com.ntvi.bkshop.fragment.FragmentCategory;

public class MyPagerAdapter extends FragmentStatePagerAdapter {
    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Fragment frag=null;
        switch (position){
            case 0:
                frag=new FragmentHome();
                break;
            case 1:
                frag=new FragmentCategory();
                break;
            case 2:
                frag= new FragmentCart();
                break;
        }
        return frag;
    }
    @Override
    public int getCount() {
        return 3;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title="Home";
                break;
            case 1:
                title="Danh mục";
                break;
            case 2:
                title="Giỏ hàng";
                break;
        }

        return title;
    }


}