package com.livefm.musicworld;

import com.livefm.musicworld.reterofit.ApiRequest;
import com.livefm.musicworld.reterofit.NetworkRequestor;
import com.livefm.musicworld.utils.Constants;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LastFmApiServiceTest extends ApiAbstract<ApiRequest> {

    private ApiRequest service;

    @Before
    public void initService() {
        this.service = createService(ApiRequest.class);

    }

    @Test
    public void fetchPostsTest() throws IOException {

        Map<String,String> query = new HashMap<>();
        query.put(Constants.METHOD_SEARCH,"album"+Constants.SEARCH_KEY);
        query.put("album","artist");
        query.put(Constants.API_KEY, NetworkRequestor.API_KEY);
        query.put(Constants.FORMAT_KEY,NetworkRequestor.FORMAT_VAL);
        Assert.assertEquals(true, query);
        Assert.assertEquals(true,service.getAlbumData(query));


    }
}
