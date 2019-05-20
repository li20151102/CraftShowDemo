package com.tyj.craftshow.model;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tyj.craftshow.R;
import com.tyj.craftshow.base.BaseActivity;
import com.tyj.craftshow.util.RxClickUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author create by kyle_2019 on 2019/5/20 10:18
 * @package com.tyj.craftshow.model
 * @fileName DemoActivity
 */
public class DemoActivity extends BaseActivity {

    /**
     * 标题TextView
     */
    private TextView mTitle;
    @BindView(R.id.iv_back)
    ImageView mback;

    /**
     * 头部导航RecyclerView
     */
    private RecyclerView headerView;
    private BaseQuickAdapter<TreeNode, BaseViewHolder> headerAdapter;
    private List<TreeNode> headerData = new ArrayList<>();

    /**
     * 组织架构显示RecyclerView
     */
    private RecyclerView mRecyclerView;
    private DemoAdapter mAdapter;
    private Group root;

    @Override
    protected int setLayout() {
        return R.layout.activity_model_demo;
    }

    @Override
    protected void inItView(Bundle savedInstanceState) {
        initData();
        initView();
    }

    @SuppressLint("CheckResult")
    private void initData() {
        RxClickUtil.clicks(mback).subscribe(o -> finish());
        //构造根节点
        root = new Group();
//        root.setName("组织架构");

        Group group0 = new Group();
        group0.setName("组织架构");
        group0.setNumber(100);
        root.add(group0);

        //二级节点
        Group group1 = new Group();
        group1.setName("杭州总部");
        group1.setNumber(70);
        group0.add(group1);
        buildDepartment(group1, 7);

        Group group2 = new Group();
        group2.setName("长沙分公司");
        group2.setNumber(30);
        group0.add(group2);
        buildDepartment(group2, 3);

    }

    private void buildDepartment(Group group, int count) {
        for (int i = 1; i <= count; i++) {
            Group g = new Group();
            g.setName("长沙研发" + i + "部");
            g.setNumber(10);
            group.add(g);
            buildDepartments(g, 10);
        }

//        Member member = new Member();
//        member.setName("洋洋CTO");
//        member.setJobTitle("总监一职");
//        group.add(member);
    }

    private void buildDepartments(Group group, int count) {
        for (int i = 1; i <= count; i++) {
            Group g = new Group();
            g.setName("杭州测试" + i + "部");
            g.setNumber(10);
            group.add(g);

            buildDepartmentd(g, 20);
        }

//        Member member = new Member();
//        member.setName("洋洋CTO");
//        member.setJobTitle("总监一职");
//        group.add(member);
    }
    private void buildDepartmentd(Group group, int count) {
        for (int i = 1; i <= count; i++) {
            Group g = new Group();
            g.setName("西湖实施" + i + "部");
            g.setNumber(10);
            group.add(g);

            buildMember(g, 20);
        }

//        Member member = new Member();
//        member.setName("洋洋CTO");
//        member.setJobTitle("总监一职");
//        group.add(member);
    }

    private void buildMember(Group group, int count) {
        for (int i = 1; i <= count; i++) {
            Member member = new Member();
            member.setName("帅帅" + i + "号");
            member.setJobTitle("码农一枚");

            group.add(member);
        }
    }

    private void initView() {
//        mTitle = findViewById(R.id.tv_bar_title);
        mRecyclerView = findViewById(R.id.mRecyclerView);

        //设置布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new DemoAdapter(null);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                TreeNode item = mAdapter.getItem(position);
                if (item == null)
                    return;

                if (item.getModel() instanceof Group)//只有group,才能展开
                    selectNode(item);
                else if (item.getModel() instanceof Member)
                    Toast.makeText(DemoActivity.this, "点击一个成员:" + ((Member) item).getName(), Toast.LENGTH_SHORT).show();
            }
        });
        mAdapter.bindToRecyclerView(mRecyclerView);

        //初始化头部导航
        headerView = (RecyclerView) LayoutInflater.from(this).inflate(R.layout.layout_model_header, mRecyclerView, false);
        headerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        headerAdapter = new BaseQuickAdapter<TreeNode, BaseViewHolder>(R.layout.item_model_arrow_group) {
            @Override
            protected void convert(BaseViewHolder helper, TreeNode item) {
                //这里都是group,直接强转
                Group group = item.getModel();
                helper.setText(R.id.tv_name, group.getName());

                if (helper.getAdapterPosition() == headerAdapter.getData().size() - 1) {
                    helper.setTextColor(R.id.tv_name, 0xff000000);
                    helper.setGone(R.id.iv_arrow, false);
                } else {
                    helper.setTextColor(R.id.tv_name, 0xff999999);
                    helper.setVisible(R.id.iv_arrow, true);
                }
            }
        };
        headerAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (position == 0) {
                    finish();
                } else {
                    TreeNode item = headerAdapter.getItem(position);
                    selectNode(item);
                }
            }
        });
        headerAdapter.bindToRecyclerView(headerView);
        headerAdapter.setNewData(headerData);

        //设置头部到整个页面
        mAdapter.setHeaderView(headerView);

        selectNode(root.getChildren().get(0));
    }

    private void selectNode(TreeNode node) {
        List<TreeNode> children = node.getChildren();
        mAdapter.setNewData(children);

        //重置Header的数据
        headerData.clear();
        while (node != null) {
            Group group = node.getModel();
            headerData.add(0, group);

            node = node.getParent();
        }
        headerAdapter.notifyDataSetChanged();

        //取头部数据最后一条显示标题
//        TreeNode treeNode = headerData.get(headerData.size() - 1);
//        Group group = treeNode.getModel();
//        mTitle.setText(group.getName());
    }

    @Override
    public void onBackPressed() {
        if (headerData.size() > 2) {
            headerAdapter.remove(headerData.size() - 1);

            TreeNode treeNode = headerData.get(headerData.size() - 1);
            selectNode(treeNode);
        } else {
            finish();
        }
    }

    public static class DemoAdapter extends BaseMultiItemQuickAdapter<TreeNode, BaseViewHolder> {

        /**
         * Same as QuickAdapter#QuickAdapter(Context,int) but with
         * some initialization data.
         *
         * @param data A new list is created out of this one to avoid mutable list
         */
        public DemoAdapter(List<TreeNode> data) {
            super(data);

            addItemType(NodeType.TYPE_GROUP, R.layout.item_model_group);
            addItemType(NodeType.TYPE_MEMBER, R.layout.item_model_member);
        }

        @Override
        protected void convert(BaseViewHolder helper, TreeNode item) {
            switch (item.getItemType()) {
                case NodeType.TYPE_GROUP:
                    Group group = (Group) item;
                    String text = String.format("%s(%d)", group.getName(), group.getNumber());
                    helper.setText(R.id.tv_name, text);
                    break;
                case NodeType.TYPE_MEMBER:
                    Member member = (Member) item;
                    helper.setText(R.id.tv_name, member.getName());
                    helper.setText(R.id.tv_job, member.getJobTitle());
                    break;
                default:
                    break;
            }
        }
    }
}
