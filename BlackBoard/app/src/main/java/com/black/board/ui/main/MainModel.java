package com.black.board.ui.main;

import com.black.board.common.base.BaseModel;
import com.black.board.common.base.BaseResp;
import com.black.board.di.api.AppInitService;
import com.black.board.entity.CxResp;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * description: Main Model
 * author:  周昇辰
 */


public class MainModel extends BaseModel {
    @Inject
    AppInitService appInitService;
    @Inject
    public MainModel() {
    }

    public Observable<BaseResp<List<CxResp>>> getData(){
        return appInitService.getData(83);
    }

}
