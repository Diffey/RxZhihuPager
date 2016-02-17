package com.diffey.view.rxzhihu.ui.activity;

import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.diffey.view.rxzhihu.R;
import com.diffey.view.rxzhihu.adapter.TRClientAdapter;
import com.diffey.view.rxzhihu.api.TRApi;
import com.diffey.view.rxzhihu.api.TRService;
import com.diffey.view.rxzhihu.base.SimpleActivity;
import com.diffey.view.rxzhihu.bean.ChatBean;
import com.diffey.view.rxzhihu.bean.TREntity;
import com.diffey.view.rxzhihu.contant.Contant;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TRClientActivity extends SimpleActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.trc_list)
    ListView trcList;

    @Bind(R.id.trc_edit)
    EditText trcEdit;

    @Bind(R.id.trc_btn_send)
    Button trcBtnSend;

    @Bind(R.id.trc_bottom)
    RelativeLayout trcBottom;

    private TRClientAdapter trClientAdapter;
    private TRApi service;

    @Override
    protected int getContentView() {
        return R.layout.activity_trclient;
    }

    @Override
    protected void initView() {
        super.initView();
        ButterKnife.bind(this);

        initToolBar();
        trClientAdapter = new TRClientAdapter(this);
        trcList.setAdapter(trClientAdapter);
    }

    private void initToolBar() {
        toolbar.setTitle("小er");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_actionbar_back);
        toolbar.setNavigationOnClickListener(v -> finish());
    }

    @Override
    protected void initData() {
        super.initData();
        service = TRService.createTRService();
        trClientAdapter.addData(new ChatBean(TRClientAdapter.TYPE_ROBOT, Contant.TRC_ROBOT_REC));
    }

    @Override
    protected void initListener() {
        super.initListener();
        trcBtnSend.setOnClickListener(v -> {
            String str = trcEdit.getText().toString();
            if (TextUtils.isEmpty(str)) {
                return;
            }
            addData(new ChatBean(TRClientAdapter.TYPE_USER, str));
            trcEdit.setText("");
            gainChat(str);
        });
    }

    private void gainChat(String str) {
        Call<TREntity> call = service.getTRResponse(Contant.TRC_KEY, str, Contant.TRC_USER_ID);
        call.enqueue(new Callback<TREntity>() {
            @Override
            public void onResponse(Call<TREntity> call, Response<TREntity> response) {
                TREntity entity = response.body();
                if (entity != null) {
                    String str;
                    if (entity.getCode() == 40004) {
                        str = Contant.TRC_ROBOT_REST;
                    } else {
                        str = entity.getText();
                    }
                    addData(new ChatBean(TRClientAdapter.TYPE_ROBOT, str));
                }
            }

            @Override
            public void onFailure(Call<TREntity> call, Throwable t) {
                addData(new ChatBean(TRClientAdapter.TYPE_ROBOT, Contant.TRC_ROBOT_FAILED));
            }
        });
    }

    private void addData(ChatBean chatBean) {
        trClientAdapter.addData(chatBean);
        trcList.setSelection(trClientAdapter.getCount());
    }
}
