/**
 * Project Name:hse-common-component
 * File Name:IEventType.java
 * Package Name:com.hd.hse.common.component.event
 * Date:2014年9月26日
 * Copyright (c) 2014, longgang@ushayden.com All Rights Reserved.
 */

package com.hd.hse.common.component.phone.event;

/**
 * ClassName:IEventType (事件类型).<br/>
 * Date: 2014年9月26日 <br/>
 *
 * @author lg
 * @see
 */
public interface IEventType {

    /**
     * HARM_PPE_CLICK:TODO(危害、防护装备单击事件).
     */
    public static final int HARM_PPE_CLICK = 1;

    /**
     * WORK_LIST_CLICK:TODO(作业票列表，点击事件).
     */
    public static final int WORK_LIST_CLICK = 2;

    /**
     * PULL_DOWN_REFRESH:TODO(下拉刷新事件).
     */
    public static final int PULL_DOWN_REFRESH = 3;
    /**
     * UP_GLIDE_REFRESH:TODO(上滑刷新事件).
     */
    public static final int UP_GLIDE_REFRESH = 4;
    /**
     * ACTIONBAR_MORE_CLICK(状态栏更多，点击事件).
     */
    public static final int ACTIONBAR_MORE_CLICK = 5;
    /**
     * ACTIONBAR_CALNEL_CLICK:TODO(状态栏取消,点击事件).
     */
    public static final int ACTIONBAR_CALNEL_CLICK = 6;
    /**
     * ACTIONBAR_DELETE_CLICK:TODO(状态栏删除,点击事件).
     */
    public static final int ACTIONBAR_DELETE_CLICK = 7;
    /**
     * ACTIONBAR_CHOICE_CLICK:TODO(状态栏选择，点击事件).
     */
    public static final int ACTIONBAR_CHOICE_CLICK = 8;
    /**
     * ACTIONBAR_ADD_CLICK:TODO(状态栏新增，点击事件).
     */
    public static final int ACTIONBAR_ADD_CLICK = 9;
    /**
     * ACTIONBAR_UPLOAD_CLICK:TODO(状态栏上传，点击事件).
     */
    public static final int ACTIONBAR_UPLOAD_CLICK = 10;

    /**
     * ACTIONBAR_UPLOAD_CLICK:TODO(状态栏下载，点击事件).
     */
    public static final int ACTIONBAR_DOWNLOAD_CLICK = 11;
    /**
     * ACTIONBAR_UPLOAD_CLICK:TODO(状态栏退回，点击事件).
     */
    public static final int ACTIONBAR_RETURN_CLICK = 12;
    /**
     * ACTIONBAR_UPLOAD_CLICK:TODO(状态栏照相机，点击事件).
     */
    public static final int ACTIONBAR_PHOTOGRAPH_CLICK = 13;
    /**
     * ACTIONBAR_UPLOAD_CLICK:TODO(状态栏作废，点击事件).
     */
    public static final int ACTIONBAR_INVAILD_CLICK = 14;
    /**
     * ACTIONBAR_UPLOAD_CLICK:TODO(状态栏搜索，点击事件).
     */
    public static final int ACTIONBAR_SEARCH_CLICK = 15;
    /**
     * ACTIONBAR_UPLOAD_CLICK:TODO(状态栏左边返回，点击事件).
     */
    public static final int ACTIONBAR_BACK_CLICK = 16;
    /**
     * ACTIONBAR_UPLOAD_CLICK:TODO(状态栏左边更多，点击事件).
     */
    public static final int ACTIONBAR_LEFTMORE_CLICK = 17;
    /**
     * ACTIONBAR_UPLOAD_CLICK:TODO(状态栏左边叉退回，点击事件).
     */
    public static final int ACTIONBAR_XBACK_CLICK = 18;
    /**
     * ACTIONBAR_UPLOAD_CLICK:TODO(审批按钮，点击事件).
     */
    public static final int EXAMINE_EXAMINE_ClICK = 19;
    /**
     * ACTIONBAR_UPLOAD_CLICK:TODO(审批中刷卡人，点击事件).
     */
    public static final int EXAMINE_EDIT_ClICK = 20;

