package com.tyj.craftshow.bean;

import java.io.Serializable;

/**
 * @author create by kyle_2019 on 2019/5/28 10:37
 * @package com.tyj.craftshow.bean
 * @fileName ProTitleBean
 */
public class ProTitleBean implements Serializable {

    private String titleName;

    private String titleId;

    private int level;

    public ProTitleBean(String titleName, String titleId,int level) {
        this.titleName = titleName;
        this.titleId = titleId;
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getTitleId() {
        return titleId;
    }

    public void setTitleId(String titleId) {
        this.titleId = titleId;
    }
}
