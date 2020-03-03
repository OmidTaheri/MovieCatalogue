
package ir.satintech.filmbaz.ui.genredmovies;


import ir.satintech.filmbaz.di.PerActivity;
import ir.satintech.filmbaz.ui.base.MvpPresenter;



@PerActivity
public interface GenredMoviesMvpPresenter<V extends GenredMoviesMvpView> extends MvpPresenter<V> {

    void loadFirstPage( int genre_id);

    void loadNextPage(int page, int genre_id);


}
