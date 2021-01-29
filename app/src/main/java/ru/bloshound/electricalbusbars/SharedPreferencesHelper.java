package ru.bloshound.electricalbusbars;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;


public class SharedPreferencesHelper {
    public static final String SHARED_PREF_NAME = "shared_pref_name";
    public static final String BUSBAR_KEY = "busbar_key";
    public static final Type BUSBAR_TYPE =  new TypeToken<Set<Busbar>>(){

    }.getType();


    public static final String CHOSEN_BUSBAR = "chosen_busbar";
    public static final String SET_DENSITY = "set_density";
    public static final String SELECTED_QUNTITY = "selected_quantity";
    public static final String SELECTED_LENGTH = "selected_length";
    public static final String SELECTED_WIDTH = "selected_width";
    public static final String SELECTED_THICKNESS = "selected_thickness";


    private SharedPreferences mSharedPreferences;
    private Gson mGson = new Gson();
    private Context context;


    public SharedPreferencesHelper(Context context) {
        this.context = context;
        this.mSharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
    }


    public Set<Busbar> getSavedBusbars() {
        Set<Busbar> initBusbars = new HashSet<>(){{
            add(new Busbar("copper", 8270,
                    context.getResources().getInteger(R.integer.min_value), 50,
                    6) {
            })
        }}
        Set<Busbar> busbars = mGson.fromJson(mSharedPreferences.getString(BUSBAR_KEY, ""), BUSBAR_TYPE);
        return busbars = null
    }


}
