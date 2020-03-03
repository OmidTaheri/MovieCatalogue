
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




public interface ApiHelper {

    Observable<MovieListResponse> MovieList(int page);

    Observable<SearchNameResponse> SearchName(String name,  int page);

    Observable<SendMovieResponse> SendMovie(SendMovieRequest request);

    Observable<DetailMovieResponse> DetailMovie(int movie_id);

    Observable<List<GenresListResponse>> GenresList();

    Observable<MovieByGenretResponse> GetMovieByGenre(int genre_id, int page);


}
