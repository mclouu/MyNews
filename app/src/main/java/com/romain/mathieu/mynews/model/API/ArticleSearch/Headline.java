/*
 * Created by Romain Mathieu => https://github.com/mclouu
 */

package com.romain.mathieu.mynews.model.API.ArticleSearch;

import com.google.gson.annotations.SerializedName;

public class Headline {

    @SerializedName("sub")
    private Object sub;

    @SerializedName("content_kicker")
    private Object contentKicker;

    @SerializedName("name")
    private Object name;

    @SerializedName("main")
    private String main;

    @SerializedName("seo")
    private Object seo;

    @SerializedName("print_headline")
    private Object printHeadline;

    @SerializedName("kicker")
    private String kicker;

    public Object getSub() {
        return sub;
    }

    public Object getContentKicker() {
        return contentKicker;
    }

    public Object getName() {
        return name;
    }

    public String getMain() {
        return main;
    }

    public Object getSeo() {
        return seo;
    }

    public Object getPrintHeadline() {
        return printHeadline;
    }

    public String getKicker() {
        return kicker;
    }
}