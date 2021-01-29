package ru.bloshound.electricalbusbars;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class SharedPreferencesHelper {
    public static final String SHARED_PREF_NAME = "shared_pref_name";
    public static final String BUSBAR_KEY = "busbar_key";
    public static final Type BUSBAR_TYPE = new TypeToken<Set<Busbar>>() {

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
        Set<Busbar> initBusbars = new HashSet<Busbar>() {{
            Resources resources = context.getResources();
            add(new Busbar("Copper", 8930,
                    resources.getInteger(R.integer.default_length), resources.getInteger(R.integer.default_width),
                    resources.getInteger(R.integer.default_thickness)) {
            });

            add(new Busbar("Aluminium", 2710,
                    resources.getInteger(R.integer.default_length), resources.getInteger(R.integer.default_width),
                    resources.getInteger(R.integer.default_thickness)) {
            });

        }};
        Set<Busbar> busbars = mGson.fromJson(mSharedPreferences.getString(BUSBAR_KEY, ""), BUSBAR_TYPE);
        return busbars == null ? initBusbars : busbars;
    }


    public String[] getMaterials() {
        List<String> listOfMaterials = new ArrayList<>();
        for (Busbar b: getSavedBusbars()) {
            listOfMaterials.add(b.getMaterial());
        }
        return listOfMaterials.toArray(new String[0]);

    }


}
