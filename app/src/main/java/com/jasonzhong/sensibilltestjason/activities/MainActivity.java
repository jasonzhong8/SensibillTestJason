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
import com.jasonzhong.sensibilltestjason.adapters.DisplayAdapter;
import com.jasonzhong.sensibilltestjason.models.Display;
import com.jasonzhong.sensibilltestjason.models.DisplayObjectComparator;
import com.jasonzhong.sensibilltestjason.services.DisplayService;

import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.displayListRecyclerView) RecyclerView displayListRecyclerView;
    @Bind(R.id.progressbar_layout) FrameLayout progressbarLayout;

    private DisplayAdapter displayListAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        progressbarLayout.setVisibility(View.VISIBLE);

        displayListAdapter = new DisplayAdapter(this);
        displayListRecyclerView.setAdapter(displayListAdapter);
        displayListRecyclerView.setHasFixedSize(true);

        //Layout manager for Recycler view
        displayListRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        DisplayService.getInstance(this).loadDisplays("https://getsensibill.com/api/tests/receipts",new DisplayService.DisplayItemHandler() {
            @Override
            public void onSuccess(List<Display> list) {
                sortAndDisplayResult(list);
            }

            @Override
            public void onError(String error) {
                progressbarLayout.setVisibility(View.GONE);//remove progressBar
                Toast.makeText(MainActivity.this, "Failed to get current display", Toast.LENGTH_LONG).show();
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
    private void sortAndDisplayResult(List<Display> list){
        //sort this list alphabetically
        DisplayObjectComparator comparator = new DisplayObjectComparator();
        Collections.sort(list, comparator);
        displayListAdapter.setDisplayList(list);
        //update recyclerView
        displayListAdapter.notifyDataSetChanged();
        progressbarLayout.setVisibility(View.GONE);//remove progressBar
    }
}
