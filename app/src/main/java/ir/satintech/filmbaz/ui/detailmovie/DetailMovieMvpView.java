

package ir.satintech.filmbaz.ui.detailmovie;

import ir.satintech.filmbaz.data.network.model.DetailMovieResponse;
import ir.satintech.filmbaz.ui.base.MvpView;



public interface DetailMovieMvpView extends MvpView {

    void showDetailMovie(DetailMovieResponse detailMovie);



    void error_load_data(int message);

}
