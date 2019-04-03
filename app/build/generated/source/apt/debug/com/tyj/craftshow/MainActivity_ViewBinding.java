// Generated code from Butter Knife. Do not modify!
package com.tyj.craftshow;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding implements Unbinder {
  private MainActivity target;

  @UiThread
  public MainActivity_ViewBinding(MainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MainActivity_ViewBinding(MainActivity target, View source) {
    this.target = target;

    target.btn1 = Utils.findRequiredViewAsType(source, R.id.btn_button1, "field 'btn1'", Button.class);
    target.btn2 = Utils.findRequiredViewAsType(source, R.id.btn_button2, "field 'btn2'", Button.class);
    target.imageView = Utils.findRequiredViewAsType(source, R.id.iv_imgs, "field 'imageView'", ImageView.class);
    target.mViewPager = Utils.findRequiredViewAsType(source, R.id.craftshow_viewpager, "field 'mViewPager'", ViewPager.class);
    target.mLinearLayout = Utils.findRequiredViewAsType(source, R.id.craftshow_linear, "field 'mLinearLayout'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btn1 = null;
    target.btn2 = null;
    target.imageView = null;
    target.mViewPager = null;
    target.mLinearLayout = null;
  }
}
