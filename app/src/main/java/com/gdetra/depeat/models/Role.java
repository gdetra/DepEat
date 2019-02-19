package com.gdetra.depeat.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Role {
    private String id;
    private String name;
    private String description;
    private String type;
    private int v;

    public Role(JSONObject jsonObject) throws JSONException {
        this.id = jsonObject.getString("_id");
        this.name = jsonObject.getString("name");
        this.description = jsonObject.getString("description");
        this.type = jsonObject.getString("type");
        this.v = jsonObject.getInt("__v");
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public int getV() {
        return v;
    }
}
