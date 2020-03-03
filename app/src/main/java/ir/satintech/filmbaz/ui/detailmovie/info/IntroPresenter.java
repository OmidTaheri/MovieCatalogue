

package ir.satintech.filmbaz.ui.detailmovie.info;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import ir.satintech.filmbaz.data.DataManager;
import ir.satintech.filmbaz.ui.base.BasePresenter;
import ir.satintech.filmbaz.utils.rx.SchedulerProvider;





public class IntroPresenter<V extends IntroMvpView> extends BasePresenter<V>
        implements IntroMvpPresenter<V> {

    @Inject
    public IntroPresenter(DataManager dataManager,
                          SchedulerProvider schedulerProvider,
                          CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }



}
