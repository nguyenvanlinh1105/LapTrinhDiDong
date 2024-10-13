package com.example.baitap5_tablayout_navigationbottom.fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class viewPagerAdapter extends FragmentStatePagerAdapter {

    public viewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new home();
            case 1:
                return new fr_profile();
            case 2:
                return new setting_fragment();
            default:
                return new home();
        }
    }


    @Override
    public int getCount() {
        return 3;
    }
}
