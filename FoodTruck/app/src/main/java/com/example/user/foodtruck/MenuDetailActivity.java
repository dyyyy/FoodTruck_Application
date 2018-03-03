package com.example.user.foodtruck;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.user.networkutil.NetworkAvailable;
import com.example.user.networkutil.RestTempleatAsyncTask;
import com.example.user.vo.FoodTruckVO;
import com.example.user.vo.NoticeVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class MenuDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private int imgaddr[] = {R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5, R.drawable.img6, R.drawable.img7, R.drawable.img8, R.drawable.img9, R.drawable.img10, R.drawable.img11};

    private FoodTruckVO fvo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_detail);
        NetworkAvailable networkAvailable = new NetworkAvailable(this);

        if (networkAvailable.isNetworkAvailable()) {

            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            fvo = (FoodTruckVO) getIntent().getSerializableExtra("object");


            try {
                String addr1 = "/getproductlist/{ftruckNo}";
                String addr2 = "/getreviewlist/{ftruckNo}";
                Map<String, String> map = new HashMap<String, String>();
                map.put("ftruckNo", fvo.getFtruckNo());
                RestTempleatAsyncTask restTempleatAsyncTask1 = new RestTempleatAsyncTask(addr1, map);
                RestTempleatAsyncTask restTempleatAsyncTask2 = new RestTempleatAsyncTask(addr2, map);

                String result1 = restTempleatAsyncTask1.execute().get();
                String result2 = restTempleatAsyncTask2.execute().get();

                mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), result1, fvo, result2);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            mViewPager = (ViewPager) findViewById(R.id.container);
            mViewPager.setAdapter(mSectionsPagerAdapter);


            toolbar.setTitle(fvo.getFtruckName());

            TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs2);

            mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
            tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

            Button callBtn = findViewById(R.id.callBtn);
            callBtn.setOnClickListener(this);

            ImageView imageView = findViewById(R.id.ftruckdetailimgview);

            String addr = fvo.getFtruckImg().replace("\\resources\\img\\upload\\", "");
            addr = addr.replace(".jpg", "");

            switch (addr) {
                case "img1":
                    imageView.setImageResource(imgaddr[0]);
                    break;
                case "img2":
                    imageView.setImageResource(imgaddr[1]);
                    break;
                case "img3":
                    imageView.setImageResource(imgaddr[2]);
                    break;
                case "img4":
                    imageView.setImageResource(imgaddr[3]);
                    break;
                case "img5":
                    imageView.setImageResource(imgaddr[4]);
                    break;
                case "img6":
                    imageView.setImageResource(imgaddr[5]);
                    break;
                case "img7":
                    imageView.setImageResource(imgaddr[6]);
                    break;
                case "img8":
                    imageView.setImageResource(imgaddr[7]);
                    break;
                case "img9":
                    imageView.setImageResource(imgaddr[8]);
                    break;
                case "img10":
                    imageView.setImageResource(imgaddr[9]);
                    break;
                case "img11":
                    imageView.setImageResource(imgaddr[10]);
                    break;
                default:
                    break;
            }
        } else {
            Toast.makeText(this, "network is not available", Toast.LENGTH_SHORT).show();
            finish();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Intent intent;

        switch (v.getId()) {
            case R.id.callBtn:
                intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + fvo.getFtruckTel()));
                startActivity(intent);
                break;
                /*바구니 구현*/
            case R.id.basketBtn:
                break;
            default:
                Toast.makeText(this, "없는 버튼입니다.", Toast.LENGTH_SHORT).show();
                break;

        }
    }

    /*
        public static class PlaceholderFragment extends Fragment {

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

            @Override
            public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                     Bundle savedInstanceState) {
                View view = inflater.inflate(R.layout.fragment_menu_detail, container, false);
                Button btn = view.findViewById(R.id.tab1btn);

                switch (getArguments().getInt(ARG_SECTION_NUMBER)) {
                    case 1:



                        return view;
                    case 2:


                        return view;
                    case 3:


                        return view;
                    default:
                        return null;
                }

                //TextView textView = (TextView) rootView.findViewById(R.id.section_label);
                //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));

                //메뉴 정보 리뷰 보여주기
            }
        }
    */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        private String result1;
        private FoodTruckVO fvo;
        private String result2;

        public SectionsPagerAdapter(FragmentManager fm, String result1, FoodTruckVO fvo, String result2) {
            super(fm);
            this.result1 = result1;
            this.fvo = fvo;
            this.result2 = result2;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    Tab1menu tab1menu = new Tab1menu();
                    return tab1menu.newInstance(position + 1, result1);
                case 1:
                    Tab2info tab2info = new Tab2info();
                    return tab2info.newInstance(position + 1, fvo);
                case 2:
                    Tab3review tab3review = new Tab3review();
                    return tab3review.newInstance(position + 1, result2);
                default:
                    return null;
            }

            //   return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }
}
