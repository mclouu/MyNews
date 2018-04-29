/*
 * Created by Romain Mathieu => https://github.com/mclouu
 */

package com.romain.mathieu.mynews.Model;

import com.romain.mathieu.mynews.Model.API.MostPopular.NYTAPIMostPopular;
import com.romain.mathieu.mynews.Model.API.TopStories.NYTAPITopstories;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NYTStreams {

    public static Observable<NYTAPITopstories> streamFetchTop(String section) {
        NYTService nytService = NYTService.retrofit.create(NYTService.class);
        return nytService.getPostTop(section)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

    public static Observable<NYTAPIMostPopular> streamFetchMost(String section) {
        NYTService nytService = NYTService.retrofit.create(NYTService.class);
        return nytService.getPostMost(section)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }
}
