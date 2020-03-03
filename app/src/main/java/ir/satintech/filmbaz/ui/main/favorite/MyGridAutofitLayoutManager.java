package ir.satintech.filmbaz.ui.main.favorite;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import ir.satintech.filmbaz.R;
import ir.satintech.filmbaz.utils.ScreenUtils;
import ir.satintech.filmbaz.utils.ViewUtils;




public class MyGridAutofitLayoutManager {
    private int mColumnWidth;

    private int mColumnNumber;

    Context context;

    RecyclerView recyclerView;


    public MyGridAutofitLayoutManager(Context context, RecyclerView recyclerView) {
        this.context = context;

        this.recyclerView = recyclerView;

        setColumnWidth();
    }

    public int getTotalPaddingItems() {

        if (getmColumnNumber() == 2) {
            return (int) (8 + (context.getResources().getDimension(R.dimen.material_component_cards_space_between_cards)* 2));
        } else if (getmColumnNumber() == 3) {
            return (int) (16 +(context.getResources().getDimension(R.dimen.material_component_cards_space_between_cards)* 2));
        }
        return 0;
    }

    public void setColumnWidth() {

        int totalSpace;
        int recyclerViewWidth = recyclerView.getWidth();

        if (recyclerViewWidth == 0) {
            recyclerViewWidth = ScreenUtils.getScreenWidth(context);
        }



        totalSpace = recyclerViewWidth - recyclerView.getPaddingRight() - recyclerView.getPaddingLeft();





        totalSpace = (int) ViewUtils.pxToDp(totalSpace);




        if (totalSpace <= 320) {

            mColumnNumber = 2;


        } else if (totalSpace > 320 && totalSpace <= 360) {
            mColumnNumber = 2;


        } else if (totalSpace > 360 && totalSpace <= 600) {

            mColumnNumber = 3;


        } else if (totalSpace > 600) {

            mColumnNumber = 3;


        }




        totalSpace = totalSpace - getTotalPaddingItems();


        if (totalSpace <= 320) {

            mColumnWidth = (int) totalSpace / mColumnNumber;

        } else if (totalSpace > 320 && totalSpace <= 360) {

            mColumnWidth = (int) totalSpace / mColumnNumber;

        } else if (totalSpace > 360 && totalSpace <= 600) {

            mColumnWidth = (int) totalSpace / mColumnNumber;

        } else if (totalSpace > 600) {

            mColumnWidth = (int) totalSpace / mColumnNumber;
        }





    }


    public int getmColumnWidth() {
        return mColumnWidth;
    }


    public int getmColumnNumber() {
        return mColumnNumber;
    }
}