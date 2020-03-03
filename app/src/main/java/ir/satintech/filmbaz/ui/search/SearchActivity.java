package ir.satintech.filmbaz.ui.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.satintech.filmbaz.R;
import ir.satintech.filmbaz.data.network.model.Movie;
import ir.satintech.filmbaz.ui.base.BaseActivity;
import ir.satintech.filmbaz.ui.detailmovie.DetailMovieActivity;


public class SearchActivity extends BaseActivity implements SearchMvpView, SearchRecyclerAdapter.Callback, MaterialSearchBar.OnSearchActionListener {

    @Inject
    SearchMvpPresenter<SearchMvpView> mPresenter;


    PaginationScrollListener scrollListener;

    private static final int PAGE_START = 1;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int TOTAL_PAGES = 0;
    private int currentPage = PAGE_START;

    SearchRecyclerAdapter adapter;
////

    @BindView(R.id.searchView)
    MaterialSearchBar searchView;
    @BindView(R.id.search_list)
    RecyclerView searchList;
    @BindView(R.id.main_progress)
    ProgressBar mainProgress;
    @BindView(R.id.error_text)
    TextView errorText;
    @BindView(R.id.error_btn_retry)
    TextView errorBtnRetry;
    @BindView(R.id.error_layout)
    ConstraintLayout errorLayout;

    @BindView(R.id.search_button)
    Button searchButton;

    //
    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, SearchActivity.class);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_search2);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(this);

        setUp();

    }

    @Override
    protected void setUp() {


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doMySearch(searchView.getText());

            }
        });


        searchView.setOnSearchActionListener(this);

        searchView.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                 if (!searchView.getText().equals("")) {
                    searchButton.setVisibility(View.VISIBLE);

                } else {
                    searchButton.setVisibility(View.GONE);

                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

        });

    }


    private void doMySearch(String query) {
        errorLayout.setVisibility(View.GONE);
        hideKeyboard();

        mPresenter.searchFirstPage(query);
    }


    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }


    @Override
    public void sucssed_load_first_page(List<Movie> list) {
        mainProgress.setVisibility(View.GONE);



        if (currentPage <= TOTAL_PAGES && TOTAL_PAGES != 1) {
            adapter.addLoadingFooter();
        } else isLastPage = true;
    }

    @Override
    public void error_load_first_page(int message, final String query) {
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

                mPresenter.searchFirstPage(query);
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
    public void error_load_next_page(String query, int page, int error_message) {
        adapter.showRetry(true, getResources().getString(error_message), query, page);
    }

    @Override
    public void SetTotalPage(int total_page) {
        this.TOTAL_PAGES = total_page;
    }


    @Override
    public void setupListofSearch(List<Movie> items_list, final String query) {


        //////////////////////////////
        MyGridAutofitLayoutManager manager = new MyGridAutofitLayoutManager(this, searchList);
        final GridLayoutManager layoutManager;
        layoutManager = new GridLayoutManager(this, manager.getmColumnNumber());
        searchList.setLayoutManager(layoutManager);
//////////


        adapter = new SearchRecyclerAdapter(items_list, SearchActivity.this, manager);
        adapter.setCallback(this);
        searchList.setAdapter(adapter);


        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (adapter.getItemViewType(position)) {
                    case SearchRecyclerAdapter.VIEW_TYPE_EMPTY:
                        searchList.removeOnScrollListener(scrollListener);
                        return layoutManager.getSpanCount();
                    case SearchRecyclerAdapter.VIEW_TYPE_NORMAL:
                        return 1;
                    case SearchRecyclerAdapter.LOADING:
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
                mPresenter.searchNextPage(currentPage, query);

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


        searchList.addOnScrollListener(scrollListener);

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
    public void retryPageLoad(String query, int page) {

        mPresenter.searchNextPage(page, query);
    }

    @Override
    public void onSearchStateChanged(boolean enabled) {

    }

    @Override
    public void onSearchConfirmed(CharSequence text) {
        //startSearch(text.toString(), true, null, true);
        doMySearch(text.toString());
    }

    @Override
    public void onButtonClicked(int buttonCode) {

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

    @Override
    public void visibility_progressBar(boolean show) {
        if (show) {
            mainProgress.setVisibility(View.VISIBLE);
        } else {

            mainProgress.setVisibility(View.GONE);
        }
    }
}
