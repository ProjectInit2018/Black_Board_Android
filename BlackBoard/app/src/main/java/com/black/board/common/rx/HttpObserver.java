package com.black.board.common.rx;

import android.text.TextUtils;
import android.util.Log;

import com.black.board.common.base.BaseResp;
import com.black.board.common.base.IBaseView;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import io.reactivex.observers.DisposableObserver;

/**
 * description: 简单抽取Observer
 * author:  周昇辰
 */


public abstract class HttpObserver<T> extends DisposableObserver<T> implements IHttpRequestCallBack {

    private IBaseView mView;
    private boolean isShow;
    public HttpObserver() {
    }
    public HttpObserver(IBaseView mView) {
        this.mView=mView;
        isShow=true;
    }
    public HttpObserver(IBaseView mView,boolean isShow) {
        this.mView=mView;
        this.isShow=isShow;
    }
    @Override
    public void onStart() {
        super.onStart();
        loading(true);
        Log.v("http","开始请求");

    }
    @Override
    public void onNext(T t) {
        loading(false);
        try {
            BaseResp resp = (BaseResp) t;
            if (resp.getStatus() == 0) {
                if(resp.getData()==null){
                    onFailed("1","返回数据为null");
                    Log.v("xxx","返回成功 数据 为null----");
                    return;
                }
               onSuccess(resp);
                Log.v("http", "数据返回成功"+"onSuccess");
            } else {
                //兼容老接口
                String berrorCode="";//TextUtils.isEmpty(resp.getBerrorcode())?resp.getErrorcode():resp.getBerrorcode();
                String berrorMsg="";//TextUtils.isEmpty(resp.getBerrormsg())?resp.getErrormsg():resp.getBerrormsg();
                onFailed(berrorCode,berrorMsg);
                Log.v("http", "数据返回成功"+"onFailed");
            }
       }catch (Exception exceptuon){
            exceptuon.printStackTrace();
            Log.v("http", ""+"onError.........-..-........"+  exceptuon.toString());
            onError();
        }

    }

    @Override
    public void onError(Throwable e) {
        loading(false);
        Log.v("http","接口请求异常"+e.toString());
        if(e instanceof  IOException){

            Log.v("xxx","io 异常 --屏蔽--");
        }else if(e instanceof TimeoutException){

        } else{
            onError();
        }

    }

    @Override
    public void onComplete() {
        loading(false);
        Log.v("http","接口请求结束！！！！");
    }


    /**
     * 加载框调用方法
     * @param isShow show or hide
     */
    private void loading(boolean isShow){
        if(mView==null)
            return;
        if(isShow){
            Log.v("http","---调用了加载框");
            mView.showLoading();
        }else
            mView.closeLoading();
    }
}
