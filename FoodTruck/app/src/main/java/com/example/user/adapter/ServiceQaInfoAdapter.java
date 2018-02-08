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

import com.example.user.vo.MInquiryVO;
import com.example.user.foodtruck.R;

import java.util.List;

/**
 * Created by bit-user on 2018-01-19.
 */

public class ServiceQaInfoAdapter extends ArrayAdapter<MInquiryVO> {
    private List<MInquiryVO> list;

    public ServiceQaInfoAdapter(Context context, List<MInquiryVO> list) {
        super(context, R.layout.board_item, list);
        this.list = list;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View view = layoutInflater.inflate(R.layout.board_item, parent, false);

        MInquiryVO vo = list.get(position);

        TextView textView = view.findViewById(R.id.textView);
        TextView textView1 = view.findViewById(R.id.textView1);
        ImageView imageView = view.findViewById(R.id.imageView);

        textView.setText(vo.getQaScTitle());
        textView1.setText(vo.getQaScReg());
        imageView.setImageResource(R.mipmap.notice_icon);

        return view;
    }
}
