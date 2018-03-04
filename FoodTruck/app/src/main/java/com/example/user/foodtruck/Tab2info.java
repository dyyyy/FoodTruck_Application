package com.example.user.foodtruck;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.vo.FoodTruckVO;
import com.nhn.android.maps.NMapContext;
import com.nhn.android.maps.NMapLocationManager;
import com.nhn.android.maps.NMapView;
import com.nhn.android.maps.overlay.NMapPOIdata;
import com.nhn.android.mapviewer.NMapPOIflagType;
import com.nhn.android.mapviewer.NMapViewerResourceProvider;
import com.nhn.android.mapviewer.overlay.NMapOverlayManager;
import com.nhn.android.mapviewer.overlay.NMapPOIdataOverlay;


/**
 * A simple {@link Fragment} subclass.
 */
public class Tab2info extends Fragment {
    private NMapContext nMapContext;
    private NMapViewerResourceProvider mMapViewerResourceProvider;
    private NMapOverlayManager mOverlayManager;
    private static final String CLIENT_ID = "hoDp6jNKOTpbBU2DrBpN";

    private static final String ARG_SECTION_NUMBER = "section_number";
    private FoodTruckVO fvo;

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
            fvo = (FoodTruckVO) getArguments().getSerializable("fvo");

            return mFragmentView;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        nMapContext = new NMapContext(super.getActivity());
        nMapContext.onCreate();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        NMapView mapView = (NMapView) getView().findViewById(R.id.mapView);
        mapView.setClientId(CLIENT_ID);// 클라이언트 아이디 설정
        nMapContext.setupMapView(mapView);
        mapView.setBuiltInZoomControls(true, null);
        // create resource provider
        mMapViewerResourceProvider = new NMapViewerResourceProvider(getContext());

        // create overlay manager
        mOverlayManager = new NMapOverlayManager(getContext(), mapView, mMapViewerResourceProvider);

        NMapLocationManager nMapLocationManager = new NMapLocationManager(getContext());
        nMapLocationManager.enableMyLocation(false);

        Log.d("mylocation", "" + nMapLocationManager.getMyLocation());


        int markerId = NMapPOIflagType.PIN;

        NMapPOIdata nMapPOIdata = new NMapPOIdata(2, mMapViewerResourceProvider);

        nMapPOIdata.beginPOIdata(2);
        nMapPOIdata.addPOIitem(Double.parseDouble(fvo.getLongitude()), Double.parseDouble(fvo.getLatitude()), fvo.getFtruckName(), markerId, 0);

        nMapPOIdata.endPOIdata();

        NMapPOIdataOverlay poiDataOverlay = mOverlayManager.createPOIdataOverlay(nMapPOIdata, null);
        poiDataOverlay.showAllPOIdata(0);
        //  poiDataOverlay.setOnStateChangeListener(onPOIdataStateChangeListener);


    }

    @Override
    public void onStart() {
        super.onStart();
        nMapContext.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        nMapContext.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        nMapContext.onPause();
    }

    @Override
    public void onStop() {
        nMapContext.onStop();
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        nMapContext.onDestroy();
        super.onDestroy();
    }
}
