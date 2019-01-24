package com.romain.mathieu.mynews.model.API.MostPopular;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ResultsItem implements Serializable {

    @SerializedName("column")
    private Object column;

    @SerializedName("section")
    private String section;

    @SerializedName("abstract")
    private String jsonMemberAbstract;

    @SerializedName("source")
    private String source;

    @SerializedName("asset_id")
    private long assetId;

    @SerializedName("media")
    private List<MediaItem> media;

    @SerializedName("type")
    private String type;

    @SerializedName("title")
    private String title;

    @SerializedName("url")
    private String url;

    @SerializedName("adx_keywords")
    private String adxKeywords;

    @SerializedName("id")
    private long id;

    @SerializedName("byline")
    private String byline;

    @SerializedName("published_date")
    private String publishedDate;

    @SerializedName("views")
    private int views;

    public Object getColumn() {
        return column;
    }

    public String getSection() {
        return section;
    }

    public String getJsonMemberAbstract() {
        return jsonMemberAbstract;
    }

    public String getSource() {
        return source;
    }

    public long getAssetId() {
        return assetId;
    }

    public List<MediaItem> getMedia() {
        return media;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getAdxKeywords() {
        return adxKeywords;
    }

    public long getId() {
        return id;
    }

    public String getByline() {
        return byline;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public int getViews() {
        return views;
    }
}