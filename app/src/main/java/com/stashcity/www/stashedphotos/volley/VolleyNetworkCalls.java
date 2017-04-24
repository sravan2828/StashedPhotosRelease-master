package com.stashcity.www.stashedphotos.volley;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.stashcity.www.stashedphotos.ApplicationController;

import org.json.JSONObject;

import static com.android.volley.VolleyLog.TAG;

/**
 * Created by sravan on 16/10/16.
 */

public class VolleyNetworkCalls {
    private String URL = "http://services.stashcity.com/Service.svc/";
    public void LikePost(int postId){
        URL=URL+"/like/"+postId;
        JsonObjectRequest jsonReq;
        jsonReq = new JsonObjectRequest(Request.Method.POST,
                URL, "", new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                VolleyLog.d(TAG, "Response: " + response.toString());
                Log.e("responce", response.toString());
                if (response != null) {
                    Log.e("LikeResponse",response.toString());
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

}
