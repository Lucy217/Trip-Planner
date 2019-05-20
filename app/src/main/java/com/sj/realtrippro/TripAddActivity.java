package com.sj.realtrippro;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;

public class TripAddActivity extends AppCompatActivity {

    //AddActivity View 선언
    ImageButton btn_back, btn_save, btn_prev, btn_next;
    EditText tripTitle, addNation;
    TextView tvMonth;
    Spinner spNation;
    GridView gridCal;

    //그리드 뷰 안에 뷰들
    TextView tvDay;
    LinearLayout calLay;

    //달력 날짜 데이터 저장소
    ArrayList<CalData> addTripData = new ArrayList<>();

    //어댑터 선언
    CalAdapter ca;

    //DB 관련

    //현재 날짜와 전년, 전달 정보 저장 변수
    int nowYear, nowMonth, nowDay;
    int cYear = -1, cMonth = -1;

    //그리드 뷰에서 선택한 시작 일자 정보 저장 변수
    int sYear, sMonth, sDay;

    //그리드 뷰에서 선택한 끝 일자 정보 저장변수
    int eYear, eMonth, eDay;

    //선택한 국가 저장 변수
    String sNation;
    

    //국가 이름을 저장할 배열 선언
    ArrayList<String> nations = new ArrayList<>();

    //nations 리스트 뷰를 사용하기 위한 ArrayAdapter 선언
    ArrayAdapter<String> sAdap;

    //국가 배열 선언
    String country[] = {
            "대한민국","일본","대만","필리핀","중국","마카오","홍콩","베트남","라오스","말레이시아","괌","러시아","태국","인도네시아","캄보디아"
            ,"미안마","싱가포르","스리랑카","네팔","방글라데시","호주","인도","몽골","몰디브","덴마크","프랑스","우주베키스탄","미국","독일","뉴질랜드"
            ,"이탈리아","스웨덴","영국","네덜란드","터키","스위스","노르웨이","아일랜드","벨기에","스페인","우크라이나","기타"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_add);


        //AddActivity View 객체 선언
        btn_back = findViewById(R.id.btn_back);         btn_save = findViewById(R.id.btn_save);
        btn_prev = findViewById(R.id.btn_prev);         btn_next = findViewById(R.id.btn_next);
        tripTitle = findViewById(R.id.tripTitle);       addNation = findViewById(R.id.addNation);
        tvMonth = findViewById(R.id.tvMonth);           spNation = findViewById(R.id.spNation);
        gridCal = findViewById(R.id.gridCal);

        //그리드뷰에 어댑터 연결
        ca = new CalAdapter(this);
        gridCal.setAdapter(ca);


