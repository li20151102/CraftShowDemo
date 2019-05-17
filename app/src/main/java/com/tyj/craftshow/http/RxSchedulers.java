package com.tyj.craftshow.http;

import android.util.Log;
import android.widget.Toast;

import com.tyj.craftshow.base.AppApplication;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by kyle on 2019/3/29.
 * wechat：
 * exp：
 **/
public class RxSchedulers {
    private static final String TAG = "RxSchedulers";

    public static <T> ObservableTransformer<T, T> compose() {

        return observable -> observable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> {
                    Log.e(TAG, "doOnSubscribe: ");
                    if (!NetUtil.isNetworkAvailable()) {
                        Toast.makeText(AppApplication.getContext(), "请检查网络", Toast.LENGTH_SHORT).show();
                    }

                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
