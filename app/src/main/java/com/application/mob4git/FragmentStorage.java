package com.application.mob4git;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FragmentStorage extends Fragment {
    View mainView;
    RecyclerView storage_recycler = null;
    Storage_Adapter mAdapter = null;
    ArrayList<RecyclerItem> mList = new ArrayList<RecyclerItem>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment_storage, container, false);

        addItem("이번강의의 내용은", "15:55");
        addItem("모바일프로그래밍 4주차", "21:17");
        addItem("데이터프레임 강좌", "10:44");
        addItem("교수님 설명 내용 메모", "42:10");

        // 리사이클러뷰에 LinearLayoutManager 객체 지정.
        RecyclerView recyclerView = mainView.findViewById(R.id.storage_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // 리사이클러뷰에 SimpleTextAdapter 객체 지정.
        Storage_Adapter adapter = new Storage_Adapter(getActivity(), mList) ;
        recyclerView.setAdapter(adapter) ;

        return mainView;
    }


    public void addItem(String title, String time){
        RecyclerItem item = new RecyclerItem();

        item.setTitle(title);
        item.setTime(time);

        mList.add(item);
    }
}