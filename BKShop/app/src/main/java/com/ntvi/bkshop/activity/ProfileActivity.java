package com.ntvi.bkshop.activity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ntvi.bkshop.R;

import java.util.Calendar;
import com.google.firebase.auth.FirebaseAuth;
import com.ntvi.bkshop.model.User;

public class ProfileActivity extends AppCompatActivity {
    private TextInputLayout txtFullName, txtAddress;
    private EditText txtBirth;
    private Button btnSend;
    private String uid;
    private DatePickerDialog.OnDateSetListener mDateListener;
    Toolbar tool;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        tool = (Toolbar) findViewById(R.id.toolbar_profile);
        txtBirth = (EditText) findViewById(R.id.txtBirth);
        txtFullName = (TextInputLayout) findViewById(R.id.txtFullName);
        txtAddress = (TextInputLayout) findViewById(R.id.txtAddress);
        btnSend = (Button) findViewById(R.id.btnSend);
        uid = getIntent().getStringExtra("UID");
        txtBirth.setOnTouchListener(new View.OnTouchListener() {
          @Override
          public boolean onTouch(View v, MotionEvent event) {
            int intype = txtBirth.getInputType();
            txtBirth.setInputType(InputType.TYPE_NULL);
            txtBirth.onTouchEvent(event);
            txtBirth.setInputType(intype);
            return true;
          }
       });

    txtBirth.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          Calendar cal = Calendar.getInstance();
          int year = cal.get(Calendar.YEAR);
          int month = cal.get(Calendar.MONTH);
          int day = cal.get(Calendar.DAY_OF_MONTH);

          DatePickerDialog dialog = new DatePickerDialog(
                 ProfileActivity.this,
                 android.R.style.Theme_Holo_Light_Dialog,
                 mDateListener,
                 year, month, day);
          dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
          dialog.show();
      mDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
          txtBirth.setText(Integer.toString(dayOfMonth) + '-'+ Integer.toString(month + 1 ) + '-' + Integer.toString(year));
         }
       };
      }
     });

     btnSend.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
         DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
         try {
           databaseReference.child("users").child(uid).child("name").setValue(txtFullName.getEditText().toString());
           databaseReference.child("users").child(uid).child("birthday").setValue(txtBirth.getText().toString());
           databaseReference.child("users").child(uid).child("address").setValue(txtAddress.getEditText().toString());
         } catch (Exception e) {
           e.printStackTrace();
         }
       }
     });
     tool.setNavigationOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
         finish();
       }
     });
    }
}
