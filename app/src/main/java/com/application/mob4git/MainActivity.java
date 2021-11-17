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

    //zoom 실행시키는 변수들
    private Intent intent;
    private final String zoomPackage = "us.zoom.videomeetings";

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private FragmentHome fragmentHome = new FragmentHome();
    private FragmentStorage fragmentStorage = new FragmentStorage();
    private FragmentSettings fragmentSettings = new FragmentSettings();
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //시작하자마자 나오는 홈 화면에 HomeFragment이 실행되게끔하는코드
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragmentHome).commitAllowingStateLoss();

        BottomNavigationView BNView = findViewById(R.id.navigationView);
        BNView.setOnNavigationItemSelectedListener(new ItemSelectedListener());

        /* 버튼을 누르면 zoom을 실행시키는 기능이었으나 zoommate 어플 내에서 켜지는게 아닌 새로운 zoom어플로서 켜지기때문에 x
        intent = this.getPackageManager().getLaunchIntentForPackage(zoomPackage);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Button button = (Button) findViewById(R.id.zoom_start);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                MainActivity.this.startActivity(intent);
            }
        });
        */
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