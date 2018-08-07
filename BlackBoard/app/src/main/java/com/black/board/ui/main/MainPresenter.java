package com.black.board.ui.main;

import com.black.board.common.base.BasePresenter;
import com.black.board.common.base.BaseResp;
import com.black.board.common.rx.HttpObserver;
import com.black.board.common.rx.RxHelper;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * description:
 * author:  周昇辰
 * date:2018/8/7
 */


public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    @Inject
    MainModel mainModel;
    @Inject
    public MainPresenter() {
    }

    @Override
    public void getXxData() {
       Disposable disposable=mainModel.getData()
                .compose(RxHelper.<BaseResp>observableTransformer())
                .subscribeWith(new HttpObserver<BaseResp>() {
                    @Override
                    public void onSuccess(BaseResp resp) {


                    }

                    @Override
                    public void onFailed(String berrorcode, String berrormsg) {

                    }

                    @Override
                    public void onError() {

                    }
                });

       //添加订阅管理
       addDisposable(disposable);

    }
}
