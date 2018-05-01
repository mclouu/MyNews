/*
 * Created by Romain Mathieu => https://github.com/mclouu
 */

package com.romain.mathieu.mynews.Model.API.ArticleSearch;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DocsItem {

    @SerializedName("snippet")
    private String snippet;

    @SerializedName("keywords")
    private List<KeywordsItem> keywords;

    @SerializedName("section_name")
    private String sectionName;

    @SerializedName("new_desk")
    private String newDesk;

    @SerializedName("source")
    private String source;

    @SerializedName("blog")
    private Blog blog;

    @SerializedName("uri")
    private String uri;

    @SerializedName("pub_date")
    private String pubDate;

    @SerializedName("multimedia")
    private List<MultimediaItem> multimedia;

    @SerializedName("word_count")
    private int wordCount;

    @SerializedName("type_of_material")
    private String typeOfMaterial;

    @SerializedName("web_url")
    private String webUrl;

    @SerializedName("_id")
    private String id;

    @SerializedName("headline")
    private Headline headline;

    @SerializedName("byline")
    private Byline byline;

    @SerializedName("document_type")
    private String documentType;

    public String getSnippet() {
        return snippet;
    }

    public List<KeywordsItem> getKeywords() {
        return keywords;
    }

    public String getSectionName() {
        return sectionName;
    }

    public String getNewDesk() {
        return newDesk;
    }

    public String getSource() {
        return source;
    }

    public Blog getBlog() {
        return blog;
    }

    public String getUri() {
        return uri;
    }

    public String getPubDate() {
        return pubDate;
    }

    public List<MultimediaItem> getMultimedia() {
        return multimedia;
    }

    public int getWordCount() {
        return wordCount;
    }

    public String getTypeOfMaterial() {
        return typeOfMaterial;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public String getId() {
        return id;
    }

    public Headline getHeadline() {
        return headline;
    }

    public Byline getByline() {
        return byline;
    }

    public String getDocumentType() {
        return documentType;
    }
}