package com.romain.mathieu.mynews.Model.API.MostPopular;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class NYTAPIMostPopular implements Serializable {

    @SerializedName("copyright")
    private String copyright;

    @SerializedName("results")
    private List<ResultsItem> results;

    @SerializedName("num_results")
    private int numResults;

    @SerializedName("status")
    private String status;

    public String getCopyright() {
        return copyright;
    }

    public List<ResultsItem> getResults() {
        return results;
    }

    public int getNumResults() {
        return numResults;
    }

    public String getStatus() {
        return status;
    }
}