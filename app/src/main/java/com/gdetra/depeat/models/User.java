package com.gdetra.depeat.models;

import org.json.JSONException;
import org.json.JSONObject;

public class User {
    private String id;
    private String username;
    private String email;
    private boolean confirmed;
    private boolean blocked;
    private String provider;
    private int v;

    public User(JSONObject jsonObject) throws JSONException{
        this.id = jsonObject.getString("_id");
        this.username = jsonObject.getString("username");
        this.email = jsonObject.getString("email");
        this.confirmed = jsonObject.getBoolean("confirmed");
        this.blocked = jsonObject.getBoolean("blocked");
        this.provider = jsonObject.getString("provider");
        this.v = jsonObject.getInt("__v");
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public String getProvider() {
        return provider;
    }

    public int getV() {
        return v;
    }


}
