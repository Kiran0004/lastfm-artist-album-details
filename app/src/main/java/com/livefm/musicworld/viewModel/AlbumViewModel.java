package com.livefm.musicworld.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.livefm.musicworld.repository.AlbumRepository;
import com.livefm.musicworld.response.ServerResponse;


/**
 * Created by Kiran on 2019-11-27.
 */

public class AlbumViewModel extends AndroidViewModel {

    private AlbumRepository albumRepository;
    private LiveData<ServerResponse> articleResponseLiveData;

    public AlbumViewModel(@NonNull Application application) {
        super(application);

        albumRepository = new AlbumRepository();
        this.articleResponseLiveData = albumRepository.getAlbumData("album.search", "believe","371850bd7aa037ad03f17bb5acb59557","json");
    }

    public LiveData<ServerResponse> getAlbumResponseLiveData() {
        return articleResponseLiveData;
    }
}