        //back 버튼 처리하기
      btn_back.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              finish();
          }
      });

        //Spinner
        //만들어둔 nation List 에 아이템 추가
        for(int i = 0; i <country.length; i++){
            nations.add(country[i]);
        }
        sAdap = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,
                nations);

        spNation.setAdapter(sAdap);

        spNation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(nations.get(position).equals("기타")){
                    addNation.setVisibility(View.VISIBLE);
                }

            sNation = nations.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





      //달력
      //gridView 에 날짜 데이터 생성
        //현재 날짜 데이터 처리
        Calendar cal = Calendar.getInstance(); // 현재 날짜 구하기
        nowYear = cal.get(Calendar.YEAR);
        nowMonth = cal.get(Calendar.MONTH);
        nowDay = cal.get(Calendar.DAY_OF_MONTH);

        //달력 데이터 생성 메소드
        setCalData(nowYear,nowMonth,nowDay);

        String calTitle = nowYear+"년 "+(nowMonth+1) + "월";
        tvMonth.setText(calTitle);

        //btn_prev, btn_next 버튼처리
        btn_prev.setOnClickListener(new View.OnClickListener() {
            //버튼이 처음 눌렸을 때의 처리
            @Override
            public void onClick(View v) {
                if(cYear == -1 && cMonth == -1){
                    cYear =  nowYear;
                    cMonth = nowMonth;
                }

                //1월일 경우 이전달을 누르면
                //년도 -1 월 12월
                if(cMonth > 0){
                    cMonth--;
                }
                else if(cMonth == 0){
                    cYear--;
                    cMonth = 11;
                }

                //년, 월이 변경된 결과가 현재의 년, 월일 경우
                //today 의 처리가 필요
                //setCalData method 를 호출하여 처리
                if(cYear == nowYear && cMonth == nowMonth){
                    setCalData(cYear,cMonth,nowDay);
                }
                else {
                    setCalData(cYear,cMonth,0);
                }
                //변경된 년월일 출력
                String calTitle = cYear+ "년 "+(cMonth +1) + "월";
                tvMonth.setText(calTitle);
                ca.notifyDataSetChanged();

            } // onClick END
        });

        //btn_next 버튼 처리
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cYear == -1 && cMonth == -1){
                    cYear = nowYear;
                    cMonth = nowMonth;
                }
                //1월일 경우 이전 달을 누르면 년도-1, 월은 12월로 변경

                if(cMonth <11){
                    cMonth++;
                }
                else if(cMonth ==11){
                    cYear++;
                    cMonth =0;
                }
                //년, 월이 변경된 결과가 현재의 년, 월인 경우
                //today의 처리가 필요함.
                //setCalData()를 호출하여 처리

                if(cYear == nowYear && cMonth == nowMonth){
                    setCalData(cYear,cMonth,nowDay);
                }
                else {
                    setCalData(cYear,cMonth,0);
                }

                //변경된 년, 월, 일 출력
                String calTitle = cYear + "년 " + (cMonth+1) + "월 ";
                tvMonth.setText(calTitle);
                ca.notifyDataSetChanged();

            }
        });







    } //onCreated END

    //달력 데이터 생성 메소드
    private void setCalData(int nowYear, int nowMonth, int nowDay){

        //새로운 달의 날짜를 저장하기 위해 초기화
        addTripData.clear();

        //출력할 달로 달력 객체를 설정
        Calendar bCal = Calendar.getInstance(); // 새로운 달의 날짜를 구하기 위한 캘린더
        bCal.set(nowYear, nowMonth,1);

        //그 달의 시작 요일 구하기
        int startDay = bCal.get(Calendar.DAY_OF_WEEK)-1;
        //그 달의 마지막 일 수 구하기
        int lastDay = bCal.getActualMaximum(Calendar.DAY_OF_MONTH);

        //시작일을 기반으로 공백 생성
        startDay %= 7;

        for(int i = 0; i < startDay; i++){
            addTripData.add(null);
        }
        int numOfWeek = startDay;
        for(int j= 1; j <= lastDay; j++) {
            numOfWeek++;
            CalData tcd = new CalData();//데이터 저장소 생성
            tcd.mYear = nowYear;
            tcd.mMonth = nowMonth;
            tcd.mDay = j;
            tcd.dayOfWeek = numOfWeek % 7;

            if (j == nowDay) { //오늘
                tcd.today = true;
            } else {
                tcd.today = false;
            }
            addTripData.add(tcd);
        } //for ENd
    } // setCalData END


    //Adapter 메소드
    //DB 처리 안됨
    class CalAdapter extends BaseAdapter{
        Context context;

        CalAdapter(Context con){ context = con; }

        @Override
        public int getCount() { return addTripData.size(); }

        @Override
        public Object getItem(int position) { return null; }

        @Override
        public long getItemId(int position) { return 0; }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            //null 값 처리
            if(convertView == null){
                LayoutInflater lif = getLayoutInflater().from(context);

                convertView = lif.inflate(R.layout.cal_item,parent,false);
            }

            //데이터 입력 작업
            tvDay = convertView.findViewById(R.id.tvDay);
            calLay = convertView.findViewById(R.id.calLay);

            //날짜 정보 객체묶음(arrData)에서 해당 날짜의 객체를 가져옴.
            CalData cd = addTripData.get(position);

            //시작일이 일요일이 아닐 경우 공간 비워주기
            if(cd == null){
                tvDay.setText("");
            }
            else {
                //날짜 데이터가 있는 경우
                String strDay = String.valueOf(cd.mDay);
                tvDay.setText(strDay);

            //오늘 날짜에 해당하는 뷰 처리
            if(cd.today == true){
                tvDay.setTextSize(15);
            }
            else{
                tvDay.setTextSize(12);
            }

            //토요일은 파란색, 일요일은 빨간색
            if(cd.dayOfWeek == 0) {//일요일
                tvDay.setTextColor(Color.BLUE);
            }
            else if(cd.dayOfWeek == 1){
                tvDay.setTextColor(Color.RED);
            }
            else{
                tvDay.setTextColor(Color.BLACK);
            }
            //날짜별 테두리 그리기
            calLay.setBackgroundResource(cd.borderType);

            }//else END

            return convertView;
        } //getView END
    } //CalAdapter END

    @Override
    protected void onResume() {
        super.onResume();
        ca.notifyDataSetChanged();
    }
} //AddTripActivity END
