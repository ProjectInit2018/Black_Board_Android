package com.black.board.common.retrofit;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * description: 添加统一请求头
 * author:  周昇辰
 */


public class OkHttpHeadInterceptor implements Interceptor {

    @Override
        public Response intercept(Chain chain) throws IOException {
        //添加公共请求头
        Request.Builder request = chain.request()
                    .newBuilder()
                    //客户端时间 yyyy-MM-dd HH:mm:ss.SSS  clientDatetime
                    .addHeader("clientDatetime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault())
                    .format(Calendar.getInstance().getTime()));
        return chain.proceed(request.build());
        }

}
