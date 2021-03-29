package com.hayden.hap.common.login.business;

import java.util.List;

/**
 * Created by liuyang on 2017/2/28.
 */

public class LoginUser {

    /**
     * tableName : null
     * usercode : test
     * dataStatus : 0
     * ver : 1
     * created_by : null
     * created_dt : 2017-02-28 09:54:27
     * updated_by : null
     * updated_dt : 2017-02-28 09:54:27
     * df : 0
     * tenantid : 1
     * ts : null
     * userid : 1000
     * userName : 测试用户
     * orgid : 808212
     * orgName : 海顿
     * orgCode : HD
     * stokenKey : eef3ab69e7b9426caa878b4c505efff9
     * funcList : [{"tableName":null,"columnValues":null,"dataStatus":0,"func_code":"HSE_WORK_TASK_DELAY_M","func_name":"延期列表","func_wf_link_proctype":"0","func_wf_finish_allow":"0","buttonList":[{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"workticketDelay"}]},{"tableName":null,"columnValues":null,"dataStatus":0,"func_code":"PHD_HD_RECHECK_M","func_name":"隐患复查","func_wf_link_proctype":"0","func_wf_finish_allow":"0","buttonList":[{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardSave"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardRead"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"subReCheck"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"reCorre"}]},{"tableName":null,"columnValues":null,"dataStatus":0,"func_code":"CSC_TASK_STATION_M","func_name":null,"func_wf_link_proctype":"0","func_wf_finish_allow":"0","buttonList":[{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"listEdit"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardSave"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardEdit"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardRead"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"listDeleteBatch"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardAdd"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cancleListEdit"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"listSave"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"saveBack"}]},{"tableName":null,"columnValues":null,"dataStatus":0,"func_code":"PHD_HD_FIND_M","func_name":"隐患发现","func_wf_link_proctype":"0","func_wf_finish_allow":"0","buttonList":[{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"selEamPosition"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardSave"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardRead"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"listDeleteBatch"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"subFind"}]},{"tableName":null,"columnValues":null,"dataStatus":0,"func_code":"EAM_TJ_EQUT_COUNT_M","func_name":"设备数量统计","func_wf_link_proctype":"0","func_wf_finish_allow":"0","buttonList":[{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"reportQuery"}]},{"tableName":null,"columnValues":null,"dataStatus":0,"func_code":"CSC_LINE_STATION_M","func_name":null,"func_wf_link_proctype":"0","func_wf_finish_allow":"0","buttonList":[{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardRead"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"installStation"}]},{"tableName":null,"columnValues":null,"dataStatus":0,"func_code":"EAM_COUNTING_M","func_name":"盘点记录","func_wf_link_proctype":"0","func_wf_finish_allow":"0","buttonList":[{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardRead"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"compCounting"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"saveAndReportCounting"}]},{"tableName":null,"columnValues":null,"dataStatus":0,"func_code":"HSE_WORKTICKET_CLOSE_LIST_M","func_name":"关闭/取消","func_wf_link_proctype":"0","func_wf_finish_allow":"0","buttonList":[{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"saveCloseTicket"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"closeTicketDisplay"}]},{"tableName":null,"columnValues":null,"dataStatus":0,"func_code":"EAM_TASK_M","func_name":"维护任务","func_wf_link_proctype":"0","func_wf_finish_allow":"0","buttonList":[{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardRead"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardEndtask"}]},{"tableName":null,"columnValues":null,"dataStatus":0,"func_code":"HSE_WORK_TICKET_SX_GB_M","func_name":null,"func_wf_link_proctype":"0","func_wf_finish_allow":"0","buttonList":[{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardSave"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"Submit"}]},{"tableName":null,"columnValues":null,"dataStatus":0,"func_code":"HSE_WORK_TICKET_WJ_MCQ_M","func_name":null,"func_wf_link_proctype":"0","func_wf_finish_allow":"0","buttonList":[{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardSave"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"Submit"}]},{"tableName":null,"columnValues":null,"dataStatus":0,"func_code":"HSE_WORK_TICKET_GC_GB_M","func_name":null,"func_wf_link_proctype":"0","func_wf_finish_allow":"0","buttonList":[{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardSave"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"Submit"}]},{"tableName":null,"columnValues":null,"dataStatus":0,"func_code":"EAM_SCHEDULE_M","func_name":"预防性策略","func_wf_link_proctype":"0","func_wf_finish_allow":"0","buttonList":[{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"listEdit"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardUnaudit"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardEdit"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardSave"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardRead"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"listDeleteBatch"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardAdd"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"listAudit"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cancleListEdit"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"listSave"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"listUnaduit"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardAudit"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"saveBack"}]},{"tableName":null,"columnValues":null,"dataStatus":0,"func_code":"HSE_WORKAPPLY_M","func_name":"作业任务申请列表","func_wf_link_proctype":"0","func_wf_finish_allow":"0","buttonList":[{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"submit"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardSave"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardRead"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"listDeleteBatch"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardAdd"}]},{"tableName":null,"columnValues":null,"dataStatus":0,"func_code":"EAM_ALLOCATION_M","func_name":"调拨批次记录","func_wf_link_proctype":"0","func_wf_finish_allow":"0","buttonList":[{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardSave"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"saveAndReportAllocation"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardRead"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"listDeleteBatch"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"receiveAllocationEquts"}]},{"tableName":null,"columnValues":null,"dataStatus":0,"func_code":"EAM_SPARE_PART_QUERY_M","func_name":"备件查询","func_wf_link_proctype":"0","func_wf_finish_allow":"0","buttonList":[{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardRead"}]},{"tableName":null,"columnValues":null,"dataStatus":0,"func_code":"CSC_STATION_M","func_name":"巡检站点","func_wf_link_proctype":"0","func_wf_finish_allow":"0","buttonList":[{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardRead"}]},{"tableName":null,"columnValues":null,"dataStatus":0,"func_code":"HSE_WORK_TICKET_SX_MCQ_M","func_name":null,"func_wf_link_proctype":"0","func_wf_finish_allow":"0","buttonList":[{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardSave"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"Submit"}]},{"tableName":null,"columnValues":null,"dataStatus":0,"func_code":"HSE_WORK_TASK_GB_M","func_name":"作业任务卡片","func_wf_link_proctype":"0","func_wf_finish_allow":"0","buttonList":[{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardSave"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"Submit"}]},{"tableName":null,"columnValues":null,"dataStatus":0,"func_code":"CSC_CHECK_ITEM_M","func_name":"巡检测项","func_wf_link_proctype":"0","func_wf_finish_allow":"0","buttonList":[{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardRead"}]},{"tableName":null,"columnValues":null,"dataStatus":0,"func_code":"CSC_BLOG_ALERT_M","func_name":"随拍随记消息","func_wf_link_proctype":"0","func_wf_finish_allow":"0","buttonList":[{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardSave"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardEdit"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardRead"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"listDeleteBatch"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardAdd"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"saveBack"}]},{"tableName":null,"columnValues":null,"dataStatus":0,"func_code":"PHD_HD_TJ_HDCOUNT_M","func_name":"隐患数量统计报表","func_wf_link_proctype":"0","func_wf_finish_allow":"0","buttonList":[{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"showReport"}]},{"tableName":null,"columnValues":null,"dataStatus":0,"func_code":"HSE_WORK_TICKET_SHEX_FY_M","func_name":null,"func_wf_link_proctype":"0","func_wf_finish_allow":"0","buttonList":[{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardSave"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"Submit"}]},{"tableName":null,"columnValues":null,"dataStatus":0,"func_code":"HSE_WORK_TICKET_GC_MCQ_M","func_name":null,"func_wf_link_proctype":"0","func_wf_finish_allow":"0","buttonList":[{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardSave"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"Submit"}]},{"tableName":null,"columnValues":null,"dataStatus":0,"func_code":"HSE_WORK_TICKET_DT_GB_M","func_name":null,"func_wf_link_proctype":"0","func_wf_finish_allow":"0","buttonList":[{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardSave"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"Submit"}]},{"tableName":null,"columnValues":null,"dataStatus":0,"func_code":"HSE_WORK_TICKET_XKZ_M","func_name":null,"func_wf_link_proctype":"0","func_wf_finish_allow":"0","buttonList":[{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardSave"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"Submit"}]},{"tableName":null,"columnValues":null,"dataStatus":0,"func_code":"CSC_HD_TJ_XJJLHZ_M","func_name":"巡检情况一览","func_wf_link_proctype":"0","func_wf_finish_allow":"0","buttonList":[{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"reportQuery"}]},{"tableName":null,"columnValues":null,"dataStatus":0,"func_code":"CSC_BLOG_M","func_name":"随拍随记","func_wf_link_proctype":"0","func_wf_finish_allow":"0","buttonList":[{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"getBlog"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardSave"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardEdit"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardRead"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"listDeleteBatch"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardAdd"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"saveBack"}]},{"tableName":null,"columnValues":null,"dataStatus":0,"func_code":"HSE_WORK_TICKET_DL_GB_M","func_name":null,"func_wf_link_proctype":"0","func_wf_finish_allow":"0","buttonList":[{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardSave"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"Submit"}]},{"tableName":null,"columnValues":null,"dataStatus":0,"func_code":"CSC_HD_TJ_LOUJIAN_M","func_name":"巡检漏检统计报表","func_wf_link_proctype":"0","func_wf_finish_allow":"0","buttonList":[{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"reportQuery"}]},{"tableName":null,"columnValues":null,"dataStatus":0,"func_code":"CSC_TASK_M","func_name":"巡检任务","func_wf_link_proctype":"0","func_wf_finish_allow":"0","buttonList":[{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardSave"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardEdit"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardRead"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"listDeleteBatch"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardAdd"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"getNewTask"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"getTask"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"upLoadTasks"}]},{"tableName":null,"columnValues":null,"dataStatus":0,"func_code":"HSE_WORK_TICKET_DZ_MCQ_M","func_name":null,"func_wf_link_proctype":"0","func_wf_finish_allow":"0","buttonList":[{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardSave"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"Submit"}]},{"tableName":null,"columnValues":null,"dataStatus":0,"func_code":"EAM_SCHEDULE_TARGET_M","func_name":null,"func_wf_link_proctype":"0","func_wf_finish_allow":"0","buttonList":[{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardSave"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardEdit"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"queryEqut"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardRead"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"listDeleteBatch"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardAdd"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"saveBack"}]},{"tableName":null,"columnValues":null,"dataStatus":0,"func_code":"PHD_HDTYPE_M","func_name":"隐患类型","func_wf_link_proctype":"0","func_wf_finish_allow":"0","buttonList":[{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardRead"}]},{"tableName":null,"columnValues":null,"dataStatus":0,"func_code":"EAM_TJ_FAULT_PERCENT_M","func_name":"故障率统计","func_wf_link_proctype":"0","func_wf_finish_allow":"0","buttonList":[{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"reportQuery"}]},{"tableName":null,"columnValues":null,"dataStatus":0,"func_code":"HSE_WORK_TICKET_MBCD_GB_M","func_name":null,"func_wf_link_proctype":"0","func_wf_finish_allow":"0","buttonList":[{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardSave"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"Submit"}]},{"tableName":null,"columnValues":null,"dataStatus":0,"func_code":"PHD_HD_HIDDANGER_VIEW_M","func_name":"隐患浏览","func_wf_link_proctype":"0","func_wf_finish_allow":"0","buttonList":[{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"queryLike"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardRead"}]},{"tableName":null,"columnValues":null,"dataStatus":0,"func_code":"EAM_TJ_BUDGET_PERCENT_M","func_name":"年度检修费用","func_wf_link_proctype":"0","func_wf_finish_allow":"0","buttonList":[{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"reportQuery"}]},{"tableName":null,"columnValues":null,"dataStatus":0,"func_code":"PHD_HD_CONFIRM_M","func_name":"隐患确认","func_wf_link_proctype":"0","func_wf_finish_allow":"0","buttonList":[{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardSave"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardRead"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"confrim"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"nullify"}]},{"tableName":null,"columnValues":null,"dataStatus":0,"func_code":"HSE_WORK_TICKET_DH_MCQ_M","func_name":null,"func_wf_link_proctype":"0","func_wf_finish_allow":"0","buttonList":[{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardSave"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"Submit"}]},{"tableName":null,"columnValues":null,"dataStatus":0,"func_code":"HSE_WORK_TASK_MCQ_M","func_name":"作业任务卡片","func_wf_link_proctype":"0","func_wf_finish_allow":"0","buttonList":[{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardSave"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"Submit"}]},{"tableName":null,"columnValues":null,"dataStatus":0,"func_code":"HSE_WORK_TICKET_GX_MCQ_M","func_name":null,"func_wf_link_proctype":"0","func_wf_finish_allow":"0","buttonList":[{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardSave"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"Submit"}]},{"tableName":null,"columnValues":null,"dataStatus":0,"func_code":"HSE_WORK_TICKET_DH_GB_M","func_name":null,"func_wf_link_proctype":"0","func_wf_finish_allow":"0","buttonList":[{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardSave"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"Submit"}]},{"tableName":null,"columnValues":null,"dataStatus":0,"func_code":"PHD_HD_TJ_HDTYPE_M","func_name":"隐患类型统计报表","func_wf_link_proctype":"0","func_wf_finish_allow":"0","buttonList":[{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"showReport"}]},{"tableName":null,"columnValues":null,"dataStatus":0,"func_code":"CSC_LINE_M","func_name":"巡检路线","func_wf_link_proctype":"0","func_wf_finish_allow":"0","buttonList":[{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardRead"}]},{"tableName":null,"columnValues":null,"dataStatus":0,"func_code":"HSE_WORK_TICKET_LSYD_GB_M","func_name":null,"func_wf_link_proctype":"0","func_wf_finish_allow":"0","buttonList":[{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardSave"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"Submit"}]},{"tableName":null,"columnValues":null,"dataStatus":0,"func_code":"HSE_STAT_HOME_M","func_name":"统计分析首页","func_wf_link_proctype":"0","func_wf_finish_allow":"0","buttonList":[{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"reportQuery"}]},{"tableName":null,"columnValues":null,"dataStatus":0,"func_code":"HSE_WORK_TICKET_LSYD_MCQ_M","func_name":null,"func_wf_link_proctype":"0","func_wf_finish_allow":"0","buttonList":[{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardSave"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"Submit"}]},{"tableName":null,"columnValues":null,"dataStatus":0,"func_code":"PHD_HD_CORRE_M","func_name":"隐患整改","func_wf_link_proctype":"0","func_wf_finish_allow":"0","buttonList":[{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardSave"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardRead"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"subCorre"}]},{"tableName":null,"columnValues":null,"dataStatus":0,"func_code":"HSE_WORK_TICKET_JSJ_FY_M","func_name":null,"func_wf_link_proctype":"0","func_wf_finish_allow":"0","buttonList":[{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardSave"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"Submit"}]},{"tableName":null,"columnValues":null,"dataStatus":0,"func_code":"PHD_WTXX_M","func_name":null,"func_wf_link_proctype":"0","func_wf_finish_allow":"0","buttonList":[{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardRead"}]},{"tableName":null,"columnValues":null,"dataStatus":0,"func_code":"EAM_TJ_EQUT_AVAILABILITY_M","func_name":"设备完好率","func_wf_link_proctype":"0","func_wf_finish_allow":"0","buttonList":[{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"reportQuery"}]},{"tableName":null,"columnValues":null,"dataStatus":0,"func_code":"CSC_PERSON_M","func_name":"巡检人员","func_wf_link_proctype":"0","func_wf_finish_allow":"0","buttonList":[{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardRead"}]},{"tableName":null,"columnValues":null,"dataStatus":0,"func_code":"EAM_COUNTING_EQUT_M","func_name":null,"func_wf_link_proctype":"0","func_wf_finish_allow":"0","buttonList":[{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardSave"}]},{"tableName":null,"columnValues":null,"dataStatus":0,"func_code":"EAM_EQUTBOM_M","func_name":"物料清单（设备）","func_wf_link_proctype":"0","func_wf_finish_allow":"0","buttonList":[{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardRead"}]},{"tableName":null,"columnValues":null,"dataStatus":0,"func_code":"HSE_WORKTICKETAPPLY_M","func_name":null,"func_wf_link_proctype":"0","func_wf_finish_allow":"0","buttonList":[{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"getTicketCard"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"submit"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardSave"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"listDeleteBatch"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardAdd"}]},{"tableName":null,"columnValues":null,"dataStatus":0,"func_code":"HSE_WORK_TICKET_DZ_GB_M","func_name":null,"func_wf_link_proctype":"0","func_wf_finish_allow":"0","buttonList":[{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardSave"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"Submit"}]},{"tableName":null,"columnValues":null,"dataStatus":0,"func_code":"EAM_EQUT_M","func_name":"设备台账","func_wf_link_proctype":"0","func_wf_finish_allow":"0","buttonList":[{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"saveAndPaste"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"equtLinkedListQuery"}]},{"tableName":null,"columnValues":null,"dataStatus":0,"func_code":"CSC_HD_TJ_DAOWEI_M","func_name":"巡检到位统计报表","func_wf_link_proctype":"0","func_wf_finish_allow":"0","buttonList":[{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"reportQuery"}]},{"tableName":null,"columnValues":null,"dataStatus":0,"func_code":"EAM_WO_M","func_name":"工单","func_wf_link_proctype":"1","func_wf_finish_allow":"1","buttonList":[{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"closeWo"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardSave"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"compWo"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardRead"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardAdd"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"submitWo"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"startWo"}]},{"tableName":null,"columnValues":null,"dataStatus":0,"func_code":"EAM_TJ_FAULT_COUNT_M","func_name":"故障数统计","func_wf_link_proctype":"0","func_wf_finish_allow":"0","buttonList":[{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"reportQuery"}]},{"tableName":null,"columnValues":null,"dataStatus":0,"func_code":"CSC_TASK_ITEM_M","func_name":null,"func_wf_link_proctype":"0","func_wf_finish_allow":"0","buttonList":[{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"listEdit"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"batchNormal"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardEdit"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardSave"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardRead"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"listDeleteBatch"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardAdd"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cancleListEdit"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"listSave"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"saveBack"}]},{"tableName":null,"columnValues":null,"dataStatus":0,"func_code":"CSC_FAULT_M","func_name":"巡检异常","func_wf_link_proctype":"0","func_wf_finish_allow":"0","buttonList":[{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"toPhdHd"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardEdit"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardSave"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardRead"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"listDeleteBatch"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardAdd"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"taskFault"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"toEamFault"}]},{"tableName":null,"columnValues":null,"dataStatus":0,"func_code":"CSC_BLOGER_NEXUS_M","func_name":"关注关系表","func_wf_link_proctype":"0","func_wf_finish_allow":"0","buttonList":[{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"listEdit"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardEdit"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardSave"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardRead"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"listDeleteBatch"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardAdd"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cancleListEdit"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"listSave"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"saveBack"}]},{"tableName":null,"columnValues":null,"dataStatus":0,"func_code":"EAM_FAULT_M","func_name":"故障","func_wf_link_proctype":"1","func_wf_finish_allow":"1","buttonList":[{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"saveToHiddanger"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"saveToWo"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardSave"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"saveAndCancel"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardRead"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardAdd"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"listDeleteBatch"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"saveAndRemove"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"saveAndReport"}]},{"tableName":null,"columnValues":null,"dataStatus":0,"func_code":"EAM_SCRAP_RECORDS_M","func_name":"报废记录","func_wf_link_proctype":"0","func_wf_finish_allow":"0","buttonList":[{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardSave"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardRead"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"listDeleteBatch"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"saveAndReport"}]},{"tableName":null,"columnValues":null,"dataStatus":0,"func_code":"HSE_WORK_TICKET_JSJ_MCQ_M","func_name":null,"func_wf_link_proctype":"0","func_wf_finish_allow":"0","buttonList":[{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"cardSave"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"Submit"}]},{"tableName":null,"columnValues":null,"dataStatus":0,"func_code":"HSE_WORK_TICKET_M","func_name":null,"func_wf_link_proctype":"0","func_wf_finish_allow":"0","buttonList":[{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"workticketInvalid"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"delAudit"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"harmAudit"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"measureAudit"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"saveGasDetection"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"ticketView"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"auditSign"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"actionapproval"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"ppeAudit"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"closeTicketDisplay"},{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"saveCloseTicket"}]}]
     * newAppVer : {"currentVer":null,"forceUpgradeVer":null,"upgradeUrl":null}
     */

