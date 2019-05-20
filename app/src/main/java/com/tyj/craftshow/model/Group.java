package com.tyj.craftshow.model;

/**
 * @author create by kyle_2019 on 2019/5/20 10:17
 * @package com.tyj.craftshow.model
 * @fileName Group
 */
public class Group extends TreeNode {
    private String name;
    private int number;

    public Group() {
        super(NodeType.TYPE_GROUP);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
