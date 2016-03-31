package com.jasonzhong.sensibilltestjason.services;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import okhttp3.OkHttpClient;

/**
 * Created by jason.zhong on 31/03/2016.
 */
public class RequestManager {
    private static RequestManager mInstance;
    private static Context mCtx;
    private RequestQueue mRequestQueue;

    private RequestManager(Context context) {
        mCtx = context;
    }

    public static synchronized RequestManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new RequestManager(context.getApplicationContext());
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            OkHttpClient client = builder.build();

            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext(), new OkHttpStack(client));
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setRetryPolicy(new DefaultRetryPolicy(25 * 1000, 1, 1f));
        getRequestQueue().add(req);
    }

    public void cancelRequests(Object tag) {
        if (mRequestQueue != null) {
            getRequestQueue().cancelAll(tag);
        }
    }

    public void cancelAllRequests() {
        getRequestQueue().cancelAll(new RequestQueue.RequestFilter() {
            @Override
            public boolean apply(Request<?> request) {
                return true;
            }
        });
    }
}
