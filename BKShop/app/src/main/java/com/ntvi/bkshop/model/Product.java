package com.ntvi.bkshop.model;

public class Product {
    private String mName;
    private double mPrice;
    private String mImage;

    public Product() {
    }

    public Product(String mName, double mPrice, String mImage) {

        this.mName = mName;
        this.mPrice = mPrice;
        this.mImage = mImage;
    }


    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public double getmPrice() {
        return mPrice;
    }

    public void setmPrice(double mPrice) {
        this.mPrice = mPrice;
    }

    public String getmImage() {
        return mImage;
    }

    public void setmImage(String mImage) {
        this.mImage = mImage;
    }
}
