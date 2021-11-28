package com.application.mob4git;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/*
이 프래그먼트에서 세로모드 기능들을 수행하고 저장이 끝나면 다시 이 프래그먼트 첫화면으로 돌아와야 한다

 */

public class VerticalModeFragment extends Fragment implements View.OnTouchListener{

    View mainView;
    View divider;
    float oldYvalue;


    //기능버튼 1,2,3
    Button button_1;
    Button button_2;
    Button button_3;
    WebView webView;
    EditText editText_1;
    ImageView saveMemo;
    ImageView backMemo;

    private AppDatabase db;



    public static VerticalModeFragment newInstance(){
        return new VerticalModeFragment();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment_vertical, container, false);
        divider = mainView.findViewById(R.id.divider);
        //버튼들  명칭 나중에 바꿔야함
        button_1 = mainView.findViewById(R.id.button_1);
        button_2 = mainView.findViewById(R.id.button_2);
        button_3 = mainView.findViewById(R.id.button_3);
        webView = mainView.findViewById(R.id.webView);
        editText_1 = mainView.findViewById(R.id.editText_1);
        saveMemo = mainView.findViewById(R.id.SaveMemo);
        backMemo = mainView.findViewById(R.id.BackMemo);


        //

        //webView 설정들
        webView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.loadUrl("https://www.google.co.kr/");


        //divider에 터치기능을 추가하고 제일 앞으로 이동시킴
        divider.setOnTouchListener(this);
        divider.bringToFront();


        //db
        db = AppDatabase.getInstance(getActivity());

        button_1.setOnClickListener(view ->{
            editText_1.setVisibility(View.VISIBLE);
            saveMemo.setVisibility(View.VISIBLE);
            backMemo.setVisibility(View.VISIBLE);
            button_1.setVisibility(View.INVISIBLE);
            button_2.setVisibility(View.INVISIBLE);
            button_3.setVisibility(View.INVISIBLE);

        });


        //뒤로가기 누르면 다시 기능선택화면
        backMemo.setOnClickListener(view -> {
                    setInvisible();
        });

        button_2.setOnClickListener(view -> {

            Intent intent = new Intent(getActivity(), PaintActivity.class);
            startActivity(intent);

        });

        //저장 아이콘을 클릭했을때, 제목을 입력하라는 메시지창 생성
        saveMemo.setOnClickListener(view ->{

            EditText editText = new EditText(getActivity());
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("제목을 입력");
            builder.setView(editText);

            builder.setPositiveButton("저장", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    Toast.makeText(getActivity(), "저장되었습니다", Toast.LENGTH_SHORT).show();

                    String title = editText.getText().toString();
                    String text = editText_1.getText().toString();
                    //db 저장
                    RecyclerItem memo = new RecyclerItem(text,title);
                    db.memoDao().insert(memo);

                    //저장했으니 시작상태로 돌아가야함
                    setInvisible();

                    dialog.dismiss();
                }
            });

            builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    dialog.dismiss();
                }
            });

            builder.show();

        });




        return mainView;
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int height = ((ViewGroup) v.getParent()).getHeight() - v.getHeight();

        if(event.getAction() == MotionEvent.ACTION_DOWN){           //뷰를 누른 순간
            oldYvalue = event.getY();
        }else if(event.getAction() == MotionEvent.ACTION_MOVE){     //뷰를 드래그하는 동안
            v.setY(event.getRawY()-(oldYvalue+v.getHeight()));
            //
            button_1.setY(event.getRawY() - (oldYvalue-180+v.getHeight()));
            button_2.setY(event.getRawY() - (oldYvalue-180+v.getHeight()));
            button_3.setY(event.getRawY() - (oldYvalue-180+v.getHeight()));
            editText_1.setY(event.getRawY() - (oldYvalue-180+v.getHeight()));
            saveMemo.setY(event.getRawY() - (oldYvalue-70+v.getHeight()));
            backMemo.setY(event.getRawY() - (oldYvalue-70+v.getHeight()));

        }else if(event.getAction() == MotionEvent.ACTION_UP){       //뷰에서 손을 뗀 순간   여기는 뷰를 제약범위 외에의 이동을시켰을 시 정해진 위치로 강제 이동시키는 기능
            if(v.getY() > height-500){
                v.setY(height-500);
                //
                button_1.setY(height-320);
                button_2.setY(height-320);
                button_3.setY(height-320);
                editText_1.setY(height-320);
                saveMemo.setY(height-440);
                backMemo.setY(height-440);
            }else if(v.getY() <500){
                v.setY(500);
                //
                button_1.setY(680);
                button_2.setY(680);
                button_3.setY(680);
                editText_1.setY(680);
                saveMemo.setY(560);
                backMemo.setY(560);
            }
        }
        return true;
    }

    private void setInvisible(){
        editText_1.setText(null);
        editText_1.setVisibility(View.INVISIBLE);
        button_1.setVisibility(View.VISIBLE);
        button_2.setVisibility(View.VISIBLE);
        button_3.setVisibility(View.VISIBLE);
        saveMemo.setVisibility(View.INVISIBLE);
        backMemo.setVisibility(View.INVISIBLE);
    }


}
