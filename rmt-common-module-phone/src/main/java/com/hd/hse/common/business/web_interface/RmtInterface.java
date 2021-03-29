package com.hd.hse.common.business.web_interface;

import com.hayden.hap.common.common.bussiess.ResultData;
import com.hd.hse.entity.assign.FcbsBean;
import com.hd.hse.entity.assign.RmtAssignRecord;
import com.hd.hse.entity.assign.RmtAssignUser;
import com.hd.hse.entity.assign.RmtWorkAssign;
import com.hd.hse.entity.base.CommonVO;
import com.hd.hse.entity.common.ApprRecordMVO;
import com.hd.hse.entity.common.Image;
import com.hd.hse.entity.common.RmtImageDetail;
import com.hd.hse.entity.conf.RmtConfMIntfc;
import com.hd.hse.entity.qstn.RmtQstnList;
import com.hd.hse.entity.resultdata.EqptType;
import com.hd.hse.entity.resultdata.RmtAuthorizeBsqr;
import com.hd.hse.entity.resultdata.RmtAuthorizeList;
import com.hd.hse.entity.resultdata.RmtAuthorizeListDetail;
import com.hd.hse.entity.resultdata.RmtAuthorizeRefuseReceiveBean;
import com.hd.hse.entity.resultdata.RmtAuthorizeSureSave;
import com.hd.hse.entity.resultdata.RmtAuthorizeTsDetailMessage;
import com.hd.hse.entity.resultdata.RmtBeAuthorizeList;
import com.hd.hse.entity.resultdata.RmtDelayTaskHistoryList;
import com.hd.hse.entity.resultdata.RmtDelayTaskMsg;
import com.hd.hse.entity.resultdata.RmtPauseResetTask;
import com.hd.hse.entity.resultdata.RmtQstnListResltData;
import com.hd.hse.entity.resultdata.RmtSjResultData;
import com.hd.hse.entity.resultdata.RmtWorkAssignResultData;
import com.hd.hse.entity.resultdata.RmtWorkEnd;
import com.hd.hse.entity.resultdata.RmtWorkSubtaskResltData;
import com.hd.hse.entity.resultdata.WorkAuthorizeRecover;
import com.hd.hse.entity.resultdata.WorkSite;
import com.hd.hse.entity.resultdata.ZscBean;
import com.hd.hse.entity.sign.ImgStringVo;
import com.hd.hse.entity.sys.RmtSignRecord;
import com.hd.hse.entity.sys.RmtSysUser;
import com.hd.hse.entity.workorder.ResultDataBean;
import com.hd.hse.entity.workorder.RmtMainPageSchedule;
import com.hd.hse.entity.workorder.RmtTaskListQuery;
import com.hd.hse.entity.workorder.RmtTaskOrganizeListQuery;
import com.hd.hse.entity.workorder.RmtWorkGasDetect;
import com.hd.hse.entity.workorder.RmtWorkSubtask;
import com.hd.hse.entity.workorder.StatisticsDept;
import com.hd.hse.entity.workorder.StatisticsTodayWorkUrl;
import com.hd.hse.entity.workorder.TaskDetail;
import com.hd.hse.entity.workorder.TaskUrl;
import com.hd.hse.entity.workorder.TermContent;
import com.hd.hse.entity.workorder.TermType;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * created by yangning on 2017/5/10 15:29.
 */

public interface RmtInterface {
    /**
     * @param userId 用户名
     * @param stage  功能编码
     * @return 分项任务列表
     */
    @GET("m/rmt_m/RMT_WORK_SUBTASK_M/listQuery.json")
    Call<ResultData<ResultDataBean<RmtWorkSubtask>>> getRmtWorkSubtaskList(@Query("userId") long userId, @Query("stage") String stage, @Query("extWhere") String extWhere,@Query("page") int page,@Query("rows") int rows);


    /**
     * 获取待分项任务
     *
     * @param userId    用户id
     * @param subtaskId 分项任务id
     * @return 分项任务
     */
    @GET("m/rmt_m/RMT_WORK_SUBTASK_M/getSubtask_noValBtn.json")
    Call<ResultData<List<RmtWorkSubtaskResltData>>> getRmtWorkSubtask(@Query("userId") long userId, @Query("subtaskId") long subtaskId, @Query("stage") String stage);


