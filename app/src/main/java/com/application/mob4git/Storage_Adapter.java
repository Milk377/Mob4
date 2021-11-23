package com.application.mob4git;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class Storage_Adapter extends RecyclerView.Adapter<Storage_Adapter.ViewHolder> {
    private Activity activity;

    private ArrayList<RecyclerItem> mData = new ArrayList<>();




    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView key;
        private TextView textStr ;
        private TextView titleStr;

        public ViewHolder(@NonNull View itemView) {
            super(itemView) ;

            // 뷰 객체에 대한 참조. (hold strong reference)
            key = itemView.findViewById(R.id.key);
            textStr = itemView.findViewById(R.id.textView_1) ;
            titleStr = itemView.findViewById(R.id.textView_2);
        }

        public void onBind(RecyclerItem memo, int position){
            String s ="" + (position+1);
            key.setText(s);
            textStr.setText(memo.getTextStr());
            titleStr.setText(memo.getTitleStr());


            //메모 클릭시 상세정보
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(itemView.getContext(), MemoActivity.class);
                intent.putExtra("data", memo);
                itemView.getContext().startActivity(intent);
            });

            itemView.setOnLongClickListener(v -> {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("메모를 지우시겠습니까?");

                builder.setPositiveButton("예", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        mData.remove(memo);
                        AppDatabase.getInstance(itemView.getContext()).memoDao().delete(memo);

                        notifyDataSetChanged();
                        Toast.makeText(v.getContext(), "삭제되었습니다", Toast.LENGTH_SHORT).show();

                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("아니요", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

                builder.show();
                return false;
            });
        }
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item,parent,false);

        return new ViewHolder(view);
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(@NonNull Storage_Adapter.ViewHolder holder, int position) {
        holder.onBind(mData.get(position), position);
    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mData.size() ;
    }

    public void addItem(RecyclerItem memo){
        mData.add(memo);
        notifyDataSetChanged();
    }

    public void addItems(ArrayList<RecyclerItem> memos){
        mData=memos;
        notifyDataSetChanged();
    }
}
