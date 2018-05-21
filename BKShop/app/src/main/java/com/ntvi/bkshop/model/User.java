package com.ntvi.bkshop.model;

import android.provider.ContactsContract;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class User {
 private String name;
 private String gender;
 private String birthday;
 private String email;
 private String address;
 private String phone;


 public User() {
  //default
 }
public  User(String address, String birthday,String email,String gender,String name,String phone){
  this.address=address;
  this.birthday=birthday;
  this.email=email;
  this.gender=gender;
  this.name=name;
  this.phone=phone;
}
 public User(String email) {
  this.email = email;
  this.name = "";
  this.gender = "male";
  this.address = "";
  String pattern = "dd-MM-yyyy";
  SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

  String date = simpleDateFormat.format(new Date());
  this.birthday = date;
  this.phone = "";
  //default
 }





 public void setEmail(String email) {
  this.email = email;
 }

 public void setAddress(String address) {
  this.address = address;
 }

 public void setPhone(String phone) {
  this.phone = phone;
 }

 public void setBirthday(String birthday) {
  this.birthday =birthday;
 }

 public void setGender(String gender) {
  this.gender = gender;
 }

 public void setName(String name) {
  this.name = name;
 }
 public String getEmail() {
  return email;
 }

 public String getAddress() {
  return address;
 }

 public String getPhone() {
  return phone;
 }

 public String getBirthday() {
  return birthday;
 }

 public String getGender() {
  return gender;
 }

 public String getName() {
  return name;
 }
}


