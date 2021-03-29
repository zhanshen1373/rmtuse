package com.hd.hse.common.component.phone.custom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.hd.hse.common.component.phone.R;
import com.hd.hse.common.component.phone.constant.IActionBar;
import com.hd.hse.common.component.phone.drawerarrow.MaterialMenuDrawable;
import com.hd.hse.common.component.phone.drawerarrow.MaterialMenuView;
import com.hd.hse.common.component.phone.event.IEventType;
import com.hd.hse.common.component.phone.listener.IEventListener;
import com.hd.hse.common.entity.SuperEntity;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.logger.LogUtils;
import com.hd.hse.entity.other.DailogDataBean;
import com.hd.hse.entity.resultdata.RmtWorkGasDetectReslt;
import com.hd.hse.entity.workorder.RmtWorkSubtask;
import com.hd.hse.entity.workorder.RmtWorkTaskBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.view.Gravity.START;

/**
 * ClassName: HSEActionBar ()<br/>
 * date: 2014年12月24日 <br/>
 *
 * @author wenlin
 */
@SuppressLint("NewApi")
public class HSEActionBar {

    private AppCompatActivity activity;
    public IEventListener eventListener;
    public String[] controlsVisible;
    private View actionBar;
    private LayoutInflater inflater;
    private DrawerLayout mDrawerLayout;
    HashMap<String, View> controlMap;
    private ListView mDrawerList;
    private DrawerArrowDrawable drawerArrowDrawable;
    private float offset;
    private boolean flipped;
    /**
     * ibtnBack:TODO(左侧返回).
     */
    public ImageButton ibtnBack;
    /**
     * tvSubTitle:TODO(副标题).
     */
    public TextView tvSubTitle;
    public ImageButton ibtnLMore;
    public TextView tvTitle;
    public ImageButton ibtnUpload;
    public ImageButton ibtnDown;
    public ImageButton ibtnRMore;
    public ImageView imgRemind;
    public MaterialMenuView imageView;
    public ImageButton ibtnLevelTwoMore;
    public ImageButton ibtnDelete;
    public ImageButton ibtnSearch;
    public EditText etSearch;
    private MyAlertDialog mAlertDialog;
    /**
     * 作业票详情页面
     */
    private ShowPopWindowDialog mShowDialog;

    private SuperEntity superEntity;
    private TextView tvCanel;
    private boolean isFlag;
    private boolean direction;
    /**
     * mIconSearchClear:TODO(清除图标).
     */
    private Drawable mIconSearchClear;

    /**
     * mIconSearch:TODO(搜索图标).
     */
    private Drawable mIconSearch;

    /**
     * isIconTip:TODO(主标题可下拉点击图标标示).
     */
    private boolean isIconTip = false;

    private boolean isOnline = false;

    /**
     * 上传
     */
    private Button btUp;
    /**
     * 上传
     */
    private Button btEdit;

    /**
     * 统计分析的筛选条件
     */
    private TextView sxtjTxt;
    /**
     * 任务取消
     */
    private ImageButton btCancle;


    /**
     * 气体检测数据
     */
    private List<RmtWorkGasDetectReslt> rmtWorkGasDetectResltList;

    public HSEActionBar(AppCompatActivity activity, String[] controlsVisible) {
        // TODO Auto-generated constructor stub
        this.activity = activity;
        this.inflater = LayoutInflater.from(activity);
        initActionBar(controlsVisible);
    }

    public HSEActionBar(AppCompatActivity activity, IEventListener eventListener,
                        String[] controlsVisible) {
        // TODO Auto-generated constructor stub
        this.activity = activity;
        this.eventListener = eventListener;
        this.inflater = LayoutInflater.from(activity);
        initActionBar(controlsVisible);
    }

    /**
     * Creates a new instance of HSEActionBar.
     *
     * @param activity
     * @param eventListener
     * @param controlsVisible
     * @param mDrawerLayout   应用界面的布局
     * @param mDrawerList     应用界面的ListView
     */
    public HSEActionBar(AppCompatActivity activity, IEventListener eventListener,
                        String[] controlsVisible, DrawerLayout mDrawerLayout,
                        ListView mDrawerList) {
        // TODO Auto-generated constructor stub
        this.activity = activity;
        this.eventListener = eventListener;
        this.inflater = LayoutInflater.from(activity);
        this.mDrawerLayout = mDrawerLayout;
        this.mDrawerList = mDrawerList;
        initActionBar(controlsVisible);
        initDrawer();
    }

