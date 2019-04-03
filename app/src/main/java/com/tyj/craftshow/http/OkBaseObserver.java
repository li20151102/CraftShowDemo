package com.tyj.craftshow.http;

/**
 * code-time: 2019/3/29
 * code-author: by kyle
 * coder-wechat:
 * exp:
 **/
public abstract  class OkBaseObserver<T> extends BaseObserver<T> {
    public abstract void handleComplete();

    @Override
    public void onComplete() {
        super.onComplete();
        handleComplete();
    }
}
