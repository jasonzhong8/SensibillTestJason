package com.jasonzhong.sensibilltestjason.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.jasonzhong.sensibilltestjason.R;
import com.jasonzhong.sensibilltestjason.adapters.ReceiptAdapter;
import com.jasonzhong.sensibilltestjason.models.Receipt;
import com.jasonzhong.sensibilltestjason.models.ReceiptObjectComparator;
import com.jasonzhong.sensibilltestjason.services.ReceiptService;

import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.receiptListRecyclerView) RecyclerView receiptListRecyclerView;
    @Bind(R.id.progressbar_layout) FrameLayout progressbarLayout;

    private ReceiptAdapter receiptListAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        progressbarLayout.setVisibility(View.VISIBLE);

        receiptListAdapter = new ReceiptAdapter(this);
        receiptListRecyclerView.setAdapter(receiptListAdapter);
        receiptListRecyclerView.setHasFixedSize(true);

        //Layout manager for Recycler view
        receiptListRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //asynchronously webservice call
        ReceiptService.getInstance(this).loadReceipts("https://getsensibill.com/api/tests/receipts", new ReceiptService.ReceiptItemHandler() {
            @Override
            public void onSuccess(List<Receipt> list) {
                sortAndDisplayResult(list);//process on UI thread
            }

            @Override
            public void onError(String error) {
                progressbarLayout.setVisibility(View.GONE);//remove progressBar
                Toast.makeText(MainActivity.this, "Failed to get current receipts", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    private void sortAndDisplayResult(List<Receipt> list) {
        //sort this list alphabetically
        ReceiptObjectComparator comparator = new ReceiptObjectComparator();
        Collections.sort(list, comparator);
        receiptListAdapter.setDisplayList(list);
        //update recyclerView
        receiptListAdapter.notifyDataSetChanged();
        progressbarLayout.setVisibility(View.GONE);//remove progressBar
    }
}
