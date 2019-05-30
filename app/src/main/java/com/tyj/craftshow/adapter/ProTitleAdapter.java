package com.tyj.craftshow.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tyj.craftshow.R;
import com.tyj.craftshow.bean.ProTitleBean;

import java.util.List;

/**
 * @author create by kyle_2019 on 2019/5/28 10:39
 * @package com.tyj.craftshow.adapter
 * @fileName ProTitleAdapter
 */
public class ProTitleAdapter extends BaseQuickAdapter<ProTitleBean, BaseViewHolder> {

    public ProTitleAdapter(int layoutResId) {
        super(layoutResId);
    }

    public ProTitleAdapter(int layoutResId, @Nullable List<ProTitleBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProTitleBean item) {
        int position = helper.getLayoutPosition();
        ImageView view = helper.getView(R.id.iv_arrow);
        view.setVisibility(position == mData.size() - 1 ? View.GONE : View.VISIBLE);
        TextView view1 = helper.getView(R.id.tv_name);
        view1.setText(item.getTitleName());
        view1.setTextColor(position == mData.size() - 1 ? Color.parseColor("#333333") : Color.parseColor("#999999"));
    }
}
