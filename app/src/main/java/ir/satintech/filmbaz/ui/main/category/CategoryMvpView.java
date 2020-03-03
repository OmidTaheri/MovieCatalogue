

package ir.satintech.filmbaz.ui.main.category;


import java.util.List;

import ir.satintech.filmbaz.data.network.model.GenresListResponse;
import ir.satintech.filmbaz.ui.base.MvpView;




public interface CategoryMvpView extends MvpView {


    void setListGenre(List<GenresListResponse> items_list);

    void showGenreDetailActivity(int genre_id,String genre_name);


    void visibility_progressBar(boolean show);

    void error_load_List(int message);
}
