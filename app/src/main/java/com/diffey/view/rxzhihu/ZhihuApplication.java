package com.diffey.view.rxzhihu;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.stetho.Stetho;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;


/**
 * Created by diff on 2016/2/2.
 */
public class ZhihuApplication extends Application {
    private static final String TAG = "difflog";

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.init(TAG);
        Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build());
        LeakCanary.install(this);
        Fresco.initialize(this);
    }
}
