
package ir.satintech.filmbaz.ui.main;


import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ir.satintech.filmbaz.ui.main.category.CategoryFragment;
import ir.satintech.filmbaz.ui.main.favorite.FavoriteFragment;
import ir.satintech.filmbaz.ui.main.vitrin.VitrinFragment;



public class VPagerMainAdapter extends FragmentPagerAdapter {

    public final int PAGE_COUNT = 3;


    public VPagerMainAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return FavoriteFragment.newInstance();

            case 1:

                return CategoryFragment.newInstance();

            case 2:
                return VitrinFragment.newInstance();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "علاقه مندی ها";

            case 1:
                return "ژانرها";


            case 2:
                return "ویترین";

            default:
                return "";
        }
    }

//    @Override
//    public int getItemPosition(@NonNull Object object) {
//
//        ////recreate  fragment  by call notify dataset change
//        return POSITION_NONE;
//    }
}
