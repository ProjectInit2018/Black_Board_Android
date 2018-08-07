package com.black.board.common.base;

import io.reactivex.disposables.Disposable;

/**
 * description: presenter 上层公共方法定义接口
 * author:  周昇辰
 */


public interface IBasePresenter<V extends IBaseView> {

    /**
     * 绑定view的方法
     * @param view
     */
    void  attachView(V view);

    /**
     * 解绑view的方法
     */
    void  detachView();

    /**
     * 获得当前view
     */
     V getView();

    /**
     * 当前presenter是否绑定view
     * @return
     */
     boolean  isAttachView();

    /**
     * 添加订阅管理
     * @param disposable
     */
     void  addDisposable(Disposable disposable);

    /**
     * 取消订阅
     */
    void  removeDisposable();
}
