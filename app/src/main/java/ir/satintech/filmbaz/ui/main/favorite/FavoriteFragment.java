package ir.satintech.filmbaz.ui.main.favorite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.satintech.filmbaz.R;
import ir.satintech.filmbaz.data.network.model.DetailMovieResponse;
import ir.satintech.filmbaz.di.component.ActivityComponent;
import ir.satintech.filmbaz.ui.base.BaseFragment;
import ir.satintech.filmbaz.ui.detailmovie.DetailMovieActivity;


public class FavoriteFragment extends BaseFragment implements
        FavoriteMvpView, FavoriteAdapter.Callback {

    @Inject
    FavoriteMvpPresenter<FavoriteMvpView> mPresenter;
    @BindView(R.id.favorit_list)
    RecyclerView favoritList;


    public static FavoriteFragment newInstance() {

        return new FavoriteFragment();
    }





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_favorite, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, v));
            mPresenter.onAttach(this);

        }


        return v;
    }


    @Override
    protected void setUp(View view) {
        mPresenter.getFavoriteList();
    }


    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();


    }


    @Override
    public void onItemClick(int movie_id) {
        mPresenter.showMovieDetailActivity(movie_id);
    }

    @Override
    public void setFavoriteList(List<DetailMovieResponse> list) {

        //////////////////////////////
        MyGridAutofitLayoutManager manager = new MyGridAutofitLayoutManager(getBaseActivity(), favoritList);
        final GridLayoutManager layoutManager;
        layoutManager = new GridLayoutManager(getBaseActivity(), manager.getmColumnNumber());
        favoritList.setLayoutManager(layoutManager);
//////////


        final FavoriteAdapter adapter = new FavoriteAdapter(list, getBaseActivity(), manager);
        adapter.setCallback(this);
        favoritList.setAdapter(adapter);


        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (adapter.getItemViewType(position)) {
                    case FavoriteAdapter.VIEW_TYPE_EMPTY:
                        return layoutManager.getSpanCount();
                    case FavoriteAdapter.VIEW_TYPE_NORMAL:
                        return 1;

                    default:
                        return 1;
                }
            }
        });


    }

    @Override
    public void showMovieDetailActivity(int movie_id) {
        Intent intent = DetailMovieActivity.getStartIntent(getBaseActivity(), movie_id,this.getClass().getSimpleName());
        startActivity(intent);
         getBaseActivity().finish();
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        }
    }
}