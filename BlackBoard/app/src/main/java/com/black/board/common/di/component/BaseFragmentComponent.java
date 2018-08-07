package com.black.board.common.di.component;


import android.support.v4.app.Fragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;


@Subcomponent(modules = AndroidInjectionModule.class)
public interface BaseFragmentComponent extends AndroidInjector<Fragment> {

    //所有继承BaseFragment的fragment共享此subComponent
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<Fragment>
    {

    }
}
