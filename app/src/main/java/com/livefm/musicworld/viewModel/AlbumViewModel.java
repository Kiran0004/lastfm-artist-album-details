package com.livefm.musicworld.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.livefm.musicworld.repository.AlbumRepository;
import com.livefm.musicworld.response.ServerResponse;
import com.livefm.musicworld.reterofit.NetworkRequestor;


/**
 * Created by Kiran on 2019-11-27.
 */

public class AlbumViewModel extends AndroidViewModel {

    private AlbumRepository albumRepository;
    private LiveData<ServerResponse> articleResponseLiveData;
    private LiveData<ServerResponse> artistResponseLiveData;
    private String method;
    private String type;

    public AlbumViewModel(@NonNull Application application,String method,String type) {
        super(application);

        albumRepository = new AlbumRepository();
        if(method.equals("album")) {
            this.articleResponseLiveData = albumRepository.getAlbumData(method + ".search", "believe", NetworkRequestor.API_KEY, NetworkRequestor.FORMAT_VAL);
        }
    }

    public LiveData<ServerResponse> getAlbumResponseLiveData() {
        return articleResponseLiveData;
    }

    public LiveData<ServerResponse> getArtistResponseLiveData() {
        return artistResponseLiveData;
    }

    public void setArtistResponseLiveData(LiveData<ServerResponse> artistResponseLiveData) {
        this.artistResponseLiveData = artistResponseLiveData;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
