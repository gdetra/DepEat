package com.gdetra.depeat.ui.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.gdetra.depeat.services.RestController;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, Response.Listener<String>, Response.ErrorListener {
    Button loginBtn;
    Button registerBtn;
    EditText emailEt;
    EditText passwordEt;
    RestController controller;
    Login loginAuth;
    SharedPreferences prefs;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Attach activity_login.xml with this activity
        setContentView(R.layout.activity_login);

        prefs = getSharedPreferences(Utils.PACKAGE_NAME, Context.MODE_PRIVATE);

        //Initialize components
        loginBtn = findViewById(R.id.login_btn);
        registerBtn = findViewById(R.id.register_btn);
        emailEt = findViewById(R.id.email_et);
        passwordEt = findViewById(R.id.password_et);

        //Setted onClickListener on Login and Register Button
        loginBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);

        controller = new RestController(this);
    }

    private void showToast(@StringRes int resId){
        Toast.makeText(this, getString(resId), Toast.LENGTH_LONG).show();
    }


    private void doLogin(){

        String email = emailEt.getText().toString();
        String password = passwordEt.getText().toString();

        Log.i("password", password);

        if(!Utils.isValidEmail(email)){
            showToast(R.string.invalid_email);
            return;
        }

        if(!Utils.isValidPassword(password)){
            showToast(R.string.invalid_password);
            return;
        }

        controller.postLoginRequest(email, password, this, this);

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

    @Override
    public void onErrorResponse(VolleyError error) {

        Log.e(LoginActivity.class.toString(), error.getMessage());
        Toast.makeText(this, error.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            loginAuth = new Login(jsonObject);
            Intent intent = new Intent();
            intent.putExtra(Auth.AUTH, loginAuth.getJwt());
            setResult(Activity.RESULT_OK, intent);
            finish();
            /*prefs.edit().putString(Auth.AUTH, loginAuth.getJwt()).apply();
            startActivity(new Intent(this,MainActivity.class));*/
        } catch (JSONException e) {
            Log.i("error", e.getMessage());
            Log.e(LoginActivity.class.toString(), e.getMessage());
        }
    }
}
