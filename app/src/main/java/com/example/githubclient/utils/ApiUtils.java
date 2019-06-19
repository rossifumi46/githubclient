package com.example.githubclient.utils;

import com.example.githubclient.data.api.Api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUtils {

    private static Retrofit mRetrofit;
    private static Api mApi;


    private static Retrofit getRetrofit() {
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl("https://api.github.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return mRetrofit;
    }

    public static Api getApiService() {
        if (mApi == null) {
            mApi = getRetrofit().create(Api.class);
        }
        return mApi;
    }
}