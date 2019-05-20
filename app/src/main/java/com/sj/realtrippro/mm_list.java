package com.sj.realtrippro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

public class mm_list extends AppCompatActivity {

    ListView memo_list;
    ImageButton btnMBack, btnMAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mm_list);

        memo_list = findViewById(R.id.memo_list);
        btnMBack = findViewById(R.id.btnMBack);
        btnMAdd = findViewById(R.id.btnMAdd);

        btnMBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnMAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mit = new Intent();
            }
        });

    }//onCreate
}//class mm_list
