package com.livefm.musicworld.reterofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Kiran on 2019-11-27.
 */
public class NetworkRequestor {

    private static Retrofit retrofit;
    private static final String BASE_URL = "http://ws.audioscrobbler.com/";
    public  static final String API_KEY = "371850bd7aa037ad03f17bb5acb59557";
    private static final String FORMAT = "json";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
