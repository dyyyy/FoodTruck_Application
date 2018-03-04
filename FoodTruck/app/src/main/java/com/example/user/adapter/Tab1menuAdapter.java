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
import com.example.user.vo.ProductVO;

import java.util.List;

/**
 * Created by bit-user on 2018-01-19.
 */

public class Tab1menuAdapter extends ArrayAdapter<ProductVO> {
    private List<ProductVO> list;

    public Tab1menuAdapter(Context context, List<ProductVO> list) {
        super(context, R.layout.menulist_item, list);
        this.list = list;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View view = layoutInflater.inflate(R.layout.menulist_item, parent, false);

        ProductVO vo = list.get(position);

        ImageView imageView = view.findViewById(R.id.productimage);
        TextView prodname = view.findViewById(R.id.productnametext);
        TextView prodprice = view.findViewById(R.id.prodPricetextView);


        prodname.setText(vo.getProdName());
        prodprice.setText(String.valueOf(vo.getProdPrice()));


        return view;
    }
}
