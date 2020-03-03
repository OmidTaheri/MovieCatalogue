package ir.satintech.filmbaz.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ir.satintech.filmbaz.BuildConfig;
import ir.satintech.filmbaz.data.network.ApiInterface;
import ir.satintech.filmbaz.di.Base_Url;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class NetworkModule {


    @Provides
    @Base_Url
    String provideBaseUrlString() {
        return BuildConfig.BASE_URL;
    }

    @Provides
    @Singleton
    Converter.Factory provideGsonConverter() {
        return GsonConverterFactory.create();
    }


    @Provides
    @Singleton
    Retrofit provideRetrofit(Converter.Factory converter, @Base_Url String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(converter)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    ApiInterface provideApiClient(Retrofit retrofit) {
        return retrofit.create(ApiInterface.class);
    }

}
