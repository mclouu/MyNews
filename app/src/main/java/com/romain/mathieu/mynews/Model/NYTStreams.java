/*
 * Created by Romain Mathieu => https://github.com/mclouu
 */

package com.romain.mathieu.mynews.Model;

import com.romain.mathieu.mynews.Model.API.TopStories.NYTAPI_topstories;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NYTStreams {

    public static Observable<List<NYTAPI_topstories>> streamFetchSection(String section) {
        NYTService nytService = NYTService.retrofit.create(NYTService.class);
        return nytService.getPostInfo(section)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }
}