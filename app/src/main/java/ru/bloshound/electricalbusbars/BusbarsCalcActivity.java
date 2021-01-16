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

    SharedPreferences mPreferences;
    BusbarsCalcViewModel mViewModel;

    private static final String PREFERENCES = "preferences";
    private static final String QUANTITY_BUSBAR = "quantity_busbar";
    private static final String LENGTH_BUSBAR = "length_busbar";
    private static final String WIDTH_BUSBAR = "width_busbar";
    private static final String THICKNESS_BUSBAR = "thickness_busbar";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());

        ViewPager viewPager = findViewById(R.id.view_pager);

        viewPager.setAdapter(sectionsPagerAdapter);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        mPreferences = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE); // настройки приложения

        int quantity = mPreferences.getInt(QUANTITY_BUSBAR, 1); // колличество шин
        int lenght = mPreferences.getInt(LENGTH_BUSBAR, 1); // длина шины
        int width = mPreferences.getInt(WIDTH_BUSBAR, 50); // ширина шины  шины
        int thickness = mPreferences.getInt(THICKNESS_BUSBAR, 6); //толшина шины



        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
    }
}