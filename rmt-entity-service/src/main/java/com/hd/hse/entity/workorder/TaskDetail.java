package com.hd.hse.entity.workorder;

import com.hd.hse.common.entity.SuperEntity;

import java.util.List;

/**
 * Created by dubojian on 2017/7/3.
 */

public class TaskDetail {


    /**
     * dictvos : null
     * mainvo : [{"headVO":{"RMT_WORK_PERMIT_M":{"tableName":"rmt_work_permit","ts":1498717557090,"owner_name":null,"est_start_time":null,"work_reason":null,"upload_file":0,"hst_ts":0,"orgid":1000000000000,"hst_rated_load":null,"work_site_id":null,"hst_object_volume":null,"tenantid":1,"updated_dt":"2017-06-29 14:25:56","created_by":1000,"work_unit_name":"安装检修公司","status":"wappr","est_end_time":null,"act_start_time":null,"hst_angle":null,"elec_voltage":null,"work_name":"0626001-主任务-hqs","work_task_id":1000000000940,"work_content":"0626001-主任务-hqs","related_unit_name":null,"work_permit_no":"GEN2017062900010","elec_power_access_point":null,"work_site_name":null,"elec_total_load":null,"work_unit_id":1000000000008,"hst_radius":null,"ver":1,"created_by_name":"系统管理员","hst_object_weight":null,"product_flag":2,"project_type":null,"df":0,"related_unit_id":null,"hst_object_name":null,"updated_by":1000,"elec_purpose":null,"act_end_time":null,"territorial_unit_name":"中国石油宁夏石化公司","work_type":"gen","isenable":1,"work_permit_id":1000000001900,"created_dt":"2017-06-29 14:25:56","work_subtask_id":1000000002400,"owner_id":null,"territorial_unit_id":1000000000000,"hst_height":null,"hst_lift_weight":null,"updated_by_name":"系统管理员","dataStatus":0}},"attachDataMap":null,"bodyVOs":{"RMT_WORK_WFINSTANCE_M":[{"headVO":{"RMT_WORK_WFINSTANCE_M":{"tableName":"rmt_work_wfinstance","pre_node_id":null,"status":"inprg","alias":"一般岗位主操","appr_node_id":1000000000276,"work_type":"gen","stage":"approve","func_order":1,"wfinstance_id":1000000000610,"work_subtask_id":1000000002400,"next_node_id":"1000000000461,1000000000463","assignee_id":"1000000000420","work_task_id":1000000000940,"appr_auth_id":1000000000465,"m_intfc_id":1000000000520,"dataStatus":0}},"attachDataMap":null,"bodyVOs":{"RMT_WORK_ASSIGN_LINE_M":[{"headVO":{"RMT_WORK_ASSIGN_LINE_M":{"tableName":"rmt_work_assign_line","created_by":1000,"ts":1498718274131,"assign_time":null,"assign_line_id":1000000000182,"relieved_time":null,"ver":1,"isenable":1,"created_by_name":"系统管理员","product_flag":2,"created_dt":"2017-06-29 14:37:54","work_subtask_id":1000000002400,"work_assign_id":1000000001681,"assignee_name":"岗位主操","df":0,"orgid":1000000000000,"end_time":null,"assignee_id":1000000000420,"relieved_status":null,"updated_by":1000,"tenantid":1,"updated_dt":"2017-06-29 14:37:54","work_task_id":1000000000940,"appr_auth_id":1000000000465,"updated_by_name":"系统管理员","dataStatus":0}},"attachDataMap":null,"bodyVOs":null,"attachDataVOs":null}]},"attachDataVOs":null},{"headVO":{"RMT_WORK_WFINSTANCE_M":{"tableName":"rmt_work_wfinstance","pre_node_id":"1000000000465","status":"wappr","alias":"一般作业人（危害）","appr_node_id":1000000000271,"work_type":"gen","stage":"approve","func_order":1,"wfinstance_id":1000000000609,"work_subtask_id":1000000002400,"next_node_id":"1000000000464","assignee_id":"1000000000417","work_task_id":1000000000940,"appr_auth_id":1000000000461,"m_intfc_id":1000000000523,"dataStatus":0}},"attachDataMap":null,"bodyVOs":{"RMT_WORK_ASSIGN_LINE_M":[{"headVO":{"RMT_WORK_ASSIGN_LINE_M":{"tableName":"rmt_work_assign_line","created_by":1000,"ts":1498718274216,"assign_time":null,"assign_line_id":1000000000187,"relieved_time":null,"ver":1,"isenable":1,"created_by_name":"系统管理员","product_flag":2,"created_dt":"2017-06-29 14:37:54","work_subtask_id":1000000002400,"work_assign_id":1000000001683,"assignee_name":"作业人员","df":0,"orgid":1000000000000,"end_time":null,"assignee_id":1000000000417,"relieved_status":null,"updated_by":1000,"tenantid":1,"updated_dt":"2017-06-29 14:37:54","work_task_id":1000000000940,"appr_auth_id":1000000000461,"updated_by_name":"系统管理员","dataStatus":0}},"attachDataMap":null,"bodyVOs":null,"attachDataVOs":null}]},"attachDataVOs":null},{"headVO":{"RMT_WORK_WFINSTANCE_M":{"tableName":"rmt_work_wfinstance","pre_node_id":"1000000000465","status":"wappr","alias":"一般作业人(措施审批)","appr_node_id":1000000000271,"work_type":"gen","stage":"approve","func_order":2,"wfinstance_id":1000000000607,"work_subtask_id":1000000002400,"next_node_id":"1000000000464","assignee_id":"1000000000417","work_task_id":1000000000940,"appr_auth_id":1000000000463,"m_intfc_id":1000000000521,"dataStatus":0}},"attachDataMap":null,"bodyVOs":{"RMT_WORK_ASSIGN_LINE_M":[{"headVO":{"RMT_WORK_ASSIGN_LINE_M":{"tableName":"rmt_work_assign_line","created_by":1000,"ts":1498718274216,"assign_time":null,"assign_line_id":1000000000188,"relieved_time":null,"ver":1,"isenable":1,"created_by_name":"系统管理员","product_flag":2,"created_dt":"2017-06-29 14:37:54","work_subtask_id":1000000002400,"work_assign_id":1000000001683,"assignee_name":"作业人员","df":0,"orgid":1000000000000,"end_time":null,"assignee_id":1000000000417,"relieved_status":null,"updated_by":1000,"tenantid":1,"updated_dt":"2017-06-29 14:37:54","work_task_id":1000000000940,"appr_auth_id":1000000000463,"updated_by_name":"系统管理员","dataStatus":0}},"attachDataMap":null,"bodyVOs":null,"attachDataVOs":null}]},"attachDataVOs":null},{"headVO":{"RMT_WORK_WFINSTANCE_M":{"tableName":"rmt_work_wfinstance","pre_node_id":"1000000000463,1000000000461","status":"wappr","alias":"一般作业人（措施确认）","appr_node_id":1000000000271,"work_type":"gen","stage":"review","func_order":5,"wfinstance_id":1000000000608,"work_subtask_id":1000000002400,"next_node_id":null,"assignee_id":"1000000000417","work_task_id":1000000000940,"appr_auth_id":1000000000464,"m_intfc_id":1000000000522,"dataStatus":0}},"attachDataMap":null,"bodyVOs":{"RMT_WORK_ASSIGN_LINE_M":[{"headVO":{"RMT_WORK_ASSIGN_LINE_M":{"tableName":"rmt_work_assign_line","created_by":1000,"ts":1498718274217,"assign_time":null,"assign_line_id":1000000000189,"relieved_time":null,"ver":1,"isenable":1,"created_by_name":"系统管理员","product_flag":2,"created_dt":"2017-06-29 14:37:54","work_subtask_id":1000000002400,"work_assign_id":1000000001683,"assignee_name":"作业人员","df":0,"orgid":1000000000000,"end_time":null,"assignee_id":1000000000417,"relieved_status":null,"updated_by":1000,"tenantid":1,"updated_dt":"2017-06-29 14:37:54","work_task_id":1000000000940,"appr_auth_id":1000000000464,"updated_by_name":"系统管理员","dataStatus":0}},"attachDataMap":null,"bodyVOs":null,"attachDataVOs":null}]},"attachDataVOs":null}]},"attachDataVOs":null},{"headVO":{"RMT_WORK_PERMIT_M":{"tableName":"rmt_work_permit","ts":1498717557109,"owner_name":null,"est_start_time":null,"work_reason":null,"upload_file":0,"hst_ts":0,"orgid":1000000000000,"hst_rated_load":null,"work_site_id":null,"hst_object_volume":null,"tenantid":1,"updated_dt":"2017-06-29 14:25:57","created_by":1000,"work_unit_name":"安装检修公司","status":"wappr","est_end_time":null,"act_start_time":null,"hst_angle":null,"elec_voltage":null,"work_name":"0626001-主任务-hqs","work_task_id":1000000000940,"work_content":"0626001-主任务-hqs","related_unit_name":null,"work_permit_no":"HOT2017062900011","elec_power_access_point":null,"work_site_name":null,"elec_total_load":null,"work_unit_id":1000000000008,"hst_radius":null,"ver":1,"created_by_name":"系统管理员","hst_object_weight":null,"product_flag":2,"project_type":null,"df":0,"related_unit_id":null,"hst_object_name":null,"updated_by":1000,"elec_purpose":null,"act_end_time":null,"territorial_unit_name":"中国石油宁夏石化公司","work_type":"hot","isenable":1,"work_permit_id":1000000001901,"created_dt":"2017-06-29 14:25:57","work_subtask_id":1000000002400,"owner_id":null,"territorial_unit_id":1000000000000,"hst_height":null,"hst_lift_weight":null,"updated_by_name":"系统管理员","dataStatus":0}},"attachDataMap":null,"bodyVOs":{"RMT_WORK_WFINSTANCE_M":[{"headVO":{"RMT_WORK_WFINSTANCE_M":{"tableName":"rmt_work_wfinstance","pre_node_id":null,"status":"inprg","alias":"动火岗位主操（工艺危害）","appr_node_id":1000000000276,"work_type":"hot","stage":"approve","func_order":41,"wfinstance_id":1000000000615,"work_subtask_id":1000000002400,"next_node_id":"1000000000382,1000000000381","assignee_id":"1000000000420","work_task_id":1000000000940,"appr_auth_id":1000000000380,"m_intfc_id":1000000000524,"dataStatus":0}},"attachDataMap":null,"bodyVOs":{"RMT_WORK_ASSIGN_LINE_M":[{"headVO":{"RMT_WORK_ASSIGN_LINE_M":{"tableName":"rmt_work_assign_line","created_by":1000,"ts":1498718274131,"assign_time":null,"assign_line_id":1000000000181,"relieved_time":null,"ver":1,"isenable":1,"created_by_name":"系统管理员","product_flag":2,"created_dt":"2017-06-29 14:37:54","work_subtask_id":1000000002400,"work_assign_id":1000000001681,"assignee_name":"岗位主操","df":0,"orgid":1000000000000,"end_time":null,"assignee_id":1000000000420,"relieved_status":null,"updated_by":1000,"tenantid":1,"updated_dt":"2017-06-29 14:37:54","work_task_id":1000000000940,"appr_auth_id":1000000000380,"updated_by_name":"系统管理员","dataStatus":0}},"attachDataMap":null,"bodyVOs":null,"attachDataVOs":null}]},"attachDataVOs":null},{"headVO":{"RMT_WORK_WFINSTANCE_M":{"tableName":"rmt_work_wfinstance","pre_node_id":null,"status":"inprg","alias":"动火岗位主操","appr_node_id":1000000000276,"work_type":"hot","stage":"approve","func_order":42,"wfinstance_id":1000000000617,"work_subtask_id":1000000002400,"next_node_id":"1000000000382,1000000000381","assignee_id":"1000000000420","work_task_id":1000000000940,"appr_auth_id":1000000000360,"m_intfc_id":1000000000525,"dataStatus":0}},"attachDataMap":null,"bodyVOs":{"RMT_WORK_ASSIGN_LINE_M":[{"headVO":{"RMT_WORK_ASSIGN_LINE_M":{"tableName":"rmt_work_assign_line","created_by":1000,"ts":1498718274131,"assign_time":null,"assign_line_id":1000000000183,"relieved_time":null,"ver":1,"isenable":1,"created_by_name":"系统管理员","product_flag":2,"created_dt":"2017-06-29 14:37:54","work_subtask_id":1000000002400,"work_assign_id":1000000001681,"assignee_name":"岗位主操","df":0,"orgid":1000000000000,"end_time":null,"assignee_id":1000000000420,"relieved_status":null,"updated_by":1000,"tenantid":1,"updated_dt":"2017-06-29 14:37:54","work_task_id":1000000000940,"appr_auth_id":1000000000360,"updated_by_name":"系统管理员","dataStatus":0}},"attachDataMap":null,"bodyVOs":null,"attachDataVOs":null}]},"attachDataVOs":null},{"headVO":{"RMT_WORK_WFINSTANCE_M":{"tableName":"rmt_work_wfinstance","pre_node_id":"1000000000380,1000000000360","status":"wappr","alias":"动火作业人（作业危害）","appr_node_id":1000000000274,"work_type":"hot","stage":"approve","func_order":43,"wfinstance_id":1000000000624,"work_subtask_id":1000000002400,"next_node_id":"1000000000383,1000000000384","assignee_id":"1000000000417","work_task_id":1000000000940,"appr_auth_id":1000000000381,"m_intfc_id":1000000000526,"dataStatus":0}},"attachDataMap":null,"bodyVOs":{"RMT_WORK_ASSIGN_LINE_M":[{"headVO":{"RMT_WORK_ASSIGN_LINE_M":{"tableName":"rmt_work_assign_line","created_by":1000,"ts":1498718274293,"assign_time":null,"assign_line_id":1000000000194,"relieved_time":null,"ver":1,"isenable":1,"created_by_name":"系统管理员","product_flag":2,"created_dt":"2017-06-29 14:37:54","work_subtask_id":1000000002400,"work_assign_id":1000000001700,"assignee_name":"作业人员","df":0,"orgid":1000000000000,"end_time":null,"assignee_id":1000000000417,"relieved_status":null,"updated_by":1000,"tenantid":1,"updated_dt":"2017-06-29 14:37:54","work_task_id":1000000000940,"appr_auth_id":1000000000381,"updated_by_name":"系统管理员","dataStatus":0}},"attachDataMap":null,"bodyVOs":null,"attachDataVOs":null}]},"attachDataVOs":null},{"headVO":{"RMT_WORK_WFINSTANCE_M":{"tableName":"rmt_work_wfinstance","pre_node_id":"1000000000380,1000000000360","status":"wappr","alias":"动火作业人(审批措施)","appr_node_id":1000000000274,"work_type":"hot","stage":"approve","func_order":44,"wfinstance_id":1000000000623,"work_subtask_id":1000000002400,"next_node_id":"1000000000383,1000000000384","assignee_id":"1000000000417","work_task_id":1000000000940,"appr_auth_id":1000000000382,"m_intfc_id":1000000000527,"dataStatus":0}},"attachDataMap":null,"bodyVOs":{"RMT_WORK_ASSIGN_LINE_M":[{"headVO":{"RMT_WORK_ASSIGN_LINE_M":{"tableName":"rmt_work_assign_line","created_by":1000,"ts":1498718274293,"assign_time":null,"assign_line_id":1000000000191,"relieved_time":null,"ver":1,"isenable":1,"created_by_name":"系统管理员","product_flag":2,"created_dt":"2017-06-29 14:37:54","work_subtask_id":1000000002400,"work_assign_id":1000000001700,"assignee_name":"作业人员","df":0,"orgid":1000000000000,"end_time":null,"assignee_id":1000000000417,"relieved_status":null,"updated_by":1000,"tenantid":1,"updated_dt":"2017-06-29 14:37:54","work_task_id":1000000000940,"appr_auth_id":1000000000382,"updated_by_name":"系统管理员","dataStatus":0}},"attachDataMap":null,"bodyVOs":null,"attachDataVOs":null}]},"attachDataVOs":null},{"headVO":{"RMT_WORK_WFINSTANCE_M":{"tableName":"rmt_work_wfinstance","pre_node_id":"1000000000382,1000000000381","status":"wappr","alias":"动火属地监护人危害","appr_node_id":1000000000263,"work_type":"hot","stage":"approve","func_order":43,"wfinstance_id":1000000000621,"work_subtask_id":1000000002400,"next_node_id":"1000000000385,1000000000420,1000000000428","assignee_id":"1000000000421","work_task_id":1000000000940,"appr_auth_id":1000000000383,"m_intfc_id":1000000000526,"dataStatus":0}},"attachDataMap":null,"bodyVOs":{"RMT_WORK_ASSIGN_LINE_M":[{"headVO":{"RMT_WORK_ASSIGN_LINE_M":{"tableName":"rmt_work_assign_line","created_by":1000,"ts":1498718274345,"assign_time":null,"assign_line_id":1000000000196,"relieved_time":null,"ver":1,"isenable":1,"created_by_name":"系统管理员","product_flag":2,"created_dt":"2017-06-29 14:37:54","work_subtask_id":1000000002400,"work_assign_id":1000000001701,"assignee_name":"属地监护人","df":0,"orgid":1000000000000,"end_time":null,"assignee_id":1000000000421,"relieved_status":null,"updated_by":1000,"tenantid":1,"updated_dt":"2017-06-29 14:37:54","work_task_id":1000000000940,"appr_auth_id":1000000000383,"updated_by_name":"系统管理员","dataStatus":0}},"attachDataMap":null,"bodyVOs":null,"attachDataVOs":null}]},"attachDataVOs":null},{"headVO":{"RMT_WORK_WFINSTANCE_M":{"tableName":"rmt_work_wfinstance","pre_node_id":"1000000000382,1000000000381","status":"wappr","alias":"动火属地监护人（审批措施）","appr_node_id":1000000000263,"work_type":"hot","stage":"approve","func_order":44,"wfinstance_id":1000000000622,"work_subtask_id":1000000002400,"next_node_id":"1000000000385,1000000000420,1000000000428","assignee_id":"1000000000421","work_task_id":1000000000940,"appr_auth_id":1000000000384,"m_intfc_id":1000000000527,"dataStatus":0}},"attachDataMap":null,"bodyVOs":{"RMT_WORK_ASSIGN_LINE_M":[{"headVO":{"RMT_WORK_ASSIGN_LINE_M":{"tableName":"rmt_work_assign_line","created_by":1000,"ts":1498718274343,"assign_time":null,"assign_line_id":1000000000195,"relieved_time":null,"ver":1,"isenable":1,"created_by_name":"系统管理员","product_flag":2,"created_dt":"2017-06-29 14:37:54","work_subtask_id":1000000002400,"work_assign_id":1000000001701,"assignee_name":"属地监护人","df":0,"orgid":1000000000000,"end_time":null,"assignee_id":1000000000421,"relieved_status":null,"updated_by":1000,"tenantid":1,"updated_dt":"2017-06-29 14:37:54","work_task_id":1000000000940,"appr_auth_id":1000000000384,"updated_by_name":"系统管理员","dataStatus":0}},"attachDataMap":null,"bodyVOs":null,"attachDataVOs":null}]},"attachDataVOs":null},{"headVO":{"RMT_WORK_WFINSTANCE_M":{"tableName":"rmt_work_wfinstance","pre_node_id":"1000000000383,1000000000384","status":"wappr","alias":"作业人(措施确认)","appr_node_id":1000000000274,"work_type":"hot","stage":"review","func_order":45,"wfinstance_id":1000000000619,"work_subtask_id":1000000002400,"next_node_id":"1000000000386","assignee_id":"1000000000417","work_task_id":1000000000940,"appr_auth_id":1000000000385,"m_intfc_id":1000000000528,"dataStatus":0}},"attachDataMap":null,"bodyVOs":{"RMT_WORK_ASSIGN_LINE_M":[{"headVO":{"RMT_WORK_ASSIGN_LINE_M":{"tableName":"rmt_work_assign_line","created_by":1000,"ts":1498718274293,"assign_time":null,"assign_line_id":1000000000192,"relieved_time":null,"ver":1,"isenable":1,"created_by_name":"系统管理员","product_flag":2,"created_dt":"2017-06-29 14:37:54","work_subtask_id":1000000002400,"work_assign_id":1000000001700,"assignee_name":"作业人员","df":0,"orgid":1000000000000,"end_time":null,"assignee_id":1000000000417,"relieved_status":null,"updated_by":1000,"tenantid":1,"updated_dt":"2017-06-29 14:37:54","work_task_id":1000000000940,"appr_auth_id":1000000000385,"updated_by_name":"系统管理员","dataStatus":0}},"attachDataMap":null,"bodyVOs":null,"attachDataVOs":null}]},"attachDataVOs":null},{"headVO":{"RMT_WORK_WFINSTANCE_M":{"tableName":"rmt_work_wfinstance","pre_node_id":"1000000000385","status":"wappr","alias":"动火属地监护人（措施确认）","appr_node_id":1000000000263,"work_type":"hot","stage":"review","func_order":46,"wfinstance_id":1000000000618,"work_subtask_id":1000000002400,"next_node_id":null,"assignee_id":"1000000000421","work_task_id":1000000000940,"appr_auth_id":1000000000386,"m_intfc_id":1000000000530,"dataStatus":0}},"attachDataMap":null,"bodyVOs":{"RMT_WORK_ASSIGN_LINE_M":[{"headVO":{"RMT_WORK_ASSIGN_LINE_M":{"tableName":"rmt_work_assign_line","created_by":1000,"ts":1498718274345,"assign_time":null,"assign_line_id":1000000000197,"relieved_time":null,"ver":1,"isenable":1,"created_by_name":"系统管理员","product_flag":2,"created_dt":"2017-06-29 14:37:54","work_subtask_id":1000000002400,"work_assign_id":1000000001701,"assignee_name":"属地监护人","df":0,"orgid":1000000000000,"end_time":null,"assignee_id":1000000000421,"relieved_status":null,"updated_by":1000,"tenantid":1,"updated_dt":"2017-06-29 14:37:54","work_task_id":1000000000940,"appr_auth_id":1000000000386,"updated_by_name":"系统管理员","dataStatus":0}},"attachDataMap":null,"bodyVOs":null,"attachDataVOs":null}]},"attachDataVOs":null}]},"attachDataVOs":null},{"headVO":{"RMT_WORK_PERMIT_M":{"tableName":null,"work_type":"workclose","work_permit_id":1000000002400,"work_subtask_id":1000000002400,"dataStatus":0}},"attachDataMap":null,"bodyVOs":{"RMT_WORK_WFINSTANCE_M":[{"headVO":{"RMT_WORK_WFINSTANCE_M":{"tableName":"rmt_work_wfinstance","pre_node_id":null,"status":"inprg","alias":"作业人（关闭）","appr_node_id":1000000000271,"work_type":"workclose","stage":"close","func_order":6,"wfinstance_id":1000000000611,"work_subtask_id":1000000002400,"next_node_id":"1000000000462","assignee_id":"1000000000417","work_task_id":1000000000940,"appr_auth_id":1000000000460,"m_intfc_id":1000000000471,"dataStatus":0}},"attachDataMap":null,"bodyVOs":{"RMT_WORK_ASSIGN_LINE_M":[{"headVO":{"RMT_WORK_ASSIGN_LINE_M":{"tableName":"rmt_work_assign_line","created_by":1000,"ts":1498718274216,"assign_time":null,"assign_line_id":1000000000186,"relieved_time":null,"ver":1,"isenable":1,"created_by_name":"系统管理员","product_flag":2,"created_dt":"2017-06-29 14:37:54","work_subtask_id":1000000002400,"work_assign_id":1000000001683,"assignee_name":"作业人员","df":0,"orgid":1000000000000,"end_time":null,"assignee_id":1000000000417,"relieved_status":null,"updated_by":1000,"tenantid":1,"updated_dt":"2017-06-29 14:37:54","work_task_id":1000000000940,"appr_auth_id":1000000000460,"updated_by_name":"系统管理员","dataStatus":0}},"attachDataMap":null,"bodyVOs":null,"attachDataVOs":null}]},"attachDataVOs":null},{"headVO":{"RMT_WORK_WFINSTANCE_M":{"tableName":"rmt_work_wfinstance","pre_node_id":null,"status":"inprg","alias":"动火作业人（关闭）","appr_node_id":1000000000274,"work_type":"workclose","stage":"close","func_order":48,"wfinstance_id":1000000000616,"work_subtask_id":1000000002400,"next_node_id":"1000000000388","assignee_id":"1000000000417","work_task_id":1000000000940,"appr_auth_id":1000000000387,"m_intfc_id":1000000000529,"dataStatus":0}},"attachDataMap":null,"bodyVOs":{"RMT_WORK_ASSIGN_LINE_M":[{"headVO":{"RMT_WORK_ASSIGN_LINE_M":{"tableName":"rmt_work_assign_line","created_by":1000,"ts":1498718274293,"assign_time":null,"assign_line_id":1000000000193,"relieved_time":null,"ver":1,"isenable":1,"created_by_name":"系统管理员","product_flag":2,"created_dt":"2017-06-29 14:37:54","work_subtask_id":1000000002400,"work_assign_id":1000000001700,"assignee_name":"作业人员","df":0,"orgid":1000000000000,"end_time":null,"assignee_id":1000000000417,"relieved_status":null,"updated_by":1000,"tenantid":1,"updated_dt":"2017-06-29 14:37:54","work_task_id":1000000000940,"appr_auth_id":1000000000387,"updated_by_name":"系统管理员","dataStatus":0}},"attachDataMap":null,"bodyVOs":null,"attachDataVOs":null}]},"attachDataVOs":null},{"headVO":{"RMT_WORK_WFINSTANCE_M":{"tableName":"rmt_work_wfinstance","pre_node_id":"1000000000387","status":"wappr","alias":"动火属地监护人（关闭）","appr_node_id":1000000000263,"work_type":"workclose","stage":"close","func_order":48,"wfinstance_id":1000000000620,"work_subtask_id":1000000002400,"next_node_id":"1000000000389","assignee_id":"1000000000421","work_task_id":1000000000940,"appr_auth_id":1000000000388,"m_intfc_id":1000000000529,"dataStatus":0}},"attachDataMap":null,"bodyVOs":{"RMT_WORK_ASSIGN_LINE_M":[{"headVO":{"RMT_WORK_ASSIGN_LINE_M":{"tableName":"rmt_work_assign_line","created_by":1000,"ts":1498718274345,"assign_time":null,"assign_line_id":1000000000198,"relieved_time":null,"ver":1,"isenable":1,"created_by_name":"系统管理员","product_flag":2,"created_dt":"2017-06-29 14:37:54","work_subtask_id":1000000002400,"work_assign_id":1000000001701,"assignee_name":"属地监护人","df":0,"orgid":1000000000000,"end_time":null,"assignee_id":1000000000421,"relieved_status":null,"updated_by":1000,"tenantid":1,"updated_dt":"2017-06-29 14:37:54","work_task_id":1000000000940,"appr_auth_id":1000000000388,"updated_by_name":"系统管理员","dataStatus":0}},"attachDataMap":null,"bodyVOs":null,"attachDataVOs":null}]},"attachDataVOs":null},{"headVO":{"RMT_WORK_WFINSTANCE_M":{"tableName":"rmt_work_wfinstance","pre_node_id":"1000000000460","status":"wappr","alias":"主修人（一般作业关闭）","appr_node_id":1000000000268,"work_type":"workclose","stage":"close","func_order":6,"wfinstance_id":1000000000612,"work_subtask_id":1000000002400,"next_node_id":null,"assignee_id":"1000000000417","work_task_id":1000000000940,"appr_auth_id":1000000000462,"m_intfc_id":1000000000471,"dataStatus":0}},"attachDataMap":null,"bodyVOs":{"RMT_WORK_ASSIGN_LINE_M":[{"headVO":{"RMT_WORK_ASSIGN_LINE_M":{"tableName":"rmt_work_assign_line","created_by":1000,"ts":1498718274166,"assign_time":null,"assign_line_id":1000000000184,"relieved_time":null,"ver":1,"isenable":1,"created_by_name":"系统管理员","product_flag":2,"created_dt":"2017-06-29 14:37:54","work_subtask_id":1000000002400,"work_assign_id":1000000001682,"assignee_name":"作业人员","df":0,"orgid":1000000000000,"end_time":null,"assignee_id":1000000000417,"relieved_status":null,"updated_by":1000,"tenantid":1,"updated_dt":"2017-06-29 14:37:54","work_task_id":1000000000940,"appr_auth_id":1000000000462,"updated_by_name":"系统管理员","dataStatus":0}},"attachDataMap":null,"bodyVOs":null,"attachDataVOs":null}]},"attachDataVOs":null},{"headVO":{"RMT_WORK_WFINSTANCE_M":{"tableName":"rmt_work_wfinstance","pre_node_id":"1000000000388","status":"wappr","alias":"主修人（动火作业关闭）","appr_node_id":1000000000268,"work_type":"workclose","stage":"close","func_order":48,"wfinstance_id":1000000000625,"work_subtask_id":1000000002400,"next_node_id":null,"assignee_id":"1000000000417","work_task_id":1000000000940,"appr_auth_id":1000000000389,"m_intfc_id":1000000000529,"dataStatus":0}},"attachDataMap":null,"bodyVOs":{"RMT_WORK_ASSIGN_LINE_M":[{"headVO":{"RMT_WORK_ASSIGN_LINE_M":{"tableName":"rmt_work_assign_line","created_by":1000,"ts":1498718274167,"assign_time":null,"assign_line_id":1000000000185,"relieved_time":null,"ver":1,"isenable":1,"created_by_name":"系统管理员","product_flag":2,"created_dt":"2017-06-29 14:37:54","work_subtask_id":1000000002400,"work_assign_id":1000000001682,"assignee_name":"作业人员","df":0,"orgid":1000000000000,"end_time":null,"assignee_id":1000000000417,"relieved_status":null,"updated_by":1000,"tenantid":1,"updated_dt":"2017-06-29 14:37:54","work_task_id":1000000000940,"appr_auth_id":1000000000389,"updated_by_name":"系统管理员","dataStatus":0}},"attachDataMap":null,"bodyVOs":null,"attachDataVOs":null}]},"attachDataVOs":null},{"headVO":{"RMT_WORK_WFINSTANCE_M":{"tableName":"rmt_work_wfinstance","pre_node_id":null,"status":"wappr","alias":"当班班长（关闭）","appr_node_id":1000000000270,"work_type":"workclose","stage":"close","func_order":6,"wfinstance_id":1000000000613,"work_subtask_id":1000000002400,"next_node_id":"1000000000391","assignee_id":"1000000000029","work_task_id":1000000000940,"appr_auth_id":1000000000390,"m_intfc_id":1000000000471,"dataStatus":0}},"attachDataMap":null,"bodyVOs":{"RMT_WORK_ASSIGN_LINE_M":[{"headVO":{"RMT_WORK_ASSIGN_LINE_M":{"tableName":"rmt_work_assign_line","created_by":1000,"ts":1498718274245,"assign_time":null,"assign_line_id":1000000000190,"relieved_time":null,"ver":1,"isenable":1,"created_by_name":"系统管理员","product_flag":2,"created_dt":"2017-06-29 14:37:54","work_subtask_id":1000000002400,"work_assign_id":1000000001684,"assignee_name":"属地值班长","df":0,"orgid":1000000000000,"end_time":null,"assignee_id":1000000000029,"relieved_status":null,"updated_by":1000,"tenantid":1,"updated_dt":"2017-06-29 14:37:54","work_task_id":1000000000940,"appr_auth_id":1000000000390,"updated_by_name":"系统管理员","dataStatus":0}},"attachDataMap":null,"bodyVOs":null,"attachDataVOs":null}]},"attachDataVOs":null},{"headVO":{"RMT_WORK_WFINSTANCE_M":{"tableName":"rmt_work_wfinstance","pre_node_id":"1000000000390","status":"wappr","alias":"作业申请人","appr_node_id":1000000000272,"work_type":"workclose","stage":"close","func_order":6,"wfinstance_id":1000000000614,"work_subtask_id":1000000002400,"next_node_id":null,"assignee_id":"1000000000418","work_task_id":1000000000940,"appr_auth_id":1000000000391,"m_intfc_id":1000000000471,"dataStatus":0}},"attachDataMap":null,"bodyVOs":{"RMT_WORK_ASSIGN_LINE_M":[{"headVO":{"RMT_WORK_ASSIGN_LINE_M":{"tableName":"rmt_work_assign_line","created_by":1000,"ts":1498718274088,"assign_time":null,"assign_line_id":1000000000180,"relieved_time":null,"ver":1,"isenable":1,"created_by_name":"系统管理员","product_flag":2,"created_dt":"2017-06-29 14:37:54","work_subtask_id":1000000002400,"work_assign_id":1000000001680,"assignee_name":"作业申请人","df":0,"orgid":1000000000000,"end_time":null,"assignee_id":1000000000418,"relieved_status":null,"updated_by":1000,"tenantid":1,"updated_dt":"2017-06-29 14:37:54","work_task_id":1000000000940,"appr_auth_id":1000000000391,"updated_by_name":"系统管理员","dataStatus":0}},"attachDataMap":null,"bodyVOs":null,"attachDataVOs":null}]},"attachDataVOs":null}]},"attachDataVOs":null}]
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

    public static class MainvoBean {
        /**
         * headVO : {"RMT_WORK_PERMIT_M":{"tableName":"rmt_work_permit","ts":1498717557090,"owner_name":null,"est_start_time":null,"work_reason":null,"upload_file":0,"hst_ts":0,"orgid":1000000000000,"hst_rated_load":null,"work_site_id":null,"hst_object_volume":null,"tenantid":1,"updated_dt":"2017-06-29 14:25:56","created_by":1000,"work_unit_name":"安装检修公司","status":"wappr","est_end_time":null,"act_start_time":null,"hst_angle":null,"elec_voltage":null,"work_name":"0626001-主任务-hqs","work_task_id":1000000000940,"work_content":"0626001-主任务-hqs","related_unit_name":null,"work_permit_no":"GEN2017062900010","elec_power_access_point":null,"work_site_name":null,"elec_total_load":null,"work_unit_id":1000000000008,"hst_radius":null,"ver":1,"created_by_name":"系统管理员","hst_object_weight":null,"product_flag":2,"project_type":null,"df":0,"related_unit_id":null,"hst_object_name":null,"updated_by":1000,"elec_purpose":null,"act_end_time":null,"territorial_unit_name":"中国石油宁夏石化公司","work_type":"gen","isenable":1,"work_permit_id":1000000001900,"created_dt":"2017-06-29 14:25:56","work_subtask_id":1000000002400,"owner_id":null,"territorial_unit_id":1000000000000,"hst_height":null,"hst_lift_weight":null,"updated_by_name":"系统管理员","dataStatus":0}}
         * attachDataMap : null
         * bodyVOs : {"RMT_WORK_WFINSTANCE_M":[{"headVO":{"RMT_WORK_WFINSTANCE_M":{"tableName":"rmt_work_wfinstance","pre_node_id":null,"status":"inprg","alias":"一般岗位主操","appr_node_id":1000000000276,"work_type":"gen","stage":"approve","func_order":1,"wfinstance_id":1000000000610,"work_subtask_id":1000000002400,"next_node_id":"1000000000461,1000000000463","assignee_id":"1000000000420","work_task_id":1000000000940,"appr_auth_id":1000000000465,"m_intfc_id":1000000000520,"dataStatus":0}},"attachDataMap":null,"bodyVOs":{"RMT_WORK_ASSIGN_LINE_M":[{"headVO":{"RMT_WORK_ASSIGN_LINE_M":{"tableName":"rmt_work_assign_line","created_by":1000,"ts":1498718274131,"assign_time":null,"assign_line_id":1000000000182,"relieved_time":null,"ver":1,"isenable":1,"created_by_name":"系统管理员","product_flag":2,"created_dt":"2017-06-29 14:37:54","work_subtask_id":1000000002400,"work_assign_id":1000000001681,"assignee_name":"岗位主操","df":0,"orgid":1000000000000,"end_time":null,"assignee_id":1000000000420,"relieved_status":null,"updated_by":1000,"tenantid":1,"updated_dt":"2017-06-29 14:37:54","work_task_id":1000000000940,"appr_auth_id":1000000000465,"updated_by_name":"系统管理员","dataStatus":0}},"attachDataMap":null,"bodyVOs":null,"attachDataVOs":null}]},"attachDataVOs":null},{"headVO":{"RMT_WORK_WFINSTANCE_M":{"tableName":"rmt_work_wfinstance","pre_node_id":"1000000000465","status":"wappr","alias":"一般作业人（危害）","appr_node_id":1000000000271,"work_type":"gen","stage":"approve","func_order":1,"wfinstance_id":1000000000609,"work_subtask_id":1000000002400,"next_node_id":"1000000000464","assignee_id":"1000000000417","work_task_id":1000000000940,"appr_auth_id":1000000000461,"m_intfc_id":1000000000523,"dataStatus":0}},"attachDataMap":null,"bodyVOs":{"RMT_WORK_ASSIGN_LINE_M":[{"headVO":{"RMT_WORK_ASSIGN_LINE_M":{"tableName":"rmt_work_assign_line","created_by":1000,"ts":1498718274216,"assign_time":null,"assign_line_id":1000000000187,"relieved_time":null,"ver":1,"isenable":1,"created_by_name":"系统管理员","product_flag":2,"created_dt":"2017-06-29 14:37:54","work_subtask_id":1000000002400,"work_assign_id":1000000001683,"assignee_name":"作业人员","df":0,"orgid":1000000000000,"end_time":null,"assignee_id":1000000000417,"relieved_status":null,"updated_by":1000,"tenantid":1,"updated_dt":"2017-06-29 14:37:54","work_task_id":1000000000940,"appr_auth_id":1000000000461,"updated_by_name":"系统管理员","dataStatus":0}},"attachDataMap":null,"bodyVOs":null,"attachDataVOs":null}]},"attachDataVOs":null},{"headVO":{"RMT_WORK_WFINSTANCE_M":{"tableName":"rmt_work_wfinstance","pre_node_id":"1000000000465","status":"wappr","alias":"一般作业人(措施审批)","appr_node_id":1000000000271,"work_type":"gen","stage":"approve","func_order":2,"wfinstance_id":1000000000607,"work_subtask_id":1000000002400,"next_node_id":"1000000000464","assignee_id":"1000000000417","work_task_id":1000000000940,"appr_auth_id":1000000000463,"m_intfc_id":1000000000521,"dataStatus":0}},"attachDataMap":null,"bodyVOs":{"RMT_WORK_ASSIGN_LINE_M":[{"headVO":{"RMT_WORK_ASSIGN_LINE_M":{"tableName":"rmt_work_assign_line","created_by":1000,"ts":1498718274216,"assign_time":null,"assign_line_id":1000000000188,"relieved_time":null,"ver":1,"isenable":1,"created_by_name":"系统管理员","product_flag":2,"created_dt":"2017-06-29 14:37:54","work_subtask_id":1000000002400,"work_assign_id":1000000001683,"assignee_name":"作业人员","df":0,"orgid":1000000000000,"end_time":null,"assignee_id":1000000000417,"relieved_status":null,"updated_by":1000,"tenantid":1,"updated_dt":"2017-06-29 14:37:54","work_task_id":1000000000940,"appr_auth_id":1000000000463,"updated_by_name":"系统管理员","dataStatus":0}},"attachDataMap":null,"bodyVOs":null,"attachDataVOs":null}]},"attachDataVOs":null},{"headVO":{"RMT_WORK_WFINSTANCE_M":{"tableName":"rmt_work_wfinstance","pre_node_id":"1000000000463,1000000000461","status":"wappr","alias":"一般作业人（措施确认）","appr_node_id":1000000000271,"work_type":"gen","stage":"review","func_order":5,"wfinstance_id":1000000000608,"work_subtask_id":1000000002400,"next_node_id":null,"assignee_id":"1000000000417","work_task_id":1000000000940,"appr_auth_id":1000000000464,"m_intfc_id":1000000000522,"dataStatus":0}},"attachDataMap":null,"bodyVOs":{"RMT_WORK_ASSIGN_LINE_M":[{"headVO":{"RMT_WORK_ASSIGN_LINE_M":{"tableName":"rmt_work_assign_line","created_by":1000,"ts":1498718274217,"assign_time":null,"assign_line_id":1000000000189,"relieved_time":null,"ver":1,"isenable":1,"created_by_name":"系统管理员","product_flag":2,"created_dt":"2017-06-29 14:37:54","work_subtask_id":1000000002400,"work_assign_id":1000000001683,"assignee_name":"作业人员","df":0,"orgid":1000000000000,"end_time":null,"assignee_id":1000000000417,"relieved_status":null,"updated_by":1000,"tenantid":1,"updated_dt":"2017-06-29 14:37:54","work_task_id":1000000000940,"appr_auth_id":1000000000464,"updated_by_name":"系统管理员","dataStatus":0}},"attachDataMap":null,"bodyVOs":null,"attachDataVOs":null}]},"attachDataVOs":null}]}
         * attachDataVOs : null
         */

