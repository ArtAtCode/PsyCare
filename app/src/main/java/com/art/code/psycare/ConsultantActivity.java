package com.art.code.psycare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class ConsultantActivity extends AppCompatActivity {

    List<Consultant> consultants;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultant);

        back = findViewById(R.id.back_consultant);

        consultants= new ArrayList<>();
        initConsultant();
        ConsultantAdapter adapter = new ConsultantAdapter(consultants, this);
        RecyclerView recyclerView = findViewById(R.id.recyclerview_consultant);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initConsultant() {
        Consultant consultant0 = new Consultant("Magna De", "国家一级咨询师", "￥10/分钟", 5);
        consultants.add(consultant0);
        Consultant consultant1 = new Consultant("Tuppence Middleton", "国家一级咨询师", "￥10/分钟", 5);
        consultants.add(consultant1);
        Consultant consultant2 = new Consultant("Aml Ameen", "国家二级咨询师", "￥7/分钟", 5);
        consultants.add(consultant2);
        Consultant consultant3 = new Consultant("Brian Jacob Smith", "国家二级咨询师", "￥5/分钟", 4);
        consultants.add(consultant3);
        Consultant consultant4 = new Consultant("Lana Wachowski", "国家二级咨询师", "￥5/分钟", 4);
        consultants.add(consultant4);
        Consultant consultant5 = new Consultant("James McTeigue", "国家二级咨询师", "￥5/分钟", 4);
        consultants.add(consultant5);
    }
}
