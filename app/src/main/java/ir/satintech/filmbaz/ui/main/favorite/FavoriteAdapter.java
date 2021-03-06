package ir.satintech.filmbaz.ui.main.favorite;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import ak.sh.ay.oblique.ObliqueView;
import butterknife.BindView;
import butterknife.ButterKnife;
import ir.satintech.filmbaz.R;
import ir.satintech.filmbaz.data.network.model.DetailMovieResponse;
import ir.satintech.filmbaz.ui.base.BaseViewHolder;
import ir.satintech.filmbaz.utils.ViewUtils;


public class FavoriteAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE_EMPTY = 0;
    public static final int VIEW_TYPE_NORMAL = 1;


    private List<DetailMovieResponse> list;
    private Context mContext;
    private Callback mCallback;
    MyGridAutofitLayoutManager Manager;

    public FavoriteAdapter(List<DetailMovieResponse> list, Context mContext,  MyGridAutofitLayoutManager Manager) {
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

                View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_favorite, parent, false);

                CardView Card = layout.findViewById(R.id.parent);

                Card.setLayoutParams(new CardView.LayoutParams(
                        (int) ViewUtils.dpToPx(Manager.getmColumnWidth()), CardView.LayoutParams.WRAP_CONTENT));


                return new ViewHolder(layout);


            case VIEW_TYPE_EMPTY:
            default:
                return new EmptyViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_empty_view_favorite, parent, false));
        }

    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }


    @Override
    public int getItemCount() {

        if (list != null && list.size() > 0) {
            return list.size();
        } else {
            return 1;
        }

    }


    @Override
    public int getItemViewType(int position) {
        if (list != null && list.size() > 0) {
            return VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }


    public void addItems(List<DetailMovieResponse> repoList) {
        list.addAll(repoList);
        notifyDataSetChanged();
    }

    public interface Callback {
        void onItemClick(int movie_id);
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

            final DetailMovieResponse item = list.get(position);

            titleMovie.setText(item.getTitle());


            Glide.with(itemView.getContext())
                    .load( item.getPoster())
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
            message.setText("آیتم برگزیده وجود ندارد");
        }

    }

}
