package com.tyj.craftshow.activity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
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
 * @fileName TestClolseUpActivity
 */
public class TestClolseUpActivity extends BaseActivity {

    @BindView(R.id.iv_bars_back)
    ImageView back;
    @BindView(R.id.bars_title)
    TextView title;

    @BindView(R.id.btn_next)
    Button btnNext;
    @Override
    protected int setLayout() {
        return R.layout.activity_closeup;
    }

    @SuppressLint("CheckResult")
    @Override
    protected void inItView(Bundle savedInstanceState) {
        title.setText("当前测试页");
        RxClickUtil.clicks(back).subscribe(o -> {
            finish();
        });
        RxClickUtil.clicks(btnNext).subscribe(o -> {
            startActivityForResult(new Intent(this,TestClolseNextActivity.class),0x001);
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            if(requestCode==0x001){
                finish();
            }
        }
    }

    public class MyBCresult extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String D = intent.getStringExtra("hehe");
            Log.e("hess",D);

        }
    }

}
