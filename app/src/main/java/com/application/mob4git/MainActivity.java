package com.application.mob4git;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private FragmentHome fragmentHome = new FragmentHome();
    private FragmentStorage fragmentStorage = new FragmentStorage();
    private FragmentSettings fragmentSettings = new FragmentSettings();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //앱을 처음켰을때 대표적 화면 나오고 2.5초뒤에 사라지고 화면나오게하기
        if (savedInstanceState == null) {
            ImageView ImageView_Logo = findViewById(R.id.ImageView_Logo);
            ImageView_Logo.setVisibility(View.VISIBLE);
            // 네비게이션바 숨기기

            BottomNavigationView BNView = findViewById(R.id.navigationView);
            BNView.setVisibility(View.INVISIBLE);
            // 툴바 숨기기
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.hide();
            }

            //화면 2.5초 나오게하고 사라지기
            ImageView_Logo.postDelayed(() -> {     //delay뒤에 실행 할 코드
                ImageView_Logo.setVisibility(View.INVISIBLE);
                // 툴바,네비게이션뷰 보이게
                if (actionBar != null) {
                    actionBar.show();
                    BNView.setVisibility(View.VISIBLE);
                }
            }, 2500);
        }

        //시작하자마자 나오는 홈 화면에 HomeFragme이 실행되게끔하는코드
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragmentHome).commitAllowingStateLoss();

        BottomNavigationView BNView = findViewById(R.id.navigationView);
        BNView.setOnNavigationItemSelectedListener(new ItemSelectedListener());
    }

    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            switch(item.getItemId()){
                case R.id.HomeItem:
                    transaction.replace(R.id.frameLayout, fragmentHome).commitAllowingStateLoss();
                    break;

                case R.id.StorageItem:
                    transaction.replace(R.id.frameLayout, fragmentStorage).commitAllowingStateLoss();
                    break;

                case R.id.SettingItem:
                    transaction.replace(R.id.frameLayout, fragmentSettings).commitAllowingStateLoss();
                    break;
            }
            return true;
        }
    }
}