        private HeadVOBean headVO;
        private Object attachDataMap;
        private BodyVOsBeanX bodyVOs;
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

        public BodyVOsBeanX getBodyVOs() {
            return bodyVOs;
        }

        public void setBodyVOs(BodyVOsBeanX bodyVOs) {
            this.bodyVOs = bodyVOs;
        }

        public Object getAttachDataVOs() {
            return attachDataVOs;
        }

        public void setAttachDataVOs(Object attachDataVOs) {
            this.attachDataVOs = attachDataVOs;
        }

        public static class HeadVOBean {
            /**
             * RMT_WORK_PERMIT_M : {"tableName":"rmt_work_permit","ts":1498717557090,"owner_name":null,"est_start_time":null,"work_reason":null,"upload_file":0,"hst_ts":0,"orgid":1000000000000,"hst_rated_load":null,"work_site_id":null,"hst_object_volume":null,"tenantid":1,"updated_dt":"2017-06-29 14:25:56","created_by":1000,"work_unit_name":"安装检修公司","status":"wappr","est_end_time":null,"act_start_time":null,"hst_angle":null,"elec_voltage":null,"work_name":"0626001-主任务-hqs","work_task_id":1000000000940,"work_content":"0626001-主任务-hqs","related_unit_name":null,"work_permit_no":"GEN2017062900010","elec_power_access_point":null,"work_site_name":null,"elec_total_load":null,"work_unit_id":1000000000008,"hst_radius":null,"ver":1,"created_by_name":"系统管理员","hst_object_weight":null,"product_flag":2,"project_type":null,"df":0,"related_unit_id":null,"hst_object_name":null,"updated_by":1000,"elec_purpose":null,"act_end_time":null,"territorial_unit_name":"中国石油宁夏石化公司","work_type":"gen","isenable":1,"work_permit_id":1000000001900,"created_dt":"2017-06-29 14:25:56","work_subtask_id":1000000002400,"owner_id":null,"territorial_unit_id":1000000000000,"hst_height":null,"hst_lift_weight":null,"updated_by_name":"系统管理员","dataStatus":0}
             */

