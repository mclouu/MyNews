/*
 * Created by Romain Mathieu => https://github.com/mclouu
 */

package com.romain.mathieu.mynews.model;

import com.romain.mathieu.mynews.model.API.ArticleSearch.NYTAPIArticleSearch;
import com.romain.mathieu.mynews.model.API.MostPopular.NYTAPIMostPopular;
import com.romain.mathieu.mynews.model.API.TopStories.NYTAPITopstories;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import static io.reactivex.android.schedulers.AndroidSchedulers.mainThread;

public class NYTStreams {

    public static Observable<NYTAPITopstories> streamFetchTop(String section, String apiKey) {
        NYTService nytService = NYTService.retrofit.create(NYTService.class);
        return nytService.getPostTop(section, apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

    public static Observable<NYTAPIMostPopular> streamFetchMost(String section, String apiKey) {
        NYTService nytService = NYTService.retrofit.create(NYTService.class);
        return nytService.getPostMost(section, apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

    public static Observable<NYTAPIArticleSearch> streamFetchArticle(String apiKey, String fQuery, String sort) {
        NYTService nytService = NYTService.retrofit.create(NYTService.class);
        return nytService.getPostArticle(apiKey, fQuery, sort)
                .subscribeOn(Schedulers.io())
                .observeOn(mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

    public static Observable<NYTAPIArticleSearch> streamFetchSearch(String apiKey, String query, String fQuery, String sort, String beginDate, String endDate) {
        NYTService nytService = NYTService.retrofit.create(NYTService.class);
        return nytService.getPostSearch(apiKey, query, fQuery, sort, beginDate, endDate)
                .subscribeOn(Schedulers.io())
                .observeOn(mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }
}
