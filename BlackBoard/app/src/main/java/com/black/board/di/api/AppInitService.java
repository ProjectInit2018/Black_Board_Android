package com.black.board.di.api;

import com.black.board.common.base.BaseResp;
import com.black.board.entity.CxResp;
import com.black.board.utils.AppConfig;


import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * description: app 初始化相关调用接口
 * author:  周昇辰
 */


public interface AppInitService {

    @GET(AppConfig.URL_GET)
    Observable<BaseResp<List<CxResp>>> getData(@Query("vc") int vc);
  //  "http://baobab.kaiyanapp.com/api/v2/categories?udid=26868b32e808498db32fd51fb422d00175e179df&vc=83"
}
