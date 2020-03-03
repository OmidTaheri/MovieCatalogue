package ir.satintech.filmbaz.ui.detailmovie.summary;

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



public class SummaryFragment extends BaseFragment implements
        SummaryMvpView {

    @Inject
    SummaryMvpPresenter<SummaryMvpView> mPresenter;
    @BindView(R.id.description)
    TextView description;
    Unbinder unbinder;


    public static SummaryFragment newInstance(String plot) {


        SummaryFragment fragment = new SummaryFragment();
        Bundle args = new Bundle();
        args.putString("plot", plot);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_description, container, false);

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
        String plot = getArguments().getString("plot", "");
        description.setText(plot);
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