
package ir.satintech.filmbaz.ui.main.favorite;


import ir.satintech.filmbaz.ui.base.MvpPresenter;




public interface FavoriteMvpPresenter<V extends FavoriteMvpView>
        extends MvpPresenter<V> {

    void getFavoriteList();

    void showMovieDetailActivity(int movie_id);
}