    /**
     * 获取延期任务历史记录
     *
     * @param userId
     * @param subtaskId
     * @return
     */
    @GET("/m/rmt_m/RMT_WORK_DELAY_RECORD_M/listQuery.json")
    Call<ResultData<RmtDelayTaskHistoryList>> getDelayTaskHistoryList(@Query("userId") long userId, @Query("subtaskId") long subtaskId);


    /**
     * 获取主修人、审批人
     *
     * @param subtaskId
     * @return
     */
    @GET("/m/rmt_m/RMT_WORK_DELAY_RECORD_M/getUserIdsOfPower.json")
    Call<ResultData<DelayUser>> getDelayUser(@Query("subtaskId") long subtaskId);

    public class DelayUser {

        /**
         * apply_personids : null
         * audit_personids : [100,1000000000427,1000000000449,1000000000448]
         */

        private List<Long> apply_personids;
        private List<Long> audit_personids;

        public List<Long> getApply_personids() {
            return apply_personids;
        }

        public void setApply_personids(List<Long> apply_personids) {
            this.apply_personids = apply_personids;
        }

        public List<Long> getAudit_personids() {
            return audit_personids;
        }

        public void setAudit_personids(List<Long> audit_personids) {
            this.audit_personids = audit_personids;
        }
    }

    /**
     * 获取作业授权记录
     *
     * @param userId
     * @return
     */
    @GET("/m/rmt_m/RMT_WORK_GRANT_M/listQuery.json")
    Call<ResultData<RmtAuthorizeList>> getAuthorizeList(@Query("userid") long userId);


    /**
     * 获取作业授权记录详情
     *
     * @param userId
     * @return
     */
    @GET("/m/rmt_m/RMT_WORK_GRANT_M/grtRead.json")
    Call<ResultData<RmtAuthorizeListDetail>> getAuthorizeListDetail(@Query("editId") long editId, @Query("userid") long userId);



    /**
     * 获取作业被授权记录
     *
     * @param userId
     * @return
     */
    @GET("/m/rmt_m/RMT_WORK_GRANT_ITEM_M/listQuery.json")
    Call<ResultData<RmtBeAuthorizeList>> getBeAuthorizeList(@Query("userid") long userId);


    /**
     * 知识窗列表
     * @param insp
     * @return
     */
   @GET("/m/phd_m/PHD_KNOWLEDGEWIN_M/listQuery.json")
   Call<ResultData<ZscBean>> getZscList(@Query("queryParam") String insp);


    /**
     * 作业授权收回按钮
     *
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})//需要添加头
    @POST("m/rmt_m/RMT_WORK_GRANT_ITEM_M/recover.json")
    Call<ResultData<Object>> getRecover(@Body List<WorkAuthorizeRecover> body, @Query("userid") long subtaskId);



    /**
     * 获取作业授权被授权人
     *
     * @param userId
     * @return
     */
    @GET("/m/rmt_m/RMT_WORK_GRANT_ITEM_M/userListQuery.json")
    Call<ResultData<RmtAuthorizeBsqr>> getAuthorizebsqr(@Query("userid") long userId);



    /**
     * 作业授权保存按钮
     *
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})//需要添加头
    @POST("m/rmt_m/RMT_WORK_GRANT_M/grtSave.json")
    Call<ResultData<Object>> getbcButton(@Body RmtAuthorizeSureSave body, @Query("userid") long subtaskId);



    /**
     * 获取作业授权消息推送详细内容
     *
     * @param userId
     * @return
     */
    @GET("/m/rmt_m/RMT_WORK_GRANT_ITEM_M/grtRead.json")
    Call<ResultData<RmtAuthorizeTsDetailMessage>> getTsWorkAuthorizeMessage(@Query("editId") long editId, @Query("userid") long userId);


