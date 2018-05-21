package com.ntvi.bkshop.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
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
import com.ntvi.bkshop.adapter.ProductAdapter;
import com.ntvi.bkshop.model.CartItem;
import com.ntvi.bkshop.model.CategoryRow;
import com.ntvi.bkshop.model.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FragmentChoose extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.choose, container, false);

        ImageView imageAvatar = view.findViewById(R.id.img_Choose_avatar);
        TextView txtName = view.findViewById(R.id.txt_Choose_Name);
        TextView txtPrices = view.findViewById(R.id.txt_Choose_Prices);
        TextView txtNum = view.findViewById(R.id.txt_Choose_Num);
        TextView txtInfo = view.findViewById(R.id.txt_Choose_Info);
        ImageButton addCart = view.findViewById(R.id.btn_add_cart_choose);
        final ImageView imgIconCungLoai = view.findViewById(R.id.img_Choose_CungLoai);


        RecyclerView recyclerView = view.findViewById(R.id.rcv_Choose);

        Bundle bundle = getArguments();

        if (bundle!= null){
            final Product product = (Product) bundle.getSerializable("product");

            Picasso.with(getActivity()).load(product.getmImage()).
                    error(R.drawable.common_google_signin_btn_icon_dark).
                    into(imageAvatar);

            addCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String itemName;
                    CartItem cartItem;
                    String itemImage;
                    Double itemPrice;
                    int itemCount;
                    itemName = product.getmName();
                    itemImage = product.getmImage();
                    itemPrice = product.getmPrice();
                    itemCount = 1;
                    cartItem = new CartItem(itemImage,itemName,itemPrice,itemCount);
                    String uid="";
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("carts");
                    FirebaseAuth auth = FirebaseAuth.getInstance();
                    FirebaseUser currentUser = auth.getCurrentUser();
                    if(currentUser != null){
                        uid = currentUser.getUid();
                    }
                    ref.child(uid).push().setValue(cartItem);
                    Toast.makeText(getContext(),"Thêm vào giỏ hàng thành công",Toast.LENGTH_SHORT).show();
                }
            });
            txtName.setText(product.getmName());


            if (product.getmNum() > 0){
                txtNum.setText("Còn hàng" );
            }
            else {
                txtNum.setText("Hiện sản phẩm đã hết hàng");
            }


            txtPrices.setText("Giá: " + product.getmPrice()+"VND");


            txtInfo.setText(product.getmInfo());



            LinearLayoutManager layoutManager = new LinearLayoutManager(recyclerView.getContext(),LinearLayoutManager.HORIZONTAL,false);
            recyclerView.setLayoutManager(layoutManager);

            final ArrayList<Product> productsList = new ArrayList<>();

            final ProductAdapter productAdapter = new ProductAdapter(productsList, getActivity().getApplicationContext());

            recyclerView.setAdapter(productAdapter);

            DatabaseReference mData = FirebaseDatabase.getInstance().getReference();

            mData.child("products").addChildEventListener(new ChildEventListener() {
               @Override
               public void onChildAdded(final DataSnapshot dataSnapshot, String s) {
                   final CategoryRow categoryRow = dataSnapshot.getValue(CategoryRow.class);

                   DatabaseReference child = FirebaseDatabase.getInstance().getReference().child("products/"+
                           dataSnapshot.getKey());
                   child.addChildEventListener(new ChildEventListener() {
                       @Override
                       public void onChildAdded(DataSnapshot data, String s) {
                           if (data.getValue().equals(product.getmTag())){
                               Picasso.with(getActivity()).load(categoryRow.getmImage()).
                                       error(R.drawable.common_google_signin_btn_icon_dark).
                                       into(imgIconCungLoai);

                               DatabaseReference list = FirebaseDatabase.getInstance().getReference().child("products/"+
                                       dataSnapshot.getKey()+
                                       "/mProducts");
                               list.addChildEventListener(new ChildEventListener() {
                                   @Override
                                   public void onChildAdded(DataSnapshot endData, String s) {
                                       productsList.add(endData.getValue(Product.class));
                                       productAdapter.notifyDataSetChanged();

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



        //recyclerView.setAdapter(new ProductAdapter((ArrayList<Product>) catelogyRows.get(position).getmProducts(),context.getApplicationContext()));


        return view;
    }
}
