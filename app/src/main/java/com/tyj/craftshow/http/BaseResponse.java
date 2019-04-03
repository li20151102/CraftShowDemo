package com.tyj.craftshow.http;

/**
 * Created by kyle on 2019/3/29.
 * wechat：
 * exp：后台返回数据的结构体
 **/

public class BaseResponse<T> {

    private String message;
    private String result;
    private T data;

    //todo 代表请求成功
    public boolean isSuccess(){
        return "true".equals(result);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
