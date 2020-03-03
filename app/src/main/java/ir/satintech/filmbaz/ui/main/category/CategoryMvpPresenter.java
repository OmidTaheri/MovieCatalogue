

package ir.satintech.filmbaz.ui.main.category;


import ir.satintech.filmbaz.ui.base.MvpPresenter;




public interface CategoryMvpPresenter<V extends CategoryMvpView>
        extends MvpPresenter<V> {

    void getGenreList();

    void showGenreDetailActivity(int genre_id, String genre_name);
}