            private RMTWORKPERMITMBean RMT_WORK_PERMIT_M;

            public RMTWORKPERMITMBean getRMT_WORK_PERMIT_M() {
                return RMT_WORK_PERMIT_M;
            }

            public void setRMT_WORK_PERMIT_M(RMTWORKPERMITMBean RMT_WORK_PERMIT_M) {
                this.RMT_WORK_PERMIT_M = RMT_WORK_PERMIT_M;
            }

            public static class RMTWORKPERMITMBean {
                /**
                 * tableName : rmt_work_permit
                 * ts : 1498717557090
                 * owner_name : null
                 * est_start_time : null
                 * work_reason : null
                 * upload_file : 0
                 * hst_ts : 0
                 * orgid : 1000000000000
                 * hst_rated_load : null
                 * work_site_id : null
                 * hst_object_volume : null
                 * tenantid : 1
                 * updated_dt : 2017-06-29 14:25:56
                 * created_by : 1000
                 * work_unit_name : 安装检修公司
                 * status : wappr
                 * est_end_time : null
                 * act_start_time : null
                 * hst_angle : null
                 * elec_voltage : null
                 * work_name : 0626001-主任务-hqs
                 * work_task_id : 1000000000940
                 * work_content : 0626001-主任务-hqs
                 * related_unit_name : null
                 * work_permit_no : GEN2017062900010
                 * elec_power_access_point : null
                 * work_site_name : null
                 * elec_total_load : null
                 * work_unit_id : 1000000000008
                 * hst_radius : null
                 * ver : 1
                 * created_by_name : 系统管理员
                 * hst_object_weight : null
                 * product_flag : 2
                 * project_type : null
                 * df : 0
                 * related_unit_id : null
                 * hst_object_name : null
                 * updated_by : 1000
                 * elec_purpose : null
                 * act_end_time : null
                 * territorial_unit_name : 中国石油宁夏石化公司
                 * work_type : gen
                 * isenable : 1
                 * work_permit_id : 1000000001900
                 * created_dt : 2017-06-29 14:25:56
                 * work_subtask_id : 1000000002400
                 * owner_id : null
                 * territorial_unit_id : 1000000000000
                 * hst_height : null
                 * hst_lift_weight : null
                 * updated_by_name : 系统管理员
                 * dataStatus : 0
                 */

