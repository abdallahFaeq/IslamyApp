package com.training.islamyapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.training.islamyapp.R;
import com.training.islamyapp.fragments.ahadeth.AhadethFragment;
import com.training.islamyapp.fragments.azkar.AzkarFragment;
import com.training.islamyapp.fragments.PrayerTimesFragment;
import com.training.islamyapp.fragments.RadioFragment;
import com.training.islamyapp.fragments.quran.SurahFragment;

public class MainActivity extends AppCompatActivity {
    // declaration
    private BottomNavigationView bottomNav;
    private static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialization
        bottomNav = findViewById(R.id.bottom_navigation);
        fragmentManager = getSupportFragmentManager();

        bottomNav.setSelectedItemId(R.id.menu_quran);
        pushFragment(new SurahFragment());

        setupNavigation();
    }

    public static FragmentManager getMyFragmentManager(){
        return fragmentManager;
    }

    String tag = "";
    public void setupNavigation(){
        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.menu_quran) {
                    // push quran fragment
                    pushFragment(new SurahFragment());
                    tag = SurahFragment.class.getSimpleName();
                    return true;
                } else if (itemId == R.id.menu_ahadeth) {
                    pushFragment(new AhadethFragment());
                    tag = AhadethFragment.class.getSimpleName();
                    return true;
                } else if (itemId == R.id.menu_azkar) {
                    pushFragment(new AzkarFragment());
                    tag = AzkarFragment.class.getSimpleName();
                    return true;
                } else if (itemId == R.id.menu_prayers) {
                    pushFragment(new PrayerTimesFragment());
                    tag = PrayerTimesFragment.class.getSimpleName();
                    return true;
                } else if (itemId == R.id.menu_radio) {
                    pushFragment(new RadioFragment());
                    tag = RadioFragment.class.getSimpleName();
                    return true;
                }
                return false;
            }
        });
    }

    public void pushFragment(Fragment fragment){
        fragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackPressed() {
        // Get the current fragment
        Fragment currentFragment = fragmentManager.findFragmentById(R.id.fragment_container);

        // Check if the current fragment is the one you want to go back to
        if (currentFragment instanceof SurahFragment) {
            // Go back to the HomeFragment
            super.onBackPressed();
            finish();
        } else {
            bottomNav.setSelectedItemId(R.id.menu_quran);
            pushFragment(new SurahFragment());
        }
    }
}