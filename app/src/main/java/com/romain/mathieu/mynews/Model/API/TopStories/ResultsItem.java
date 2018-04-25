/*
 * Created by Romain Mathieu => https://github.com/mclouu
 */

package com.romain.mathieu.mynews.Model.API.TopStories;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultsItem {

    @SerializedName("per_facet")
    private List<String> perFacet;

    @SerializedName("subsection")
    private String subsection;

    @SerializedName("item_type")
    private String itemType;

    @SerializedName("org_facet")
    private List<String> orgFacet;

    @SerializedName("section")
    private String section;

    @SerializedName("abstract")
    private String jsonMemberAbstract;

    @SerializedName("title")
    private String title;

    @SerializedName("des_facet")
    private List<String> desFacet;

    @SerializedName("url")
    private String url;

    @SerializedName("short_url")
    private String shortUrl;

    @SerializedName("material_type_facet")
    private String materialTypeFacet;

    @SerializedName("multimedia")
    private List<MultimediaItem> multimedia;

    @SerializedName("geo_facet")
    private List<Object> geoFacet;

    @SerializedName("updated_date")
    private String updatedDate;

    @SerializedName("created_date")
    private String createdDate;

    @SerializedName("byline")
    private String byline;

    @SerializedName("published_date")
    private String publishedDate;

    @SerializedName("kicker")
    private String kicker;

    public List<String> getPerFacet() {
        return perFacet;
    }

    public String getSubsection() {
        return subsection;
    }

    public String getItemType() {
        return itemType;
    }

    public List<String> getOrgFacet() {
        return orgFacet;
    }

    public String getSection() {
        return section;
    }

    public String getJsonMemberAbstract() {
        return jsonMemberAbstract;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getDesFacet() {
        return desFacet;
    }

    public String getUrl() {
        return url;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public String getMaterialTypeFacet() {
        return materialTypeFacet;
    }

    public List<MultimediaItem> getMultimedia() {
        return multimedia;
    }

    public List<Object> getGeoFacet() {
        return geoFacet;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getByline() {
        return byline;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public String getKicker() {
        return kicker;
    }

    @Override
    public String toString() {
        return
                "ResultsItem{" +
                        "per_facet = '" + perFacet + '\'' +
                        ",subsection = '" + subsection + '\'' +
                        ",item_type = '" + itemType + '\'' +
                        ",org_facet = '" + orgFacet + '\'' +
                        ",section = '" + section + '\'' +
                        ",abstract = '" + jsonMemberAbstract + '\'' +
                        ",title = '" + title + '\'' +
                        ",des_facet = '" + desFacet + '\'' +
                        ",url = '" + url + '\'' +
                        ",short_url = '" + shortUrl + '\'' +
                        ",material_type_facet = '" + materialTypeFacet + '\'' +
                        ",multimedia = '" + multimedia + '\'' +
                        ",geo_facet = '" + geoFacet + '\'' +
                        ",updated_date = '" + updatedDate + '\'' +
                        ",created_date = '" + createdDate + '\'' +
                        ",byline = '" + byline + '\'' +
                        ",published_date = '" + publishedDate + '\'' +
                        ",kicker = '" + kicker + '\'' +
                        "}";
    }
}