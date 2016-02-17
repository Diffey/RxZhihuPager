package com.diffey.view.rxzhihu.ui.activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.diffey.view.rxzhihu.R;
import com.diffey.view.rxzhihu.adapter.StoryAdapter;
import com.diffey.view.rxzhihu.base.SimpleActivity;
import com.diffey.view.rxzhihu.bean.StoriesEntity;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class StoryActivity extends SimpleActivity {

    public static final String PARAM_POS = "param_pos";
    public static final String PARAM_DATA_LIST = "param_data_list";

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.story_viewpager)
    ViewPager storyViewpager;

    private int curPos = -1;
    private ArrayList<StoriesEntity> arrayList;

    @Override
    protected int getContentView() {
        return R.layout.activity_story;
    }

    @Override
    protected void obtainParam(Intent intent) {
        super.obtainParam(intent);
        curPos = intent.getIntExtra(PARAM_POS, -1);
        arrayList = intent.getParcelableArrayListExtra(PARAM_DATA_LIST);
    }

    @Override
    protected void initView() {
        super.initView();
        ButterKnife.bind(this);
        initToolBar();

        storyViewpager.setAdapter(new StoryAdapter(getSupportFragmentManager(), arrayList));
        storyViewpager.setCurrentItem(curPos);
    }

    private void initToolBar() {
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_actionbar_back);
        toolbar.setNavigationOnClickListener(v -> finish());
    }

    @Override
    protected void initData() {
        super.initData();
    }

}
