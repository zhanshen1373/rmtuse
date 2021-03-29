package rmt.assignstaff.phone.app.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.hayden.hap.common.common.bussiess.HttpBusiness;
import com.hayden.hap.common.common.bussiess.HttpCallBack;
import com.hayden.hap.common.common.bussiess.ResultData;
import com.hayden.hap.common.login.business.LoginUserRecord;
import com.hayden.hap.common.utils.RetrofitUtil;
import com.hd.hse.common.business.web_interface.RmtInterface;
import com.hd.hse.common.component.phone.constant.IActionBar;
import com.hd.hse.common.component.phone.event.IEventType;
import com.hd.hse.common.component.phone.listener.IEventListener;
import com.hd.hse.common.component.phone.util.ToastUtils;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.module.phone.ui.activity.BaseFrameActivity;
import com.hd.hse.entity.assign.RmtAssignUser;
import com.hd.hse.entity.assign.RmtWorkAssign;
import com.hd.hse.entity.resultdata.RmtSjResultData;
import com.hd.hse.entity.workorder.RmtWorkSubtask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import rmt.assignstaff.phone.app.adapter.SelectPersionAdapter;
import rmt.assignstaff.phone.app.adapter.SjDataAdapter;
import rmt.assignstaff.phone.app.utils.PinYin4j;
import test.demo.rmt_assignstaff_phone_app.R;

import static rmt.assignstaff.phone.app.activity.AssignStaffActivity.COMEWHERE;
import static rmt.assignstaff.phone.app.activity.AssignStaffActivity.SELECTDATA;
import static rmt.assignstaff.phone.app.activity.AssignStaffActivity.WORKSUBTASK;

/**
 * 人员选择界面
 * created by yangning on 2017/8/17 11:05.
 */

public class SelectPersionActivity extends BaseFrameActivity implements View.OnClickListener, IEventListener {
    private List<RmtWorkAssign> selectData;
    private RmtWorkSubtask workSubtask;
    private List<RmtAssignUser> usersData;
    private List<RmtSjResultData.MainvoBean> sjData;
    private SelectPersionAdapter mAdapter;
    private SjDataAdapter sjDataAdapter;
    private ListView lv;
    private Button btCancel;
    private Button btEnsure;
    private RelativeLayout reAll;
    private String ids = "";
    private String comeWhere;

