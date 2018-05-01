/*
 * Created by Romain Mathieu => https://github.com/mclouu
 */

package com.romain.mathieu.mynews.Model.API.ArticleSearch;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response {

    @SerializedName("docs")
    private List<DocsItem> docs;

    @SerializedName("meta")
    private Meta meta;

    public List<DocsItem> getDocs() {
        return docs;
    }

    public Meta getMeta() {
        return meta;
    }
}