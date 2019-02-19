package com.gdetra.depeat.models;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterUser extends User {
    private String role;

    public RegisterUser(JSONObject jsonObject) throws JSONException {
        super(jsonObject);
        this.role = jsonObject.getString("role");
    }

    public String getRole() {
        return role;
    }
}