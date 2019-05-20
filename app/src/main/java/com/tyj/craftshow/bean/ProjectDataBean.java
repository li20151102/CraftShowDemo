package com.tyj.craftshow.bean;

import java.io.Serializable;

/**
 * @author create by kyle_2019 on 2019/5/20 9:34
 * @package com.tyj.craftshow.bean
 * @fileName ProjectDataBean
 */
public class ProjectDataBean implements Serializable {


    /**
     * confirmStatus : 1
     * deleteMark : 0
     * investment : 5591
     * isSub : 0
     * level : 1
     * parentId : 1
     * parentName :
     * projectDefinition : 7152942a-7552-11e9-a7f6-7cd30ab8a5d8
     * projectId : 71529417-7552-11e9-a7f6-7cd30ab8a5d8
     * projectName : 测试
     */

    private int confirmStatus;
    private int deleteMark;
    private int investment;
    private int isSub;
    private int level;
    private String parentId;
    private String parentName;
    private String projectDefinition;
    private String projectId;
    private String projectName;

    public int getConfirmStatus() {
        return confirmStatus;
    }

    public void setConfirmStatus(int confirmStatus) {
        this.confirmStatus = confirmStatus;
    }

    public int getDeleteMark() {
        return deleteMark;
    }

    public void setDeleteMark(int deleteMark) {
        this.deleteMark = deleteMark;
    }

    public int getInvestment() {
        return investment;
    }

    public void setInvestment(int investment) {
        this.investment = investment;
    }

    public int getIsSub() {
        return isSub;
    }

    public void setIsSub(int isSub) {
        this.isSub = isSub;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getProjectDefinition() {
        return projectDefinition;
    }

    public void setProjectDefinition(String projectDefinition) {
        this.projectDefinition = projectDefinition;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Override
    public String toString() {
        return "ProjectDataBean{" +
                "confirmStatus=" + confirmStatus +
                ", deleteMark=" + deleteMark +
                ", investment=" + investment +
                ", isSub=" + isSub +
                ", level=" + level +
                ", parentId='" + parentId + '\'' +
                ", parentName='" + parentName + '\'' +
                ", projectDefinition='" + projectDefinition + '\'' +
                ", projectId='" + projectId + '\'' +
                ", projectName='" + projectName + '\'' +
                '}';
    }
}
