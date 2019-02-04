package com.gdetra.depeat.ui.activities;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gdetra.depeat.R;
import com.gdetra.depeat.Utils;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    Button loginBtn;
    Button registerBtn;
    EditText emailEt;
    EditText passwordEt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Attach activity_login.xml with this activity
        setContentView(R.layout.activity_login);

        //Initialize components
        loginBtn = findViewById(R.id.login_btn);
        registerBtn = findViewById(R.id.register_btn);
        emailEt = findViewById(R.id.email_et);
        passwordEt = findViewById(R.id.password_et);

        //Setted onClickListener on Login and Register Button
        loginBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);
    }

    private void showToast(@StringRes int resId){
        Toast.makeText(this, getString(resId), Toast.LENGTH_LONG).show();
    }


    private void doLogin(){

        String email = emailEt.getText().toString();
        String password = passwordEt.getText().toString();

        if(!Utils.isValidEmail(email)){
            showToast(R.string.invalid_email);
            return;
        }

        if(Utils.isValidPassword(password)){
            showToast(R.string.invalid_password);
            return;
        }

        showToast(R.string.login_succeded);

    }

    private void doRegister(){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.login_btn){
            doLogin();
        }else if(v.getId() == R.id.register_btn){
            doRegister();
        }
    }
}
