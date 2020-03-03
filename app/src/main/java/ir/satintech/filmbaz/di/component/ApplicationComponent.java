
package ir.satintech.filmbaz.di.component;

import android.app.Application;
import android.content.Context;

import ir.satintech.filmbaz.AppLoader;
import ir.satintech.filmbaz.data.DataManager;
import ir.satintech.filmbaz.di.ApplicationContext;
import ir.satintech.filmbaz.di.module.ApplicationModule;
import ir.satintech.filmbaz.di.module.NetworkModule;


import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {ApplicationModule.class,NetworkModule.class})
public interface ApplicationComponent {

    void inject(AppLoader app);


    @ApplicationContext
    Context context();

    Application application();

    DataManager getDataManager();
}