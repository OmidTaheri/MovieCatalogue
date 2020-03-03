

package ir.satintech.filmbaz.ui.main;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.satintech.filmbaz.R;
import ir.satintech.filmbaz.ui.aboutus.AboutUsActivity;
import ir.satintech.filmbaz.ui.base.BaseActivity;
import ir.satintech.filmbaz.ui.search.SearchActivity;
import uk.co.chrisjenx.calligraphy.TypefaceUtils;



public class MainActivity extends BaseActivity implements MainMvpView {

    @Inject
    MainMvpPresenter<MainMvpView> mPresenter;

    @Inject
    VPagerMainAdapter vPagerAdapter;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.vpager)
    ViewPager vpager;

    ///
    boolean doubleBackToExitPressedOnce = false;


    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }


    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;

        showMessage(getString(R.string.double_exit_message));

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        getSupportActionBar().setTitle(R.string.app_name);


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

    }


    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_search:
                mPresenter.showSearchActivity();
                return true;

            case R.id.action_aboutus:
                mPresenter.showAboutUsActivity();
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }


    }

    @Override
    public void showSearchActivity() {
        startActivity(SearchActivity.getStartIntent(this));
    }

    @Override
    public void showAboutUsActivity() {
        startActivity(AboutUsActivity.getStartIntent(this));
    }



}
