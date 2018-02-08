package com.example.user.foodtruck;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import com.example.user.adapter.MenuAdapter;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemClickListener, View.OnClickListener {
    private BackPressCloseHandler backPressCloseHandler;

    Intent intent;
/*쉐어프리퍼런스로 로그인 기능 권한주기*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        backPressCloseHandler = new BackPressCloseHandler(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //Button loginBtn = findViewById(R.id.nav_loginBtn);
        Button loginButton = findViewById(R.id.loginBtn);
        loginButton.setOnClickListener(this);


        /*네비게이션 메뉴 바디*/
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);





        int[] images = {R.drawable.ic_menu_slideshow, R.drawable.ic_menu_manage, R.drawable.ic_menu_send, R.drawable.ic_menu_camera, R.drawable.ic_menu_camera, R.drawable.ic_menu_camera, R.drawable.ic_menu_send, R.drawable.ic_menu_camera, R.drawable.ic_menu_share};
        String[] values = {"한식", "분식", "돈까스.회.일식", "치킨", "피자", "중국집", "족발.보쌈", "야식", "찜.탕"};
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
        intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
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

    /*navigation item*/
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.
        int id = item.getItemId();


        switch(id){
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
                Toast.makeText(getApplicationContext(), "message"+id, Toast.LENGTH_SHORT).show();
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);


        return true;
    }


}
