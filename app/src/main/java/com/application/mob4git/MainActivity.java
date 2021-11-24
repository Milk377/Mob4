package com.application.mob4git;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.PointF;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity{


    private FragmentManager fragmentManager = getSupportFragmentManager();
    private FragmentHome fragmentHome = new FragmentHome();
    //

    //BNView로 Main으로 돌아왔을때 Home 아이콘이 선택되어있도록
    @Override
    protected void onResume() {
        super.onResume();
        BottomNavigationView BNView = findViewById(R.id.navigationView);
        BNView.setSelectedItemId(R.id.HomeItem);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //시작하자마자 나오는 홈 화면에 HomeFragment이 실행되게끔하는코드
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragmentHome).commitAllowingStateLoss();

        BottomNavigationView BNView = findViewById(R.id.navigationView);
        BNView.setSelectedItemId(R.id.HomeItem);
        BNView.setOnNavigationItemSelectedListener(new ItemSelectedListener());


    }
    //Fragment 내부에서 Fragment를 전환할 때 사용할 메소드
    public void replaceFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment).commit();
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
                    Intent intent_storage = new Intent(getApplicationContext(), StorageActivity.class);
                    startActivity(intent_storage);
                    break;

                case R.id.SettingItem:
                    Intent intent_setting = new Intent(getApplicationContext(), SettingActivity.class);
                    startActivity(intent_setting);
                    break;

            }
            return true;
        }
    }

}