package ir.satintech.filmbaz.ui.detailmovie.gallery;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.satintech.filmbaz.R;
import ir.satintech.filmbaz.ui.base.BaseViewHolder;
import ir.satintech.filmbaz.utils.ViewUtils;


public class GalleryAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE_EMPTY = 0;
    public static final int VIEW_TYPE_NORMAL = 1;


    private List<String> list;
    private Context mContext;
    MyGridAutofitLayoutManager Manager;

    public GalleryAdapter(List<String> list, Context mContext, MyGridAutofitLayoutManager Manager) {
        this.list = list;
        this.mContext = mContext;
        this.Manager = Manager;
    }


    private Context getContext() {
        return mContext;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        switch (viewType) {

            case VIEW_TYPE_NORMAL:

                View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_gallery, parent, false);

                ImageView imageview = layout.findViewById(R.id.image);

                android.view.ViewGroup.LayoutParams layoutParams = imageview.getLayoutParams();
                layoutParams.width = (int) ViewUtils.dpToPx(Manager.getmColumnWidth());
                layoutParams.height = (int) ViewUtils.dpToPx(Manager.getmColumnWidth());
                imageview.setLayoutParams(layoutParams);


                return new ViewHolder(layout);

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


    public void addItems(List<String> repoList) {
        list.addAll(repoList);
        notifyDataSetChanged();
    }


    public class ViewHolder extends BaseViewHolder {

        @BindView(R.id.image)
        ImageView image;

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

            final String item = list.get(position);

            int width = (int) ViewUtils.dpToPx(Manager.getmColumnWidth());

            RequestOptions option = new RequestOptions();
            option.placeholder(R.drawable.image_placeholder);


            Glide.with(itemView.getContext())
                    .load(item)
                    .apply(option)
                    .into(image);


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

}
