package com.art.code.psycare;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ReadSentenceActivity extends AppCompatActivity {

    private static final int PERMISSION_OF_RECORD_AUDIO = 1;
    private TextView nextBt ;
    private ImageView back;

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

        setContentView(R.layout.activity_read_sentence);
        nextBt=findViewById(R.id.nextbut);
        nextBt.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(ReadSentenceActivity.this,QuestionTestActivity.class);
                startActivity(intent);
                finish();
            }
        });

        back = findViewById(R.id.back_readsentence);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        switch(requestCode){
            case PERMISSION_OF_RECORD_AUDIO:
                if(grantResults.length>0&&grantResults[0]!=PackageManager.PERMISSION_GRANTED){
                    this.finish();
                    break;
                }
            default:
               break;
        }
    }
}
