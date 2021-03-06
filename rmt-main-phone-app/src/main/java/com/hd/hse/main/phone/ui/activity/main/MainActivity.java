package com.hd.hse.main.phone.ui.activity.main;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.hayden.hap.common.base.BaseApplication;
import com.hayden.hap.common.common.bussiess.HttpBusiness;
import com.hayden.hap.common.common.bussiess.HttpCallBack;
import com.hayden.hap.common.common.bussiess.ResultData;
import com.hayden.hap.common.login.business.AppVersion;
import com.hayden.hap.common.login.business.LoginUserRecord;
import com.hayden.hap.common.utils.RetrofitUtil;
import com.hayden.hap.common.utils.ToastUtil;
import com.hayden.hap.common.version.VersionUp;
import com.hayden.hap.hd_push.sdk.HDPush;
import com.hayden.hap.hd_push.sdk.PushCallback;
import com.hayden.hap.hd_push.sdk.PushOption;
import com.hayden.hap.plugin.android.qr_code.QrCode;
import com.hd.hse.business.action.BusinessAction;
import com.hd.hse.common.business.web_interface.RmtInterface;
import com.hd.hse.common.component.phone.constant.IActionBar;
import com.hd.hse.common.component.phone.event.IEventType;
import com.hd.hse.common.component.phone.listener.IEventListener;
import com.hd.hse.common.component.phone.util.ToastUtils;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.logger.LogUtils;
import com.hd.hse.common.module.branch.ui.model.activity.FrameworkActivity;
import com.hd.hse.common.module.phone.camera.CameraCaptureActivity;
import com.hd.hse.common.module.phone.ui.activity.BaseFrameActivity;
import com.hd.hse.common.module.phone.ui.activity.LocationSwCard;
import com.hd.hse.common.module.phone.ui.activity.SystemApplication;
import com.hd.hse.constant.IActionType;
import com.hd.hse.entity.assign.FcbsBean;
import com.hd.hse.entity.common.NoTiceBean;
import com.hd.hse.entity.common.PersonCard;
import com.hd.hse.entity.common.ReMindBean;
import com.hd.hse.entity.sys.AppModule;
import com.hd.hse.entity.workorder.RmtJjbData;
import com.hd.hse.entity.workorder.RmtWorkSubtask;
import com.hd.hse.main.business.main.MainActionListener;
import com.hd.hse.main.phone.R;
import com.hd.hse.main.phone.ui.activity.login.LoginActivity;
import com.hd.hse.main.phone.ui.receiver.UdpClient;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;

import static com.hd.hse.system.SystemProperty.getSystemProperty;


public class MainActivity extends BaseFrameActivity {
    /**
     * logger:TODO(????????????).
     */
    private static Logger logger = LogUtils.getLogger(MainActivity.class);

    /**
     * listAppModule:TODO(????????????????????????).
     */
    private List<AppModule> listAppModule;

    /**
     * actionBarMainMenu:TODO().
     */
    private ActionBarMainMenu actionBarMainMenu;
    /**
     * metrics:TODO(???????????????).
     */
    private DisplayMetrics metrics;

    /**
     * ??????????????????????????????
     */
    private TextView nameTV;
    /**
     * ????????????
     */
//    private NoticeListView taskListView;
    private TextView msgCountTV;
    private View msgView;
    private TextView timeTV;
    private TextView weekTV;
    private Button clean_chose_message;
    private Button all_chose_message;

    /**
     * ???????????????msg
     */
    private ReMindBean.ContentBean reContBean = null;
    /**
     * notice?????????????????????
     */
    private String gnbm = null;
    /**
     * notice?????????work_subtask_id
     */
    private String notice_work_subtask_id = null;
    /**
     * notice?????????description
     */
    private String notice_work_subtask_description = null;
    /**
     * ??????tasklistview??????????????????id
     */
    private String click_work_subtask_id = null;
    /**
     * prsDlg:TODO(?????????).
     */
    private ProgressDialog prsDlg;

    /**
     * notice?????????title
     */
    private String messageTitle;
    /**
     * startSrv:TODO(??????).
     */
    private Intent startSrv;
    private static final String TAG = MainActivity.class.getName();
    private static HashMap<Long, List<ReMindBean.ContentBean>> hm = new HashMap<>();
    private static HashMap<String, List<String>> hmtitle = new HashMap<>();
    private static HashMap<String, List<String>> hmbody = new HashMap<>();

    private static ArrayList<ReMindBean.ContentBean> reverse = new ArrayList<ReMindBean.ContentBean>();
    private static ArrayList<String> reversebodyList = new ArrayList<>();
    private static ArrayList<String> reversetitleList = new ArrayList<>();
    private static ArrayList<Boolean> checkList = new ArrayList<>();

    /**
     * ????????????
     */
    private AppVersion appVersion;
    private VersionUp versionUp;
    private String allMessageBody;
    private String allMessageTitle;
    private int click_position = -1;
    private ReMindBean.ContentBean ContBean;
    private Fragment currentFragment;
    /**
     * ????????????????????????????????????
     */
    private MyBroadcastReceiver receiver;
    private CheckBox messageSwitch;
    private TextView tasktj;
    private TextView taskcontrol;
    private MessageFragment messageFragment;
    private WorkStatisticsFragment workStatisticsFragment;
    private FragmentTransaction ft;
    private FragmentManager supportFragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            // ????????????????????????????????????????????????????????????????????????
//            Class<?> aClass = Class.forName("com.hayden.hap.hd_push.sdk.PushService");
//            Field isStart = aClass.getDeclaredField("isStart");
//            isStart.setAccessible(true);
//            Object o = aClass.newInstance();
//            isStart.set(o,false);

