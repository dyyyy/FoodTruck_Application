package com.example.user.foodtruck;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.user.vo.FoodTruckVO;
import com.example.user.vo.ProductVO;
import com.example.user.vo.ReviewVO;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Tab2info extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    public Tab2info() {
        // Required empty public constructor
    }

    public Tab2info newInstance(int sectionNumber, FoodTruckVO fvo) {
        Tab2info tab2info = new Tab2info();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putSerializable("fvo", fvo);
        tab2info.setArguments(args);
        return tab2info;
    }

    private View mFragmentView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mFragmentView != null) {
            Log.d("mFragmentView !=null", "");
            return mFragmentView;
        } else {
            mFragmentView = inflater.inflate(R.layout.fragment_tab2info, container, false);

            int secnum = getArguments().getInt(ARG_SECTION_NUMBER);
            Log.d("secnum", " : " + secnum);
            FoodTruckVO fvo = (FoodTruckVO) getArguments().getSerializable("fvo");
            Log.d("fvo", " : " + fvo.toString());

            return mFragmentView;
        }
    }


}
