package com.diffey.view.rxzhihu.ui.fragment;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.diffey.view.rxzhihu.R;
import com.diffey.view.rxzhihu.adapter.MainAdapter;
import com.diffey.view.rxzhihu.api.ZhihuApi;
import com.diffey.view.rxzhihu.api.ZhihuService;
import com.diffey.view.rxzhihu.bean.NewsEntity;
import com.diffey.view.rxzhihu.bean.StoriesEntity;
import com.diffey.view.rxzhihu.db.bean.NewBean;
import com.diffey.view.rxzhihu.db.dao.NewDao;
import com.diffey.view.rxzhihu.util.ViewUtils;
import com.diffey.view.rxzhihu.view.AutoLoadRecylerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import jp.co.recruit_lifestyle.android.widget.WaveSwipeRefreshLayout;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by diff on 2016/2/3.
 */
public class MainFragment extends Fragment implements WaveSwipeRefreshLayout.OnRefreshListener, AutoLoadRecylerView.loadMoreListener {

    @Bind(R.id.main_recycler)
    AutoLoadRecylerView mainRecycler;

    @Bind(R.id.main_wsrefresh)
    WaveSwipeRefreshLayout mainWsrefresh;

    @Bind(R.id.common_loading)
    FrameLayout commonLoading;

    @Bind(R.id.common_error)
    RelativeLayout commonError;

    private Context mActivity;
    private MainAdapter mainAdapter;
    private String curDate;

    private NewDao dao;
    private List<NewBean> beanList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainWsrefresh.setColorSchemeColors(Color.WHITE);
        mainWsrefresh.setWaveColor(ContextCompat.getColor(mActivity, R.color.colorPrimary));
        mainWsrefresh.setOnRefreshListener(this);

        mainRecycler.setHasFixedSize(true);
        mainRecycler.setLayoutManager(new LinearLayoutManager(mActivity));
        mainRecycler.setItemAnimator(new DefaultItemAnimator());

        mainAdapter = new MainAdapter(mActivity);
        mainRecycler.setAdapter(mainAdapter);
        mainRecycler.setLoadMoreListener(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadLastestData();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_refresh) {
            loadLastestData();
            return true;
        }
        return false;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        removeAutoScroller();
        ButterKnife.unbind(this);
    }

    @Override
    public void onRefresh() {
        loadLastestData();
    }

    @Override
    public void onLoadMore() {
        loadBeforeData(curDate);
    }

    private void removeAutoScroller() {
        if (mainRecycler != null) {
            mainRecycler.removeAutoScroller();
        }
    }

    /**
     * 加载最近数据
     */
    private void loadLastestData() {
        ViewUtils.setViewVisibility(commonLoading, true);
        ZhihuApi service = ZhihuService.createZhihuService();
        service.getLastestNews()
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .map(newsEntity -> changeReadState(newsEntity))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(newsEntity1 -> handlerSuccess(newsEntity1, true), throwable -> handlerFailure(true));
    }

    /**
     * 改变阅读状态
     *
     * @param newsEntity
     */
    private NewsEntity changeReadState(NewsEntity newsEntity) {
        dao = new NewDao(mActivity);
        beanList = dao.getAllNewBeans();
        for (StoriesEntity entity : newsEntity.getStories()) {
            for (NewBean bean : beanList) {
                if (entity.getId() == bean.getId()) {
                    entity.setRead(true);
                }
            }
        }
        return newsEntity;
    }

    private void loadBeforeData(String id) {
        ZhihuApi service = ZhihuService.createZhihuService();
        service.getBeforeNews(id)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .map(newsEntity -> changeReadState(newsEntity))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(newsEntity1 -> handlerSuccess(newsEntity1, false), throwable -> handlerFailure(false));
    }

    private void handlerSuccess(NewsEntity entity, boolean isRefresh) {
        ViewUtils.setViewVisibility(commonLoading, false);
        ViewUtils.setViewVisibility(commonError, false);
        ViewUtils.setViewVisibility(mainWsrefresh, true);
        curDate = entity.getDate();
        stopLoadStatus(isRefresh);
        chageListDatas(isRefresh, entity.getStories());
    }

    private void handlerFailure(boolean isRefresh) {
        stopLoadStatus(isRefresh);
        ViewUtils.setViewVisibility(commonError, true);
        ViewUtils.setViewVisibility(commonLoading, false);
        ViewUtils.setViewVisibility(mainWsrefresh, false);
    }


    /**
     * 改变list数据
     *
     * @param isRefresh
     * @param stories
     */
    private void chageListDatas(boolean isRefresh, ArrayList<StoriesEntity> stories) {
        if (mainAdapter == null) {
            return;
        }
        if (isRefresh) {
            mainAdapter.changeData(stories);
        } else {
            mainAdapter.addData(stories);
        }
    }

    /**
     * 停止加载状态
     *
     * @param isRefresh 是否刷新
     */
    private void stopLoadStatus(boolean isRefresh) {
        if (isRefresh && mainWsrefresh != null) {
            mainWsrefresh.setRefreshing(false);
        } else if (mainRecycler != null) {
            mainRecycler.setLoading(false);
        }
    }

}
