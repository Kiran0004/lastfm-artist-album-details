package com.livefm.musicworld.reterofit;

import com.livefm.musicworld.response.ServerResponse;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Created by Kiran on 2019-11-27.
 */

public interface ApiRequest {


    @GET("/2.0/")
    Call<ServerResponse> getAlbumData(
            @Query("method") String query,
            @Query("album") String album,
            @Query("api_key") String apikey,
            @Query("format") String format
    );
}
