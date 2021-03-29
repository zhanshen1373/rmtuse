/**
 * Project Name:hse-cbs-app
 * File Name:SystemProperty.java
 * Package Name:com.hd.hse.pub.system
 * Date:2014��9��2��
 * Copyright (c) 2014, longgang@ushayden.com All Rights Reserved.
 */

package com.hd.hse.system;

import android.os.Environment;

import com.hd.hse.business.util.SYSConstant;
import com.hd.hse.business.webservice.WebConfig;
import com.hd.hse.common.exception.DaoException;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.logger.LogUtils;
import com.hd.hse.dao.BaseDao;
import com.hd.hse.dao.connection.IConnection;
import com.hd.hse.dao.factory.ConnectionSourceManager;
import com.hd.hse.dao.result.MapResult;
import com.hd.hse.dao.source.IConnectionSource;
import com.hd.hse.entity.common.PersonCard;
import com.hd.hse.entity.common.PositionCard;
import com.hd.hse.entity.common.SysActionAgeConfig;
import com.hd.hse.entity.sys.AppModule;
import com.hd.hse.service.workorder.queryinfo.QueryWorkInfo;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

/**
 * ClassName: SystemProperty (系统属性)<br/>
 * date: 2014年9月3日 <br/>
 *
 * @author lg
 */
public class SystemProperty {

    private static Logger logger = LogUtils.getLogger(SystemProperty.class);

    // 启动系统是，给赋值
    private static String rootpath = Environment.getExternalStorageDirectory()
            + File.separator + "rmtapp";

    private String rootDBpath;

    private String rootVersionPath;

    private String rootImagePath;

    /**
     * rootPhotoPath:TODO(照片路径).
     */
    private String rootPhotoPath;
    private String rootApkPath;

    private static SystemProperty instance;

    /**
     * loginPerson:TODO(登录人).
     */
    private PersonCard loginPerson;

    /**
     * position:TODO(位置卡，刷位置卡时记录).
     */
    private PositionCard positionCard;

    /**
     * webconfig:TODO(上传、下载数据配置).
     */
    private WebConfig webDataConfig;

    /**
     * imageupconfig:TODO(上传、下载图片配置).
     */
    private WebConfig webImageConfig;

    /**
     * padmac:TODO(获取MAC地址).
     */
    private String padmac;

    private static List<AppModule> listMainAppModule;

