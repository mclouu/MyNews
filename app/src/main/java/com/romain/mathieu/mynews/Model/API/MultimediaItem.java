package com.romain.mathieu.mynews.Model.API;

import com.google.gson.annotations.SerializedName;

public class MultimediaItem {

    @SerializedName("copyright")
    private String copyright;

    @SerializedName("subtype")
    private String subtype;

    @SerializedName("format")
    private String format;

    @SerializedName("width")
    private int width;

    @SerializedName("caption")
    private String caption;

    @SerializedName("type")
    private String type;

    @SerializedName("url")
    private String url;

    @SerializedName("height")
    private int height;

    public String getCopyright() {
        return copyright;
    }

    public String getSubtype() {
        return subtype;
    }

    public String getFormat() {
        return format;
    }

    public int getWidth() {
        return width;
    }

    public String getCaption() {
        return caption;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return
                "MultimediaItem{" +
                        "copyright = '" + copyright + '\'' +
                        ",subtype = '" + subtype + '\'' +
                        ",format = '" + format + '\'' +
                        ",width = '" + width + '\'' +
                        ",caption = '" + caption + '\'' +
                        ",type = '" + type + '\'' +
                        ",url = '" + url + '\'' +
                        ",height = '" + height + '\'' +
                        "}";
    }
}