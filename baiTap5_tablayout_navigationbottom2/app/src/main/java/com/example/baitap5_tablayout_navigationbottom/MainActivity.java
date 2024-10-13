package com.example.baitap5_tablayout_navigationbottom;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.example.baitap5_tablayout_navigationbottom.R;


import com.example.baitap5_tablayout_navigationbottom.fragment.fr_profile;
import com.example.baitap5_tablayout_navigationbottom.fragment.home;
import com.example.baitap5_tablayout_navigationbottom.fragment.setting_fragment;
import com.example.baitap5_tablayout_navigationbottom.fragment.viewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private BottomNavigationView bottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        viewPager = findViewById(R.id.viewpager);
        bottomNavigation = findViewById(R.id.navigationBottomview);
        viewPagerAdapter adapter = new viewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        bottomNavigation.getMenu().findItem(R.id.menu_home).setChecked(true);
                        break;
                    case 1:
                        bottomNavigation.getMenu().findItem(R.id.menu_contact).setChecked(true);
                        break;
                    case 2:
                        bottomNavigation.getMenu().findItem(R.id.menu_setting).setChecked(true);
                        break;
                    default:
                        bottomNavigation.getMenu().findItem(R.id.menu_home).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.menu_home) {
                    viewPager.setCurrentItem(0);
                    return true;
                } else if (itemId == R.id.menu_contact) {
                    viewPager.setCurrentItem(1);
                    return true;
                } else if (itemId == R.id.menu_setting) {
                    viewPager.setCurrentItem(2);
                    return true;
                }

                return false;
            }
        });




    }
}