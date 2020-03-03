
package ir.satintech.filmbaz.ui.detailmovie;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ir.satintech.filmbaz.data.network.model.DetailMovieResponse;
import ir.satintech.filmbaz.ui.detailmovie.summary.SummaryFragment;
import ir.satintech.filmbaz.ui.detailmovie.gallery.GalleryFragment;
import ir.satintech.filmbaz.ui.detailmovie.info.IntroFragment;



public class VPagerDetailAdapter extends FragmentPagerAdapter {

    public final int PAGE_COUNT = 3;

    DetailMovieResponse detailMovie;

    public VPagerDetailAdapter(FragmentManager fm, DetailMovieResponse detailMovie) {
        super(fm);

        this.detailMovie = detailMovie;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return GalleryFragment.newInstance(detailMovie.getImages());


            case 1:
                return SummaryFragment.newInstance(detailMovie.getPlot());

            case 2:
                return IntroFragment.newInstance(detailMovie.getDirector(),
                        detailMovie.getWriter(),
                        detailMovie.getYear(),
                        detailMovie.getCountry(),
                        detailMovie.getActors(),
                        detailMovie.getAwards());

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
                return "عکس ها";

            case 1:
                return "خلاصه داستان";


            case 2:
                return "توضیحات";

            default:
                return null;
        }
    }

}
