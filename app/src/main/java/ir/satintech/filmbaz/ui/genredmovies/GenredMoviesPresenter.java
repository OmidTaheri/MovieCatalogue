

package ir.satintech.filmbaz.ui.genredmovies;


import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import ir.satintech.filmbaz.data.DataManager;
import ir.satintech.filmbaz.data.network.model.MovieByGenretResponse;
import ir.satintech.filmbaz.ui.base.BasePresenter;
import ir.satintech.filmbaz.utils.rx.SchedulerProvider;




public class GenredMoviesPresenter<V extends GenredMoviesMvpView> extends BasePresenter<V>
        implements GenredMoviesMvpPresenter<V> {



    @Inject
    public GenredMoviesPresenter(DataManager dataManager,
                                 SchedulerProvider schedulerProvider,
                                 CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


    @Override
    public void loadFirstPage(final int genre_id) {


        getCompositeDisposable().add(getDataManager().GetMovieByGenre(genre_id, 1)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<MovieByGenretResponse>() {
                    @Override
                    public void accept(MovieByGenretResponse response) throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }

                        getMvpView().SetTotalPage(response.getMetadata().getPageCount());
                        getMvpView().setupList(response.getData(), genre_id);

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }


                        getMvpView().error_load_first_page(handleApiError(throwable), genre_id);
                    }
                }));

    }

    @Override
    public void loadNextPage(final int page, final int genre_id) {


        getCompositeDisposable().add(getDataManager().GetMovieByGenre(genre_id, page)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<MovieByGenretResponse>() {
                    @Override
                    public void accept(MovieByGenretResponse response) throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }
                        getMvpView().sucssed_load_next_page(response.getData());

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }

                        getMvpView().error_load_next_page(genre_id, page, handleApiError(throwable));
                    }
                }));
    }


}
