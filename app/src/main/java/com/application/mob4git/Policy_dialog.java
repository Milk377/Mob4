package com.application.mob4git;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Policy_dialog extends AppCompatActivity {
    EditText dialog;
    Button Check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.policy_dialog);
        Button Check=findViewById(R.id.Check);
        Check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //인텐트 호출로 하면은 뒤로가기 버튼을 눌렀을시 무한루프에 빠져가지고 확인 누르면 finish()로 액티비티를 종료
                //Intent intent = new Intent(getApplication(), SettingActivity.class);
                //startActivity(intent);
                finish();
            }
        });
    }
}