    /**
     * DOWN_WORK_LIST_SHOW:TODO(获取作业票列，监听表事件).
     */
    public static final int DOWN_WORK_LIST_SHOW = 21;

    /**
     * DOWN_WORK_LIST_LOAD:TODO(下载作业票，监听事件).
     */
    public static final int DOWN_WORK_LIST_LOAD = 22;
    /**
     * ET_SEARCH_CLICK:TODO(搜索框，点击事件).
     */
    public static final int ET_SEARCH_CLICK = 23;
    /**
     * ACTIONBAR_UPLOAD_CLICK:TODO(无审批人时的保存按钮，点击事件).
     */
    public static final int EXAMINE_SAVE_ClICK = 24;

    /**
     * SIGN_APPROVEL_CLICK:TODO(签发审核按钮事件).
     */
    public static final int SIGN_APPROVEL_CLICK = 25;

    /**
     * SING_SHOWPEOPLE_ClICK:TODO(签发点击人员信息事件).
     */
    public static final int SING_SHOWPEOPLE_ClICK = 26;

    /**
     * STEP_CHECK_VP_CHANGED:TODO(逐条审核弹窗滑动).
     */
    public static final int STEP_CHECK_VP_CHANGED = 30;

    // 导航栏 item 点击事件.
    final int NAVIGATION_ITEM_CLICK = 31;

    // SelectableGridView 的item 点击事件
    final int SELECTABLE_GRIDVIEW_ITEM_CLICK = 32;
    // 措施分类点击事件
    final int MEASURE_TYPE_CLICK = 33;

    // 措施审核标签的点击事件
    final int TAB_TYPE_CLICK = 34;

    // 措施审核标签的长点击事件
    final int TAB_TYPE_LONG_CLICK = 35;

    /**
     * CLOSE_CLICK:TODO(关闭事件).
     */
    public final static int CLOSE_CLICK = 36;
    /**
     * CANCEL_CLICK:TODO(取消事件).
     */
    public final static int CANCEL_CLICK = 37;

    /**
     * WORKORDER_AUDIT:TODO(作业票现场核查).
     */
    public final static int WORKORDER_AUDIT = 38;

    /**
     * WORKORDER_AUDIT:TODO(域单选事件).
     */
    public final static int SINGLE_ITEM = 39;

    /**
     * CHECKBOX_CLICK:TODO(checbox点击事件).
     */
    public final static int CHECKBOX_CLICK = 40;

    /**
     * ACTIONBAR_POPWINDOW_TV01_CLICK:TODO(导航栏pop01).
     */
    public final static int ACTIONBAR_POPWINDOW_TV01_CLICK = 41;
    /**
     * ACTIONBAR_POPWINDOW_TV01_CLICK:TODO(导航栏pop02).
     */
    public final static int ACTIONBAR_POPWINDOW_TV02_CLICK = 42;

    /**
     * ACTIONBAR_UPLOAD_CLICK:TODO(审批中,审批环节获得焦点事件).
     */
    public static final int EXAMINE_EDIT_GETFOCUS = 43;

    /**
     * POPUPWINDOW_CHOICE_MULTIPLE:TODO(弹出框多选点击或单击确定事件).
     */
    public static final int POPUPWINDOW_CHOICE_MULTIPLEORSINGLE = 44;
    /**
     * ACTIONBAR_UPLOAD_CLICK:TODO(状态栏右边作业票浏览，点击事件).
     */
    public static final int ACTIONBAR_SEETICKET_CLICK = 45;

    /**
     * ACTION_QTJC_SYNCHRONOUS:TODO(气体检测同步点击事件).
     */
    public static final int ACTION_QTJC_SYNCHRONOUS = 46;

