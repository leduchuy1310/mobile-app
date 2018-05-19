package com.ntvi.bkshop.model;

import android.provider.ContactsContract;

import java.util.Date;

public class User {
 private String name;
 private String gender;
 private Date birthday;
 private ContactsContract.CommonDataKinds.Email email;
 private String address;
 private ContactsContract.CommonDataKinds.Phone phone;


 public User(){
  //default
 }

 public ContactsContract.CommonDataKinds.Email getEmail() {
  return email;
 }

 public String getAddress() {
  return address;
 }

 public ContactsContract.CommonDataKinds.Phone getPhone() {
  return phone;
 }

 public Date getBirthday() {
  return birthday;
 }

 public String getGender() {
  return gender;
 }

 public String getName() {
  return name;
 }



 public  User(String image, String address, String name, String gender, Date birthday){
  this.address = address;
  this.birthday = birthday;
  this.email = email;
  this.gender = gender;
  this.phone = phone;
  this.name = name;
 }
}
