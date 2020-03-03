

package ir.satintech.filmbaz.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import ir.satintech.filmbaz.data.network.model.DetailMovieResponse;

import ir.satintech.filmbaz.di.ApplicationContext;
import ir.satintech.filmbaz.di.PreferenceInfo;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AppPreferencesHelper implements PreferencesHelper {

    private static final String PREF_KEY_FAVORITES_ITEMS = "PREF_KEY_FAVORITES_ITEMS";



    private final SharedPreferences mPrefs;

    @Inject
    public AppPreferencesHelper(@ApplicationContext Context context,
                                @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }


    @Override
    public void addFavoriteMovie(DetailMovieResponse movie) {
        boolean break_boolean = false;
        Gson gson = new Gson();
        SharedPreferences.Editor editor = mPrefs.edit();

        String list = mPrefs.getString(PREF_KEY_FAVORITES_ITEMS, "");

        List<DetailMovieResponse> movieFromShared = new ArrayList<>();
        Type type = new TypeToken<List<DetailMovieResponse>>() {
        }.getType();

        movieFromShared = gson.fromJson(list, type);
//////////////////////////////////////////////////////////////////
        String jsonNewmovieToAdd = gson.toJson(movie);
        JSONArray jsonArrayMovie = new JSONArray();

        try {
            if (list.length() != 0) {
                jsonArrayMovie = new JSONArray(list);


                for (int i = 0; i < movieFromShared.size(); i++) {

                    if (movieFromShared.get(i).getId() == movie.getId()) {
                        break_boolean = true;
                        break;

                    }

                }

                if (!break_boolean) {
                    jsonArrayMovie.put(new JSONObject(jsonNewmovieToAdd));

                }


            } else {
                jsonArrayMovie.put(new JSONObject(jsonNewmovieToAdd));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        editor.putString(PREF_KEY_FAVORITES_ITEMS, String.valueOf(jsonArrayMovie));
        editor.apply();
    }

    @Override
    public void deleteFavoriteMovie(int movie_id) {

        Gson gson = new Gson();
        SharedPreferences.Editor editor = mPrefs.edit();

        String list = mPrefs.getString(PREF_KEY_FAVORITES_ITEMS, "");

        List<DetailMovieResponse> movieFromShared = new ArrayList<>();
        Type type = new TypeToken<List<DetailMovieResponse>>() {
        }.getType();


        movieFromShared = gson.fromJson(list, type);


        for (int i = 0; i < movieFromShared.size(); i++) {

            if (movieFromShared.get(i).getId() == movie_id) {
                movieFromShared.remove(i);
            }

        }

        String FavoriteString;

        if (movieFromShared != null || movieFromShared.size() != 0) {
            FavoriteString = gson.toJson(movieFromShared);
        } else {
            FavoriteString = "";
        }

        editor.putString(PREF_KEY_FAVORITES_ITEMS, FavoriteString);
        editor.apply();
    }

    @Override
    public boolean existInFavoriteMovie(int item_id) {
        Gson gson = new Gson();

        String list = mPrefs.getString(PREF_KEY_FAVORITES_ITEMS, "");

        List<DetailMovieResponse> movieFromShared = new ArrayList<>();
        Type type = new TypeToken<List<DetailMovieResponse>>() {
        }.getType();

        movieFromShared = gson.fromJson(list, type);

        if (movieFromShared != null) {
            for (int i = 0; i < movieFromShared.size(); i++) {

                if (movieFromShared.get(i).getId() == item_id) {
                    return true;

                }

            }
        }

        return false;
    }

    @Override
    public List<DetailMovieResponse> getFavoriteMovies() {
        Gson gson = new Gson();

        String list = mPrefs.getString(PREF_KEY_FAVORITES_ITEMS, "");


        List<DetailMovieResponse> movieFromShared = new ArrayList<>();
        Type type = new TypeToken<List<DetailMovieResponse>>() {
        }.getType();


        movieFromShared = gson.fromJson(list, type);

        if (movieFromShared == null) {
            movieFromShared = new ArrayList<>();
        }

        return movieFromShared;
    }
}
