package com.example.nonem.ex3.model;

public class ItemRow {
    private String mTitle;
    private String mDate;
    private double mPrices;
    private String mInfo;
    private String mImage;
    private String mAddress;
    private String mLink;

    public ItemRow() {
    }

    public ItemRow(String mTitle, String mDate, double mPrices, String mInfo, String mImage, String mAddress, String mLink) {
        this.mTitle = mTitle;
        this.mDate = mDate;
        this.mPrices = mPrices;
        this.mInfo = mInfo;
        this.mImage = mImage;
        this.mAddress = mAddress;
        this.mLink = mLink;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public double getmPrices() {
        return mPrices;
    }

    public void setmPrices(double mPrices) {
        this.mPrices = mPrices;
    }

    public String getmInfo() {
        return mInfo;
    }

    public void setmInfo(String mInfo) {
        this.mInfo = mInfo;
    }

    public String getmImage() {
        return mImage;
    }

    public void setmImage(String mImage) {
        this.mImage = mImage;
    }

    public String getmAddress() {
        return mAddress;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public void setmLink(String mLink) {
        this.mLink = mLink;
    }
}
