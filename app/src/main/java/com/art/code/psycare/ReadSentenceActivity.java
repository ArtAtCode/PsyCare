package com.art.code.psycare;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ReadSentenceActivity extends AppCompatActivity {

    private static final int PERMISSION_OF_RECORD_AUDIO = 1;

    private String[] mPermissions = new String[] {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.RECORD_AUDIO
    };

    List<String> mPermissionList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        mPermissionList.clear();
        for (String mPermission : mPermissions) {
            if (ContextCompat.checkSelfPermission(this, mPermission) != PackageManager.PERMISSION_GRANTED)
                mPermissionList.add(mPermission);
        }
        if (!mPermissionList.isEmpty()) {
            String[] permissions = mPermissionList.toArray(new String[mPermissionList.size()]);
            ActivityCompat.requestPermissions(this, permissions, PERMISSION_OF_RECORD_AUDIO);
        }

        /*
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
        */
        setContentView(R.layout.activity_read_sentence);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        switch(requestCode){
            case PERMISSION_OF_RECORD_AUDIO:
                if(grantResults.length>0&&grantResults[0]!=PackageManager.PERMISSION_GRANTED){
                    this.finish();
                    break;
                }
                /*
            case 2:
                if(grantResults.length>0&&grantResults[0]!=PackageManager.PERMISSION_GRANTED){
                    this.finish();
                    break;
                }
            case 3:
                if(grantResults.length>0&&grantResults[0]!=PackageManager.PERMISSION_GRANTED){
                this.finish();
                break;
                */
            default:
               break;
        }
    }
}
