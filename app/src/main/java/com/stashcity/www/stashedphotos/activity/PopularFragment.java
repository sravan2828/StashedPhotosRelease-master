package com.stashcity.www.stashedphotos.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.stashcity.www.stashedphotos.ApplicationController;
import com.stashcity.www.stashedphotos.R;
import com.stashcity.www.stashedphotos.adapter.FeedListAdapter;
import com.stashcity.www.stashedphotos.model.FeedItem;
import com.stashcity.www.stashedphotos.model.Image;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PopularFragment extends Fragment {
    private static final String TAG = MainActivity.class.getSimpleName();
    private ListView listView;
    private FeedListAdapter listAdapter;
    private List<FeedItem> feedItems;
    private String URL_FEED = "http://services.stashcity.com/Service.svc/GetPopulars";
    Gson gson;
    FeedItem item;

    public PopularFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_popular, container, false);
        listView = (ListView) rootView.findViewById(R.id.listPopular);
        feedItems = new ArrayList<>();
        listAdapter = new FeedListAdapter(getActivity(), feedItems,getContext());
        listView.setAdapter(listAdapter);

        Cache cache = ApplicationController.getInstance().getRequestQueue().getCache();
        Cache.Entry entry = cache.get(URL_FEED);
        if (entry != null) {
            // fetch the data from cache
            try {
                String data = new String(entry.data, "UTF-8");
                try {
                    parseJsonFeed(new JSONObject(data));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        } else {
            // making fresh volley request and getting json
            JsonObjectRequest jsonReq;
            jsonReq = new JsonObjectRequest(Request.Method.GET,
                    URL_FEED, "", new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    VolleyLog.d(TAG, "Response: " + response.toString());
                    Log.e("responce", response.toString());
                    if (null != response) {
                        parseJsonFeed(response);
                    }
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.d(TAG, "Error: " + error.getMessage());
                }
            });

            // Adding request to volley request queue
            ApplicationController.getInstance().addToRequestQueue(jsonReq);
        }



        // Inflate the layout for this fragment
        return rootView;
    }
    /**
     * Parsing json reponse and passing the data to feed view list adapter
     * */
    private void parseJsonFeed(JSONObject response) {

        gson = new Gson();
        try {
            JSONArray feedArray = response.getJSONArray("GetPopularsResult");
            Image imageobj=null;
            for (int i = 0; i < feedArray.length(); i++) {
                JSONObject feedObj = (JSONObject) feedArray.get(i);
                imageobj= gson.fromJson(feedObj.toString(),Image.class);
                Log.e("object",imageobj.toString());

                if (imageobj.getUrl()!=null&&imageobj.getThumbnailUrl()!=null && !imageobj.getUrl().endsWith("mp4"))
                {
                    item = new FeedItem(imageobj);
                    feedItems.add(item);
                }


            }

            // notify data changes to list adapater
            listAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
