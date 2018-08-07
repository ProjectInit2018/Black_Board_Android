package com.black.board.common.base;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import com.black.board.common.core.AppController;
import com.black.board.common.core.AppManager;

import java.lang.reflect.Field;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.AndroidInjection;

/**
 * description:基类activity
 * author:  周昇辰
 */

public abstract class BaseActivity<P extends BasePresenter> extends FragmentActivity implements IBaseView {

    @Inject
    protected P mPresenter;
    private Unbinder bind;
    private FragmentManager fm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (isInject())
            AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        //添加Activity到堆栈
        AppManager.getAppManager().addActivity(this);
        //初始化控制器
        AppController.getController().setCurrentActivity(this);
        if(setLayoutId()!=0)
        setContentView(setLayoutId());
        bind = ButterKnife.bind(this);
        getSupportFragmentManager();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        initView();
    }

    /**
     * 动态的设置状态栏
     */
    public void initState() {

        //当系统版本为4.4或者4.4以上时可以使用沉浸式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                try {
                    Class decorViewClazz = Class.forName("com.android.internal.policy.DecorView");
                    Field field = decorViewClazz.getDeclaredField("mSemiTransparentStatusBarColor");
                    field.setAccessible(true);
                    field.setInt(getWindow().getDecorView(), Color.TRANSPARENT);  //改为透明
                } catch (Exception e) {
                }
            }
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            // getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            //
         /*   LinearLayout linear_bar = (LinearLayout) findViewById(R.id.ll_bar);
            linear_bar.setBackgroundColor(Color.parseColor("#f00000"));
            linear_bar.setVisibility(View.VISIBLE);
            //获取到状态栏的高度
            int statusHeight = getStatusBarHeight();
            //动态的设置隐藏布局的高度
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) linear_bar.getLayoutParams();
            params.height = statusHeight;
            linear_bar.setLayoutParams(params);*/

           //  LinearLayout contentView = (LinearLayout) findViewById(android.R.id.content);
           /* ViewGroup decorView = (ViewGroup)getWindow().getDecorView();
            LinearLayout  layout= (LinearLayout) decorView.getChildAt(0);
            layout.addView(createStatusBarView(this,Color.parseColor("#00f0f0")),0);*/
        }
    }
    /**
     * 获取状态栏高度
     *
     * @param context context
     * @return 状态栏高度
     */
    public static int getStatusBarHeight(Context context) {
        // 获得状态栏高度
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }

    public void finishOnly() {;
        super.finish();
        if (getCurrentFocus() != null) {
            ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
    /**
     * 是否需要提供注入能力
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
     * 处理逻辑
     * @return
     */
    protected abstract void initView();

    /**
     * 调用加载框
     */
    @Override
    public void showLoading() {
    }

    /**
     * 关闭加载框
     */
    @Override
    public void closeLoading() {
    }

    protected void addFragment(int frameLayoutId, Fragment fragment) {
        getFm().beginTransaction().add(frameLayoutId, fragment).commit();
    }

    protected void relaceFragment(int frameLayoutId, Fragment fragment) {
        getFm().beginTransaction().replace(frameLayoutId, fragment).commit();
    }

    protected void showFragment(Fragment fragment) {
        getFm().beginTransaction().show(fragment).commit();
    }

    protected void hideFragment(Fragment fragment) {
        getFm().beginTransaction().hide(fragment).commit();

    }

    public FragmentManager getFm(){
        if(fm==null){
            fm=getSupportFragmentManager();
        }
        return fm;
    }

    /**
     * 判断App是否在前台
     * @return
     */
    public boolean isAppOnForeground() {
        ActivityManager activityManager = (ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        String packageName = getApplicationContext().getPackageName();
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager
                .getRunningAppProcesses();
        if (appProcesses == null)
            return false;
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            // 判断应用的包是否在运行.
            if (appProcess.processName.equals(packageName)
                    && appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true;
            }
        }
        return false;
    }

    /**
     * 解绑
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mPresenter!=null)
            mPresenter.detachView();
        if(bind!=null)
            bind.unbind();
    }
}
