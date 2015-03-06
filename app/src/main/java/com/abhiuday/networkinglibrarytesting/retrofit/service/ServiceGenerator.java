package com.abhiuday.networkinglibrarytesting.retrofit.service;

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

    public static <S> S createService(Class<S> serviceClass, String baseUrl, Gson gson, OkClient client) {
        RestAdapter adapter = new RestAdapter.Builder().
                setEndpoint(baseUrl).
                setClient(client).
                setLogLevel(RestAdapter.LogLevel.FULL).
                setConverter(new GsonConverter(gson)).
                build();
        return adapter.create(serviceClass);
    }

}
