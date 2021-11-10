package com.application.mob4git;

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
import android.widget.TextView;
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

        //webView 설정들
        webView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        //예시 url 현재로서는 zoom의 홈페이지 혹은 로그인공간? 같은곳을 설정해야 할까 고민
        webView.loadUrl("https://copycoding.tistory.com");

        //divider에 터치기능을 추가하고 제일 앞으로 이동시킴
        divider.setOnTouchListener(this);
        divider.bringToFront();


        button_1.setOnClickListener(view ->{
            editText_1.setVisibility(View.VISIBLE);
            // 나중에 함수로 뺄거 - 속성값을 정수로정해줘서넘기는식 속성값은 현재 메모를하려고 하는상황인지,
            // 메모가끝나서 세로모드의첫화면으로 돌아가는상황인지 메모를하려고하는 상황이면 ex) 1을 인자로전달 -> 버튼 invisible 아니라면 2전달 -> 버튼 visible
            button_1.setVisibility(View.INVISIBLE);
            button_2.setVisibility(View.INVISIBLE);
            button_3.setVisibility(View.INVISIBLE);

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
            editText_1.setY(event.getRawY() - (oldYvalue-60+v.getHeight()));
        }else if(event.getAction() == MotionEvent.ACTION_UP){       //뷰에서 손을 뗀 순간   여기는 뷰를 제약범위 외에의 이동을시켰을 시 정해진 위치로 강제 이동시키는 기능
            if(v.getY() > height-500){
                v.setY(height-500);
                //
                button_1.setY(height-320);
                button_2.setY(height-320);
                button_3.setY(height-320);
                editText_1.setY(height-430);
            }else if(v.getY() <500){
                v.setY(500);
                //
                button_1.setY(680);
                button_2.setY(680);
                button_3.setY(680);
                editText_1.setY(570);
            }/*else if(v.getY() <0 || v.getY() > height){       //해당부분은 동작안하는거같음 추후 진행에 문제없다 생각되면 삭제
                if(v.getY() < 0){
                    v.setY(500);
                    //
                    button_horizontal.setY(210);
                    button_vertical.setY(210);
                }else{
                    v.setY(height-500);
                    //
                    button_horizontal.setY(height-410);
                    button_vertical.setY(height-410);
                }
            }*/
        }
        return true;
    }


}
