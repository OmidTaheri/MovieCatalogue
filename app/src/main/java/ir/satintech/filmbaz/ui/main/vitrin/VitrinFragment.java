package ir.satintech.filmbaz.ui.main.vitrin;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
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
import ir.satintech.filmbaz.R;
import ir.satintech.filmbaz.data.network.model.Movie;
import ir.satintech.filmbaz.di.component.ActivityComponent;
import ir.satintech.filmbaz.ui.base.BaseFragment;
import ir.satintech.filmbaz.ui.detailmovie.DetailMovieActivity;
import ir.satintech.filmbaz.ui.genredmovies.GenredMoviesActivity;




public class VitrinFragment extends BaseFragment implements
        VitrinMvpView, VitrinAdapter.Callback {

    @Inject
    VitrinMvpPresenter<VitrinMvpView> mPresenter;
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.list1)
    RecyclerView list1;
    @BindView(R.id.progressBar1)
    ProgressBar progressBar1;
    @BindView(R.id.error_text)
    TextView erroreText;
    @BindView(R.id.error_btn_retry)
    TextView errorBtnRetry;
    @BindView(R.id.error_layout)
    ConstraintLayout errorLayout;
    @BindView(R.id.list_layout1)
    ConstraintLayout listLayout1;
    @BindView(R.id.title2)
    TextView title2;
    @BindView(R.id.list2)
    RecyclerView list2;
    @BindView(R.id.progressBar2)
    ProgressBar progressBar2;
    @BindView(R.id.error_text2)
    TextView errorText2;
    @BindView(R.id.error_btn_retry2)
    TextView errorBtnRetry2;
    @BindView(R.id.error_layout2)
    ConstraintLayout errorLayout2;
    @BindView(R.id.list_layout2)
    ConstraintLayout listLayout2;
    @BindView(R.id.title3)
    TextView title3;
    @BindView(R.id.list3)
    RecyclerView list3;
    @BindView(R.id.progressBar3)
    ProgressBar progressBar3;
    @BindView(R.id.error_text3)
    TextView errorText3;
    @BindView(R.id.error_btn_retry3)
    TextView errorBtnRetry3;
    @BindView(R.id.error_layout3)
    ConstraintLayout errorLayout3;
    @BindView(R.id.list_layout3)
    ConstraintLayout listLayout3;
    @BindView(R.id.show_all1)
    TextView showAll1;
    @BindView(R.id.show_all2)
    TextView showAll2;
    @BindView(R.id.show_all3)
    TextView showAll3;
    @BindView(R.id.cl_root_view)
    CoordinatorLayout clRootView;

    /////////////

    List<Movie> items1;
    List<Movie> items2;
    List<Movie> items3;

    public static VitrinFragment newInstance() {

        return new VitrinFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_vitrin, container, false);

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

        hideLoading();


        if (items1 == null) {

            mPresenter.GetMovieListByGenre(3, 1);
        } else {
            setupList1(items1);
        }


        if (items2 == null) {

            mPresenter.GetMovieListByGenre(2, 2);
        } else {
            setupList2(items2);
        }


        if (items3 == null) {

            mPresenter.GetMovieListByGenre(9, 3);
        } else {
            setupList3(items3);
        }

        ///
        showAll1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.showAllMovieActivity(3, "Action");
            }
        });

        showAll2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.showAllMovieActivity(2, "Drama");
            }
        });

        showAll3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.showAllMovieActivity(9, "Comedy");
            }
        });

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
    public void setupList1(List<Movie> items_list) {

        progressBar1.setVisibility(View.GONE);

        items1=items_list;
        VitrinAdapter adapter = new VitrinAdapter(items_list, getBaseActivity());
        adapter.setCallback(this);


        list1.setAdapter(adapter);
        list1.setFocusable(false);
        list1.setLayoutManager(
                new LinearLayoutManager(getBaseActivity(), LinearLayoutManager.HORIZONTAL, true));

    }

    @Override
    public void setupList2(List<Movie> items_list) {

        progressBar2.setVisibility(View.GONE);

        items2=items_list;

        VitrinAdapter adapter = new VitrinAdapter(items_list, getBaseActivity());
        adapter.setCallback(this);


        list2.setAdapter(adapter);
        list2.setFocusable(false);
        list2.setLayoutManager(
                new LinearLayoutManager(getBaseActivity(), LinearLayoutManager.HORIZONTAL, true));

    }

    @Override
    public void setupList3(List<Movie> items_list) {
        progressBar3.setVisibility(View.GONE);

        items3=items_list;

        VitrinAdapter adapter = new VitrinAdapter(items_list, getBaseActivity());
        adapter.setCallback(this);


        list3.setAdapter(adapter);
        list3.setFocusable(false);
        list3.setLayoutManager(
                new LinearLayoutManager(getBaseActivity(), LinearLayoutManager.HORIZONTAL, true));
    }

    @Override
    public void showMovieDetailActivity(int movie_id) {
        Intent intent = DetailMovieActivity.getStartIntent(getBaseActivity(), movie_id, this.getClass().getSimpleName());
        startActivity(intent);

    }

    @Override
    public void showAllMovieActivity(int genre_id, String genre_name) {
        Intent intent = GenredMoviesActivity.getStartIntent(getBaseActivity(), genre_id, genre_name);
        startActivity(intent);
    }

    @Override
    public void visibility_progressBar1(boolean show) {
        if (show) {
            progressBar1.setVisibility(View.VISIBLE);
        } else {

            progressBar1.setVisibility(View.GONE);
        }
    }

    @Override
    public void visibility_progressBar2(boolean show) {
        if (show) {
            progressBar2.setVisibility(View.VISIBLE);
        } else {

            progressBar2.setVisibility(View.GONE);
        }
    }

    @Override
    public void visibility_progressBar3(boolean show) {
        if (show) {
            progressBar3.setVisibility(View.VISIBLE);
        } else {

            progressBar3.setVisibility(View.GONE);
        }
    }

    @Override
    public void error_load_List_1(int message) {

        if (errorLayout.getVisibility() == View.GONE) {
            errorLayout.setVisibility(View.VISIBLE);
            progressBar1.setVisibility(View.GONE);


            erroreText.setText(getResources().getString(message));
        }

        errorBtnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                errorLayout.setVisibility(View.GONE);
                progressBar1.setVisibility(View.VISIBLE);

                mPresenter.GetMovieListByGenre(3, 1);

            }
        });

    }

    @Override
    public void error_load_List_2(int message) {
        if (errorLayout2.getVisibility() == View.GONE) {
            errorLayout2.setVisibility(View.VISIBLE);
            progressBar2.setVisibility(View.GONE);


            errorText2.setText(getResources().getString(message));
        }

        errorBtnRetry2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                errorLayout2.setVisibility(View.GONE);
                progressBar2.setVisibility(View.VISIBLE);


                mPresenter.GetMovieListByGenre(2, 2);

            }
        });
    }

    @Override
    public void error_load_List_3(int message) {
        if (errorLayout3.getVisibility() == View.GONE) {
            errorLayout3.setVisibility(View.VISIBLE);
            progressBar3.setVisibility(View.GONE);
            errorText3.setText(getResources().getString(message));

        }

        errorBtnRetry3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                errorLayout3.setVisibility(View.GONE);
                progressBar3.setVisibility(View.VISIBLE);
                mPresenter.GetMovieListByGenre(9, 3);
            }
        });
    }
}