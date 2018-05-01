package com.art.code.psycare;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextPaint;
import android.util.TypedValue;
import android.widget.TextView;

public class ReadSentenceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null) actionBar.hide();
        TextView textView = findViewById(R.id.sentence_read_text);
        setContentView(R.layout.activity_read_sentence);
    }



}
