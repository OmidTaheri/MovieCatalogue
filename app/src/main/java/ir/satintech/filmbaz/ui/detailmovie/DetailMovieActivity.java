package ir.satintech.filmbaz.ui.detailmovie;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.flexbox.FlexboxLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.satintech.filmbaz.R;
import ir.satintech.filmbaz.data.network.model.DetailMovieResponse;
import ir.satintech.filmbaz.ui.base.BaseActivity;
import ir.satintech.filmbaz.ui.main.MainActivity;
import ir.satintech.filmbaz.ui.main.favorite.FavoriteFragment;
import uk.co.chrisjenx.calligraphy.TypefaceUtils;


public class DetailMovieActivity extends BaseActivity implements DetailMovieMvpView, AppBarLayout.OnOffsetChangedListener {


    @Inject
    DetailMovieMvpPresenter<DetailMovieMvpView> mPresenter;
    @BindView(R.id.main_toolbar)
    Toolbar mtoolbar;
    @BindView(R.id.main_appbar)
    AppBarLayout appbar;
    @BindView(R.id.main_collapsing)
    CollapsingToolbarLayout mainCollapsing;


    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.vpager)
    ViewPager vpager;
    ////////
    int movie_id;
    /////
    private static final int PERCENTAGE_TO_ANIMATE_AVATAR = 20;
    @BindView(R.id.favorite_button)
    FloatingActionButton fab;
    @BindView(R.id.main_backdrop)
    ImageView mainBackdrop;
    @BindView(R.id.ratingBar)
    RatingBar ratingBar;
    @BindView(R.id.flexbox_genre)
    FlexboxLayout flexboxGenre;
    @BindView(R.id.time)
    TextView time;


    private boolean mIsAvatarShown = true;
    private int mMaxScrollSize;
    private String ParentTag;


    public static Intent getStartIntent(Context context, int movie_id,  String ParentTag) {
        Intent intent = new Intent(context, DetailMovieActivity.class);
        intent.putExtra("movie_id", movie_id);
        intent.putExtra("ParentTag", ParentTag);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie_bytechnic);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(this);

        setUp();


    }


    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        if (mMaxScrollSize == 0)
            mMaxScrollSize = appBarLayout.getTotalScrollRange();

        int percentage = (Math.abs(i)) * 100 / mMaxScrollSize;

        if (percentage >= PERCENTAGE_TO_ANIMATE_AVATAR && mIsAvatarShown) {
            mIsAvatarShown = false;

            fab.animate()
                    .scaleY(0).scaleX(0)
                    .setDuration(200)
                    .start();

            fab.setClickable(mIsAvatarShown);
        }

        if (percentage <= PERCENTAGE_TO_ANIMATE_AVATAR && !mIsAvatarShown) {
            mIsAvatarShown = true;

            fab.animate()
                    .scaleY(1).scaleX(1)
                    .start();

            fab.setClickable(mIsAvatarShown);
        }
    }

    @Override
    protected void setUp() {

        setSupportActionBar(mtoolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mainCollapsing.setExpandedTitleTypeface(Typeface.createFromAsset(getAssets(), "fonts/roboto_regular.ttf"));
        mainCollapsing.setCollapsedTitleTypeface(Typeface.createFromAsset(getAssets(), "fonts/roboto_regular.ttf"));

        appbar.addOnOffsetChangedListener(this);
        mMaxScrollSize = appbar.getTotalScrollRange();


        movie_id = getIntent().getExtras().getInt("movie_id");
        ParentTag = getIntent().getExtras().getString("ParentTag");

        if (mPresenter.existInFavorite(movie_id)) {
            fab.setImageResource(R.drawable.heart);
        } else {

            fab.setImageResource(R.drawable.heart_outline);
        }

        mPresenter.getDetailMovie(movie_id);

    }


    @Override
    public void showDetailMovie(final DetailMovieResponse detailMovie) {


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mPresenter.existInFavorite(movie_id)) {
                    mPresenter.deleteFromFavorite(detailMovie.getId());
                    fab.setImageResource(R.drawable.heart_outline);

                } else {
                    mPresenter.addToFavorite(detailMovie);
                    fab.setImageResource(R.drawable.heart);
                }


            }
        });


        mainCollapsing.setTitle(detailMovie.getTitle());

        Glide.with(DetailMovieActivity.this)
                .load(detailMovie.getPoster())
                .apply(new RequestOptions().placeholder(R.drawable.film_placeholder))
                .into(mainBackdrop);

        System.out.println("detailMovie.getPoster()"+detailMovie.getPoster());

        ratingBar.setRating(Float.parseFloat(detailMovie.getImdbRating()));



        time.setText(detailMovie.getRuntime().replace("min", "دقیقه"));


        VPagerDetailAdapter vPagerAdapter = new VPagerDetailAdapter(getSupportFragmentManager(), detailMovie);

        vpager.setAdapter(vPagerAdapter);
        tablayout.setupWithViewPager(vpager);

        vpager.setCurrentItem(2);

        Typeface typeface = TypefaceUtils.load(getAssets(), getString(R.string.font_path_regular));

        for (int i = 0; i < tablayout.getTabCount(); i++) {
            //noinspection ConstantConditions
            TextView tv = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab_item_main, null);
            tv.setText(vpager.getAdapter().getPageTitle(i));
            tv.setTypeface(typeface);
            tablayout.getTabAt(i).setCustomView(tv);

        }


        for (int i = 0; i < detailMovie.getGenres().size(); i++) {
            View subchild = getLayoutInflater().inflate(R.layout.single_chip, null);
            final TextView tv = ((TextView) subchild.findViewById(R.id.txt_title));
            tv.setText(detailMovie.getGenres().get(i));
            flexboxGenre.addView(subchild);
        }
    }


    @Override
    public void error_load_data(int message) {
        onError(message);
    }


    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
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
    public void onBackPressed() {

        if (ParentTag.equals(FavoriteFragment.class.getSimpleName())) {

            startActivity(MainActivity.getStartIntent(this));
            finish();

        } else {
            super.onBackPressed();
        }
    }

}
