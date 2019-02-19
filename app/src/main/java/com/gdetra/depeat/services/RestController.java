package com.gdetra.depeat.services;

import android.content.Context;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gdetra.depeat.models.Login;
import com.gdetra.depeat.models.Register;

import java.util.HashMap;
import java.util.Map;

public class RestController {
    private final static String BASE_URL = "http://138.68.86.70/";
    //private final static String VERSION = "v1/";

    private RequestQueue queue;

    public RestController(Context context){
        queue = Volley.newRequestQueue(context);
    }

    public void getRequest(String endPoint, Response.Listener<String> onSuccess, Response.ErrorListener onError){
        StringRequest request = new StringRequest(
                Request.Method.GET,
                //BASE_URL.concat(VERSION).concat(endPoint),
                BASE_URL.concat(endPoint),
                onSuccess,
                onError
        );
        queue.add(request);
    }

    public void postLoginRequest(final String identifier, final String password, Response.Listener<String> onSuccess, Response.ErrorListener onError){
        StringRequest request = new StringRequest(
            Request.Method.POST,
            BASE_URL.concat(Login.ENDPOINT),
            onSuccess,
            onError
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("identifier", identifier);
                params.put("password", password);
                return params;
            }

        };
        queue.add(request);
    }

    public void postRegisterRequest(final String username, final String password, final String email, Response.Listener<String> onSuccess, Response.ErrorListener onError){
        StringRequest request = new StringRequest(
                Request.Method.POST,
                BASE_URL.concat(Register.ENDPOINT),
                onSuccess,
                onError
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);
                params.put("email", email);
                return params;
            }
        };
        queue.add(request);
    }


}
