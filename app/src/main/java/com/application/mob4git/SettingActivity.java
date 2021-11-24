package com.application.mob4git;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SettingActivity extends AppCompatActivity {

    ImageButton policy;
    ImageButton Languagebutton;
    ImageButton Noticebutton;
    ImageButton Versionbutton;
    ImageButton Appsetbutton;
    ImageButton Sendmailbutton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_setting);

        policy = findViewById(R.id.policy);
        //policy button
        policy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Policy_dialog.class);
                startActivity(intent);
            }
        });


        //Languagebutton
        Languagebutton=findViewById(R.id.Languagebutton);
        Languagebutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Activity_language.class);
                startActivity(intent);
            }
        });

        //Noticebutton
        Noticebutton=findViewById(R.id.Noticebutton);
        Noticebutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Activity_notice.class);
                startActivity(intent);
            }
        });

        //Versionbutton
        Versionbutton=findViewById(R.id.Versionbutton);
        Versionbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Activity_version.class);
                startActivity(intent);
            }
        });

        //Appsetbutton
        Appsetbutton=findViewById(R.id.Appsetbutton);
        Appsetbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Activity_set.class);
                startActivity(intent);
            }
        });

        //Sendmailbutton
        Sendmailbutton=findViewById(R.id.Sendmailbutton);
        Sendmailbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Activity_sendmail.class);
                startActivity(intent);
            }
        });



        BottomNavigationView BNView = findViewById(R.id.navigationView);
        BNView.setSelectedItemId(R.id.SettingItem);
        BNView.setOnNavigationItemSelectedListener(new SettingActivity.ItemSelectedListener());
    }


    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch(item.getItemId()){
                case R.id.HomeItem:
                    finish();
                    break;

                case R.id.StorageItem:
                    finish();
                    Intent intent_storage = new Intent(getApplicationContext(), StorageActivity.class);
                    startActivity(intent_storage);
                    break;

                case R.id.SettingItem:
                    //Intent intent_setting = new Intent(getApplicationContext(), SettingActivity.class);
                    //startActivity(intent_setting);
                    break;

            }
            return true;
        }
    }
}
