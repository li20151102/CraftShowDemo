package com.tyj.craftshow.base;

import android.app.Activity;
import android.os.Bundle;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;

/**
 * @author create by kyle_2019 on 2019/5/16 17:09
 * @package com.tyj.craftshow
 * @fileName BaseActivity
 */
public abstract class BaseActivity extends RxAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout());
        ButterKnife.bind(this);
        inItView(savedInstanceState);
    }

    protected abstract int setLayout();

    protected abstract void inItView(Bundle savedInstanceState);
}
