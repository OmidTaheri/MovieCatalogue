

package ir.satintech.filmbaz.ui.detailmovie;


import ir.satintech.filmbaz.data.network.model.DetailMovieResponse;
import ir.satintech.filmbaz.di.PerActivity;
import ir.satintech.filmbaz.ui.base.MvpPresenter;



@PerActivity
public interface DetailMovieMvpPresenter<V extends DetailMovieMvpView> extends MvpPresenter<V> {

    void getDetailMovie(int movie_id);



    void addToFavorite(DetailMovieResponse item);

    void deleteFromFavorite(int movie_id);

    boolean existInFavorite(int item);

}
