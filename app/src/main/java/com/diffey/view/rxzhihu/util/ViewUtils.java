package com.diffey.view.rxzhihu.util;

import android.view.View;

/**
 * Created by diff on 2016/2/5.
 */
public class ViewUtils {

    public static void setViewVisibility(View view, boolean visibility) {
        if (view == null) {
            return;
        }
        if (visibility) {
            if (view.getVisibility() != View.VISIBLE) {
                view.setVisibility(View.VISIBLE);
            }
        } else {
            if (view.getVisibility() == View.VISIBLE) {
                view.setVisibility(View.GONE);
            }
        }
    }
}
