package com.tyj.craftshow.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.tyj.craftshow.MainActivity;
import com.tyj.craftshow.R;
import com.tyj.craftshow.bean.EventBusBase;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class BroadCastMain2Activity extends AppCompatActivity {

    LocalBroadcastManager localBroadcastManager;
    MainActivity mainActivity = new MainActivity();
    MainActivity.MyBroadCast myBroadCast = mainActivity.new MyBroadCast();
    IntentFilter intentFilter = new IntentFilter("by");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        EventBus.getDefault().register(this);
        TextView tvSend = findViewById(R.id.tv_send);
        TextView tvSends = findViewById(R.id.tv_sends);
        localBroadcastManager= LocalBroadcastManager.getInstance(getApplicationContext());
        localBroadcastManager.registerReceiver(myBroadCast,intentFilter);
        tvSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("by");
                intent.putExtra("hh","接收内容");
                localBroadcastManager.sendBroadcast(intent);
            }
        });
        tvSends.setMovementMethod(LinkMovementMethod.getInstance());  //网站链接
        tvSends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), EventBusMain3Activity.class));
            }
        });
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getEventMsg(EventBusBase busBase){
        Log.e("TAGDD",busBase.getMsg());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(myBroadCast);
        EventBus.getDefault().unregister(this);
    }
}
