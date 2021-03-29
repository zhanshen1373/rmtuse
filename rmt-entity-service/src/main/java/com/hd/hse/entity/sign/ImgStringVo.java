package com.hd.hse.entity.sign;

import com.hd.hse.common.entity.SuperEntity;

/**
 * 图片序列化成字符串的对象
 *
 * @author zhangfeng
 * @date 2016年5月5日
 */
public class ImgStringVo extends SuperEntity {

    /**
     * 图片UUID
     */
    private String uuid;
    /**
     * 图片记录主键
     */
    private Long attachdataid;
    /**
     * 图片名称
     */
    private String att_d_name;
    /**
     * 图片序列化的字符串
     */
    private String imgStr;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Long getAttachdataid() {
        return attachdataid;
    }

    public void setAttachdataid(Long attachdataid) {
        this.attachdataid = attachdataid;
    }

    public String getAtt_d_name() {
        return att_d_name;
    }

    public void setAtt_d_name(String att_d_name) {
        this.att_d_name = att_d_name;
    }

    public String getImgStr() {
        return imgStr;
    }

    public void setImgStr(String imgStr) {
        this.imgStr = imgStr;
    }
}
