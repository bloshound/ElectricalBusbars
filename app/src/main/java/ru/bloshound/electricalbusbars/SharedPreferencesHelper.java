package ru.bloshound.electricalbusbars;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.ArrayAdapter;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class SharedPreferencesHelper {
    public static final String SHARED_PREF_NAME = "shared_pref_name";
    public static final String KEY = "";


    private SharedPreferences mSharedPreferences;
    private Gson mGson = new Gson();


    public SharedPreferencesHelper(Context context) {
        this.mSharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        ArrayList arrayList = new ArrayList();

    }
}
