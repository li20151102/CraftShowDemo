package com.tyj.craftshow;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    LocalBroadcastManager localBroadcastManager;
    MainActivity mainActivity = new MainActivity();
    MainActivity.MyBroadCast myBroadCast = mainActivity.new MyBroadCast();
    IntentFilter intentFilter = new IntentFilter("by");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(myBroadCast);
    }
}
