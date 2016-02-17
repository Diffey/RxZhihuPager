package com.diffey.view.rxzhihu.util;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by diff on 2016/2/5.
 */
public class AppInfoUtils {

    /**
     * 获取版本名
     *
     * @param activity
     * @return
     */
    public static String getVersionName(Activity activity) {
        PackageManager packageManager = activity.getPackageManager();
        PackageInfo packInfo;
        try {
            packInfo = packageManager.getPackageInfo(activity.getPackageName(), 0);
            return packInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "1.0";
        }
    }
}
