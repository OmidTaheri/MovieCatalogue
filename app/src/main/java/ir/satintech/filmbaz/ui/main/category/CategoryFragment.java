package ir.satintech.filmbaz.ui.main.category;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ir.satintech.filmbaz.R;
import ir.satintech.filmbaz.data.network.model.GenresListResponse;
import ir.satintech.filmbaz.di.component.ActivityComponent;
import ir.satintech.filmbaz.ui.base.BaseFragment;
import ir.satintech.filmbaz.ui.genredmovies.GenredMoviesActivity;





public class CategoryFragment extends BaseFragment implements
        CategoryMvpView, CategoryAdapter.Callback {

    @Inject
    CategoryMvpPresenter<CategoryMvpView> mPresenter;
    @BindView(R.id.genre_list)
    RecyclerView genreList;


    @BindView(R.id.category_progressBar)
    ProgressBar categoryProgressBar;
    @BindView(R.id.error_text)
    TextView erroreText;
    @BindView(R.id.error_btn_retry)
    TextView errorBtnRetry;
    @BindView(R.id.error_layout)
    ConstraintLayout errorLayout;
    Unbinder unbinder;

    ////////////
    List<GenresListResponse> items;
    public static CategoryFragment newInstance() {

        return new CategoryFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_category, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, v));
            mPresenter.onAttach(this);

        }


        unbinder = ButterKnife.bind(this, v);
        return v;
    }


    @Override
    protected void setUp(View view) {


        if (items == null) {

            mPresenter.getGenreList();
        } else {
            setListGenre(items);
        }





    }


    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
        unbinder.unbind();


    }


    @Override
    public void setListGenre(List<GenresListResponse> list) {

        items=list;

        CategoryAdapter adapter = new CategoryAdapter(list, getBaseActivity());
        genreList.setAdapter(adapter);


        adapter.setCallback(this);
        genreList.setLayoutManager(
                new LinearLayoutManager(getBaseActivity(), LinearLayoutManager.VERTICAL, false));


    }

    @Override
    public void showGenreDetailActivity(int genre_id,String genre_name) {

        Intent intent = GenredMoviesActivity.getStartIntent(getBaseActivity(), genre_id,genre_name);
        startActivity(intent);

    }

    @Override
    public void onItemClick(GenresListResponse item) {
        mPresenter.showGenreDetailActivity(item.getId(),item.getName());
    }


    @Override
    public void visibility_progressBar(boolean show) {
        if (show) {
            categoryProgressBar.setVisibility(View.VISIBLE);
        } else {

            categoryProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void error_load_List(int message) {
        if (errorLayout.getVisibility() == View.GONE) {
            errorLayout.setVisibility(View.VISIBLE);
            categoryProgressBar.setVisibility(View.GONE);


            erroreText.setText(getResources().getString(message));
        }

        errorBtnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                errorLayout.setVisibility(View.GONE);
                categoryProgressBar.setVisibility(View.VISIBLE);

                mPresenter.getGenreList();

            }
        });
    }
}