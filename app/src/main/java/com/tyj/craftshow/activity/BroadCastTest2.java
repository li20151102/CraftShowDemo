package com.tyj.craftshow.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Button;

import com.tyj.craftshow.R;
import com.tyj.craftshow.base.BaseActivity;
import com.tyj.craftshow.bean.EventBusBase;
import com.tyj.craftshow.util.RxClickUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

/**
 * @author create by kyle_2019 on 2019/6/10 9:10
 * @package com.tyj.craftshow.activity
 * @fileName BroadCastTest2
 */
public class BroadCastTest2 extends BaseActivity {
    private static final String TAG = "BroadCastTest2";
    LocalBroadcastManager localBroadcastManager;
    BroadCastTest broadCastTest =new BroadCastTest();
    BroadCastTest.MyBroadTest myBroadTest = broadCastTest.new MyBroadTest();
    IntentFilter intentFilter = new IntentFilter("broadTest");

    @BindView(R.id.btn_sends)
    Button sends;

    @Override
    protected int setLayout() {
        return R.layout.activity_broadcast_test2;
    }

    @SuppressLint("CheckResult")
    @Override
    protected void inItView(Bundle savedInstanceState) {
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        localBroadcastManager.registerReceiver(myBroadTest,intentFilter);

        EventBus.getDefault().register(this);
        RxClickUtil.clicks(sends).subscribe(o -> {

            //发送广播
            Intent intent = new Intent("broadTest");
            intent.putExtra("hshs","测试测试");
            localBroadcastManager.sendBroadcast(intent);

            EventBus.getDefault().post(new EventBusBase("hehe"));
            finish();
        });

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getEVBUS(EventBusBase event) {}

    @Override
    protected void onDestroy() {
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(myBroadTest);
        EventBus.getDefault().unregister(this);
    }
}
