package com.gdetra.depeat.models;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginUser extends User {
    private Role role;
    public LoginUser(JSONObject jsonObject) throws JSONException {
        super(jsonObject);
        this.role = new Role(jsonObject.getJSONObject("role"));
    }

    public Role getRole() {
        return role;
    }
}