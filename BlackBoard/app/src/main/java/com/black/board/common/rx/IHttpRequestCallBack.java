package com.black.board.common.rx;


import com.black.board.common.base.BaseResp;

/**
 * description: 抽取回调方法
 * author:  周昇辰
 */


public interface IHttpRequestCallBack {

    void onSuccess(BaseResp resp);

    void onFailed(String berrorcode, String berrormsg);

    void onError();
}