                private String tableName;
                private long ts;
                private Object owner_name;
                private Object est_start_time;
                private Object work_reason;
                private int upload_file;
                private int hst_ts;
                private long orgid;
                private Object hst_rated_load;
                private long work_site_id;
                private Object hst_object_volume;
                private long tenantid;
                private String updated_dt;
                private long created_by;
                private String work_unit_name;
                private String status;
                private Object est_end_time;
                private Object act_start_time;
                private Object hst_angle;
                private Object elec_voltage;
                private String work_name;
                private long work_task_id;
                private String work_content;
                private Object related_unit_name;
                private String work_permit_no;
                private Object elec_power_access_point;
                private Object work_site_name;
                private Object elec_total_load;
                private long work_unit_id;
                private Object hst_radius;
                private int ver;
                private String created_by_name;
                private Object hst_object_weight;
                private int product_flag;
                private Object project_type;
                private int df;
                private long related_unit_id;
                private Object hst_object_name;
                private long updated_by;
                private Object elec_purpose;
                private Object act_end_time;
                private String territorial_unit_name;
                private String work_type;
                private String work_type_name;

                public String getWork_type_name() {
                    return work_type_name;
                }

                public void setWork_type_name(String work_type_name) {
                    this.work_type_name = work_type_name;
                }

