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

import com.example.user.vo.EventVO;
import com.example.user.foodtruck.R;

import java.util.List;

/**
 * Created by bit-user on 2018-01-19.
 */

public class EventAdapter extends ArrayAdapter<EventVO> {
    private List<EventVO> list;

    public EventAdapter(Context context, List<EventVO> list) {
        super(context, R.layout.notice_item, list);
        this.list = list;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View view = layoutInflater.inflate(R.layout.event_item, parent, false);

        EventVO vo = list.get(position);

        TextView textView = view.findViewById(R.id.eventtextView);
        TextView textView1 = view.findViewById(R.id.eventtextView2);
        ImageView imageView = view.findViewById(R.id.imageView);

        textView.setText(vo.getEventTitle());
        textView1.setText(vo.getEventReg());
        imageView.setImageResource(R.drawable.if_simpline_39_2305601);

        return view;
    }
}
