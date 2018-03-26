package com.romain.mathieu.mynews.Model;

/**
 * Created by romain on 17/03/2018.
 */

public class CardData {

    private String title, subtitle, imageURL;


    public CardData(String mTitle, String mSubtitle, String mImageURL) {

        this.title = mTitle;
        this.subtitle = mSubtitle;
        this.imageURL = mImageURL;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getTitle() {
        return title;
    }
}
