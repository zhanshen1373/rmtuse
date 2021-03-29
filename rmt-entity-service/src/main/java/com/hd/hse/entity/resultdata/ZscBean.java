package com.hd.hse.entity.resultdata;

import com.hd.hse.common.entity.SuperEntity;

import java.io.Serializable;
import java.util.List;

public class ZscBean {


    /**
     * dictvos : null
     * mainvo : [{"headVO":{"PHD_KNOWLEDGEWIN_M":{"tableName":"phd_knowledgewin","endtime":"2020-04-05 14:34:50","starttime":"2020-03-03 14:34:21","attach_attachShowList":[{"tableName":"sy_attach_data","columnValues":null,"dataStatus":0,"ver":1,"created_by":1000,"created_dt":"2020-03-27 14:24:26","updated_by":1000,"updated_dt":"2020-03-27 14:24:26","df":0,"tenantid":1,"ts":null,"attachdataid":1000000002720,"att_d_uuid":"n2ZQpgS3hsiPNPruOXpH5Bz2tFjT","att_d_name":"心累.png","att_d_remarks":null,"att_d_type":".png","att_d_url":"/myfiles-dev/PHD_KNOWLEDGEWIN/attach/1/2020/03/","att_d_size":"27783","modulecode":"phd","func_code":"PHD_KNOWLEDGEWIN","att_ownerid":8,"att_colcode":"attach","is_file_delete":1,"thumbnail_url":"_Thumbnail","record_created_dt":"2020-03-27 13:58:11","previewfile_url":"_Preview","addition_file_size":null}],"release_orgid":1000000000006,"type":"insp","created_dt":"2020-03-27 13:58:11","id":8,"release_orgname":"化肥一厂","release_dt":"2020-03-27 14:34:26","attach":1,"name":"这两个字在习近平心中重千钧","release_by":1000,"release_by_name":"系统管理员","filetype":"DOC","updated_dt":"2020-03-27 14:24:35","dataStatus":0}},"attachDataMap":null,"bodyVOs":null,"attachDataVOs":null},{"headVO":{"PHD_KNOWLEDGEWIN_M":{"tableName":"phd_knowledgewin","endtime":"2020-03-27 14:23:01","starttime":"2020-03-01 14:22:57","attach_attachShowList":[{"tableName":"sy_attach_data","columnValues":null,"dataStatus":0,"ver":1,"created_by":1000,"created_dt":"2020-03-27 14:40:49","updated_by":1000,"updated_dt":"2020-03-27 14:40:49","df":0,"tenantid":1,"ts":null,"attachdataid":1000000002740,"att_d_uuid":"rs3l6ijuj8qWO10HBKNGGBJf1l4w","att_d_name":"作业许可离线办票设计.docx","att_d_remarks":null,"att_d_type":".docx","att_d_url":"/myfiles-dev/PHD_KNOWLEDGEWIN/attach/1/2020/03/","att_d_size":"791295","modulecode":"phd","func_code":"PHD_KNOWLEDGEWIN","att_ownerid":9,"att_colcode":"attach","is_file_delete":1,"thumbnail_url":null,"record_created_dt":"2020-03-27 14:22:41","previewfile_url":null,"addition_file_size":null}],"release_orgid":1000000000006,"type":"insp","created_dt":"2020-03-27 14:22:41","id":9,"release_orgname":"化肥一厂","release_dt":"2020-03-27 14:34:29","attach":1,"name":"只是名称","release_by":1000,"release_by_name":"系统管理员","filetype":"DOC","updated_dt":"2020-03-27 14:40:57","dataStatus":0}},"attachDataMap":null,"bodyVOs":null,"attachDataVOs":null}]
     */

    private Object dictvos;
    private List<MainvoBean> mainvo;

    public Object getDictvos() {
        return dictvos;
    }

    public void setDictvos(Object dictvos) {
        this.dictvos = dictvos;
    }

    public List<MainvoBean> getMainvo() {
        return mainvo;
    }

