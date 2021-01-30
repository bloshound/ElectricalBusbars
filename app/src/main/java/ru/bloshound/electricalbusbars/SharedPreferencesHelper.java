package ru.bloshound.electricalbusbars;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SharedPreferencesHelper {
    public static final String SHARED_PREF_NAME = "shared_pref_name";
    public static final String BUSBAR_KEY = "busbar_key";
    public static final Type BUSBAR_TYPE = new TypeToken<Map<String, Busbar>>() {

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


    public HashMap<String, Busbar> getSavedBusbars() {
        HashMap<String, Busbar> initBusbars = new HashMap<>();
        Busbar copperBusbar = new Busbar("copper", 8970, context.getResources().getInteger(R.integer.default_length),
                context.getResources().getInteger(R.integer.default_width),
                context.getResources().getInteger(R.integer.default_thickness)) {
        };

        Busbar aluminiumBusbar = new Busbar("aluminium", 2710, context.getResources().getInteger(R.integer.default_length),
                context.getResources().getInteger(R.integer.default_width),
                context.getResources().getInteger(R.integer.default_thickness)) {
        };

        initBusbars.put(copperBusbar.getMaterial(), copperBusbar);
        initBusbars.put(aluminiumBusbar.getMaterial(), aluminiumBusbar);

        HashMap<String, Busbar> busbars = mGson.fromJson(mSharedPreferences.getString(BUSBAR_KEY, ""), BUSBAR_TYPE);
        return busbars == null ? initBusbars : busbars;
    }


    public String[] getMaterials() {
        List<String> listOfMaterials = new ArrayList<>();

        for (Map.Entry<String, Busbar> pair : getSavedBusbars().entrySet()) {
            listOfMaterials.add(pair.getKey());
        }

        return listOfMaterials.toArray(new String[0]);

    }


}