    /**
     * 获取作业授权消息推送接收
     *
     * @param userId
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})//需要添加头
    @POST("/m/rmt_m/RMT_WORK_GRANT_ITEM_M/receive.json")
    Call<ResultData<Object>> getTsWorkAuthorizeReceive(@Body RmtAuthorizeRefuseReceiveBean body,@Query("userid") long userId);

    /**
     * 人员指定清除
     *
     * @param subtaskid
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})//需要添加头
    @POST("/m/rmt_m/RMT_WORK_ASSIGN_M/assignClear.json")
    Call<ResultData<Object>> getWorkAssignRemove(@Body List<RmtWorkAssign> body, @Query("subtaskId") long subtaskid);



    /**
     * 获取作业授权消息推送拒绝
     *
     * @param userId
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})//需要添加头
    @POST("/m/rmt_m/RMT_WORK_GRANT_ITEM_M/refuse.json")
    Call<ResultData<Object>> getTsWorkAuthorizeRefuse(@Body RmtAuthorizeRefuseReceiveBean body,@Query("userid") long userId);





    /**
     * 消息推送到作业延期界面得到数据
     *
     * @param userId
     * @param delayId
     * @return
     */
    @GET("/m/rmt_m/RMT_WORK_DELAY_RECORD_M/getDelayRecordOfMsg.json")
    Call<ResultData<RmtDelayTaskMsg>> getDelayTask(@Query("userId") long userId, @Query("work_delay_id") long delayId);


    /**
     * 消息推送启停
     *
     * @param userId
     * @param work_startpause_id
     * @param stage
     * @return
     */
    @GET("/m/rmt_m/RMT_WORK_STARTPAUSE_RECORD_M/getStartPauseRecord.json")
    Call<ResultData<RmtPauseResetTask>> getPauseResetMsg(@Query("userId") long userId, @Query("work_startpause_id") long work_startpause_id, @Query("stage") String stage);


//    getStartPauseRecordOfMsg

    /**
     * 获取暂停任务、复工任务
     */
    @GET("/m/rmt_m/RMT_WORK_STARTPAUSE_RECORD_M/getStartPauseRecord.json")
    Call<ResultData<RmtPauseResetTask>> getPauseResetTask(@Query("userId") long userId, @Query("subtaskId") long subtaskId, @Query("stage") String stage);


    /**
     * 作业延期保存按钮
     *
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})//需要添加头
    @POST("m/rmt_m/RMT_WORK_DELAY_RECORD_M/delaySave.json")
    Call<ResultData<Object>> getSaveButton(@Body RmtDelayTaskHistoryList.MainvoBean.HeadVOBean.RmtWdrList body, @Query("subtaskId") String subtaskId);

    /**
     * 作业延期提交按钮
     *
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})//需要添加头
    @POST("m/rmt_m/RMT_WORK_DELAY_RECORD_M/delayCommit.json")
    Call<ResultData<Object>> getCommitButton(@Body RmtDelayTaskHistoryList.MainvoBean.HeadVOBean.RmtWdrList body, @Query("subtaskId") String subtaskId);

    /**
     * 作业延期同意按钮
     *
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})//需要添加头
    @POST("m/rmt_m/RMT_WORK_DELAY_RECORD_M/delayAgree.json")
    Call<ResultData<Object>> getAgreeButton(@Body RmtDelayTaskHistoryList.MainvoBean.HeadVOBean.RmtWdrList body);

    /**
     * 作业延期退回按钮
     *
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})//需要添加头
    @POST("m/rmt_m/RMT_WORK_DELAY_RECORD_M/delayBack.json")
    Call<ResultData<Object>> getBackButton(@Body RmtDelayTaskHistoryList.MainvoBean.HeadVOBean.RmtWdrList body);


    /**
     * 作业延期删除
     *
     * @param work_delay_id
     * @return
     */
    @GET("m/rmt_m/RMT_WORK_DELAY_RECORD_M/delayDel.json")
    Call<ResultData<Object>> getDelayDelButton(@Query("work_delay_id") long work_delay_id);

