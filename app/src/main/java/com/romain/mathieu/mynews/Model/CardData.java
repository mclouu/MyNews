/*
 * Created by Romain Mathieu => https://github.com/mclouu
 */

package com.romain.mathieu.mynews.Model;


public class CardData {

    private String title, subtitle, imageURL;


    public CardData(String mTitle, String mSubtitle) {

        this.title = mTitle;
        this.subtitle = mSubtitle;
        //this.imageURL = mImageURL;
    }

//    public String getImageURL() {
//        return imageURL;
//    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getTitle() {
        return title;
    }
}
