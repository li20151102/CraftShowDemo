package com.tyj.craftshow.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tyj.craftshow.MainActivity;
import com.tyj.craftshow.R;
import com.tyj.craftshow.base.BaseActivity;
import com.tyj.craftshow.http.RetrofitUtil;
import com.tyj.craftshow.util.DialogUtil;
import com.tyj.craftshow.util.RxClickUtil;
import com.tyj.craftshow.util.ToastUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

import static com.tyj.craftshow.http.RxSchedulers.compose;

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
            setPostRegist();
        });
        RxClickUtil.clicks(mLogin).subscribe(o -> {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        });
    }

    @SuppressLint("CheckResult")
    public void setPostRegist(){//登录请求
        DialogUtil.showWaittingDialog(RegisterActivity.this);
        String name = mName.getText().toString().trim();
        String pwd = mPwd.getText().toString().trim();
        if(TextUtils.isEmpty(name)){
            ToastUtil.showLongToast("用户名不能为空！");
            return;
        }
        if(TextUtils.isEmpty(name)){
            ToastUtil.showLongToast("密码不能为空！");
            return;
        }
        Map<String,Object> map = new HashMap<>(15);
        map.put("userName", name);
        map.put("password", pwd);
        RetrofitUtil.getApiService().postInsertInfo(map)
                .compose(compose())
                .compose(bindToLifecycle())
                .subscribe(baseResponse -> {
                    DialogUtil.closeWaittingDialog();
                    if(baseResponse!=null){
                        baseResponse.getData();
                        ToastUtil.showLongToast(""+baseResponse.getMessage());
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        finish();
                    }

                },throwable -> {
                    DialogUtil.closeWaittingDialog();
                    Log.e("TAG_LoginActivity", throwable.getMessage());
                });
    }

}
