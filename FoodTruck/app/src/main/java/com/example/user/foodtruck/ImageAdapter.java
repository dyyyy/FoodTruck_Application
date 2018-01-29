package com.example.user.foodtruck;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bit-user on 2018-01-19.
 */

public class ImageAdapter extends ArrayAdapter<NoticeVO> {
    private ArrayList<NoticeVO> nvolist;

    ImageAdapter(Context context, ArrayList<NoticeVO> nvolist) {
        super(context, R.layout.image_layout, nvolist);
        this.nvolist = nvolist;

    }
    ArrayList<NoticeVO> item;
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View view = layoutInflater.inflate(R.layout.image_layout, parent, false);

        NoticeVO nvo = nvolist.get(position);


        TextView textView = view.findViewById(R.id.textView);
        TextView textView1 = view.findViewById(R.id.textView1);
        ImageView imageView = view.findViewById(R.id.imageView);

        textView.setText(nvo.getNoticeTitle());
        textView1.setText(nvo.getNoticeReg());
        imageView.setImageResource(R.mipmap.notice_icon);

        return view;
    }
}
