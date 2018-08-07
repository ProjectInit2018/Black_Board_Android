package com.black.board.common.di.module;

import android.util.Log;

import com.black.board.common.base.ApiServiceKey;
import com.black.board.common.retrofit.OkHttpHeadInterceptor;
import com.black.board.common.retrofit.OkHttpParamsInterceptor;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 类的用途：提供一些第三方实例
 * <p>   如 retrofit
 * mac周昇辰
 * 8888/88/8  20:06
 */

@Module
public class ClientModule {

    private static final int TIMEOUT=30;//超时时间

    @Provides
    @Singleton
    Retrofit.Builder provideRetrofitBuilder()
    {
        return  new Retrofit.Builder();
    }


    @Provides
    @Singleton
    OkHttpClient.Builder provideOkHttpBuild()
    {
        return  new OkHttpClient.Builder();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(Retrofit.Builder retrofitBuilder, OkHttpClient okHttpClient)
    {
        retrofitBuilder
                .baseUrl("https://api.github.com/")//设置baseUrl
                .client(okHttpClient)//添加okHttp 默认配置
           //    .addConverterFactory(RequestConverterFactory.create())//自定义
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//retrofit rxjava 结合
              //  .addConverterFactory(ScalarsConverterFactory.create())//String 类型支持
                .addConverterFactory(GsonConverterFactory.create());//Gson 解析

        return  retrofitBuilder.build();
    }


    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(OkHttpClient.Builder okHttpBuilder, HttpLoggingInterceptor httpLoggingInterceptor)
    {
        okHttpBuilder
                .connectTimeout(TIMEOUT,TimeUnit.SECONDS)//连接超时
                .readTimeout(TIMEOUT,TimeUnit.SECONDS)//读取超时
                .writeTimeout(TIMEOUT,TimeUnit.SECONDS)//写入超时
                .retryOnConnectionFailure(true)//失败重连
                .addInterceptor(new OkHttpHeadInterceptor())//统一请求头
                .addInterceptor(new OkHttpParamsInterceptor())//统一请求参数
                ;
        if(httpLoggingInterceptor!=null) {
            okHttpBuilder.addInterceptor(httpLoggingInterceptor);//添加日志拦截器
        }

        return  okHttpBuilder.build();
    }

    @Provides
    @Singleton
    HttpLoggingInterceptor provideHttpLogg()
    {
        HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.v(ApiServiceKey.LOG_HTTP,message);
            }
        });
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
       return  httpLoggingInterceptor;
    }




}
