package com.abhiuday.networkinglibrarytesting.retrofit.deserializer;

import android.util.Log;

import com.abhiuday.networkinglibrarytesting.retrofit.model.BlogInfo;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 Created by abhiuday.tomar on 05/03/15.
 */
public class BlogInfoDeserializer implements JsonDeserializer<BlogInfo>{
    private static final String TAG = "BlogInfoDeserializer";
    @Override
    public BlogInfo deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        BlogInfo blogInfo = new BlogInfo();
        JsonObject obj = (JsonObject) json;
        if(obj.has("blog")) {
            JsonArray blogInfoCollection = (JsonArray) obj.get("blog");
            if(blogInfoCollection.size() == 1) {
                JsonObject blogInfoObj = (JsonObject)blogInfoCollection.get(0);
                try {
                    blogInfo.setTitle(blogInfoObj.get("title").getAsString());
                    blogInfo.setPosts(blogInfoObj.get("posts").getAsInt());
                    blogInfo.setName(blogInfoObj.get("name").getAsString());
                    blogInfo.setUrl(blogInfoObj.get("url").getAsString());
                    blogInfo.setUpdated(blogInfoObj.get("updated").getAsNumber());
                    blogInfo.setDescription(blogInfoObj.get("description").getAsString());
                    blogInfo.setIsNsfw(blogInfoObj.get("is_nsfw").getAsBoolean());
                    blogInfo.setAsk(blogInfoObj.get("ask").getAsBoolean());
                    blogInfo.setAskPageTitle(blogInfoObj.get("ask_page_title").getAsString());
                    blogInfo.setAskAnon(blogInfoObj.get("ask_anon").getAsBoolean());
                    blogInfo.setShareLikes(blogInfoObj.get("share_likes").getAsBoolean());
                } catch (Exception e) {
                    Log.e(TAG,"Gson paring error" + e.getMessage());
                    return null;
                }
            }
        }
            
        return blogInfo;
    }
}
