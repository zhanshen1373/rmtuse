/**
 * Project Name:hse-common-component-phone
 * File Name:StepDialogManager.java
 * Package Name:com.hd.hse.common.component.phone.custom
 * Date:2015年6月2日
 * Copyright (c) 2015, fulibo@ushayden.com All Rights Reserved.
 */

package com.hd.hse.common.module.phone.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hayden.hap.common.utils.ToastUtil;
import com.hd.hse.common.component.phone.custom.StepCheckEditText;
import com.hd.hse.common.component.phone.event.IEventType;
import com.hd.hse.common.component.phone.listener.IEventListener;
import com.hd.hse.common.entity.SuperEntity;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.module.phone.R;
import com.hd.hse.common.module.phone.adapter.StepBottomBar.OnCheckedResultChangedListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * ClassName:StepDialogManager ().<br/>
 * Date:     2015年6月2日  <br/>
 *
 * @author flb
 * @see
 */
public class StepDialogManager implements OnClickListener, OnPageChangeListener, StepCheckEditText.AfterTextChangedListener, OnCheckedResultChangedListener {

    private Context context;
    private LayoutInflater inflater;

    private AlertDialog dialog;
    private View rootView;
    private IEventListener listener;

    private ViewPager viewPager;
    private ViewPagerAdapter pagerAdapter;

    private List<SuperEntity> source;
    private List<SuperEntity> oldsource;
    private List<String> attrs;
    private int position;

    private StepDialogManager() {
    }

    private static StepDialogManager manager;

    public static StepDialogManager getInstance() {
        if (manager == null) {
            manager = new StepDialogManager();
        }
        return manager;
    }

    public AlertDialog showDialog(Context context, List<? extends SuperEntity> entities, int position, List<String> attrs) {
        this.context = context;
        oldsource = (List<SuperEntity>) entities;
        source = new ArrayList<SuperEntity>();
        for (SuperEntity entity : entities) {
            try {
                source.add(entity.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }

        this.position = position;
        this.attrs = attrs;

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        dialog = builder.create();

        inflater = LayoutInflater.from(context);
        rootView = inflater.inflate(R.layout.hd_hse_common_component_check_text_dialog_style_three, null);
        viewPager = (ViewPager) rootView.findViewById(R.id.vp_dialog_step);
        pagerAdapter = new ViewPagerAdapter();
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(this.position);
        viewPager.setOnPageChangeListener(this);

        TextView confirmTV = (TextView) rootView.findViewById(R.id.tv_dialog_step_confirm);
        TextView cancelTV = (TextView) rootView.findViewById(R.id.tv_dialog_step_cancel);
        confirmTV.setOnClickListener(this);
        cancelTV.setOnClickListener(this);

        dialog.setView(rootView, 0, 0, 0, 0);

        if (dialog != null) {
//			dialog.setOnShowListener(new OnShowListener() {
//				public void onShow(DialogInterface dialog) {
//					InputMethodManager imm = (InputMethodManager) (StepDialogManager.this.context.getSystemService(Context.INPUT_METHOD_SERVICE));
//					imm.showSoftInput(et_dialog_confirmphoneguardpswd, InputMethodManager.SHOW_IMPLICIT);
//				}
//			});
            dialog.show();
        }

        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        return dialog;
    }

    public void dismissDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            dialog = null;
        }
    }

    public void setOnIEventListener(IEventListener listener) {
        this.listener = listener;
    }

