package com.mwj.depthview.depthview_lib;

public class DepthDataBean {

    private float mPrice;
    private float mVolume;

    private float currentVolume;

    public float getVolume() {
        return mVolume;
    }

    public void setVolume(float volume) {
        this.mVolume = volume;
    }

    public float getPrice() {
        return mPrice;
    }

    public void setPrice(float price) {
        this.mPrice = price;
    }

    public float getCurrentVolume() {
        return currentVolume;
    }

    public void setCurrentVolume(float currentVolume) {
        this.currentVolume = currentVolume;
    }
}
