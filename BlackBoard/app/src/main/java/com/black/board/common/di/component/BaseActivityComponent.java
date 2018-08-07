package com.black.board.common.di.component;

import android.app.Activity;

import dagger.Subcomponent;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;


@Subcomponent(modules = {AndroidInjectionModule.class})
public interface BaseActivityComponent extends AndroidInjector<Activity> {

    //每一个继承baseactivity的activity都共享一个SubComponent
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<Activity>
    {

    }

}
