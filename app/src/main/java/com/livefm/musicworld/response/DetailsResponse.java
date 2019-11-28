package com.livefm.musicworld.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.livefm.musicworld.model.AlbumDataModel;
import com.livefm.musicworld.model.WikiDetails;

public class DetailsResponse {

    @SerializedName(value = "album",alternate = {"artist","track"})
    @Expose
    private AlbumDataModel albumDataModel;


    public AlbumDataModel getAlbumDataModel() {
        return albumDataModel;
    }

    public void setAlbumDataModel(AlbumDataModel albumDataModel) {
        this.albumDataModel = albumDataModel;
    }
}
