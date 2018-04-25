/*
 * Created by Romain Mathieu => https://github.com/mclouu
 */

package com.romain.mathieu.mynews.Model;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.romain.mathieu.mynews.Model.API.TopStories.NYTAPI_topstories;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

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

    //@GET("svc/mostpopular/v2/{mode}/all-sections/1.json?api-key=e5ace90626ec4c7495500a0dbb327980")
    //Call<List<>> getPostInfo(@path("mode") String mode);

    @GET("svc/topstories/v2/{section}.json?api-key=e5ace90626ec4c7495500a0dbb327980")
    Observable<List<NYTAPI_topstories>> getPostInfo(@Path("section") String section);
}
