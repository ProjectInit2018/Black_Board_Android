package com.black.board.common.rx;

import org.reactivestreams.Publisher;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * description: rx相关工具代码
 * author:  周昇辰
 * date:2018/6/7
 */
public class RxHelper {

    /**
     * 线程调度器
     * Observable 可用
     * @param <T>
     * @return
     */
    public static  <T> ObservableTransformer<T,T> observableTransformer(){
      return new ObservableTransformer<T, T>() {
          @Override
          public ObservableSource<T> apply(Observable<T> upstream) {
              return   upstream.subscribeOn(Schedulers.io())//网络请求切换在io线程
                      .unsubscribeOn(Schedulers.io())//取消网络请求切换在io线程
                      .observeOn(AndroidSchedulers.mainThread());//观察者主线程调用
          }
      };
    }

    /**
     * 线程调度器
     * Flowable 可用
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<T,T> flowableTransformer()
    {
        return new FlowableTransformer<T, T>() {
            @Override
            public Publisher<T> apply(Flowable<T> upstream) {
                return   upstream.subscribeOn(Schedulers.io())//网络请求切换在io线程
                        .unsubscribeOn(Schedulers.io())//取消网络请求切换在io线程
                        .observeOn(AndroidSchedulers.mainThread());//观察者主线程调用
            }
        };

    }
}
