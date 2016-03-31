package com.jasonzhong.sensibilltestjason.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jasonzhong.sensibilltestjason.R;
import com.jasonzhong.sensibilltestjason.models.Display;

import java.util.List;

/**
 * Created by jason.zhong on 31/03/2016.
 */
public class DisplayAdapter extends RecyclerView.Adapter<DisplayAdapter.CustomViewHolder> {

    static Context context;
    static private List<Display> displayItems;
    LayoutInflater inflater;

    public DisplayAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.display_item, viewGroup, false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {
        Display displayItem = displayItems.get(i);
        //Setting text view content
        customViewHolder.nameTextView.setText(displayItem.getName());
        customViewHolder.amountTextView.setText(displayItem.getAmount());
        customViewHolder.nameTextView.setText(displayItem.getName());
    }
    public void setDisplayList(List<Display> displayItems) {
        this.displayItems = displayItems;
    }

    @Override
    public int getItemCount() {
        return (null != displayItems ? displayItems.size() : 0);
    }
    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView nameTextView;
        protected TextView amountTextView;
        protected TextView dateTextView;

        public CustomViewHolder(View view) {
            super(view);
            Typeface boldTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Bold.ttf");
            this.nameTextView = (TextView) view.findViewById(R.id.nameTextView);
            this.nameTextView.setTypeface(boldTypeface);
            this.amountTextView = (TextView) view.findViewById(R.id.amountTextView);
            this.amountTextView.setTypeface(boldTypeface);
            this.dateTextView = (TextView) view.findViewById(R.id.dateTextView);
        }
    }
}
