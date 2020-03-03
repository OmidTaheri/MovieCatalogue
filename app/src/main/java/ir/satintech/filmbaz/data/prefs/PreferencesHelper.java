

package ir.satintech.filmbaz.data.prefs;

import java.util.List;

import ir.satintech.filmbaz.data.network.model.DetailMovieResponse;


public interface PreferencesHelper {


    void addFavoriteMovie(DetailMovieResponse movie);

    void deleteFavoriteMovie(int movie_id);

    boolean existInFavoriteMovie(int item_id);

    List<DetailMovieResponse> getFavoriteMovies();

}
