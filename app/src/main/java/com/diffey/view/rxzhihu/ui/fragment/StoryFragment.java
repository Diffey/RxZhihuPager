package com.diffey.view.rxzhihu.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.diffey.view.rxzhihu.R;
import com.diffey.view.rxzhihu.api.ZhihuApi;
import com.diffey.view.rxzhihu.api.ZhihuService;
import com.diffey.view.rxzhihu.util.HtmlUtils;
import com.diffey.view.rxzhihu.util.ViewUtils;
import com.orhanobut.logger.Logger;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by diff on 2016/2/5.
 */
public class StoryFragment extends Fragment {

    public static final String PARAM_ID = "param_id";

    @Bind(R.id.story_web)
    WebView storyWeb;

    @Bind(R.id.common_error)
    RelativeLayout commonError;

    @Bind(R.id.common_loading)
    FrameLayout commonLoading;

    private int curId = -1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        curId = getArguments().getInt(PARAM_ID, -1);
        Logger.i("curId:" + curId);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_story, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        storyWeb.setVerticalScrollBarEnabled(false);
        storyWeb.getSettings().setDefaultTextEncodingName("UTF-8");

        loadData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void loadData() {
        ZhihuApi service = ZhihuService.createZhihuService();
        service.getNewsDetails(curId).observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .map(storyDetailsEntity -> HtmlUtils.structHtml(storyDetailsEntity.getBody(), storyDetailsEntity.getCss()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> webShowData(s), throwable -> handlerFailure());

    }

    private void handlerFailure() {
        ViewUtils.setViewVisibility(commonError, true);
        ViewUtils.setViewVisibility(commonLoading, false);
        ViewUtils.setViewVisibility(storyWeb, false);
    }

    private void webShowData(String htl) {
        if (storyWeb != null) {
            ViewUtils.setViewVisibility(commonLoading, false);
            ViewUtils.setViewVisibility(commonError, false);
            storyWeb.loadData(htl, "text/html; charset=UTF-8", null);
        }
    }

}
