package profile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.baitap5_tablayout_navigationbottom.fragment.fr_profile;
import com.example.baitap5_tablayout_navigationbottom.fragment.home;
import com.example.baitap5_tablayout_navigationbottom.fragment.setting_fragment;

public class ProfileviewPagerAdapter extends FragmentStatePagerAdapter {
    public ProfileviewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new tab1_profile();
            case 1:
                return new tab2_profile();
            case 2:
                return new tab3_profile();
            default:
                return new tab1_profile();
        }
    }


    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Info";
            case 1:
                return "Contact";
            case 2:
                return "Skill";
            default:
                return "Info";
        }
    }
}
