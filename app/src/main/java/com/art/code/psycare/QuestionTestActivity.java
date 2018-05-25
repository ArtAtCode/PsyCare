package com.art.code.psycare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class QuestionTestActivity extends AppCompatActivity {

    Button uploadbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_test);
        uploadbtn = findViewById(R.id.uploadbutton);
        uploadbtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(QuestionTestActivity.this,ResultActivity.class);
                startActivity(intent);
            }

        });
    }
}
