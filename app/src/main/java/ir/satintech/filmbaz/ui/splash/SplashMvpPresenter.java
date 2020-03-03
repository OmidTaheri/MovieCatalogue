

package ir.satintech.filmbaz.ui.splash;


import android.content.Context;

import ir.satintech.filmbaz.di.PerActivity;
import ir.satintech.filmbaz.ui.base.MvpPresenter;




@PerActivity
public interface SplashMvpPresenter<V extends SplashMvpView> extends MvpPresenter<V> {
    void delayToNextActivity(Context ctx);
}
