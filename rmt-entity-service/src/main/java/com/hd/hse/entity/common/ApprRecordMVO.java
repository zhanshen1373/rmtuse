package com.hd.hse.entity.common;

import com.hd.hse.common.entity.SuperEntity;

import java.util.List;

/**
 * @author YaoSone
 * @date 2017-7-5
 **/

public class ApprRecordMVO extends SuperEntity {

    //需要保存审批记录id
    private Long apprRecordId;

    public Long getApprRecordId() {
        return apprRecordId;
    }

    public void setApprRecordId(Long apprRecordId) {
        this.apprRecordId = apprRecordId;
    }

    //图片内容
    private List<String> imageList;

    //图片名字
    private String imageName;
    //attach_id
    private Long  imageId;

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    private Long subtaskId;
    private String tabType;
    private String createUser;
    private String createUsername;
    private String createDate;
    private String funCode;

    public Long getSubtaskId() {
        return subtaskId;
    }

    public void setSubtaskId(Long subtaskId) {
        this.subtaskId = subtaskId;
    }

    public String getTabType() {
        return tabType;
    }

    public void setTabType(String tabType) {
        this.tabType = tabType;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateUsername() {
        return createUsername;
    }

    public void setCreateUsername(String createUsername) {
        this.createUsername = createUsername;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getFunCode() {
        return funCode;
    }

    public void setFunCode(String funCode) {
        this.funCode = funCode;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }


    public List<String> getImageList() {

        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }



}