            PushOption option = new PushOption();
            //??????ip?????????
            // ?????????emq???tcp://192.168.6.190:41010
            //????????????emq???tcp://192.168.6.48:1883
            //????????????eqm???tcp://10.200.1.56:1883
            option.setUri("tcp://10.200.1.56:1883");
            //??????????????????
            option.setClientId("rmt/1/" + LoginUserRecord.getInstance().getUser().getUserid());
            //??????????????????????????????
//            option.setUserName("hap");
//            5rt%RT
//            option.setPassword("5rt%RT");
            HDPush.init(MainActivity.this, option, new PushCallback() {
                @Override
                public void connectSuccess() {
                    Log.d(TAG, "connectSucess");
                }

                @Override
                public void connectError(Throwable exception) {
                    Log.d(TAG, "connectError");
                }

                @Override
                public void connectLost(Throwable cause) {
                    Log.d(TAG, "connectLost");
                }

                @Override
                public void messageArrived(String topic, String msg) {
                    // ?????????????????????????????????
                    if (currentFragment == messageFragment) {
                        messageFragment.setListViewStatus(0);
                        messageFragment.setList(hm, hmtitle, hmbody);
                    }

                    //?????????notice????????????????????????????????????
                    ReMindBean reMindBean = new Gson().fromJson(msg, ReMindBean.class);
                    allMessageBody = reMindBean.getMessageBody();
                    allMessageTitle = reMindBean.getMessageTitle();

                    ReMindBean.ContentBean content = reMindBean.getContent();
                    List<String> titleList = hmtitle.get("title");
                    List<String> bodyList = hmbody.get("body");
                    List<ReMindBean.ContentBean> remindlist = hm.get(LoginUserRecord.getInstance().getUser().getUserid());

                    if (remindlist == null) {
                        remindlist = new ArrayList<ReMindBean.ContentBean>();
                    }
                    if (titleList == null) {
                        titleList = new ArrayList<String>();
                    }
                    if (bodyList == null) {
                        bodyList = new ArrayList<String>();
                    }
                    //????????????????????????null???????????????????????????
                    remindlist.add(content);
                    bodyList.add(allMessageBody);
                    titleList.add(allMessageTitle);

                    hm.put(LoginUserRecord.getInstance().getUser().getUserid(), remindlist);
                    hmtitle.put("title", titleList);
                    hmbody.put("body", bodyList);

                    //???true?????????????????????????????????????????????
                    initNoticList(remindlist, hmbody.get("body"), hmtitle.get("title"), true);
                    if (currentFragment == messageFragment) {
                        messageFragment.setListViewStatus(1);
                    }
                    msgCountTV.setText(remindlist.size() + "");


                }

                @Override
                public void notificationClicked(String content) {
                    //????????????notice
                    //????????????????????????
                    //????????????????????????

                    NoTiceBean noTiceBean = new Gson().fromJson(content, NoTiceBean.class);
                    if (noTiceBean != null) {
                        gnbm = noTiceBean.getNextopr();
                        messageTitle = noTiceBean.getMessageTitle();
                        notice_work_subtask_id = noTiceBean.getDataid();
                        notice_work_subtask_description = noTiceBean.getMessageBody();
                    }

                    toNextPage(new RmtWorkSubtask());

                }
            });

