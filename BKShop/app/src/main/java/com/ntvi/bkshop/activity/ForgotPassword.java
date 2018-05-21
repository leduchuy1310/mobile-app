package com.ntvi.bkshop.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.ntvi.bkshop.R;


/**
 * A login screen that offers login via email/password.
 */
public class ForgotPassword extends AppCompatActivity{

 /**
  * Id to identity READ_CONTACTS permission request.
  */

 /**
  * A dummy authentication store containing known user names and passwords.
  * TODO: remove after connecting to a real authentication system.
  */
 /**
  * Keep track of the login task to ensure we can cancel it if requested.
  */

 // UI references.
 private AutoCompleteTextView mEmailView;
 private View progressBar;
 private View forgotForm;
 private Button btn_forgot_password;
 private FirebaseAuth auth;

 @Override
 protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);

  setContentView(R.layout.activity_forgot_password);
  // Set up the forgot form.
  mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
  auth = FirebaseAuth.getInstance();
  btn_forgot_password = (Button) findViewById(R.id.btn_forgot_password);

  btn_forgot_password.setOnClickListener(new OnClickListener() {
   @Override
   public void onClick(View view) {
    attemForgotPassword();
   }
  });

  forgotForm = findViewById(R.id.forgot_form);
  progressBar = findViewById(R.id.login_progress);
 }
 /**
  * Callback received when a permissions request has been completed.
  */

 /**
  * Attempts to sign in or register the account specified by the login form.
  * If there are form errors (invalid email, missing fields, etc.), the
  * errors are presented and no actual login attempt is made.
  */
  private void attemForgotPassword() {
  // Reset errors.
  mEmailView.setError(null);

  // Store values at the time of the login attempt.
  String email = mEmailView.getText().toString();

  boolean cancel = false;
  View focusView = null;


  // Check for a valid email address.
  if (TextUtils.isEmpty(email)) {
   mEmailView.setError(getString(R.string.error_field_required));
   focusView = mEmailView;
   cancel = true;
  } else if (!isEmailValid(email)) {
   mEmailView.setError(getString(R.string.error_invalid_email));
   focusView = mEmailView;
   cancel = true;
  }

  if (cancel) {
   // There was an error; don't attempt login and focus the first
   // form field with an error.
   focusView.requestFocus();
  } else {
   // Show a progress spinner, and kick off a background task to
   // perform the user login attempt.
   showProgress(true);
   auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
    @Override
    public void onComplete(@NonNull Task<Void> task) {
     if (task.isSuccessful()) {
      Toast.makeText(ForgotPassword.this, "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
      Intent intent = new Intent(ForgotPassword.this, LoginActivity.class);
      startActivity(intent);
      finish();

     } else {
      Toast.makeText(ForgotPassword.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();

     }

     progressBar.setVisibility(View.GONE);
    }
   });
//   mAuthTask = new UserLoginTask(email, password);
//   mAuthTask.execute((Void) null);
  }
 }

 private boolean isEmailValid(String email) {
  //TODO: Replace this with your own logic
  return email.contains("@");
 }

 private boolean isPasswordValid(String password) {
  //TODO: Replace this with your own logic
  return password.length() > 4;
 }

 /**
  * Shows the progress UI and hides the login form.
  */
 @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
 private void showProgress(final boolean show) {
  // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
  // for very easy animations. If available, use these APIs to fade-in
  // the progress spinner.
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
   int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

   forgotForm.setVisibility(show ? View.GONE : View.VISIBLE);
   forgotForm.animate().setDuration(shortAnimTime).alpha(
           show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
    @Override
    public void onAnimationEnd(Animator animation) {
     forgotForm.setVisibility(show ? View.GONE : View.VISIBLE);
    }
   });

   progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
   progressBar.animate().setDuration(shortAnimTime).alpha(
           show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
    @Override
    public void onAnimationEnd(Animator animation) {
     progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }
   });
  } else {
   // The ViewPropertyAnimator APIs are not available, so simply show
   // and hide the relevant UI components.
   progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
   forgotForm.setVisibility(show ? View.GONE : View.VISIBLE);
  }
 }
}