    /**
     * initActionBar:(). <br/>
     * date: 2014年12月24日 <br/>
     *
     * @author wenlin
     */
    private void initActionBar(String[] controlsVisible) {
        // TODO Auto-generated method stub

        actionBar = inflater.inflate(
                R.layout.hd_hse_common_component_phone_actionbar, null, false);

        mIconSearchClear = activity.getResources().getDrawable(
                R.drawable.hd_hse_commom_component_phone_etsearch_clear_it);
        mIconSearch = activity.getResources().getDrawable(
                R.drawable.hd_hse_common_component_phone_icon_search_sel);

        OnClickListener clickLst = new OnClickLstner();

        initControl(clickLst);

        setControlVisible(controlsVisible);

        initActivityActionBar();
    }

    /**
     * initControl:(初始化控件). <br/>
     * date: 2014年12月24日 <br/>
     *
     * @param clickLst
     * @author wenlin
     */
    private void initControl(OnClickListener clickLst) {
        // TODO Auto-generated method stub
        sxtjTxt = (TextView) actionBar.findViewById(R.id.hd_hse_common_phone_actionbar_custom_tv_sxtj);
        sxtjTxt.setOnClickListener(clickLst);
        btCancle = (ImageButton) actionBar.findViewById(R.id.hd_hse_common_phone_actionbar_custom_img_ibtn_cancle);
        btCancle.setOnClickListener(clickLst);
        ibtnBack = (ImageButton) actionBar
                .findViewById(R.id.hd_hse_common_phone_actionbar_custom_img_tv_back);
        ibtnBack.setOnClickListener(clickLst);
        tvSubTitle = (TextView) actionBar
                .findViewById(R.id.hd_hse_common_phone_actionbar_custon_subtitle);
        tvSubTitle.setOnClickListener(clickLst);
        imageView = (MaterialMenuView) actionBar
                .findViewById(R.id.hd_hse_common_phone_actionbar_custom_img_tv_more_left);
        tvTitle = (TextView) actionBar
                .findViewById(R.id.hd_hse_common_phone_actionbar_custom_title);
        tvTitle.setOnClickListener(clickLst);
        ibtnUpload = (ImageButton) actionBar
                .findViewById(R.id.hd_hse_common_phone_actionbar_custom_img_ibtn_upload);
        ibtnUpload.setOnClickListener(clickLst);
        ibtnDown = (ImageButton) actionBar
                .findViewById(R.id.hd_hse_common_phone_actionbar_custom_img_ibtn_dowmload);
        ibtnDown.setOnClickListener(clickLst);
        ibtnRMore = (ImageButton) actionBar
                .findViewById(R.id.hd_hse_common_phone_actionbar_custom_img_tv_more);
        imgRemind = (ImageView) actionBar
                .findViewById(R.id.hd_hse_common_phone_actionbar_custom_img_tv_remind);
        ibtnRMore.setOnClickListener(clickLst);
        ibtnLevelTwoMore = (ImageButton) actionBar
                .findViewById(R.id.hd_hse_common_phone_actionbar_custom_img_more_leveltwo);
        ibtnLevelTwoMore.setOnClickListener(clickLst);
        ibtnDelete = (ImageButton) actionBar
                .findViewById(R.id.hd_hse_common_phone_actionbar_custom_img_ibtn_delete);
        ibtnDelete.setOnClickListener(clickLst);
        etSearch = (EditText) actionBar
                .findViewById(R.id.hd_hse_common_phone_actionbar_custom_img_et_search);
        etSearch.setOnClickListener(clickLst);
        etSearch.setOnKeyListener(etInputKeylistener);
        etSearch.setOnTouchListener(etSearch_OnTouch);
        etSearch.addTextChangedListener(etSearch_TextChanged);

        ibtnSearch = (ImageButton) actionBar
                .findViewById(R.id.hd_hse_common_phone_actionbar_custom_img_ibtn_search);
        ibtnSearch.setOnClickListener(clickLst);
        tvCanel = (TextView) actionBar
                .findViewById(R.id.hd_hse_common_phone_actionbar_custom_tv_canel);
        tvCanel.setOnClickListener(clickLst);
        btUp = (Button) actionBar
                .findViewById(R.id.hd_hse_statistic_actionbar_bt_up);
        btUp.setOnClickListener(clickLst);
        btEdit = (Button) actionBar
                .findViewById(R.id.hd_hse_statistic_actionbar_bt_edit);
        btEdit.setOnClickListener(clickLst);

        controlMap = new HashMap<String, View>() {
            /**
             * serialVersionUID:TODO().
             */
            private static final long serialVersionUID = 1L;

            {
                put(IActionBar.TV_TITLE, tvTitle);
                put(IActionBar.TV_BACK, ibtnBack);
                put(IActionBar.TV_MORE, ibtnRMore);
                put(IActionBar.IMG_REMIND, imgRemind);
                put(IActionBar.IV_LMORE, imageView);
                put(IActionBar.IBTN_UPLOAD, ibtnUpload);
                put(IActionBar.IBTN_DOWNLOAD, ibtnDown);
                put(IActionBar.IBTN_DELETE, ibtnDelete);
                put(IActionBar.IBTN_LEVELTWO_MORE, ibtnLevelTwoMore);
                put(IActionBar.TV_SUBTITLE, tvSubTitle);
                put(IActionBar.ET_SEARCH, etSearch);
                put(IActionBar.IBTN_SEARCH, ibtnSearch);
                put(IActionBar.TV_CANEL, tvCanel);
                put(IActionBar.BT_UP, btUp);
                put(IActionBar.BT_Edit, btEdit);
                put(IActionBar.TV_SXTJ, sxtjTxt);
                put(IActionBar.BT_CANCLE, btCancle);
            }

        };
    }

