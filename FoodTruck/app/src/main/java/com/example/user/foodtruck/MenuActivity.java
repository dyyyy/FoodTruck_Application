package com.example.user.foodtruck;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.user.adapter.MenuTabAdapter;
import com.example.user.networkutil.NetworkAvailable;
import com.example.user.networkutil.RestTempleatAsyncTask;
import com.example.user.vo.FoodTruckVO;
import com.example.user.vo.ReviewVO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class MenuActivity extends AppCompatActivity {

    int setPosition;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        NetworkAvailable networkAvailable = new NetworkAvailable(this);

        if (networkAvailable.isNetworkAvailable()) {
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
            setPosition = getIntent().getIntExtra("position", 0);

            mViewPager = (ViewPager) findViewById(R.id.container);

            mViewPager.setAdapter(mSectionsPagerAdapter);
            mViewPager.setCurrentItem(setPosition, false);

            TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs1);

            mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
            tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        } else {
            Toast.makeText(this, "network is not available", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static class PlaceholderFragment extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {

        }

        public static PlaceholderFragment newInstance(int sectionNumber) {

            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);

            return fragment;
        }

        private View mFragmentView;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            Log.d("argument", " : " + getArguments().get(ARG_SECTION_NUMBER));

            if (mFragmentView != null) {

                return mFragmentView;
            } else {

                //한번씩만 호출됨 그뒤로 호출안됨..
                mFragmentView = inflater.inflate(R.layout.menu_tab, container, false);
                Map<String, Integer> map = new HashMap<>();
                map.put("category", getArguments().getInt(ARG_SECTION_NUMBER));

                String addr = "/getfoodtrucklist/{category}";
                RestTempleatAsyncTask restTempleatAsyncTask = new RestTempleatAsyncTask(addr, map);

                try {
                    String result = restTempleatAsyncTask.execute().get();
                    List<FoodTruckVO> ftrucklist1 = new ObjectMapper().readValue(result, new TypeReference<List<FoodTruckVO>>() {
                    });
                    ListView listView = mFragmentView.findViewById(R.id.menutablistview);
                    MenuTabAdapter menuTabAdapter = new MenuTabAdapter(listView.getContext(), ftrucklist1);
                    listView.setAdapter(menuTabAdapter);
                    listView.setOnItemClickListener(this);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return mFragmentView;
            }
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            FoodTruckVO vo = (FoodTruckVO) parent.getItemAtPosition(position);

            Intent intent;
            if (vo != null) {
                intent = new Intent(this.getContext(), MenuDetailActivity.class);
                intent.putExtra("object", vo);

                startActivity(intent);

            } else {
                Toast.makeText(this.getContext(), "게시물이 없습니다.", Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        public void onClick(View v) {
            /*페이징 처리부분*/
            Toast.makeText(v.getContext(), "페이징 버튼 : " + v.getId() + "category : " + getArguments().getInt(ARG_SECTION_NUMBER), Toast.LENGTH_SHORT).show();

            switch (getArguments().getInt(ARG_SECTION_NUMBER)) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6:

                    break;
                case 7:

                    break;
                case 8:

                    break;
                case 9:

                    break;
                default:
                    break;
            }


        }
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public Fragment getItem(int position) {

            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public CharSequence getPageTitle(int position) {

            switch (position) {
                case 0:
                    return "tab1";
                case 1:
                    return "tab2";
                case 2:
                    return "tab3";
                case 3:
                    return "tab4";
                case 4:
                    return "tab5";
                case 5:
                    return "tab6";
                case 6:
                    return "tab7";
                case 7:
                    return "tab8";
                case 8:
                    return "tab9";
                default:
                    return "error";


            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 9;
        }

    }


}
