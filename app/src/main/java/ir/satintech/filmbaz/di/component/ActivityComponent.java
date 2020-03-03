
package ir.satintech.filmbaz.di.component;

import dagger.Component;
import ir.satintech.filmbaz.di.PerActivity;
import ir.satintech.filmbaz.di.module.ActivityModule;
import ir.satintech.filmbaz.ui.aboutus.AboutUsActivity;
import ir.satintech.filmbaz.ui.detailmovie.DetailMovieActivity;
import ir.satintech.filmbaz.ui.detailmovie.info.IntroFragment;
import ir.satintech.filmbaz.ui.detailmovie.summary.SummaryFragment;
import ir.satintech.filmbaz.ui.detailmovie.gallery.GalleryFragment;
import ir.satintech.filmbaz.ui.genredmovies.GenredMoviesActivity;
import ir.satintech.filmbaz.ui.main.MainActivity;
import ir.satintech.filmbaz.ui.main.category.CategoryFragment;
import ir.satintech.filmbaz.ui.main.favorite.FavoriteFragment;
import ir.satintech.filmbaz.ui.main.vitrin.VitrinFragment;
import ir.satintech.filmbaz.ui.search.SearchActivity;
import ir.satintech.filmbaz.ui.splash.SplashActivity;


@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity activity);

    void inject(SplashActivity activity);

    void inject(DetailMovieActivity activity);

    void inject(AboutUsActivity activity);

    void inject(SearchActivity activity);

    void inject(GenredMoviesActivity activity);

    void inject(CategoryFragment fragment);

    void inject(FavoriteFragment fragment);

    void inject(VitrinFragment fragment);

    void inject(IntroFragment fragment);

    void inject(SummaryFragment fragment);

    void inject(GalleryFragment fragment);


}
