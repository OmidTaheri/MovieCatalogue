

package ir.satintech.filmbaz.ui.detailmovie;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import ir.satintech.filmbaz.data.DataManager;
import ir.satintech.filmbaz.data.network.model.DetailMovieResponse;
import ir.satintech.filmbaz.ui.base.BasePresenter;
import ir.satintech.filmbaz.utils.rx.SchedulerProvider;




public class DetailMoviePresenter<V extends DetailMovieMvpView> extends BasePresenter<V>
        implements DetailMovieMvpPresenter<V> {



    @Inject
    public DetailMoviePresenter(DataManager dataManager,
                                SchedulerProvider schedulerProvider,
                                CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


    @Override
    public void getDetailMovie(int movie_id) {


        getMvpView().showLoading();

        getCompositeDisposable().add(getDataManager().DetailMovie(movie_id)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<DetailMovieResponse>() {
                    @Override
                    public void accept(DetailMovieResponse response) throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }

                        getMvpView().hideLoading();
                        getMvpView().showDetailMovie(response);

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }

                        getMvpView().hideLoading();
                        getMvpView().error_load_data(handleApiError(throwable));


                    }
                }));


    }



    @Override
    public void addToFavorite(DetailMovieResponse item) {
        getDataManager().addFavoriteMovie(item);
        getMvpView().showMessage("به علاقه مندی اضافه شد");
    }

    @Override
    public void deleteFromFavorite(int movie_id) {
        getDataManager().deleteFavoriteMovie(movie_id);
        getMvpView().showMessage("از علاقه مندی ها حذف شد");
    }

    @Override
    public boolean existInFavorite(int item) {
        return getDataManager().existInFavoriteMovie(item);
    }
}
