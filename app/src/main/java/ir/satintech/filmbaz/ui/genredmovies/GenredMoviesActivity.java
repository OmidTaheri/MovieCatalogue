package ir.satintech.filmbaz.ui.genredmovies;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.satintech.filmbaz.R;
import ir.satintech.filmbaz.data.network.model.Movie;
import ir.satintech.filmbaz.ui.base.BaseActivity;
import ir.satintech.filmbaz.ui.detailmovie.DetailMovieActivity;


public class GenredMoviesActivity extends BaseActivity implements GenredMoviesMvpView, GenredMoviesRecyclerAdapter.Callback {

    @Inject
    GenredMoviesMvpPresenter<GenredMoviesMvpView> mPresenter;


    PaginationScrollListener scrollListener;


    private static final int PAGE_START = 1;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int TOTAL_PAGES = 0;
    private int currentPage = PAGE_START;
    private GenredMoviesRecyclerAdapter adapter;
    /////////////////
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.movie_list)
    RecyclerView movieList;
    @BindView(R.id.main_progress)
    ProgressBar mainProgress;
    @BindView(R.id.error_text)
    TextView errorText;
    @BindView(R.id.error_btn_retry)
    TextView errorBtnRetry;
    @BindView(R.id.error_layout)
    ConstraintLayout errorLayout;


    public static Intent getStartIntent(Context context, int genre_id, String genre_name) {
        Intent intent = new Intent(context, GenredMoviesActivity.class);
        intent.putExtra("genre_id", genre_id);
        intent.putExtra("genre_name", genre_name);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre_movie);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(this);

        setUp();


    }


    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    protected void setUp() {

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int genre_id = getIntent().getExtras().getInt("genre_id");

        String genre_name = getIntent().getExtras().getString("genre_name");

        getSupportActionBar().setTitle(genre_name);

        mPresenter.loadFirstPage(genre_id);

    }


    @Override
    public void sucssed_load_first_page(List<Movie> list) {
        mainProgress.setVisibility(View.GONE);



        if (currentPage <= TOTAL_PAGES && TOTAL_PAGES != 1) {
            adapter.addLoadingFooter();
        } else isLastPage = true;
    }

    @Override
    public void error_load_first_page(int message, final int genre_id) {
        if (errorLayout.getVisibility() == View.GONE) {
            errorLayout.setVisibility(View.VISIBLE);
            mainProgress.setVisibility(View.GONE);

            errorText.setText(getResources().getString(message));
        }

        errorBtnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                errorLayout.setVisibility(View.GONE);
                mainProgress.setVisibility(View.VISIBLE);

                mPresenter.loadFirstPage(genre_id);
            }
        });
    }

    @Override
    public void sucssed_load_next_page(List<Movie> list) {
        adapter.removeLoadingFooter();

        isLoading = false;
        adapter.addAll(list);

        if (currentPage != TOTAL_PAGES) {
            adapter.addLoadingFooter();

        } else isLastPage = true;
    }

    @Override
    public void error_load_next_page(int genre_id, int page, int error_message) {
        adapter.showRetry(true, getResources().getString(error_message), genre_id, page);
    }

    @Override
    public void SetTotalPage(int total_page) {
        this.TOTAL_PAGES = total_page;
    }


    @Override
    public void setupList(List<Movie> items_list, final int category_id) {

        //////////////////////////////
        MyGridAutofitLayoutManager manager = new MyGridAutofitLayoutManager(this, movieList);
        final GridLayoutManager layoutManager;
        layoutManager = new GridLayoutManager(this, manager.getmColumnNumber());
        movieList.setLayoutManager(layoutManager);
//////////
        adapter = new GenredMoviesRecyclerAdapter(items_list, this, manager);
        adapter.setCallback(this);
        movieList.setAdapter(adapter);


        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (adapter.getItemViewType(position)) {
                    case GenredMoviesRecyclerAdapter.VIEW_TYPE_EMPTY:
                        movieList.removeOnScrollListener(scrollListener);
                        return layoutManager.getSpanCount();
                    case GenredMoviesRecyclerAdapter.VIEW_TYPE_NORMAL:
                        return 1;
                    case GenredMoviesRecyclerAdapter.LOADING:
                        return layoutManager.getSpanCount();

                    default:
                        return 1;
                }
            }
        });


        scrollListener = new PaginationScrollListener(layoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage += 1;
                mPresenter.loadNextPage(currentPage, category_id);

            }

            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGES;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        };


        movieList.addOnScrollListener(scrollListener);

        if (items_list != null && items_list.size() > 0) {
            sucssed_load_first_page(items_list);
        } else {
            mainProgress.setVisibility(View.GONE);
        }

    }

    @Override
    public void showMovieDetailActivity(int movie_id) {
        Intent intent = DetailMovieActivity.getStartIntent(this, movie_id, this.getClass().getSimpleName());
        startActivity(intent);
    }


    @Override
    public void onItemClick(int movie_id) {
        showMovieDetailActivity(movie_id);
    }

    @Override
    public void retryPageLoad(int category_id, int page) {

        mPresenter.loadNextPage(page, category_id);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                return true;

        }

        return false;
    }
}
