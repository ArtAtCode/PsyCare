package com.art.code.psycare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.art.code.psycare.Compent.User;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText ePhone;
    private EditText ePasswd;
    private Button login;
    private TextView tRegister;
    private TextView tForgetpasswd;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

        login.setOnClickListener(this);
        tRegister.setOnClickListener(this);
        tForgetpasswd.setOnClickListener(this);
    }

    private void init() {
        ePhone = findViewById(R.id.phoneNum_login);
        ePasswd = findViewById(R.id.password_login);
        login = findViewById(R.id.login_login);
        tRegister = findViewById(R.id.register_login);
        tForgetpasswd = findViewById(R.id.forgetpassword_login);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_login:
                Intent login = new Intent(this, MainActivity.class);
                login.putExtra("user", user);
                startActivity(login);
                finish();
                break;
            case R.id.register_login:
                Intent register = new Intent(this, RegisterActivity.class);
                startActivity(register);
                break;
            case R.id.forgetpassword_login:
                Intent forgetpasswd = new Intent(this, ForgetpasswordActivity.class);
                startActivity(forgetpasswd);
                break;

            default:
                break;
        }
    }
}
