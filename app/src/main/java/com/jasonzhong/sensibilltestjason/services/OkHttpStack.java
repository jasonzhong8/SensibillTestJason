package com.jasonzhong.sensibilltestjason.services;

import com.android.volley.toolbox.HurlStack;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.OkUrlFactory;

/**
 * Created by jason.zhong on 31/03/2016.
 */
public class OkHttpStack extends HurlStack {
    private final OkUrlFactory mFactory;

    public OkHttpStack(OkHttpClient client) {
        if (client == null) {
            throw new NullPointerException("Client must not be null.");
        }
        mFactory = new OkUrlFactory(client);
    }

    @Override protected HttpURLConnection createConnection(URL url) throws IOException {
        return mFactory.open(url);
    }
}
