package com.abhiuday.networkinglibrarytesting.retrofit.service;

import com.abhiuday.networkinglibrarytesting.retrofit.RetrofitTumblrImagesFragment;
import com.abhiuday.networkinglibrarytesting.retrofit.adapter.RetrofitHttpOAuthConsumer;
import com.abhiuday.networkinglibrarytesting.retrofit.adapter.SigningOkClient;
import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 Created by abhiuday.tomar on 02/03/15.
 */
public class ServiceGenerator {
    private ServiceGenerator () {}
    
    public static <S> S createService(Class<S> serviceClass, String baseUrl, Gson gson) {
        RestAdapter adapter = new RestAdapter.Builder().
                                          setEndpoint(baseUrl).
                                          setClient(new OkClient(new OkHttpClient())).
                                          setLogLevel(RestAdapter.LogLevel.FULL).
                                          setConverter(new GsonConverter(gson)).
                                          build();
        return adapter.create(serviceClass);
    }

    public static <S> S createServiceWithSigingOkClient(Class<S> serviceClass, String baseUrl, Gson gson, final String access_token, final String access_token_secret) {
        RetrofitHttpOAuthConsumer oAuthConsumer = new RetrofitHttpOAuthConsumer(RetrofitTumblrImagesFragment.CONSUMER_KEY, RetrofitTumblrImagesFragment.CONSUMER_SECRET);
        oAuthConsumer.setTokenWithSecret(access_token, access_token_secret);
        OkClient client = new SigningOkClient(oAuthConsumer);
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(baseUrl)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setConverter(new GsonConverter(gson))
                .setClient(client)
                .build();
        return adapter.create(serviceClass);
    }

}