    /**
     * 作业暂停\恢复保存按钮
     *
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})//需要添加头
    @POST("m/rmt_m/RMT_WORK_STARTPAUSE_RECORD_M/startPauseSave.json")
    Call<ResultData<Object>> getPauseSaveButton(@Body RmtPauseResetTask.BodyvosBean.RMTWORKSTARTPAUSERECORDMBean body, @Query("subtaskId") String subtaskId, @Query("stage") String stage);


    /**
     * 作业暂停\恢复提交按钮
     *
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})//需要添加头
    @POST("m/rmt_m/RMT_WORK_STARTPAUSE_RECORD_M/startPauseCommit.json")
    Call<ResultData<Object>> getPauseCommitButton(@Body RmtPauseResetTask.BodyvosBean.RMTWORKSTARTPAUSERECORDMBean body, @Query("subtaskId") String subtaskId, @Query("stage") String stage);


    /**
     * 作业暂停\恢复同意按钮
     *
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})//需要添加头
    @POST("m/rmt_m/RMT_WORK_STARTPAUSE_RECORD_M/startPauseAgree.json")
    Call<ResultData<Object>> getPauseAgreeButton(@Body RmtPauseResetTask.BodyvosBean.RMTWORKSTARTPAUSERECORDMBean body, @Query("stage") String stage);

    /**
     * 作业暂停\恢复退回按钮
     *
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})//需要添加头
    @POST("m/rmt_m/RMT_WORK_STARTPAUSE_RECORD_M/startPauseBack.json")
    Call<ResultData<Object>> getPauseBackButton(@Body RmtPauseResetTask.BodyvosBean.RMTWORKSTARTPAUSERECORDMBean body, @Query("stage") String stage);


    /**
     * 任务结束获取服务器时间和审批人编码
     *
     * @param userId
     * @return
     */
    @GET("m/rmt_m/RMT_WORK_SUBTASK_M/beforeClose_noValBtn.json")
    Call<ResultData<RmtWorkEnd>> getWorkEnd(@Query("userId") long userId);



    /**
     * 获取待关闭的主任务
     *
     * @param userId
     * @param taskId 主任务id
     * @param stage
     * @return
     */
    @GET("m/rmt_m/RMT_WORK_TASK_M/getTask.json")
    Call<ResultData<List<RmtWorkSubtaskResltData>>> getTask(@Query("userId") long userId, @Query("taskId") long taskId, @Query("stage") String stage);

    /**
     * 获取作业票详情
     *
     * @param userId    用户id
     * @param subtaskId 分项任务id
     * @return 分项任务
     */
    @GET("m/rmt_m/RMT_WORK_PERMIT_M/getPermit.json")
    Call<ResultData<List<RmtWorkSubtaskResltData>>> getPermit(@Query("userId") long userId, @Query("subtaskId") long subtaskId, @Query("permitId") long permitId, @Query("stage") String stage);


    /**
     * 提交措施,提交危害,提交承诺信息
     *
     * @param body
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})//需要添加头
    @POST("m/rmt_m/RMT_WORK_SUBTASK_M/approveByTab_noValBtn.json")
    Call<ResultData<Map<String, Long>>> approveByTab(@Body RmtConfMIntfc body, @Query("userId") long userId);


    /**
     * 领导
     * 提交措施,提交危害,提交承诺信息
     *
     * @param body
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})//需要添加头
    @POST("m/rmt_m/RMT_WORK_PERMIT_M/approveByTab.json")
    Call<ResultData<Map<String, Long>>> leaderApproveByTab(@Body RmtConfMIntfc body, @Query("userId") long userId);

    /**
     * 提交关闭信息
     *
     * @param body
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})//需要添加头
    @POST("m/rmt_m/RMT_WORK_SUBTASK_M/approveByTab_noValBtn.json")
    Call<ResultData<Object>> approveByTab(@Body List<RmtConfMIntfc> body, @Query("userId") long userId);

    /**
     * 提交接班信息
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})//需要添加头
    @POST("m/rmt_m/RMT_WORK_SUBTASK_M/handover_noValBtn.json")
    Call<ResultData<Object>> handover(@Body RmtConfMIntfc body, @Query("userId") long userId);

    /**
     * @return 气体检测
     */
    @GET("m/rmt_m/RMT_WORK_SUBTASK_M/getGasDetect_noValBtn.json")
    Call<ResultData<RmtWorkGasDetect>> getRmtWorkGas(@Query("subtaskId") long subtaskId);