                private int isenable;
                private long work_permit_id;
                private String created_dt;
                private long work_subtask_id;
                private long owner_id;
                private long territorial_unit_id;
                private Object hst_height;
                private Object hst_lift_weight;
                private String updated_by_name;
                private int dataStatus;

                public String getTableName() {
                    return tableName;
                }

                public void setTableName(String tableName) {
                    this.tableName = tableName;
                }

                public long getTs() {
                    return ts;
                }

                public void setTs(long ts) {
                    this.ts = ts;
                }

                public Object getOwner_name() {
                    return owner_name;
                }

                public void setOwner_name(Object owner_name) {
                    this.owner_name = owner_name;
                }

                public Object getEst_start_time() {
                    return est_start_time;
                }

                public void setEst_start_time(Object est_start_time) {
                    this.est_start_time = est_start_time;
                }

                public Object getWork_reason() {
                    return work_reason;
                }

                public void setWork_reason(Object work_reason) {
                    this.work_reason = work_reason;
                }

                public int getUpload_file() {
                    return upload_file;
                }

                public void setUpload_file(int upload_file) {
                    this.upload_file = upload_file;
                }

                public int getHst_ts() {
                    return hst_ts;
                }

                public void setHst_ts(int hst_ts) {
                    this.hst_ts = hst_ts;
                }

