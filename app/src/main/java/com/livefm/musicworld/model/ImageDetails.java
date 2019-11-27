package com.livefm.musicworld.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * Created by Kiran on 2019-11-27.
 */

public class ImageDetails {
    @SerializedName("#text")
    @Expose
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
