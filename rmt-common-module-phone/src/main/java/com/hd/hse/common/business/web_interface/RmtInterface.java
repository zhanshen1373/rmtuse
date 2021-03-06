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
     * @param userId ?????????
     * @param stage  ????????????
     * @return ??????????????????
     */
    @GET("m/rmt_m/RMT_WORK_SUBTASK_M/listQuery.json")
    Call<ResultData<ResultDataBean<RmtWorkSubtask>>> getRmtWorkSubtaskList(@Query("userId") long userId, @Query("stage") String stage, @Query("extWhere") String extWhere,@Query("page") int page,@Query("rows") int rows);


    /**
     * ?????????????????????
     *
     * @param userId    ??????id
     * @param subtaskId ????????????id
     * @return ????????????
     */
    @GET("m/rmt_m/RMT_WORK_SUBTASK_M/getSubtask_noValBtn.json")
    Call<ResultData<List<RmtWorkSubtaskResltData>>> getRmtWorkSubtask(@Query("userId") long userId, @Query("subtaskId") long subtaskId, @Query("stage") String stage);


    /**
     * ??????????????????????????????
     *
     * @param userId
     * @param subtaskId
     * @return
     */
    @GET("/m/rmt_m/RMT_WORK_DELAY_RECORD_M/listQuery.json")
    Call<ResultData<RmtDelayTaskHistoryList>> getDelayTaskHistoryList(@Query("userId") long userId, @Query("subtaskId") long subtaskId);


    /**
     * ???????????????????????????
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
     * ????????????????????????
     *
     * @param userId
     * @return
     */
    @GET("/m/rmt_m/RMT_WORK_GRANT_M/listQuery.json")
    Call<ResultData<RmtAuthorizeList>> getAuthorizeList(@Query("userid") long userId);


    /**
     * ??????????????????????????????
     *
     * @param userId
     * @return
     */
    @GET("/m/rmt_m/RMT_WORK_GRANT_M/grtRead.json")
    Call<ResultData<RmtAuthorizeListDetail>> getAuthorizeListDetail(@Query("editId") long editId, @Query("userid") long userId);



    /**
     * ???????????????????????????
     *
     * @param userId
     * @return
     */
    @GET("/m/rmt_m/RMT_WORK_GRANT_ITEM_M/listQuery.json")
    Call<ResultData<RmtBeAuthorizeList>> getBeAuthorizeList(@Query("userid") long userId);


    /**
     * ???????????????
     * @param insp
     * @return
     */
   @GET("/m/phd_m/PHD_KNOWLEDGEWIN_M/listQuery.json")
   Call<ResultData<ZscBean>> getZscList(@Query("queryParam") String insp);


    /**
     * ????????????????????????
     *
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})//???????????????
    @POST("m/rmt_m/RMT_WORK_GRANT_ITEM_M/recover.json")
    Call<ResultData<Object>> getRecover(@Body List<WorkAuthorizeRecover> body, @Query("userid") long subtaskId);



    /**
     * ??????????????????????????????
     *
     * @param userId
     * @return
     */
    @GET("/m/rmt_m/RMT_WORK_GRANT_ITEM_M/userListQuery.json")
    Call<ResultData<RmtAuthorizeBsqr>> getAuthorizebsqr(@Query("userid") long userId);



    /**
     * ????????????????????????
     *
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})//???????????????
    @POST("m/rmt_m/RMT_WORK_GRANT_M/grtSave.json")
    Call<ResultData<Object>> getbcButton(@Body RmtAuthorizeSureSave body, @Query("userid") long subtaskId);



    /**
     * ??????????????????????????????????????????
     *
     * @param userId
     * @return
     */
    @GET("/m/rmt_m/RMT_WORK_GRANT_ITEM_M/grtRead.json")
    Call<ResultData<RmtAuthorizeTsDetailMessage>> getTsWorkAuthorizeMessage(@Query("editId") long editId, @Query("userid") long userId);


    /**
     * ????????????????????????????????????
     *
     * @param userId
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})//???????????????
    @POST("/m/rmt_m/RMT_WORK_GRANT_ITEM_M/receive.json")
    Call<ResultData<Object>> getTsWorkAuthorizeReceive(@Body RmtAuthorizeRefuseReceiveBean body,@Query("userid") long userId);

    /**
     * ??????????????????
     *
     * @param subtaskid
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})//???????????????
    @POST("/m/rmt_m/RMT_WORK_ASSIGN_M/assignClear.json")
    Call<ResultData<Object>> getWorkAssignRemove(@Body List<RmtWorkAssign> body, @Query("subtaskId") long subtaskid);



    /**
     * ????????????????????????????????????
     *
     * @param userId
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})//???????????????
    @POST("/m/rmt_m/RMT_WORK_GRANT_ITEM_M/refuse.json")
    Call<ResultData<Object>> getTsWorkAuthorizeRefuse(@Body RmtAuthorizeRefuseReceiveBean body,@Query("userid") long userId);





    /**
     * ?????????????????????????????????????????????
     *
     * @param userId
     * @param delayId
     * @return
     */
    @GET("/m/rmt_m/RMT_WORK_DELAY_RECORD_M/getDelayRecordOfMsg.json")
    Call<ResultData<RmtDelayTaskMsg>> getDelayTask(@Query("userId") long userId, @Query("work_delay_id") long delayId);


    /**
     * ??????????????????
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
     * ?????????????????????????????????
     */
    @GET("/m/rmt_m/RMT_WORK_STARTPAUSE_RECORD_M/getStartPauseRecord.json")
    Call<ResultData<RmtPauseResetTask>> getPauseResetTask(@Query("userId") long userId, @Query("subtaskId") long subtaskId, @Query("stage") String stage);


    /**
     * ????????????????????????
     *
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})//???????????????
    @POST("m/rmt_m/RMT_WORK_DELAY_RECORD_M/delaySave.json")
    Call<ResultData<Object>> getSaveButton(@Body RmtDelayTaskHistoryList.MainvoBean.HeadVOBean.RmtWdrList body, @Query("subtaskId") String subtaskId);

    /**
     * ????????????????????????
     *
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})//???????????????
    @POST("m/rmt_m/RMT_WORK_DELAY_RECORD_M/delayCommit.json")
    Call<ResultData<Object>> getCommitButton(@Body RmtDelayTaskHistoryList.MainvoBean.HeadVOBean.RmtWdrList body, @Query("subtaskId") String subtaskId);

    /**
     * ????????????????????????
     *
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})//???????????????
    @POST("m/rmt_m/RMT_WORK_DELAY_RECORD_M/delayAgree.json")
    Call<ResultData<Object>> getAgreeButton(@Body RmtDelayTaskHistoryList.MainvoBean.HeadVOBean.RmtWdrList body);

    /**
     * ????????????????????????
     *
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})//???????????????
    @POST("m/rmt_m/RMT_WORK_DELAY_RECORD_M/delayBack.json")
    Call<ResultData<Object>> getBackButton(@Body RmtDelayTaskHistoryList.MainvoBean.HeadVOBean.RmtWdrList body);


    /**
     * ??????????????????
     *
     * @param work_delay_id
     * @return
     */
    @GET("m/rmt_m/RMT_WORK_DELAY_RECORD_M/delayDel.json")
    Call<ResultData<Object>> getDelayDelButton(@Query("work_delay_id") long work_delay_id);

    /**
     * ????????????\??????????????????
     *
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})//???????????????
    @POST("m/rmt_m/RMT_WORK_STARTPAUSE_RECORD_M/startPauseSave.json")
    Call<ResultData<Object>> getPauseSaveButton(@Body RmtPauseResetTask.BodyvosBean.RMTWORKSTARTPAUSERECORDMBean body, @Query("subtaskId") String subtaskId, @Query("stage") String stage);


    /**
     * ????????????\??????????????????
     *
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})//???????????????
    @POST("m/rmt_m/RMT_WORK_STARTPAUSE_RECORD_M/startPauseCommit.json")
    Call<ResultData<Object>> getPauseCommitButton(@Body RmtPauseResetTask.BodyvosBean.RMTWORKSTARTPAUSERECORDMBean body, @Query("subtaskId") String subtaskId, @Query("stage") String stage);


    /**
     * ????????????\??????????????????
     *
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})//???????????????
    @POST("m/rmt_m/RMT_WORK_STARTPAUSE_RECORD_M/startPauseAgree.json")
    Call<ResultData<Object>> getPauseAgreeButton(@Body RmtPauseResetTask.BodyvosBean.RMTWORKSTARTPAUSERECORDMBean body, @Query("stage") String stage);

    /**
     * ????????????\??????????????????
     *
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})//???????????????
    @POST("m/rmt_m/RMT_WORK_STARTPAUSE_RECORD_M/startPauseBack.json")
    Call<ResultData<Object>> getPauseBackButton(@Body RmtPauseResetTask.BodyvosBean.RMTWORKSTARTPAUSERECORDMBean body, @Query("stage") String stage);


    /**
     * ???????????????????????????????????????????????????
     *
     * @param userId
     * @return
     */
    @GET("m/rmt_m/RMT_WORK_SUBTASK_M/beforeClose_noValBtn.json")
    Call<ResultData<RmtWorkEnd>> getWorkEnd(@Query("userId") long userId);



    /**
     * ???????????????????????????
     *
     * @param userId
     * @param taskId ?????????id
     * @param stage
     * @return
     */
    @GET("m/rmt_m/RMT_WORK_TASK_M/getTask.json")
    Call<ResultData<List<RmtWorkSubtaskResltData>>> getTask(@Query("userId") long userId, @Query("taskId") long taskId, @Query("stage") String stage);

    /**
     * ?????????????????????
     *
     * @param userId    ??????id
     * @param subtaskId ????????????id
     * @return ????????????
     */
    @GET("m/rmt_m/RMT_WORK_PERMIT_M/getPermit.json")
    Call<ResultData<List<RmtWorkSubtaskResltData>>> getPermit(@Query("userId") long userId, @Query("subtaskId") long subtaskId, @Query("permitId") long permitId, @Query("stage") String stage);


    /**
     * ????????????,????????????,??????????????????
     *
     * @param body
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})//???????????????
    @POST("m/rmt_m/RMT_WORK_SUBTASK_M/approveByTab_noValBtn.json")
    Call<ResultData<Map<String, Long>>> approveByTab(@Body RmtConfMIntfc body, @Query("userId") long userId);


    /**
     * ??????
     * ????????????,????????????,??????????????????
     *
     * @param body
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})//???????????????
    @POST("m/rmt_m/RMT_WORK_PERMIT_M/approveByTab.json")
    Call<ResultData<Map<String, Long>>> leaderApproveByTab(@Body RmtConfMIntfc body, @Query("userId") long userId);

    /**
     * ??????????????????
     *
     * @param body
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})//???????????????
    @POST("m/rmt_m/RMT_WORK_SUBTASK_M/approveByTab_noValBtn.json")
    Call<ResultData<Object>> approveByTab(@Body List<RmtConfMIntfc> body, @Query("userId") long userId);

    /**
     * ??????????????????
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})//???????????????
    @POST("m/rmt_m/RMT_WORK_SUBTASK_M/handover_noValBtn.json")
    Call<ResultData<Object>> handover(@Body RmtConfMIntfc body, @Query("userId") long userId);

    /**
     * @return ????????????
     */
    @GET("m/rmt_m/RMT_WORK_SUBTASK_M/getGasDetect_noValBtn.json")
    Call<ResultData<RmtWorkGasDetect>> getRmtWorkGas(@Query("subtaskId") long subtaskId);


    /**
     * @param body
     * @return ??????????????????????????????
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})//???????????????
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
     * @return ???????????????????????????
     */
    @GET("m/rmt_m/RMT_WORK_TASK_M/bfListQuery.json")
    Call<ResultData<List<RmtTaskOrganizeListQuery>>> getTaskOrganizeListQuery(@Query("userId") long userId, @Query("stage") String stage);



    /**
     * @param userId
     * @param stage
     * @return ??????????????????
     */
    @GET("m/rmt_m/RMT_WORK_TASK_M/listQuery.json")
    Call<ResultData<RmtTaskListQuery>> getTaskListQuery(@Query("userId") long userId, @Query("stage") String stage, @Query("extWhere") String extWhere,@Query("territorial_unit_id")Long territorial_unit_id,@Query("page") int page,@Query("rows") int rows);

    /**
     * @param userId
     * @param subtaskId
     * @return ???????????????????????????
     */
    @GET("m/rmt_m/RMT_WORK_SUBTASK_M/getSubtaskSchedule_noValBtn.json")
    Call<ResultData<TaskDetail>> getTaskDetail(@Query("userId") long userId, @Query("subtaskId") long subtaskId);

    /**
     * @param subtaskId
     * @param permitId
     * @return ????????????????????????
     */
    @GET("m/rmt_m/RMT_WORK_PERMIT_M/showReport.json")
    Call<ResultData<TaskUrl>> getTaskSchedule(@Query("work_subtask_id") long subtaskId, @Query("work_permit_id") long permitId);


    /**
     * @param userId
     * @return ?????????????????????
     */
    @GET("allowAccess/m/rmt_m/RMT_SCENE_WORK_COUNT/showPrint.json")
    Call<ResultData<List<RmtMainPageSchedule>>> getMainPageSchedule(@Query("userId") long userId);


    /**
     * @param territorial_unit_id
     * @return ???????????????????????????
     */
    @GET("m/rmt_m/RMT_SCENE_WORK_COUNT_DETAIL_M/showReport.json")
    Call<ResultData<TaskUrl>> getNextPageSchedule(@Query("territorial_unit_id") long territorial_unit_id);





    /**
     * ????????????????????????
     *
     * @param rmtQstnList
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})//???????????????
    @POST("m/rmt_m/RMT_WORK_QSTN_LIST_M/qstnSave.json?colcode=pic")
    Call<ResultData<Object>> submitQstnList(@Body RmtQstnList rmtQstnList);

    /**
     * ????????????????????????
     *
     * @return
     */
    @GET("m/rmt_m/RMT_WORK_QSTN_LIST_M/listQuery.json")
    Call<ResultData<RmtQstnListResltData>> getQstnList();

    /**
     * ??????????????????
     *
     * @param qstnId
     * @return
     */
    @GET("m/rmt_m/RMT_WORK_QSTN_LIST_M/getQstn.json")
    Call<ResultData<RmtQstnList>> getQstn(@Query("qstnId") long qstnId);

    /**
     * ???????????????????????????????????????owner,????????????:violator???
     *
     * @param type
     * @param sutaskId
     * @return
     */
    @GET("m/rmt_m/RMT_WORK_QSTN_LIST_M/getUsers.json")
    Call<ResultData<List<RmtSysUser>>> getUsers(@Query("type") String type, @Query("subtaskId") long sutaskId);


    /**
     * ????????????
     *
     * @param image
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})//???????????????
    @POST("m/rmt_m/RMT_WORK_SUBTASK_M/uploadApprPic_noValBtn")
    Call<ResultData<Object>> uploadPic(@Body Image image);

    /**
     * ????????????id?????????????????????
     *
     * @param type (??????task ??????subtask)
     * @param id   ???????????????id?????? ????????????id
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})//???????????????
    @GET("m/rmt_m/RMT_WORK_SUBTASK_M/getApprRecordPicList_noValBtn")
    Call<ResultData<List<ApprRecordMVO>>> getRecordPicList(@Query("type") String type, @Query("id") long id);

    /**
     * ????????????Base64??????
     *
     * @param attachDataId
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})//???????????????
    @GET("m/rmt_m/RMT_WORK_SUBTASK_M/getApprRecordPic_noValBtn")
    Call<ResultData<RmtImageDetail>> getRecordPic(@Query("attachDataId") long attachDataId);

    /**
     * ??????????????????
     *
     * @param rmtSignRecord
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})//???????????????
    @POST("m/rmt_m/RMT_WORK_SUBTASK_M/uploadSignPic_noValBtn")
    Call<ResultData<Object>> uploadSignPic(@Body RmtSignRecord rmtSignRecord);

    /**
     * ??????????????????
     * @param rmtSignRecord
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})//???????????????
    @POST("m/rmt_m/RMT_WORK_APPR_RECORD/uploadAttPhoto_noValBtn")
    Call<ResultData<Object>> uploadQZPic(@Body RmtSignRecord rmtSignRecord);


    /**
     * ????????????
     */
    @GET("m/rmt_m/RMT_WORK_ASSIGN_M/listQuery.json")
    Call<ResultData<RmtWorkAssignResultData>> getAssignList(@Query("taskId") long taskId, @Query("subtaskId") long sutaskId);


    /**
     * ????????????
     *
     * @param taskId
     * @param sutaskId
     * @return
     */
    @GET("m/rmt_m/RMT_WORK_ASSIGN_M/userListQuery.json")
    Call<ResultData<RmtSjResultData>> getSjList(@Query("taskId") long taskId, @Query("subtaskId") long sutaskId);


    /**
     * ????????????
     *
     * @param subtaskId
     * @param
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})//???????????????
    @POST("m/rmt_m/RMT_WORK_ASSIGN_M/subtaskMDeliver")
    Call<ResultData<Object>> sjpostOK(@Query("subtaskId") Long subtaskId, @Query("userId") long userId, @Query("personId") Long personId);


    /**
     * ????????????????????????????????????
     *
     * @return
     */
    @GET("m/rmt_m/RMT_WORK_TASK_M/workSiteListQuery.json")
    Call<ResultData<WorkSite>> getworkSiteList(@Query("orgid") long orgid);


    /**
     * ????????????????????????????????????
     *
     * @return
     */
    @GET("m/rmt_m/RMT_WORK_TASK_M/eqptTypeListQuery.json")
    Call<ResultData<EqptType>> geteqptTypeList();


    /**
     * ????????????????????????
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
     * ?????????????????????????????????????????????
     *
     * @return
     */

    @Headers({"Content-Type: application/json", "Accept: application/json"})//???????????????
    @GET("m/rmt_m/RMT_WORK_SUBTASK_M/getOrgsOfAjAndDyb_noValBtn.json")
    Call<ResultData<List<FcbsBean>>> getfcbs();




    /**
     * ??????????????????
     *
     * @param ids  work_assign_id????????????????????????
     * @param body
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})//???????????????
    @POST("m/rmt_m/RMT_WORK_ASSIGN_M/assignOK")
    Call<ResultData<Object>> assignOK(@Query("ids") String ids, @Body List<RmtAssignUser> body);


    /**
     * ??????????????????
     *
     * @return
     */

    @Headers({"Content-Type: application/json", "Accept: application/json"})//???????????????
    @POST("m/rmt_m/PUB_PERSON/uploadSign")
    Call<ResultData<Object>> upSign(@Body ImgStringVo body, @Query("userid") long userid);

    /**
     * ??????????????????
     *
     * @return
     */

    @Headers({"Content-Type: application/json", "Accept: application/json"})//???????????????
    @GET("m/rmt_m/PUB_PERSON/downLoadSign")
    Call<ResultData<ImgStringVo>> getSign(@Query("userid") long userid);

    /**
     * ?????????????????????
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})//???????????????
    @GET("m/rmt_m/RMT_WORK_ASSIGN_M/getAssignList.json")
    Call<ResultData<List<RmtAssignRecord>>> getAssignRecord(@Query("subtaskId") long subtaskId, @Query("userId") long userId);

    /**
     * @param ids    ????????????IDs
     * @param result ?????????????????????agree/disagree
     * @param userId ???????????????
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})//???????????????
    @GET("m/rmt_m/RMT_WORK_SUBTASK_M/leaderAudit.json")
    Call<ResultData<Object>> viewAttachment(@Query("subtaskIds") String ids, @Query("result") String result, @Query("userId") long userId);

    /**
     * ????????????????????????????????????
     *
     * @param id
     * @param userId
     * @return
     */

    @Headers({"Content-Type: application/json", "Accept: application/json"})//???????????????
    @GET("/m/rmt_m/RMT_WORK_SUBTASK_M/wfFinishAfterAssign.json")
    Call<ResultData<Object>> wfFinishAfterAssign(@Query("subtaskId") long id, @Query("personId") long userId);

    /**
     * ??????????????????????????????????????????
     *
     * @return
     */
    @GET("m/rmt_m/RMT_WORK_TASK_M/getAllOrgs.json")
    Call<ResultData<List<StatisticsDept>>> getDept();

    /**
     * ????????????????????????????????????
     *
     * @return
     */
    @GET("m/rmt_m/RMT_WORK_SUBTASK_M/getLeaderAuditConfig_noValBtn.json")
    Call<ResultData<PZ>> getQzpz();

    public class PZ{
        public int force_photo;
    }


    /**
     * ?????????????????????????????????
     *
     * @return
     */
    @GET("m/rmt_m/RMT_COUNT_WORK_M/showReport.json")
    Call<ResultData<StatisticsTodayWorkUrl>> getDeptSchedule(@Query("queryParam") String param);


    /**
     * ???????????????????????????????????????
     *
     * @param param
     * @return
     */
    @GET("m/rmt_m/RMT_WORK_PERMIT_COUNT_ORG_TYPE_M/showReport.json")
    Call<ResultData<StatisticsTodayWorkUrl>> getWorkerOrderTypeSchedule(@Query("queryParam") String param);


    /**
     * ?????????????????????????????????????????????
     *
     * @param param
     * @return
     */
    @GET("m/rmt_m/RMT_PERMIT_COUNT_ORG_MONTH_M/showReport.json")
    Call<ResultData<StatisticsTodayWorkUrl>> getWorkerMonthNumSchedule(@Query("queryParam") String param);

    /**
     * ????????????
     *
     * @return
     */
    @GET("m/rmt_m/RMT_WORK_SUBTASK_M/subtaskCancel.json")
    Call<ResultData<Object>> subtaskCancel(@Query("userId") long userId, @Query("subtaskId") long subtaskId, @Query("stopComment") String stopComment);


    /**
     * ????????????????????????
     *
     * @return
     */
    @GET("m/rmt_m/RMT_WORK_QSTN_LIST_M/getTermType.json")
    Call<ResultData<List<TermType>>> getTermType();

    /**
     * ??????????????????????????????
     *
     * @return
     */
    @GET("m/rmt_m/RMT_WORK_QSTN_LIST_M/getViolationterm.json")
    Call<ResultData<List<TermContent>>> getViolationterm(@Query("termType") String termType);

}
