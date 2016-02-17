package com.diffey.view.rxzhihu.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.v4.content.ContextCompat;

import com.afollestad.materialdialogs.MaterialDialog;
import com.diffey.view.rxzhihu.R;
import com.diffey.view.rxzhihu.contant.UrlContant;
import com.diffey.view.rxzhihu.util.AppInfoUtils;
import com.diffey.view.rxzhihu.util.IntentUtils;

/**
 * Created by diff on 2016/2/5.
 */
public class SettingFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener {
    private static final String APP_VERSION = "app_version";
    private static final String ABOUT_DEV = "about_dev";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        Preference appVersion = findPreference(APP_VERSION);
        appVersion.setTitle("版本号:" + AppInfoUtils.getVersionName(getActivity()));

        Preference aboutDev = findPreference(ABOUT_DEV);
        aboutDev.setOnPreferenceClickListener(this);
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        String key = preference.getKey();
        if (ABOUT_DEV.equals(key)) {
            showDialog();
        }
        return true;
    }

    private void showDialog() {
        MaterialDialog dialog = new MaterialDialog.Builder(getActivity())
                .title(getString(R.string.app_name))
                .backgroundColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary))
                .contentColor(Color.WHITE)
                .positiveColor(Color.WHITE)
                .negativeColor(Color.WHITE)
                .neutralColor(Color.WHITE)
                .titleColor(Color.WHITE)
                .content(R.string.person_info)
                .positiveText("GitHub")
                .negativeText("简书")
                .onPositive((dialog1, which) -> {
                    IntentUtils.toBrowserView(getActivity(), UrlContant.URL_DEV_GITHUB);
                    dialog1.dismiss();
                })
                .onNegative((dialog1, which) -> IntentUtils.toBrowserView(getActivity(), UrlContant.URL_DEV_JIANSHU))
                .build();
        dialog.show();
    }
}
