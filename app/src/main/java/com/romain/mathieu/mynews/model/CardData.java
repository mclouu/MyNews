/*
 * Created by Romain Mathieu => https://github.com/mclouu
 */

package com.romain.mathieu.mynews.model;


public class CardData {

    private String title, subtitle, date, imageURL, articleURL;


    public CardData(String mTitle, String mSubtitle, String mDate, String mImageURL, String articleURL) {

        this.title = mTitle;
        this.subtitle = mSubtitle;
        this.date = mDate;
        this.imageURL = mImageURL;
        this.articleURL = articleURL;

    }


    public String getSubtitle() {
        return subtitle;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getArticleURL() {
        return articleURL;
    }
}
