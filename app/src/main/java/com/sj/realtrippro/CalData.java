package com.sj.realtrippro;

public class CalData {
    int mYear;
    int mMonth;
    int mDay;

    // 토, 일 색상 구분용
    int dayOfWeek;

    // 일자 선택 시 테두리 선 변경용
    int borderType = R.drawable.border;

    // 해당 일자가 오늘일 때
    boolean today = false;

}
