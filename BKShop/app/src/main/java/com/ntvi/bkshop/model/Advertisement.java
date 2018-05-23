package com.ntvi.bkshop.model;

public class Advertisement {
    private String url;
    private String title;
    private String image;
    public Advertisement(){
        //default
    }

    public  Advertisement(String url,String image,String title){
        this.url=url;
        this.image=image;
        this.title=title;
    }

    public String getUrl() {
        return url;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }
}
