package com.tyj.craftshow.activity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.tyj.craftshow.R;
import com.tyj.craftshow.base.BaseActivity;
import com.tyj.craftshow.bean.EventBusBase;
import com.tyj.craftshow.util.RxClickUtil;
import com.tyj.craftshow.widget.CountNumView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

/**
 * @author create by kyle_2019 on 2019/6/10 9:04
 * @package com.tyj.craftshow.activity
 * @fileName BroadCastTest
 */
public class BroadCastTest extends BaseActivity {
    private static final String TAG = "BroadCastTest";

    @BindView(R.id.btn_nexts)
    Button nexts;
    @BindView(R.id.btn_nexts2)
    Button nexts2;
    @BindView(R.id.cnv)
    CountNumView cnvs;

    @Override
    protected int setLayout() {
        return R.layout.activity_broadcast_test;
    }

    @SuppressLint("CheckResult")
    @Override
    protected void inItView(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart: "+"start" );

        RxClickUtil.clicks(nexts).subscribe(o -> startActivity(new Intent(this,BroadCastTest2.class)));

        cnvs.setAlpha(0.1f);
    }

    class MyBroadTest extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            String data = intent.getStringExtra("hshs");
            Log.e(TAG,"ds"+data);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getEVBUS(EventBusBase event) {
        nexts.setText(event.getMsg());
        Log.e(TAG, "getEVBUSee: "+event.getMsg() );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
