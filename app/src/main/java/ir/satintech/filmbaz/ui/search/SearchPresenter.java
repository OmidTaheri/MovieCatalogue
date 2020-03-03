

package ir.satintech.filmbaz.ui.search;


import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import ir.satintech.filmbaz.data.DataManager;
import ir.satintech.filmbaz.data.network.model.SearchNameResponse;
import ir.satintech.filmbaz.ui.base.BasePresenter;
import ir.satintech.filmbaz.utils.rx.SchedulerProvider;




public class SearchPresenter<V extends SearchMvpView> extends BasePresenter<V>
        implements SearchMvpPresenter<V> {



    @Inject
    public SearchPresenter(DataManager dataManager,
                           SchedulerProvider schedulerProvider,
                           CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


    @Override
    public void searchFirstPage(final String query) {

        getMvpView().visibility_progressBar(true);

        getCompositeDisposable().add(getDataManager().SearchName(query, 1)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<SearchNameResponse>() {
                    @Override
                    public void accept(SearchNameResponse response) throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }

                        getMvpView().SetTotalPage(response.getMetadata().getPageCount());
                        getMvpView().setupListofSearch(response.getData(), query);

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }

                        getMvpView().error_load_first_page(handleApiError(throwable), query);

                    }
                }));


    }


    @Override
    public void searchNextPage(final int page, final String query) {

        getCompositeDisposable().add(getDataManager().SearchName(query, page)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<SearchNameResponse>() {
                    @Override
                    public void accept(SearchNameResponse response) throws Exception {
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

                        getMvpView().error_load_next_page(query, page, handleApiError(throwable));

                    }
                }));


    }


}
