package com.ntvi.bkshop.model;

import java.io.Serializable;

public class Product implements Serializable {
    private String mName;
    private double mPrice;
    private String mImage;
    private int mNum;
    private String mInfo;
    private String mTag;
    public Product() {
    }

    public Product(String mName, double mPrice, String mImage, int mNum, String mInfo, String mtag) {
        this.mName = mName;
        this.mPrice = mPrice;
        this.mImage = mImage;
        this.mNum = mNum;
        this.mInfo = mInfo;
        this.mTag = mtag;
    }

    public String getmTag() {
        return mTag;
    }

    public void setmTag(String mTag) {
        this.mTag = mTag;
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