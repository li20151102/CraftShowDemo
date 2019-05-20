package com.tyj.craftshow.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tyj.craftshow.R;
import com.tyj.craftshow.base.BaseActivity;
import com.tyj.craftshow.util.RxClickUtil;
import com.tyj.craftshow.util.ToastUtil;

import butterknife.BindView;

/**
 * @author create by kyle_2019 on 2019/5/17 9:48
 * @package com.tyj.craftshow
 * @fileName RegisterActivity
 */
public class RegisterActivity extends BaseActivity{
    @BindView(R.id.et_regist_name)
    EditText mName;
    @BindView(R.id.et_regist_password)
    EditText mPwd;
    @BindView(R.id.btn_register)
    Button mRegist;
    @BindView(R.id.tv_regist_login)
    TextView mLogin;
    @Override
    protected int setLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void inItView(Bundle savedInstanceState) {
        OnClicks();
    }

    @SuppressLint("CheckResult")
    private void OnClicks() {
        RxClickUtil.clicks(mRegist).subscribe(o -> {
            ToastUtil.showLongToast("注册");
        });
        RxClickUtil.clicks(mLogin).subscribe(o -> {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        });
    }

}
