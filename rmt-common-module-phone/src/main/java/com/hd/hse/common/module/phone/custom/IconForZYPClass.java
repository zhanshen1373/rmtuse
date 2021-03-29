/**
 * Project Name:hse-common-module-phone
 * File Name:PictureForZYPClass.java
 * Package Name:com.hd.hse.common.component.custom
 * Date:2014年12月25日
 * Copyright (c) 2014, xuxinwen@ushayden.com All Rights Reserved.
 */

package com.hd.hse.common.module.phone.custom;

import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.module.phone.R;
import com.hd.hse.constant.IWorkOrderStatus;
import com.hd.hse.constant.IWorkOrderZypClass;

/**
 * ClassName:PictureForZYPClass ().<br/>
 * Date:     2014年12月25日  <br/>
 *
 * @author xuxinwen
 * @see
 */
public class IconForZYPClass {

    public static int getIconIDByZYPState(String state) throws HDException {
        if (IWorkOrderStatus.APPR.equalsIgnoreCase(state)) {
            // 作业中
            return R.drawable.hd_hse_common_module_zyp_state_appr;
        }
        if (IWorkOrderStatus.CAN.equalsIgnoreCase(state)) {
            // 取消作业
            return R.drawable.hd_hse_common_module_zyp_state_cancle;
        }
        if (IWorkOrderStatus.CLOSE.equalsIgnoreCase(state)) {
            // 关闭
            return R.drawable.hd_hse_common_module_zyp_state_close;
        }
        if (IWorkOrderStatus.NULLIFY.equalsIgnoreCase(state)) {
            // 作废
            return R.drawable.hd_hse_common_module_zyp_state_nullify;
        }
        if (IWorkOrderStatus.INPRG.equalsIgnoreCase(state)) {
            // 审核
            return R.drawable.hd_hse_common_module_zyp_state_check;
        }
        if (IWorkOrderStatus.WAPPR.equalsIgnoreCase(state)) {
            // 草稿
            return R.drawable.hd_hse_common_module_zyp_state_wappr;
        }
        if (IWorkOrderStatus.CQCLOSE.equalsIgnoreCase(state)) {
            // 超期关闭
            return R.drawable.hd_hse_common_module_zyp_state_close;
        }
        if (IWorkOrderStatus.APPAUDITED.equalsIgnoreCase(state)) {
            // 审核
            return R.drawable.hd_hse_common_module_zyp_state_check;
        }
        throw new HDException("IconForZYPClass-->" + ": 没有这这个状态！！！-->" + state);
    }

    /**
     * 根据作业票类型获得相应的图标id
     * getDrawableByZYPClass:(). <br/>
     * date: 2014年12月25日 <br/>
     *
     * @param zypClass
     * @return
     * @author xuxinwen
     */
    public static int getIconIDByZYPClass(String zypClass) {

        if (IWorkOrderZypClass.ZYPCLASS_DHZY.equals(zypClass)) {
            // 动火作业
            return R.drawable.hd_hse_common_module_zyp_type_donghuo_selector;
        }
        if (IWorkOrderZypClass.ZYPCLASS_DZZY.equals(zypClass)) {
            // 吊装作业
            return R.drawable.hd_hse_common_module_zyp_type_diaozhuang_selector;
        }

        if (IWorkOrderZypClass.ZYPCLASS_GCZY.equals(zypClass)) {
            //高处
            return R.drawable.hd_hse_common_module_zyp_type_gaochu_selector;
        }

        if (IWorkOrderZypClass.ZYPCLASS_LSYDZY.equals(zypClass)) {
            //临时用电
            return R.drawable.hd_hse_common_module_zyp_type_linshiyongdian_selector;
        }
        if (IWorkOrderZypClass.ZYPCLASS_SXKJ.equals(zypClass)) {
            //受限空间
            return R.drawable.hd_hse_common_module_zyp_type_shouxiankongjian_selector;
        }

        if (IWorkOrderZypClass.ZYPCLASS_WJZY.equals(zypClass)) {
            //挖掘
            return R.drawable.hd_hse_common_module_zyp_type_wajue_selector;
        }
        if (IWorkOrderZypClass.ZYPCLASS_ZYDP.equals(zypClass)) {
            //大票
            return R.drawable.hd_hse_common_module_zyp_type_zuoyexuke_selector;
        }
        if (IWorkOrderZypClass.ZYPCLASS_GXASSET.equals(zypClass)) {
            //管线
            return R.drawable.hd_hse_common_module_zyp_type_mangban_selector;
        }
        if (IWorkOrderZypClass.ZYPCLASS_SXZY.equals(zypClass)) {
            //射线
            return R.drawable.hd_hse_common_module_zyp_type_fangshe_selector;
        }
        if (IWorkOrderZypClass.ZYPCLASS_DQ1.equals(zypClass)) {
            //dq1
            return R.drawable.hd_hse_common_module_zyp_type_dq1_selector;
        }
        if (IWorkOrderZypClass.ZYPCLASS_DQ2.equals(zypClass)) {
            //dq2
            return R.drawable.hd_hse_common_module_zyp_type_dq2_selector;
        }
        throw new IllegalArgumentException("IconForZYPClass-->" + ": 没有这个作业类型！！！-->" + zypClass);

    }

