package com.abhiuday.networkinglibrarytesting.retrofit.service.tumblr;

import android.util.Log;

import com.abhiuday.networkinglibrarytesting.retrofit.RetrofitTumblrImagesFragment;
import com.abhiuday.networkinglibrarytesting.retrofit.adapter.RetrofitHttpOAuthConsumer;
import com.abhiuday.networkinglibrarytesting.retrofit.adapter.SigningOkClient;
import com.abhiuday.networkinglibrarytesting.retrofit.deserializer.BlogInfoDeserializer;
import com.abhiuday.networkinglibrarytesting.retrofit.service.ServiceGenerator;
import com.abhiuday.networkinglibrarytesting.retrofit.tumblrapis.TumblrApi;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
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

    private static Callback callback = new Callback() {
        @Override
        public void success(Object o, Response response) {
            Log.v(TAG,"Coming in on success");
        }

        @Override
        public void failure(RetrofitError retrofitError) {
            Log.v(TAG, "Call back has failed" + retrofitError.getMessage());
        }
    };
    
    public static TumblrApi getTumblrConnection() {
        if(tumblrApi == null) {
            tumblrApi = ServiceGenerator.createService(TumblrApi.class, TumblrApi.BASE_URL, gson);
        }
        return tumblrApi;
    }

    public static TumblrApi getTumblrConnection(String oauth_token, String oauth_verifier) {
        if(tumblrApi == null) {
            RetrofitHttpOAuthConsumer oAuthConsumer = new RetrofitHttpOAuthConsumer(RetrofitTumblrImagesFragment.CONSUMER_KEY, RetrofitTumblrImagesFragment.CONSUMER_SECRET);
            oAuthConsumer.setTokenWithSecret(oauth_token, oauth_verifier);
            OkClient client = new SigningOkClient(oAuthConsumer);
            tumblrApi = ServiceGenerator.createService(TumblrApi.class, TumblrApi.BASE_URL, gson, client);
        }
        return tumblrApi;
    }
    
    public static void getBlogInfo(String oauth_verifier) {
        try {
            getTumblrConnection().getBlogInfo("awesometechenthu.tumblr.com", oauth_verifier, callback);

        } catch (RetrofitError e) {
            System.out.println(e.getResponse().getStatus());
        }
    }

    public static void getBlogInfo(String oauth_token, String oauth_verifier) {
        try {
            getTumblrConnection(oauth_token, oauth_verifier).getBlogInfoV1("awesometechenthu.tumblr.com", callback);

        } catch (RetrofitError e) {
            System.out.println(e.getResponse().getStatus());
        }
    }
}
