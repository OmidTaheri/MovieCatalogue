

package ir.satintech.filmbaz.ui.main.favorite;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import ir.satintech.filmbaz.data.DataManager;
import ir.satintech.filmbaz.data.network.model.DetailMovieResponse;
import ir.satintech.filmbaz.ui.base.BasePresenter;
import ir.satintech.filmbaz.utils.rx.SchedulerProvider;




public class FavoritePresenter<V extends FavoriteMvpView> extends BasePresenter<V>
        implements FavoriteMvpPresenter<V> {

    @Inject
    public FavoritePresenter(DataManager dataManager,
                             SchedulerProvider schedulerProvider,
                             CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


    @Override
    public void getFavoriteList() {
        List<DetailMovieResponse> favoriteMovies = getDataManager().getFavoriteMovies();
        getMvpView().setFavoriteList(favoriteMovies);
    }

    @Override
    public void showMovieDetailActivity(int movie_id) {
        getMvpView().showMovieDetailActivity(movie_id);
    }
}
