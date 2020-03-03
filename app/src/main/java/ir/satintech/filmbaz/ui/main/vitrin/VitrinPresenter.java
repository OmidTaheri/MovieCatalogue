

package ir.satintech.filmbaz.ui.main.vitrin;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import ir.satintech.filmbaz.data.DataManager;
import ir.satintech.filmbaz.data.network.model.MovieByGenretResponse;
import ir.satintech.filmbaz.ui.base.BasePresenter;
import ir.satintech.filmbaz.utils.rx.SchedulerProvider;





public class VitrinPresenter<V extends VitrinMvpView> extends BasePresenter<V>
        implements VitrinMvpPresenter<V> {

    @Inject
    public VitrinPresenter(DataManager dataManager,
                           SchedulerProvider schedulerProvider,
                           CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void showMovieDetailActivity(int movie_id) {
        getMvpView().showMovieDetailActivity(movie_id);
    }

    @Override
    public void showAllMovieActivity(int genre_id,String genre_name) {
        getMvpView().showAllMovieActivity(genre_id,genre_name);
    }

    @Override
    public void GetMovieListByGenre(int genre_id, final int list) {


        if (list == 1) {
            getMvpView().visibility_progressBar1(true);
        } else if (list == 2) {
            getMvpView().visibility_progressBar2(true);
        } else if (list == 3) {
            getMvpView().visibility_progressBar3(true);
        }


        getCompositeDisposable().add(getDataManager().GetMovieByGenre(genre_id, 1)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<MovieByGenretResponse>() {
                    @Override
                    public void accept(MovieByGenretResponse response) throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }


                        if (list == 1) {
                            getMvpView().visibility_progressBar1(false);
                            getMvpView().setupList1(response.getData());
                        } else if (list == 2) {
                            getMvpView().visibility_progressBar2(false);
                            getMvpView().setupList2(response.getData());
                        } else if (list == 3) {
                            getMvpView().visibility_progressBar3(false);
                            getMvpView().setupList3(response.getData());
                        }


                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }

                        if (list == 1) {

                            getMvpView().error_load_List_1(handleApiError(throwable));

                        } else if (list == 2) {

                            getMvpView().error_load_List_2(handleApiError(throwable));

                        } else if (list == 3) {

                            getMvpView().error_load_List_3(handleApiError(throwable));

                        }

                    }
                }));


    }
}