    /**
     * 根据作业票类型获得相应的Disabled的图标id
     * getDrawableByZYPClass:(). <br/>
     * date: 2014年12月25日 <br/>
     *
     * @param zypClass
     * @return
     * @author xuxinwen
     */
    public static int getDisabledZYPIconIDByZYPClass(String zypClass) {

        if (IWorkOrderZypClass.ZYPCLASS_DHZY.equals(zypClass)) {
            // 动火作业
            return R.drawable.hd_hse_common_module_zyp_type_donghuo_disable;
        }
        if (IWorkOrderZypClass.ZYPCLASS_DZZY.equals(zypClass)) {
            // 吊装作业
            return R.drawable.hd_hse_common_module_zyp_type_diaozhuang_disable;
        }

        if (IWorkOrderZypClass.ZYPCLASS_GCZY.equals(zypClass)) {
            //高处
            return R.drawable.hd_hse_common_module_zyp_type_gaochu_disable;
        }

        if (IWorkOrderZypClass.ZYPCLASS_LSYDZY.equals(zypClass)) {
            //临时用电
            return R.drawable.hd_hse_common_module_zyp_type_linshiyongdian_disable;
        }
        if (IWorkOrderZypClass.ZYPCLASS_SXKJ.equals(zypClass)) {
            //受限空间
            return R.drawable.hd_hse_common_module_zyp_type_shouxiankongjian_disable;
        }

        if (IWorkOrderZypClass.ZYPCLASS_WJZY.equals(zypClass)) {
            //挖掘
            return R.drawable.hd_hse_common_module_zyp_type_wajue_disable;
        }
        if (IWorkOrderZypClass.ZYPCLASS_ZYDP.equals(zypClass)) {
            //大票
            return R.drawable.hd_hse_common_module_zyp_type_zuoyexuke_disable;
        }
        if (IWorkOrderZypClass.ZYPCLASS_GXASSET.equals(zypClass)) {
            //管线
            return R.drawable.hd_hse_common_module_zyp_type_mangban_disable;
        }
        if (IWorkOrderZypClass.ZYPCLASS_SXZY.equals(zypClass)) {
            //射线
            return R.drawable.hd_hse_common_module_zyp_type_fangshe_disable;
        }
        if (IWorkOrderZypClass.ZYPCLASS_DQ1.equals(zypClass)) {
            //dq1
            return R.drawable.hd_hse_common_module_zyp_type_dq1_disable;
        }
        if (IWorkOrderZypClass.ZYPCLASS_DQ2.equals(zypClass)) {
            //dq2
            return R.drawable.hd_hse_common_module_zyp_type_dq2_disable;
        }
        throw new IllegalArgumentException("IconForZYPClass-->" + ": 没有这个作业类型！！！-->" + zypClass);

    }

    public static String getZYPNameByZYPClass(String zypClass) {
        if (IWorkOrderZypClass.ZYPCLASS_DHZY.equals(zypClass)) {
            // 动火作业
            return "动火作业";
        }
        if (IWorkOrderZypClass.ZYPCLASS_DZZY.equals(zypClass)) {
            // 吊装作业
            return "吊装作业";
        }

        if (IWorkOrderZypClass.ZYPCLASS_GCZY.equals(zypClass)) {
            //高处
            return "高处作业";
        }

        if (IWorkOrderZypClass.ZYPCLASS_LSYDZY.equals(zypClass)) {
            //临时用电
            return "临时用电";
        }
        if (IWorkOrderZypClass.ZYPCLASS_SXKJ.equals(zypClass)) {
            //受限空间
            return "受限空间";
        }

        if (IWorkOrderZypClass.ZYPCLASS_WJZY.equals(zypClass)) {
            //挖掘
            return "挖掘作业";
        }
        if (IWorkOrderZypClass.ZYPCLASS_ZYDP.equals(zypClass)) {
            //大票
            return "一般作业";
        }
        if (IWorkOrderZypClass.ZYPCLASS_GXASSET.equals(zypClass)) {
            //管线
            return "管线作业";
        }
        if (IWorkOrderZypClass.ZYPCLASS_SXZY.equals(zypClass)) {
            //射线
            return "射线作业";
        }
        if (IWorkOrderZypClass.ZYPCLASS_DQ1.equals(zypClass)) {
            //dq1
            return "电气一种工作票";
        }
        if (IWorkOrderZypClass.ZYPCLASS_DQ2.equals(zypClass)) {
            //dq2
            return "电气第二种工作票";
        }

        throw new IllegalArgumentException("IconForZYPClass-->" + ": 没有这个作业类型！！！-->" + zypClass);

    }

