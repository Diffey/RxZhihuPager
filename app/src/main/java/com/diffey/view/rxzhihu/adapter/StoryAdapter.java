package com.diffey.view.rxzhihu.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.diffey.view.rxzhihu.bean.StoriesEntity;
import com.diffey.view.rxzhihu.ui.fragment.StoryFragment;

import java.util.ArrayList;

/**
 * Created by diff on 2016/2/5.
 */
public class StoryAdapter extends FragmentPagerAdapter {
    private ArrayList<StoriesEntity> storiesEntities;

    public StoryAdapter(FragmentManager fm, ArrayList<StoriesEntity> storiesEntities) {
        super(fm);
        this.storiesEntities = storiesEntities;
    }

    @Override
    public Fragment getItem(int position) {
        StoryFragment storyFragment = new StoryFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(StoryFragment.PARAM_ID, storiesEntities.get(position).getId());
        storyFragment.setArguments(bundle);
        return storyFragment;
    }

    @Override
    public int getCount() {
        return storiesEntities == null ? 0 : storiesEntities.size();
    }
}
