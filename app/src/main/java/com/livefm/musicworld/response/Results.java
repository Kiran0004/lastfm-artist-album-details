package com.livefm.musicworld.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * Created by Kiran on 2019-11-27.
 */

public class Results {

    @SerializedName("albummatches")
    @Expose
    private MethodMatchDetails matchDetails;

    public MethodMatchDetails getMatchDetails() {
        return matchDetails;
    }

    public void setMatchDetails(MethodMatchDetails matchDetails) {
        this.matchDetails = matchDetails;
    }
}
