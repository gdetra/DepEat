package com.gdetra.depeat.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Register extends Auth {
    public final static String ENDPOINT = "auth/local/register";
    private RegisterUser registerUser;

    public Register(JSONObject jsonObject) throws JSONException{
        super(jsonObject);
        this.registerUser = new RegisterUser(jsonObject.getJSONObject("user"));
    }

    public RegisterUser getRegisterUser() {
        return registerUser;
    }
}
