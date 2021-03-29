package com.hd.hse.main.phone.ui.activity.main;

import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hayden.hap.common.login.business.LoginUserRecord;
import com.hd.hse.common.component.phone.event.IEventType;
import com.hd.hse.common.component.phone.listener.IEventListener;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.module.phone.custom.NoticeListView;
import com.hd.hse.entity.common.ReMindBean;
import com.hd.hse.entity.workorder.RmtWorkSubtask;
import com.hd.hse.main.phone.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static android.content.Context.NOTIFICATION_SERVICE;

public class MessageFragment extends Fragment {

    private NoticeListView taskListView;
    private ProgressDialog prsDlg;
    private String click_title;
    private String click_body;
    private int click_position = -1;
    private static HashMap<Long, List<ReMindBean.ContentBean>> hm = new HashMap<>();
    private static HashMap<String, List<String>> hmtitle = new HashMap<>();
    private static HashMap<String, List<String>> hmbody = new HashMap<>();
    private ReMindBean.ContentBean reContBean = null;
    private String click_work_subtask_id = null;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.hd_hse_main_phone_app_layout_main_mfg, null);
        taskListView = (NoticeListView) inflate.findViewById(R.id.hd_hse_main_tasklist);
        taskListView.setiEventLsn(iEventLsn);


        taskListView.setonRefreshListener(new NoticeListView.OnRefreshListener() {
            @Override
            public void onRefresh() {

                List<ReMindBean.ContentBean> rmdlist = hm.get(LoginUserRecord.getInstance().getUser().getUserid());
                taskListView.onRefreshStart();
                ((MainActivity)getActivity()).initNoticList(rmdlist, hmbody.get("body"), hmtitle.get("title"), false);
                taskListView.onRefreshComplete("刷新成功!");
            }
        });

        return inflate;
    }

    /**
     * iEventLsn:TODO(点击操作类消息执行的事件).
     */
    private IEventListener iEventLsn = new IEventListener() {

        @Override
        public void eventProcess(int eventType, Object... objects)
                throws HDException {
            if (eventType == IEventType.NOTICE_LIST_CLICK && objects[0] != null) {
                // WorkTask workTask = null;
                if (prsDlg == null) {
                    prsDlg = new ProgressDialog(getActivity());
                    prsDlg.setMessage("数据加载中");
                }
                prsDlg.show();
                if (objects[0] instanceof ReMindBean.ContentBean) {
                    reContBean = (ReMindBean.ContentBean) objects[0];
                    ((MainActivity)getActivity()).setMessageJump1(reContBean);
                    if (objects[1] instanceof String) {
                        click_work_subtask_id = (String) objects[1];
                    }
                    ((MainActivity)getActivity()).setMessageJump3(click_work_subtask_id);
//                    if (objects[4] instanceof Integer) {
//                        click_position = (Integer) objects[4];
//                    }
                    //移除通知栏消息
                    NotificationManager nm = (NotificationManager) getActivity().getSystemService(NOTIFICATION_SERVICE);
                    nm.cancelAll();
                    ((MainActivity)getActivity()).toNextPage(new RmtWorkSubtask());
                }
                prsDlg.dismiss();
            }
            if (eventType == IEventType.NOTICE_TIMING_CLICK) {
                if (objects[0] == null) {
                    click_title = "日常检维修";
                }
                if (objects[1] instanceof String) {
                    click_body = (String) objects[1];
                }
                if (objects[2] instanceof Integer) {
                    click_position = (Integer) objects[2];
                }

                ((MainActivity)getActivity()).setMessageJump2(click_title,click_body,click_position);
                //移除通知栏消息
                NotificationManager nm = (NotificationManager) (getActivity()).getSystemService(NOTIFICATION_SERVICE);
                nm.cancelAll();
                ((MainActivity)getActivity()).toNextPage(new RmtWorkSubtask());
            }
            if (eventType == IEventType.ACTION_CLEAN_CHOSE_MESSAGE) {
                if (objects[0] instanceof Integer) {

                    ((MainActivity)getActivity()).changeView(objects[0]+"");
//                    if (("0").equals(msgCountTV.getText().toString())) {
//                        all_chose_message.setText("全选消息");
//                    }
                    //删除之后的消息，再次使用要变回原来顺序
                    List<ReMindBean.ContentBean> returnReverseList = (List<ReMindBean.ContentBean>) objects[1];
                    List<String> returnReversebodyList = (List<String>) objects[2];
                    List<String> returnReversetitleList = (List<String>) objects[3];

                    List<ReMindBean.ContentBean> ylList = new ArrayList<>();
                    List<String> ylbodyList = new ArrayList<>();
                    List<String> yltitleList = new ArrayList<>();
                    if (returnReverseList != null && returnReverseList.size() > 0) {
                        ylList.addAll(returnReverseList);
                        ylbodyList.addAll(returnReversebodyList);
                        yltitleList.addAll(returnReversetitleList);
                    }

                    if (ylList != null && ylList.size() > 0) {

                        Collections.reverse(ylList);
                        Collections.reverse(ylbodyList);
                        Collections.reverse(yltitleList);

                    }

                    //正常顺序的
                    hm.put(LoginUserRecord.getInstance().getUser().getUserid(), ylList);
                    hmbody.put("body", ylbodyList);
                    hmtitle.put("title", yltitleList);
//                    taskListView.isAllChose(false);
                }
            }
//            if (eventType == IEventType.BUTTON_CONTENTE) {
//                if (objects[0] instanceof String) {
//                    all_chose_message.setText((CharSequence) objects[0]);
//                }
//            }
        }
    };

    public void setCheckBox(boolean isChecked) {

        if (isChecked) {
            taskListView.isAllChose(true);
        } else {
            taskListView.isClean(true);
        }
    }

    public void setDatas(ArrayList<Boolean> checkList,ArrayList<ReMindBean.ContentBean> reverse, ArrayList<String> reversebodyList, ArrayList<String> reversetitleList) {
        taskListView.setDatas(checkList,reverse, reversebodyList, reversetitleList);
    }

    public void setListViewStatus(int i) {
        switch (i){
            case 0:
                taskListView.onRefreshStart();
                break;
            case 1:
                taskListView.onRefreshComplete("刷新成功!");
                break;
        }
    }

    public void setList(HashMap<Long, List<ReMindBean.ContentBean>> hm, HashMap<String, List<String>> hmtitle, HashMap<String, List<String>> hmbody) {
        this.hm=hm;
        this.hmtitle=hmtitle;
        this.hmbody=hmbody;
    }
}
