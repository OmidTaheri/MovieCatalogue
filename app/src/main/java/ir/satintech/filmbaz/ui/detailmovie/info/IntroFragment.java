package ir.satintech.filmbaz.ui.detailmovie.info;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ir.satintech.filmbaz.R;
import ir.satintech.filmbaz.di.component.ActivityComponent;
import ir.satintech.filmbaz.ui.base.BaseFragment;


public class IntroFragment extends BaseFragment implements
        IntroMvpView {

    @Inject
    IntroMvpPresenter<IntroMvpView> mPresenter;
    @BindView(R.id.director)
    TextView director;
    @BindView(R.id.writer)
    TextView writer;
    @BindView(R.id.year)
    TextView year;
    @BindView(R.id.country)
    TextView country;
    @BindView(R.id.actors)
    TextView actors;
    @BindView(R.id.awards)
    TextView awards;
    Unbinder unbinder;


    public static IntroFragment newInstance(String director, String writer, String year, String country, String actors, String awards) {


        IntroFragment fragment = new IntroFragment();

        Bundle args = new Bundle();
        args.putString("director", director);
        args.putString("writer", writer);
        args.putString("year", year);
        args.putString("country", country);
        args.putString("actors", actors);
        args.putString("awards", awards);


        fragment.setArguments(args);
        return fragment;


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_intro, container, false);

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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {


        String directorText = getArguments().getString("director", "");
        String writerText = getArguments().getString("writer", "");
        String yearText = getArguments().getString("year", "");
        String countryText = getArguments().getString("country", "");
        String actorsText = getArguments().getString("actors", "");
        String awardsText = getArguments().getString("awards", "");


        director.setText(directorText);
        writer.setText(writerText);
        year.setText(yearText);
        country.setText(countryText);
        actors.setText(actorsText);
        awards.setText(awardsText);
    }


    @Override
    protected void setUp(View view) {


    }


    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
        unbinder.unbind();


    }



}