            setContentView(R.layout.hd_hse_main_phone_app_layout_main);
            metrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metrics);
            initParam();
            // ?????????????????????
            initTime();
            initAppVersion();
            //?????????????????????????????????????????????
            getData();
        } catch (Exception e) {
            logger.error(e);
            ToastUtils.imgToast(this, R.drawable.hd_hse_common_msg_wrong,
                    "????????????????????????????????????");
        }
        //???????????????????????????????????????????????????
        receiver = new MyBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BaseApplication.getmAppContext().getPackageName() + ".LOGOUT");
        registerReceiver(receiver, intentFilter);
    }

    private void getData() {
        final RmtInterface rmtInterface = RetrofitUtil.createInterface(RmtInterface.class);
        // ????????????????????????
        Call<ResultData<List<FcbsBean>>> call = rmtInterface.getfcbs();
        HttpBusiness.action(MainActivity.this, false, call, new HttpCallBack<List<FcbsBean>>() {
            @Override
            public void success(List<FcbsBean> fcbsBeanList) {
                List<Long> orgidList = new ArrayList<>();
                for (FcbsBean f : fcbsBeanList) {
                    orgidList.add(f.getOrgid());
                }
                LoginUserRecord.getInstance().setOrgidList(orgidList);

            }

            @Override
            public void warning(String msg) {

                ToastUtils.toast(getApplicationContext(), msg);
            }

            @Override
            public void error(Throwable t) {

                ToastUtils.toast(getApplicationContext(), "???????????????????????????");
            }
        });
    }

    /**
     * ??????????????????
     */
    private void initAppVersion() {
        appVersion = (AppVersion) getIntent().getSerializableExtra(LoginActivity.APPVERSION);
        versionUp = new VersionUp(MainActivity.this);
        if (appVersion != null && appVersion.getCurrentVer() !=
                null && versionUp.isNeedUp(appVersion.getCurrentVer())) {
            actionBarMainMenu.setVersionUp(true);
            setCustomActionBar(true, eventLst,
                    new String[]{
                            IActionBar.IV_LMORE,
                            IActionBar.TV_TITLE,
                            IActionBar.TV_MORE,
                            IActionBar.IMG_REMIND});
        } else {
            actionBarMainMenu.setVersionUp(false);
            setCustomActionBar(true, eventLst,
                    new String[]{
                            IActionBar.IV_LMORE,
                            IActionBar.TV_TITLE,
                            IActionBar.TV_MORE,
                    });
        }


    }

    /**
     * TODO
     */
    @Override
    protected void onRestart() {
        // TODO Auto-generated method stub
        super.onRestart();
        initTime();
    }

    private void removedata() {

        List<String> titleList = hmtitle.get("title");
        List<String> bodyList = hmbody.get("body");
        List<ReMindBean.ContentBean> rmidlist = hm.get(LoginUserRecord.getInstance().getUser().getUserid());
        if (rmidlist != null) {
            for (int k = 0; k < rmidlist.size(); k++) {
                if (click_work_subtask_id != null) {
                    if (click_work_subtask_id.equals(rmidlist.get(k).getDataid())) {
                        ContBean = rmidlist.get(k);
                        titleList.remove(k);
                        bodyList.remove(k);
                        break;
                    }
                }
                if (notice_work_subtask_id != null) {
                    if (notice_work_subtask_id.equals(rmidlist.get(k).getDataid())) {
                        ContBean = rmidlist.get(k);
                        titleList.remove(k);
                        bodyList.remove(k);
                        break;
                    }
                }
            }
            click_work_subtask_id = null;
            notice_work_subtask_id = null;
            rmidlist.remove(ContBean);
            ContBean = null;
        }

        if (click_position != -1) {
            rmidlist.remove(ContBean);
            ContBean = null;
        }

        hm.put(LoginUserRecord.getInstance().getUser().getUserid(), rmidlist);
        hmtitle.put("title", titleList);
        hmbody.put("body", bodyList);
        if (rmidlist != null && rmidlist.size() > 0) {
            refreshNoticeDatas(rmidlist, hmbody.get("body"), hmtitle.get("title"), false);
            msgCountTV.setText(rmidlist.size() + "");
        } else {
            refreshNoticeDatas(rmidlist, hmbody.get("body"), hmtitle.get("title"), false);
            msgCountTV.setText("0");
//            all_chose_message.setText("????????????");
        }


    }


    @Override
    protected void onResume() {
        super.onResume();

        removedata();
    }


    @Override
    public void finish() {
        // TODO Auto-generated method stub
        if (LocationSwCard.mTimer != null) {
            LocationSwCard.mTimer.cancel();
            LocationSwCard.mTimer = null;
        }
        // ????????????????????????
        getSystemProperty().setPositionCard(null);
        super.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
//        HDPush.disConnect();
    }

    /**
     * initParam:(?????????????????????). <br/>
     * date: 2015???1???6??? <br/>
     *
     * @author lxf
     */
    private void initParam() {

        messageFragment = new MessageFragment();
        workStatisticsFragment = new WorkStatisticsFragment();
        supportFragmentManager = getSupportFragmentManager();
        ft = supportFragmentManager.beginTransaction();
        ft.add(R.id.messageOrtj, messageFragment).show(messageFragment);
//        ft.add(R.id.messageOrtj, workStatisticsFragment).hide(workStatisticsFragment);
        ft.commit();
        currentFragment = messageFragment;
//        taskListView = (NoticeListView) findViewById(R.id.hd_hse_main_tasklist);
//        taskListView.setiEventLsn(iEventLsn);
        nameTV = (TextView) findViewById(R.id.name);
        msgCountTV = (TextView) findViewById(R.id.hd_hse_main_notice_count);
        msgView = findViewById(R.id.hd_hse_main_message_ll);
        timeTV = (TextView) (findViewById(R.id.time));
        weekTV = (TextView) findViewById(R.id.week);
        messageSwitch = (CheckBox) findViewById(R.id.messageswitch);
        tasktj = (TextView) findViewById(R.id.tasktj);
        taskcontrol = (TextView) findViewById(R.id.taskcontrol);


        taskcontrol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
                if (workStatisticsFragment.isAdded()) {
                    fragmentTransaction.hide(workStatisticsFragment);
                }
                if (currentFragment != messageFragment)
                    currentFragment = messageFragment;
                if (messageFragment.isAdded()) {
                    fragmentTransaction.show(messageFragment).commit();
                } else {
                    fragmentTransaction.add(R.id.messageOrtj, messageFragment).show(messageFragment).commit();

                }

            }
        });
        tasktj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
                if (messageFragment.isAdded()) {
                    fragmentTransaction.hide(messageFragment);
                }
                if (currentFragment != workStatisticsFragment)
                    currentFragment = workStatisticsFragment;
                if (workStatisticsFragment.isAdded()) {
                    fragmentTransaction.show(workStatisticsFragment).commit();
                } else {
                    fragmentTransaction.add(R.id.messageOrtj, workStatisticsFragment).commit();
                }
            }
        });


//        all_chose_message = (Button) findViewById(R.id.all_chose_message);
//        clean_chose_message = (Button) findViewById(R.id.clean_chose_message);
        messageSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (currentFragment == messageFragment) {
                    messageFragment.setCheckBox(isChecked);
                }

            }
        });
