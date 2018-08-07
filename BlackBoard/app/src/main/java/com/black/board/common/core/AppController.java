package com.black.board.common.core;

import android.app.Activity;

/**
 * description: 基础控制类
 * author:  周昇辰
 */
public class AppController {
    /**
     * 当前Android活动对象（页面Activity）
     */
    private Activity currentActivity;

    /**
     * 控制层采用单例模式
     */
    private static AppController appc;

    /**
     * 构造函数：
     */
    private AppController() {
    }

    public synchronized static AppController getController() {
        if (null == appc) {
            appc = new AppController();
        }
        return appc;
    }

    /**
     * 设置新的活动页面
     */
    public void setCurrentActivity(Activity act) {
        this.currentActivity = act;
    }

    /**
     * 退出APP
     */
    public void exitApp() {

        // 退出应用程序
        AppManager.getAppManager().AppExit(currentActivity);
    }


    /**
     * 清退本账户，用于其他账号登录
     */
    public void exitAccount() {


    }

}
