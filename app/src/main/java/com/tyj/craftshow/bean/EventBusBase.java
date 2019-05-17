package com.tyj.craftshow.bean;

/**
 * @author create by kyle_2019 on 2019/4/9 9:14
 * @package com.tyj.craftshow
 * @fileName EventBusBase
 */
public class EventBusBase {
    private String msg;

    public EventBusBase(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
