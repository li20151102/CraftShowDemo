package com.tyj.craftshow.adapter;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tyj.craftshow.R;
import com.tyj.craftshow.bean.ProjectDataBean;
import com.tyj.craftshow.util.ToastUtil;

import java.util.List;


/**
 * @author create by kyle_2019 on 2019/5/28 10:32
 * @package com.tyj.craftshow.adapter
 * @fileName ProjectDataAdapter
 */
public class ProjectDataAdapter extends BaseQuickAdapter<ProjectDataBean, BaseViewHolder> {

    public ProjectDataAdapter(int layoutResId) {
        super(layoutResId);
    }
    public ProjectDataAdapter(int layoutResId, @Nullable List<ProjectDataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProjectDataBean item) {
        int type = item.getType();
        if (type == 0) {
            if (item.getConfirmStatus() == 1) {
                helper.setText(R.id.projectmm_state, "已入库");
                helper.setTextColor(R.id.projectmm_state, mContext.getResources().getColor(R.color.colorgray));
                helper.setGone(R.id.projectmm_newAdd, true);
                helper.setGone(R.id.projectmm_addMonomer, true);
            } else {
                helper.setText(R.id.projectmm_state, "未入库");
                helper.setTextColor(R.id.projectmm_state, mContext.getResources().getColor(R.color.colorAccent));
                helper.setGone(R.id.projectmm_newAdd, false);
                helper.setGone(R.id.projectmm_addMonomer, false);
            }
            helper.setText(R.id.projectmm_name, "项目名称");
            helper.setText(R.id.tv_projectmm_name, "" + item.getName());
            helper.setText(R.id.projectmm_amount, "投资额度");
            helper.setText(R.id.tv_projectmm_amount, "" + item.getMoney());
            helper.setText(R.id.projectmm_addMonomer, "新增单体项目");
            helper.setText(R.id.projectmm_newAdd, "新增子项目");
            helper.setOnClickListener(R.id.projectmm_addMonomer, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.showLongToast("新增单体项目");
                }
            });
            helper.setOnClickListener(R.id.projectmm_newAdd, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.showLongToast("新增子项目");
                }
            });

        } else {
            helper.setText(R.id.projectmm_name, "单体名称");
            helper.setText(R.id.tv_projectmm_name, "" + item.getName());
            helper.setText(R.id.tv_projectmm_amount, "" + item.getMoney());
            helper.setGone(R.id.projectmm_addMonomer, false);
            helper.setGone(R.id.projectmm_newAdd, false);
            helper.setText(R.id.projectmm_state, item.getConfirmStatus() == 1 ? "已入库" : "未入库");


        }
    }
}
