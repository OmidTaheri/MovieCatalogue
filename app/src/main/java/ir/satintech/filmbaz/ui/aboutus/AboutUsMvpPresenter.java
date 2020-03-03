
package ir.satintech.filmbaz.ui.aboutus;


import ir.satintech.filmbaz.di.PerActivity;
import ir.satintech.filmbaz.ui.base.MvpPresenter;



@PerActivity
public interface AboutUsMvpPresenter<V extends AboutUsMvpView> extends MvpPresenter<V> {



    void openSite();
    void openTelegram();
    void openInstagram();

}
