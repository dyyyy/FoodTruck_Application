package com.example.user.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.foodtruck.R;
import com.example.user.vo.FoodTruckVO;
import com.example.user.vo.ReviewVO;

import java.util.List;

/**
 * Created by bit-user on 2018-01-19.
 */

public class Tab3reviewAdapter extends ArrayAdapter<ReviewVO> {
    private List<ReviewVO> list;

    public Tab3reviewAdapter(Context context, List<ReviewVO> list) {
        super(context, R.layout.reviewlist_item, list);
        this.list = list;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View view = layoutInflater.inflate(R.layout.reviewlist_item, parent, false);

        ReviewVO vo = list.get(position);

        ImageView imageView = view.findViewById(R.id.rimage);
        TextView textView = view.findViewById(R.id.rnametext);
        TextView textView1 = view.findViewById(R.id.rcontenttext);

        textView.setText(vo.getMemId());
        textView1.setText(vo.getRevContent());


        return view;
    }


}
