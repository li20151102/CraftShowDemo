package com.tyj.craftshow.model;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.tyj.craftshow.R;
import com.tyj.craftshow.adapter.ProTitleAdapter;
import com.tyj.craftshow.adapter.ProjectDataAdapter;
import com.tyj.craftshow.base.BaseActivity;
import com.tyj.craftshow.bean.ProTitleBean;
import com.tyj.craftshow.bean.ProjectDataBean;
import com.tyj.craftshow.http.BaseResponse;
import com.tyj.craftshow.http.RetrofitUtil;
import com.tyj.craftshow.util.RxClickUtil;
import com.tyj.craftshow.util.ToastUtil;
import com.tyj.craftshow.util.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

import static com.tyj.craftshow.http.RxSchedulers.compose;

/**
 * @author create by kyle_2019 on 2019/5/20 10:18
 * @package com.tyj.craftshow.model
 * @fileName DemoActivity
 */
public class DemoActivity extends BaseActivity {
    private int page = 1;
    private static final String TAG = "ProjectLibManagementAci";
    private ProjectDataAdapter mProLitsAdapter;
    private ProTitleAdapter mTitleAdapter;
    private Disposable mDispose = null;
    private boolean isLoading = false;

    @BindView(R.id.iv_back)
    ImageView mback;

    @BindView(R.id.title_rcv)
    RecyclerView mTitleRecyclerView;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    @Override
    protected int setLayout() {
        return R.layout.activity_model_demo;
    }

    @Override
    protected void inItView(Bundle savedInstanceState) {
        initView();
    }

    @SuppressLint("CheckResult")
    private void initView() {

        initRecyclerView();
        initTitleRecyclerView();
        setPostData(1, "", new ProTitleBean("市级项目", "none", 0), false);
        RxClickUtil.clicks(mback).subscribe(o -> {
            finish();
        });

    }
    private void initRecyclerView() {

        //设置布局管理器
        mProLitsAdapter = new ProjectDataAdapter(R.layout.item_model_group);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mProLitsAdapter);
        mProLitsAdapter.setOnItemClickListener((adapter, view, position) -> {
            ProjectDataBean item = mProLitsAdapter.getItem(position);
            List<ProTitleBean> list = mTitleAdapter.getData();
            if (item.getType() == 1) {
                ToastUtil.showLongToast("详情");
            } else {
                if (!list.isEmpty()) {
                    ProTitleBean titleLevel = list.get(list.size() - 1);
                    int level = item.getLevel();
                    if (titleLevel.getLevel() == level) {
                        Log.e(TAG, "是点击了同一个列表的两个不同实体导致的");
                        return;
                    }
                }
                setPostData(item.getLevel() + 1, item.getId(), new ProTitleBean(item.getName(), item.getId(), item.getLevel()), false);
            }

        });
    }

    private void initTitleRecyclerView() {
        mTitleRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayout.HORIZONTAL, false));
        mTitleAdapter = new ProTitleAdapter(R.layout.item_model_title_group);
        mTitleRecyclerView.setAdapter(mTitleAdapter);
        mTitleAdapter.setOnItemClickListener((adapter, view, position) -> {
            if (position == mTitleAdapter.getData().size() - 1) {
                Log.e(TAG, "点击的时标题的最后面一个，可以不用管");
                return;
            }
            mProLitsAdapter.setNewData(new ArrayList<>());
            io.reactivex.Observable.just(1).delay(600, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(integer -> {
                        if (isLoading) {
                            return;
                        }

                        if (position == 0) {
                            setPostData(1, "", new ProTitleBean("市级项目", "none", 0), false);
                        } else {
                            ProTitleBean item = mTitleAdapter.getItem(position);
                            List<ProTitleBean> list = new ArrayList<>();
                            for (int i = 0; i < position + 1; i++) {
                                list.add(mTitleAdapter.getItem(i));
                            }
                            mTitleAdapter.setNewData(list);
                            setPostData(item.getLevel() + 1, item.getTitleId(), null, true);
                        }
                    });
        });
    }

    @Override
    public void onBackPressed() {
        if (isLoading) {
            return;
        }
        List<ProTitleBean> data = mTitleAdapter.getData();
        if (data.isEmpty() || data.size() == 1) {
            finish();
        } else {
            mProLitsAdapter.setNewData(new ArrayList<>());
            if (data.size() > 2) {
                ProTitleBean proTitleEntity = data.get(data.size() - 2);
                setPostData(proTitleEntity.getLevel() + 1, proTitleEntity.getTitleId(), null, true);
            } else {
                setPostData(1, "", null, true);
            }
            data.remove(data.size() - 1);
            mTitleAdapter.setNewData(data);
        }
    }

    @SuppressLint("CheckResult")
    public void setPostData(int level, String id, ProTitleBean title, boolean isTitleClick){//请求数据
        if (isLoading) {
            return;
        } else {
            isLoading = true;
        }
        showDialog("正在加载数据,请稍后...");
        Map<String,Object> map = new HashMap<>(15);
        map.put("vo.level", level);
        map.put("vo.id", id);
        map.put("page", page);
        map.put("limit", 50);
        RetrofitUtil.getApiService().queryScreenProjectInfo(map)
                .compose(compose())
                .compose(bindToLifecycle())
                .subscribe(new Observer<BaseResponse<List<ProjectDataBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResponse<List<ProjectDataBean>> listBaseResponse) {
                        isLoading = false;
                        if (listBaseResponse.getData() != null) {
                            if (listBaseResponse.getData().isEmpty()) {
                                ToastUtils.showToast("您点击的 “" + title.getTitleName() + "”没有下一层数据哦!");
                                return;
                            }
                            mProLitsAdapter.setNewData(listBaseResponse.getData());
                            if (!isTitleClick) {
                                if ("none".equals(title.getTitleId())) {
                                    List<ProTitleBean> list = new ArrayList<>();
                                    list.add(title);
                                    mTitleAdapter.setNewData(list);
                                } else {
                                    List<ProTitleBean> data = mTitleAdapter.getData();
                                    data.add(title);
                                    mTitleAdapter.setNewData(data);
                                }
                            }
                        } else {
                            mProLitsAdapter.setNewData(new ArrayList<>());
                        }
                        dismissDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        dismissDialog();
                        isLoading = false;
                        Log.e("DemoActivit",e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        dismissDialog();
                        isLoading = false;
                    }
                });

    }
}
