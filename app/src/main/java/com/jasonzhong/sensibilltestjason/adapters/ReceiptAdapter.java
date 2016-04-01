package com.jasonzhong.sensibilltestjason.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jasonzhong.sensibilltestjason.R;
import com.jasonzhong.sensibilltestjason.models.Receipt;

import java.util.List;

/**
 * Created by jason.zhong on 31/03/2016.
 */
public class ReceiptAdapter extends RecyclerView.Adapter<ReceiptAdapter.CustomViewHolder> {

    static Context context;
    static private List<Receipt> receiptItems;
    LayoutInflater inflater;

    public ReceiptAdapter(Context context) {
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
        Receipt receiptItem = receiptItems.get(i);
        //Setting textView content
        customViewHolder.nameTextView.setText(receiptItem.getName());
        customViewHolder.amountTextView.setText(receiptItem.getAmount());
        customViewHolder.nameTextView.setText(receiptItem.getName());
    }

    public void setDisplayList(List<Receipt> displayItems) {
        this.receiptItems = displayItems;
    }

    @Override
    public int getItemCount() {
        return (null != receiptItems ? receiptItems.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView nameTextView;
        protected TextView amountTextView;
        protected TextView dateTextView;

        public CustomViewHolder(View view) {
            super(view);
            //custom font
            Typeface boldTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Bold.ttf");
            this.nameTextView = (TextView) view.findViewById(R.id.nameTextView);
            this.nameTextView.setTypeface(boldTypeface);
            this.amountTextView = (TextView) view.findViewById(R.id.amountTextView);
            this.amountTextView.setTypeface(boldTypeface);
            this.dateTextView = (TextView) view.findViewById(R.id.dateTextView);
        }
    }
}
