package com.example.user.foodtruck;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private BackPressCloseHandler backPressCloseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        backPressCloseHandler = new BackPressCloseHandler(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
/*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        GridView gridView = findViewById(R.id.gridview);


        TextView textView = findViewById(R.id.contentmain);
        textView.setText("Android 2017 Library 추천 10개\n" +
                "2017.05.25 15:58 by ParkJuno 프로그래밍/Andorid\n" +
                "안녕하세요. 오늘은 라이브러리를 추천하려고합니다. \n" +
                "1. 마티스\n" +
                "이것은 아름다운 로컬 이미지 및 비디오 선택기입니다. 주요 기능 :\n" +
                "\n" +
                "JPEG, PNG, GIF 및 MPEG, MP4,\n" +
                "두 가지 기본 제공 테마를 포함한 맞춤형 테마 적용,\n" +
                "다른 이미지 로더,\n" +
                "사용자 정의 필터 규칙 정의,\n" +
                "내에서 완벽하게 작동 Activities하고 Fragments.\n" +
                "lib의 wiki 에서 더 많은 것을 찾을 수 있습니다 .\n" +
                "\n" +
                "\n" +
                "zhihu / Matisse\n" +
                "\n" +
                "Matisse - : fireworks : 안드로이드 용 잘 디자인 된 로컬 이미지 및 비디오 선택기\n" +
                "github.com\t\n" +
                "2. 스프루스 안드로이드 애니메이션 라이브러리\n" +
                "Spruce는 가벼운 애니메이션 라이브러리로, 화면에서 애니메이션을 안무하게 도와줍니다. 많은 다른 애니메이션 라이브러리가 있으므로 개발자는 각 시각이 적절한 시간에 애니메이션으로 나타나는지 확인해야합니다. Spruce는 디자이너가 복잡한 멀티 뷰 애니메이션을 요청하고 개발자가 프로토 타입을 쓰지 않도록 도와줍니다.\n" +
                "\n" +
                "willowtreeapps / spruce-android\n" +
                "\n" +
                "- 스프루스 애니메이션 라이브러리\n" +
                "github.com\t\n" +
                "3. MaterialChipsInput\n" +
                "칩은 Material Design에서 발표되었습니다. 그들\n" +
                "\n" +
                "연락처와 같은 작은 블록의 복잡한 엔티티를 나타냅니다. 칩에는 사진, 텍스트, 규칙, 아이콘 또는 연락처와 같은 엔티티가 포함될 수 있습니다.\n" +
                "MaterialChipsInput은 Android 용 구성 요소의 구현입니다. 라이브러리는 두 가지보기를 제공 ChipsInput하고 ChipView.\n" +
                "\n" +
                "\n" +
                "pchmn / MaterialChipsInput\n" +
                "\n" +
                "MaterialChipsInput - Android github.com의 Material Design Chips 구성 요소\n" +
                "구현\t\n" +
                "4. Grav\n" +
                "이 라이브러리는 포인트를 기반으로 여러 애니메이션을 만들 수 있습니다. 멋진 애니메이션을 아주 쉽게 만들 수 있습니다. README 에는 많은 예제가 있으므로 여기에서 확인 하십시오 .");

    }

    /*onBackPressed Toast Message*/
    public class BackPressCloseHandler {
        private long backKeyPressedTime = 0;
        private Toast toast;
        private Activity activity;

        public BackPressCloseHandler(Activity context) {
            this.activity = context;
        }

        public void onBackPressed() {
            if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
                backKeyPressedTime = System.currentTimeMillis();
                showGuide();
                return;
            }
            if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
                backKeyPressedTime = System.currentTimeMillis();
                activity.finish();
                toast.cancel();
            }
        }

        private void showGuide() {
            toast = Toast.makeText(activity, "뒤로 버튼을 한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    public void onBackPressed() {
        backPressCloseHandler.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        if (isNetworkAvailable()) {
            // Handle navigation view item clicks here.
            int id = item.getItemId();

            Intent intent;
            if (id == R.id.nav_first_layout) {
                intent = new Intent(this, NoticeActivity.class);
                startActivity(intent);
            } else if (id == R.id.nav_second_layout) {
                intent = new Intent(this, EventActivity.class);
                startActivity(intent);
            } else if (id == R.id.nav_slideshow) {

            } else if (id == R.id.nav_manage) {

            } else if (id == R.id.nav_share) {

            } else if (id == R.id.nav_send) {

            }

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        } else {
            Toast.makeText(getApplicationContext(), "Network is not Available", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private boolean isNetworkAvailable() {
        boolean available = false;

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isAvailable()) {
            available = true;
        }

        return available;
    }
}