    public void setMainvo(List<MainvoBean> mainvo) {
        this.mainvo = mainvo;
    }

    public static class MainvoBean extends SuperEntity {
        /**
         * headVO : {"PHD_KNOWLEDGEWIN_M":{"tableName":"phd_knowledgewin","endtime":"2020-04-05 14:34:50","starttime":"2020-03-03 14:34:21","attach_attachShowList":[{"tableName":"sy_attach_data","columnValues":null,"dataStatus":0,"ver":1,"created_by":1000,"created_dt":"2020-03-27 14:24:26","updated_by":1000,"updated_dt":"2020-03-27 14:24:26","df":0,"tenantid":1,"ts":null,"attachdataid":1000000002720,"att_d_uuid":"n2ZQpgS3hsiPNPruOXpH5Bz2tFjT","att_d_name":"心累.png","att_d_remarks":null,"att_d_type":".png","att_d_url":"/myfiles-dev/PHD_KNOWLEDGEWIN/attach/1/2020/03/","att_d_size":"27783","modulecode":"phd","func_code":"PHD_KNOWLEDGEWIN","att_ownerid":8,"att_colcode":"attach","is_file_delete":1,"thumbnail_url":"_Thumbnail","record_created_dt":"2020-03-27 13:58:11","previewfile_url":"_Preview","addition_file_size":null}],"release_orgid":1000000000006,"type":"insp","created_dt":"2020-03-27 13:58:11","id":8,"release_orgname":"化肥一厂","release_dt":"2020-03-27 14:34:26","attach":1,"name":"这两个字在习近平心中重千钧","release_by":1000,"release_by_name":"系统管理员","filetype":"DOC","updated_dt":"2020-03-27 14:24:35","dataStatus":0}}
         * attachDataMap : null
         * bodyVOs : null
         * attachDataVOs : null
         */

        private HeadVOBean headVO;
        private Object attachDataMap;
        private Object bodyVOs;
        private Object attachDataVOs;

        public HeadVOBean getHeadVO() {
            return headVO;
        }

        public void setHeadVO(HeadVOBean headVO) {
            this.headVO = headVO;
        }

        public Object getAttachDataMap() {
            return attachDataMap;
        }

        public void setAttachDataMap(Object attachDataMap) {
            this.attachDataMap = attachDataMap;
        }

        public Object getBodyVOs() {
            return bodyVOs;
        }

        public void setBodyVOs(Object bodyVOs) {
            this.bodyVOs = bodyVOs;
        }

        public Object getAttachDataVOs() {
            return attachDataVOs;
        }

        public void setAttachDataVOs(Object attachDataVOs) {
            this.attachDataVOs = attachDataVOs;
        }

        public static class HeadVOBean implements Serializable {
            /**
             * PHD_KNOWLEDGEWIN_M : {"tableName":"phd_knowledgewin","endtime":"2020-04-05 14:34:50","starttime":"2020-03-03 14:34:21","attach_attachShowList":[{"tableName":"sy_attach_data","columnValues":null,"dataStatus":0,"ver":1,"created_by":1000,"created_dt":"2020-03-27 14:24:26","updated_by":1000,"updated_dt":"2020-03-27 14:24:26","df":0,"tenantid":1,"ts":null,"attachdataid":1000000002720,"att_d_uuid":"n2ZQpgS3hsiPNPruOXpH5Bz2tFjT","att_d_name":"心累.png","att_d_remarks":null,"att_d_type":".png","att_d_url":"/myfiles-dev/PHD_KNOWLEDGEWIN/attach/1/2020/03/","att_d_size":"27783","modulecode":"phd","func_code":"PHD_KNOWLEDGEWIN","att_ownerid":8,"att_colcode":"attach","is_file_delete":1,"thumbnail_url":"_Thumbnail","record_created_dt":"2020-03-27 13:58:11","previewfile_url":"_Preview","addition_file_size":null}],"release_orgid":1000000000006,"type":"insp","created_dt":"2020-03-27 13:58:11","id":8,"release_orgname":"化肥一厂","release_dt":"2020-03-27 14:34:26","attach":1,"name":"这两个字在习近平心中重千钧","release_by":1000,"release_by_name":"系统管理员","filetype":"DOC","updated_dt":"2020-03-27 14:24:35","dataStatus":0}
             */

