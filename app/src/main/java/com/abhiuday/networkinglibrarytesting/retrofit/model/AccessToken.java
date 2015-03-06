package com.abhiuday.networkinglibrarytesting.retrofit.model;

/**
 Created by abhiuday.tomar on 02/03/15.
 */
public class AccessToken extends BaseResponse {
    private String accessToken;
    private String tokenType;

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        if (!Character.isUpperCase(tokenType.charAt(0))) {
           tokenType = Character.toString(tokenType.charAt(0)).toUpperCase() + tokenType.substring(1);
        }
        return tokenType;
    }
}
