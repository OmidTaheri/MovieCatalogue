

package ir.satintech.filmbaz.ui.genredmovies;


import java.util.List;

import ir.satintech.filmbaz.data.network.model.Movie;
import ir.satintech.filmbaz.ui.base.MvpView;



public interface GenredMoviesMvpView extends MvpView {


    void sucssed_load_first_page(List<Movie> list);

    void error_load_first_page(int message, int genre_id);

    void sucssed_load_next_page(List<Movie> list);

    void error_load_next_page(int genre_id, int page, int message);


    void SetTotalPage(int total_page);


    void setupList(List<Movie> items_list, int genre_id);

    void showMovieDetailActivity(int movie_id);


}
