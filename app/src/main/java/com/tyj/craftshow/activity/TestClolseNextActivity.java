package com.tyj.craftshow.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tyj.craftshow.R;
import com.tyj.craftshow.base.BaseActivity;
import com.tyj.craftshow.util.RxClickUtil;

import butterknife.BindView;

/**
 * @author create by kyle_2019 on 2019/6/6 9:11
 * @package com.tyj.craftshow.activity
 * @fileName TestClolseNextActivity
 */
public class TestClolseNextActivity extends BaseActivity {

    @BindView(R.id.iv_bars_back)
    ImageView back;
    @BindView(R.id.bars_title)
    TextView title;

    @BindView(R.id.btn_up_upPage)
    Button btnUp;

    LocalBroadcastManager localBroadcastManager;
    TestClolseUpActivity testClolseUpActivity = new TestClolseUpActivity();
    TestClolseUpActivity.MyBCresult myBCresult = testClolseUpActivity.new MyBCresult();
    IntentFilter intentFilter = new IntentFilter("getBC");

    @Override
    protected int setLayout() {
        return R.layout.activity_closenext;
    }

    @SuppressLint("CheckResult")
    @Override
    protected void inItView(Bundle savedInstanceState) {
        title.setText("关闭测试页");
        localBroadcastManager= LocalBroadcastManager.getInstance(this); //绑定
        localBroadcastManager.registerReceiver(myBCresult,intentFilter);

        RxClickUtil.clicks(back).subscribe(o -> {
            finish();
        });
        RxClickUtil.clicks(btnUp).subscribe(o -> {
            Intent intent =new Intent("getBC");
            intent.putExtra("hehe","接收");
            localBroadcastManager.sendBroadcast(intent);
            setResult(RESULT_OK);
            finish();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(myBCresult);
    }
}
