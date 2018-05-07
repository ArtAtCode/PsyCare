package com.art.code.psycare;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
                ){
            ActivityCompat.requestPermissions(ReadSentenceActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);

        }
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(ReadSentenceActivity.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},2);
        }
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(ReadSentenceActivity.this,
                    new String[]{Manifest.permission.RECORD_AUDIO},3);
        }
        setContentView(R.layout.activity_read_sentence);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        switch(requestCode){
            case 1:
                if(grantResults.length>0&&grantResults[0]!=PackageManager.PERMISSION_GRANTED){
                    this.finish();
                    break;
                }
            case 2:
                if(grantResults.length>0&&grantResults[0]!=PackageManager.PERMISSION_GRANTED){
                    this.finish();
                    break;
                }
            case 3:
                if(grantResults.length>0&&grantResults[0]!=PackageManager.PERMISSION_GRANTED){
                this.finish();
                break;
            }
        }
    }
}
