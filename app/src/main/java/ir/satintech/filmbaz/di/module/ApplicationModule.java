
package ir.satintech.filmbaz.di.module;

import android.app.Application;
import android.content.Context;

import ir.satintech.filmbaz.R;
import ir.satintech.filmbaz.data.AppDataManager;
import ir.satintech.filmbaz.data.DataManager;
import ir.satintech.filmbaz.data.network.ApiHelper;
import ir.satintech.filmbaz.data.network.AppApiHelper;
import ir.satintech.filmbaz.data.prefs.AppPreferencesHelper;
import ir.satintech.filmbaz.data.prefs.PreferencesHelper;
import ir.satintech.filmbaz.di.ApplicationContext;
import ir.satintech.filmbaz.di.PreferenceInfo;
import ir.satintech.filmbaz.utils.AppConstants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }




    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }



    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }


    @Provides
    @Singleton
    CalligraphyConfig provideCalligraphyDefaultConfig() {
        return new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/byekan.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
    }
}
