package com.example.user.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.foodtruck.R;
import com.example.user.vo.FoodTruckVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by bit-user on 2018-01-19.
 */

public class MenuTabAdapter extends ArrayAdapter<FoodTruckVO> {
    private List<FoodTruckVO> list;

    public MenuTabAdapter(Context context, List<FoodTruckVO> list) {
        super(context, R.layout.menutab_item, list);
        this.list = list;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View view = layoutInflater.inflate(R.layout.menutab_item, parent, false);

        FoodTruckVO vo = list.get(position);

        TextView textView = view.findViewById(R.id.menutabtextView);
        TextView textView1 = view.findViewById(R.id.menutabtextView2);
        ImageView imageView = view.findViewById(R.id.imageView);

        textView.setText(vo.getFtruckName());
        textView1.setText(vo.getFtruckAddr());

        String addr = vo.getFtruckImg().replace("\\resources\\img\\upload\\", "");
        addr = addr.replace(".jpg", "");

        switch (addr) {
            case "img1":
                imageView.setImageResource(imgaddr[0]);
                break;
            case "img2":
                imageView.setImageResource(imgaddr[1]);
                break;
            case "img3":
                imageView.setImageResource(imgaddr[2]);
                break;
            case "img4":
                imageView.setImageResource(imgaddr[3]);
                break;
            case "img5":
                imageView.setImageResource(imgaddr[4]);
                break;
            case "img6":
                imageView.setImageResource(imgaddr[5]);
                break;
            case "img7":
                imageView.setImageResource(imgaddr[6]);
                break;
            case "img8":
                imageView.setImageResource(imgaddr[7]);
                break;
            case "img9":
                imageView.setImageResource(imgaddr[8]);
                break;
            case "img10":
                imageView.setImageResource(imgaddr[9]);
                break;
            case "img11":
                imageView.setImageResource(imgaddr[10]);
                break;
            default:
                break;
        }


        return view;
    }

   private int imgaddr[] = {R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5, R.drawable.img6, R.drawable.img7, R.drawable.img8, R.drawable.img9, R.drawable.img10, R.drawable.img11};

}