            private PHDKNOWLEDGEWINMBean PHD_KNOWLEDGEWIN_M;

            public PHDKNOWLEDGEWINMBean getPHD_KNOWLEDGEWIN_M() {
                return PHD_KNOWLEDGEWIN_M;
            }

            public void setPHD_KNOWLEDGEWIN_M(PHDKNOWLEDGEWINMBean PHD_KNOWLEDGEWIN_M) {
                this.PHD_KNOWLEDGEWIN_M = PHD_KNOWLEDGEWIN_M;
            }

            public static class PHDKNOWLEDGEWINMBean implements Serializable{
                /**
                 * tableName : phd_knowledgewin
                 * endtime : 2020-04-05 14:34:50
                 * starttime : 2020-03-03 14:34:21
                 * attach_attachShowList : [{"tableName":"sy_attach_data","columnValues":null,"dataStatus":0,"ver":1,"created_by":1000,"created_dt":"2020-03-27 14:24:26","updated_by":1000,"updated_dt":"2020-03-27 14:24:26","df":0,"tenantid":1,"ts":null,"attachdataid":1000000002720,"att_d_uuid":"n2ZQpgS3hsiPNPruOXpH5Bz2tFjT","att_d_name":"心累.png","att_d_remarks":null,"att_d_type":".png","att_d_url":"/myfiles-dev/PHD_KNOWLEDGEWIN/attach/1/2020/03/","att_d_size":"27783","modulecode":"phd","func_code":"PHD_KNOWLEDGEWIN","att_ownerid":8,"att_colcode":"attach","is_file_delete":1,"thumbnail_url":"_Thumbnail","record_created_dt":"2020-03-27 13:58:11","previewfile_url":"_Preview","addition_file_size":null}]
                 * release_orgid : 1000000000006
                 * type : insp
                 * created_dt : 2020-03-27 13:58:11
                 * id : 8
                 * release_orgname : 化肥一厂
                 * release_dt : 2020-03-27 14:34:26
                 * attach : 1
                 * name : 这两个字在习近平心中重千钧
                 * release_by : 1000
                 * release_by_name : 系统管理员
                 * filetype : DOC
                 * updated_dt : 2020-03-27 14:24:35
                 * dataStatus : 0
                 */

                private String tableName;
                private String endtime;
                private String starttime;
                private long release_orgid;
                private String type;
                private String created_dt;
                private long id;
                private String release_orgname;
                private String release_dt;
                private long attach;
                private String name;
                private long release_by;
                private String release_by_name;
                private String filetype;
                private String updated_dt;
                private long dataStatus;
                private List<AttachAttachShowListBean> attach_attachShowList;

                public String getTableName() {
                    return tableName;
                }

                public void setTableName(String tableName) {
                    this.tableName = tableName;
                }

                public String getEndtime() {
                    return endtime;
                }

                public void setEndtime(String endtime) {
                    this.endtime = endtime;
                }

                public String getStarttime() {
                    return starttime;
                }

                public void setStarttime(String starttime) {
                    this.starttime = starttime;
                }

                public long getRelease_orgid() {
                    return release_orgid;
                }