//        all_chose_message.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (("????????????").equals(all_chose_message.getText().toString())) {
//                    all_chose_message.setText("????????????");
//                    taskListView.isAllChose(true);
//                } else if (("????????????").equals(all_chose_message.getText().toString())) {
//                    all_chose_message.setText("????????????");
//                    taskListView.isAllChose(false);
//                }
//            }
//        });
//        clean_chose_message.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                taskListView.isClean(true);
//            }
//        });


        PersonCard personCard = getSystemProperty()
                .getLoginPerson();
        if (personCard != null) {
            String name = personCard.getPersonid_desc();
            name = name.length() > 4 ? name.substring(0, 3) + "..." : name;
            nameTV.setText(name);
        } else {
            nameTV.setText("");
        }
        // ?????????????????????
        setCustomActionBar(true, eventLst, new String[]{IActionBar.IV_LMORE,
                IActionBar.TV_TITLE, IActionBar.TV_MORE});
        // ????????????????????????
        setActionBartitleContent("????????????", false);
        // ?????????????????????
        actionBarMainMenu = new ActionBarMainMenu(this, eventLst);
        // ????????????????????????
        listAppModule = getSystemProperty()
                .getMainAppModulelist("ONLINE");
        Collections.sort(listAppModule);
        // ????????????app????????????
        setNavContent(listAppModule, "hse-main-phone-app");

    }

    /**
     * initParam:(?????????????????????). <br/>
     * date: 2015???1???9??? <br/>
     *
     * @author lych
     */
    private void initTime() {
        Calendar ca = Calendar.getInstance();
        String weekdesc = "??????";
        int year = ca.get(Calendar.YEAR);// ????????????
        int month = ca.get(Calendar.MONTH) + 1;// ????????????
        int day = ca.get(Calendar.DATE);// ?????????
        int week = ca.get(Calendar.DAY_OF_WEEK);
        timeTV.setText(year + " - " + month + " - " + day);
        switch (week) {
            case 1:
                weekdesc = "?????????";
                break;
            case 2:
                weekdesc = "?????????";
                break;
            case 3:
                weekdesc = "?????????";
                break;
            case 4:
                weekdesc = "?????????";
                break;
            case 5:
                weekdesc = "?????????";
                break;
            case 6:
                weekdesc = "?????????";
                break;
            case 7:
                weekdesc = "?????????";
                break;
        }
        weekTV.setText(weekdesc);
    }

    //?????????????????????????????????title
    private String click_title;
    private String click_body;


    /**
     * ???????????????????????????????????????
     * noticList????????????????????????
     */
    public void refreshNoticeDatas(List<ReMindBean.ContentBean> noticList, List<String> bodyList, List<String> titleList, boolean flag) {
        List<ReMindBean.ContentBean> copynoticList = new ArrayList<>();
        List<String> copybodyList = new ArrayList<>();
        List<String> copytitleList = new ArrayList<>();
        if (noticList != null && noticList.size() > 0) {
            copynoticList.addAll(noticList);
            copybodyList.addAll(bodyList);
            copytitleList.addAll(titleList);
        }
        if (copynoticList != null && copynoticList.size() > 0) {
            //??????

            reverse.clear();
            reverse.addAll(copynoticList);
            reversebodyList.clear();
            reversebodyList.addAll(copybodyList);
            reversetitleList.clear();
            reversetitleList.addAll(copytitleList);
            for (int j = 0; j < copynoticList.size(); j++) {
                reverse.set(j, copynoticList.get(copynoticList.size() - j - 1));
                reversebodyList.set(j, copybodyList.get(copybodyList.size() - j - 1));
                reversetitleList.set(j, copytitleList.get(copytitleList.size() - j - 1));
            }

        }
        if (currentFragment == messageFragment) {
            if (noticList != null && noticList.size() > 0) {
                for (int j = 0; j < reverse.size(); j++) {
                    checkList.add(false);
                }
                messageFragment.setDatas(checkList, reverse, reversebodyList, reversetitleList);
            } else {
                messageFragment.setDatas(null, null, null, null);
            }
//            for (int i=0;i<28;i++){
//                ReMindBean.ContentBean contentBean=new ReMindBean.ContentBean();
//                contentBean.setDataid(i+"");
//                checkList.add(false);
//                reverse.add(contentBean);
//                reversebodyList.add(i+"ww");
//                reversetitleList.add(i+"hh");
//            }
//            messageFragment.setDatas(checkList,reverse, reversebodyList, reversetitleList);
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            // logout();
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }

    /**
     * eventLst:TODO(???????????????).
     */
    public IEventListener eventLst = new IEventListener() {

        @Override
        public void eventProcess(int arg0, Object... arg1) throws HDException {
            // TODO Auto-generated method stub
            if (arg0 == IEventType.ACTIONBAR_PHOTOGRAPH_CLICK) {
                Intent intent = new Intent(MainActivity.this,
                        CameraCaptureActivity.class);
                startActivity(intent);
            } else if (arg0 == IEventType.ACTIONBAR_MORE_CLICK) {
                actionBarMainMenu.showAsDropDown((View) arg1[0]);
            } else if (arg0 == IEventType.ACTION_UPDATE_VERSION) {
                if (isNeedUp())
                    versionUp.showUpdateDialog(appVersion.getUpgradeUrl(),
                            appVersion.getCurrentVer(), false);
                else
                    versionUp.showCurrentVersionInfor();
            } else if (arg0 == IEventType.ACTION_SYS) {
                //?????????
                QrCode.init(MainActivity.this);
                QrCode.openQrScanner(MainActivity.this);
            }
        }
    };

    private boolean isNeedUp() {
        boolean isNeedUp = false;
        if (appVersion != null && appVersion.getCurrentVer() != null
                && versionUp.isNeedUp(appVersion.getCurrentVer()))
            isNeedUp = true;
        return isNeedUp;
    }


    /**
     * ???????????????????????????????????????,???????????????????????????????????????
     *
     * @param noticList
     */
    protected void initNoticList(List<ReMindBean.ContentBean> noticList, List<String> bodyList, List<String> titleList, boolean flag) {
        // ??????????????????
        refreshNoticeDatas(noticList, bodyList, titleList, flag);
    }


    private void finishServer() {

        if (UdpClient.taskListDatas != null) {
            UdpClient.taskListDatas.clear();
        }
        if (startSrv != null) {
            this.getApplicationContext().stopService(startSrv);
        }
    }

    public void setMessageJump1(ReMindBean.ContentBean contBean) {
        this.reContBean = contBean;
    }

    public void setMessageJump2(String a, String b, int c) {
        this.click_title = a;
        this.click_body = b;
        this.click_position = c;
    }

    public void setMessageJump3(String a) {
        this.click_work_subtask_id = a;
    }


    public void toNextPage(RmtWorkSubtask rmtWorkSubtask) {

        if (gnbm != null) {
            switch (gnbm) {
                // ????????????
                case "approve":
                    Intent spIntent = new Intent(
                            this,
                            getClass("com.hd.hse.scene.phone.ui.activity.SceneTaskActivity"));
                    rmtWorkSubtask.setWork_subtask_id(Long.parseLong(notice_work_subtask_id));
                    rmtWorkSubtask.setDescription(notice_work_subtask_description);
                    spIntent.putExtra(FrameworkActivity.INTENT_FUNCTION_KEY,
                            gnbm);
                    spIntent.putExtra(FrameworkActivity.INTENT_WORKTASK_KEY,
                            rmtWorkSubtask);
                    startActivity(spIntent);
                    break;
                //????????????
                case "authorize":
                    Intent zysqIntent = new Intent(
                            this,
                            getClass("rmt.authorize.phone.app.activity.WorkAuthorizeMessageActivity"));
                    rmtWorkSubtask.setWork_subtask_id(Long.parseLong(notice_work_subtask_id));
//                    rmtWorkSubtask.setDescription(notice_work_subtask_description);
//                    zysqIntent.putExtra(FrameworkActivity.INTENT_FUNCTION_KEY,
//                            gnbm);
                    zysqIntent.putExtra(FrameworkActivity.INTENT_WORKTASK_KEY,
                            rmtWorkSubtask);
                    startActivity(zysqIntent);
                    break;
                //??????
                case "review":
                    Intent fcIntent = new Intent(
                            this,
                            getClass("com.hd.hse.scene.phone.ui.activity.SceneTaskActivity"));
                    rmtWorkSubtask.setWork_subtask_id(Long.parseLong(notice_work_subtask_id));
                    rmtWorkSubtask.setDescription(notice_work_subtask_description);
                    fcIntent.putExtra(FrameworkActivity.INTENT_FUNCTION_KEY,
                            gnbm);
                    fcIntent.putExtra(FrameworkActivity.INTENT_WORKTASK_KEY,
                            rmtWorkSubtask);
                    startActivity(fcIntent);
                    break;
                // ??????
                case "GB01":
                    break;
                // ????????????
                case "browse":
                    Intent rwllIntent = new Intent(
                            this,
                            getClass("com.hd.hse.wov.phone.ui.activity.worktask.TaskTabulationActivityOnline"));

                    rmtWorkSubtask.setWork_subtask_id(Long.parseLong(notice_work_subtask_id));
                    rmtWorkSubtask.setDescription(notice_work_subtask_description);
                    rwllIntent.putExtra(FrameworkActivity.INTENT_FUNCTION_KEY,
                            gnbm);
                    rwllIntent.putExtra(FrameworkActivity.INTENT_WORKTASK_KEY,
                            rmtWorkSubtask);
                    startActivity(rwllIntent);
                    break;
                case "delay":
                    Intent adtIntent = new Intent(
                            this,
                            getClass("rmt.delay.phone.app.activity.AddDelayTaskActivity"));

                    rmtWorkSubtask.setDelay_id(Long.parseLong(notice_work_subtask_id));
                    adtIntent.putExtra(FrameworkActivity.INTENT_WORKTASK_KEY,
                            rmtWorkSubtask);
                    startActivity(adtIntent);
                    break;
                case "pause":
                    Intent pIntent = new Intent(
                            this,
                            getClass("rmt.pause.phone.app.activity.PauseActivity"));

                    //notice_work_subtask_id????????????????????????
                    rmtWorkSubtask.setDelay_id(Long.parseLong(notice_work_subtask_id));
                    pIntent.putExtra(FrameworkActivity.INTENT_WORKTASK_KEY,
                            rmtWorkSubtask);
                    startActivity(pIntent);
                    break;
                case "reset":
                    Intent rIntent = new Intent(
                            this,
                            getClass("rmt.resume.phone.app.activity.ResumeActivity"));

                    rmtWorkSubtask.setDelay_id(Long.parseLong(notice_work_subtask_id));
                    rIntent.putExtra(FrameworkActivity.INTENT_WORKTASK_KEY,
                            rmtWorkSubtask);
                    startActivity(rIntent);
                    break;
                // ?????????
                case "relieve":
                    Intent jjbIntent = new Intent(
                            this,
                            getClass("com.hd.hse.scw.phone.ui.event.activity.workorder.GuardianShiftChangeActivity"));

                    rmtWorkSubtask.setWork_subtask_id(Long.parseLong(notice_work_subtask_id));
                    rmtWorkSubtask.setDescription(notice_work_subtask_description);
                    jjbIntent.putExtra(FrameworkActivity.INTENT_FUNCTION_KEY,
                            gnbm);
                    jjbIntent.putExtra(FrameworkActivity.INTENT_WORKTASK_KEY,
                            rmtWorkSubtask);
                    startActivity(jjbIntent);
                    break;
                //
                case "other":
                    new AlertDialog.Builder(MainActivity.this)
                            .setOnDismissListener(new DialogInterface.OnDismissListener() {
                                @Override
                                public void onDismiss(DialogInterface dialog) {

                                    removedata();
                                }
                            })
                            .setMessage(messageTitle + ":" + notice_work_subtask_description).show();

                    break;
                // ??????
                case "stop":
                    Intent jsIntent = new Intent(
                            this,
                            getClass("hd.hse.end.phone.ui.activity.TaskTabulationActivityOnline"));
//                    hd.hse.end.phone.ui.activity.EndTaskActivity

                    rmtWorkSubtask.setWork_subtask_id(Long.parseLong(notice_work_subtask_id));
                    rmtWorkSubtask.setDescription(notice_work_subtask_description);
                    jsIntent.putExtra(FrameworkActivity.INTENT_FUNCTION_KEY,
                            gnbm);
                    jsIntent.putExtra(FrameworkActivity.INTENT_WORKTASK_KEY,
                            rmtWorkSubtask);
                    startActivity(jsIntent);
                    break;
                //????????????
                case "assign":
                    Intent zdIntent = new Intent(
                            this,
                            getClass("rmt.assignstaff.phone.app.activity.AssignStaffActivity"));

                    rmtWorkSubtask.setWork_subtask_id(Long.parseLong(notice_work_subtask_id));
                    rmtWorkSubtask.setDescription(notice_work_subtask_description);
                    zdIntent.putExtra(FrameworkActivity.INTENT_FUNCTION_KEY,
                            gnbm);
                    zdIntent.putExtra(FrameworkActivity.INTENT_WORKTASK_KEY,
                            rmtWorkSubtask);
                    startActivity(zdIntent);
                    break;
                //????????????
                case "leader":
                    Intent ldspIntent = new Intent(
                            this,
                            getClass("rmt.leaderappr.phone.app.activity.LeaderApprActivity"));

                    rmtWorkSubtask.setWork_subtask_id(Long.parseLong(notice_work_subtask_id));
                    rmtWorkSubtask.setDescription(notice_work_subtask_description);
                    ldspIntent.putExtra(FrameworkActivity.INTENT_FUNCTION_KEY,
                            gnbm);
                    ldspIntent.putExtra(FrameworkActivity.INTENT_WORKTASK_KEY,
                            rmtWorkSubtask);
                    startActivity(ldspIntent);
                    break;
                case "xgf":
                    Intent xgfIntent = new Intent(
                            this,
                            getClass("rmt.leaderappr.phone.app.activity.LeaderApprActivity"));

                    rmtWorkSubtask.setWork_subtask_id(Long.parseLong(notice_work_subtask_id));
                    rmtWorkSubtask.setDescription(notice_work_subtask_description);
                    xgfIntent.putExtra(FrameworkActivity.INTENT_FUNCTION_KEY,
                            gnbm);
                    xgfIntent.putExtra(FrameworkActivity.INTENT_WORKTASK_KEY,
                            rmtWorkSubtask);
                    startActivity(xgfIntent);
                    break;
                default:
                    ToastUtils.imgToast(this, R.drawable.hd_hse_common_msg_wrong,
                            "????????????????????????????????????????????????");
                    break;

            }
            gnbm = null;
            return;
        }

        if (reContBean != null && reContBean.getNextopr() != null
                && rmtWorkSubtask != null) {
            String funCode = reContBean.getNextopr();
            switch (funCode) {
                // ????????????
                case "approve":
                    Intent spIntent = new Intent(
                            this,
                            getClass("com.hd.hse.scene.phone.ui.activity.SceneTaskActivity"));
                    rmtWorkSubtask.setWork_subtask_id(Long.parseLong(reContBean.getDataid()));
                    rmtWorkSubtask.setDescription(reContBean.getMessageBody());
                    spIntent.putExtra(FrameworkActivity.INTENT_FUNCTION_KEY,
                            funCode);
                    spIntent.putExtra(FrameworkActivity.INTENT_WORKTASK_KEY,
                            rmtWorkSubtask);
                    startActivity(spIntent);
                    break;
                //????????????
                case "authorize":
                    Intent zysqIntent = new Intent(
                            this,
                            getClass("rmt.authorize.phone.app.activity.WorkAuthorizeMessageActivity"));
                    rmtWorkSubtask.setWork_subtask_id(Long.parseLong(reContBean.getDataid()));
//                    rmtWorkSubtask.setDescription(reContBean.getMessageBody());
//                    zysqIntent.putExtra(FrameworkActivity.INTENT_FUNCTION_KEY,
//                            funCode);
                    zysqIntent.putExtra(FrameworkActivity.INTENT_WORKTASK_KEY,
                            rmtWorkSubtask);
                    startActivity(zysqIntent);
                    break;
                //??????
                case "review":

                    Intent fcIntent = new Intent(
                            this,
//                            com.hd.hse.scene.phone.ui.activity.ReTaskTabulationActivity
                            getClass("com.hd.hse.scene.phone.ui.activity.SceneTaskActivity"));
                    rmtWorkSubtask.setWork_subtask_id(Long.parseLong(reContBean.getDataid()));
                    rmtWorkSubtask.setDescription(reContBean.getMessageBody());
                    fcIntent.putExtra(FrameworkActivity.INTENT_FUNCTION_KEY,
                            funCode);
                    fcIntent.putExtra(FrameworkActivity.INTENT_WORKTASK_KEY,
                            rmtWorkSubtask);
                    startActivity(fcIntent);

                    break;
                // ??????
                case "GB01":
                    break;
                // ????????????
                case "browse":
                    Intent rwllIntent = new Intent(
                            this,
                            getClass("com.hd.hse.wov.phone.ui.activity.worktask.TaskTabulationActivityOnline"));

                    rmtWorkSubtask.setWork_subtask_id(Long.parseLong(reContBean.getDataid()));
                    rmtWorkSubtask.setDescription(reContBean.getMessageBody());
                    rwllIntent.putExtra(FrameworkActivity.INTENT_FUNCTION_KEY,
                            funCode);
                    rwllIntent.putExtra(FrameworkActivity.INTENT_WORKTASK_KEY,
                            rmtWorkSubtask);
                    startActivity(rwllIntent);
                    break;
                //????????????
                case "delay":
                    Intent adtIntent = new Intent(
                            this,
                            getClass("rmt.delay.phone.app.activity.AddDelayTaskActivity"));

                    rmtWorkSubtask.setDelay_id(Long.parseLong(reContBean.getDataid()));
                    adtIntent.putExtra(FrameworkActivity.INTENT_WORKTASK_KEY,
                            rmtWorkSubtask);
                    startActivity(adtIntent);
                    break;
                case "pause":
                    Intent pIntent = new Intent(
                            this,
                            getClass("rmt.pause.phone.app.activity.PauseActivity"));

                    rmtWorkSubtask.setDelay_id(Long.parseLong(reContBean.getDataid()));
                    pIntent.putExtra(FrameworkActivity.INTENT_PAUSEORRESET_KEY,
                            rmtWorkSubtask);
                    startActivity(pIntent);
                    break;
                case "reset":
                    Intent rIntent = new Intent(
                            this,
                            getClass("rmt.resume.phone.app.activity.ResumeActivity"));

                    rmtWorkSubtask.setDelay_id(Long.parseLong(reContBean.getDataid()));
                    rIntent.putExtra(FrameworkActivity.INTENT_PAUSEORRESET_KEY,
                            rmtWorkSubtask);
                    startActivity(rIntent);
                    break;
                // ?????????
                case "relieve":
                    Intent jjbIntent = new Intent(
                            this,
                            getClass("com.hd.hse.scw.phone.ui.event.activity.workorder.GuardianShiftChangeActivity"));

                    rmtWorkSubtask.setWork_subtask_id(Long.parseLong(reContBean.getDataid()));
                    rmtWorkSubtask.setDescription(reContBean.getMessageBody());
                    jjbIntent.putExtra(FrameworkActivity.INTENT_FUNCTION_KEY,
                            funCode);
                    jjbIntent.putExtra(FrameworkActivity.INTENT_WORKTASK_KEY,
                            rmtWorkSubtask);
                    startActivity(jjbIntent);
                    break;
                //
                case "other":
                    new AlertDialog.Builder(MainActivity.this)
                            .setOnDismissListener(new DialogInterface.OnDismissListener() {
                                @Override
                                public void onDismiss(DialogInterface dialog) {

                                    removedata();
                                }
                            })
                            .setMessage(reContBean.getMessageTitle() + ":" + reContBean.getMessageBody()).show();
                    break;
                // ???????????????
                case "LL01":
                    break;
                // ??????
                case "QX01":
                    break;
                // ????????????
                case "WZ01":
                    break;
                // ??????
                case "stop":
                    Intent jsIntent = new Intent(
                            this,
                            getClass("hd.hse.end.phone.ui.activity.TaskTabulationActivityOnline"));

//                    hd.hse.end.phone.ui.activity.EndTaskActivity
                    rmtWorkSubtask.setWork_subtask_id(Long.parseLong(reContBean.getDataid()));
                    rmtWorkSubtask.setDescription(reContBean.getMessageBody());
                    jsIntent.putExtra(FrameworkActivity.INTENT_FUNCTION_KEY,
                            funCode);
                    jsIntent.putExtra(FrameworkActivity.INTENT_WORKTASK_KEY,
                            rmtWorkSubtask);
                    startActivity(jsIntent);
                    break;
                //????????????
                case "assign":
                    Intent zdIntent = new Intent(
                            this,
                            getClass("rmt.assignstaff.phone.app.activity.AssignStaffActivity"));

                    rmtWorkSubtask.setWork_subtask_id(Long.parseLong(reContBean.getDataid()));
                    rmtWorkSubtask.setDescription(reContBean.getMessageBody());
                    zdIntent.putExtra(FrameworkActivity.INTENT_FUNCTION_KEY,
                            funCode);
                    zdIntent.putExtra(FrameworkActivity.INTENT_WORKTASK_KEY,
                            rmtWorkSubtask);
                    startActivity(zdIntent);
                    break;
                //????????????
                case "leader":
                    Intent ldspIntent = new Intent(
                            this,
                            getClass("rmt.leaderappr.phone.app.activity.LeaderApprActivity"));

                    rmtWorkSubtask.setWork_subtask_id(Long.parseLong(reContBean.getDataid()));
                    rmtWorkSubtask.setDescription(reContBean.getMessageBody());
                    ldspIntent.putExtra(FrameworkActivity.INTENT_FUNCTION_KEY,
                            funCode);
                    ldspIntent.putExtra(FrameworkActivity.INTENT_WORKTASK_KEY,
                            rmtWorkSubtask);
                    startActivity(ldspIntent);
                    break;
                //????????????
                case "xgf":
                    Intent xgfIntent = new Intent(
                            this,
                            getClass("rmt.leaderappr.phone.app.activity.LeaderApprActivity"));

                    rmtWorkSubtask.setWork_subtask_id(Long.parseLong(reContBean.getDataid()));
                    rmtWorkSubtask.setDescription(reContBean.getMessageBody());
                    xgfIntent.putExtra(FrameworkActivity.INTENT_FUNCTION_KEY,
                            funCode);
                    xgfIntent.putExtra(FrameworkActivity.INTENT_WORKTASK_KEY,
                            rmtWorkSubtask);
                    startActivity(xgfIntent);
                    break;
                default:
                    ToastUtils.imgToast(this, R.drawable.hd_hse_common_msg_wrong,
                            "????????????????????????????????????????????????");
                    break;

            }
        }
        //????????????
        if (click_position != -1) {

            if (gnbm == null && reContBean == null) {
                new AlertDialog.Builder(MainActivity.this)
                        .setOnDismissListener(new DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialog) {
                                List<String> titleList = hmtitle.get("title");
                                List<String> bodyList = hmbody.get("body");
                                List<ReMindBean.ContentBean> rmidlist = hm.get(LoginUserRecord.getInstance().getUser().getUserid());

                                if (rmidlist != null) {
                                    rmidlist.remove(click_position);
                                    bodyList.remove(click_position);
                                    titleList.remove(click_position);

                                }
                                hm.put(LoginUserRecord.getInstance().getUser().getUserid(), rmidlist);
                                hmtitle.put("title", titleList);
                                hmbody.put("body", bodyList);
                                if (rmidlist != null && rmidlist.size() > 0) {
                                    refreshNoticeDatas(rmidlist, hmbody.get("body"), hmtitle.get("title"), false);
                                    msgCountTV.setText(rmidlist.size() + "");
                                } else {
                                    refreshNoticeDatas(rmidlist, hmbody.get("body"), hmtitle.get("title"), false);
                                    msgCountTV.setText("0");
                                }
                                click_position = -1;
                            }
                        })
                        .setMessage(click_title + ":" + click_body).show();

            }
        }
    }

    /**
     * getClass:(). <br/>
     * date: 2015???6???25??? <br/>
     *
     * @param packagename
     * @return
     * @author lxf
     */
    private Class<?> getClass(String packagename) {

        try {
            return Class.forName(packagename);
        } catch (ClassNotFoundException e) {
            logger.error(e);
            ToastUtils.imgToast(this, R.drawable.hd_hse_common_msg_wrong,
                    "????????????????????????????????????");
        }
        return null;
    }

    public void changeView(String s) {
        msgCountTV.setText(s);
    }

    class MyBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            //????????????
            relogout();
        }
    }

    private void relogout() {
        MainActionListener actionListener = new MainActionListener();
        BusinessAction action = new BusinessAction(actionListener);
        try {
            // ??????????????????
            action.action(IActionType.MAIN_LOGOUT);
            //?????????????????????
            NotificationManager nm = (NotificationManager) MainActivity.this.getSystemService(NOTIFICATION_SERVICE);
            nm.cancelAll();
            Intent i = new Intent(SystemApplication.getInstance().getLastActivity(), LoginActivity.class);
            Activity activity = SystemApplication.getInstance().getLastActivity();
            SystemApplication.getInstance().getLastActivity().startActivity(i);
            // ????????????
            SystemApplication.getInstance().exit();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            ToastUtils.toast(getApplicationContext(), e.getMessage());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RESULT_OK == resultCode) {
            if (data != null) {
                if (data.getIntExtra(CodeUtils.RESULT_TYPE, 0) == CodeUtils.RESULT_SUCCESS) {
                    String dataString = data.getStringExtra(CodeUtils.RESULT_STRING);
                    if (TextUtils.isEmpty(dataString)) {
                        Toast.makeText(getApplicationContext(), "???????????????????????????,????????????????????????", Toast.LENGTH_LONG).show();
                        return;
                    }
                    try {
                        Gson gson = new Gson();
                        RmtJjbData rmtJjbData = gson.fromJson(dataString, RmtJjbData.class);
                        afterScanQrcode(rmtJjbData);
                    } catch (JsonSyntaxException e) {
                        Toast.makeText(getApplicationContext(), "???????????????????????????,????????????????????????", Toast.LENGTH_LONG).show();
                        return;
                    }


                } else
                    Toast.makeText(getApplicationContext(), "?????????????????????", Toast.LENGTH_LONG).show();

            }
        }
    }

    /**
     * ?????????????????????
     */
    private void afterScanQrcode(RmtJjbData rmtJjbData) {
        long createTime = rmtJjbData.getCreateTime();
        long timeOut = rmtJjbData.getTimeOut();
        long currentTime = System.currentTimeMillis();
        if (currentTime - createTime > timeOut) {
            ToastUtil.toast(getApplicationContext(), "???????????????????????????????????????????????????");
            return;
        }
        String funCode = rmtJjbData.getFunCode();
        RmtWorkSubtask rmtWorkSubtask = new RmtWorkSubtask();
        switch (funCode) {
            //??????
            case "review":
                Intent fcIntent = new Intent(
                        this,
                        getClass("com.hd.hse.scene.phone.ui.activity.SceneTaskActivity"));
                rmtWorkSubtask.setWork_subtask_id(rmtJjbData.getWork_subtask_id());
                rmtWorkSubtask.setDescription(rmtJjbData.getDescription());
                fcIntent.putExtra(FrameworkActivity.INTENT_FUNCTION_KEY,
                        funCode);
                fcIntent.putExtra(FrameworkActivity.INTENT_WORKTASK_KEY,
                        rmtWorkSubtask);
                startActivity(fcIntent);
                break;
        }

    }
}