package com.livefm.musicworld.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.livefm.musicworld.model.AlbumDataModel;

import java.util.List;


/**
 * Created by Kiran on 2019-11-27.
 */

public class MethodMatchDetails {

    @SerializedName(value = "album",alternate = {"artist","track"})
    @Expose
    private List<AlbumDataModel> albumDataModels = null;

    public List<AlbumDataModel> getAlbumDataModels() {
        return albumDataModels;
    }

    public void setAlbumDataModels(List<AlbumDataModel> albumDataModels) {
        this.albumDataModels = albumDataModels;
    }
}
