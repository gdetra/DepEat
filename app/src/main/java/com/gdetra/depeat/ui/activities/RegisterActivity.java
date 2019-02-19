package com.gdetra.depeat.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.gdetra.depeat.R;
import com.gdetra.depeat.Utils;
import com.gdetra.depeat.models.Auth;
import com.gdetra.depeat.models.Login;
import com.gdetra.depeat.models.Register;
import com.gdetra.depeat.models.RegisterUser;
import com.gdetra.depeat.services.RestController;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, Response.Listener<String>, Response.ErrorListener {
    EditText emailReg_et;
    EditText passwordReg_et;
    EditText phoneReg_et;
    Button signIn_btn;
    boolean isValidEmail;
    boolean isValidPassword;
    boolean isValidPhoneNumber;
    RestController controller;
    Register registerAuth;
    SharedPreferences prefs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        prefs = getSharedPreferences(Utils.PACKAGE_NAME, Context.MODE_PRIVATE);
        controller = new RestController(this);
        emailReg_et = findViewById(R.id.email_reg_et);
        passwordReg_et = findViewById(R.id.passowrd_reg_et);
        phoneReg_et = findViewById(R.id.phone_reg_et);
        signIn_btn = findViewById(R.id.sign_in_reg_btn);
        signIn_btn.setOnClickListener(this);

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
                isValidPhoneNumber = Utils.isValidUsername(s.toString());
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

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.sign_in_reg_btn){
            doSignIn();
        }
    }

    private void doSignIn(){
        String email = emailReg_et.getText().toString();
        String password = passwordReg_et.getText().toString();
        String username = phoneReg_et.getText().toString();

        controller.postRegisterRequest(username, password, email, this, this);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e(RegisterActivity.class.toString(), error.getMessage());
        Toast.makeText(this, error.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            registerAuth = new Register(jsonObject);
            /*prefs.edit().putString(Auth.AUTH, registerAuth.getJwt()).apply();*/
            startActivity(new Intent(this, MainActivity.class));
        } catch (JSONException e) {
            Log.e(LoginActivity.class.toString(), e.getMessage());
        }
    }
}