    /**
     * ACTION_MEASURE_PEOPLE_LONGCLICK:TODO(措施确认人长按事件).
     */
    public static final int ACTION_MEASURE_PEOPLE_LONGCLICK = 47;

    /**
     * ACTION_POINTER_CHECK_STATUS:TODO(用户点击状态改变).
     */
    public static final int ACTION_POINTER_CHECK_STATUS = 48;

    /**
     * ACTIONBAR_TITLE_CLICK:TODO(导航栏标题).
     *
     * @author wenlin 2014.12.24 下午
     */
    public static final int ACTIONBAR_TITLE_CLICK = 49;
    /**
     * ACTIONBAR_LEVELTWO_MORE_CLICK:TODO(详细页面中的导航栏菜单).
     */
    public static final int ACTIONBAR_LEVELTWO_MORE_CLICK = 50;

    /**
     * NAVIGATION_ITME_SINGLE_CLICK:TODO(PagerSlidingTabStrip中Item的单击事件).
     */
    public static final int NAVIGATION_ITME_SINGLE_CLICK = 51;
    /**
     * MENU_LEVELTWO_PHOTOMANAGER_CLICK:TODO(照片管理).
     */
    public static final int MENUBAR_LEVELTWO_PHOTOMANAGER_CLICK = 52;
    /**
     * ACTIONBAR_SUBTITLE_CLICK:TODO(导航栏副标题点击).
     */
    public static final int ACTIONBAR_SUBTITLE_CLICK = 53;
    /**
     * MENUBAR_SELECTALL_CLICK:TODO(全部选中).
     */
    public static final int MENUBAR_SELECTALL_CLICK = 54;

    /**
     * MENUBAR_SELECTCANEL_CLICK:TODO(全部取消选取).
     */
    public static final int MENUBAR_SELECTCANEL_CLICK = 55;
    /**
     * EXAMINE_TOEXAMINE_ClICK:TODO(审批环节点击跳转).
     */
    public static final int EXAMINE_TOEXAMINE_ClICK = 56;

    /**
     * SIGN_CLICK:TODO(会签用户单击事件).
     */
    public static final int SIGN_CLICK = 57;
    /**
     * CLOSEORCANCEL_CLICK:TODO(关闭或取消原因点击事件).
     */
    public static final int CLOSEORCANCEL_CLICK = 58;

    /**
     * 措施确认界面底部菜单 向上按钮点击事件
     */
    public static final int BOTTOM_UPWARD_CLICK = 59;

    /**
     * 措施确认界面底部菜单 向下按钮点击事件
     */
    public static final int BOTTOM_DOWNWARD_CLICK = 60;

    /**
     * 措施确认界面底部菜单 确认按钮点击事件
     */
    public static final int BOTTOM_BUTTON_CLICK = 61;

    /**
     * 措施确认界面底部菜单 对号图标选中
     */
    public static final int BOTTOM_SELECT_CHECKED = 62;

    /**
     * 措施确认界面底部菜单 差图标选中
     */
    public static final int BOTTOM_UNSELECT_CHECKED = 63;

    /**
     * 措施确认界面底部菜单 圈图标选中
     */
    public static final int BOTTOM_CIRCLE_CHECKED = 64;

    /**
     * 措施审核界面页签导航的点击事件
     */
    public static final int TOP_CIRCLE_CHECKED = 65;
    /**
     * ACTIONBAR_UPLOAD_CLICK:TODO(审批按钮，点击事件后).
     */
    public static final int EXAMINE_EXAMINE_ClICK_AFTER = 66;

    /**
     * ACTIONBAR_UPLOAD_CLICK:TODO(无审批人时的保存按钮，点击事件后).
     */
    public static final int EXAMINE_SAVE_ClICK_AFTER = 67;

    /**
     * ACTIONBAR_UPLOAD_CLICK:TODO(读卡返回结果).
     */
    public static final int READCARD_TYPE = 68;

