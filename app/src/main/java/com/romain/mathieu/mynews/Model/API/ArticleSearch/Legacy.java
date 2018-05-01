/*
 * Created by Romain Mathieu => https://github.com/mclouu
 */

package com.romain.mathieu.mynews.Model.API.ArticleSearch;

import com.google.gson.annotations.SerializedName;

public class Legacy {

    @SerializedName("xlarge")
    private String xlarge;

    @SerializedName("xlargewidth")
    private int xlargewidth;

    @SerializedName("xlargeheight")
    private int xlargeheight;

    public String getXlarge() {
        return xlarge;
    }

    public int getXlargewidth() {
        return xlargewidth;
    }

    public int getXlargeheight() {
        return xlargeheight;
    }
}