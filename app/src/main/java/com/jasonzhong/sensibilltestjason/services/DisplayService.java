package com.jasonzhong.sensibilltestjason.services;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.android.volley.toolbox.JsonObjectRequest;
import com.jasonzhong.sensibilltestjason.models.Display;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jason.zhong on 31/03/2016.
 */
public class DisplayService {
    Context context;
    static DisplayService mInstance;

    private DisplayService(Context context) {
        this.context = context;
    }

    public static synchronized DisplayService getInstance(Context context) {
        if (mInstance == null) mInstance = new DisplayService(context.getApplicationContext());

        return mInstance;
    }

    public void loadDisplays(String urlJsonArry, final DisplayItemHandler displayItemHandler) {

        final List<Display> displayList = new ArrayList<>();
      /*  JsonArrayRequest req = new JsonArrayRequest(urlJsonArry,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        try {
                            // Parsing json array response
                            // loop through each json object
                            for (int i = 0; i < response.length(); i++) {
                                Display display = new Display();
                                JSONObject displayJObject = (JSONObject) response
                                        .get(i);

                                display.setName(displayJObject.getString("name"));
                                display.setAmount(displayJObject.getString("amount"));
                                display.setDate(displayJObject.getString("date"));
                                displayList.add(display);

                            }
                            displayItemHandler.onSuccess(displayList);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            displayItemHandler.onError(e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("DisplayService", "Error: " + error.getMessage());
                displayItemHandler.onError(error.getMessage());
            }
        });*/

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, urlJsonArry, (String)null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //JSONObject jb1 = (JSONObject) response.getJSONObject("receipts");
                            JSONArray receipts = (JSONArray) response.getJSONArray("receipts");
                            for(int i=0;i<receipts.length();i++)
                            {
                                JSONObject b = receipts.getJSONObject(i);
                                JSONObject displayJObject = b.getJSONObject("display");
                                Display display = new Display();
                                display.setName(displayJObject.getString("name"));
                                display.setAmount(displayJObject.getString("amount"));
                                display.setDate(displayJObject.getString("date"));
                                displayList.add(display);
                              /*  String id = b.getString("id");
                                Log.i(".......",id);
                                JSONObject location = b.getJSONObject("location");
                                String lat = location.getString("lat");
                                Log.i(".......",lat);*/
                                Log.e("TAG", displayJObject.toString());
                            }
                            displayItemHandler.onSuccess(displayList);
                            Log.e("TAG", response.toString());
                        }catch(Exception e){}
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        displayItemHandler.onError(error.getMessage());
                    }
                });



        // Adding request to request queue
        RequestManager.getInstance(context).addToRequestQueue(jsObjRequest);
    }

    public interface DisplayItemHandler {
        void onSuccess(List<Display> list);

        void onError(String error);
    }
}
