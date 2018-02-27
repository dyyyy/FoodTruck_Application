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

import com.example.user.vo.NoticeVO;
import com.example.user.foodtruck.R;

import java.util.List;

/**
 * Created by bit-user on 2018-01-19.
 */

public class NoticeAdapter extends ArrayAdapter<NoticeVO> {
    private List<NoticeVO> list;

    public NoticeAdapter(Context context, List<NoticeVO> list) {
        super(context, R.layout.board_item, list);
        this.list = list;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View view = layoutInflater.inflate(R.layout.board_item, parent, false);



        NoticeVO vo = list.get(position);

        TextView textView = view.findViewById(R.id.textView);
        TextView textView1 = view.findViewById(R.id.textView1);
        ImageView imageView = view.findViewById(R.id.imageView);

        textView.setText(vo.getNoticeTitle());
        textView1.setText(vo.getNoticeReg());
        imageView.setImageResource(R.mipmap.notice_icon);

        return view;
    }
}
