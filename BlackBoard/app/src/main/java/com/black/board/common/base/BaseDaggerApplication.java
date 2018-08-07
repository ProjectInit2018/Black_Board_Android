package com.black.board.common.base;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;


import com.black.board.common.di.component.DaggerAppComponent;
import com.black.board.common.di.module.AppModule;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * description: 全局注入
 * author:  周昇辰
 */

public class BaseDaggerApplication extends Application implements HasActivityInjector,HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;
    @Inject
    DispatchingAndroidInjector<Fragment> fdispatchingAndroidInjector;


    @Override
    public void onCreate() {
        super.onCreate();
     DaggerAppComponent
                   .builder()
                   .appModule(new AppModule(this))
                   .build()
                   .inject(this);

    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }


    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fdispatchingAndroidInjector;
    }
}
