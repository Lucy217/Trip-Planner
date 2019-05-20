package com.sj.realtrippro;

public class bean {


    //main_table
    int m_id;
    String m_name;
    String m_sdate;
    String m_edate;
    String m_nation;

    //household_setting_table
    int hs_id;
    String hs_nation;
    String hs_budget;
    int hs_m_id;

    //household_table
    int h_id;
    String h_name;
    String h_date;
    String h_time;
    String h_amount;
    String h_memo;
    int h_hs_id;

    //memo_table
    int mm_id;
    String mm_name;
    String mm_img;
    String mm_memo;
    String mm_date;
    String mm_time;
    int mm_m_id;

    //check_table
    int c_id;
    String c_name;
    boolean c_checked;
    int c_m_id;

    //day_table
    int d_id;
    String d_name;
    String d_place;
    String d_time;
    String d_memo;
    String d_date;

}
