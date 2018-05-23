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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ntvi.bkshop.R;
import com.ntvi.bkshop.activity.MainActivity;
import com.ntvi.bkshop.adapter.CartAdapter;
import com.ntvi.bkshop.model.CartItem;

import java.util.ArrayList;

public class FragmentCart extends Fragment{
    RecyclerView cart_rcv;
    TextView txtCount;
    CartAdapter cartAdapter;
    ArrayList<CartItem> list_cart;
    double sumPay=0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.cart_tab, container, false);
        init(view);
        cart_rcv.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        cart_rcv.setLayoutManager(layoutManager);
        // Set Recycleview
        getData();
        cartAdapter = new CartAdapter(list_cart,getContext());
        cart_rcv.setAdapter(cartAdapter);
        //txtCount.setText(String.valueOf(sumPay)+"đ");
        return view;
    }
    private void init(View view){
        list_cart= new ArrayList<>();
        cart_rcv = view.findViewById(R.id.rcv_cart);
        txtCount= view.findViewById(R.id.txtCount);
    }
    private void getData(){
        DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
        FirebaseUser auth = FirebaseAuth.getInstance().getCurrentUser();
        String uid = auth.getUid();
        mData.child("carts").child(uid).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                CartItem cartItem =dataSnapshot.getValue(CartItem.class);
                list_cart.add(cartItem);
                sumPay+= cartItem.getCount()*cartItem.getPrice();
                cartAdapter.notifyDataSetChanged();
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
