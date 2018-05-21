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
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ProgressBar;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.ntvi.bkshop.R;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

 /**


 /**
  * A dummy authentication store containing known user names and passwords.
  * TODO: remove after connecting to a real authentication system.
  */
 /**
  * Keep track of the login task to ensure we can cancel it if requested.
  */

 // UI references.
 private AutoCompleteTextView mEmailView;
 private EditText mPasswordView;
 private View progressBar;
 private View mLoginFormView;
 private TextView btn_sign_up;
 private FirebaseAuth auth;
 private TextView btn_forgot_password;
 @Override
 protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  btn_sign_up = (TextView) findViewById(R.id.btn_sign_up);
  btn_forgot_password = (TextView) findViewById(R.id.btn_forgot_password);
  auth = FirebaseAuth.getInstance();

  if (auth.getCurrentUser() != null) {
   Intent intent = new Intent(LoginActivity.this, MainActivity.class);
   intent.putExtra("UID", auth.getCurrentUser().getUid());
   startActivity(intent);
   finish();
  }
  setContentView(R.layout.activity_login);
  // Set up the login form.
  mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
//  populateAutoComplete();

  mPasswordView = (EditText) findViewById(R.id.password);
  mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
   @Override
   public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
    if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
     attemptLogin();
     return true;
    }
    return false;
   }
  });

  Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
  mEmailSignInButton.setOnClickListener(new OnClickListener() {
   @Override
   public void onClick(View view) {
    attemptLogin();
   }
  });

  mLoginFormView = findViewById(R.id.login_form);
  progressBar = findViewById(R.id.login_progress);



 }

  public void SignUpClick(View target){
   startActivity(new Intent(LoginActivity.this, SignupActivity.class));

  }

 public void ResetPasswordClick(View target){
  startActivity(new Intent(LoginActivity.this, ForgotPassword.class));
 }

 /**
  * Callback received when a permissions request has been completed.
  */


 /**
  * Attempts to sign in or register the account specified by the login form.
  * If there are form errors (invalid email, missing fields, etc.), the
  * errors are presented and no actual login attempt is made.
  */
 private void attemptLogin() {

  // Reset errors.
  mEmailView.setError(null);
  mPasswordView.setError(null);

  // Store values at the time of the login attempt.
  final String email = mEmailView.getText().toString();
  final String password = mPasswordView.getText().toString();

  boolean cancel = false;
  View focusView = null;

  // Check for a valid password, if the user entered one.
  if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
   mPasswordView.setError(getString(R.string.error_invalid_password));
   focusView = mPasswordView;
   cancel = true;
  }

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
   auth.signInWithEmailAndPassword(email, password)
           .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
             // If sign in fails, display a message to the user. If sign in succeeds
             // the auth state listener will be notified and logic to handle the
             // signed in user can be handled in the listener.
             progressBar.setVisibility(View.GONE);
             if (!task.isSuccessful()) {
              // there was an error
              if (password.length() < 6) {
               mPasswordView.setError(getString(R.string.minium_password));
              } else {
               Toast.makeText(LoginActivity.this, getString(R.string.auth_fail), Toast.LENGTH_LONG).show();
              }
             } else {

              Intent intent = new Intent(LoginActivity.this, MainActivity.class);
              intent.putExtra("UID", auth.getCurrentUser().getUid());
              startActivity(intent);
              finish();
             }
            }
           });

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

   mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
   mLoginFormView.animate().setDuration(shortAnimTime).alpha(
           show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
    @Override
    public void onAnimationEnd(Animator animation) {
     mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
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
   mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
  }
 }
}

