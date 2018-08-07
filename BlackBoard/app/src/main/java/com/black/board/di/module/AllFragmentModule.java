package com.black.board.di.module;

import com.black.board.common.di.component.BaseFragmentComponent;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module(subcomponents = {BaseFragmentComponent.class})
public abstract class AllFragmentModule {

  /*  @ContributesAndroidInjector()
    abstract HomeFragment bindHomeFragment();*/

}