                public long getOrgid() {
                    return orgid;
                }

                public void setOrgid(long orgid) {
                    this.orgid = orgid;
                }

                public Object getHst_rated_load() {
                    return hst_rated_load;
                }

                public void setHst_rated_load(Object hst_rated_load) {
                    this.hst_rated_load = hst_rated_load;
                }

                public long getWork_site_id() {
                    return work_site_id;
                }

                public void setWork_site_id(long work_site_id) {
                    this.work_site_id = work_site_id;
                }

                public Object getHst_object_volume() {
                    return hst_object_volume;
                }

                public void setHst_object_volume(Object hst_object_volume) {
                    this.hst_object_volume = hst_object_volume;
                }

                public long getTenantid() {
                    return tenantid;
                }

                public void setTenantid(long tenantid) {
                    this.tenantid = tenantid;
                }

                public String getUpdated_dt() {
                    return updated_dt;
                }

                public void setUpdated_dt(String updated_dt) {
                    this.updated_dt = updated_dt;
                }

                public long getCreated_by() {
                    return created_by;
                }

                public void setCreated_by(long created_by) {
                    this.created_by = created_by;
                }

                public String getWork_unit_name() {
                    return work_unit_name;
                }

                public void setWork_unit_name(String work_unit_name) {
                    this.work_unit_name = work_unit_name;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public Object getEst_end_time() {
                    return est_end_time;
                }

                public void setEst_end_time(Object est_end_time) {
                    this.est_end_time = est_end_time;
                }

                public Object getAct_start_time() {
                    return act_start_time;
                }

                public void setAct_start_time(Object act_start_time) {
                    this.act_start_time = act_start_time;
                }

                public Object getHst_angle() {
                    return hst_angle;
                }

                public void setHst_angle(Object hst_angle) {
                    this.hst_angle = hst_angle;
                }

                public Object getElec_voltage() {
                    return elec_voltage;
                }

                public void setElec_voltage(Object elec_voltage) {
                    this.elec_voltage = elec_voltage;
                }

                public String getWork_name() {
                    return work_name;
                }

                public void setWork_name(String work_name) {
                    this.work_name = work_name;
                }

                public long getWork_task_id() {
                    return work_task_id;
                }

                public void setWork_task_id(long work_task_id) {
                    this.work_task_id = work_task_id;
                }

                public String getWork_content() {
                    return work_content;
                }

                public void setWork_content(String work_content) {
                    this.work_content = work_content;
                }

                public Object getRelated_unit_name() {
                    return related_unit_name;
                }

                public void setRelated_unit_name(Object related_unit_name) {
                    this.related_unit_name = related_unit_name;
                }

                public String getWork_permit_no() {
                    return work_permit_no;
                }

                public void setWork_permit_no(String work_permit_no) {
                    this.work_permit_no = work_permit_no;
                }

                public Object getElec_power_access_point() {
                    return elec_power_access_point;
                }

                public void setElec_power_access_point(Object elec_power_access_point) {
                    this.elec_power_access_point = elec_power_access_point;
                }

                public Object getWork_site_name() {
                    return work_site_name;
                }

                public void setWork_site_name(Object work_site_name) {
                    this.work_site_name = work_site_name;
                }

                public Object getElec_total_load() {
                    return elec_total_load;
                }

                public void setElec_total_load(Object elec_total_load) {
                    this.elec_total_load = elec_total_load;
                }

                public long getWork_unit_id() {
                    return work_unit_id;
                }

                public void setWork_unit_id(long work_unit_id) {
                    this.work_unit_id = work_unit_id;
                }

                public Object getHst_radius() {
                    return hst_radius;
                }

                public void setHst_radius(Object hst_radius) {
                    this.hst_radius = hst_radius;
                }

                public int getVer() {
                    return ver;
                }

                public void setVer(int ver) {
                    this.ver = ver;
                }

                public String getCreated_by_name() {
                    return created_by_name;
                }

                public void setCreated_by_name(String created_by_name) {
                    this.created_by_name = created_by_name;
                }

                public Object getHst_object_weight() {
                    return hst_object_weight;
                }

                public void setHst_object_weight(Object hst_object_weight) {
                    this.hst_object_weight = hst_object_weight;
                }

                public int getProduct_flag() {
                    return product_flag;
                }

                public void setProduct_flag(int product_flag) {
                    this.product_flag = product_flag;
                }

                public Object getProject_type() {
                    return project_type;
                }

                public void setProject_type(Object project_type) {
                    this.project_type = project_type;
                }

                public int getDf() {
                    return df;
                }

                public void setDf(int df) {
                    this.df = df;
                }

                public long getRelated_unit_id() {
                    return related_unit_id;
                }

                public void setRelated_unit_id(long related_unit_id) {
                    this.related_unit_id = related_unit_id;
                }

                public Object getHst_object_name() {
                    return hst_object_name;
                }

                public void setHst_object_name(Object hst_object_name) {
                    this.hst_object_name = hst_object_name;
                }

                public long getUpdated_by() {
                    return updated_by;
                }

                public void setUpdated_by(long updated_by) {
                    this.updated_by = updated_by;
                }

                public Object getElec_purpose() {
                    return elec_purpose;
                }

                public void setElec_purpose(Object elec_purpose) {
                    this.elec_purpose = elec_purpose;
                }

                public Object getAct_end_time() {
                    return act_end_time;
                }

                public void setAct_end_time(Object act_end_time) {
                    this.act_end_time = act_end_time;
                }

                public String getTerritorial_unit_name() {
                    return territorial_unit_name;
                }

                public void setTerritorial_unit_name(String territorial_unit_name) {
                    this.territorial_unit_name = territorial_unit_name;
                }

                public String getWork_type() {
                    return work_type;
                }

                public void setWork_type(String work_type) {
                    this.work_type = work_type;
                }

                public int getIsenable() {
                    return isenable;
                }

                public void setIsenable(int isenable) {
                    this.isenable = isenable;
                }

                public long getWork_permit_id() {
                    return work_permit_id;
                }

                public void setWork_permit_id(long work_permit_id) {
                    this.work_permit_id = work_permit_id;
                }

                public String getCreated_dt() {
                    return created_dt;
                }

                public void setCreated_dt(String created_dt) {
                    this.created_dt = created_dt;
                }

                public long getWork_subtask_id() {
                    return work_subtask_id;
                }

                public void setWork_subtask_id(long work_subtask_id) {
                    this.work_subtask_id = work_subtask_id;
                }

                public long getOwner_id() {
                    return owner_id;
                }

                public void setOwner_id(long owner_id) {
                    this.owner_id = owner_id;
                }

                public long getTerritorial_unit_id() {
                    return territorial_unit_id;
                }

                public void setTerritorial_unit_id(long territorial_unit_id) {
                    this.territorial_unit_id = territorial_unit_id;
                }

                public Object getHst_height() {
                    return hst_height;
                }

                public void setHst_height(Object hst_height) {
                    this.hst_height = hst_height;
                }

                public Object getHst_lift_weight() {
                    return hst_lift_weight;
                }

                public void setHst_lift_weight(Object hst_lift_weight) {
                    this.hst_lift_weight = hst_lift_weight;
                }

                public String getUpdated_by_name() {
                    return updated_by_name;
                }

                public void setUpdated_by_name(String updated_by_name) {
                    this.updated_by_name = updated_by_name;
                }

                public int getDataStatus() {
                    return dataStatus;
                }

                public void setDataStatus(int dataStatus) {
                    this.dataStatus = dataStatus;
                }
            }
        }

