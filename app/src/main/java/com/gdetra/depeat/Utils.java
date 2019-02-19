package com.gdetra.depeat;

import android.telephony.PhoneNumberUtils;

public class Utils {
    public static final int MAX_LENGTH_PASSWORD = 6;
    public static final String PACKAGE_NAME = "com.gdetra.depeat";
    public static final String IS_GRID_LAYOUT_SELECTED = "isGridLayoutSelectedKey";


    public static boolean isValidEmail(String email){
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isValidPassword(String password){
        return password.length() > MAX_LENGTH_PASSWORD;
    }

    public static boolean isValidPhoneNumber(String phoneNumber){
        return PhoneNumberUtils.isGlobalPhoneNumber(phoneNumber);
    }

    public static boolean isValidUsername(String username){
        return username.length() > 5;
    }
}
