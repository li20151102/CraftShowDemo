package com.tyj.craftshow.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;

/**
 * @author create by kyle_2019 on 2019/5/16 17:09
 * @package com.tyj.craftshow
 * @fileName BaseActivity
 */
public abstract class BaseActivity extends RxAppCompatActivity {
    private CommonDialog mDialog = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout());
        ButterKnife.bind(this);
        inItView(savedInstanceState);
    }

    protected abstract int setLayout();

    protected abstract void inItView(Bundle savedInstanceState);

    protected void showDialog(String msg) {
        if (null == mDialog) {
            mDialog = new CommonDialog(this, msg);
        }
        if (!mDialog.isShowing()) {
            mDialog.setMessage(msg);
            if (!isFinishing()) {
                mDialog.show();
            }
        } else {
            mDialog.setMessage(msg);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dismissDialog();
    }

    protected void dismissDialog() {
        if (!isFinishing() && null != mDialog && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    class CommonDialog extends ProgressDialog {

        public CommonDialog(Context context, String str) {
            super(context);
            //我发现这里不能用application的那个context，因为要show这个dialog
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setCanceledOnTouchOutside(false);
            setMessage(str);
        }

        @Override
        public void setMessage(CharSequence message) {
            super.setMessage(message);
        }
    }
}
