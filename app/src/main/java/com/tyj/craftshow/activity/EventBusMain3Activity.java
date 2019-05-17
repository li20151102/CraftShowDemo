package com.tyj.craftshow.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.tyj.craftshow.R;
import com.tyj.craftshow.bean.EventBusBase;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class EventBusMain3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus_main3);
        Button btn = findViewById(R.id.btn_events);
        EventBus.getDefault().register(this);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }

    public void setBtnClick(View view) {
        Log.e("TAG","DD");
        EventBus.getDefault().post(new EventBusBase("dsfdsf"));
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getEventMsg(EventBusBase busBase){
        Log.e("TAGS",busBase.getMsg());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
