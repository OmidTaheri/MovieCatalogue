

package ir.satintech.filmbaz.ui.search;


import ir.satintech.filmbaz.di.PerActivity;
import ir.satintech.filmbaz.ui.base.MvpPresenter;



@PerActivity
public interface SearchMvpPresenter<V extends SearchMvpView> extends MvpPresenter<V> {


    void searchFirstPage(String query);

    void searchNextPage(int page, String query);

}
