package com.application.mob4git;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class StorageActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private Storage_Adapter adapter;
    private List<RecyclerItem> memos;

    private AppDatabase db;
    private String searchtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        ActionBar ac = getSupportActionBar();
        ac.setTitle("저장소");

        recyclerView = findViewById(R.id.storage_recycler);
        linearLayoutManager = new LinearLayoutManager(this);
        adapter = new Storage_Adapter();    //수정

        memos = AppDatabase.getInstance(this).memoDao().getAll();
        int size = memos.size();
        for(int i=0; i< size; i++){
            adapter.addItem(memos.get(i));
        }

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);



        BottomNavigationView BNView = findViewById(R.id.navigationView);
        BNView.setSelectedItemId(R.id.StorageItem);
        BNView.setOnNavigationItemSelectedListener(new ItemSelectedListener());

    }


    @Override
    protected void onStart(){
        memos = AppDatabase.getInstance(this).memoDao().getAll();
        adapter.addItems((ArrayList) memos);
        super.onStart();
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_storage, menu);
        return true;
    }

    //검색 버튼 눌렀을때 기능
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.search:
                EditText editText = new EditText(this);
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("검색할 메모의 제목을 입력하세요");
                builder.setView(editText);
                builder.setPositiveButton("검색", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        List<RecyclerItem> memo = AppDatabase.getInstance(getApplicationContext()).memoDao().getAll();
                        searchtitle = editText.getText().toString();
                        int size = memo.size();
                        Toast.makeText(getApplicationContext(), "메모위치 ",Toast.LENGTH_LONG).show();
                        for(int i=0; i< size; i++){
                            String a = memo.get(i).getTitleStr();
                            if(searchtitle==a){
                                Toast.makeText(getApplicationContext(), "메모위치 "+i+1,Toast.LENGTH_LONG).show();

                            }
                        }


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

        }


        return super.onOptionsItemSelected(item);
    }

    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch(item.getItemId()){
                case R.id.HomeItem:
                    finish();
                    break;

                case R.id.StorageItem:
                    // intent_storage = new Intent(getApplicationContext(), StorageActivity.class);
                    //startActivity(intent_storage);
                    break;

                case R.id.SettingItem:
                    finish();
                    Intent intent_setting = new Intent(getApplicationContext(), SettingActivity.class);
                    startActivity(intent_setting);
                    break;

            }
            return true;
        }
    }
}
