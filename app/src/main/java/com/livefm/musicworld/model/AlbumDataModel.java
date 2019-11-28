package com.livefm.musicworld.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


/**
 * Created by Kiran on 2019-11-27.
 */

public class AlbumDataModel {

    private String name;
    private String artist;
    private String url;
    @SerializedName("image")
    @Expose
    private List<ImageDetails> imgdtl;
    @SerializedName(value = "wiki",alternate = {"bio"})
    @Expose
    private WikiDetails wikiDetails;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<ImageDetails> getImgdtl() {
        return imgdtl;
    }

    public void setImgdtl(List<ImageDetails> imgdtl) {
        this.imgdtl = imgdtl;
    }

    public WikiDetails getWikiDetails() {
        return wikiDetails;
    }

    public void setWikiDetails(WikiDetails wikiDetails) {
        this.wikiDetails = wikiDetails;
    }
}
