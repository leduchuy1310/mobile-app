package com.ntvi.bkshop.model;

public class Product {
    private String mName;
    private double mPrice;
    private String mImage;
    private int mNum;
    private String mInfo;

    public Product() {
    }

    public Product(String mName, double mPrice, String mImage, int mNum, String mInfo) {
        this.mName = mName;
        this.mPrice = mPrice;
        this.mImage = mImage;
        this.mNum = mNum;
        this.mInfo = mInfo;
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

    public int getmNum() {
        return mNum;
    }

    public void setmNum(int mNum) {
        this.mNum = mNum;
    }

    public String getmInfo() {
        return mInfo;
    }

    public void setmInfo(String mInfo) {
        this.mInfo = mInfo;
    }
}
