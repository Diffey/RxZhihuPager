package com.diffey.view.rxzhihu.ui.activity;

import android.support.v7.widget.Toolbar;

import com.diffey.view.rxzhihu.R;
import com.diffey.view.rxzhihu.base.SimpleActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SettingActivity extends SimpleActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected int getContentView() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
        super.initView();
        ButterKnife.bind(this);
        initToolBar();
    }

    private void initToolBar() {
        toolbar.setTitle("设置");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_actionbar_back);
        toolbar.setNavigationOnClickListener(v -> finish());
    }
}
