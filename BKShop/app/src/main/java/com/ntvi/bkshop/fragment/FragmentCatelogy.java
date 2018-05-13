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

import com.ntvi.bkshop.R;
import com.ntvi.bkshop.adapter.CatelogyAdapter;
import com.ntvi.bkshop.model.CatelogyRow;

import java.util.ArrayList;

public class FragmentCatelogy extends Fragment {
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

        ArrayList<CatelogyRow> arrayList = new ArrayList<>();


        // dữ liệu test
        arrayList.add(new CatelogyRow("Điện thoại",
                "http://наклейка52.рф/images/product/s/1192651c35.jpg",
                null));
        arrayList.add(new CatelogyRow("Máy tính",
                "http://наклейка52.рф/images/product/s/1192651c35.jpg",
                null));

        arrayList.add(new CatelogyRow("Quần áo",
                "http://наклейка52.рф/images/product/s/1192651c35.jpg",
                null));

        arrayList.add(new CatelogyRow("Điện thoại",
                "http://наклейка52.рф/images/product/s/1192651c35.jpg",
                null));






        CatelogyAdapter catelogyAdapter = new CatelogyAdapter(arrayList, getActivity().getApplicationContext());
        recyclerView.setAdapter(catelogyAdapter);
    }
}
