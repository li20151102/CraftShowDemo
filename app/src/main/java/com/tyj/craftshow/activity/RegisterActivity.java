package com.tyj.craftshow.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tyj.craftshow.R;
import com.tyj.craftshow.base.BaseActivity;
import com.tyj.craftshow.util.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author create by kyle_2019 on 2019/5/17 9:48
 * @package com.tyj.craftshow
 * @fileName RegisterActivity
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener {
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

    }

    @Override
    @OnClick({R.id.btn_register,R.id.tv_regist_login})
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_register:
                ToastUtil.showLongToast("sdfhh");
                break;
            case R.id.tv_regist_login:

                break;
        }

    }
}
