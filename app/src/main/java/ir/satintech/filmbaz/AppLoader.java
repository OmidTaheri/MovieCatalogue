package ir.satintech.filmbaz;

import android.app.Application;



import javax.inject.Inject;


import ir.satintech.filmbaz.data.DataManager;
import ir.satintech.filmbaz.di.component.ApplicationComponent;
import ir.satintech.filmbaz.di.component.DaggerApplicationComponent;
import ir.satintech.filmbaz.di.module.ApplicationModule;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


public class AppLoader extends Application {

    @Inject
    DataManager mDataManager;

    @Inject
    CalligraphyConfig mCalligraphyConfig;

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();

        mApplicationComponent.inject(this);

        CalligraphyConfig.initDefault(mCalligraphyConfig);

    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }


    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}
