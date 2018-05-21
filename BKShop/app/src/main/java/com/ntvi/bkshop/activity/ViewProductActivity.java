package com.ntvi.bkshop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ntvi.bkshop.R;
import com.ntvi.bkshop.fragment.FragmentChoose;

public class ViewProductActivity extends AppCompatActivity {
    ImageView imageAvatar;
    TextView txtPrices;
    TextView txtNum;
    TextView txtName;
    TextView txtInfo;
    ImageView imgIconCungLoai;
    RecyclerView recyclerView;
    android.support.v7.widget.Toolbar  toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_view_product);
        init();
        ChooseITem();
        ToolBarAction();

    }

    private void init(){
        toolbar = (Toolbar)findViewById(R.id.toolbar_view_product);
//        imageAvatar = (ImageView)findViewById(R.id.img_Choose_avatar);
//        txtName = (TextView)findViewById(R.id.txt_Choose_Name);
//        txtPrices = (TextView)findViewById(R.id.txt_Choose_Prices);
//        txtNum = (TextView)findViewById(R.id.txt_Choose_Num);
//        txtInfo = (TextView)findViewById(R.id.txt_Choose_Info);
//        imgIconCungLoai =(ImageView) findViewById(R.id.img_Choose_CungLoai);
//        recyclerView = (RecyclerView)findViewById(R.id.rcv_Choose);
    }
    private void ChooseITem() {

        android.app.FragmentManager  fragmentManager = getFragmentManager();
        android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("product_adapter");

        if(bundle != null){

            FragmentChoose fragmentChoose = new FragmentChoose();

            fragmentChoose.setArguments(bundle);
            fragmentTransaction.replace(R.id.layout_view_product, fragmentChoose);

        }
        fragmentTransaction.commit();
    }
    private void ToolBarAction(){
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite,null));
        toolbar.setTitle(getResources().getString(R.string.txtInfo));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