    private List<String> names = new ArrayList<>();
    private List<String> copynames = new ArrayList<>();
    private List<RmtAssignUser> ks = new ArrayList<>();
    private List<Integer> lengthlist = new ArrayList<>();
    private List<String> storagelist = new ArrayList<>();
    private List<Set<String>> hanziSpellList = null;
    PinYin4j pinyin = new PinYin4j();
    private String sm[] = new String[]{"b", "p", "m", "f", "d", "t", "n", "l", "g",
            "k", "h", "j", "q", "x", "zh", "ch", "sh", "r", "z", "c", "s", "y", "w"};
    private String ym[] = new String[]{"a", "o", "e", "i", "u", "ü", "ai", "ei", "ui", "ao",
            "ou", "iu", "ie", "üe", "er", "an", "en", "in", "un", "ün", "ang", "eng", "ing", "ong"

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rmt_select_people_activity_layout);
        initView();
        initActionbar();
        comeWhere = (String) getIntent().getSerializableExtra(COMEWHERE);
        if ("送交".equals(comeWhere)) {
            initSjData();
        } else {
            initData();
        }
    }

    private void initSjData() {

        sjData = new ArrayList<>();
        sjDataAdapter = new SjDataAdapter(sjData, SelectPersionActivity.this);
        lv.setAdapter(sjDataAdapter);
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sjDataAdapter.setCurrentCheck(position, !sjDataAdapter.getCurrentCheck(position));
            }
        });

        workSubtask = (RmtWorkSubtask) getIntent().getSerializableExtra(WORKSUBTASK);
        if (workSubtask != null) {

            getSjData(workSubtask.getWork_task_id(), workSubtask.getWork_subtask_id());
        } else {
            ToastUtils.toast(getApplicationContext(), "传入数据有误");
        }

    }

    private void getSjData(long taskId, long subtaskId) {

        final ProgressDialog dialog = new ProgressDialog(SelectPersionActivity.this);
        dialog.setMessage("加载数据中。。。");
        dialog.show();
        final RmtInterface rmtInterface = RetrofitUtil.createInterface(RmtInterface.class);
        Call<ResultData<RmtSjResultData>> call = rmtInterface.getSjList(taskId, subtaskId);
        HttpBusiness.action(SelectPersionActivity.this, false, call, new HttpCallBack<RmtSjResultData>() {


            @Override
            public void success(RmtSjResultData rmtSjResultData) {

                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                sjData.clear();
                if (rmtSjResultData != null) {
                    List<RmtSjResultData.MainvoBean> mainvo = rmtSjResultData.getMainvo();
                    sjData.addAll(mainvo);
                }
                sjDataAdapter.initCheckMap();
                sjDataAdapter.notifyDataSetChanged();

            }

            @Override
            public void warning(String msg) {

                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                ToastUtils.toast(getApplicationContext(), msg);
            }

            @Override
            public void error(Throwable t) {

                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                ToastUtils.toast(getApplicationContext(), "获取送交数据失败");
            }
        });


    }

    private void initData() {
        usersData = new ArrayList<>();
        mAdapter = new SelectPersionAdapter(usersData, SelectPersionActivity.this);
        lv.setAdapter(mAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mAdapter.setCurrentCheck(position, !mAdapter.getCurrentCheck(position));
            }
        });
        selectData = (List<RmtWorkAssign>) getIntent().getSerializableExtra(SELECTDATA);
        workSubtask = (RmtWorkSubtask) getIntent().getSerializableExtra(WORKSUBTASK);
        if (workSubtask != null && selectData != null && selectData.size() > 0) {

            for (RmtWorkAssign rmtWorkAssign : selectData) {
                if ("".equals(ids))
                    ids += rmtWorkAssign.getWork_assign_id();
                else
                    ids += ("," + rmtWorkAssign.getWork_assign_id());
            }
            getNetData(workSubtask.getWork_task_id(), workSubtask.getWork_subtask_id(), ids);
        } else {
            ToastUtils.toast(getApplicationContext(), "传入数据有误");
        }
    }

    private void initView() {
        lv = (ListView) findViewById(R.id.rmt_select_people_activity_layout_lv);
        btCancel = (Button) findViewById(R.id.rmt_select_people_activity_layout_bt_cancel);
        btEnsure = (Button) findViewById(R.id.rmt_select_people_activity_layout_bt_ensure);
        reAll = (RelativeLayout) findViewById(R.id.rmt_select_people_activity_layout_li_all);
        btCancel.setOnClickListener(this);
        btEnsure.setOnClickListener(this);
    }

    private void initActionbar() {
        // 初始化ActionBar
        setCustomActionBar(false, this, new String[]{IActionBar.TV_BACK, IActionBar.TV_TITLE, IActionBar.IBTN_SEARCH});
        // 设置导航栏标题
        setActionBartitleContent("人员选择", false);
    }

    private void getNetData(long subtaskId, long sutaskId, String ids) {
        final ProgressDialog dialog = new ProgressDialog(SelectPersionActivity.this);
        dialog.setMessage("加载数据中。。。");
        dialog.show();
        final RmtInterface rmtInterface = RetrofitUtil.createInterface(RmtInterface.class);
        Call<ResultData<List<RmtAssignUser>>> call = rmtInterface.getWorkAssign(subtaskId, sutaskId, ids, "assign", 2);
        HttpBusiness.action(SelectPersionActivity.this, false, call, new HttpCallBack<List<RmtAssignUser>>() {
            @Override
            public void success(List<RmtAssignUser> rmtAssignUsers) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                usersData.clear();
                if (rmtAssignUsers != null)
                    usersData.addAll(rmtAssignUsers);
                ks.addAll(usersData);
                for (int w = 0; w < usersData.size(); w++) {
                    names.add(usersData.get(w).getPerson_name());
                }
                copynames.addAll(names);
                mAdapter.initCheckMap();
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void warning(String msg) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                ToastUtils.toast(getApplicationContext(), msg);
            }

            @Override
            public void error(Throwable t) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                ToastUtils.toast(getApplicationContext(), "获取人员指定数据失败");
            }
        });
    }

    /**
     * @param ids
     * @param body
     */
    private void assignOK(String ids, List<RmtAssignUser> body) {
        final ProgressDialog dialog = new ProgressDialog(SelectPersionActivity.this);
        dialog.setMessage("数据提交中。。。");
        dialog.show();
        final RmtInterface rmtInterface = RetrofitUtil.createInterface(RmtInterface.class);
        Call<ResultData<Object>> call = rmtInterface.assignOK(ids, body);
        HttpBusiness.action(SelectPersionActivity.this, false, call, new HttpCallBack<Object>() {
            @Override
            public void success(Object Object) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                ToastUtils.toast(getApplicationContext(), "数据提交成功");
                finish();
            }

            @Override
            public void warning(String msg) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                ToastUtils.toast(getApplicationContext(), msg);
            }

            @Override
            public void error(Throwable t) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                ToastUtils.toast(getApplicationContext(), "数据提交失败");
            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.rmt_select_people_activity_layout_bt_cancel) {
            finish();
        } else if (id == R.id.rmt_select_people_activity_layout_bt_ensure) {

            if ("送交".equals(comeWhere)) {
                HashMap<Integer, Boolean> checkMap = sjDataAdapter.getCheckMap();
                List<Long> rmtsjData = new ArrayList<>();
                for (int i = 0; i < sjData.size(); i++) {
                    if (checkMap.get(i)) {
                        long userid = sjData.get(i).getHeadVO().getSY_USER_M_QUERY().getUserid();
                        rmtsjData.add(userid);
                    }
                }
                if (rmtsjData.size() == 0) {
                    ToastUtils.toast(getApplicationContext(), "您还未勾选任何条目");
                    return;
                }

                postSjData(workSubtask.getWork_subtask_id(), rmtsjData.get(0));
            } else {
                HashMap<Integer, Boolean> checkMap = mAdapter.getCheckMap();
                List<RmtAssignUser> rmtAssignUsers = new ArrayList<>();
                for (int i = 0; i < usersData.size(); i++) {
                    if (checkMap.get(i))
                        rmtAssignUsers.add(usersData.get(i));
                }
                if (rmtAssignUsers.size() == 0) {
                    ToastUtils.toast(getApplicationContext(), "您还未勾选任何条目");
                    return;
                }
                //提交
                assignOK(ids, rmtAssignUsers);
            }


        }
    }

    private void postSjData(long subtaskid, Long personid) {

        final ProgressDialog dialog = new ProgressDialog(SelectPersionActivity.this);
        dialog.setMessage("加载数据中。。。");
        dialog.show();
        final RmtInterface rmtInterface = RetrofitUtil.createInterface(RmtInterface.class);
        Call<ResultData<Object>> call = rmtInterface.sjpostOK(workSubtask.getWork_subtask_id(), LoginUserRecord.getInstance().getUser().getUserid(), personid);
        HttpBusiness.action(SelectPersionActivity.this, false, call, new HttpCallBack<Object>() {


            @Override
            public void success(Object result) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                ToastUtils.toast(getApplicationContext(), "数据提交成功");
            }

            @Override
            public void warning(String msg) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                ToastUtils.toast(getApplicationContext(), msg);
            }

            @Override
            public void error(Throwable t) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                ToastUtils.toast(getApplicationContext(), "数据提交失败");
            }
        });

    }

    @Override
    public void eventProcess(int eventType, Object... objects) throws HDException {
        if (eventType == IEventType.ACTIONBAR_SEARCH_DELBUTTON || eventType ==
                IEventType.ACTIONBAR_CALNEL_CLICK || eventType == IEventType.ACTIONBAR_SEARCH_CLICK) {

            usersData.clear();
            usersData.addAll(ks);
            names.clear();
            names.addAll(copynames);
        }
        if (eventType == IEventType.ACTIONBAR_SEARCH_INPUTCHANGED) {
            String s = (String) objects[0];
            int start = (int) objects[1];
            int count = (int) objects[2];
            if (s.toString().length() > 0) {
                //判断第一个输入的是什么
                String substring = s.toString().substring(0, 1);

                //输入的是字母
                Pattern p = Pattern.compile("[a-zA-Z]");
                Matcher m = p.matcher(substring);
                if (m.matches()) {

                    //全拼
                    hanziSpellList = getHanziSpellList(true, names);

                    if (s.toString().length() > 1) {
                        String substring1 = s.toString().substring(1, 2);
                        String substring2 = s.toString().substring(0, 2);
                        List<String> ymstrings = Arrays.asList(ym);
                        List<String> smstrings = Arrays.asList(sm);

                        if (smstrings.contains(substring2)) {

                        } else {

                            //不在韵母表中
                            if (!ymstrings.contains(substring1)) {
                                //开启简拼
                                hanziSpellList = getHanziSpellList(false, names);
                            }

                        }


                    }

                    if (count != 0) {
                        //输入的时候
                        for (int i = 0; i < hanziSpellList.size(); i++) {
                            lengthlist.clear();
                            storagelist.clear();
                            Set<String> innerList = hanziSpellList.get(i);
                            Iterator<String> iterator = innerList.iterator();
                            while (iterator.hasNext()) {
                                String next = iterator.next();
                                lengthlist.add(next.length());
                                storagelist.add(next);
                            }
                            //移除数据中长度小于输入的
                            if (start + count > Collections.max(lengthlist)) {
                                names.remove(i);
                                hanziSpellList.remove(i);
                                usersData.remove(i);
                                i--;
                                continue;
                            }

                            for (String str : storagelist) {


                                if (str.length() < start + count) {
                                    innerList.remove(str);

                                } else {
                                    if (!str.substring(start, start + count).equals(s.toString().substring(start, start + count))) {
                                        innerList.remove(str);
                                    }
                                }

                            }

                            if (innerList.size() == 0) {
                                names.remove(i);
                                hanziSpellList.remove(i);
                                usersData.remove(i);
                                i--;
                            }


                        }

                    } else {
                        //回退的时候

                        for (int i = 0; i < hanziSpellList.size(); i++) {
                            lengthlist.clear();
                            storagelist.clear();
                            Set<String> innerList = hanziSpellList.get(i);
                            Iterator<String> iterator = innerList.iterator();
                            String next = null;
                            while (iterator.hasNext()) {
                                next = iterator.next();
                                lengthlist.add(next.length());
                                storagelist.add(next);
                            }
                            //移除数据中长度小于输入的
                            if (start + count > Collections.max(lengthlist)) {
                                names.remove(i);
                                hanziSpellList.remove(i);
                                usersData.remove(i);
                                i--;
                                continue;
                            }

                            for (String str : storagelist) {
                                if (str.length() < start) {
                                    innerList.remove(str);

                                } else {
                                    if (!str.substring(0, start).equals(s.toString().substring(0, start))) {
                                        innerList.remove(str);
                                    }
                                }

                            }

                            if (innerList.size() == 0) {
                                names.remove(i);
                                hanziSpellList.remove(i);
                                usersData.remove(i);
                                i--;
                            }
                        }


                    }
                }


                //输入的是汉字
                p = Pattern.compile("[\u4e00-\u9fa5]");
                m = p.matcher(substring);
                if (m.matches()) {

                    if (count != 0) {
                        //输入的时候
                        for (int i = 0; i < names.size(); i++) {

                            //移除数据中长度小于输入的
                            if (start + count > names.get(i).length()) {
                                names.remove(i);
                                usersData.remove(i);
                                i--;
                                continue;
                            }
                            if (!names.get(i).substring(start, count + start).equals(s.toString().substring(start, count + start))) {
                                names.remove(i);
                                usersData.remove(i);
                                i--;
                            }
                        }
                    } else {
                        //回退的时候

                        for (int i = 0; i < names.size(); i++) {

                            //移除数据中长度小于输入的
                            if (start + count > names.get(i).length()) {
                                names.remove(i);
                                usersData.remove(i);
                                i--;
                                continue;
                            }
                            if (!names.get(i).substring(0, start).equals(s.toString().substring(0, start))) {
                                names.remove(i);
                                usersData.remove(i);
                                i--;
                            }
                        }
                    }
                }

                //输入的是数字
                p = Pattern.compile("[0-9]*");
                m = p.matcher(substring);
                if (m.matches()) {
                    usersData.clear();
                }


            }

            mAdapter.notifyDataSetChanged();


        }
    }

    /**
     * 获得汉字拼音首字母列表
     */
    private List<Set<String>> getHanziSpellList(boolean qporjp, List<String> hanzi) {

        List<Set<String>> listSet = new ArrayList<Set<String>>();
        for (int i = 0; i < hanzi.size(); i++) {
//            if (!listSet.contains(pinyin.getPinyin(qporjp, hanzi.get(i).toString()))) {
//
//            }
            listSet.add(pinyin.getPinyin(qporjp, hanzi.get(i).toString()));

        }
        return listSet;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        setResult(0);
    }


}
