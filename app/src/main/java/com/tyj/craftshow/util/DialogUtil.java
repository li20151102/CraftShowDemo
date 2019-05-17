package com.tyj.craftshow.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.tyj.craftshow.R;

import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by shdf on 2018/6/16.
 * wechat：zcm656025633
 * exp：
 **/
public class DialogUtil {
    private static ProgressDialog dialog;

    private DialogUtil() {
    }

    public static ProgressDialog getDialogInstance(Context context) {
            dialog = new ProgressDialog(context);
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setMessage("正在加载...");
            dialog.setCanceledOnTouchOutside(false);
            dialog.setCancelable(false);
        return dialog;
    }
    /**
     * 开启加载效果  直接调用方法
     */
    public static void showWaittingDialog(Context context) {
        try {
            if (dialog != null) {
                dialog.dismiss();
                dialog = null;
            }

            dialog = new ProgressDialog(context, R.style.CustomProgressDialog);
            dialog.setCanceledOnTouchOutside(false);
            LayoutInflater mInflater = dialog.getLayoutInflater();
            View mView = mInflater.inflate(R.layout.dialog_loading, null);
            dialog.show();
            dialog.setContentView(mView);
            // 3秒后还未完成任务，则设置为可取消
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    if (dialog != null)
                        dialog.setCancelable(true);
                }
            };
            Timer timer = new Timer(true);
            timer.schedule(task, 3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 关闭加载效果  直接调用方法
     */
    public static void closeWaittingDialog() {
        try {
            if (dialog != null)
                dialog.dismiss();
            dialog = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
