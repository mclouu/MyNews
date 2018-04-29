package com.romain.mathieu.mynews.Model.API.MostPopular;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MediaMetadataItem implements Serializable {

    @SerializedName("format")
    private String format;

    @SerializedName("width")
    private int width;

    @SerializedName("url")
    private String url;

    @SerializedName("height")
    private int height;

    public String getFormat() {
        return format;
    }

    public int getWidth() {
        return width;
    }

    public String getUrl() {
        return url;
    }

    public int getHeight() {
        return height;
    }
}