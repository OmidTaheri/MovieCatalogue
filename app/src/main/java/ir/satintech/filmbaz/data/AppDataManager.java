
package ir.satintech.filmbaz.data;


import android.content.Context;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import ir.satintech.filmbaz.data.network.ApiHelper;
import ir.satintech.filmbaz.data.network.model.DetailMovieResponse;
import ir.satintech.filmbaz.data.network.model.GenresListResponse;
import ir.satintech.filmbaz.data.network.model.MovieByGenretResponse;
import ir.satintech.filmbaz.data.network.model.MovieListResponse;
import ir.satintech.filmbaz.data.network.model.SearchNameResponse;
import ir.satintech.filmbaz.data.network.model.SendMovieRequest;
import ir.satintech.filmbaz.data.network.model.SendMovieResponse;
import ir.satintech.filmbaz.data.prefs.PreferencesHelper;
import ir.satintech.filmbaz.di.ApplicationContext;


@Singleton
public class AppDataManager implements DataManager {



    private final Context mContext;

    private final PreferencesHelper mPreferencesHelper;
    private final ApiHelper mApiHelper;

    @Inject
    public AppDataManager(@ApplicationContext Context context,
                          PreferencesHelper preferencesHelper,
                          ApiHelper apiHelper) {
        mContext = context;
        mPreferencesHelper = preferencesHelper;
        mApiHelper = apiHelper;
    }


    @Override
    public Observable<MovieListResponse> MovieList(int page) {
        return mApiHelper.MovieList(page);
    }

    @Override
    public Observable<SearchNameResponse> SearchName(String name,int page) {
        return mApiHelper.SearchName(name,page);
    }

    @Override
    public Observable<SendMovieResponse> SendMovie(SendMovieRequest request) {
        return mApiHelper.SendMovie(request);
    }

    @Override
    public Observable<DetailMovieResponse> DetailMovie(int movie_id) {
        return mApiHelper.DetailMovie(movie_id);
    }

    @Override
    public Observable<List<GenresListResponse>> GenresList() {
        return mApiHelper.GenresList();
    }

    @Override
    public Observable<MovieByGenretResponse> GetMovieByGenre(int genre_id, int page) {
        return mApiHelper.GetMovieByGenre(genre_id, page);
    }


    @Override
    public void addFavoriteMovie(DetailMovieResponse item) {
        mPreferencesHelper.addFavoriteMovie(item);
    }

    @Override
    public void deleteFavoriteMovie(int movie_id) {
        mPreferencesHelper.deleteFavoriteMovie(movie_id);
    }

    @Override
    public boolean existInFavoriteMovie(int item_id) {
        return mPreferencesHelper.existInFavoriteMovie(item_id);
    }

    @Override
    public List<DetailMovieResponse> getFavoriteMovies() {
        return mPreferencesHelper.getFavoriteMovies();
    }
}