        public static class BodyVOsBeanX {
            private List<RMTWORKWFINSTANCEMBeanX> RMT_WORK_WFINSTANCE_M;

            public List<RMTWORKWFINSTANCEMBeanX> getRMT_WORK_WFINSTANCE_M() {
                return RMT_WORK_WFINSTANCE_M;
            }

            public void setRMT_WORK_WFINSTANCE_M(List<RMTWORKWFINSTANCEMBeanX> RMT_WORK_WFINSTANCE_M) {
                this.RMT_WORK_WFINSTANCE_M = RMT_WORK_WFINSTANCE_M;
            }

            public static class RMTWORKWFINSTANCEMBeanX extends SuperEntity{
                /**
                 * headVO : {"RMT_WORK_WFINSTANCE_M":{"tableName":"rmt_work_wfinstance","pre_node_id":null,"status":"inprg","alias":"一般岗位主操","appr_node_id":1000000000276,"work_type":"gen","stage":"approve","func_order":1,"wfinstance_id":1000000000610,"work_subtask_id":1000000002400,"next_node_id":"1000000000461,1000000000463","assignee_id":"1000000000420","work_task_id":1000000000940,"appr_auth_id":1000000000465,"m_intfc_id":1000000000520,"dataStatus":0}}
                 * attachDataMap : null
                 * bodyVOs : {"RMT_WORK_ASSIGN_LINE_M":[{"headVO":{"RMT_WORK_ASSIGN_LINE_M":{"tableName":"rmt_work_assign_line","created_by":1000,"ts":1498718274131,"assign_time":null,"assign_line_id":1000000000182,"relieved_time":null,"ver":1,"isenable":1,"created_by_name":"系统管理员","product_flag":2,"created_dt":"2017-06-29 14:37:54","work_subtask_id":1000000002400,"work_assign_id":1000000001681,"assignee_name":"岗位主操","df":0,"orgid":1000000000000,"end_time":null,"assignee_id":1000000000420,"relieved_status":null,"updated_by":1000,"tenantid":1,"updated_dt":"2017-06-29 14:37:54","work_task_id":1000000000940,"appr_auth_id":1000000000465,"updated_by_name":"系统管理员","dataStatus":0}},"attachDataMap":null,"bodyVOs":null,"attachDataVOs":null}]}
                 * attachDataVOs : null
                 */

                private HeadVOBeanX headVO;
                private Object attachDataMap;
                private BodyVOsBean bodyVOs;
                private Object attachDataVOs;

                public HeadVOBeanX getHeadVO() {
                    return headVO;
                }

                public void setHeadVO(HeadVOBeanX headVO) {
                    this.headVO = headVO;
                }

                public Object getAttachDataMap() {
                    return attachDataMap;
                }

                public void setAttachDataMap(Object attachDataMap) {
                    this.attachDataMap = attachDataMap;
                }

                public BodyVOsBean getBodyVOs() {
                    return bodyVOs;
                }

                public void setBodyVOs(BodyVOsBean bodyVOs) {
                    this.bodyVOs = bodyVOs;
                }

                public Object getAttachDataVOs() {
                    return attachDataVOs;
                }

                public void setAttachDataVOs(Object attachDataVOs) {
                    this.attachDataVOs = attachDataVOs;
                }

                public static class HeadVOBeanX {
                    /**
                     * RMT_WORK_WFINSTANCE_M : {"tableName":"rmt_work_wfinstance","pre_node_id":null,"status":"inprg","alias":"一般岗位主操","appr_node_id":1000000000276,"work_type":"gen","stage":"approve","func_order":1,"wfinstance_id":1000000000610,"work_subtask_id":1000000002400,"next_node_id":"1000000000461,1000000000463","assignee_id":"1000000000420","work_task_id":1000000000940,"appr_auth_id":1000000000465,"m_intfc_id":1000000000520,"dataStatus":0}
                     */

                    private RMTWORKWFINSTANCEMBean RMT_WORK_WFINSTANCE_M;

                    public RMTWORKWFINSTANCEMBean getRMT_WORK_WFINSTANCE_M() {
                        return RMT_WORK_WFINSTANCE_M;
                    }

                    public void setRMT_WORK_WFINSTANCE_M(RMTWORKWFINSTANCEMBean RMT_WORK_WFINSTANCE_M) {
                        this.RMT_WORK_WFINSTANCE_M = RMT_WORK_WFINSTANCE_M;
                    }

                    public static class RMTWORKWFINSTANCEMBean {
                        /**
                         * tableName : rmt_work_wfinstance
                         * pre_node_id : null
                         * status : inprg
                         * alias : 一般岗位主操
                         * appr_node_id : 1000000000276
                         * work_type : gen
                         * stage : approve
                         * func_order : 1
                         * wfinstance_id : 1000000000610
                         * work_subtask_id : 1000000002400
                         * next_node_id : 1000000000461,1000000000463
                         * assignee_id : 1000000000420
                         * work_task_id : 1000000000940
                         * appr_auth_id : 1000000000465
                         * m_intfc_id : 1000000000520
                         * dataStatus : 0
                         */

                        private String tableName;
                        private String pre_node_id;
                        private String status;
                        private String status_name;
                        private String alias;
                        private long appr_node_id;
                        private String work_type;
                        private String stage;
                        private int func_order;
                        private long wfinstance_id;
                        private long work_subtask_id;
                        private String next_node_id;
                        private String assignee_id;
                        private long work_task_id;
                        private long appr_auth_id;
                        private long m_intfc_id;
                        private int dataStatus;

                        public String getStatus_name() {
                            return status_name;
                        }

                        public void setStatus_name(String status_name) {
                            this.status_name = status_name;
                        }

                        public String getTableName() {
                            return tableName;
                        }

                        public void setTableName(String tableName) {
                            this.tableName = tableName;
                        }

                        public String getPre_node_id() {
                            return pre_node_id;
                        }

                        public void setPre_node_id(String pre_node_id) {
                            this.pre_node_id = pre_node_id;
                        }

                        public String getStatus() {
                            return status;
                        }

                        public void setStatus(String status) {
                            this.status = status;
                        }

                        public String getAlias() {
                            return alias;
                        }

                        public void setAlias(String alias) {
                            this.alias = alias;
                        }

                        public long getAppr_node_id() {
                            return appr_node_id;
                        }

                        public void setAppr_node_id(long appr_node_id) {
                            this.appr_node_id = appr_node_id;
                        }

                        public String getWork_type() {
                            return work_type;
                        }

                        public void setWork_type(String work_type) {
                            this.work_type = work_type;
                        }

                        public String getStage() {
                            return stage;
                        }

                        public void setStage(String stage) {
                            this.stage = stage;
                        }

                        public int getFunc_order() {
                            return func_order;
                        }

                        public void setFunc_order(int func_order) {
                            this.func_order = func_order;
                        }

                        public long getWfinstance_id() {
                            return wfinstance_id;
                        }

                        public void setWfinstance_id(long wfinstance_id) {
                            this.wfinstance_id = wfinstance_id;
                        }

                        public long getWork_subtask_id() {
                            return work_subtask_id;
                        }

                        public void setWork_subtask_id(long work_subtask_id) {
                            this.work_subtask_id = work_subtask_id;
                        }

                        public String getNext_node_id() {
                            return next_node_id;
                        }

                        public void setNext_node_id(String next_node_id) {
                            this.next_node_id = next_node_id;
                        }

                        public String getAssignee_id() {
                            return assignee_id;
                        }

                        public void setAssignee_id(String assignee_id) {
                            this.assignee_id = assignee_id;
                        }

                        public long getWork_task_id() {
                            return work_task_id;
                        }

                        public void setWork_task_id(long work_task_id) {
                            this.work_task_id = work_task_id;
                        }

                        public long getAppr_auth_id() {
                            return appr_auth_id;
                        }

                        public void setAppr_auth_id(long appr_auth_id) {
                            this.appr_auth_id = appr_auth_id;
                        }

                        public long getM_intfc_id() {
                            return m_intfc_id;
                        }

                        public void setM_intfc_id(long m_intfc_id) {
                            this.m_intfc_id = m_intfc_id;
                        }

                        public int getDataStatus() {
                            return dataStatus;
                        }

                        public void setDataStatus(int dataStatus) {
                            this.dataStatus = dataStatus;
                        }
                    }
                }

                public static class BodyVOsBean {
                    private List<RMTWORKASSIGNLINEMBeanX> RMT_WORK_ASSIGN_LINE_M;

                    public List<RMTWORKASSIGNLINEMBeanX> getRMT_WORK_ASSIGN_LINE_M() {
                        return RMT_WORK_ASSIGN_LINE_M;
                    }

