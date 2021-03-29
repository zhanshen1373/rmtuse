package com.hd.hse.common.module.phone.custom;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hd.hse.common.component.phone.event.IEventType;
import com.hd.hse.common.component.phone.listener.IEventListener;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.logger.LogUtils;
import com.hd.hse.common.module.phone.R;
import com.hd.hse.entity.common.ReMindBean;

import org.apache.log4j.Logger;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class NoticeListView extends ListView implements OnScrollListener {
    private static Logger logger = LogUtils.getLogger(NoticeListView.class);

    private final static int RELEASE_TO_REFRESH = 0; // 下拉过程的状态值
    private final static int PULL_TO_REFRESH = 1; // 从下拉返回到不刷新状态值
    private final static int REFRESHING = 2; // 正在刷新的状态值
    private final static int DONE = 3;
    private final static int LOADING = 4;

    private Context context;

    // 实际的padding的距离与界面偏移距离的比例
    private final static int RATIO = 3;
    private LayoutInflater inflater;

    // ListView 头部下拉刷新的布局
    private LinearLayout headerView;
    private TextView lvHeaderTipsTv;
    // private TextView lvHeaderLastUpdatedTv;
    private ImageView lvHeaderImg;
    private ProgressBar lvheaderprsBar;

    // 定义头部下拉刷新的布局的高度
    private int headerContentHeight;

    private RotateAnimation animation;
    private RotateAnimation reverseAnimation;
    private AnimationSet outAnimation;

    private int startY;
    private int state;
    private boolean isBack;
    // 用于保证startY的值在一个完整的touch事件中只被记录一次
    private boolean isRecored;

    private OnRefreshListener refreshListener;

    private boolean isRefreshable;

    /**
     * 任务集合
     */
//	private List<PushMessage> listDatas;
    private List<String> bodyList;

    private List<String> titleList;

    private List<ReMindBean.ContentBean> listDatas;

    private List<ReMindBean.ContentBean> whichchosed;

    private List<String> chosedBodyList;

    private List<String> chosedTitleList;

    private List<Boolean> checkList;

    private boolean clean;
    /**
     * 自定义适配器
     */
    private BaseAdepterImlp adapter;
    /**
     * 自定义时间格式
     */
    private SimpleDateFormat formatDate;
    /**
     * 时间和日期
     */
    private String date, time;

    private Resources resources;
    /**
     * 无数据时提示
     */
    public TextView noDatasText;
    /**
     * 回调
     */
    private IEventListener iEventLsn;
    private boolean allchose;


    public NoticeListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        init();
    }

    public NoticeListView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public NoticeListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {
        if (firstVisibleItem == 0) {
            isRefreshable = true;
        } else {
            isRefreshable = false;
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        adapter.closeAllItems();
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (isRefreshable) {
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if (!isRecored) {
                        isRecored = true;
                        startY = (int) ev.getY();// 手指按下时记录当前位置
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    if (state != REFRESHING && state != LOADING) {
                        if (state == PULL_TO_REFRESH) {
                            state = DONE;
                            changeHeaderViewByState();
                        }
                        if (state == RELEASE_TO_REFRESH) {
                            state = REFRESHING;
                            changeHeaderViewByState();
                            onLvRefresh();
                        }
                    }
                    isRecored = false;
                    isBack = false;

                    break;

                case MotionEvent.ACTION_MOVE:
                    int tempY = (int) ev.getY();
                    if (!isRecored) {
                        isRecored = true;
                        startY = tempY;
                    }
                    if (state != REFRESHING && isRecored && state != LOADING) {
                        // 保证在设置padding的过程中，当前的位置一直是在head，否则如果当列表超出屏幕的话，当在上推的时候，列表会同时进行滚动
                        // 可以松手去刷新了
                        if (state == RELEASE_TO_REFRESH) {
                            setSelection(0);
                            // 往上推了，推到了屏幕足够掩盖head的程度，但是还没有推到全部掩盖的地步
                            if (((tempY - startY) / RATIO < headerContentHeight)// 由松开刷新状态转变到下拉刷新状态
                                    && (tempY - startY) > 0) {
                                state = PULL_TO_REFRESH;
                                changeHeaderViewByState();
                            }
                            // 一下子推到顶了
                            else if (tempY - startY <= 0) {// 由松开刷新状态转变到done状态
                                state = DONE;
                                changeHeaderViewByState();
                            }
                        }
                        // 还没有到达显示松开刷新的时候,DONE或者是PULL_To_REFRESH状态
                        if (state == PULL_TO_REFRESH) {
                            setSelection(0);
                            // 下拉到可以进入RELEASE_TO_REFRESH的状态
                            if ((tempY - startY) / RATIO >= headerContentHeight) {// 由done或者下拉刷新状态转变到松开刷新
                                state = RELEASE_TO_REFRESH;
                                isBack = true;
                                changeHeaderViewByState();
                            }
                            // 上推到顶了
                            else if (tempY - startY <= 0) {// 由DOne或者下拉刷新状态转变到done状态
                                state = DONE;
                                changeHeaderViewByState();
                            }
                        }
                        // done状态下
                        if (state == DONE) {
                            if (tempY - startY > 0) {
                                state = PULL_TO_REFRESH;
                                changeHeaderViewByState();
                            }
                        }
                        // 更新headView的size
                        if (state == PULL_TO_REFRESH) {
                            headerView.setPadding(0, -1 * headerContentHeight
                                    + (tempY - startY) / RATIO, 0, 0);

                        }
                        // 更新headView的paddingTop
                        if (state == RELEASE_TO_REFRESH) {
                            headerView.setPadding(0, (tempY - startY) / RATIO
                                    - headerContentHeight, 0, 0);
                        }

                    }
                    break;

                default:
                    break;
            }
        }
        return super.onTouchEvent(ev);
    }

    public List<ReMindBean.ContentBean> getDatas() {
        return listDatas;
    }

    public void setDatas(List<Boolean> checkList, List<ReMindBean.ContentBean> noticList, List<String> bodylist, List<String> titlelist) {
        this.checkList = checkList;
        this.listDatas = noticList;
        this.bodyList = bodylist;
        this.titleList = titlelist;
        adapter.notifyDataSetChanged();
    }

    public IEventListener getiEventLsn() {
        return iEventLsn;
    }

    public void setiEventLsn(IEventListener iEventLsn) {
        this.iEventLsn = iEventLsn;
    }

    /**
     * 初始化数据
     */
    private void init() {
        whichchosed = new ArrayList<>();
        chosedBodyList = new ArrayList<>();
        chosedTitleList = new ArrayList<>();
        resources = context.getResources();
        inflater = LayoutInflater.from(context);
        headerView = (LinearLayout) inflater.inflate(
                R.layout.hd_hse_common_refresh_header, null);
        lvHeaderTipsTv = (TextView) headerView
                .findViewById(R.id.hd_hse_common_refresh_lvHeaderTipsTv);
        lvHeaderImg = (ImageView) headerView
                .findViewById(R.id.hd_hse_common_refresh_lvHeaderArrowIv);
        lvheaderprsBar = (ProgressBar) headerView
                .findViewById(R.id.hd_hse_common_refresh_lvHeaderProgressBar);
        // 设置下拉刷新图标的最小高度和宽度
        measureView(headerView);
        headerContentHeight = headerView.getMeasuredHeight();
        // 设置内边距，正好距离顶部为一个负的整个布局的高度，正好把头部隐藏
        headerView.setPadding(0, -1 * headerContentHeight, 0, 0);
        // 重绘一下
        headerView.invalidate();
        // 将下拉刷新的布局加入ListView的顶部
        addHeaderView(headerView, null, false);
        // 设置滚动监听事件
        setOnScrollListener(this);

        // 设置旋转动画事件
        animation = new RotateAnimation(0, -180,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        animation.setInterpolator(new LinearInterpolator());
        animation.setDuration(250);
        animation.setFillAfter(true);

        reverseAnimation = new RotateAnimation(-180, 0,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        reverseAnimation.setInterpolator(new LinearInterpolator());
        reverseAnimation.setDuration(200);
        reverseAnimation.setFillAfter(true);

        outAnimation = new AnimationSet(true);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
        alphaAnimation.setDuration(500);
        outAnimation.addAnimation(alphaAnimation);

        // 一开始的状态就是下拉刷新完的状态，所以为DONE
        state = DONE;
        // 是否正在刷新
        isRefreshable = false;

        formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        resources = this.getResources();
        adapter = new BaseAdepterImlp();

        setAdapter(adapter);
        this.setSelector(R.color.hd_hse_common_grey);
        noDatasText = new TextView(getContext());
        this.setOnItemClickListener(onItemLsn);
    }

    // 当状态改变时候，调用该方法，以更新界面
    private void changeHeaderViewByState() {
        switch (state) {
            case RELEASE_TO_REFRESH:
                lvHeaderImg.setVisibility(View.VISIBLE);
                lvheaderprsBar.setVisibility(View.GONE);
                lvHeaderTipsTv.setVisibility(View.VISIBLE);

                lvHeaderImg.clearAnimation();// 清除动画
                lvHeaderImg.startAnimation(animation);// 开始动画效果

                lvHeaderTipsTv.setText("松开刷新");
                break;
            case PULL_TO_REFRESH:
                lvheaderprsBar.setVisibility(View.GONE);
                lvHeaderTipsTv.setVisibility(View.VISIBLE);
                lvHeaderImg.clearAnimation();
                lvHeaderImg.setVisibility(View.VISIBLE);
                // 是由RELEASE_To_REFRESH状态转变来的
                if (isBack) {
                    isBack = false;
                    lvHeaderImg.clearAnimation();
                    lvHeaderImg.startAnimation(reverseAnimation);

                    lvHeaderTipsTv.setText("下拉刷新");
                } else {
                    lvHeaderTipsTv.setText("下拉刷新");
                }
                break;

            case REFRESHING:
                headerView.setPadding(0, 0, 0, 0);
                lvheaderprsBar.setVisibility(View.VISIBLE);
                lvHeaderImg.clearAnimation();
                lvHeaderImg.setVisibility(View.GONE);
                lvHeaderTipsTv.setText("正在刷新...");
                break;
            case DONE:

                ObjectAnimator anim = null;
                if (anim == null) {
                    anim = ObjectAnimator.ofFloat(headerView, "", 0.5f);
                }
                anim.setDuration(2000);
                anim.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        headerView.setPadding(0, -1 * headerContentHeight, 0, 0);
                    }
                });
                anim.start();
                lvheaderprsBar.setVisibility(View.GONE);
                lvHeaderImg.setImageResource(R.drawable.hd_hse_common_arrow);
                break;
        }
    }

    public void setonRefreshListener(OnRefreshListener refreshListener) {
        this.refreshListener = refreshListener;
        isRefreshable = true;
    }

    public void isAllChose(boolean allchose) {
//        this.allchose = allchose;
        for (int i = 0; i < listDatas.size(); i++) {
            whichchosed.add(listDatas.get(i));
            chosedBodyList.add(bodyList.get(i));
            chosedTitleList.add(titleList.get(i));
            checkList.set(i,true);
        }
        adapter.notifyDataSetChanged();
    }


    public void isClean(boolean t) {
        this.clean = t;
        if (listDatas != null && listDatas.size() > 0) {
            if (whichchosed.size() > 0) {
                for (int i = 0; i < whichchosed.size(); i++) {
                    listDatas.remove(whichchosed.get(i));
                    bodyList.remove(chosedBodyList.get(i));
                    titleList.remove(chosedTitleList.get(i));
                    checkList.remove(true);

                }
                whichchosed.clear();
                chosedBodyList.clear();
                chosedTitleList.clear();
                try {
                    iEventLsn.eventProcess(IEventType.ACTION_CLEAN_CHOSE_MESSAGE,
                            listDatas.size(), listDatas, bodyList, titleList);
                } catch (HDException e) {
                    e.printStackTrace();
                }
                adapter.notifyDataSetChanged();

            }
        }
    }

    public interface OnRefreshListener {
        public void onRefresh();
    }

    public void onRefreshStart() {
        state = REFRESHING;
        changeHeaderViewByState();
    }

    /**
     * 刷新完成
     *
     * @param finishMsg
     */
    public void onRefreshComplete(String finishMsg) {
        state = DONE;
        // lvHeaderLastUpdatedTv.setText("最近更新:" + new Date().toLocaleString());
        lvHeaderTipsTv.setText(finishMsg);
        changeHeaderViewByState();
    }

    private void onLvRefresh() {
        if (refreshListener != null) {
            refreshListener.onRefresh();
        }
    }

    // 此处是“估计”headView的width以及height
    private void measureView(View child) {
        ViewGroup.LayoutParams params = child.getLayoutParams();
        if (params == null) {
            params = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0 + 0,
                params.width);
        int lpHeight = params.height;
        int childHeightSpec;
        if (lpHeight > 0) {
            childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight,
                    MeasureSpec.EXACTLY);
        } else {
            childHeightSpec = MeasureSpec.makeMeasureSpec(0,
                    MeasureSpec.UNSPECIFIED);
        }
        child.measure(childWidthSpec, childHeightSpec);
    }

    /**
     * item的点击事件监听
     */
    OnItemClickListener onItemLsn = new OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> arg0, View view, int position,
                                long arg3) {
            try {
                if (iEventLsn != null && listDatas != null
                        && listDatas.size() > position - 1) {
                    whichchosed.clear();
                    chosedBodyList.clear();
                    chosedTitleList.clear();
                    if (listDatas.get(position - 1) != null) {
                        iEventLsn.eventProcess(IEventType.NOTICE_LIST_CLICK,
                                listDatas.get(position - 1), listDatas.get(position - 1).getDataid(),
                                titleList.get(position - 1), bodyList.get(position - 1), position - 1);
                    }
                    if (listDatas.get(position - 1) == null) {
                        iEventLsn.eventProcess(IEventType.NOTICE_TIMING_CLICK,
                                titleList.get(position - 1), bodyList.get(position - 1), position - 1);
                    }

                } else {
                    NoticeListView.this.onRefreshComplete("刷新未完成");
                }
            } catch (HDException e) {
                logger.error("消息列表回调错误：" + e.getMessage());
            }
        }
    };

    private class BaseAdepterImlp extends BaseSwipeAdapter {

        @Override
        public int getCount() {
            if (listDatas != null && listDatas.size() > 0) {
                return listDatas.size();
            } else {
                return 0;

            }

        }

        @Override
        public Object getItem(int position) {
            if (listDatas != null && listDatas.size() > 0) {
                return listDatas.get(position);
            }
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getSwipeLayoutResourceId(int position) {
            if (listDatas == null || listDatas.size() == 0) {
                return 0;
            }
            return R.id.swipe;
        }

        /**
         * ClassName: deleteOnClickListener (添加删除方法)<br/>
         * date: 2015年7月16日  <br/>
         *
         * @author lxf
         * @version NoticeListView.BaseAdepterImlp
         */
//		public class deleteOnClickListener implements OnClickListener {
//			private int position = -1;
//			public deleteOnClickListener(int position) {
//				this.position = position;
//			}
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				if (position == -1) {
//					return;
//				}
////				PushMessage pushMessage = listDatas.get(position);
//				try {
//					ManagerInstance
//							.getInstance()
//							.getActionInstance()
//							.action(BusinessActionNum.ACTION_NEWS_DELTET,
//									new ICallBack() {
//
//										private ProgressDialog mProgressDialog = new ProgressDialog(
//												getContext(), "正在删除...");
//
//										@Override
//										public void start(String action,
//												int flag, Object... objects) {
//											if (!mProgressDialog.isShowing()) {
//												mProgressDialog.show();
//											}
//										}
//
//										@Override
//										public void process(String action,
//												int flag, Object... objects) {
//
//										}
//
//										@Override
//										public void error(String action,
//												int flag, Object... objects) {
//											if (mProgressDialog.isShowing()) {
//												mProgressDialog.dismiss();
//											}
//											if (objects != null) {
//												ToastUtils
//														.imgToast(
//																getContext(),
//																R.drawable.hd_common_message_error,
//																objects[0]
//																		.toString());
//											} else {
//												ToastUtils
//														.imgToast(
//																getContext(),
//																R.drawable.hd_common_message_error,
//																objects[0]
//																		.toString());
//											}
//										}
//										@Override
//										public void end(String action,
//												int flag, Object... objects) {
//											listDatas.remove(position);
//											if(adapter != null){
//												adapter.notifyDatasetChanged();
//											}
//											if (mProgressDialog.isShowing()) {
//												mProgressDialog.dismiss();
//											}
//											ToastUtils.toast(getContext(),
//													"删除成功！");
//											notifyDatasetChanged();
//										}
//									}, pushMessage);
//				} catch (HDException e) {
//					e.printStackTrace();
//				}
//			}
//
//		}
        @Override
        public View generateView(final int position, ViewGroup parent) {
            View view = null;
            // 当没有数据时列表显示暂无数据
            if (listDatas == null || listDatas.size() == 0) {
                noDatasText.setText("暂无数据");
                view = noDatasText;
                // 当有数据时加载数据
            } else {
                ViewHolder holder = null;
                view = View.inflate(getContext(),
                        R.layout.hd_hse_main_task_list_item, null);
                holder = new ViewHolder();
                holder.dateText = (TextView) view
                        .findViewById(R.id.hd_hse_main_task_date);
                holder.timeText = (TextView) view
                        .findViewById(R.id.hd_hse_main_task_time);
                holder.contentText = (TextView) view
                        .findViewById(R.id.hd_hse_main_task_content);
                holder.line = view
                        .findViewById(R.id.hd_hse_main_task_spit_line);
                holder.swipeLayout = (SwipeLayout) view
                        .findViewById(R.id.swipe);
                holder.checkBox = (CheckBox) view.findViewById(R.id.hd_hse_main_task_checkbox);
                view.setTag(holder);

            }

            return view;
        }


        public void fillValues(final int position,View convertView) {
            ViewHolder holder = null;
            if (convertView instanceof SwipeLayout) {

                holder = (ViewHolder) convertView.getTag();

//				PushMessage msg = listDatas.get(position);
                ReMindBean.ContentBean rcbean = listDatas.get(position);

                try {
                    Date dateTime = null;
                    if (rcbean != null) {
                        if (rcbean.getSendTime() != null) {
                            dateTime = formatDate.parse(rcbean.getSendTime());
                        }
                    }

                    Calendar cld = Calendar.getInstance();
                    if (dateTime != null) {
                        cld.setTime(dateTime);
                    }
//                    cld.get(Calendar.DAY_OF_MONTH)
                    String formatMonth = new DecimalFormat("00").format(cld.get(Calendar.DAY_OF_MONTH));
                    date = cld.get(Calendar.MONTH) + 1 + "月"
                            + formatMonth + "日";
                    if (dateTime != null) {
                        int hours = dateTime.getHours();
                        int minutes = dateTime.getMinutes();
                        String format_hours = new DecimalFormat("00").format(hours);
                        String format_minutes = new DecimalFormat("00").format(minutes);
                        time = format_hours + ":" + format_minutes;
                    } else {
                        String format = new DecimalFormat("00").format(cld.get(Calendar.MINUTE) + 1);
                        time = cld.get(Calendar.HOUR_OF_DAY) + ":"
                                + format;
                    }
                } catch (ParseException e) {
                    logger.error("任务列表获取日期错误" + e.getMessage());
                }

                holder.dateText.setText(date);
                holder.timeText.setText(time);
                holder.checkBox.setChecked(checkList.get(position));
                holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            if (!whichchosed.contains(listDatas.get(position))) {
                                whichchosed.add(listDatas.get(position));
                                chosedBodyList.add(bodyList.get(position));
                                chosedTitleList.add(titleList.get(position));
                                checkList.set(position,true);
                            }


                        } else {
                            if (whichchosed.size() != 0) {
                                whichchosed.remove(listDatas.get(position));
                                chosedBodyList.remove(bodyList.get(position));
                                chosedTitleList.remove(titleList.get(position));
                                checkList.set(position,false);
                            }

                        }

//                        try {
//                            if (whichchosed.size() != listDatas.size()) {
//                                //全选消息
//                                iEventLsn.eventProcess(IEventType.BUTTON_CONTENTE, "全选消息");
//                            } else {
//                                //取消全选
//                                iEventLsn.eventProcess(IEventType.BUTTON_CONTENTE, "取消全选");
//                            }
//
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }

                    }
                });

                if (position == listDatas.size() - 1) {

                    try {
                        if (whichchosed.size() != listDatas.size()) {
                            //全选消息

                            iEventLsn.eventProcess(IEventType.BUTTON_CONTENTE, "全选消息");

                        } else {
                            //取消全选
                            iEventLsn.eventProcess(IEventType.BUTTON_CONTENTE, "取消全选");
                        }
                    } catch (HDException e) {
                        e.printStackTrace();
                    }

                }

                if (titleList != null && titleList.size() > 0 &&
                        bodyList != null && bodyList.size() > 0) {
                    if (titleList.get(position) != null) {
                        holder.contentText.setText(titleList.get(position) + ":" + bodyList.get(position));
                    } else if (titleList.get(position) == null && listDatas.get(position) == null) {
                        holder.contentText.setText("日常检维修");
                    } else if (titleList.get(position) == null && listDatas.get(position).getMessageTitle() != null) {
                        holder.contentText.setText(listDatas.get(position).getMessageTitle() + ":" + listDatas.get(position).getMessageBody());
                    } else if (titleList.get(position) == null && listDatas.get(position).getMessageTitle() == null) {
                        holder.contentText.setText("日常检维修");
                    }

                }


                if (position % 2 == 0) {
                    holder.line.setBackgroundColor(resources
                            .getColor(R.color.hd_hse_common_bule));
                }

                holder.swipeLayout.close();

//				holder.delete.setOnClickListener(new deleteOnClickListener(position));
            }
        }
    }

    class ViewHolder {
        TextView dateText;
        TextView timeText;
        TextView contentText;
        View line;
        SwipeLayout swipeLayout;
        CheckBox checkBox;
//		RelativeLayout delete;
    }
}