    /**
     * ACTION_QTJC_FC:TODO(复查气体检测).
     */
    public static final int ACTION_QTJC_FC = 69;

    /**
     * ACTIONBAR_UPLOAD_CLICK:TODO(复查措施确认).
     */
    public static final int ACTION_CSQR_FC = 70;

    /**
     * ACTION_QTJC_FC:TODO(scw作业人交接).
     */
    public static final int ACTION_ZYRJJ_SCW = 71;

    /**
     * ACTIONBAR_UPLOAD_CLICK:TODO(scw监护人交接).
     */
    public static final int ACTION_JHRJJ_SCW = 72;

    /**
     * ACTION_DIALOG_CANCEL:TODO(Dialog 消失事件).
     */
    public static final int ACTION_DIALOG_DIS = 73;

    /**
     * INTERNET_CONNECT_FAIL:TODO(网络连接失败).
     */
    public static final int INTERNET_CONNECT_FAIL = 74;

    /**
     * INTERNET_CONNECT_SUC:TODO(网络连接成功).
     */
    public static final int INTERNET_CONNECT_SUC = 75;
    /**
     * POSITION_CARD:TODO(位置卡)
     */
    public static final int POSITION_CARD = 76;

    /**
     * 作业票列表长按事件。
     */
    public static final int WORK_LIST_LONG_CLICK = 77;

    /**
     * 措施列表编辑弹窗。
     */
    public static final int STEP_LIST_DIALOG_CLICK = 78;

    /**
     * 消息或任务点击事件
     */
    public static final int NOTICE_LIST_CLICK = 79;
    /**
     * ACTIONBAR_CLICK_POPUP:TODO(菜单栏，点击查看详细信息).
     */
    public static final int ACTIONBAR_CLICK_POPUP = 80;


    /**
     * NOTICE_LIST_REFRESH(消息任务刷新).
     */
    public static final int NOTICE_LIST_REFRESH = 80;
    /**
     * NOTICE_LIST_REFRESH(查看分项任务的明细).
     */
    public static final int ACTION_LOOK_ITEMDETAIL = 81;

    /**
     * TPQM_TYPE:TODO(手写图片签名).
     */
    public static final int TPQM_TYPE = 82;

    /**
     * (上传，点击事件)
     */
    public static final int ACTIONBAR_BT_UP_CLICK = 83;

    /**
     * 结束分项任务
     */
    public static final int ACTION_END_SUB_TASK = 84;
    /**
     * 结束主任务
     */
    public static final int ACTION_END_WORK_TASK = 85;
    /**
     * 版本升级按钮点击
     */
    public static final int ACTION_UPDATE_VERSION = 86;
    /**
     * 定时消息的
     */
    public static final int NOTICE_TIMING_CLICK = 87;
    /**
     * 统计分析的"筛选条件"
     */
    public static final int ACTION_SXTJ = 88;
    /**
     * 首页扫一扫
     */
    public static final int ACTION_SYS = 89;
    /**
     * 复查交接班二维码
     */
    public static final int ACTIONBAR_JJBQRCODE_CLICK = 90;
    /**
     * 任务取消
     */
    public static final int ACTION_SUBTASK_CANCLE = 91;
    /**
     * (编辑，点击事件)
     */
    public static final int ACTIONBAR_BT_EDIT_CLICK = 92;
    /**
     * 清除选中消息
     */
    public static final int ACTION_CLEAN_CHOSE_MESSAGE = 93;
    /**
     * ACTIONBAR_UPLOAD_CLICK:TODO(状态栏搜索，点击删除按钮).
     */
    public static final int ACTIONBAR_SEARCH_DELBUTTON = 94;
    /**
     * ACTIONBAR_UPLOAD_CLICK:TODO(状态栏搜索，输入改变的时候).
     */
    public static final int ACTIONBAR_SEARCH_INPUTCHANGED = 95;

    /**
     * 选中所有按钮的内容
     */
    public static final int BUTTON_CONTENTE = 96;
}
