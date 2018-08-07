package com.black.board.common.base;

import android.content.Context;
import android.os.Build;
import android.support.multidex.MultiDex;


/**
 * description: 初始化应用基础信息
 * author:  周昇辰
 */


public class BaseApplication extends BaseDaggerApplication {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //Dex优化
        if ( Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {//<5.0的系统默认对dex进行oat优化
            MultiDex.install(this);
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }
}
