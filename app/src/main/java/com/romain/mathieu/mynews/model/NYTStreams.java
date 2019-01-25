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

    public static Observable<NYTAPITopstories> streamFetchTop(String section) {
        NYTService nytService = NYTService.retrofit.create(NYTService.class);
        return nytService.getPostTop(section)
                .subscribeOn(Schedulers.io())
                .observeOn(mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

    public static Observable<NYTAPIMostPopular> streamFetchMost(String section) {
        NYTService nytService = NYTService.retrofit.create(NYTService.class);
        return nytService.getPostMost(section)
                .subscribeOn(Schedulers.io())
                .observeOn(mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

    public static Observable<NYTAPIArticleSearch> streamFetchArticle() {
        NYTService nytService = NYTService.retrofit.create(NYTService.class);
        return nytService.getPostArticle()
                .subscribeOn(Schedulers.io())
                .observeOn(mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

    public static Observable<NYTAPIArticleSearch> streamFetchSearch() {
        NYTService nytService = NYTService.retrofit.create(NYTService.class);
        return nytService.getPostSearch()
                .subscribeOn(Schedulers.io())
                .observeOn(mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }
}
