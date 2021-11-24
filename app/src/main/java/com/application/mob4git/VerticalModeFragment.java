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

-> 프래그먼트에서 프래그먼트를 호출하는데 에서 스스로가 스스로를 호출하게끔 하면 첫화면으로 돌아갈 거 라고 생각함
 추후 테스트 해봐야함

-> 미리 필요한 텍스트뷰를 쓸 수도 있지만, 상황에 따라 동적으로 생성이 필요할 때도 있음
 visible속성을 이용하면될거로 생각
 */

public class VerticalModeFragment extends Fragment implements View.OnTouchListener{

    View mainView;
    View divider;
    float oldYvalue;


    // 세로모드의 기능 1, 2, 3을 임시로 표현한 것으로 나중에 id와함께 변경해줘야 한다
    // webView도 일단은 생각이 나지않기 때문에 zoom의 홈페이지를 시작점으로 주고 연결시키게끔? 하는걸 생각 현재의 url은 그냥 예시일뿐
    Button button_1;
    Button button_2;
    Button button_3;
    WebView webView;
    EditText editText_1;
    ImageView saveMemo;

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


        //webView 설정들
        webView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        //예시 url 현재로서는 zoom의 홈페이지 혹은 로그인공간? 같은곳을 설정해야 할까 고민
        webView.loadUrl("https://www.google.co.kr/");

        //divider에 터치기능을 추가하고 제일 앞으로 이동시킴
        divider.setOnTouchListener(this);
        divider.bringToFront();


        //db
        db = AppDatabase.getInstance(getActivity());

        button_1.setOnClickListener(view ->{
            editText_1.setVisibility(View.VISIBLE);
            saveMemo.setVisibility(View.VISIBLE);

            // 나중에 함수로 뺄거 - 속성값을 정수로정해줘서넘기는식 속성값은 현재 메모를하려고 하는상황인지,
            // 메모가끝나서 세로모드의첫화면으로 돌아가는상황인지 메모를하려고하는 상황이면 ex) 1을 인자로전달 -> 버튼 invisible 아니라면 2전달 -> 버튼 visible
            button_1.setVisibility(View.INVISIBLE);
            button_2.setVisibility(View.INVISIBLE);
            button_3.setVisibility(View.INVISIBLE);

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
                    editText_1.setText(null);
                    editText_1.setVisibility(View.INVISIBLE);
                    button_1.setVisibility(View.VISIBLE);
                    button_2.setVisibility(View.VISIBLE);
                    button_3.setVisibility(View.VISIBLE);
                    saveMemo.setVisibility(View.INVISIBLE);

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
        }else if(event.getAction() == MotionEvent.ACTION_UP){       //뷰에서 손을 뗀 순간   여기는 뷰를 제약범위 외에의 이동을시켰을 시 정해진 위치로 강제 이동시키는 기능
            if(v.getY() > height-500){
                v.setY(height-500);
                //
                button_1.setY(height-320);
                button_2.setY(height-320);
                button_3.setY(height-320);
                editText_1.setY(height-320);
                saveMemo.setY(height-440);
            }else if(v.getY() <500){
                v.setY(500);
                //
                button_1.setY(680);
                button_2.setY(680);
                button_3.setY(680);
                editText_1.setY(680);
                saveMemo.setY(560);
            }
        }
        return true;
    }


}
