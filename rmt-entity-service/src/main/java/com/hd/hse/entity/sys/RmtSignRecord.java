package com.hd.hse.entity.sys;

import com.hd.hse.common.entity.SuperEntity;
import com.hd.hse.entity.common.Image;

import java.util.List;

/**
 * created by yangning on 2017/7/12 09:50.
 * 手签记录
 */

public class RmtSignRecord extends SuperEntity{
    private long apprRecordId;
    private String imageName;
    private List<String> imageList;
    private Image image;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public long getApprRecordId() {
        return apprRecordId;
    }

    public void setApprRecordId(long apprRecordId) {
        this.apprRecordId = apprRecordId;
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