    /**
     * 分项任务根据作业票类型获得相应的图标id
     * getDrawableByZYPClass:(). <br/>
     * date: 2014年12月25日 <br/>
     *
     * @param zypClass
     * @return
     * @author xuxinwen
     */
    public static int getIconIDByZYPClassItem(String zypClass) {

        if (IWorkOrderZypClass.ZYPCLASS_DHZY.equalsIgnoreCase(zypClass)) {
            // 动火作业
            return R.drawable.hd_hse_common_module_zyp_type_donghuo_selector_item;
        }
        if (IWorkOrderZypClass.ZYPCLASS_DZZY.equalsIgnoreCase(zypClass)) {
            // 吊装作业
            return R.drawable.hd_hse_common_module_zyp_type_diaozhuang_selector_item;
        }

        if (IWorkOrderZypClass.ZYPCLASS_GCZY.equalsIgnoreCase(zypClass)) {
            //高处
            return R.drawable.hd_hse_common_module_zyp_type_gaochu_selector_item;
        }

        if (IWorkOrderZypClass.ZYPCLASS_LSYDZY.equalsIgnoreCase(zypClass)) {
            //临时用电
            return R.drawable.hd_hse_common_module_zyp_type_linshiyongdian_selector_item;
        }
        if (IWorkOrderZypClass.ZYPCLASS_SXKJ.equalsIgnoreCase(zypClass)) {
            //受限空间
            return R.drawable.hd_hse_common_module_zyp_type_shouxiankongjian_selector_item;
        }

        if (IWorkOrderZypClass.ZYPCLASS_WJZY.equalsIgnoreCase(zypClass)) {
            //挖掘
            return R.drawable.hd_hse_common_module_zyp_type_wajue_selector_item;
        }
        if (IWorkOrderZypClass.ZYPCLASS_ZYDP.equalsIgnoreCase(zypClass)) {
            //大票
            return R.drawable.hd_hse_common_module_zyp_type_zuoyexuke_selector_item;
        }
        if (IWorkOrderZypClass.ZYPCLASS_GXASSET.equalsIgnoreCase(zypClass)) {
            //管线
            return R.drawable.hd_hse_common_module_zyp_type_mangban_selector_item;
        }
        if (IWorkOrderZypClass.ZYPCLASS_SXZY.equalsIgnoreCase(zypClass)) {
            //射线
            return R.drawable.hd_hse_common_module_zyp_type_fangshe_selector_item;
        }
        if (IWorkOrderZypClass.ZYPCLASS_DQ1.equalsIgnoreCase(zypClass)) {
            //dq1
            return R.drawable.hd_hse_common_module_zyp_type_dq1_normal_item;
        }
        if (IWorkOrderZypClass.ZYPCLASS_DQ2.equalsIgnoreCase(zypClass)) {
            //dq2
            return R.drawable.hd_hse_common_module_zyp_type_dq2_normal_item;
        }


        throw new IllegalArgumentException("IconForZYPClass-->" + ": 没有这个作业类型！！！-->" + zypClass);

    }

    /**
     * getIconIDByZYPStateDesc:(获取状态描述字段). <br/>
     * date: 2015年8月13日 <br/>
     *
     * @param state
     * @return
     * @throws HDException
     * @author lxf
     */
    public static String getIconIDByZYPStateDesc(String state) {
        if (IWorkOrderStatus.APPR.equals(state)) {
            // 作业中
            return "作业中";
        }
        if (IWorkOrderStatus.CAN.equals(state)) {
            // 取消作业
            return "取消";
        }
        if (IWorkOrderStatus.CLOSE.equals(state)) {
            // 关闭
            return "关闭";
        }
        if (IWorkOrderStatus.NULLIFY.equals(state)) {
            // 作废
            return "作废";
        }
        if (IWorkOrderStatus.INPRG.equals(state)) {
            // 审核
            return "审核";
        }
        if (IWorkOrderStatus.WAPPR.equals(state)) {
            // 草稿
            return "草稿";
        }
        if (IWorkOrderStatus.CQCLOSE.equals(state)) {
            // 超期关闭
            return "超期关闭";
        }
        if (IWorkOrderStatus.APPAUDITED.equals(state)) {
            // 审核
            return "审核";
        }
        return "添加状态";
        //throw new HDException("IconForZYPClass-->"+": 没有这这个状态！！！-->"+state);
    }
}

