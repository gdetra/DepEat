package com.gdetra.depeat.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Auth {
    public static final String AUTH = "jwt";
    private String jwt;

    public Auth(JSONObject jsonObject) throws JSONException{
        this.jwt = jsonObject.getString("jwt");
    }

    public String getJwt() {
        return jwt;
    }
}
