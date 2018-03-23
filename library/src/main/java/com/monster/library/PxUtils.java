package com.monster.library;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

/**
 * <pre>
 *     @author : monster
 *     time   : 2018/03/23
 *     desc   :
 *     version: 1.0
 * </pre>
 */


public class PxUtils {

    public static int dp2px(Context context, int dp) {
        Resources r = context.getResources();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
    }
}
