package com.hayden.hap.common.common.bussiess;

import android.app.ProgressDialog;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.hayden.hap.common.base.activity.ActivityManager;
import com.hayden.hap.common.login.business.LoginUserRecord;
import com.hayden.hap.common.utils.ToastUtil;
import com.hd.hse.common.logger.LogUtils;

import org.apache.log4j.Logger;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 网络连接业务类
 * Created by liuyang on 2017/2/27.
 */

public class HttpBusiness {

    private static Logger logger = LogUtils
            .getLogger(HttpBusiness.class);

    /**
     * @param context      上下文
     * @param isForeground 控制是否显示ProgressDialog
     * @param call         call
     * @param callBack     callBack
     * @return 返回值为null
     */
    public static <T> T action(final Context context, boolean isForeground, Call<ResultData<T>> call, final HttpCallBack<T> callBack) {
        ProgressDialog dialog = null;
        if (isForeground) {
            dialog = new ProgressDialog(context);
            dialog.setMessage("加载中。。。");
            dialog.show();
        }
        final ProgressDialog finalDialog = dialog;
        call.enqueue(new Callback<ResultData<T>>() {
            @Override
            public void onResponse(Call<ResultData<T>> call, Response<ResultData<T>> response) {
                if (finalDialog != null && finalDialog.isShowing())
                    finalDialog.cancel();
                ResultData<T> resultData = response.body();
                if (resultData == null || resultData.getSuccessfull() == null) {
                    callBack.warning("未知错误");
                    return;
                }
                if (resultData.getSuccessfull() == 1)
                    callBack.success(resultData.getResultData());
                else if (resultData.getSuccessfull() == 100) {
                    if (context != null) {
                        ToastUtil.toast(context, "登录超时");
                    }
                    ActivityManager.getInstance().reLogin();
                } else if (resultData.getSuccessfull() == 3) {
                    Gson gs=new Gson();
                    if (resultData.getResultData()!=null){
                        String s = gs.toJson(resultData.getResultData());
                        LoginUserRecord.getInstance().setOb(s);
                    }
                    callBack.warning(resultData.getMessages().get(0));
                } else {
                    String error = getSuccessfulCfg().get(resultData.getSuccessfull());
                    if (error != null && !error.equals(""))
                        callBack.warning(error);
                    else
                        callBack.warning(resultData.getMessages().get(0));
                }
            }

            @Override
            public void onFailure(Call<ResultData<T>> call, Throwable e) {
                String error = "";
                if (e instanceof TimeoutException || e instanceof SocketTimeoutException
                        || e instanceof ConnectException) {
                    error = "连接超时，请检查网络连接";
                } else if (e instanceof JsonSyntaxException) {

                    error = "返回Json格式出错";
                    e.printStackTrace();
                    logger.error("返回Json格式出错: " + e.toString());
                    //Log.e("返回Json格式出错", e.toString());
                    //假如导致这个异常触发的原因是服务器的问题，那么应该让服务器知道，所以可以在这里
                    //选择上传原始异常描述信息给服务器
                }
                if (finalDialog != null && finalDialog.isShowing())
                    try {
                        finalDialog.cancel();
                    } catch (Exception e1) {

                    }
                if (error.equals(""))
                    callBack.error(e);
                else callBack.warning(error);
            }
        });
        return null;
    }

    private static Map<Integer, String> sucCfg;

    private static Map<Integer, String> getSuccessfulCfg() {
        if (sucCfg == null) {
            sucCfg = new HashMap<>();
            sucCfg.put(1, "成功");
            sucCfg.put(1503, "服务器正在升级中");
            sucCfg.put(100, "token失效超时");
            sucCfg.put(403, "没有操作权限");
        }
        return sucCfg;
    }
}
