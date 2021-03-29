/**
 * Project Name:hse-entity-service
 * File Name:IWorkOrderZypClass.java
 * Package Name:com.hd.hse.constant
 * Date:2014年10月23日
 * Copyright (c) 2014, longgang@ushayden.com All Rights Reserved.
 */

package com.hd.hse.constant;

/**
 * ClassName:IWorkOrderZypClass (作业票类型).<br/>
 * Date:     2014年10月23日  <br/>
 * @author Administrator
 * @version
 * @see
 */
public interface IWorkOrderZypClass {
    // 作业票类型
    /**
     * ZYPCLASS_DHZY:TODO(动火).
     */
    public static final String ZYPCLASS_DHZY = "hot";// 动火
    /**
     * ZYPCLASS_SXKJ:TODO(受限空间).
     */
    public static final String ZYPCLASS_SXKJ = "cfd";// 受限空间
    /**
     * ZYPCLASS_WJZY:TODO(挖掘).
     */
    public static final String ZYPCLASS_WJZY = "dig";// 挖掘
    /**
     * ZYPCLASS_GCZY:TODO(高处).
     */
    public static final String ZYPCLASS_GCZY = "high";// 高处
    /**
     * ZYPCLASS_DZZY:TODO(吊装).
     */
    public static final String ZYPCLASS_DZZY = "hst";// 吊装
    /**
     * ZYPCLASS_GXASSET:TODO(管线).
     */
    public static final String ZYPCLASS_GXASSET = "ppl";// 管线
    /**
     * ZYPCLASS_LSYDZY:TODO(临时用电).
     */
    public static final String ZYPCLASS_LSYDZY = "elec";// 临时用电
    /**
     * ZYPCLASS_ZYDP:TODO(大票).
     */
    public static final String ZYPCLASS_ZYDP = "gen";// 大票
    /**
     * ZYPCLASS_PTZY:TODO(普通票（适用于大庆炼化、抚顺石化）).
     */
    public static final String ZYPCLASS_PTZY = "zylx98";// 普通票（适用于大庆炼化、抚顺石化）
    /**
     * ZYPCLASS_JCP:TODO(进车票（适用于抚顺石化）).
     */
    public static final String ZYPCLASS_JCP = "zylx95";// 进车票（适用于抚顺石化）
    /**
     * ZYPCLASS_SXZY:TODO(射线).
     */
    public static final String ZYPCLASS_SXZY = "ray";// 射线
    /**
     * 电气一种票
     */
    public static final String ZYPCLASS_DQ1 = "dq1";// 电气一种票

    /**
     * 电气二种票
     */
    public static final String ZYPCLASS_DQ2 = "dq2";// 电气二种票

    /**
     * ZYPCLASS_JSA:TODO(jsa分析类型(适用于乌石化)).
     */
    public static final String ZYPCLASS_JSA = "zylx96";// jsa分析类型(适用于乌石化)
    /**
     * ZYPCLASS_QT:TODO(其他（适用于大港石化）).
     */
    public static final String ZYPCLASS_QT = "zylx14";// 其他（适用于大港石化）
    /**
     * ZYPCLASS_FXBG:TODO(风险报告).
     */
    public static final String ZYPCLASS_FXBG = "fxbg";// 风险报告
}

