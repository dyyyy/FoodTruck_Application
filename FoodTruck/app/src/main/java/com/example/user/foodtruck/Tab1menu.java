package com.example.user.foodtruck;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.vo.ReviewVO;

import java.io.Serializable;
import java.util.List;


public class Tab1menu extends Fragment {


    public Tab1menu() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab1menu, container, false);
//        List<ReviewVO> plist = (List<ReviewVO>) getArguments().getSerializable("plist");

        return view;
    }

}
