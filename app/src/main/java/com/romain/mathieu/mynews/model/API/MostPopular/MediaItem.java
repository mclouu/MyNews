package com.romain.mathieu.mynews.model.API.MostPopular;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class MediaItem implements Serializable {

    @SerializedName("copyright")
    private String copyright;

    @SerializedName("media-metadata")
    private List<MediaMetadataItem> mediaMetadata;

    @SerializedName("subtype")
    private String subtype;

    @SerializedName("caption")
    private String caption;

    @SerializedName("type")
    private String type;

    @SerializedName("approved_for_syndication")
    private int approvedForSyndication;

    public String getCopyright() {
        return copyright;
    }

    public List<MediaMetadataItem> getMediaMetadata() {
        return mediaMetadata;
    }

    public String getSubtype() {
        return subtype;
    }

    public String getCaption() {
        return caption;
    }

    public String getType() {
        return type;
    }

    public int getApprovedForSyndication() {
        return approvedForSyndication;
    }
}