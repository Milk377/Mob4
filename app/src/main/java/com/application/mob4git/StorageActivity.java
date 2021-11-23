package com.application.mob4git;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class StorageActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private Storage_Adapter adapter;
    private List<RecyclerItem> memos;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);


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

    }


    @Override
    protected void onStart(){
        memos = AppDatabase.getInstance(this).memoDao().getAll();
        adapter.addItems((ArrayList) memos);
        super.onStart();
    }

}
