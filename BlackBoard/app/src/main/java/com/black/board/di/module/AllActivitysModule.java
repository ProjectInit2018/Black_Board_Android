package com.black.board.di.module;

import com.black.board.ui.main.MainActivity;
import com.black.board.common.di.component.BaseActivityComponent;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * 想要获得注入实例必须在此处声明
 */
@Module(subcomponents = {BaseActivityComponent.class})
public  abstract  class AllActivitysModule {

        //返回类型 既注入实体类 -----对应module 即提供对象容器
        @ContributesAndroidInjector()
        abstract MainActivity bindMain();



}
