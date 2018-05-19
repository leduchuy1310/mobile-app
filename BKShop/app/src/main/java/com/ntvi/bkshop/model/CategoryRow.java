package com.ntvi.bkshop.model;

import java.util.List;

public class CategoryRow {
    private String mName;
    private String mImage;
    private List <Product> mProducts;

    public CategoryRow() {
    }

    public CategoryRow(String mName, String mImage, List<Product> mProducts) {
        this.mName = mName;
        this.mImage = mImage;
        this.mProducts = mProducts;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmImage() {
        return mImage;
    }

    public void setmImage(String mImage) {
        this.mImage = mImage;
    }

    public List<Product> getmProducts() {
        return mProducts;
    }

    public void setmProducts(List<Product> mProducts) {
        this.mProducts = mProducts;
    }
}
