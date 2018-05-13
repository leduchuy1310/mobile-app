package com.ntvi.bkshop.model;

public class Product {
    private int mId;
    private String mName;
    private float mPrice;
    private String mImage;

    public Product(int mId, String mName, float mPrice, String mImage) {
        this.mId = mId;
        this.mName = mName;
        this.mPrice = mPrice;
        this.mImage = mImage;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public float getmPrice() {
        return mPrice;
    }

    public void setmPrice(float mPrice) {
        this.mPrice = mPrice;
    }

    public String getmImage() {
        return mImage;
    }

    public void setmImage(String mImage) {
        this.mImage = mImage;
    }
}