    private List<String> imageList;


    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }

    private SystemProperty() {

    }

    public static SystemProperty getSystemProperty() {
        if (StringUtils.isEmpty(rootpath)) {
            //
        }
        if (instance == null)
            instance = new SystemProperty();
        return instance;
    }

    public static SystemProperty getSystemProperty(String mrootpath) {
        rootpath = mrootpath;
        if (StringUtils.isEmpty(rootpath)) {
            //
        }
        if (instance == null)
            instance = new SystemProperty();
        return instance;
    }

    public String getRootpath() {
        return rootpath;
    }

    /**
     * getRootDBpath:(数据库存放路径). <br/>
     * date: 2014年9月12日 <br/>
     *
     * @return
     * @author lxf
     */
    public String getRootDBpath() {
        if (StringUtils.isEmpty(rootDBpath)) {
            rootDBpath = rootpath;
        }
        return rootDBpath;
    }

    /**
     * getRootDBpath:(设置数据库存放路径). <br/>
     * date: 2014年9月12日 <br/>
     *
     * @return
     * @author lxf
     */
    public void setRootDBpath(String rootdbPath) {
        rootpath = rootdbPath;
    }

    /**
     * getRootVersionPath:(升级版本保存路径). <br/>
     * date: 2014年9月12日 <br/>
     *
     * @return
     * @author lxf
     */
    public String getRootVersionPath() {
        if (StringUtils.isEmpty(rootVersionPath)) {
            rootVersionPath = rootpath + File.separator + "version";
        }
        return rootVersionPath;
    }

    /**
     * getRootImagePath:(照片保存路径). <br/>
     * date: 2014年9月12日 <br/>
     *
     * @return
     * @author lxf
     */
    public String getRootImagePath() {
        if (StringUtils.isEmpty(rootImagePath)) {
            rootImagePath = rootpath + File.separator + "image";
        }
        return rootImagePath;

    }

    /**
     * getRootPhotoPath:(照片路径). <br/>
     * date: 2014年9月13日 <br/>
     *
     * @return
     * @author lg
     */
    public String getRootPhotoPath() {
        if (StringUtils.isEmpty(rootPhotoPath)) {
            rootPhotoPath = rootpath + File.separator + "photo";
        }
        return rootPhotoPath;
    }

    public String getRootApkPath() {
        if (StringUtils.isEmpty(rootApkPath)) {
            rootApkPath = rootpath + File.separator + "apk";
        }
        return rootApkPath;
    }


    /**
     * getSysDateTime:(系统当前时间). <br/>
     * date: 2014年9月4日 <br/>
     *
     * @author lg
     * @return格式：yyyy-MM-dd HH:mm:ss
     */
    // @SuppressLint("SimpleDateFormat")
    public String getSysDateTime() {
        SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                Locale.CHINA);
        return dtFormat.format(new Date());
    }

    public String getSysSHDateTime() {
        SimpleDateFormat dtFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH时mm分ss秒", Locale.CHINA);
        return dtFormat.format(new Date());
    }

    public PersonCard getLoginPerson() {
        return loginPerson;
    }

    public void setLoginPerson(PersonCard loginPerson) {
        this.loginPerson = loginPerson;
    }

    @SuppressWarnings("unchecked")
    public WebConfig getWebDataConfig() {
        if (null == webDataConfig) {
            BaseDao dao = new BaseDao();
            // 读取IP
            String sql = "select syscode,sysurl from hse_sys_config where syscode in('inurl','outurl') and enable=1;";
            // 读取url
            String sql2 = "select syscode,maximoplatform,oldplatform from  hse_sys_config_sub where syscode='dataurl';";
            // 读取超时时间
            String sql3 = "select syscode,sysurl from hse_sys_config where syscode='timeout';";
            String url = null;
            HashMap<String, Object> configMainRes = null;
            HashMap<String, Object> confingSubRes = null;
            HashMap<String, Object> confingTimeOut = null;
            try {
                // 读取主表配置
                configMainRes = (HashMap<String, Object>) dao.executeQuery(sql,
                        new MapResult());
                if (null != configMainRes) {
                    Object obj = configMainRes.get("sysurl");
                    if (obj != null) {
                        url = configMainRes.get("sysurl").toString();
                    }
                }
                // 读取子表配置
                confingSubRes = (HashMap<String, Object>) dao.executeQuery(
                        sql2, new MapResult());
                //
                confingTimeOut = (HashMap<String, Object>) dao.executeQuery(
                        sql3, new MapResult());
            } catch (DaoException e) {
                logger.error("查询配置信息报错", e);
            }
            if (StringUtils.isEmpty(url)) {
                logger.error("没有配置访问地址");
            }
            url = "http://" + url + File.separator;
            if (null == confingSubRes || confingSubRes.size() == 0) {
                logger.error("请准确配置hse_sys_config表数据。");
            } else {
                int timeout = 10000;
                if (null != confingTimeOut && confingTimeOut.size() != 0) {
                    timeout = Integer.parseInt(confingTimeOut.get("sysurl")
                            .toString());
                }
                webDataConfig = WebConfig.newInstance();
                webDataConfig.setUrlpart(confingSubRes.get("maximoplatform").toString());
                webDataConfig.setTimeOut(timeout);
                webDataConfig.setUrl(url
                        + confingSubRes.get("maximoplatform").toString());
                webDataConfig.setClientType(SYSConstant.CLIENTHESSION);
                webDataConfig.setDataAnalyzetype(SYSConstant.ANALYZYGSON);
            }
        }
        return webDataConfig;
    }

    /**
     * clearWebConfig:(清空网络配置属性). <br/>
     * date: 2014年9月4日 <br/>
     *
     * @author lg
     */
    public void clearWebConfig() {
        this.webDataConfig = null;
        this.webImageConfig = null;
    }

    public WebConfig getWebImageConfig() {
        return webImageConfig;
    }

    /**
     * getPadmac:(获取mac地址). <br/>
     * date: 2014年10月10日 <br/>
     *
     * @return
     * @author lxf
     */
    @SuppressWarnings("unchecked")
    public String getPadmac() {
        if (StringUtils.isEmpty(padmac)) {
            HashMap<String, Object> configMac = null;
            BaseDao dao = new BaseDao();
            // 读取IP
            String sql = "select syscode,sysurl from hse_sys_config where syscode ='padmac';";
            // 读取主表配置
            try {
                configMac = (HashMap<String, Object>) dao.executeQuery(sql,
                        new MapResult());
            } catch (DaoException e) {
                logger.error("读取MAC地址报错", e);
            }
            if (null != configMac) {
                Object obj = configMac.get("sysurl");
                if (obj != null) {
                    padmac = configMac.get("sysurl").toString();
                }
            }
            // 如果还是空，此时需要生产
            if (StringUtils.isEmpty(padmac)) {
                padmac = getUUID();
                sql = "insert into hse_sys_config(sysdesc,syscode,sysurl)values('mac地址','padmac','"
                        + padmac + "');";
                IConnectionSource conSrc = null;
                IConnection connection = null;
                try {
                    conSrc = ConnectionSourceManager.getInstance()
                            .getJdbcPoolConSource();
                    connection = conSrc.getConnection();
                    dao.executeUpdate(connection, sql);
                    connection.commit();
                } catch (SQLException e) {
                    logger.error("插入MAC地址报错", e);
                } catch (DaoException e) {
                    logger.error("插入MAC地址报错", e);
                } finally {
                    if (connection != null) {
                        try {
                            conSrc.releaseConnection(connection);
                        } catch (SQLException e) {

                        }
                    }
                }

            }
        }
        return padmac;
    }

    private String getHashValue(HashMap<String, String> hash, String key,
                                boolean enableMaximo) {
        String str = null;
        if (hash.containsKey("syscode")
                && hash.get("syscode").equalsIgnoreCase(key)) {
            if (hash.containsKey("maximoplatform")) {
                str = hash.get("maximoplatform");
            }
        }
        return str;
    }

    public PositionCard getPositionCard() {
        return positionCard;
    }

    public void setPositionCard(PositionCard positionCard) {
        this.positionCard = positionCard;
    }

    /**
     * getUUID:(获取唯一值). <br/>
     * date: 2014年9月19日 <br/>
     *
     * @return
     * @author lxf
     */
    private static String getUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    // 获取位置卡超时间
    private static Float locationTimeOut = new Float(-1.0);

    /**
     * getPadmac:(获取位置卡超时时间). <br/>
     * date: 2014年11月12日 <br/>
     *
     * @return
     * @author lxf
     */
    public static Float getLocationTimeOut() {
        if (locationTimeOut == -1) {
            QueryWorkInfo queryWorkInfo = new QueryWorkInfo();
            try {
                SysActionAgeConfig sysaction = queryWorkInfo
                        .querySysActionAgeConfig("BACK_SERVICE_AUTO_TIMEOUT_LOCATION_EXITE");
                if (sysaction != null) {
                    locationTimeOut = sysaction.getSx();
                }
            } catch (HDException e) {
                logger.error("读取位置卡超时时间出错", e);
            }
        }
        return locationTimeOut;
    }

    // 获取锁屏超时时间
    private static Float LockScreenExiteTimeOut = new Float(-1.0);

    /**
     * getPadmac:(获取锁屏超时时间). <br/>
     * date: 2014年11月12日 <br/>
     *
     * @return
     * @author lxf
     */
    public static Float getTimeoutExiteTime() {
        if (LockScreenExiteTimeOut == -1.0) {
            QueryWorkInfo queryWorkInfo = new QueryWorkInfo();
            try {
                SysActionAgeConfig sysaction = queryWorkInfo
                        .querySysActionAgeConfig("BACK_SERVICE_AUTO_TIMEOUT_EXITE");
                if (sysaction != null) {
                    LockScreenExiteTimeOut = sysaction.getSx();
                }
            } catch (HDException e) {
                logger.error("读取锁屏超时时间出错", e);
            }

        }
        return LockScreenExiteTimeOut;
    }

    /**
     * getAppModulelist:(获取公共模块信息). <br/>
     * date: 2015年1月7日 <br/>
     *
     * @return
     * @author lxf
     */
    public static List<AppModule> getMainAppModulelist(String type) {
        if (listMainAppModule == null) {
            listMainAppModule = new ArrayList<AppModule>();
            AppModule appModule1=new AppModule("hse-main-phone-app","主界面","hd_hse_main_phone_home","com.hd.hse.main.phone.ui.event.homepage.OnSiteCkApp",1,null,0);
            listMainAppModule.add(appModule1);

            AppModule appModule2=new AppModule("rmt-assignstaff-phone-app","人员指定","hd_hse_main_phone_assignstaff","rmt.assignstaff.phone.app.event.homepage.AssignApp",2,"assign",0);
            listMainAppModule.add(appModule2);

            AppModule appModule3=new AppModule("hse-scene-phone-app","措施确认","hd_hse_nav_model_osc","com.hd.hse.scene.phone.ui.event.homepage.SceneApp",3,"approve",0);
            listMainAppModule.add(appModule3);

            AppModule appModule4=new AppModule("hse-resc-phone-app","措施复查","hd_hse_nav_model_osr","com.hd.hse.scene.phone.ui.event.homepage.SceneReApp",4,"review",0);
            listMainAppModule.add(appModule4);

            AppModule appModule5=new AppModule("hse-scw-phone-app","任务接班","hd_hse_nav_model_sca","com.hd.hse.scw.phone.ui.event.homepage.ShiftChangeApp",5,"relieve",0);
            listMainAppModule.add(appModule5);

            AppModule appModule6=new AppModule("hse-end-phone-app","任务结束","hd_hse_nav_model_cc","hd.hse.end.phone.ui.event.homepage.OnSiteCkApp",6,"stop",0);
            listMainAppModule.add(appModule6);

            AppModule appModule7=new AppModule("hse-wov-phone-app","任务浏览","hd_hse_nav_model_swc","com.hd.hse.wov.phone.ui.event.homepage.MainWovApp",7,"browse",0);
            listMainAppModule.add(appModule7);

            AppModule appModule8=new AppModule("hse-gas-phone-app","气体检测","hd_hse_nav_model_qtjc","com.hd.hse.scene.phone.ui.event.homepage.GasDetectApp",8,"gas",0);
            listMainAppModule.add(appModule8);

            AppModule appModule9=new AppModule("rmt-question-phone-app","问题登记","hd_hse_nav_model_ss","rmt.question.phone.app.event.homepage.QstnApp",9,"qstn",0);
            listMainAppModule.add(appModule9);

            AppModule appModule10=new AppModule("rmt-leaderappr-phone-app","领导审批","hd_hse_nav_model_leader","rmt.leaderappr.phone.app.homepage.LeaderApprApp",10,"leader",0);
            listMainAppModule.add(appModule10);

            AppModule appModule11=new AppModule("rmt-statistics-phone-app","统计分析","hd_hse_nav_model_tjfx","rmt.statistics.phone.app.event.homepage.StatisticsApp",11,"statistics",0);
            listMainAppModule.add(appModule11);

            AppModule appModule12=new AppModule("rmt-delay-phone-app","作业延期","hd_hse_nav_model_dly","rmt.delay.phone.app.event.homepage.DelayApp",13,"delay",0);
            listMainAppModule.add(appModule12);

            AppModule appModule13=new AppModule("rmt-pause-phone-app","作业暂停","hd_hse_nav_model_workzt","rmt.pause.phone.app.event.homepage.PauseApp",14,"pause",0);
            listMainAppModule.add(appModule13);

            AppModule appModule14=new AppModule("rmt-resume-phone-app","作业恢复","hd_hse_nav_model_workhf","rmt.resume.phone.app.event.homepage.ResumeApp",12,"reset",0);
            listMainAppModule.add(appModule14);

            AppModule appModule15=new AppModule("rmt-authorize-phone-app","作业授权","hd_hse_nav_model_worksq","rmt.authorize.phone.app.event.homepage.AuthorizeApp",15,"authorize",0);
            listMainAppModule.add(appModule15);

            AppModule appModule16=new AppModule("rmt-zsc-phone-app","知识窗","hd_hse_nav_model_zsc","rmt.zsc.phone.app.event.homepage.ZscApp",16,"zsc",0);
            listMainAppModule.add(appModule16);
        }
            /*try {
                BusinessAction action = new BusinessAction();

                IQueryRelativeConfig config = new QueryRelativeConfig();
                RelationTableName newRelaction = config.getRelativeObj(IRelativeEncoding.SHOWMODELSTR);
                String functionStr = "";
                if (newRelaction != null && !StringUtils.isEmpty(newRelaction.getSys_fuction())) {
                    functionStr = UtilTools.convertToSqlString(newRelaction
                            .getSys_fuction());
                }
                action = new BusinessAction();
                String sql = "enabled = 1 and type='" + type + "' ";
                if (!StringUtils.isEmpty(functionStr)) {
                    sql += " and (modelnum in " + functionStr + " or ifnull(modelnum,'')='') ";
                }
                sql += " order by layoutorder asc;";
                // 数据库中注册的模块
                List<SuperEntity> tempListSuper = action.queryEntities(AppModule.class, null, sql);
                List temp = tempListSuper;
                listMainAppModule = temp;
                //屏蔽掉问题清单
                *//*
                for (int i = 0; i < listMainAppModule.size(); i++) {
                    AppModule appModule = listMainAppModule.get(i);
                    if ("statistics".equals(appModule.getModelnum())) {
                        listMainAppModule.remove(appModule);
                        break;
                    }
                }
               *//*
            } catch (HDException e) {
                logger.error(e.getMessage(), e);
            }
        }*/
        return listMainAppModule;
    }
}
