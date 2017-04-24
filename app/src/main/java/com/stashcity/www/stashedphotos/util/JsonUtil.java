package com.stashcity.www.stashedphotos.util;

import android.util.Log;

import com.google.gson.Gson;
import com.stashcity.www.stashedphotos.adapter.FeedListAdapter;
import com.stashcity.www.stashedphotos.model.FeedItem;
import com.stashcity.www.stashedphotos.model.Image;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sravan on 20/02/17.
 */

public class JsonUtil {


    public static List<FeedItem> parseJsonFeedItems(JSONObject response, String feedType,FeedListAdapter listAdapter) {
        Gson gsonObj;
        FeedItem feedItem=null;
        gsonObj = new Gson();
        List<FeedItem> feedItems=new ArrayList<>();
        try {
            JSONArray feedArray = response.getJSONArray(feedType);

            for (int i = 0; i < feedArray.length(); i++) {
                JSONObject feedObj = (JSONObject) feedArray.get(i);
                Image imageobj= gsonObj.fromJson(feedObj.toString(),Image.class);
                Log.e("object",imageobj.toString());

                if (imageobj.getUrl()!=null&&imageobj.getThumbnailUrl()!=null)
                {
                    feedItem = new FeedItem(imageobj);
                }

                feedItems.add(feedItem);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        listAdapter.notifyDataSetChanged();
        return feedItems;
    }
}
