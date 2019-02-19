package com.gdetra.depeat.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends Auth{
    public final static String ENDPOINT = "auth/local";
    private LoginUser loginUser;

    public Login(JSONObject jsonObject) throws JSONException{
        super(jsonObject);
        this.loginUser = new LoginUser(jsonObject.getJSONObject("user"));
    }

    public LoginUser getLoginUser() {
        return loginUser;
    }
}