                public void setRelease_orgid(long release_orgid) {
                    this.release_orgid = release_orgid;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getCreated_dt() {
                    return created_dt;
                }

                public void setCreated_dt(String created_dt) {
                    this.created_dt = created_dt;
                }

                public long getId() {
                    return id;
                }

                public void setId(long id) {
                    this.id = id;
                }

                public String getRelease_orgname() {
                    return release_orgname;
                }

                public void setRelease_orgname(String release_orgname) {
                    this.release_orgname = release_orgname;
                }

                public String getRelease_dt() {
                    return release_dt;
                }

                public void setRelease_dt(String release_dt) {
                    this.release_dt = release_dt;
                }

                public long getAttach() {
                    return attach;
                }

                public void setAttach(long attach) {
                    this.attach = attach;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public long getRelease_by() {
                    return release_by;
                }

                public void setRelease_by(long release_by) {
                    this.release_by = release_by;
                }

                public String getRelease_by_name() {
                    return release_by_name;
                }

                public void setRelease_by_name(String release_by_name) {
                    this.release_by_name = release_by_name;
                }

                public String getFiletype() {
                    return filetype;
                }

                public void setFiletype(String filetype) {
                    this.filetype = filetype;
                }

                public String getUpdated_dt() {
                    return updated_dt;
                }

                public void setUpdated_dt(String updated_dt) {
                    this.updated_dt = updated_dt;
                }

                public long getDataStatus() {
                    return dataStatus;
                }

                public void setDataStatus(long dataStatus) {
                    this.dataStatus = dataStatus;
                }

                public List<AttachAttachShowListBean> getAttach_attachShowList() {
                    return attach_attachShowList;
                }

                public void setAttach_attachShowList(List<AttachAttachShowListBean> attach_attachShowList) {
                    this.attach_attachShowList = attach_attachShowList;
                }

                public static class AttachAttachShowListBean implements Serializable {
                    /**
                     * tableName : sy_attach_data
                     * columnValues : null
                     * dataStatus : 0
                     * ver : 1
                     * created_by : 1000
                     * created_dt : 2020-03-27 14:24:26
                     * updated_by : 1000
                     * updated_dt : 2020-03-27 14:24:26
                     * df : 0
                     * tenantid : 1
                     * ts : null
                     * attachdataid : 1000000002720
                     * att_d_uuid : n2ZQpgS3hsiPNPruOXpH5Bz2tFjT
                     * att_d_name : 心累.png
                     * att_d_remarks : null
                     * att_d_type : .png
                     * att_d_url : /myfiles-dev/PHD_KNOWLEDGEWIN/attach/1/2020/03/
                     * att_d_size : 27783
                     * modulecode : phd
                     * func_code : PHD_KNOWLEDGEWIN
                     * att_ownerid : 8
                     * att_colcode : attach
                     * is_file_delete : 1
                     * thumbnail_url : _Thumbnail
                     * record_created_dt : 2020-03-27 13:58:11
                     * previewfile_url : _Preview
                     * addition_file_size : null
                     */

                    private String tableName;
                    private Object columnValues;
                    private int dataStatus;
                    private int ver;
                    private long created_by;
                    private String created_dt;
                    private long updated_by;
                    private String updated_dt;
                    private int df;
                    private int tenantid;
                    private Object ts;
                    private long attachdataid;
                    private String att_d_uuid;
                    private String att_d_name;
                    private Object att_d_remarks;
                    private String att_d_type;
                    private String att_d_url;
                    private String att_d_size;
                    private String modulecode;
                    private String func_code;
                    private int att_ownerid;
                    private String att_colcode;
                    private int is_file_delete;
                    private String thumbnail_url;
                    private String record_created_dt;
                    private String previewfile_url;
                    private Object addition_file_size;

                    public String getTableName() {
                        return tableName;
                    }

                    public void setTableName(String tableName) {
                        this.tableName = tableName;
                    }

                    public Object getColumnValues() {
                        return columnValues;
                    }

                    public void setColumnValues(Object columnValues) {
                        this.columnValues = columnValues;
                    }

                    public int getDataStatus() {
                        return dataStatus;
                    }

                    public void setDataStatus(int dataStatus) {
                        this.dataStatus = dataStatus;
                    }

                    public int getVer() {
                        return ver;
                    }

                    public void setVer(int ver) {
                        this.ver = ver;
                    }

                    public long getCreated_by() {
                        return created_by;
                    }

                    public void setCreated_by(long created_by) {
                        this.created_by = created_by;
                    }

                    public String getCreated_dt() {
                        return created_dt;
                    }

                    public void setCreated_dt(String created_dt) {
                        this.created_dt = created_dt;
                    }

                    public long getUpdated_by() {
                        return updated_by;
                    }

                    public void setUpdated_by(long updated_by) {
                        this.updated_by = updated_by;
                    }

                    public String getUpdated_dt() {
                        return updated_dt;
                    }

                    public void setUpdated_dt(String updated_dt) {
                        this.updated_dt = updated_dt;
                    }

                    public int getDf() {
                        return df;
                    }

                    public void setDf(int df) {
                        this.df = df;
                    }

                    public int getTenantid() {
                        return tenantid;
                    }

                    public void setTenantid(int tenantid) {
                        this.tenantid = tenantid;
                    }

                    public Object getTs() {
                        return ts;
                    }

                    public void setTs(Object ts) {
                        this.ts = ts;
                    }

                    public long getAttachdataid() {
                        return attachdataid;
                    }

                    public void setAttachdataid(long attachdataid) {
                        this.attachdataid = attachdataid;
                    }

                    public String getAtt_d_uuid() {
                        return att_d_uuid;
                    }

                    public void setAtt_d_uuid(String att_d_uuid) {
                        this.att_d_uuid = att_d_uuid;
                    }

                    public String getAtt_d_name() {
                        return att_d_name;
                    }

                    public void setAtt_d_name(String att_d_name) {
                        this.att_d_name = att_d_name;
                    }

                    public Object getAtt_d_remarks() {
                        return att_d_remarks;
                    }

                    public void setAtt_d_remarks(Object att_d_remarks) {
                        this.att_d_remarks = att_d_remarks;
                    }

                    public String getAtt_d_type() {
                        return att_d_type;
                    }

                    public void setAtt_d_type(String att_d_type) {
                        this.att_d_type = att_d_type;
                    }

                    public String getAtt_d_url() {
                        return att_d_url;
                    }

                    public void setAtt_d_url(String att_d_url) {
                        this.att_d_url = att_d_url;
                    }

                    public String getAtt_d_size() {
                        return att_d_size;
                    }

                    public void setAtt_d_size(String att_d_size) {
                        this.att_d_size = att_d_size;
                    }

                    public String getModulecode() {
                        return modulecode;
                    }

                    public void setModulecode(String modulecode) {
                        this.modulecode = modulecode;
                    }

                    public String getFunc_code() {
                        return func_code;
                    }

                    public void setFunc_code(String func_code) {
                        this.func_code = func_code;
                    }

                    public int getAtt_ownerid() {
                        return att_ownerid;
                    }

                    public void setAtt_ownerid(int att_ownerid) {
                        this.att_ownerid = att_ownerid;
                    }

                    public String getAtt_colcode() {
                        return att_colcode;
                    }

                    public void setAtt_colcode(String att_colcode) {
                        this.att_colcode = att_colcode;
                    }

                    public int getIs_file_delete() {
                        return is_file_delete;
                    }

                    public void setIs_file_delete(int is_file_delete) {
                        this.is_file_delete = is_file_delete;
                    }

                    public String getThumbnail_url() {
                        return thumbnail_url;
                    }

                    public void setThumbnail_url(String thumbnail_url) {
                        this.thumbnail_url = thumbnail_url;
                    }

                    public String getRecord_created_dt() {
                        return record_created_dt;
                    }

                    public void setRecord_created_dt(String record_created_dt) {
                        this.record_created_dt = record_created_dt;
                    }

                    public String getPreviewfile_url() {
                        return previewfile_url;
                    }

                    public void setPreviewfile_url(String previewfile_url) {
                        this.previewfile_url = previewfile_url;
                    }

                    public Object getAddition_file_size() {
                        return addition_file_size;
                    }

                    public void setAddition_file_size(Object addition_file_size) {
                        this.addition_file_size = addition_file_size;
                    }
                }
            }
        }
    }
}
