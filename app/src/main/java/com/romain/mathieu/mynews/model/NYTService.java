/*
 * Created by Romain Mathieu => https://github.com/mclouu
 */

package com.romain.mathieu.mynews.model;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.romain.mathieu.mynews.model.API.ArticleSearch.NYTAPIArticleSearch;
import com.romain.mathieu.mynews.model.API.MostPopular.NYTAPIMostPopular;
import com.romain.mathieu.mynews.model.API.TopStories.NYTAPITopstories;
import com.romain.mathieu.mynews.utils.MyConstant;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NYTService {

    MyConstant constant = new MyConstant();

    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addNetworkInterceptor(new StethoInterceptor())
            .build();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(constant.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();


    @GET("svc/topstories/v2/{section}.json")
    Observable<NYTAPITopstories> getPostTop(@Path("section") String section,
                                            @Query("api-key") String apiKey);

    @GET("svc/mostpopular/v2/mostviewed/{section}/30.json")
    Observable<NYTAPIMostPopular> getPostMost(@Path("section") String section,
                                              @Query("api-key") String apiKey);

    @GET("svc/search/v2/articlesearch.json")
    Observable<NYTAPIArticleSearch> getPostArticle(@Query("api-key") String apiKey,
                                                   @Query("fq") String fQuery,
                                                   @Query("sort") String sort);

    @GET("svc/search/v2/articlesearch.json")
    Observable<NYTAPIArticleSearch> getPostSearch(@Query("api-key") String apiKey,
                                                  @Query("q") String query,
                                                  @Query("fq") String fQuery,
                                                  @Query("sort") String sort,
                                                  @Query("begin_date") String beginDate,
                                                  @Query("end_date") String endDate);
}