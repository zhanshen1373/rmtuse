/**
 * File:    HseSysImage.java
 * Author:  hd
 * Company:
 * Created: 2014-09-25 11:58:43
 * Purpose: 定义数据类 HseSysImage
 * NOTE:    该文件为自动生成，请勿手工改动！
 */

package com.hd.hse.entity.common;

import com.hd.hse.common.field.DBField;
import com.hd.hse.common.table.DBTable;

import java.util.List;

/**
 * @pdOid 1879ac36-863a-4b6e-8e2d-f8b12370b428
 */
@DBTable(tableName = "hse_sys_image")
public class Image extends com.hd.hse.common.entity.SuperEntity {
    /**
     * 表明
     *
     * @pdOid 38431966-e4b7-4365-9415-1b53dfeec2e4
     */
    @DBField
    private String tablename;
    /**
     * 表主键
     *
     * @pdOid dc76aa30-d752-4ebf-ab66-d8b2b06a0817
     */
    @DBField
    private String tableid;
    /**
     * 路径
     *
     * @pdOid 2678cdcf-78d4-42b8-86ea-51a141434929
     */
    @DBField
    private String imagepath;
    /**
     * 名称
     *
     * @pdOid 4b3994c2-2da3-4855-98b7-44650c4e16ac
     */
    /**
     * 创建时间
     *
     * @pdOid 17081003-4c19-469a-8c12-46846892116e
     */
    @DBField
    private String createDate;
    /**
     * 创建人
     *
     * @pdOid 31df5686-818d-4ecf-9a88-867cc920814b
     */
    @DBField
    private long createUser;
    /**
     * 主键
     *
     * @pdOid 842963f9-1654-4451-bbc6-d8e491f45999
     */
    @DBField(id = true)
    private String id;
    /**
     * 对应的功能
     *
     * @pdOid 0ef7944c-0b22-410e-8829-0f3cb5b4e40a
     */
    @DBField
    private String funCode;
    /**
     * 控件类型
     *
     * @pdOid fb5c6d4a-b057-43ab-8143-ee620fc7d634
     */
    @DBField
    private Integer contype;
    /**
     * createusername:TODO(创建人名字).
     *
     * @pdOid 82dbdddf-0c2f-447b-9d81-9ad795505418
     */
    @DBField
    private String createUsername;


    /**
     * 分项任务id
     */
    private Long subtaskId;
    /**
     * 主任务id
     */
    private Long taskId;

    /**
     * 页面类型
     */
    private Integer tabType;
    /**
     * 图片集合base64编码
     */
    private List<String> imageList;
    /**
     * 图片名字
     */
    private String imageName;
    private long imageId;

    public long getImageId() {
        return imageId;
    }

    public void setImageId(long imageId) {
        this.imageId = imageId;
    }

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }


    /**
     * @return the contype
     * @pdOid dd21f3aa-66aa-4979-a26d-8a9854b52009
     */
    public Integer getContype() {
        return contype;
    }

    /**
     * @param contype the contype to set
     * @pdOid edc09235-fd53-46ec-9cef-edd30018daa0
     */
    public void setContype(Integer contype) {
        this.contype = contype;
    }

    /**
     * 设置 表明 该字段是：表明
     *
     * @param tablename
     * @pdOid d9b663ab-80ea-4bba-bad9-3e15c715eddb
     */
    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    /**
     * 获取 表明 该字段是：表明
     *
     * @pdOid 14ed9263-6a12-4343-82f5-e636b50c861a
     */
    public String getTablename() {
        return tablename;
    }

    /**
     * 设置 表主键 该字段是：表主键
     *
     * @param tableid
     * @pdOid b52de189-5c1c-4a43-a74f-2c6c218ffd73
     */
    public void setTableid(String tableid) {
        this.tableid = tableid;
    }

    /**
     * 获取 表主键 该字段是：表主键
     *
     * @pdOid f8559e36-a92b-426a-8006-fe0d08fe49a8
     */
    public String getTableid() {
        return tableid;
    }

    /**
     * 设置 路径 该字段是：路径
     *
     * @param imagepath
     * @pdOid 18e464d1-e88d-4773-9cde-91d1e806bf45
     */
    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    /**
     * 获取 路径 该字段是：路径
     *
     * @pdOid e670ce60-104f-44ae-8c55-1791a2e40061
     */
    public String getImagepath() {
        return imagepath;
    }


    /**
     * 设置 主键 该字段是：主键
     *
     * @param id
     * @pdOid 3f0a3e0a-7b3e-4b05-80a9-85498dcd2b56
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取 主键 该字段是：主键
     *
     * @pdOid 9d68427e-161f-49bc-b420-99505b53c478
     */
    public String getId() {
        return id;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(long createUser) {
        this.createUser = createUser;
    }

    public String getFunCode() {
        return funCode;
    }

    public void setFunCode(String funCode) {
        this.funCode = funCode;
    }

    public String getCreateUsername() {
        return createUsername;
    }

    public void setCreateUsername(String createUsername) {
        this.createUsername = createUsername;
    }

    public Long getSubtaskId() {
        return subtaskId;
    }

    public void setSubtaskId(Long subtaskId) {
        this.subtaskId = subtaskId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Integer getTabType() {
        return tabType;
    }

    public void setTabType(Integer tabType) {
        this.tabType = tabType;
    }
}