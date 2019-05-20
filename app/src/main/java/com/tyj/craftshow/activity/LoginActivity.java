package com.tyj.craftshow.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tyj.craftshow.MainActivity;
import com.tyj.craftshow.R;
import com.tyj.craftshow.base.BaseActivity;
import com.tyj.craftshow.http.BaseResponse;
import com.tyj.craftshow.http.RetrofitUtil;
import com.tyj.craftshow.http.RxSchedulers;
import com.tyj.craftshow.util.DialogUtil;
import com.tyj.craftshow.util.RxClickUtil;
import com.tyj.craftshow.util.ToastUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.tyj.craftshow.http.RxSchedulers.compose;

/**
 * @author create by kyle_2019 on 2019/5/16 17:15
 * @package com.tyj.craftshow
 * @fileName LoginActivity
 */
public class LoginActivity extends BaseActivity {

    @BindView(R.id.et_log_name)
    EditText mName;
    @BindView(R.id.et_log_password)
    EditText mPwd;
    @BindView(R.id.btn_login)
    Button logins;
    @BindView(R.id.tv_log_forgotPassword)
    TextView mFpwd;
    @BindView(R.id.tv_log_register)
    TextView mRegist;

    @Override
    protected int setLayout() {
        return R.layout.activity_login;
    }

    @SuppressLint("CheckResult")
    @Override
    protected void inItView(Bundle savedInstanceState) {

        RxClickUtil.clicks(logins).subscribe(o -> {
            if(TextUtils.isEmpty(mName.getText().toString().trim())){
                Toast.makeText(getApplicationContext(),"用户名不能为空",Toast.LENGTH_LONG).show();
                return;
            }
            if(TextUtils.isEmpty(mPwd.getText().toString().trim())){
                Toast.makeText(getApplicationContext(),"密码不能为空",Toast.LENGTH_LONG).show();
                return;
            }else {
                setPostLogin();
            }
        });
        RxClickUtil.clicks(mFpwd).subscribe(o -> {
            startActivity(new Intent(this, UpdataPasswordActivity.class));
        });
        RxClickUtil.clicks(mRegist).subscribe(o -> {
            startActivity(new Intent(this, RegisterActivity.class));
        });
    }

    @SuppressLint("CheckResult")
    public void setPostLogin(){//登录请求
        DialogUtil.showWaittingDialog(LoginActivity.this);
        Map<String,Object> map = new HashMap<>(10);
        map.put("user","12345678");
        map.put("password","111222");
        RetrofitUtil.getApiService().postLoginInfo(map)
                .compose(compose())
                .compose(bindToLifecycle())
                .subscribe(baseResponse -> {
                    if(baseResponse!=null){
                        baseResponse.getData();

//                        finish();
//                        startActivity(new Intent(this, MainActivity.class));
                    }
                    DialogUtil.closeWaittingDialog();
                },throwable -> {
                    DialogUtil.closeWaittingDialog();
                    Log.e("TAG_LoginActivity", throwable.getMessage());
                });
        finish();
        startActivity(new Intent(this, MainActivity.class));

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
