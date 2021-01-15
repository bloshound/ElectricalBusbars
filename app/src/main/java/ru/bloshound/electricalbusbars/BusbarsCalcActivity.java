package ru.bloshound.electricalbusbars;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import ru.bloshound.electricalbusbars.ui.main.SectionsPagerAdapter;

public class BusbarsCalcActivity extends AppCompatActivity {

    private static final String APP_PREFERENCES = "app_preferences";
    private static final String QUANTITY_BAR = "quantity_bar";
    private static final String LENGHT_BAR = "lenght_bar";

    BusbarsCalcViewModel mMainViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());

        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);


        SharedPreferences mySharedPreferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        int quantity = mySharedPreferences.getInt(QUANTITY_BAR, 1);
        int lenght = mySharedPreferences.getInt(LENGHT_BAR, 0);

        mySharedPreferences.edit().apply();
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
    }

}