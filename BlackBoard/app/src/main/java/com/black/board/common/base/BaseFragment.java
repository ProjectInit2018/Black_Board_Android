package com.black.board.common.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;

/**
 * description: 基础fragment
 * author:  周昇辰
 * date:2018/6/25
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements IBaseView {
    @Inject
    public P mPresenter;
    private View view;
    private Unbinder bind;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(isInject())
        AndroidSupportInjection.inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if(view==null) {
            view = inflater.inflate(setLayoutId(),container,false);
            bind = ButterKnife.bind(this,view);
        }
        ViewGroup vg= (ViewGroup) view.getParent();
        if(vg!=null) {
            vg.removeView(view);
        }
        if (mPresenter!=null) {
            mPresenter.attachView(this);
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    /**
     * 是否需要提供注入能力
     * 如不需要重写此方法
     * @return
     */
    protected boolean isInject (){
        return true;
    }

    /**
     * 设置布局
     * @return
     */
    protected abstract int setLayoutId();

    /**
     * 初始化布局相关操作
     */
    protected abstract void initView();


    @Override
    public void showLoading() {

    }

    @Override
    public void closeLoading() {

    }
    /**
     * 解绑
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (bind != null)
            bind.unbind();
        if (mPresenter != null)
            mPresenter.detachView();
    }
}
