

package ir.satintech.filmbaz.ui.detailmovie.summary;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import ir.satintech.filmbaz.data.DataManager;
import ir.satintech.filmbaz.ui.base.BasePresenter;
import ir.satintech.filmbaz.utils.rx.SchedulerProvider;




public class SummaryPresenter<V extends SummaryMvpView> extends BasePresenter<V>
        implements SummaryMvpPresenter<V> {

    @Inject
    public SummaryPresenter(DataManager dataManager,
                            SchedulerProvider schedulerProvider,
                            CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }



}
