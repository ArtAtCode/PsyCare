package com.art.code.psycare;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText eNickname;
    private EditText ePhone;
    private EditText eVerifyCode;
    private EditText ePassword;
    private EditText eConfirmPassword;
    private Button getCode;
    private Button register;
    private TextView tBackTologin;

    private int i = 60;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();

        getCode.setOnClickListener(this);
        register.setOnClickListener(this);
        tBackTologin.setOnClickListener(this);
    }

    private void init() {
        eNickname = findViewById(R.id.nickname_register);
        ePhone = findViewById(R.id.phoneNum_register);
        eVerifyCode = findViewById(R.id.verifycode_register);
        ePassword = findViewById(R.id.password_register);
        eConfirmPassword = findViewById(R.id.confirmpassword_reigster);
        getCode = findViewById(R.id.getcode_register);
        register = findViewById(R.id.register_register);
        tBackTologin = findViewById(R.id.backtologin_register);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.getcode_register:
                Toast.makeText(this, "获取验证码成功，接收可能需要一分钟~", Toast.LENGTH_SHORT).show();
                getCode.setClickable(false);
                getCode.setBackgroundColor(Color.parseColor("#c9c9c9"));
                getCode.setText("(" + i + "s)");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (; i > 0; i--) {
                            handler.sendEmptyMessage(-9);
                            if (i <= 0) {
                                break;
                            }
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        handler.sendEmptyMessage(-8);
                    }
                }).start();
                break;
            case R.id.register_register:
                Toast.makeText(this, "恭喜您注册成功！", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.backtologin_register:
                finish();
                break;
            default:
                break;
        }
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @SuppressLint("SetTextI18n")
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case -9:
                    getCode.setText("(" + i + "s)");
                    break;
                case -8:
                    getCode.setText("获取验证码");
                    getCode.setClickable(true);
                    getCode.setBackgroundColor(Color.parseColor("#A7D5EA"));
                    i = 60;
                    break;
                default:
                    break;
            }
        }
    };
}
