/*
 * Created by Romain Mathieu => https://github.com/mclouu
 */

package com.romain.mathieu.mynews.Model.API.TopStories;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NYTAPI_topstories {

    @SerializedName("copyright")
    private String copyright;

    @SerializedName("last_updated")
    private String lastUpdated;

    @SerializedName("section")
    private String section;

    @SerializedName("results")
    private List<ResultsItem> results;

    @SerializedName("num_results")
    private int numResults;

    @SerializedName("status")
    private String status;

    public String getCopyright() {
        return copyright;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public String getSection() {
        return section;
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

    @Override
    public String toString() {
        return
                "NYTAPI_topstories{" +
                        "copyright = '" + copyright + '\'' +
                        ",last_updated = '" + lastUpdated + '\'' +
                        ",section = '" + section + '\'' +
                        ",results = '" + results + '\'' +
                        ",num_results = '" + numResults + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}