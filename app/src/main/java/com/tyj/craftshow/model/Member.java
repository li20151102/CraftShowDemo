package com.tyj.craftshow.model;

/**
 * @author create by kyle_2019 on 2019/5/20 10:16
 * @package com.tyj.craftshow.model
 * @fileName Member
 */
public class Member extends TreeNode {
    private String name;
    private String jobTitle;

    public Member() {
        super(NodeType.TYPE_MEMBER);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
}
