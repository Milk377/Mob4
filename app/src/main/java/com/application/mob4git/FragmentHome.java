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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FragmentHome extends Fragment implements View.OnTouchListener{

    View mainView;
    View divider;
    float oldYvalue;
    Button button_horizontal;
    Button button_vertical;

    //각 Fragment마다 Instance를 반환해 줄 메소드를 생성
    public static FragmentHome newInstance(){
        return new FragmentHome();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment_home, container, false);
        divider = mainView.findViewById(R.id.divider);
        button_horizontal = mainView.findViewById(R.id.button_horizontal);      //click시 Fragment를 전환 가로모드
        button_vertical = mainView.findViewById(R.id.button_vertical);          //click시 Fragment를 전환 세로모드
        divider.setOnTouchListener(this);

        //세로모드 버튼 클릭시 프래그먼트 화면 전환?
        /*Fragment내부에서 다른 Fragment로 이동하는 것은 그 Fragment가 자신의 하위레벨이 아니기 때문에
        내부에서 직접 제어할 수 없으므로, 상위 레벨인 Activity를 호출하여 제어하는 형태가 되어야 합니다.
        Fragment 내에서 Context 객체가필요한 경우 getActivity()를 호출하면 된다. 그러나 getActivity()를 호출하는 것은
        프래그먼트가 액티비티에 첨부되어 있는 경우뿐이니 주의 , 프래그먼트가아직 첨부되지 않았거나 수명 주기가 끝날 무렵
        분리된 경우, getActivity()가 null을 반환 함

        프래그먼트가 있는 액티비티의 수명주기는 해당 프래그먼트의 수명 주기에 직접적인 영향을 미침,
        따라서 액티비티에 대한 각 수명 주기 콜백이 각 프래그먼트에 대한 비슷한 콜백을 유발함,
        ex) onPause()를 받으면, 해당 액티비티 내의 각 프래그먼트가 onPause()를 받습니다.
        */

        button_vertical.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //getActivity로 MainActivity의 replaceFragment 메소드를 불러와야함
                ((MainActivity) getActivity()).replaceFragment(VerticalModeFragment.newInstance());     // 불러올 Fragment의 Instance를 메인으로전달
            }
        });

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
