package ir.satintech.filmbaz.ui.genredmovies;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import ak.sh.ay.oblique.ObliqueView;
import butterknife.BindView;
import butterknife.ButterKnife;
import ir.satintech.filmbaz.R;
import ir.satintech.filmbaz.data.network.model.Movie;
import ir.satintech.filmbaz.ui.base.BaseViewHolder;
import ir.satintech.filmbaz.utils.ViewUtils;


public class GenredMoviesRecyclerAdapter extends RecyclerView.Adapter<BaseViewHolder> {


    public static final int VIEW_TYPE_EMPTY = 0;
    public static final int VIEW_TYPE_NORMAL = 1;
    public static final int LOADING = 2;


    private List<Movie> list;
    private Context mContext;
    private Callback mCallback;


    private boolean isLoadingAdded = false;
    private boolean retryPageLoad = false;
    private String errorMsg;


    int errored_category_id;
    int errored_page;
    MyGridAutofitLayoutManager  Manager;

    public GenredMoviesRecyclerAdapter(List<Movie> list, Context mContext, MyGridAutofitLayoutManager Manager) {
        this.list = list;
        this.mContext = mContext;
        this.Manager = Manager;
    }

    public void setCallback(Callback callback) {
        mCallback = callback;
    }


    private Context getContext() {
        return mContext;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        switch (viewType) {

            case VIEW_TYPE_NORMAL:

                View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_vitrin, parent, false);

                             CardView Card = layout.findViewById(R.id.parent);

                Card.setLayoutParams(new CardView.LayoutParams(
                        (int)ViewUtils.dpToPx(Manager.getmColumnWidth()) , CardView.LayoutParams.WRAP_CONTENT));


                return new ViewHolder(layout);


            case LOADING:
                return new LoadingViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_progress, parent, false));


            case VIEW_TYPE_EMPTY:
            default:
                return new EmptyViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_empty_view, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }


    @Override
    public int getItemCount() {


        if (list != null && list.size() != 0) {
            return list.size();
        } else {
            return 1;
        }
    }


    public void addItems(List<Movie> repoList) {
        list.addAll(repoList);
        notifyDataSetChanged();
    }

    public interface Callback {

        void onItemClick(int movie_id);

        void retryPageLoad(int genre_id, int page);
    }

    @Override
    public int getItemViewType(int position) {
        if (list != null && list.size() > 0) {
            return (position == list.size() - 1 && isLoadingAdded) ? LOADING : VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }
      /*
   Helpers
   _________________________________________________________________________________________________
    */

    public void add(Movie mc) {
        list.add(mc);
        notifyItemInserted(list.size() - 1);
    }

    public void addAll(List<Movie> List) {
        for (Movie item : List) {
            add(item);
        }
    }

    public void remove(Movie item) {
        int position = list.indexOf(item);
        if (position > -1) {
            list.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        isLoadingAdded = false;
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }


    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new Movie());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = list.size() - 1;
        Movie item = getItem(position);

        if (item != null) {
            list.remove(position);
            notifyItemRemoved(position);
        }
    }

    public Movie getItem(int position) {
        return list.get(position);
    }

    public void showRetry(boolean show, @Nullable String errorMsg, int category_id, int page) {

        errored_category_id = category_id;
        errored_page = page;


        retryPageLoad = show;
        notifyItemChanged(list.size() - 1);

        if (errorMsg != null) this.errorMsg = errorMsg;
    }


    public class ViewHolder extends BaseViewHolder {

        @BindView(R.id.obliqueView)
        ObliqueView obliqueView;
        @BindView(R.id.title_movie)
        TextView titleMovie;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        protected void clear() {

        }

        @Override
        public void onBind(int position) {
            super.onBind(position);
            final Movie item = list.get(position);

            titleMovie.setText(item.getTitle());

            Glide.with(itemView.getContext())
                    .load(item.getPoster())
                    .apply(new RequestOptions().placeholder(R.drawable.film_placeholder))
                    .into(obliqueView);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mCallback != null)
                        mCallback.onItemClick(item.getId());


                }
            });
        }
    }

    public class EmptyViewHolder extends BaseViewHolder {
        @BindView(R.id.message)
        TextView message;

        public EmptyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        protected void clear() {

        }

        @Override
        public void onBind(int position) {
            super.onBind(position);
            message.setText("موردی وجود ندارد");
        }

    }


    public class LoadingViewHolder extends BaseViewHolder implements View.OnClickListener {


        @BindView(R.id.loadmore_progress)
        ProgressBar loadmoreProgress;
        @BindView(R.id.loadmore_retry)
        Button loadmoreRetry;
        @BindView(R.id.loadmore_errortxt)
        TextView loadmoreErrortxt;
        @BindView(R.id.loadmore_errorlayout)
        ConstraintLayout loadmoreErrorlayout;

        public LoadingViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            loadmoreRetry.setOnClickListener(this);
        }

        @Override
        protected void clear() {

        }

        public void onBind(int position) {
            super.onBind(position);

            if (retryPageLoad) {
                loadmoreErrorlayout.setVisibility(View.VISIBLE);
                loadmoreProgress.setVisibility(View.GONE);

                if (errorMsg.length() != 0) {
                    loadmoreErrortxt.setText(errorMsg);
                } else {
                    loadmoreErrortxt.setText(R.string.error_load_page);
                }


            } else {
                loadmoreErrorlayout.setVisibility(View.GONE);
                loadmoreProgress.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.loadmore_retry:

                    mCallback.retryPageLoad(errored_category_id, errored_page);
                    showRetry(false, null, 0, 0);
                    break;
            }
        }
    }
}