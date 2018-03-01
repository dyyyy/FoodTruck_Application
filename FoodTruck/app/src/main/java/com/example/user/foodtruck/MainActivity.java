package com.example.user.foodtruck;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.adapter.LoginPreference;
import com.example.user.adapter.MenuAdapter;

import java.security.acl.Group;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemClickListener, View.OnClickListener {
    private BackPressCloseHandler backPressCloseHandler;

    Intent intent;
    Button loginBtn, registerBtn;
    LinearLayout userInfoLayout, userLoginLayout;

    TextView userName, userMileage, loginText;

    /*쉐어프리퍼런스로 로그인 기능*/
    /*앱이 종료되면 autoLogin이 아닐때 sharedpreference (로그인)정보 삭제*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("d", "onCreate");
        backPressCloseHandler = new BackPressCloseHandler(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        /*네비게이션 메뉴 바디*/
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setItemTextColor(ColorStateList.valueOf(Color.BLACK));
        navigationView.setBackgroundColor(Color.LTGRAY);

        navigationView.setNavigationItemSelectedListener(this);


        View header = navigationView.getHeaderView(0);
        loginText = header.findViewById(R.id.login_text); //
        loginBtn = header.findViewById(R.id.nav_loginBtn);
        registerBtn = header.findViewById(R.id.nav_register); //

        userInfoLayout = header.findViewById(R.id.nav_userinfo_layout);
        userLoginLayout = header.findViewById(R.id.nav_login_layout);

       // navHeaderLayout = header.findViewById(R.id.nav_header_layout);

        userName = header.findViewById(R.id.userName);
        userMileage = header.findViewById(R.id.userMileage);

        loginCheck();

        int[] images = {R.drawable.korean, R.drawable.snack, R.drawable.japanese, R.drawable.chicken, R.drawable.pizza, R.drawable.chinese, R.drawable.pig, R.drawable.midnight, R.drawable.western};
        String[] values = getResources().getStringArray(R.array.menu_list);
        ExpandableHeightGridView gridView = findViewById(R.id.gridView);
        gridView.setExpanded(true);
        MenuAdapter menuAdapter = new MenuAdapter(this, images, values);

        gridView.setAdapter(menuAdapter);
        gridView.setOnItemClickListener(this);


    }

    /*menu Adapter*/
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        intent = new Intent(this, MenuActivity.class);
        intent.putExtra("position", position);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            /*로그인 버튼*/
            case R.id.nav_loginBtn:
                intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_register:
                intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_userinfo_layout:
                intent = new Intent(this, UserDetailActivity.class);
                startActivity(intent);
                break;
            default:
                Toast.makeText(this, "없는 버튼입니다.", Toast.LENGTH_SHORT).show();
                break;
        }

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
        Toast.makeText(getApplicationContext(), "optionItemselected id : " + id, Toast.LENGTH_SHORT).show();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*navigation item*/
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_notice:
                intent = new Intent(this, NoticeActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_event:
                intent = new Intent(this, EventActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_service_center:
                intent = new Intent(this, ServiceCenterActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_settings:
                intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                break;
            default:
                Toast.makeText(getApplicationContext(), "message" + id, Toast.LENGTH_SHORT).show();
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);


        return true;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        loginCheck();
    }

    private void loginCheck() {
        LoginPreference pref = new LoginPreference(this);
        /*로그인정보가 있는지 없는지 확인 후 로그인버튼을 보여줄지 사용자 정보를 보여줄지 결정*/
        boolean user = pref.getValue(LoginPreference.USER_INFO, false);

        //저장된 사용자 정보가 있을때
        if (user) {
            //로그인버튼은 안보여주고 프로필과 사용자 정보를 보여준다.

            userInfoLayout.setVisibility(View.VISIBLE);
            userLoginLayout.setVisibility(View.GONE);

            userInfoLayout.setOnClickListener(this);

            userName.setText(pref.getValue(LoginPreference.MEMBER_NAME,"anonymous"));
            userMileage.setText(pref.getValue(LoginPreference.MEMBER_MILEAGE,"0")+"P");


        } else { //저장된 사용자정보가 없을때
            //로그인 버튼과 이벤트를 보여준다.


            userInfoLayout.setVisibility(View.GONE);
            userLoginLayout.setVisibility(View.VISIBLE);

            loginBtn.setOnClickListener(this);
            registerBtn.setOnClickListener(this);
        }
    }
}