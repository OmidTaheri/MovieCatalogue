
package ir.satintech.filmbaz.data.network;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import ir.satintech.filmbaz.data.network.model.DetailMovieResponse;
import ir.satintech.filmbaz.data.network.model.GenresListResponse;
import ir.satintech.filmbaz.data.network.model.MovieByGenretResponse;
import ir.satintech.filmbaz.data.network.model.MovieListResponse;
import ir.satintech.filmbaz.data.network.model.SearchNameResponse;
import ir.satintech.filmbaz.data.network.model.SendMovieRequest;
import ir.satintech.filmbaz.data.network.model.SendMovieResponse;



@Singleton
public class AppApiHelper implements ApiHelper {

    private ApiInterface mApiInterface;

    @Inject
    public AppApiHelper(ApiInterface apiInterface) {
        mApiInterface = apiInterface;
    }


    public ApiInterface getApiInterface() {
        return mApiInterface;
    }


    @Override
    public Observable<MovieListResponse> MovieList(int page) {

        return getApiInterface().MovieList(page);

    }

    @Override
    public Observable<SearchNameResponse> SearchName(String name, int page) {
        return getApiInterface().SearchName(name, page);
    }


    @Override
    public Observable<SendMovieResponse> SendMovie(SendMovieRequest request) {
        return getApiInterface().SendMovie(request);
    }

    @Override
    public Observable<DetailMovieResponse> DetailMovie(int movie_id) {
        return getApiInterface().DetailMovie(movie_id);
    }

    @Override
    public Observable<List<GenresListResponse>> GenresList() {
        return getApiInterface().GenresList();
    }

    @Override
    public Observable<MovieByGenretResponse> GetMovieByGenre(int genre_id, int page) {
        return getApiInterface().GetMovieByGenre(genre_id, page);
    }

}

