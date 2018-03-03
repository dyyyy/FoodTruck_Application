package com.example.user.foodtruck;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.user.vo.FoodTruckVO;
import com.example.user.vo.ReviewVO;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Tab2info extends Fragment {


    public Tab2info() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab2info, container, false);
        FoodTruckVO fvo = (FoodTruckVO) getArguments().getSerializable("fvo");
        Log.d("fvo","fvo");
        return view;
    }

}
