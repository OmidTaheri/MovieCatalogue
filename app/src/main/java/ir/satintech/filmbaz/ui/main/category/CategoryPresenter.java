

package ir.satintech.filmbaz.ui.main.category;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import ir.satintech.filmbaz.data.DataManager;
import ir.satintech.filmbaz.data.network.model.GenresListResponse;
import ir.satintech.filmbaz.ui.base.BasePresenter;
import ir.satintech.filmbaz.utils.rx.SchedulerProvider;





public class CategoryPresenter<V extends CategoryMvpView> extends BasePresenter<V>
        implements CategoryMvpPresenter<V> {

    @Inject
    public CategoryPresenter(DataManager dataManager,
                             SchedulerProvider schedulerProvider,
                             CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


    @Override
    public void getGenreList() {
        getMvpView().visibility_progressBar(true);

        getCompositeDisposable().add(getDataManager().GenresList()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<List<GenresListResponse>>() {
                    @Override
                    public void accept(List<GenresListResponse> response) throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }
                        getMvpView().visibility_progressBar(false);
                        getMvpView().setListGenre(response);

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }

                        getMvpView().visibility_progressBar(false);
                        getMvpView().error_load_List(handleApiError(throwable));
                    }
                }));
    }

    @Override
    public void showGenreDetailActivity(int genre_id, String genre_name) {

        getMvpView().showGenreDetailActivity(genre_id,genre_name);
    }
}
