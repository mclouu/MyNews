/*
 * Created by Romain Mathieu => https://github.com/mclouu
 */

package com.romain.mathieu.mynews.Model;


public class CardData {

    private String title, subtitle, date, imageURL;


    public CardData(String mTitle, String mSubtitle, String mDate, String mImageURL) {

        this.title = mTitle;
        this.subtitle = mSubtitle;
        this.date = mDate;
        this.imageURL = mImageURL;
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
}
