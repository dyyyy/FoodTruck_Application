package com.example.user.adapter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.user.foodtruck.R;

/**
 * Created by bit-user on 2018-02-14.
 */

public class ImageAdapter extends BaseAdapter {
    private Context context;

    public ImageAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if(convertView == null){
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8,8,8,8);

        }else{
            imageView = (ImageView)convertView;
        }
        imageView.setImageResource(mThumbleIds[position]);

        return imageView;
    }
    private Integer[] mThumbleIds ={R.drawable.ic_menu_share, R.drawable.ic_menu_camera, R.drawable.ic_menu_send};
}
