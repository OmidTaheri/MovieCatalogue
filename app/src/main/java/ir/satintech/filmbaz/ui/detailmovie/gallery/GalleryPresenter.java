

package ir.satintech.filmbaz.ui.detailmovie.gallery;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import ir.satintech.filmbaz.data.DataManager;
import ir.satintech.filmbaz.ui.base.BasePresenter;
import ir.satintech.filmbaz.utils.rx.SchedulerProvider;





public class GalleryPresenter<V extends GalleryMvpView> extends BasePresenter<V>
        implements GalleryMvpPresenter<V> {

    @Inject
    public GalleryPresenter(DataManager dataManager,
                            SchedulerProvider schedulerProvider,
                            CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


}
