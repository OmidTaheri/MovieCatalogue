

package ir.satintech.filmbaz.ui.main.favorite;


import java.util.List;

import ir.satintech.filmbaz.data.network.model.DetailMovieResponse;
import ir.satintech.filmbaz.ui.base.MvpView;




public interface FavoriteMvpView extends MvpView {


    void setFavoriteList(List<DetailMovieResponse>list);

    void showMovieDetailActivity(int movie_id);

}
