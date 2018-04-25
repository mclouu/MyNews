/*
 * Created by Romain Mathieu => https://github.com/mclouu
 */

package com.romain.mathieu.mynews.Model;

import java.util.Hashtable;

class MyConstant {


    String BASE_URL = "http://api.nytimes.com/"; // http://api.nytimes.com/svc/topstories/v2/world.json?api-key=e5ace90626ec4c7495500a0dbb327980
    String API_KEY = "e5ace90626ec4c7495500a0dbb327980";

    String GET_SECTION(int section) {
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

}
