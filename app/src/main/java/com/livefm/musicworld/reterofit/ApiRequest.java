package com.livefm.musicworld.reterofit;

import com.livefm.musicworld.response.DetailsResponse;
import com.livefm.musicworld.response.ServerResponse;


import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;


/**
 * Created by Kiran on 2019-11-27.
 */

public interface ApiRequest {


    @GET("/2.0/")
    Call<ServerResponse> getAlbumData(
            @QueryMap Map<String, String> options

    );
    @GET("/2.0/")
    Call<DetailsResponse> getDetailsInfo(
            @QueryMap Map<String, String> options

    );
}
