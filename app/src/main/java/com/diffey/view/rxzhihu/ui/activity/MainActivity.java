package com.diffey.view.rxzhihu.ui.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;

import com.diffey.view.rxzhihu.R;
import com.diffey.view.rxzhihu.base.SimpleActivity;
import com.diffey.view.rxzhihu.ui.fragment.MainFragment;
import com.diffey.view.rxzhihu.ui.fragment.MainMenuFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends SimpleActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.main_drawerlayout)
    DrawerLayout mainDrawerlayout;

    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        super.initView();
        ButterKnife.bind(this);

        initToolBar();
        addFragment();
    }


    @Override
    protected void initData() {
        super.initData();
    }

    private void initToolBar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        drawerToggle = new ActionBarDrawerToggle(this, mainDrawerlayout, toolbar,
                R.string.app_name, R.string.app_name);
        drawerToggle.syncState();
        mainDrawerlayout.setDrawerListener(drawerToggle);
    }

    private void addFragment() {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.main_content_container, new MainFragment());
        transaction.replace(R.id.main_menu_container, new MainMenuFragment());
        transaction.commit();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mainDrawerlayout.isDrawerOpen(Gravity.LEFT)) {
            mainDrawerlayout.closeDrawers();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }
}
