package com.gdetra.depeat;

import android.telephony.PhoneNumberUtils;

public class Utils {
    public static final int MAX_LENGTH_PASSWORD = 6;


    public static boolean isValidEmail(String email){
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isValidPassword(String password){
        return password.length() > MAX_LENGTH_PASSWORD;
    }

    public static boolean isValidPhoneNumber(String phoneNumber){
        return PhoneNumberUtils.isGlobalPhoneNumber(phoneNumber);
    }
}
