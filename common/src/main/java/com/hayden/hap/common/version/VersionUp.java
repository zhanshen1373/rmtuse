package com.hayden.hap.common.version;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.hayden.hap.common.utils.ToastUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class VersionUp {
    private Context context;
    /**
     * 显示升级提示
     */
    private AlertDialog dialog;
    private AlertDialog dialogCurrentVer;
    /**
     * app下载显示进度
     */
    private ProgressDialog progressDialog;


    public VersionUp(Context context) {
        this.context = context;
    }


    /**
     * installApk:(自动安装APK). <br/>
     * date: 2014年9月9日 <br/>
     *
     * @param filepath
     * @author lxf
     */
    private void installApk(String filepath) {
        File file = new File(filepath);
        installApk(file);
    }

    /**
     * getVersionName:(获取系统当前版本). <br/>
     * date: 2014年9月9日 <br/>
     *
     * @return
     * @throws NameNotFoundException
     * @author lxf
     */
    private String getVersionName() throws NameNotFoundException {
        PackageManager packageManger = context.getPackageManager();
        PackageInfo packinfo = packageManger
                .getPackageInfo(context.getPackageName(), 0);
        return packinfo.versionName;
    }

    /**
     * 判断是否强制升级
     *
     * @param forceUpgradeVer
     * @return
     */
    public boolean isForceUp(String forceUpgradeVer) {
        boolean isForceUp = false;
        try {
            String currentVer = getVersionName();
            int currentNum = Integer.parseInt(currentVer.replace(".", ""));
            int forceUpgradeNum = Integer.parseInt(forceUpgradeVer.replace(".", ""));
            if (currentNum < forceUpgradeNum)
                isForceUp = true;
        } catch (NameNotFoundException e) {
            isForceUp = true;
            e.printStackTrace();
        }
        return isForceUp;
    }

    /**
     * 是否需要升级
     *
     * @param newVer 服务器返回的最新版本
     * @return
     */
    public boolean isNeedUp(String newVer) {
        return isForceUp(newVer);
    }

    /**
     * 显示升级提示dialog
     *
     * @param apkurl          升级的url
     * @param versionCodeName 最新版本号
     * @param isForceUp       是否强制升级
     */


    public void showUpdateDialog(final String apkurl, final String versionCodeName, boolean isForceUp) {
        if (apkurl == null) {
            ToastUtil.toast(context, "未找到升级链接");
            return;
        }
        if (versionCodeName == null) {
            ToastUtil.toast(context, "未找到最新版本号");
            return;
        }
        if (dialog == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("检测到新版本");
            builder.setMessage("版本号:" + versionCodeName);
            builder.setNegativeButton("下次再说", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.setPositiveButton("升级", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    downloadApk(apkurl, versionCodeName);

                }
            });
            dialog = builder.show();
            dialog.setCanceledOnTouchOutside(false);
        } else
            dialog.show();
        if (isForceUp)
            dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setVisibility(View.GONE);
        else
            dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setVisibility(View.VISIBLE);

    }

    /**
     * 不升级，显示当前版本信息
     */
    public void showCurrentVersionInfor() {
        if (dialogCurrentVer == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("版本信息");
            try {
                builder.setMessage("当前已是最新版本:" + getVersionName());
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialogCurrentVer = builder.show();
            } catch (NameNotFoundException e) {
                e.printStackTrace();
                ToastUtil.toast(context, "未查找到当前版本号");
                return;
            }


        } else
            dialogCurrentVer.show();

    }

    /**
     * 下载apk
     *
     * @param apkurl
     * @param versionCodeName
     */
    private void downloadApk(final String apkurl, final String versionCodeName) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setTitle("版本升级");
            progressDialog.setMessage("安装包下载中...");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setMax(100);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setIndeterminate(false);
        }
        progressDialog.show();
        progressDialog.setProgress(0);
        new Thread() {
            @Override
            public void run() {
                super.run();
                FileOutputStream output = null;
                try {

                    URL url = new URL(apkurl);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestProperty("Accept-Encoding", "identity");
                    int totalSize = conn.getContentLength();
                    conn.setConnectTimeout(8000);
                    conn.setReadTimeout(8000);

                    String apkFolderName = Environment.getExternalStorageDirectory()
                            + File.separator + "rmtapp" + File.separator + "apk";
                    final File file = new File(apkFolderName + File.separator + "com.haydenzk.rmt_nx_" + versionCodeName + ".apk");
                    if (!file.getParentFile().exists()) {
                        file.getParentFile().mkdirs();
                    }
                    InputStream input = conn.getInputStream();
                    int len = 0;
                    int readLength = 0;
                    file.createNewFile();//新建文件
                    output = new FileOutputStream(file);
                    //读取大文件
                    byte[] buffer = new byte[10 * 1024];
                    while ((len = input.read(buffer)) != -1) {
                        output.write(buffer, 0, len);
                        readLength += len;
                        final int percent = (int) (((float) readLength / totalSize) * 100);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                if (progressDialog != null && progressDialog.isShowing()) {
                                    progressDialog.setProgress(percent);
                                }
                            }
                        });
                    }
                    output.flush();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (progressDialog != null && progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }
                            //安装apk
                            installApk(file);
                        }
                    });


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (progressDialog != null && progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }
                            ToastUtil.toast(context, "下载更新包失败，请检查网络连接");
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (progressDialog != null && progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }
                            ToastUtil.toast(context, "下载更新包失败，请检查网络连接");
                        }
                    });

                } finally {
                    try {
                        if (output != null)
                            output.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    private Handler handler = new Handler();

    /**
     * 安装apk
     *
     * @param file
     */
    private void installApk(File file) {
       /* Intent intent = new Intent();
        // 执行动作
        intent.setAction(Intent.ACTION_VIEW);
        // 执行的数据类型
        intent.setDataAndType(Uri.fromFile(file),
                "application/vnd.android.package-archive");// 编者按：此处Android应为android，否则造成安装不了
        context.startActivity(intent);*/
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri data;
        // 判断版本大于等于7.0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            // "net.csdn.blog.ruancoder.fileprovider"即是在清单文件中配置的authorities
            data = FileProvider.getUriForFile(context, "com.hayden.hap.rmt.nx.fileprovider", file);
            // 给目标应用一个临时授权
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            data = Uri.fromFile(file);
        }
        intent.setDataAndType(data, "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

}

