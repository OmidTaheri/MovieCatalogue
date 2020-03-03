package ir.satintech.filmbaz.ui.genredmovies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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
            return 8;
        } else if (getmColumnNumber() == 3) {
            return 16;
        }
        return 0;
    }

    public void setColumnWidth() {

        int totalSpace;
        int recyclerViewWidth = recyclerView.getWidth();

        if (recyclerViewWidth == 0) {
            recyclerViewWidth = ScreenUtils.getScreenWidth(context);
        }

        Log.i("ecyclerView.getWidth()", "" + recyclerViewWidth);

        totalSpace = recyclerViewWidth - recyclerView.getPaddingRight() - recyclerView.getPaddingLeft();


        Log.i("totalSpace1", "" + totalSpace);


        totalSpace = (int) ViewUtils.pxToDp(totalSpace);

        Log.i("totalSpace2", "" + totalSpace);


        if (totalSpace <= 320) {

            mColumnNumber = 2;


        } else if (totalSpace > 320 && totalSpace <= 360) {
            mColumnNumber = 2;


        } else if (totalSpace > 360 && totalSpace <= 600) {

            mColumnNumber = 3;


        } else if (totalSpace > 600) {

            mColumnNumber = 3;


        }

        Log.i("mColumnNumber inlayout", "" + mColumnNumber);


        totalSpace = totalSpace - getTotalPaddingItems();
        Log.i("totalSpace3", "" + totalSpace);

        if (totalSpace <= 320) {

            mColumnWidth = (int) totalSpace / mColumnNumber;

        } else if (totalSpace > 320 && totalSpace <= 360) {

            mColumnWidth = (int) totalSpace / mColumnNumber;

        } else if (totalSpace > 360 && totalSpace <= 600) {

            mColumnWidth = (int) totalSpace / mColumnNumber;

        } else if (totalSpace > 600) {

            mColumnWidth = (int) totalSpace / mColumnNumber;
        }


        Log.i("mColumnWidth inlayout", "" + mColumnWidth);


    }


    public int getmColumnWidth() {
        return mColumnWidth;
    }


    public int getmColumnNumber() {
        return mColumnNumber;
    }
}