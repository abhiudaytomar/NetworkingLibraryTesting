package com.abhiuday.networkinglibrarytesting.retrofit.tumblrapis;

import com.abhiuday.networkinglibrarytesting.retrofit.model.BlogInfo;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 Created by abhiuday.tomar on 03/03/15.
 */
public interface TumblrApi {
    public static final String BASE_URL = "http://api.tumblr.com/v2";
    
    @GET("/blog/{base-hostname}/info")
    public void getBlogInfo(@Path("base-hostname") String hostname, @Query("api_key") String oauth_verifier, Callback<BlogInfo> cb);

}
