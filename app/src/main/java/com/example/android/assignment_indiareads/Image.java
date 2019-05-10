package com.example.android.assignment_indiareads;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by User on 5/6/2019.
 */

public class Image {

    public String title;
    public int width;
    public int height;
    public String imageUrl;

    public Image(JSONObject image){
        try {

            title = image.getString("title");
            imageUrl=image.getString("url");
            width = image.getInt("width");
            height = image.getInt("height");
        } catch (JSONException e) {
            Log.d("error", e.toString());
        }
    }
    public String getImageUrl(){
        return imageUrl;
    }
    public static ArrayList<Image> parseJSON(JSONArray images){
        ArrayList<Image> result = new ArrayList<Image>();
        try {
            for(int i=0; i < images.length(); i++){
                Image image = new Image(images.getJSONObject(i));
                result.add(image);
            }
        } catch (JSONException je) {
            Log.d("DEBUG", je.toString());
        }
        return result;
    }
}
