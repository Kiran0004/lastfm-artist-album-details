package com.livefm.musicworld.repository;

import android.util.Log;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.livefm.musicworld.response.ServerResponse;
import com.livefm.musicworld.reterofit.ApiRequest;
import com.livefm.musicworld.reterofit.NetworkRequestor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Kiran on 2019-11-27.
 */


public class AlbumRepository {
    private static final String TAG = AlbumRepository.class.getSimpleName();
    private ApiRequest apiRequest;

    public AlbumRepository() {
        apiRequest = NetworkRequestor.getRetrofitInstance().create(ApiRequest.class);
    }

    public LiveData<ServerResponse> getAlbumData(String method, String type, String key, String format) {
        final MutableLiveData<ServerResponse> data = new MutableLiveData<>();
        apiRequest.getAlbumData(method,type, key,format)
                .enqueue(new Callback<ServerResponse>() {


                    @Override
                    public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                        Log.d(TAG, "onResponse response:: " + response);
                        if (response.body() != null) {
                            data.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<ServerResponse> call, Throwable t) {
                        data.setValue(null);
                    }
                });
        return data;
    }
}
