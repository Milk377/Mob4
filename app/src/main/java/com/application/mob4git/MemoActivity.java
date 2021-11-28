package com.application.mob4git;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.application.mob4git.AppDatabase;
import com.application.mob4git.RecyclerItem;

public class MemoActivity extends AppCompatActivity {


    private EditText memoTitle;
    private EditText memoText;
    private AppDatabase db;
    private int key;
    private String text;
    private String title;
    private RecyclerItem detail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);


        memoTitle = findViewById(R.id.memoTitle);
        memoText = findViewById(R.id.memoText);
        db = AppDatabase.getInstance(this);

        //RecyclerItem
        detail = getIntent().getParcelableExtra("data");

        key =detail.getKey();
        text = detail.getTextStr();
        title = detail.getTitleStr();


        memoTitle.setText(title);
        memoText.setText(text);

    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case R.id.save:
                EditText editText = new EditText(this);
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("수정할 제목을 입력하세요");
                builder.setView(editText);

                builder.setPositiveButton("확인", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        text = memoText.getText().toString();
                        title = editText.getText().toString();
                        db.memoDao().update(text,title,key);


                        dialog.dismiss();
                        finish();
                    }
                });
                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();

                break;

            case R.id.delete:
                AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
                builder2.setTitle("작성한 메모를 삭제합니까?.");

                builder2.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        db.memoDao().delete(detail);
                        Toast.makeText(getApplicationContext(), "삭제되었습니다", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });

                builder2.setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder2.show();

                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_control, menu);
        return true;
    }
}
