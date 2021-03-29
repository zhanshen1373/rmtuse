package com.hd.hse.main.phone.ui.activity.main;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.hayden.hap.common.common.bussiess.HttpBusiness;
import com.hayden.hap.common.common.bussiess.HttpCallBack;
import com.hayden.hap.common.common.bussiess.ResultData;
import com.hayden.hap.common.utils.RetrofitUtil;
import com.hayden.hap.common.utils.ToastUtil;
import com.hd.hse.common.business.web_interface.RmtInterface;
import com.hd.hse.common.component.phone.custom.ProgressDialog;
import com.hd.hse.common.module.phone.ui.activity.WebActivity;
import com.hd.hse.entity.workorder.RmtMainPageSchedule;
import com.hd.hse.entity.workorder.TaskUrl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import retrofit2.Call;

public class Zdyview extends LinearLayout {

    private Context mContext;
    private Paint p;
    private String url;
    private List<RmtMainPageSchedule> list;


    public Zdyview(Context context) {
        super(context);
    }


    public Zdyview(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        this.mContext = context;
    }

    public Zdyview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        final int width = getMeasuredWidth();


        //子view
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            int measuredHeight = childAt.getMeasuredHeight();
            getChildAt(i).layout(0, 0 + measuredHeight * i, width, measuredHeight * (i + 1));
        }


    }


    private void init() {
        setOrientation(VERTICAL);
        p = new Paint();
        p.setColor(Color.BLUE);
        p.setStyle(Paint.Style.STROKE);
        p.setAntiAlias(true);
    }

    public void initEvent() {

        for (int jj = 0; jj < getChildCount(); jj++) {
            ViewGroup childAt = (ViewGroup) getChildAt(jj);
            if (jj != 0 && jj != getChildCount() - 1) {
                final int finalJj = jj;
                childAt.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String territorial_unit_id = list.get(finalJj).getTerritorial_unit_id();
                        final long l = Long.parseLong(territorial_unit_id);
                        final ProgressDialog dialog = new ProgressDialog(mContext,
                                "正在查询表单...");
                        dialog.show();

                        final RmtInterface rmtInterface = RetrofitUtil.createInterface(RmtInterface.class);
                        // 单张票报表的接口
                        Call<ResultData<TaskUrl>> call = rmtInterface.getNextPageSchedule(l);
                        HttpBusiness.action(mContext, false, call, new HttpCallBack<TaskUrl>() {
                            @Override
                            public void success(TaskUrl rmtTaskListQuery) {
                                dialog.dismiss();

                                try {
                                    url = URLDecoder.decode(rmtTaskListQuery.getUrl(), "utf-8");
                                    url=url+"&territorial_unit_id="+l;
                                } catch (UnsupportedEncodingException e) {
                                    e.printStackTrace();
                                }

                                Intent intent = new Intent(mContext, WebActivity.class);
                                intent.putExtra(WebActivity.TITLE,"报表");
                                intent.putExtra(WebActivity.URL, url);
                                mContext.startActivity(intent);
                            }

                            @Override
                            public void warning(String msg) {
                                dialog.dismiss();
                                ToastUtil.toast(mContext, msg);
                            }

                            @Override
                            public void error(Throwable t) {
                                dialog.dismiss();
                                ToastUtil.toast(mContext, "获取数据列表失败");
                            }
                        });


                    }
                });
            }

        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        //画框
//        for (int i = 0; i < getChildCount(); i++) {
//            ViewGroup childAt = (ViewGroup) getChildAt(i);
//            for (int t = 0; t < childAt.getChildCount(); t++) {
//                View childAt1 = childAt.getChildAt(t);
//                int measuredHeight = childAt1.getMeasuredHeight();
//                int measuredWidth = childAt1.getMeasuredWidth();
//                canvas.drawRect(new Rect(0 + t * measuredWidth, 0 + i * measuredHeight, (t + 1) * measuredWidth, (i + 1) * measuredHeight), p);
//            }
//        }


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
//                MeasureSpec.AT_MOST);
//        super.onMeasure(expandSpec, expandSpec);
//        setMeasuredDimension(widthMeasureSpec,heightMeasureSpec);
//        getChildAt(0).measure(getChildAt(0).getWidth(),getChildAt(0).getHeight());
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void setData(List<RmtMainPageSchedule> list) {
        this.list = list;

    }
}
