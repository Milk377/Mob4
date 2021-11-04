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

public class FragmentHome extends Fragment implements View.OnTouchListener{

    View mainView;
    View divider;
    float oldYvalue;
    Button button_horizontal;
    Button button_vertical;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment_home, container, false);
        divider = mainView.findViewById(R.id.divider);
        button_horizontal = mainView.findViewById(R.id.button_horizontal);
        button_vertical = mainView.findViewById(R.id.button_vertical);

        divider.setOnTouchListener(this);

        return mainView;

    }

    //xml에서 버튼들이 View를 따라 움직이는거는 구현했으나 실제 기기에서는 움직이질 않음
    // -> View onTouch리스너 기능 속에 버튼의 좌표도 같이 움직이게끔하면 해결할 수 있지 않을까
    // 구현성공
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int height = ((ViewGroup) v.getParent()).getHeight() - v.getHeight();

        if(event.getAction() == MotionEvent.ACTION_DOWN){
            oldYvalue = event.getY();
        }else if(event.getAction() == MotionEvent.ACTION_MOVE){
            v.setY(event.getRawY()-(oldYvalue+v.getHeight()));
            //
            button_horizontal.setY(event.getRawY() - (oldYvalue-180+v.getHeight()));
            button_vertical.setY(event.getRawY() - (oldYvalue-180+v.getHeight()));
        }else if(event.getAction() == MotionEvent.ACTION_UP){
            if(v.getY() > height-500){
                v.setY(height-500);
                //
                button_horizontal.setY(height-320);
                button_vertical.setY(height-320);
            }else if(v.getY() <500){
                v.setY(500);
                //
                button_horizontal.setY(680);
                button_vertical.setY(680);
            }/*else if(v.getY() <0 || v.getY() > height){       //해당부분은 동작안하는거같음
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
