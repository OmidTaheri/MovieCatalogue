package ir.satintech.filmbaz.data.network;

import java.util.List;

import io.reactivex.Observable;
import ir.satintech.filmbaz.data.network.model.DetailMovieResponse;
import ir.satintech.filmbaz.data.network.model.GenresListResponse;
import ir.satintech.filmbaz.data.network.model.MovieByGenretResponse;
import ir.satintech.filmbaz.data.network.model.MovieListResponse;
import ir.satintech.filmbaz.data.network.model.SearchNameResponse;
import ir.satintech.filmbaz.data.network.model.SendMovieRequest;
import ir.satintech.filmbaz.data.network.model.SendMovieResponse;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;



public interface ApiInterface {

    @GET(ApiEndPoint.ENDPOINT_MOVIES_LIST)
    Observable<MovieListResponse> MovieList(@Query("page") int page);


    @GET(ApiEndPoint.ENDPOINT_MOVIES_LIST)
    Observable<SearchNameResponse> SearchName(@Query("q") String name,@Query("page") int page);


    @POST(ApiEndPoint.ENDPOINT_SEND_MOVIE)
    Observable<SendMovieResponse> SendMovie(@Body SendMovieRequest request);


    @GET(ApiEndPoint.ENDPOINT_MOVIES_LIST+"/{movie_id}")
    Observable<DetailMovieResponse> DetailMovie(@Path("movie_id") int movie_id);

    @GET(ApiEndPoint.ENDPOINT_GENRES)
    Observable<List<GenresListResponse>> GenresList();

    @GET(ApiEndPoint.ENDPOINT_GENRES+"/{genre_id}/movies")
    Observable<MovieByGenretResponse> GetMovieByGenre(@Path("genre_id") int genre_id,@Query("page") int page);



}
