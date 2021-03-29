/**
 * Project Name:hse-common-component-phone
 * File Name:StepCheckPointListAdapter.java
 * Package Name:com.hd.hse.common.component.phone.adapter
 * Date:2015年6月1日
 * Copyright (c) 2015, fulibo@ushayden.com All Rights Reserved.
 */

package com.hd.hse.common.module.phone.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hd.hse.common.component.phone.event.IEventType;
import com.hd.hse.common.entity.SuperEntity;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.module.phone.R;
import com.hd.hse.entity.workorder.RmtWorkPermitMeas;

import java.util.List;

/**
 * ClassName:StepCheckPointListAdapter ().<br/>
 * Date: 2015年6月1日 <br/>
 *
 * @author flb
 * @see
 */
public class StepCheckPointListAdapter<T extends SuperEntity> extends SuperStepAdapter<T> implements OnClickListener {

    private int mLastPressedItem = -1;

    public StepCheckPointListAdapter(Context context, List<T> data, List<String> attrs) {
        super(context, data, attrs);
    }

    @Override
    public int getItemResource() {
        return R.layout.hd_hse_component_list_item_step_check_sample_1;
    }

    @Override
    public void handleViews(int position, SuperEntity t, SuperViewHolder holder) {
        LinearLayout rootView = holder.getView(R.id.step_check_root_ll);
        TextView leftTV = holder.getView(R.id.step_check_left_tv);
        TextView centerTV = holder.getView(R.id.step_check_center_tv);
        ImageView rightIV = holder.getView(R.id.step_check_right_iv);
        ImageView flagIV = holder.getView(R.id.step_check_left_iv_pc);

        rootView.setTag(position);
        leftTV.setTag(position);
        centerTV.setTag(position);
        rightIV.setTag(position);

        leftTV.setTag(R.string.hd_hse_moduel_step_desc_attr_position, 0);
        leftTV.setTag(R.string.hd_hse_moduel_step_status_attr_position, 2);
        leftTV.setTag(R.string.hd_hse_moduel_step_gas_attr_position, 5);
        centerTV.setTag(R.string.hd_hse_moduel_step_desc_attr_position, 1);
        centerTV.setTag(R.string.hd_hse_moduel_step_gas_attr_position, 5);

        String desc = (String) t.getAttribute(attrs.get(0));
        Integer confirm = (Integer) t.getAttribute(attrs.get(1));
        Integer isselect = (Integer) t.getAttribute(attrs.get(2));
        Boolean isselectitem = ((RmtWorkPermitMeas) t).getIsselectitem();

        if (!TextUtils.isEmpty(desc)) {
            leftTV.setText(desc);
        } else {
            leftTV.setText("");
        }


//		if(status == null || status == -1){
//			rightIV.setVisibility(View.INVISIBLE);
//			rightIV.setImageResource(iconResLight[0]);
//		}else{
//			rightIV.setVisibility(View.VISIBLE);
//			rightIV.setImageResource(iconResLight[status]);
//		}

//        if (flag == null || flag == 0) {
//            flagIV.setVisibility(View.GONE);
//        } else {
//            flagIV.setVisibility(View.VISIBLE);
//        }

        if (position == mLastPressedItem) {
            rootView.setBackgroundResource(R.drawable.hd_component_step_check_item_bg_selected_current);
            leftTV.setTextColor(context.getResources().getColor(R.color.step_check_text_color_selected));
        } else {
            rootView.setBackgroundResource(R.drawable.hd_component_step_check_item_bg_normal);
            leftTV.setTextColor(context.getResources().getColor(R.color.step_check_text_color_normal));
        }


        if (isselectitem) {

            rightIV.setVisibility(View.VISIBLE);
            rightIV.setImageResource(iconResLight[1]);

        } else {
            rightIV.setVisibility(View.INVISIBLE);

//			if(t.isModified()){
//				rootView.setBackgroundResource(R.drawable.hd_component_step_check_item_bg_selected);
//				leftTV.setTextColor(context.getResources().getColor(R.color.step_check_text_color_selected));
//			}else{
//				if(flag == null || flag == 0){
//					rootView.setBackgroundResource(R.drawable.hd_component_step_check_item_bg_normal);
//				}else{
//					rootView.setBackgroundResource(R.drawable.hd_component_step_check_item_bg_normal);
//				}
//				leftTV.setTextColor(context.getResources().getColor(R.color.step_check_text_color_normal));
//			}
        }


        //confirm开始的值是Null的
        //选中后的项值为0
        //点击审核按钮后为1
        if (confirm != null) {

            if (confirm == 1) {

//                if (isselect != null) {
//                    if (isselect == 1) {
//                        //选中
//                        rightIV.setVisibility(View.VISIBLE);
//                        rightIV.setImageResource(iconResLight[1]);
//                    } else if (isselect == 0) {
//                        //不选中
//                        rightIV.setVisibility(View.INVISIBLE);
//                    }
//                }

                rightIV.setVisibility(View.VISIBLE);
                rightIV.setImageResource(iconResLight[1]);

            }
//            else if (confirm==0){
//                rightIV.setVisibility(View.INVISIBLE);
//            }

        }


        leftTV.setOnLongClickListener(this);
//		centerTV.setOnLongClickListener(this);

        leftTV.setOnClickListener(this);
        centerTV.setOnClickListener(this);
        rightIV.setOnClickListener(this);
        rootView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Integer position = (Integer) view.getTag();
        SuperEntity t = sourceData.get(position);
        boolean isSel = ((RmtWorkPermitMeas) t).getIsselectitem();
        if (isSel) {
            ((RmtWorkPermitMeas) t).setIsselectitem(false);
        } else {
            ((RmtWorkPermitMeas) t).setIsselectitem(true);
        }

        //只是为了点击审核按钮后选中每项的操作
        ((RmtWorkPermitMeas) t).setConfirm(0);
        mLastPressedItem = position;

        notifyDataSetChanged();

        if (listener != null) {
            try {
                listener.eventProcess(IEventType.ACTION_POINTER_CHECK_STATUS, position);
            } catch (HDException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void eventProcess(int action, Object... obj) throws HDException {
        if (action == IEventType.STEP_LIST_DIALOG_CLICK) {
            List source = (List) obj[0];
            setSourceValues(source);
            notifyDataSetChanged();
        }
    }
}
