/*
 * Created by Romain Mathieu => https://github.com/mclouu
 */

package com.romain.mathieu.mynews.utils;

import java.util.Hashtable;

public class MyConstant {

    public static final String BUNDLED_EXTRA = "BUNDLED_EXTRA";
    public static final String SEARCH_ID = "SEARCH_ID";
    public static final String NOTIF_ID = "NOTIF_ID";

    static final String MY_FILE = "MySharedPreference.xml";
    static final String KEY_INT = "KEY_INT";
    static final String KEY_LIST = "KEY_LIST";


    public String BASE_URL = "http://api.nytimes.com/"; // http://api.nytimes.com/svc/topstories/v2/world.json?api-key=e5ace90626ec4c7495500a0dbb327980
    String API_KEY = "603VoqkXe4T0cL2iwBnuUndaTW7vBz5G";

    public String GET_SECTION_TOP(int section) {
        Hashtable<Integer, String> category = new Hashtable<>();
        category.put(25, "home");
        category.put(24, "arts");
        category.put(23, "automobiles");
        category.put(22, "books");
        category.put(21, "business");
        category.put(20, "fashion");
        category.put(29, "food");
        category.put(18, "health");
        category.put(17, "insider");
        category.put(16, "magazine");
        category.put(15, "movies");
        category.put(14, "national");
        category.put(13, "nyregion");
        category.put(12, "obituaries");
        category.put(11, "opinion");
        category.put(10, "politics");
        category.put(9, "realestate");
        category.put(8, "science");
        category.put(7, "sports");
        category.put(6, "sundayreview");
        category.put(5, "technology");
        category.put(4, "theater");
        category.put(3, "tmagazine");
        category.put(2, "travel");
        category.put(1, "upshot");
        category.put(0, "world");

        return category.get(section);
    }

    public String GET_SECTION_MOST(int section) {
        Hashtable<Integer, String> category = new Hashtable<>();
        category.put(35, "art");
        category.put(34, "Automobiles");
        category.put(33, "Blogs");
        category.put(32, "Books");
        category.put(31, "Business Day");
        category.put(30, "Education");
        category.put(29, "Fashion & Style");
        category.put(28, "Food");
        category.put(27, "Health");
        category.put(26, "Job Market");
        category.put(25, "Magazine");
        category.put(24, "membercenter");
        category.put(23, "Movies");
        category.put(22, "Multimedia");
        category.put(21, "N.Y.%20%2F%20Region");
        category.put(20, "NYT Now");
        category.put(19, "Obituaries");
        category.put(18, "Open");
        category.put(17, "Opinion");
        category.put(16, "Public Editor");
        category.put(15, "Real Estate");
        category.put(14, "Science");
        category.put(13, "Sports");
        category.put(12, "Style");
        category.put(11, "Sunday Review");
        category.put(10, "T Magazine");
        category.put(9, "Technology");
        category.put(8, "The Upshot");
        category.put(7, "Theater");
        category.put(6, "Times Insider");
        category.put(5, "Today’s Paper");
        category.put(4, "Travel");
        category.put(3, "U.S.");
        category.put(2, "World");
        category.put(1, "Your Money");
        category.put(0, "all-sections");
        return category.get(section);
    }
}
