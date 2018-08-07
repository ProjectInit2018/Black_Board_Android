package com.black.board.di.module;


import com.black.board.di.api.AppInitService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * description: 注入代理类对象
 * author:  周昇辰
 */

@Module
public class ApiServieModule {

    /**
     * 将接口代理类 分类
     * @param retrofit
     * @return
     */
    //注入代理类对象
    @Singleton
    @Provides
    AppInitService provideAppInitService(Retrofit retrofit){
        return   retrofit.create(AppInitService.class);
    }


}
