package com.tyj.craftshow.bean;

/**
 * Created by kyle on 2019/2/11.
 */
public class BannerModel {

    private String imageUrl;
    private String mTips;
    private int img;

    public String getTips() {
        return mTips;
    }

    public void setTips(String tips) {
        mTips = tips;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