                    public void setRMT_WORK_ASSIGN_LINE_M(List<RMTWORKASSIGNLINEMBeanX> RMT_WORK_ASSIGN_LINE_M) {
                        this.RMT_WORK_ASSIGN_LINE_M = RMT_WORK_ASSIGN_LINE_M;
                    }

                    public static class RMTWORKASSIGNLINEMBeanX extends SuperEntity {
                        /**
                         * headVO : {"RMT_WORK_ASSIGN_LINE_M":{"tableName":"rmt_work_assign_line","created_by":1000,"ts":1498718274131,"assign_time":null,"assign_line_id":1000000000182,"relieved_time":null,"ver":1,"isenable":1,"created_by_name":"系统管理员","product_flag":2,"created_dt":"2017-06-29 14:37:54","work_subtask_id":1000000002400,"work_assign_id":1000000001681,"assignee_name":"岗位主操","df":0,"orgid":1000000000000,"end_time":null,"assignee_id":1000000000420,"relieved_status":null,"updated_by":1000,"tenantid":1,"updated_dt":"2017-06-29 14:37:54","work_task_id":1000000000940,"appr_auth_id":1000000000465,"updated_by_name":"系统管理员","dataStatus":0}}
                         * attachDataMap : null
                         * bodyVOs : null
                         * attachDataVOs : null
                         */

                        private HeadVOBeanXX headVO;
                        private Object attachDataMap;
                        private Object bodyVOs;
                        private Object attachDataVOs;

                        public HeadVOBeanXX getHeadVO() {
                            return headVO;
                        }

                        public void setHeadVO(HeadVOBeanXX headVO) {
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

                        public static class HeadVOBeanXX {
                            /**
                             * RMT_WORK_ASSIGN_LINE_M : {"tableName":"rmt_work_assign_line","created_by":1000,"ts":1498718274131,"assign_time":null,"assign_line_id":1000000000182,"relieved_time":null,"ver":1,"isenable":1,"created_by_name":"系统管理员","product_flag":2,"created_dt":"2017-06-29 14:37:54","work_subtask_id":1000000002400,"work_assign_id":1000000001681,"assignee_name":"岗位主操","df":0,"orgid":1000000000000,"end_time":null,"assignee_id":1000000000420,"relieved_status":null,"updated_by":1000,"tenantid":1,"updated_dt":"2017-06-29 14:37:54","work_task_id":1000000000940,"appr_auth_id":1000000000465,"updated_by_name":"系统管理员","dataStatus":0}
                             */

                            private RMTWORKASSIGNLINEMBean RMT_WORK_ASSIGN_LINE_M;

                            public RMTWORKASSIGNLINEMBean getRMT_WORK_ASSIGN_LINE_M() {
                                return RMT_WORK_ASSIGN_LINE_M;
                            }

                            public void setRMT_WORK_ASSIGN_LINE_M(RMTWORKASSIGNLINEMBean RMT_WORK_ASSIGN_LINE_M) {
                                this.RMT_WORK_ASSIGN_LINE_M = RMT_WORK_ASSIGN_LINE_M;
                            }

                            public static class RMTWORKASSIGNLINEMBean {
                                /**
                                 * tableName : rmt_work_assign_line
                                 * created_by : 1000
                                 * ts : 1498718274131
                                 * assign_time : null
                                 * assign_line_id : 1000000000182
                                 * relieved_time : null
                                 * ver : 1
                                 * isenable : 1
                                 * created_by_name : 系统管理员
                                 * product_flag : 2
                                 * created_dt : 2017-06-29 14:37:54
                                 * work_subtask_id : 1000000002400
                                 * work_assign_id : 1000000001681
                                 * assignee_name : 岗位主操
                                 * df : 0
                                 * orgid : 1000000000000
                                 * end_time : null
                                 * assignee_id : 1000000000420
                                 * relieved_status : null
                                 * updated_by : 1000
                                 * tenantid : 1
                                 * updated_dt : 2017-06-29 14:37:54
                                 * work_task_id : 1000000000940
                                 * appr_auth_id : 1000000000465
                                 * updated_by_name : 系统管理员
                                 * dataStatus : 0
                                 */

                                private String tableName;
                                private long created_by;
                                private long ts;
                                private Object assign_time;
                                private long assign_line_id;
                                private Object relieved_time;
                                private int ver;
                                private int isenable;
                                private String created_by_name;
                                private int product_flag;
                                private String created_dt;
                                private long work_subtask_id;
                                private long work_assign_id;
                                private String assignee_name;
                                private int df;
                                private long orgid;
                                private String end_time;
                                private long assignee_id;
                                private String relieved_status;
                                private String relieved_status_name;
                                private long updated_by;
                                private long tenantid;
                                private String updated_dt;
                                private long work_task_id;
                                private long appr_auth_id;
                                private String updated_by_name;
                                private int dataStatus;

                                public String getRelieved_status_name() {
                                    return relieved_status_name;
                                }

                                public void setRelieved_status_name(String relieved_status_name) {
                                    this.relieved_status_name = relieved_status_name;
                                }

                                public String getTableName() {
                                    return tableName;
                                }

                                public void setTableName(String tableName) {
                                    this.tableName = tableName;
                                }

                                public long getCreated_by() {
                                    return created_by;
                                }

                                public void setCreated_by(long created_by) {
                                    this.created_by = created_by;
                                }

                                public long getTs() {
                                    return ts;
                                }

                                public void setTs(long ts) {
                                    this.ts = ts;
                                }

                                public Object getAssign_time() {
                                    return assign_time;
                                }

                                public void setAssign_time(Object assign_time) {
                                    this.assign_time = assign_time;
                                }

                                public long getAssign_line_id() {
                                    return assign_line_id;
                                }

                                public void setAssign_line_id(long assign_line_id) {
                                    this.assign_line_id = assign_line_id;
                                }

                                public Object getRelieved_time() {
                                    return relieved_time;
                                }

                                public void setRelieved_time(Object relieved_time) {
                                    this.relieved_time = relieved_time;
                                }

                                public int getVer() {
                                    return ver;
                                }

                                public void setVer(int ver) {
                                    this.ver = ver;
                                }

                                public int getIsenable() {
                                    return isenable;
                                }

                                public void setIsenable(int isenable) {
                                    this.isenable = isenable;
                                }

                                public String getCreated_by_name() {
                                    return created_by_name;
                                }

                                public void setCreated_by_name(String created_by_name) {
                                    this.created_by_name = created_by_name;
                                }

                                public int getProduct_flag() {
                                    return product_flag;
                                }

                                public void setProduct_flag(int product_flag) {
                                    this.product_flag = product_flag;
                                }

                                public String getCreated_dt() {
                                    return created_dt;
                                }

                                public void setCreated_dt(String created_dt) {
                                    this.created_dt = created_dt;
                                }

                                public long getWork_subtask_id() {
                                    return work_subtask_id;
                                }

                                public void setWork_subtask_id(long work_subtask_id) {
                                    this.work_subtask_id = work_subtask_id;
                                }

                                public long getWork_assign_id() {
                                    return work_assign_id;
                                }

                                public void setWork_assign_id(long work_assign_id) {
                                    this.work_assign_id = work_assign_id;
                                }

                                public String getAssignee_name() {
                                    return assignee_name;
                                }

                                public void setAssignee_name(String assignee_name) {
                                    this.assignee_name = assignee_name;
                                }

                                public int getDf() {
                                    return df;
                                }

                                public void setDf(int df) {
                                    this.df = df;
                                }

                                public long getOrgid() {
                                    return orgid;
                                }

                                public void setOrgid(long orgid) {
                                    this.orgid = orgid;
                                }

                                public String getEnd_time() {
                                    return end_time;
                                }

                                public void setEnd_time(String end_time) {
                                    this.end_time = end_time;
                                }

                                public long getAssignee_id() {
                                    return assignee_id;
                                }

                                public void setAssignee_id(long assignee_id) {
                                    this.assignee_id = assignee_id;
                                }

                                public String getRelieved_status() {
                                    return relieved_status;
                                }

                                public void setRelieved_status(String relieved_status) {
                                    this.relieved_status = relieved_status;
                                }

                                public long getUpdated_by() {
                                    return updated_by;
                                }

                                public void setUpdated_by(long updated_by) {
                                    this.updated_by = updated_by;
                                }

                                public long getTenantid() {
                                    return tenantid;
                                }

                                public void setTenantid(long tenantid) {
                                    this.tenantid = tenantid;
                                }

                                public String getUpdated_dt() {
                                    return updated_dt;
                                }

                                public void setUpdated_dt(String updated_dt) {
                                    this.updated_dt = updated_dt;
                                }

                                public long getWork_task_id() {
                                    return work_task_id;
                                }

                                public void setWork_task_id(long work_task_id) {
                                    this.work_task_id = work_task_id;
                                }

                                public long getAppr_auth_id() {
                                    return appr_auth_id;
                                }

                                public void setAppr_auth_id(long appr_auth_id) {
                                    this.appr_auth_id = appr_auth_id;
                                }

                                public String getUpdated_by_name() {
                                    return updated_by_name;
                                }

                                public void setUpdated_by_name(String updated_by_name) {
                                    this.updated_by_name = updated_by_name;
                                }

                                public int getDataStatus() {
                                    return dataStatus;
                                }

                                public void setDataStatus(int dataStatus) {
                                    this.dataStatus = dataStatus;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
