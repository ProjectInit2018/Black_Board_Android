package com.black.board.common.retrofit;

import android.text.TextUtils;

import com.black.board.common.base.ApiServiceKey;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * description: 1. 添加请求公共参数--系统参数
 *              2. 加密key 为data 的数据
 *              3. 根据head 判断是否需要 添加token and ucode
 * author:  周昇辰
 */


public class OkHttpParamsInterceptor implements Interceptor {

    private static final String SYSTEM_PARAMS="system";
    private static final String TOKEN_PARAMS="token";
    private static final String UCODE_PARAMS="ucode";

    @Override
    public Response intercept(Chain chain) throws IOException {
        //获取请求的Request
        Request original = chain.request();
        //获取本地定义请求头
        String t_u_value=original.header(ApiServiceKey.T_U_KEY);
        Request.Builder request=original.newBuilder();
        //当添加了此请求头，并且值为本地定义的值时
        //移除本地定义的请求头
        if(!TextUtils.isEmpty(t_u_value)&& ApiServiceKey.T_U_VALUE.equals(t_u_value)){
            //移除本地head
            request.removeHeader(ApiServiceKey.T_U_KEY);
        }
        //请求体 统一添加stsytem参数
        if(original.body() instanceof FormBody){
            FormBody.Builder newFormBody = new FormBody.Builder();
            FormBody oidFormBody = (FormBody) original.body();
            for (int i = 0;i<oidFormBody.size();i++){
                String key=oidFormBody.encodedName(i);
                if("data".equals(key)){//如果请求参数有key 值为data 默认加密
                    // TODO: 2018/8/7   加密处理
                    continue;
                }
                // TODO: 2018/8/7  retrofit post特殊处理
                //过滤掉参数
                if("system".equals(key)){
                    continue;
                }
                newFormBody.addEncoded(key,oidFormBody.encodedValue(i));
            }
            //添加公共参数
            // TODO: 2018/8/7 添加所有请求公共参数
            //newFormBody.add(SYSTEM_PARAMS,getUserAgent());

            //添加特殊处理参数
            if(!TextUtils.isEmpty(t_u_value)&& ApiServiceKey.T_U_VALUE.equals(t_u_value)){
                  //添加其他参数 token and ucode
                // TODO: 2018/8/7 添加特殊处理参数
            }
            request.method(original.method(),newFormBody.build());
        }
        return chain.proceed(request.build());
    }

}
