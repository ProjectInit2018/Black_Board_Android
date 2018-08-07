package com.black.board.common.base;

/**
 * description: 本地定义一些请求相关 key-value 值
 * author:  周昇辰
 */


public interface ApiServiceKey {

    String LOG_HTTP="http";
    //本地head key
    String T_U_KEY="t_u";
    //本地head value
    String T_U_VALUE="token_ucode";
    /**
     * 添加此请求头 --- 会自动添加参数（token ,ucode）
     */
    //head 键值对
    String T_U_KEYVALUE=T_U_KEY+":"+T_U_VALUE;



}
