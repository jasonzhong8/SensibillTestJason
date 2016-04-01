package com.jasonzhong.sensibilltestjason.services;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.jasonzhong.sensibilltestjason.models.Receipt;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jason.zhong on 31/03/2016.
 */
public class ReceiptService {
    Context context;
    static ReceiptService mInstance;

    private ReceiptService(Context context) {
        this.context = context;
    }

    public static synchronized ReceiptService getInstance(Context context) {
        if (mInstance == null) mInstance = new ReceiptService(context.getApplicationContext());

        return mInstance;
    }

    public void loadReceipts(String urlJson, final ReceiptItemHandler receiptItemHandler) {

        final List<Receipt> receiptList = new ArrayList<>();

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, urlJson, (String) null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray receipts = (JSONArray) response.getJSONArray("receipts");
                            for (int i = 0; i < receipts.length(); i++) {
                                JSONObject b = receipts.getJSONObject(i);
                                JSONObject displayJObject = b.getJSONObject("display");
                                Receipt receipt = new Receipt();
                                receipt.setName(displayJObject.getString("name"));
                                receipt.setAmount(displayJObject.getString("amount"));
                                receipt.setDate(displayJObject.getString("date"));
                                receiptList.add(receipt);
                            }
                            receiptItemHandler.onSuccess(receiptList);
                        } catch (Exception e) {
                            receiptItemHandler.onError(e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        receiptItemHandler.onError(error.getMessage());
                    }
                });

        // Adding request to request queue
        RequestManager.getInstance(context).addToRequestQueue(jsObjRequest);
    }

    public interface ReceiptItemHandler {
        void onSuccess(List<Receipt> list);

        void onError(String error);
    }
}
