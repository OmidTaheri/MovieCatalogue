

package ir.satintech.filmbaz.ui.main.vitrin;


import ir.satintech.filmbaz.ui.base.MvpPresenter;




public interface VitrinMvpPresenter<V extends VitrinMvpView>
        extends MvpPresenter<V> {

    void showMovieDetailActivity(int movie_id);

    void showAllMovieActivity(int genre_id,String genre_name);

    void GetMovieListByGenre(int genre_id, int list);
}


