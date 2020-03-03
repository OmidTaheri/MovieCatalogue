

package ir.satintech.filmbaz.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import ir.satintech.filmbaz.di.ActivityContext;
import ir.satintech.filmbaz.di.PerActivity;
import ir.satintech.filmbaz.ui.aboutus.AboutUsMvpPresenter;
import ir.satintech.filmbaz.ui.aboutus.AboutUsMvpView;
import ir.satintech.filmbaz.ui.aboutus.AboutUsPresenter;
import ir.satintech.filmbaz.ui.detailmovie.DetailMovieMvpPresenter;
import ir.satintech.filmbaz.ui.detailmovie.DetailMovieMvpView;
import ir.satintech.filmbaz.ui.detailmovie.DetailMoviePresenter;
import ir.satintech.filmbaz.ui.detailmovie.info.IntroMvpPresenter;
import ir.satintech.filmbaz.ui.detailmovie.info.IntroMvpView;
import ir.satintech.filmbaz.ui.detailmovie.info.IntroPresenter;
import ir.satintech.filmbaz.ui.detailmovie.summary.SummaryMvpPresenter;
import ir.satintech.filmbaz.ui.detailmovie.summary.SummaryMvpView;
import ir.satintech.filmbaz.ui.detailmovie.summary.SummaryPresenter;
import ir.satintech.filmbaz.ui.detailmovie.gallery.GalleryMvpPresenter;
import ir.satintech.filmbaz.ui.detailmovie.gallery.GalleryMvpView;
import ir.satintech.filmbaz.ui.detailmovie.gallery.GalleryPresenter;
import ir.satintech.filmbaz.ui.genredmovies.GenredMoviesMvpPresenter;
import ir.satintech.filmbaz.ui.genredmovies.GenredMoviesMvpView;
import ir.satintech.filmbaz.ui.genredmovies.GenredMoviesPresenter;
import ir.satintech.filmbaz.ui.main.MainMvpPresenter;
import ir.satintech.filmbaz.ui.main.MainMvpView;
import ir.satintech.filmbaz.ui.main.MainPresenter;
import ir.satintech.filmbaz.ui.main.VPagerMainAdapter;
import ir.satintech.filmbaz.ui.main.category.CategoryMvpPresenter;
import ir.satintech.filmbaz.ui.main.category.CategoryMvpView;
import ir.satintech.filmbaz.ui.main.category.CategoryPresenter;
import ir.satintech.filmbaz.ui.main.favorite.FavoriteMvpPresenter;
import ir.satintech.filmbaz.ui.main.favorite.FavoriteMvpView;
import ir.satintech.filmbaz.ui.main.favorite.FavoritePresenter;
import ir.satintech.filmbaz.ui.main.vitrin.VitrinMvpPresenter;
import ir.satintech.filmbaz.ui.main.vitrin.VitrinMvpView;
import ir.satintech.filmbaz.ui.main.vitrin.VitrinPresenter;
import ir.satintech.filmbaz.ui.search.SearchMvpPresenter;
import ir.satintech.filmbaz.ui.search.SearchMvpView;
import ir.satintech.filmbaz.ui.search.SearchPresenter;
import ir.satintech.filmbaz.ui.splash.SplashMvpPresenter;
import ir.satintech.filmbaz.ui.splash.SplashMvpView;
import ir.satintech.filmbaz.ui.splash.SplashPresenter;
import ir.satintech.filmbaz.utils.rx.AppSchedulerProvider;
import ir.satintech.filmbaz.utils.rx.SchedulerProvider;

@Module
public class ActivityModule {

    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @PerActivity
    SplashMvpPresenter<SplashMvpView> provideSplashPresenter(
            SplashPresenter<SplashMvpView> presenter) {
        return presenter;
    }


    @Provides
    @PerActivity
    MainMvpPresenter<MainMvpView> provideMainPresenter(
            MainPresenter<MainMvpView> presenter) {
        return presenter;
    }


    @Provides
    DetailMovieMvpPresenter<DetailMovieMvpView> provideDetailMoviePresenter(
            DetailMoviePresenter<DetailMovieMvpView> presenter) {
        return presenter;
    }


    @Provides
    VPagerMainAdapter provideMVPagerAdapter() {
        return new VPagerMainAdapter(mActivity.getSupportFragmentManager());
    }


    @Provides
    LinearLayoutManager provideLinearLayoutManager(AppCompatActivity activity) {
        return new LinearLayoutManager(activity);
    }


    @Provides
    @PerActivity
    AboutUsMvpPresenter<AboutUsMvpView> provideAboutUsPresenter(
            AboutUsPresenter<AboutUsMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    FavoriteMvpPresenter<FavoriteMvpView> provideFavoritePresenter(
            FavoritePresenter<FavoriteMvpView> presenter) {
        return presenter;
    }


    @Provides
    @PerActivity
    VitrinMvpPresenter<VitrinMvpView> provideVitrinPresenter(
            VitrinPresenter<VitrinMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    SearchMvpPresenter<SearchMvpView> provideSearchPresenter(
            SearchPresenter<SearchMvpView> presenter) {
        return presenter;
    }


    @Provides
    @PerActivity
    CategoryMvpPresenter<CategoryMvpView> provideCategoryPresenter(
            CategoryPresenter<CategoryMvpView> presenter) {
        return presenter;
    }


    @Provides
    @PerActivity
    IntroMvpPresenter<IntroMvpView> provideIntroPresenter(
            IntroPresenter<IntroMvpView> presenter) {
        return presenter;
    }


    @Provides
    @PerActivity
    SummaryMvpPresenter<SummaryMvpView> provideDescriptionPresenter(
            SummaryPresenter<SummaryMvpView> presenter) {
        return presenter;
    }




    @Provides
    @PerActivity
    GenredMoviesMvpPresenter<GenredMoviesMvpView> provideGenredMoviesPresenter(
            GenredMoviesPresenter<GenredMoviesMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    GalleryMvpPresenter<GalleryMvpView> provideGalleryPresenter(
            GalleryPresenter<GalleryMvpView> presenter) {
        return presenter;
    }
}
