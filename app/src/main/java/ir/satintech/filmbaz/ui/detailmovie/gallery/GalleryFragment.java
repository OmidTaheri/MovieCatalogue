package ir.satintech.filmbaz.ui.detailmovie.gallery;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ir.satintech.filmbaz.R;
import ir.satintech.filmbaz.di.component.ActivityComponent;
import ir.satintech.filmbaz.ui.base.BaseFragment;




public class GalleryFragment extends BaseFragment implements
        GalleryMvpView {

    @Inject
    GalleryMvpPresenter<GalleryMvpView> mPresenter;


    @BindView(R.id.image_list)
    RecyclerView imageList;
    Unbinder unbinder;

    public static GalleryFragment newInstance(List<String> image_urls) {

        GalleryFragment fragment = new GalleryFragment();

        Bundle args = new Bundle();
        args.putStringArrayList("image_urls", (ArrayList<String>) image_urls);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_gallery, container, false);

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
        List<String> list = getArguments().getStringArrayList("image_urls");

        //////////////////////////////
         MyGridAutofitLayoutManager manager = new  MyGridAutofitLayoutManager(getBaseActivity(), imageList);
        final GridLayoutManager layoutManager;
        layoutManager = new GridLayoutManager(getBaseActivity(), manager.getmColumnNumber());
        imageList.setLayoutManager(layoutManager);
        //////////////


        final GalleryAdapter adapter = new GalleryAdapter(list, getBaseActivity(), manager);
        imageList.setAdapter(adapter);



        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (adapter.getItemViewType(position)) {
                    case GalleryAdapter.VIEW_TYPE_EMPTY:
                        return layoutManager.getSpanCount();
                    case GalleryAdapter.VIEW_TYPE_NORMAL:
                        return 1;

                    default:
                        return 1;
                }
            }
        });


    }


    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
        unbinder.unbind();


    }



}