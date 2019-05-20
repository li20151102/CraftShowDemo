package com.tyj.craftshow.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.tyj.craftshow.R;
import com.tyj.craftshow.base.BaseActivity;
import com.tyj.craftshow.util.RxClickUtil;
import com.tyj.craftshow.util.ToastUtil;

import butterknife.BindView;

/**
 * @author create by kyle_2019 on 2019/5/17 10:01
 * @package com.tyj.craftshow
 * @fileName UpdataPasswordActivity
 */
public class UpdataPasswordActivity extends BaseActivity {
    @BindView(R.id.et_update_name)
    EditText mName;
    @BindView(R.id.et_update_password)
    EditText mPwd;
    @BindView(R.id.btn_update)
    Button mModify;
    @Override
    protected int setLayout() {
        return R.layout.activity_update_password;
    }

    @Override
    protected void inItView(Bundle savedInstanceState) {
        onClicks();
    }

    @SuppressLint("CheckResult")
    private void onClicks() {
        RxClickUtil.clicks(mModify).subscribe(o -> {
            ToastUtil.showLongToast("注册");
        });
    }
}
