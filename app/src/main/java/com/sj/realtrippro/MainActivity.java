package com.sj.realtrippro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    //메인화면의 뷰들
    ListView tripList;
    ImageButton btn_add;

    // 두줄짜리 어댑터 선언 for TripList
    SimpleAdapter mainAdap;

    // TripList 한 항목에 대한 데이터 저장 객체
    HashMap<String,String> mItem;

    // 모든 TripList 정보를 저장하는 데이터 객체 생성 : 공간만
    ArrayList<HashMap<String,String>> tList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //선언한 객체 가져오기
        tripList = findViewById(R.id.tripList);
        btn_add =findViewById(R.id.btn_add);

        //어댑터 생성
        mainAdap = new SimpleAdapter(this,tList,android.R.layout.simple_list_item_2
        , new String[]{"item1","item2"}, new int[]{android.R.id.text1,android.R.id.text2});

        //리스트뷰에 adapter 연결
        tripList.setAdapter(mainAdap);


        //btn_add 화면 전환
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //화면 전환을 위한 Intent 생성
                Intent mainIt = new Intent(MainActivity.this, TripAddActivity.class);
                startActivity(mainIt);

}
        });



    } // onCreated END
}//MainActivity END
