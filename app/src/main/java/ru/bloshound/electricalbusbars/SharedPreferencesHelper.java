package ru.bloshound.electricalbusbars;

import android.content.Context;
import android.content.SharedPreferences;


import com.google.gson.Gson;


public class SharedPreferencesHelper {
    public static final String SHARED_PREF_NAME = "shared_pref_name";
    public static final String KEY = "";


    private SharedPreferences mSharedPreferences;
    private Gson mGson = new Gson();


    public SharedPreferencesHelper(Context context) {
        this.mSharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);



    }
}
