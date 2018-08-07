package com.black.board.common.di.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * description: 提供全局实例
 * author:  周昇辰
 */

@Module
public class AppModule {

    private Context  context;
    public AppModule(Context context){
        this.context=context;
    }

    @Provides
    @Singleton
    Context provideContext (){
        return context;
    }

}
