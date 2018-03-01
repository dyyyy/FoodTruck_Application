package com.example.user.foodtruck;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.vo.FoodTruckVO;

import java.util.List;

/**
 * Created by bit-user on 2018-03-01.
 */

public class Tab1 extends Fragment{
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<FoodTruckVO> listItems;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu_tab1, container, false);

        return view;
    }
}
