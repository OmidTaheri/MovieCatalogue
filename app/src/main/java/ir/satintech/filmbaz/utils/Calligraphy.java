package ir.satintech.filmbaz.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;


import ir.satintech.filmbaz.R;
import uk.co.chrisjenx.calligraphy.CalligraphyTypefaceSpan;
import uk.co.chrisjenx.calligraphy.TypefaceUtils;



public class Calligraphy {


    public static Spannable wrapInCustomfont(String myText, Context ctx) {
        final Typeface typeface = TypefaceUtils.load(ctx.getAssets(), ctx.getString(R.string.font_path_regular));
        CalligraphyTypefaceSpan typefaceSpan = new CalligraphyTypefaceSpan(typeface);
        SpannableString spannable = new SpannableString(myText);
        spannable.setSpan(typefaceSpan, 0, myText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannable;
    }
}
