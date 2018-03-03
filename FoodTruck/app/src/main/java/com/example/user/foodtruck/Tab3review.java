package com.example.user.foodtruck;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.vo.ReviewVO;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Tab3review extends Fragment {


    public Tab3review() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab3review, container, false);
   //     List<ReviewVO> rlist = (List<ReviewVO>) getArguments().getSerializable("rlist");

        return view;
    }

}