    /**
     * @param body
     * @return 气体检测增加数据按钮
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})//需要添加头
    @POST("m/rmt_m/RMT_WORK_SUBTASK_M/saveGasDetect_noValBtn.json")
    Call<ResultData<Object>> savegas(@Body SaveGasRequestBody body);
//    JsonObject


    public class SaveGasRequestBody {
        public String detect_site;
        public Long work_subtask_id;
        public Integer qualified;
        public String detect_time;
        public List<CommonVO> voList;
        public Long analyzer_id;
        public String analyzer_name;
    }

    /**
     * @param userId
     * @param stage
     * @return 任务浏览组织树列表
     */
    @GET("m/rmt_m/RMT_WORK_TASK_M/bfListQuery.json")
    Call<ResultData<List<RmtTaskOrganizeListQuery>>> getTaskOrganizeListQuery(@Query("userId") long userId, @Query("stage") String stage);



    /**
     * @param userId
     * @param stage
     * @return 任务浏览列表
     */
    @GET("m/rmt_m/RMT_WORK_TASK_M/listQuery.json")
    Call<ResultData<RmtTaskListQuery>> getTaskListQuery(@Query("userId") long userId, @Query("stage") String stage, @Query("extWhere") String extWhere,@Query("territorial_unit_id")Long territorial_unit_id,@Query("page") int page,@Query("rows") int rows);

    /**
     * @param userId
     * @param subtaskId
     * @return 任务浏览的详细信息
     */
    @GET("m/rmt_m/RMT_WORK_SUBTASK_M/getSubtaskSchedule_noValBtn.json")
    Call<ResultData<TaskDetail>> getTaskDetail(@Query("userId") long userId, @Query("subtaskId") long subtaskId);

    /**
     * @param subtaskId
     * @param permitId
     * @return 单张票报表的接口
     */
    @GET("m/rmt_m/RMT_WORK_PERMIT_M/showReport.json")
    Call<ResultData<TaskUrl>> getTaskSchedule(@Query("work_subtask_id") long subtaskId, @Query("work_permit_id") long permitId);


    /**
     * @param userId
     * @return 首页报表的接口
     */
    @GET("allowAccess/m/rmt_m/RMT_SCENE_WORK_COUNT/showPrint.json")
    Call<ResultData<List<RmtMainPageSchedule>>> getMainPageSchedule(@Query("userId") long userId);


    /**
     * @param territorial_unit_id
     * @return 首页二级报表的接口
     */
    @GET("m/rmt_m/RMT_SCENE_WORK_COUNT_DETAIL_M/showReport.json")
    Call<ResultData<TaskUrl>> getNextPageSchedule(@Query("territorial_unit_id") long territorial_unit_id);





    /**
     * 问题清单提交接口
     *
     * @param rmtQstnList
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})//需要添加头
    @POST("m/rmt_m/RMT_WORK_QSTN_LIST_M/qstnSave.json?colcode=pic")
    Call<ResultData<Object>> submitQstnList(@Body RmtQstnList rmtQstnList);

    /**
     * 得到问题清单列表
     *
     * @return
     */
    @GET("m/rmt_m/RMT_WORK_QSTN_LIST_M/listQuery.json")
    Call<ResultData<RmtQstnListResltData>> getQstnList();

    /**
     * 问题清单详情
     *
     * @param qstnId
     * @return
     */
    @GET("m/rmt_m/RMT_WORK_QSTN_LIST_M/getQstn.json")
    Call<ResultData<RmtQstnList>> getQstn(@Query("qstnId") long qstnId);

    /**
     * 问题登记人员（现场负责人：owner,违章人员:violator）
     *
     * @param type
     * @param sutaskId
     * @return
     */
    @GET("m/rmt_m/RMT_WORK_QSTN_LIST_M/getUsers.json")
    Call<ResultData<List<RmtSysUser>>> getUsers(@Query("type") String type, @Query("subtaskId") long sutaskId);


