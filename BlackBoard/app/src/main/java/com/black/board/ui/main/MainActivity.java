package com.black.board.ui.main;


import com.black.board.R;
import com.black.board.common.base.BaseActivity;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
      mPresenter.getXxData();
    }

    @Override
    public void initData() {

    }
}
