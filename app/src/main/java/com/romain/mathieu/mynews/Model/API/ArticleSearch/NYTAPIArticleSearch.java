/*
 * Created by Romain Mathieu => https://github.com/mclouu
 */

package com.romain.mathieu.mynews.Model.API.ArticleSearch;

import com.google.gson.annotations.SerializedName;

public class NYTAPIArticleSearch {

    @SerializedName("copyright")
    private String copyright;

    @SerializedName("response")
    private Response response;

    @SerializedName("status")
    private String status;

    public String getCopyright() {
        return copyright;
    }

    public Response getResponse() {
        return response;
    }

    public String getStatus() {
        return status;
    }
}