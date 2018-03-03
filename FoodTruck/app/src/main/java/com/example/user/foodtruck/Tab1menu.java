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
import com.example.user.vo.ProductVO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;


public class Tab1menu extends Fragment implements AdapterView.OnItemClickListener {
    private static final String ARG_SECTION_NUMBER = "section_number";

    public Tab1menu() {
        // Required empty public constructor
    }

    public Tab1menu newInstance(int sectionNumber, String result1) {
        Tab1menu tab1menu = new Tab1menu();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putString("result1", result1);
        tab1menu.setArguments(args);
        return tab1menu;
    }

    private View mFragmentView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        if (mFragmentView != null) {
            Log.d("mFragmentView !=null", "");
            return mFragmentView;
        } else {

            mFragmentView = inflater.inflate(R.layout.fragment_tab1menu, container, false);


            String result1 = getArguments().getString("result1");
            if (!result1.equals("[]")) {
                try {
                    List<ProductVO> pvolist = new ObjectMapper().readValue(result1, new TypeReference<List<ProductVO>>() {
                    });
                    Log.d("pvolist", " : " + pvolist.toString());

                    ListView listView = mFragmentView.findViewById(R.id.menulistview);
                    Tab1menuAdapter tab1menuAdapter = new Tab1menuAdapter(listView.getContext(), pvolist);
                    listView.setAdapter(tab1menuAdapter);
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
