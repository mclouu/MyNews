/*
 * Created by Romain Mathieu => https://github.com/mclouu
 */

package com.romain.mathieu.mynews.Model.API.ArticleSearch;

import com.google.gson.annotations.SerializedName;

public class KeywordsItem {

    @SerializedName("major")
    private String major;

    @SerializedName("name")
    private String name;

    @SerializedName("rank")
    private int rank;

    @SerializedName("value")
    private String value;

    public String getMajor() {
        return major;
    }

    public String getName() {
        return name;
    }

    public int getRank() {
        return rank;
    }

    public String getValue() {
        return value;
    }
}