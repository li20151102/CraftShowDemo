package com.tyj.craftshow.util;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;

import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxAdapterView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

/**
 * @author create by kyle_2019 on 2019/5/17 17:24
 * @package com.tyj.craftshow.util
 * @fileName RxClickUtil
 */
public class RxClickUtil {
    /**
     * 单击事件
     *
     * @param view view
     * @return observable
     */
    public static Observable<Object> clicks(@NonNull View view) {
        return RxView.clicks(view).throttleFirst(500, TimeUnit.MILLISECONDS);
    }

    /**
     * adapterView 点击事件
     *
     * @param view
     * @param <T>
     * @return
     */
    public static <T extends Adapter> Observable<Integer> itemClicks(@NonNull AdapterView<T> view) {
        return RxAdapterView.itemClicks(view).throttleFirst(500, TimeUnit.MILLISECONDS);
    }
}
