package com.tyj.craftshow.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author create by kyle_2019 on 2019/5/20 10:13
 * @package com.tyj.craftshow.model
 * @fileName TreeNode
 */
public class TreeNode implements MultiItemEntity {
    private int type;
    private TreeNode parent;
    private List<TreeNode> children = new ArrayList<>();

    public TreeNode(@NodeType int type) {
        this.type = type;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void add(TreeNode node) {
        children.add(node);

        node.setParent(this);
    }

    public void remove(TreeNode node) {
        children.remove(node);
    }

    public int size() {
        return children.size();
    }

    /**
     * 多条目关键
     *
     * @return
     */
    @Override
    public int getItemType() {
        return type;
    }

    public <T extends TreeNode> T getModel() {
        return (T) this;
    }
}
