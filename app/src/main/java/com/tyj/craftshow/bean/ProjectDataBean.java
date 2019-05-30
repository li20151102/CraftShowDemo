package com.tyj.craftshow.bean;

import java.io.Serializable;

/**
 * @author create by kyle_2019 on 2019/5/20 9:34
 * @package com.tyj.craftshow.bean
 * @fileName ProjectDataBean
 */
public class ProjectDataBean implements Serializable {


    /**
     * id : b7e8acf3-7878-11e9-a7f6-7cd30ab8a5d8
     * level : 3
     * money : 0
     * name : 哈哈
     * type : 0
     * “confirmStatus”: 1,
     */

    private String id;
    private int level;
    private int money;
    private String name;
    private int confirmStatus;
    private int type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getConfirmStatus() {
        return confirmStatus;
    }

    public void setConfirmStatus(int confirmStatus) {
        this.confirmStatus = confirmStatus;
    }
}
