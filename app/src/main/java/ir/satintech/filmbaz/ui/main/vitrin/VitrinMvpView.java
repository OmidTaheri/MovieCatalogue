

package ir.satintech.filmbaz.ui.main.vitrin;


import java.util.List;

import ir.satintech.filmbaz.data.network.model.Movie;
import ir.satintech.filmbaz.ui.base.MvpView;



public interface VitrinMvpView extends MvpView {

    void setupList1(List<Movie> items_list);

    void setupList2(List<Movie> items_list);

    void setupList3(List<Movie> items_list);

    void showMovieDetailActivity(int movie_id);

    void showAllMovieActivity(int genre_id,String genre_name);


    void visibility_progressBar1(boolean show);

    void visibility_progressBar2(boolean show);

    void visibility_progressBar3(boolean show);


    void error_load_List_1(int message);

    void error_load_List_2(int message);

    void error_load_List_3(int message);


}
