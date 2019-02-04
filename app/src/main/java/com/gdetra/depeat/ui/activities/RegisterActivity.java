package com.gdetra.depeat.ui.activities;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import com.gdetra.depeat.R;
import com.gdetra.depeat.Utils;

public class RegisterActivity extends AppCompatActivity {
    EditText emailReg_et;
    EditText passwordReg_et;
    EditText phoneReg_et;
    Button signIn_btn;
    boolean isValidEmail;
    boolean isValidPassword;
    boolean isValidPhoneNumber;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        emailReg_et = findViewById(R.id.email_reg_et);
        passwordReg_et = findViewById(R.id.passowrd_reg_et);
        phoneReg_et = findViewById(R.id.phone_reg_et);
        signIn_btn = findViewById(R.id.sign_in_reg_btn);


        emailReg_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isValidEmail = Utils.isValidEmail(s.toString());
                tryEnableSignInButton();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        passwordReg_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isValidPassword = Utils.isValidPassword(s.toString());
                tryEnableSignInButton();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        phoneReg_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isValidPhoneNumber = Utils.isValidPhoneNumber(s.toString());
                tryEnableSignInButton();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    private void tryEnableSignInButton(){
        if(isValidEmail && isValidPassword && isValidPhoneNumber){
            signIn_btn.setEnabled(true);
        }else{
            signIn_btn.setEnabled(false);
        }
    }
}