    /**
     * initDrawer:(初始化左侧抽屉). <br/>
     * date: 2014年12月24日 <br/>
     *
     * @author wenlin
     */
    private void initDrawer() {
        imageView.setVisibility(View.VISIBLE);
        imageView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (isFlag) {
                    mDrawerLayout.closeDrawer(Gravity.LEFT);
                    isFlag = false;
                } else {
                    mDrawerLayout.openDrawer(Gravity.LEFT);
                    isFlag = true;
                }
            }
        });
        mDrawerLayout
                .setDrawerListener(new DrawerLayout.SimpleDrawerListener() {

                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {
                        imageView
                                .getDrawable()
                                .setTransformationOffset(
                                        MaterialMenuDrawable.AnimationState.BURGER_ARROW,
                                        direction ? 2 - slideOffset
                                                : slideOffset);
                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {
                        direction = true;
                        isFlag = true;
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        direction = false;
                        isFlag = false;
                    }
                });
    }

    /**
     * initActivityActionBar:(将布局显示在目标布局上). date: 2014年10月10日
     *
     * @author wenlin
     */
    public void initActivityActionBar() {
        // TODO Auto-generated method stub
        ActionBar activityActionBar = activity.getSupportActionBar();
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.MATCH_PARENT, Gravity.CENTER);
        activityActionBar.setDisplayShowHomeEnabled(false);
        activityActionBar.setDisplayShowTitleEnabled(false);
        activityActionBar.setDisplayHomeAsUpEnabled(false);
        activityActionBar.setDisplayShowCustomEnabled(true);
        activityActionBar.setCustomView(actionBar, params);
        Toolbar toolbar = (Toolbar) actionBar.getParent();
        toolbar.setContentInsetsAbsolute(0, 0);

    }

    /**
     * setEventListener:(设置监听). <br/>
     * date: 2015年1月9日 <br/>
     *
     * @param eventListener
     * @author wenlin
     */
    public void setEventListener(IEventListener eventListener) {
        this.eventListener = eventListener;
    }

    public class OnClickLstner implements OnClickListener {

        @Override
        public void onClick(View view) {
            // TODO Auto-generated method stub
            try {
                if (eventListener == null)
                    return;

                if (mDrawerLayout != null
                        && mDrawerLayout.isDrawerVisible(START)) {
                    mDrawerLayout.closeDrawer(START);
                }

                if (view.getId() == R.id.hd_hse_common_phone_actionbar_custom_img_ibtn_dowmload) {
                    eventListener
                            .eventProcess(IEventType.ACTIONBAR_DOWNLOAD_CLICK);
                } else if (view.getId() == R.id.hd_hse_common_phone_actionbar_custom_img_ibtn_upload) {
                    eventListener
                            .eventProcess(IEventType.ACTIONBAR_UPLOAD_CLICK);
                } else if (view.getId() == R.id.hd_hse_common_phone_actionbar_custom_img_tv_back) {
                    if (etSearch.getVisibility() == View.VISIBLE) {
                        whenSearchActionbarStyle(false, view);
                    } else {
                        activity.finish();
                    }
                    eventListener.eventProcess(IEventType.ACTIONBAR_BACK_CLICK);
                } else if (view.getId() == R.id.hd_hse_common_phone_actionbar_custom_img_tv_more_left) {
                    if (mDrawerLayout != null)
                        mDrawerLayout.closeDrawer(mDrawerList);
                    eventListener
                            .eventProcess(IEventType.ACTIONBAR_LEFTMORE_CLICK);
                } else if (view.getId() == R.id.hd_hse_common_phone_actionbar_custom_title) {
                    if (isIconTip) {
                        eventListener.eventProcess(
                                IEventType.ACTIONBAR_CLICK_POPUP, view);
                        showTitleInfoPopupWin();
                    }
                } else if (view.getId() == R.id.hd_hse_common_phone_actionbar_custom_img_tv_more) {
                    eventListener.eventProcess(IEventType.ACTIONBAR_MORE_CLICK,
                            view);
                } else if (view.getId() == R.id.hd_hse_common_phone_actionbar_custom_img_more_leveltwo) {
                    eventListener.eventProcess(
                            IEventType.ACTIONBAR_LEVELTWO_MORE_CLICK, view);
                } else if (view.getId() == R.id.hd_hse_common_phone_actionbar_custom_img_ibtn_delete) {
                    eventListener.eventProcess(
                            IEventType.ACTIONBAR_DELETE_CLICK, view);
                } else if (view.getId() == R.id.hd_hse_common_phone_actionbar_custon_subtitle) {
                    eventListener.eventProcess(
                            IEventType.ACTIONBAR_SUBTITLE_CLICK, view);
                } else if (view.getId() == R.id.hd_hse_common_phone_actionbar_custom_img_ibtn_search) {
                    whenSearchActionbarStyle(true, view);

                    eventListener
                            .eventProcess(IEventType.ACTIONBAR_RETURN_CLICK);
                } else if (view.getId() == R.id.hd_hse_common_phone_actionbar_custom_img_et_search) {
                    eventListener.eventProcess(IEventType.ET_SEARCH_CLICK);
                } else if (view.getId() == R.id.hd_hse_common_phone_actionbar_custom_tv_canel) {
                    whenSearchActionbarStyle(false, view);
                    etSearch.setText("");
                    eventListener
                            .eventProcess(IEventType.ACTIONBAR_CALNEL_CLICK);
                } else if (view.getId() == R.id.hd_hse_statistic_actionbar_bt_up) {
                    eventListener.eventProcess(
                            IEventType.ACTIONBAR_BT_UP_CLICK, view);
                } else if (view.getId() == R.id.hd_hse_statistic_actionbar_bt_edit) {
                    eventListener.eventProcess(
                            IEventType.ACTIONBAR_BT_EDIT_CLICK, view);
                } else if (view.getId() == R.id.hd_hse_common_phone_actionbar_custom_tv_sxtj) {
                    eventListener.eventProcess(IEventType.ACTION_SXTJ);
                } else if (view.getId() == R.id.hd_hse_common_phone_actionbar_custom_img_ibtn_cancle) {
                    eventListener.eventProcess(IEventType.ACTION_SUBTASK_CANCLE);
                }
            } catch (HDException e) {
                LogUtils.getLogger(HSEActionBar.class).error(e.getMessage(), e);
                try {
                    throw new HDException(e.getMessage());
                } catch (HDException e1) {
                    // TODO Auto-generated catch block
                    LogUtils.getLogger(HSEActionBar.class).error(
                            e.getMessage(), e1);
                }
            }
        }
    }

    private OnKeyListener etInputKeylistener = new OnKeyListener() {

        @Override
        public boolean onKey(View arg0, int keyCode, KeyEvent event) {
            // TODO Auto-generated method stub
            if (keyCode == KeyEvent.KEYCODE_ENTER
                    && event.getAction() == KeyEvent.ACTION_DOWN) {
                if (eventListener != null) {
                    try {
                        // 隐藏输入键盘
                        InputMethodManager imm = (InputMethodManager) activity
                                .getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(arg0.getWindowToken(), 0);
                        eventListener.eventProcess(
                                IEventType.ACTIONBAR_SEARCH_CLICK,
                                getSearchContent());
                    } catch (HDException e) {
                        try {
                            throw new HDException(e.getMessage());
                        } catch (HDException e1) {
                            LogUtils.getLogger(HSEActionBar.class).error(
                                    e.getMessage(), e);
                        }
                    }
                }
            }
            if (keyCode == KeyEvent.KEYCODE_DEL && event.getAction() == KeyEvent.ACTION_DOWN) {

                if (eventListener != null) {
                    try {
                        eventListener.eventProcess(
                                IEventType.ACTIONBAR_SEARCH_DELBUTTON);
                    } catch (HDException e) {
                        e.printStackTrace();
                    }
                }

            }
            return false;
        }
    };

    private OnTouchListener etSearch_OnTouch = new OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            // TODO Auto-generated method stub
            switch (event.getAction()) {
                case MotionEvent.ACTION_UP:
                    int curX = (int) event.getX();
                    if (curX > v.getWidth() - 38
                            && !TextUtils.isEmpty(etSearch.getText())) {

                        etSearch.setText("");
                        // 显示输入键盘
                        InputMethodManager imm = (InputMethodManager) activity
                                .getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.showSoftInput(v, 0);
                        try {
                            eventListener.eventProcess(
                                    IEventType.ACTIONBAR_SEARCH_CLICK,
                                    getSearchContent());
                        } catch (HDException e) {
                            LogUtils.getLogger(HSEActionBar.class).error(
                                    e.getMessage(), e);
                        }
                        return true;
                    }
                    break;
            }
            return false;
        }
    };

    private TextWatcher etSearch_TextChanged = new TextWatcher() {
        // 判断上一次文本框内容是否为空
        private boolean isNull = true;

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            // TODO Auto-generated method stub
            if (eventListener != null) {
                try {
                    eventListener.eventProcess(
                            IEventType.ACTIONBAR_SEARCH_INPUTCHANGED, s.toString(), start, count);
                } catch (HDException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
            // TODO Auto-generated method stub

        }

        @Override
        public void afterTextChanged(Editable content) {
            if (TextUtils.isEmpty(content)) {
                if (!isNull) {
                    // 隐藏清除
                    etSearch.setCompoundDrawablesRelativeWithIntrinsicBounds(
                            mIconSearch, null, null, null);
                    isNull = true;
                }
            } else {
                if (isNull) {
                    // 显示清除图标
                    etSearch.setCompoundDrawablesRelativeWithIntrinsicBounds(
                            mIconSearch, null, mIconSearchClear, null);
                    isNull = false;
                }
            }

        }
    };

    /**
     * showTitleInfoPopupWin:(显示导航栏作业票详细信息). <br/>
     * date: 2014年12月31日 <br/>
     *
     * @author wenlin
     */
    public void showTitleInfoPopupWin() {
        if (isOnline) {
            showWorkOrderPopupWin();
        } else {
            if (mAlertDialog == null && isIconTip) {
                mAlertDialog = new MyAlertDialog(activity,
                        R.style.workorder_dialog, superEntity, true);
            }
            if (isIconTip)
                mAlertDialog.show();
            // WindowManager.LayoutParams params = mAlertDialog.getWindow()
            // .getAttributes();
            // mAlertDialog.getWindow().setAttributes(params);
        }
    }

    /**
     * TODO 长按作业票图标显示作业票详细信息 showWorkOrderPopupWin:(). <br/>
     * date: 2015年3月2日 <br/>
     *
     * @author wenlin
     */
    public void showWorkOrderPopupWin() {
        List<SuperEntity> datalist = getShowPopupWinDate(superEntity);
        if (datalist == null) {
            return;
        }
        if (mShowDialog == null && isIconTip) {
            mShowDialog = new ShowPopWindowDialog(activity,
                    R.style.workorder_dialog);
        }
        if (isIconTip) {
            mShowDialog.setDataList(datalist);
            mShowDialog.show();
            WindowManager.LayoutParams params = mShowDialog.getWindow()
                    .getAttributes();
            params.height = 800;
            mShowDialog.getWindow().setAttributes(params);
        }
    }

    public void setDyTable(SuperEntity superEntity) {
        this.superEntity = superEntity;
    }

    public void setRmtWorkGasDetectResltList(List<RmtWorkGasDetectReslt> rmtWorkGasDetectResltList) {
        this.rmtWorkGasDetectResltList = rmtWorkGasDetectResltList;
    }

    /**
     * setControlVisible:(显示控件). date: 2014年10月9日
     *
     * @param controlsName
     * @author wenlin
     */
    public void setControlVisible(String[] controlsName) {
        setControl(controlsName, View.VISIBLE);
    }

    /**
     * setControlInVisible:(隐藏控件). date: 2014年10月9日
     *
     * @param controlsName
     * @author wenlin
     */
    public void setControlInVisible(String[] controlsName) {
        setControl(controlsName, View.GONE);
    }

    /**
     * controls:(控件是否显示属性设置). date: 2014年10月8日
     *
     * @param controlsVisible 控件数组
     * @param visible         是否显示
     * @author wenlin
     */
    private void setControl(String[] controlsVisible, int visible) {
        if (controlsVisible != null) {
            for (String controlName : controlsVisible) {
                if (controlName != null && controlMap.containsKey(controlName)) {
                    controlMap.get(controlName).setVisibility(visible);
                }
            }
        }
    }

    /**
     * setSearchVisible:(控制搜索框显示和隐藏). <br/>
     * date: 2015年3月18日 <br/>
     *
     * @param visible
     * @author lxf
     */
    public void setSearchVisible(boolean visible) {
        if (etSearch != null) {
            whenSearchActionbarStyle(visible, etSearch);
        }
    }

    /**
     * actionbarChanged:(搜索功能时导航栏的布局). <br/>
     * date: 2015年1月15日 <br/>
     *
     * @param etSearchVisible 搜索功能是否显示
     * @author wenlin
     */
    public void whenSearchActionbarStyle(Boolean etSearchVisible, View v) {
        if (etSearchVisible) {
            InputMethodManager imm = (InputMethodManager) activity
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            // 显示键盘
            imm.showSoftInput(v, 0);
            ibtnBack.setVisibility(View.GONE);
            etSearch.setVisibility(View.VISIBLE);
            tvCanel.setVisibility(View.VISIBLE);
            ibtnSearch.setVisibility(View.GONE);
            tvTitle.setVisibility(View.GONE);
            imageView.setVisibility(View.GONE);

        } else {
            etSearch.setVisibility(View.INVISIBLE);
            ibtnBack.setVisibility(View.GONE);
            tvCanel.setVisibility(View.INVISIBLE);
            ibtnSearch.setVisibility(View.VISIBLE);
            tvTitle.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.VISIBLE);
            InputMethodManager imm = (InputMethodManager) activity
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            // 隐藏输入键盘
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }

    }

    /**
     * setTitleContent:(设置导航栏标题内容). <br/>
     * date: 2015年1月6日 <br/>
     *
     * @param titleInfo 内容信息
     * @param isIconTip 是否显示下拉图标
     * @author wenlin
     */
    public void setTitleContent(String titleInfo, Boolean isIconTip) {
        if (titleInfo != null) {
            tvTitle.setText(titleInfo);
            this.isIconTip = isIconTip;
            setTitleClick();
        }
    }

    /**
     * setTitleContent:(设置导航栏标题内容). <br/>
     * date: 2015年1月6日 <br/>
     *
     * @param titleInfo 内容信息
     * @param isIconTip 是否显示下拉图标
     * @author wenlin
     */
    public void setTitleContent(String titleInfo, Boolean isIconTip, Boolean isonline) {
        if (titleInfo != null) {
            tvTitle.setText(titleInfo);
            this.isIconTip = isIconTip;
            this.isOnline = isonline;
            setTitleClick();
        }
    }

    /**
     * setSubTitleContent:(设置副标题内容). <br/>
     * date: 2015年1月7日 <br/>
     *
     * @param subTitleInfo
     * @author wenlin
     */
    public void setSubTitleContent(String subTitleInfo) {
        if (subTitleInfo != null)
            tvSubTitle.setText(subTitleInfo);
    }

    /**
     * setTitleClick:(设置标题是否可以点击，并弹出dialog). <br/>
     * date: 2015年1月14日 <br/>
     *
     * @author wenlin
     */
    public void setTitleClick() {
        if (isIconTip) {
            tvTitle.setClickable(true);
            tvTitle.setCompoundDrawablesWithIntrinsicBounds(
                    null,
                    null,
                    activity.getResources()
                            .getDrawable(
                                    R.drawable.hd_hse_common_phone_actionbar_custon_icon_subtitle),
                    null);
        } else {
            tvTitle.setEnabled(false);
        }
    }

    /**
     * getSearchContent:(返回输入的搜索内容). <br/>
     * date: 2015年1月15日 <br/>
     *
     * @return
     * @author wenlin
     */
    public String getSearchContent() {
        return etSearch.getText().toString();
    }

    private List<String[]> attrcode;
    private List<String[]> arrtnames;

    private List<SuperEntity> getShowPopupWinDate(SuperEntity superEntity) {

        List<SuperEntity> retSuper = new ArrayList<SuperEntity>();
        if (superEntity instanceof RmtWorkSubtask) {

            SuperEntity s = new DailogDataBean("任务信息", "", true);
            SuperEntity s1 = new DailogDataBean("作业名称:", ((RmtWorkSubtask) superEntity).getDescription());
            SuperEntity s2 = new DailogDataBean("属地单位:", ((RmtWorkSubtask) superEntity).getTerritorial_unit_name());
            SuperEntity s3 = new DailogDataBean("作业单位:", ((RmtWorkSubtask) superEntity).getWork_unit_name());
            SuperEntity s4 = new DailogDataBean("作业内容:", ((RmtWorkSubtask) superEntity).getDescription());
            SuperEntity s5 = new DailogDataBean("开始时间:", ((RmtWorkSubtask) superEntity).getEst_start_time());
            SuperEntity s6 = new DailogDataBean("结束时间:", ((RmtWorkSubtask) superEntity).getEst_end_time());
            retSuper.add(s);
            retSuper.add(s1);
            retSuper.add(s2);
            retSuper.add(s3);
            retSuper.add(s4);
            retSuper.add(s5);
            retSuper.add(s6);
            //得到气体检测数据
            List<SuperEntity> superEntities = getGasCheckData();
            if (superEntities != null && superEntities.size() > 0)
                retSuper.addAll(superEntities);
        } else if (superEntity instanceof RmtWorkTaskBean) {
            SuperEntity s = new DailogDataBean("任务信息", "", true);
            SuperEntity s2 = new DailogDataBean("属地单位:", ((RmtWorkTaskBean) superEntity).getTerritorial_unit_name());
            SuperEntity s3 = new DailogDataBean("作业单位:", ((RmtWorkTaskBean) superEntity).getWork_unit_name());
            SuperEntity s5 = new DailogDataBean("开始时间:", ((RmtWorkTaskBean) superEntity).getEst_start_time());
            SuperEntity s6 = new DailogDataBean("结束时间:", ((RmtWorkTaskBean) superEntity).getEst_end_time());
            retSuper.add(s);
            retSuper.add(s2);
            retSuper.add(s3);
            retSuper.add(s5);
            retSuper.add(s6);

        }
        return retSuper;
    }

    private List<SuperEntity> getGasCheckData() {

        if (rmtWorkGasDetectResltList != null && rmtWorkGasDetectResltList.size() > 0) {
            List<SuperEntity> retSuper = new ArrayList<SuperEntity>();
            SuperEntity s0 = new DailogDataBean("气体检测信息", "", true);
            retSuper.add(s0);
            for (RmtWorkGasDetectReslt rmtWorkGasDetectReslt : rmtWorkGasDetectResltList) {
                SuperEntity s = new DailogDataBean("检测时间:", rmtWorkGasDetectReslt.getDetect_time());
                SuperEntity s2 = new DailogDataBean("检测地点:", rmtWorkGasDetectReslt.getDetect_site());
                SuperEntity s3 = new DailogDataBean("是否合格:", rmtWorkGasDetectReslt.getQualified() + "");
                retSuper.add(s);
                retSuper.add(s2);
                retSuper.add(s3);
                for (RmtWorkGasDetectReslt.VoListBean voListBean : rmtWorkGasDetectReslt.getVoList()) {
                    SuperEntity s4 = new DailogDataBean("类型:", voListBean.getGas_type_sub_name());
                    SuperEntity s5 = new DailogDataBean("浓度:", voListBean.getGas_value());
                    retSuper.add(s4);
                    retSuper.add(s5);
                }
            }
            return retSuper;
        } else {
            return null;
        }
    }

}
