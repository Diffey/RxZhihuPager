package com.diffey.view.rxzhihu.ui.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.diffey.view.rxzhihu.R;
import com.diffey.view.rxzhihu.adapter.MainMenuAdapter;
import com.diffey.view.rxzhihu.bean.MenuItem;
import com.diffey.view.rxzhihu.ui.activity.SettingActivity;
import com.diffey.view.rxzhihu.util.IntentUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by diff on 2016/2/3.
 */
public class MainMenuFragment extends Fragment {

    @Bind(R.id.main_menu_list)
    ListView mainMenuList;

    @Bind(R.id.main_menu_ll_setting)
    LinearLayout mainMenuLlSetting;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_menu, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainMenuList.setAdapter(new MainMenuAdapter(getActivity(), getMenuList()));
        mainMenuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        IntentUtils.toTRClientActivity(getActivity());
                        break;
                    default:
                        break;
                }
            }
        });
        mainMenuLlSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), SettingActivity.class));
            }
        });
    }

    private List<MenuItem> getMenuList() {
        List<MenuItem> list = new ArrayList<>();
        list.add(new MenuItem(R.drawable.ic_movie_white_24dp, "聊一会"));
        return list;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
