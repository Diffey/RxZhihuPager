package com.diffey.view.rxzhihu.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.diffey.view.rxzhihu.bean.StoriesEntity;
import com.diffey.view.rxzhihu.ui.activity.SettingActivity;
import com.diffey.view.rxzhihu.ui.activity.StoryActivity;
import com.diffey.view.rxzhihu.ui.activity.TRClientActivity;

import java.util.ArrayList;

/**
 * Created by diff on 2016/2/4.
 */
public class IntentUtils {

    public static void toStoryActivity(Context context, int pos, ArrayList<StoriesEntity> storiesEntityList) {
        Intent intent = new Intent(context, StoryActivity.class);
        intent.putExtra(StoryActivity.PARAM_POS, pos);
        intent.putParcelableArrayListExtra(StoryActivity.PARAM_DATA_LIST, storiesEntityList);
        context.startActivity(intent);
    }

    public static void toTRClientActivity(Context context) {
        context.startActivity(new Intent(context, TRClientActivity.class));
    }

    public static void toSettingActivity(Context context) {
        context.startActivity(new Intent(context, SettingActivity.class));
    }

    public static void toBrowserView(Context context, String url) {
        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }
}
