/*
 * Created by Romain Mathieu => https://github.com/mclouu
 */

package com.romain.mathieu.mynews.Model;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.romain.mathieu.mynews.Model.API.NYTAPI;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
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
            .build();

    @GET("svc/topstories/v2/{section}.json?api-key=e5ace90626ec4c7495500a0dbb327980")
    Call<List<NYTAPI>> getPostInfo(@Path("section") String section);
}
