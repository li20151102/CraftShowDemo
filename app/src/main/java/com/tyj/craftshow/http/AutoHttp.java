package com.tyj.craftshow.http;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by kyle on 2019/3/29.
 * exp：
 **/
public abstract class AutoHttp<T> {
     public AutoHttp(Observable obs){
         obs.subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(new BaseObserver<T>() {
                     @Override
                     protected void onHandleSuccess(T userBean) {
                         handleSuccess(userBean);
                     }

                     @Override
                     protected void onHandleError(String msg) {

                     }
                 });
    }
    public abstract void handleSuccess(T t);
}
