package com.black.board.common.base;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * description: 基础presenter
 * author:  周昇辰
 */

public  class BasePresenter<V extends IBaseView> implements IBasePresenter<V> {
    /**
     * 弱引用管理v
     */
    private Reference<V> mViewReference;

    /**
     * 订阅管理
     */
    private static CompositeDisposable mCompositeDisposable;

    /**
     * 添加订阅
     */
    @Override
    public void addDisposable(Disposable disposable) {
        if (mCompositeDisposable == null)
            mCompositeDisposable = new CompositeDisposable();
        mCompositeDisposable.add(disposable);

    }
    /**
     * 取消订阅
     */
    @Override
    public void removeDisposable(){
        if(mCompositeDisposable!=null){
            mCompositeDisposable.dispose();
            mCompositeDisposable.clear();
            mCompositeDisposable=null;
        }
    }
    /**
     * 绑定的方法
     * @param view
     */
    @Override
    public void attachView(V view) {
        mViewReference=new WeakReference<>( view);
    }
    /**
     * 判断是否关联
     * @param
     */
    @Override
    public boolean isAttachView() {
        return mViewReference != null && mViewReference.get() != null;
    }
    /**
     * 获取view
     */

    public V getView(){
        return  isAttachView()?mViewReference.get():null;
    }

    /**
     * 解绑
     * @param
     */
    @Override
    public void detachView() {
        if (isAttachView()) {
            removeDisposable();
            mViewReference.clear();
            mViewReference=null;
        }
    }
}
