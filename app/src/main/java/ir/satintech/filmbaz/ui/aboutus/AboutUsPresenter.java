
package ir.satintech.filmbaz.ui.aboutus;


import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import ir.satintech.filmbaz.data.DataManager;
import ir.satintech.filmbaz.ui.base.BasePresenter;
import ir.satintech.filmbaz.utils.rx.SchedulerProvider;




public class AboutUsPresenter<V extends AboutUsMvpView> extends BasePresenter<V>
        implements AboutUsMvpPresenter<V> {



    @Inject
    public AboutUsPresenter(DataManager dataManager,
                            SchedulerProvider schedulerProvider,
                            CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }




    @Override
    public void openSite() {
        getMvpView().openWebsite();
    }

    @Override
    public void openTelegram() {
        getMvpView().openTelegramChannel();
    }

    @Override
    public void openInstagram() {

        getMvpView().openInstagram();

    }


}
