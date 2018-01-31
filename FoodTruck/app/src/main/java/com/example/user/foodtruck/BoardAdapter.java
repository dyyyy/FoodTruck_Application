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

/**
 * Created by bit-user on 2018-01-19.
 */

public class BoardAdapter extends ArrayAdapter<BoardVO> {
    private ArrayList<BoardVO> bvolist;

    BoardAdapter(Context context, ArrayList<BoardVO> bvolist) {
        super(context, R.layout.board_item, bvolist);
        this.bvolist = bvolist;

    }
    ArrayList<BoardVO> item;
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View view = layoutInflater.inflate(R.layout.board_item, parent, false);

        BoardVO bvo = bvolist.get(position);


        TextView textView = view.findViewById(R.id.textView);
        TextView textView1 = view.findViewById(R.id.textView1);
        ImageView imageView = view.findViewById(R.id.imageView);

        textView.setText(bvo.getBoardTitle());
        textView1.setText(bvo.getBoardReg());
        imageView.setImageResource(R.mipmap.notice_icon);

        return view;
    }
}
