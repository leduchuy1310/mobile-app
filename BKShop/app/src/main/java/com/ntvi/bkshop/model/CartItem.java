package com.ntvi.bkshop.model;

public class CartItem {
    private String itemImage;
    private String itemName;
    private double price;
    private int count;
    public CartItem(){}
    public CartItem(String itemImage,String itemName,double price,int count){
        this.itemImage=itemImage;
        this.itemName=itemName;
        this.price=price;
        this.count=count;
    }

    public int getCount() {
        return count;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemImage() {
        return itemImage;
    }

    public double getPrice() {
        return price;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
