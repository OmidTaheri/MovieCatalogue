
package ir.satintech.filmbaz.ui.main;


import ir.satintech.filmbaz.di.PerActivity;
import ir.satintech.filmbaz.ui.base.MvpPresenter;




@PerActivity
public interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V> {


    void showSearchActivity();

    void showAboutUsActivity();
}