    /**
     * 上传图片
     *
     * @param image
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})//需要添加头
    @POST("m/rmt_m/RMT_WORK_SUBTASK_M/uploadApprPic_noValBtn")
    Call<ResultData<Object>> uploadPic(@Body Image image);

    /**
     * 得到图片id集合，名字集合
     *
     * @param type (值为task 或者subtask)
     * @param id   值为主任务id或者 分项任务id
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})//需要添加头
    @GET("m/rmt_m/RMT_WORK_SUBTASK_M/getApprRecordPicList_noValBtn")
    Call<ResultData<List<ApprRecordMVO>>> getRecordPicList(@Query("type") String type, @Query("id") long id);

    /**
     * 返回图片Base64编码
     *
     * @param attachDataId
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})//需要添加头
    @GET("m/rmt_m/RMT_WORK_SUBTASK_M/getApprRecordPic_noValBtn")
    Call<ResultData<RmtImageDetail>> getRecordPic(@Query("attachDataId") long attachDataId);

    /**
     * 上传手签记录
     *
     * @param rmtSignRecord
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})//需要添加头
    @POST("m/rmt_m/RMT_WORK_SUBTASK_M/uploadSignPic_noValBtn")
    Call<ResultData<Object>> uploadSignPic(@Body RmtSignRecord rmtSignRecord);

    /**
     * 强制拍照照片
     * @param rmtSignRecord
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})//需要添加头
    @POST("m/rmt_m/RMT_WORK_APPR_RECORD/uploadAttPhoto_noValBtn")
    Call<ResultData<Object>> uploadQZPic(@Body RmtSignRecord rmtSignRecord);


    /**
     * 人员指定
     */
    @GET("m/rmt_m/RMT_WORK_ASSIGN_M/listQuery.json")
    Call<ResultData<RmtWorkAssignResultData>> getAssignList(@Query("taskId") long taskId, @Query("subtaskId") long sutaskId);


    /**
     * 送交按钮
     *
     * @param taskId
     * @param sutaskId
     * @return
     */
    @GET("m/rmt_m/RMT_WORK_ASSIGN_M/userListQuery.json")
    Call<ResultData<RmtSjResultData>> getSjList(@Query("taskId") long taskId, @Query("subtaskId") long sutaskId);


    /**
     * 送交提交
     *
     * @param subtaskId
     * @param
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})//需要添加头
    @POST("m/rmt_m/RMT_WORK_ASSIGN_M/subtaskMDeliver")
    Call<ResultData<Object>> sjpostOK(@Query("subtaskId") Long subtaskId, @Query("userId") long userId, @Query("personId") Long personId);


    /**
     * 温度压力界面作业区域接口
     *
     * @return
     */
    @GET("m/rmt_m/RMT_WORK_TASK_M/workSiteListQuery.json")
    Call<ResultData<WorkSite>> getworkSiteList(@Query("orgid") long orgid);


    /**
     * 温度压力界面设备类型接口
     *
     * @return
     */
    @GET("m/rmt_m/RMT_WORK_TASK_M/eqptTypeListQuery.json")
    Call<ResultData<EqptType>> geteqptTypeList();


    /**
     * 人员指定选择查询
     *
     * @param taskId
     * @param sutaskId
     * @param ids
     * @param fitemCode     assign
     * @param isGetMetaData 2
     * @return
     */
    @GET("m/rmt_m/RMT_WORK_ASSIGN_M/assign.json")
    Call<ResultData<List<RmtAssignUser>>> getWorkAssign(@Query("taskId") long taskId,
                                                        @Query("subtaskId") long sutaskId,
                                                        @Query("ids") String ids,
                                                        @Query("fitemCode") String fitemCode,
                                                        @Query("isGetMetaData") int isGetMetaData);


    /**
     * 得到安检公司、电仪部及其子部门
     *
     * @return
     */

    @Headers({"Content-Type: application/json", "Accept: application/json"})//需要添加头
    @GET("m/rmt_m/RMT_WORK_SUBTASK_M/getOrgsOfAjAndDyb_noValBtn.json")
    Call<ResultData<List<FcbsBean>>> getfcbs();




    /**
     * 人员指定提交
     *
     * @param ids  work_assign_id，多个以逗号分隔
     * @param body
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})//需要添加头
    @POST("m/rmt_m/RMT_WORK_ASSIGN_M/assignOK")
    Call<ResultData<Object>> assignOK(@Query("ids") String ids, @Body List<RmtAssignUser> body);


    /**
     * 上传手写签名
     *
     * @return
     */

