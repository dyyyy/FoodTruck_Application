package com.example.user.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.foodtruck.R;

/**
 * Created by bit-user on 2018-01-31.
 */

public class MenuAdapter extends BaseAdapter {
    Context context;
    private int[] images;
    private String[] values;
    View view;

    public MenuAdapter(Context context, int[] images, String[] values) {
        this.context = context;
        this.images = images;
        this.values = values;
    }

    @Override
    public int getCount() {
        return values.length;
    }

    @Override
    public Object getItem(int position) {
        return values[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            view = new View(context);
            view = layoutInflater.inflate(R.layout.category_item, null);
            ImageView imageView = view.findViewById(R.id.grid_item_image);
            TextView textView = view.findViewById(R.id.grid_item_text);

            imageView.setImageResource(images[position]);
            textView.setText(values[position]);

        }
        return view;
    }
}