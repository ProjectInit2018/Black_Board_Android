package com.black.board.ui.main;

import com.black.board.common.base.IBasePresenter;
import com.black.board.common.base.IBaseView;

/**
 * description: 模拟main 契约类 规范
 * author:  周昇辰
 */


public class MainContract {

    public interface View extends IBaseView {
        void  initData();
    }
    public interface Presenter extends IBasePresenter<View> {

         void getXxData();
    }
}