    @Headers({"Content-Type: application/json", "Accept: application/json"})//需要添加头
    @POST("m/rmt_m/PUB_PERSON/uploadSign")
    Call<ResultData<Object>> upSign(@Body ImgStringVo body, @Query("userid") long userid);

    /**
     * 下载手写签名
     *
     * @return
     */

    @Headers({"Content-Type: application/json", "Accept: application/json"})//需要添加头
    @GET("m/rmt_m/PUB_PERSON/downLoadSign")
    Call<ResultData<ImgStringVo>> getSign(@Query("userid") long userid);

    /**
     * 获取交接班记录
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})//需要添加头
    @GET("m/rmt_m/RMT_WORK_ASSIGN_M/getAssignList.json")
    Call<ResultData<List<RmtAssignRecord>>> getAssignRecord(@Query("subtaskId") long subtaskId, @Query("userId") long userId);

    /**
     * @param ids    分项任务IDs
     * @param result 领导审批结果：agree/disagree
     * @param userId 当前登录人
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})//需要添加头
    @GET("m/rmt_m/RMT_WORK_SUBTASK_M/leaderAudit.json")
    Call<ResultData<Object>> viewAttachment(@Query("subtaskIds") String ids, @Query("result") String result, @Query("userId") long userId);

    /**
     * 设备员确认的按钮对应接口
     *
     * @param id
     * @param userId
     * @return
     */

    @Headers({"Content-Type: application/json", "Accept: application/json"})//需要添加头
    @GET("/m/rmt_m/RMT_WORK_SUBTASK_M/wfFinishAfterAssign.json")
    Call<ResultData<Object>> wfFinishAfterAssign(@Query("subtaskId") long id, @Query("personId") long userId);

    /**
     * 统计分析里得到属地单位的接口
     *
     * @return
     */
    @GET("m/rmt_m/RMT_WORK_TASK_M/getAllOrgs.json")
    Call<ResultData<List<StatisticsDept>>> getDept();

    /**
     * 领导审批是否配置强制拍照
     *
     * @return
     */
    @GET("m/rmt_m/RMT_WORK_SUBTASK_M/getLeaderAuditConfig_noValBtn.json")
    Call<ResultData<PZ>> getQzpz();

    public class PZ{
        public int force_photo;
    }


    /**
     * 统计分析今日作业的接口
     *
     * @return
     */
    @GET("m/rmt_m/RMT_COUNT_WORK_M/showReport.json")
    Call<ResultData<StatisticsTodayWorkUrl>> getDeptSchedule(@Query("queryParam") String param);


    /**
     * 统计分析作业类型统计的接口
     *
     * @param param
     * @return
     */
    @GET("m/rmt_m/RMT_WORK_PERMIT_COUNT_ORG_TYPE_M/showReport.json")
    Call<ResultData<StatisticsTodayWorkUrl>> getWorkerOrderTypeSchedule(@Query("queryParam") String param);


    /**
     * 统计分析作业月度数量统计的接口
     *
     * @param param
     * @return
     */
    @GET("m/rmt_m/RMT_PERMIT_COUNT_ORG_MONTH_M/showReport.json")
    Call<ResultData<StatisticsTodayWorkUrl>> getWorkerMonthNumSchedule(@Query("queryParam") String param);

    /**
     * 作业取消
     *
     * @return
     */
    @GET("m/rmt_m/RMT_WORK_SUBTASK_M/subtaskCancel.json")
    Call<ResultData<Object>> subtaskCancel(@Query("userId") long userId, @Query("subtaskId") long subtaskId, @Query("stopComment") String stopComment);


    /**
     * 获取条款分类接口
     *
     * @return
     */
    @GET("m/rmt_m/RMT_WORK_QSTN_LIST_M/getTermType.json")
    Call<ResultData<List<TermType>>> getTermType();

    /**
     * 获取条款内容列表接口
     *
     * @return
     */
    @GET("m/rmt_m/RMT_WORK_QSTN_LIST_M/getViolationterm.json")
    Call<ResultData<List<TermContent>>> getViolationterm(@Query("termType") String termType);

}