    private boolean checkContent(String content) {
        if (content.contains("(") || content.contains("（")) {
            Stack<Character> stack = new Stack<Character>();
            char[] cArray = content.toCharArray();
            for (int i = 0; i < cArray.length; i++) {
                char c = cArray[i];
                if (c != '\0') {
                    if (c == '(' || c == '（') {
                        stack.push(c);
                    } else if (c == ')') {
                        if (!stack.empty() && stack.peek() == '(') {
                            stack.pop();
                            continue;
                        } else {
                            return false;
                        }
                    } else if (c == '）') {
                        if (!stack.empty() && stack.peek() == '（') {
                            stack.pop();
                            continue;
                        } else {
                            return false;
                        }
                    }
                }
            }

            if (stack.empty()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }


    private class ViewPagerAdapter extends PagerAdapter {

        private SparseArray<View> views;

        public ViewPagerAdapter() {
            views = new SparseArray<View>();
        }

        @Override
        public int getCount() {
            return source != null ? source.size() : 0;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            SuperEntity entity = source.get(position);
            final String content = (String) (entity.getAttribute(attrs.get(0)));
            Integer status = (Integer) (entity.getAttribute(attrs.get(1)));
            View view = inflater.inflate(R.layout.hd_hse_common_component_step_dialog_content, container, false);
            RelativeLayout descContainer = (RelativeLayout) view.findViewById(R.id.flyt_dialog_content_desc_container);
            TextView textView = null;

            if (!TextUtils.isEmpty(content)) { //不为null或者""
                if (checkContent(content)) { //有括号
                    Log.i("BracketEditText", "有括号");
                    textView = new StepCheckEditText(context);
                    ((StepCheckEditText) textView).setHasBracketsText(content);
                    ((StepCheckEditText) textView).setOnAfterTextChangedListener(StepDialogManager.this);
                } else {   //无括号
                    textView = new TextView(context);
                    textView.setText(content);
                }
            } else { //内容为null或""
                textView = new TextView(context);
                textView.setText("");
            }
            textView.setGravity(Gravity.CENTER);

            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            params.addRule(RelativeLayout.CENTER_IN_PARENT);
            //params.
            textView.setLayoutParams(params);
            textView.setMovementMethod(ScrollingMovementMethod.getInstance());
            textView.setBackgroundColor(Color.TRANSPARENT);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.getResources().getDimensionPixelOffset(R.dimen.hd_hse_common_component_phone_editable_dialog_text));
            textView.setTextColor(context.getResources().getColor(R.color.hd_hse_common_alerttext_black));
            descContainer.addView(textView);

            ImageView leftIV = (ImageView) view.findViewById(R.id.iv_step_dialog_arrow_left);
            ImageView rightIV = (ImageView) view.findViewById(R.id.iv_step_dialog_arrow_right);
            if (position == 0) {
                leftIV.setVisibility(View.INVISIBLE);
            }

            if (position == (source.size() - 1)) {
                rightIV.setVisibility(View.INVISIBLE);
            }

            leftIV.setOnClickListener(StepDialogManager.this);
            rightIV.setOnClickListener(StepDialogManager.this);

            StepBottomBar sbb = (StepBottomBar) view.findViewById(R.id.sbb_dialog_content_botton_bar);
            sbb.setOnCheckedResultChangedListener(StepDialogManager.this);


            sbb.setShowCheckedButtonAll();


            if (status == null || status == -1) {
                sbb.setChecked(-1);
            } else {
                sbb.setChecked(status);

            }

            container.addView(view);
            views.put(position, view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(views.get(position));
        }
    }

    @Override
    public void onClick(View v) {
        int vid = v.getId();
        if (vid == R.id.iv_step_dialog_arrow_left) {
            position -= 1;
            viewPager.setCurrentItem(position);
        } else if (vid == R.id.iv_step_dialog_arrow_right) {
            position += 1;
            viewPager.setCurrentItem(position);
        } else if (vid == R.id.tv_dialog_step_confirm) {
            Message msg = handler.obtainMessage();
            msg.what = EXAMIN_ALL_GAS_STEP;
            msg.sendToTarget();
        } else if (vid == R.id.tv_dialog_step_cancel) {
            dismissDialog();
        }
    }

    @Override
    public void onPageSelected(int position) {
        this.position = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void afterTextChanged(String content, List<String> splitContent) {
        SuperEntity entity = source.get(position);
        entity.setAttribute(attrs.get(0), content);
    }

    @Override
    public void onCheckedResultChanged(int result) {
        if (result != -1) {
            source.get(position).setAttribute(attrs.get(1), result);
        }
    }

    private static final int EXAMIN_ALL_GAS_STEP = 0x11;
    private Handler handler = new Handler() {

        @SuppressWarnings("unchecked")
        @Override
        public void handleMessage(Message msg) {
            int flag = msg.what;
            if (flag == EXAMIN_ALL_GAS_STEP) {
                if (source.size() == oldsource.size()) {
                    for (int i = 0; i < source.size(); i++) {
                        SuperEntity entity = source.get(i);
                        SuperEntity entity2 = oldsource.get(i);
                        entity2.setAttribute("confirm",entity.getAttribute("confirm"));
                        entity2.setAttribute("meas_description",entity.getAttribute("meas_description"));
                    }
                }else {
                    ToastUtil.toast(context,"数据有误，请联系管理员");
                }
                if (listener != null) {
                    try {
                        listener.eventProcess(IEventType.STEP_LIST_DIALOG_CLICK, new Object[]{oldsource});
                    } catch (HDException e) {
                        e.printStackTrace();
                    }
                    dismissDialog();
                }
            }
        }
    };

    public static boolean isNumeric(String content) {
        if (content == null || "".equals(content.trim())) {
            return false;
        }

        String str = content.replaceFirst("\\.", "");

        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }

        return true;
    }
}

