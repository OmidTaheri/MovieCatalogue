package ir.satintech.filmbaz.ui.splash;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


import ir.satintech.filmbaz.R;
import ir.satintech.filmbaz.ui.base.BaseActivity;
import ir.satintech.filmbaz.ui.main.MainActivity;
import ir.satintech.filmbaz.utils.Dialog;


public class SplashActivity extends BaseActivity implements SplashMvpView, Dialog.Callback {

    @Inject
    SplashMvpPresenter<SplashMvpView> mPresenter;
    @BindView(R.id.app_name)
    TextView appName;
    @BindView(R.id.main_progress)
    ProgressBar mainProgress;
    @BindView(R.id.errore_text)
    TextView erroreText;
    @BindView(R.id.error_btn_retry)
    TextView errorBtnRetry;
    @BindView(R.id.error_layout)
    ConstraintLayout errorLayout;
    @BindView(R.id.copyright)
    TextView copyright;
    /////////////////////////////////////////////////////

    private static final String TAG = "UpdateCheck";


    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, SplashActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(SplashActivity.this);
        setUp();


    }


    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    protected void setUp() {
        mainProgress.getIndeterminateDrawable().setColorFilter(getResources()
                .getColor(R.color.colorAccent), PorterDuff.Mode.SRC_IN);



    }





    @Override
    public void launchMainActivity() {
        Intent intent = MainActivity.getStartIntent(SplashActivity.this);
        startActivity(intent);
        finish();

    }

    @Override
    public void showErrorLayout() {
        if (errorLayout.getVisibility() == View.GONE) {
            errorLayout.setVisibility(View.VISIBLE);
            mainProgress.setVisibility(View.GONE);

            erroreText.setText(R.string.error_connection);

        }

        errorBtnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                errorLayout.setVisibility(View.GONE);
                mainProgress.setVisibility(View.VISIBLE);

                mPresenter.delayToNextActivity(SplashActivity.this);


            }
        });
    }

    @Override
    public void onPositiveClick() {

    }

    @Override
    public void onNegativeClick() {
        mPresenter.delayToNextActivity(this);
    }


}
