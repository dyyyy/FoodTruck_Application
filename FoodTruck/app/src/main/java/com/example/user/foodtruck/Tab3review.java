package com.example.user.foodtruck;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.user.adapter.MenuTabAdapter;
import com.example.user.adapter.Tab1menuAdapter;
import com.example.user.adapter.Tab3reviewAdapter;
import com.example.user.vo.ProductVO;
import com.example.user.vo.ReviewVO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Tab3review extends Fragment implements AdapterView.OnItemClickListener {


    private static final String ARG_SECTION_NUMBER = "section_number";

    public Tab3review() {
        // Required empty public constructor
    }


    public Tab3review newInstance(int sectionNumber, String result2) {
        Tab3review tab3review = new Tab3review();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putString("result2", result2);
        tab3review.setArguments(args);
        return tab3review;
    }

    private View mFragmentView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mFragmentView != null) {
            Log.d("mFragmentView !=null", "");
            return mFragmentView;
        } else {
            mFragmentView = inflater.inflate(R.layout.fragment_tab3review, container, false);


            String result2 = getArguments().getString("result2");
            if (!result2.equals("[]")) {
                try {
                    List<ReviewVO> rvolist = new ObjectMapper().readValue(result2, new TypeReference<List<ReviewVO>>() {
                    });
                    Log.d("rvolist", " : " + rvolist.toString());

                    ListView listView = mFragmentView.findViewById(R.id.reviewlistview);
                    Tab3reviewAdapter tab3reviewAdapter = new Tab3reviewAdapter(listView.getContext(), rvolist);
                    listView.setAdapter(tab3reviewAdapter);
                    listView.setOnItemClickListener(this);


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return mFragmentView;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
