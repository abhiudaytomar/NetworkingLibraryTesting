package com.abhiuday.networkinglibrarytesting.retrofit;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.abhiuday.networkinglibrarytesting.R;
import com.abhiuday.networkinglibrarytesting.retrofit.service.tumblr.TumblrService;

import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthProvider;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.exception.OAuthNotAuthorizedException;

/**
 Created by abhiuday.tomar on 27/02/15.
 */
public class RetrofitTumblrImagesFragment extends Fragment {
    
    private static final String TAG = "RetrofitTumblrImagesFragment";

    public static final String CONSUMER_KEY = "xxxxxxxxxxxxxxxxx";
    public static final String CONSUMER_SECRET = "yyyyyyyyyyyyyyyyyy";
    
    private static final String REQUEST_TOKEN_URL = "https://www.tumblr.com/oauth/request_token";
    private static final String ACCESS_TOKEN_URL = "https://www.tumblr.com/oauth/access_token";
    private static final String AUTH_URL = "https://www.tumblr.com/oauth/authorize";
    
    public static final String CALLBACK_URL = "networkingdemo://oauthResponse";
    private CommonsHttpOAuthConsumer consumer;
    private CommonsHttpOAuthProvider provider;
    private String authUrl;
    private static String tokenSecret;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.retrofit_tumblr_images_fragment, container, false);
        Button loginButton = (Button) rootView.findViewById(R.id.loginbutton);
        setConsumerAndProvider();
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... voids) {
                        try {
                            authUrl = provider.retrieveRequestToken(consumer, CALLBACK_URL);
                            Log.d(TAG, "mAuthUrl " + authUrl);
                            tokenSecret = consumer.getTokenSecret();
                            Log.v(TAG, "Token Secret 1 ::" + tokenSecret);
                        } catch (OAuthMessageSignerException e) {
                            e.printStackTrace();
                        } catch (OAuthNotAuthorizedException e) {
                            e.printStackTrace();
                        } catch (OAuthExpectationFailedException e) {
                            e.printStackTrace();
                        } catch (OAuthCommunicationException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void v) {
                        if (!TextUtils.isEmpty(authUrl)) {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(authUrl)));
                        }
                    }
                }.execute();
            }
        });
        return rootView;
    }


    @Override
    public void onResume() {
        super.onResume();
        Log.v(TAG, "Coming in onResume");
        Uri uri = getActivity().getIntent().getData();
        if (uri != null && uri.toString().startsWith(CALLBACK_URL)) {
            final String oauth_token = uri.getQueryParameter("oauth_token");
            final String oauth_verifier = uri.getQueryParameter("oauth_verifier");

            Log.v(TAG, "The complete uri is::" + uri);
            Log.v(TAG, "RetrofitTumblrImagesFragment Activity OAuth Token 5:" + oauth_token);
            Log.v(TAG, "RetrofitTumblrImagesFragment Activity OAuth Verifier 5:" + oauth_verifier);
            Log.v(TAG, "Token Secret:" + tokenSecret);
            consumer.setTokenWithSecret(oauth_token, tokenSecret);
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    try {
                        provider.retrieveAccessToken(consumer, oauth_verifier);
                    } catch (OAuthMessageSignerException e) {
                        e.printStackTrace();
                    } catch (OAuthNotAuthorizedException e) {
                        e.printStackTrace();
                    } catch (OAuthExpectationFailedException e) {
                        e.printStackTrace();
                    } catch (OAuthCommunicationException e) {
                        e.printStackTrace();
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Void v) {
                    Log.v(TAG, "Coming in onPostExecute of retrieveAccessToken Token::" + consumer.getToken() + " Token Secret::" + consumer.getTokenSecret());
                    TumblrService.getBlogInfo(consumer.getToken(), consumer.getToken());
                }
            }.execute();
        }
    }
    private void setConsumerAndProvider() {
        consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
        provider = new CommonsHttpOAuthProvider(REQUEST_TOKEN_URL, ACCESS_TOKEN_URL, AUTH_URL);

        provider.setOAuth10a(true);
    }
}
    