    private Object tableName;
    private String usercode;
    private int dataStatus;
    private int ver;
    private Long created_by;
    private String created_dt;
    private Long updated_by;
    private String updated_dt;
    private int df;
    private long tenantid;
    private Object ts;
    private long userid;
    private String userName;
    private long orgid;
    private String orgName;
    private String orgCode;
    private String stokenKey;
    private Object ext;
    private AppVersion newAppVer;
    private List<FuncList> funcList;

    public Object getTableName() {
        return tableName;
    }

    public void setTableName(Object tableName) {
        this.tableName = tableName;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
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

    public Long getCreated_by() {
        return created_by;
    }

    public void setCreated_by(Long created_by) {
        this.created_by = created_by;
    }

    public String getCreated_dt() {
        return created_dt;
    }

    public void setCreated_dt(String created_dt) {
        this.created_dt = created_dt;
    }

    public Long getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(Long updated_by) {
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

    public long getTenantid() {
        return tenantid;
    }

    public void setTenantid(long tenantid) {
        this.tenantid = tenantid;
    }

    public Object getTs() {
        return ts;
    }

    public void setTs(Object ts) {
        this.ts = ts;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getOrgid() {
        return orgid;
    }

    public void setOrgid(long orgid) {
        this.orgid = orgid;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getStokenKey() {
        return stokenKey;
    }

    public void setStokenKey(String stokenKey) {
        this.stokenKey = stokenKey;
    }

    public Object getExt() {
        return ext;
    }

    public void setExt(Object ext) {
        this.ext = ext;
    }

    public AppVersion getNewAppVer() {
        return newAppVer;
    }

    public void setNewAppVer(AppVersion newAppVer) {
        this.newAppVer = newAppVer;
    }

    public List<FuncList> getFuncList() {
        return funcList;
    }

    public void setFuncList(List<FuncList> funcList) {
        this.funcList = funcList;
    }

    public static class FuncList {
        /**
         * tableName : null
         * columnValues : null
         * dataStatus : 0
         * func_code : HSE_WORK_TASK_DELAY_M
         * func_name : 延期列表
         * func_wf_link_proctype : 0
         * func_wf_finish_allow : 0
         * buttonList : [{"tableName":null,"columnValues":null,"dataStatus":0,"btn_code":"workticketDelay"}]
         */

        private Object tableName;
        private Object columnValues;
        private int dataStatus;
        private String func_code;
        private String func_name;
        private String func_wf_link_proctype;
        private String func_wf_finish_allow;
        private List<ButtonList> buttonList;

        public Object getTableName() {
            return tableName;
        }

        public void setTableName(Object tableName) {
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

        public String getFunc_code() {
            return func_code;
        }

        public void setFunc_code(String func_code) {
            this.func_code = func_code;
        }

        public String getFunc_name() {
            return func_name;
        }

        public void setFunc_name(String func_name) {
            this.func_name = func_name;
        }

        public String getFunc_wf_link_proctype() {
            return func_wf_link_proctype;
        }

        public void setFunc_wf_link_proctype(String func_wf_link_proctype) {
            this.func_wf_link_proctype = func_wf_link_proctype;
        }

        public String getFunc_wf_finish_allow() {
            return func_wf_finish_allow;
        }

        public void setFunc_wf_finish_allow(String func_wf_finish_allow) {
            this.func_wf_finish_allow = func_wf_finish_allow;
        }

        public List<ButtonList> getButtonList() {
            return buttonList;
        }

        public void setButtonList(List<ButtonList> buttonList) {
            this.buttonList = buttonList;
        }

        public static class ButtonList {
            /**
             * tableName : null
             * columnValues : null
             * dataStatus : 0
             * btn_code : workticketDelay
             */

            private Object tableName;
            private Object columnValues;
            private int dataStatus;
            private String btn_code;

            public Object getTableName() {
                return tableName;
            }

            public void setTableName(Object tableName) {
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

            public String getBtn_code() {
                return btn_code;
            }

            public void setBtn_code(String btn_code) {
                this.btn_code = btn_code;
            }
        }
    }
}
