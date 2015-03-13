package com.abhiuday.networkinglibrarytesting.retrofit.service.tumblr;

import android.util.Log;

import com.abhiuday.networkinglibrarytesting.retrofit.RetrofitTumblrImagesFragment;
import com.abhiuday.networkinglibrarytesting.retrofit.deserializer.BlogInfoDeserializer;
import com.abhiuday.networkinglibrarytesting.retrofit.model.BlogInfo;
import com.abhiuday.networkinglibrarytesting.retrofit.service.ServiceGenerator;
import com.abhiuday.networkinglibrarytesting.retrofit.tumblrapis.TumblrApi;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 Created by abhiuday.tomar on 04/03/15.
 */
public class TumblrService {
    private static String TAG = "TumblrService";
    private static TumblrApi tumblrApi;
    private static Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                                .setDateFormat("yyyy-MM-dd")
                                .registerTypeAdapter(BlogInfoDeserializer.class, new BlogInfoDeserializer())
                                .create();
    private TumblrService() {}

    private static Callback<BlogInfo> callback = new Callback<BlogInfo>() {
        @Override
        public void success(BlogInfo blogInfo, Response response) {
            Log.v(TAG,"Coming in on success");
            Log.v(TAG, "Response body is ::" + response.getBody());
        }

        @Override
        public void failure(RetrofitError retrofitError) {
            Log.v(TAG, "Call back has failed" + retrofitError.getMessage());
        }
    };

    public static TumblrApi getTumblrConnection(final String access_token, final String access_token_secret) {
        if(tumblrApi == null) {
            tumblrApi = ServiceGenerator.createServiceWithSigingOkClient(TumblrApi.class, TumblrApi.BASE_URL, gson, access_token, access_token_secret);
        }
        return tumblrApi;
    }
    
    public static void getBlogInfo(String access_token, String access_token_secret) {
        try {
            getTumblrConnection(access_token, access_token_secret).getBlogInfo("awesometechenthu.tumblr.com", RetrofitTumblrImagesFragment.CONSUMER_KEY, callback);

        } catch (RetrofitError e) {
            System.out.println(e.getResponse().getStatus());
        }
    }
}
