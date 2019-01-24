/*
 * Created by Romain Mathieu => https://github.com/mclouu
 */

package com.romain.mathieu.mynews.model.API.ArticleSearch;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Byline {

    @SerializedName("original")
    private String original;

    @SerializedName("person")
    private List<PersonItem> person;

    @SerializedName("organization")
    private Object organization;

    public String getOriginal() {
        return original;
    }

    public List<PersonItem> getPerson() {
        return person;
    }

    public Object getOrganization() {
        return organization;
    }
}