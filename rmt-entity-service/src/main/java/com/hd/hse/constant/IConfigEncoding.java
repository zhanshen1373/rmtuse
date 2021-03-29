/**
 * Project Name:hse-entity-service
 * File Name:IConfigEncoding.java
 * Package Name:com.hd.hse.constant
 * Date:2014年10月23日
 * Copyright (c) 2014, zhaofeng@ushayden.com All Rights Reserved.
 */
package com.hd.hse.constant;

/**
 * ClassName: IConfigEncoding ()<br/>
 * date: 2014年10月23日  <br/>
 *
 * @author zhaofeng
 */
public interface IConfigEncoding {

    /**
     * SP:TODO(现场核查-功能编码).
     */
    public static final String SP = "SP01";
    /**
     * FC:TODO(现场复查-功能编码).
     */
    public static final String FC = "FC01";
    /**
     * YQ:TODO(延期-功能编码).
     */
    public static final String YQ = "YQ01";
    /**
     * GB:TODO(关闭取消-功能编码).
     */
    public static final String GB = "GB01";
    /**
     * QX:TODO(取消).
     */
    public static final String QX = "QX01";
    /**
     * JJB:TODO(交接班-作业人变更).
     */
    public static final String JJB01 = "JJB01";
    /**
     * JJB:TODO(交接班-监护人变更).
     */
    public static final String JJB02 = "JJB02";
    /**
     * MEASURE_TYPE:TODO(表示措施类别).
     */
    public static final int MEASURE_TYPE = 1;


    public static final int MEASURE_TYPELIETWO = -2;
    /**
     * MEASURE_TYPELIETWO:TODO(批量显示人名).
     */
    public static final int MEASURE_TYPELIETHREE = -3;
    /**
     * MEASURE_TYPELIEONE:TODO(逐条).
     */
    public static final int MEASURE_TYPELIEONE = -1;

    /**
     * MEASURE_TYPELIETHREE:TODO(显示措施).
     */
    public static final int MEASURE_TYPELIEZERO = 0;

    /**
     * MEASURE_TYPELIEFOUR:TODO(危害+措施显示).
     */
    public static final int MEASURE_TYPELIEFOUR = 1;

    /**
     * MEASURE_TYPEONEBYNOE:TODO(逐条).
     */
    public static final int MEASURE_TYPEONEBYNOE = 10;

    /**
     * MEASURE_TYPEONEBYNOE:TODO(非逐条).
     */
    public static final int MEASURE_TYPENOONEBYNOE = 11;
    /**
     * MEASURE_TYPEONEBYNOE:TODO(逐条批量).
     */
    public static final int MEASURE_TYPEONEBYNOEBATCH = 12;

    /**
     * HARM_TYPE:TODO(危害类别).
     */
    public static final int HARM_TYPE = 3;
    /**
     * PPE_TYPE:TODO(个人防护类别).
     */
    public static final int PPE_TYPE = 2;
    /**
     * ENERGY_TYPE:TODO(能量隔离类别).
     */
    public static final int ENERGY_TYPE = 4;
    /**
     * QT_TYPE:TODO(气体检测类别).
     */
    public static final int GAS_TYPE = 6;
    /**
     * GB_TYPE:TODO(关闭类别的).
     */
    public static final int GB_TYPE = 8;
    /**
     * QX_TYPE:TODO(取消类别).
     */
    public static final int QX_TYPE = 9;
    /**
     * HQ_TYPE:TODO(会签类别).
     */
    public static final int SIGN_TYPE = 10;

    /**
     * YQ_SIGN_TYPE:TODO(延期确认类别).
     */
    public static final int YQ_SIGN_TYPE = 11;
    /**
     * PROMISE_TYPE:TODO(承诺信息显示类别).
     */
    public static final int PROMISE_TYPE = 22;
    /**
     * END_TYPE:TODO(作业结束界面类别).
     */
    public static final int END_TYPE = 23;

    /**
     * TIMELINE_TYPE:TODO(事件轴作业票类别).
     */
    public static final int TIMELINE_TYPE = 24;
    /**
     * TEMPELE_TYPE:TODO(临时用电设备清单类别).
     */
    public static final int TEMPELE_TYPE = 25;
    /**
     * TEMPELE_TYPE:TODO(气体检测类别).
     */
    public static final int Gas_Detect_TYPE = 26;
    /**
     * TEMPELE_TYPE:TODO(吊装信息类别).
     */
    public static final int DiaoZhuang_Detail_TYPE = 27;
    /**
     * TIMELINE_TYPE:TODO(事件轴作业票类别).
     */
    public static final int ASSIGN_RECORD_TYPE = 28;
    /**
     * TEMPELE_TYPE:TODO(温度压力类别).
     */
    public static final int TEMPERATURE_PRESSURE_TYPE = 29;
    /**
     * QTJC_NUM:TODO(审批-气体检测动态编码).
     */
    public static final String SP_GAS_NUM = "PGTYPE005";
    /**
     * SP_ENERGY_NUM:TODO(能量隔离界面编码).
     */
    public static final String SP_ENERGY_NUM = "PGTYPE004";
    /**
     * SP_SIGN_NUM:TODO(会签界面编码).
     */
    public static final String SP_SIGN_NUM = "PGTYPE009";

    /**
     * FC_GAS_NUM:TODO(复查-气体检测界面编码).
     */
    public static final String FC_GAS_NUM = "PGTYPE104";

    /**
     * YQ_SIGN_NUM:TODO(延期确认的编码).
     */
    public static final String YQ_SIGN_NUM = "PGTYPE204";

    /**
     * JJB_WORK_PERSON_CHANGE_NUM:TODO(交接班-作业人变更界面编码).
     */
    public static final String JJB_WORK_PERSON_CHANGE_NUM = "PGTYPE501";

    /**
     * JJB_GUARDIAN_CHANGE_NUM:TODO(交接班-监护人变更界面编码).
     */
    public static final String JJB_GUARDIAN_CHANGE_NUM = "PGTYPE502";

    /**
     * RESULT_OKCODE:TODO(返回ok结果).
     */
    public static final int RESULT_OKCODE = 1;
    /**
     * RESULT_NOCODE:TODO(返回NO结果).
     */
    public static final int RESULT_NOCODE = 0;
    /**
     * REQUESTCODE:TODO(界面请求编码).
     */
    public static final int REQUESTCODE = 1;

    /**
     * ISTEST:TODO(false  刷卡的，true 不需要刷卡).
     */
    public static final boolean ISTEST = true;
    /**
     * ZY_TYPE:TODO(作业控件类型).
     */
    public static final int ZY_TYPE = 20;

    /**
     * CS:TODO(措施确认).
     */
    public static final String CS = "CS01";

    /**
     * JB:TODO(接班).
     */
    public static final String JB = "JB01";

    /**
     * JS:TODO(结束).
     */
    public static final String JS = "JS01";

    /**
     * LL:TODO(作业票浏览).
     */
    public static final String LL = "LL01";

}
