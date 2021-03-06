package com.hd.hse.main.phone.ui.activity.welcome;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.hayden.hap.common.login.business.LoginUser;
import com.hd.hse.common.component.phone.util.ToastUtils;
import com.hd.hse.common.exception.DaoException;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.logger.LogUtils;
import com.hd.hse.common.module.phone.ui.activity.SystemApplication;
import com.hd.hse.dao.config.DBConfig;
import com.hd.hse.dao.config.DBType;
import com.hd.hse.entity.common.PersonCard;
import com.hd.hse.main.business.welcome.SysInitSrv;
import com.hd.hse.main.phone.R;
import com.hd.hse.main.phone.ui.activity.login.LoginActivity;
import com.hd.hse.main.phone.ui.activity.main.MainActivity;
import com.hd.hse.system.SystemProperty;

import org.apache.log4j.Level;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ClassName: StartWelcomeActivity (????????????????????????)<br/>
 * date: 2014???9???17??? <br/>
 *
 * @author lxf
 */
// @ContentView(R.layout.hd_hse_main_app_layout_welcome)
public class StartWelcomeActivity extends com.hayden.hap.common.login.WelcomeActivity {
    public static Context context;

    /**
     * DB_FILE:TODO(???????????????).
     */
    private final static String DB_FILE = "SYS.db";

    /**
     * LOG_FILE:TODO(????????????).
     */
    private final static String LOG_FILE = "hes_app.txt";

    /**
     * HSE_FIRST_RUN:TODO().
     */
    private final static String HSE_FIRST_RUN = "hse_first_run";

    /**
     * UPGRADE_MSG:TODO(????????????).
     */
    private final static String UPGRADE_MSG = "upgrade_msg";

    /**
     * UPGRADE_SUCESS:TODO(????????????).
     */
    private final static int UPGRADE_SUCESS = 1;

    /**
     * sysInitSrv:TODO(??????????????????).
     */
    private SysInitSrv sysInitSrv;

    private SharedPreferences preferences = null;

    private String currVersion;

    // ??????????????????
    private String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.CALL_PHONE};
    private AlertDialog dialog;
    private boolean notificationEnabled;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SystemApplication.getInstance().exit();
        this.context = this;
        if (!isTaskRoot()) {
            // ????????????????????????
            finish();
            return;
        }

        //???????????????????????????
        notificationEnabled = isNotificationEnabled(this);

        /*
        NotificationManagerCompat manager = NotificationManagerCompat.from(this);
        boolean isOpened = manager.areNotificationsEnabled();

        // ??????isOpened?????????????????????????????????????????????AppInfo??????????????????App????????????
        if (!isOpened){
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package", getApplication().getPackageName(), null);
            intent.setData(uri);
            startActivity(intent);
        }
        */
        // ???????????????????????????????????? 23 ?????????????????????????????????????????????
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            // ?????????????????????????????????
            boolean flag = false;
            for (int j = 0; j < permissions.length; j++) {
                int i = ContextCompat.checkSelfPermission(this, permissions[j]);

                // ?????????????????? ?????? GRANTED---??????  DINIED---??????
                if (i != PackageManager.PERMISSION_GRANTED) {
                    // ?????????????????????????????????
                    flag = true;
                    break;
                }
            }
            if (flag) {
                startRequestPermission();
            } else {
                if (!notificationEnabled) {
                    //?????????????????????
                    goSetting();

                } else {
                    // ???????????????
                    initConfig();
                }
            }
        } else {

            if (!notificationEnabled) {
                //?????????????????????
                goSetting();

            } else {
                // ???????????????
                initConfig();
            }
        }


    }

    private void goSetting() {
        dialog = new AlertDialog.Builder(this)
                .setTitle("?????????????????????")
                .setMessage("??????-????????????-??????-???????????????????????????????????????????????????????????????")
                .setPositiveButton("????????????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // ???????????????????????????
                        toSetting();
                    }
                })
                .setNegativeButton("??????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        initConfig();
                    }
                }).setCancelable(false).show();
    }

    private void toSetting() {
        Intent localIntent = new Intent();
        if (Build.VERSION.SDK_INT >= 9) {
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package", getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            localIntent.setAction(Intent.ACTION_VIEW);
            localIntent.setClassName("com.android.settings", "com.android.setting.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", getPackageName());
        }

        startActivityForResult(localIntent, 111);
    }


    private boolean isNotificationEnabled(Context context) {

        String CHECK_OP_NO_THROW = "checkOpNoThrow";
        String OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION";

        AppOpsManager mAppOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
        ApplicationInfo appInfo = context.getApplicationInfo();
        String pkg = context.getApplicationContext().getPackageName();
        int uid = appInfo.uid;

        Class appOpsClass = null;
     /* Context.APP_OPS_MANAGER */
        try {
            appOpsClass = Class.forName(AppOpsManager.class.getName());
            Method checkOpNoThrowMethod = appOpsClass.getMethod(CHECK_OP_NO_THROW, Integer.TYPE, Integer.TYPE,
                    String.class);
            Field opPostNotificationValue = appOpsClass.getDeclaredField(OP_POST_NOTIFICATION);

            int value = (Integer) opPostNotificationValue.get(Integer.class);
            return ((Integer) checkOpNoThrowMethod.invoke(mAppOps, value, uid, pkg) == AppOpsManager.MODE_ALLOWED);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    // ????????????????????????
    private void startRequestPermission() {
        ActivityCompat.requestPermissions(this, permissions, 321);
    }

    // ???????????? ?????? ???????????????
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 321) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                boolean pag = true;
                boolean b = true;
                for (int i = 0; i < permissions.length; i++) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        pag = false;
                        b = b && shouldShowRequestPermissionRationale(permissions[i]);
                    }
                }
                if (pag) {

                    if (!notificationEnabled) {
                        //?????????????????????
                        goSetting();

                    } else {
                        //????????????????????????
                        Toast.makeText(this, "??????????????????", Toast.LENGTH_SHORT).show();
                        // ???????????????
                        initConfig();
                    }
                } else {
                    if (!b) {
                        //??????????????????????????????
                        showDialogTipUserGoToAppSettting();
                    } else
                        finish();
                }
            }
        }
    }

    // ???????????????????????????????????????????????????

    private void showDialogTipUserGoToAppSettting() {

        dialog = new AlertDialog.Builder(this)
                .setTitle("???????????????")
                .setMessage("??????-????????????-??????-???????????????????????????????????????")
                .setPositiveButton("????????????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // ???????????????????????????
                        goToAppSetting();
                    }
                })
                .setNegativeButton("??????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).setCancelable(false).show();
    }

    // ????????????????????????????????????
    private void goToAppSetting() {
        Intent intent = new Intent();

        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);

        startActivityForResult(intent, 123);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 123) {

            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                // ?????????????????????????????????
                boolean isGrant = true;
                for (int p = 0; p < permissions.length; p++) {
                    int i = ContextCompat.checkSelfPermission(this, permissions[p]);
                    if (i != PackageManager.PERMISSION_GRANTED) {
                        isGrant = false;
                    }
                }
                // ?????????????????? ?????? GRANTED---??????  DINIED---??????
                if (!isGrant) {
                    //???????????????????????????????????????
                    // ?????????????????????????????????????????????????????????
                    showDialogTipUserGoToAppSettting();
                } else {
                    if (dialog != null && dialog.isShowing()) {
                        dialog.dismiss();
                    }
                    if (!notificationEnabled) {
                        //?????????????????????
                        goSetting();

                    } else {
                        //????????????????????????
                        Toast.makeText(this, "??????????????????", Toast.LENGTH_SHORT).show();
                        // ???????????????
                        initConfig();
                    }
                }
            }
        }
        if (requestCode == 111) {
            initConfig();
        }
    }


    /**
     * appinit:(???????????????). <br/>
     * date: 2014???9???16??? <br/>
     *
     * @author lxf
     */
    private void initConfig() {
        try {
            // preferences = getSharedPreferences(HSE_FIRST_RUN, MODE_PRIVATE);
            boolean firstRun = true;// preferences.getBoolean(HSE_FIRST_RUN,
            // 1.????????????
            initLogConfig();
            // ???????????????
            initDBConfig();
            sysInitSrv = new SysInitSrv();
            if (firstRun) {
                currVersion = getVersionCode();
                // 2.???????????????
                copyDBFileToSD();
                // ???????????????????????????
                new DBUpdateThread().start();
            } else {
                startLoginActivity();
            }

        } catch (Exception e) {
            ToastUtils.toast(this, "???????????????????????????????????????????????????");
        }
    }

    /**
     * startLoginActivity:(??????????????????). <br/>
     * date: 2014???9???16??? <br/>
     *
     * @author lxf
     */
    public void startLoginActivity() {
        init();
    }

    @Override
    protected Intent getIntentToMainActivity() {
        return new Intent(StartWelcomeActivity.this, MainActivity.class);
    }

    @Override
    protected Intent getIntentToLoginActivity() {
        return new Intent(StartWelcomeActivity.this, LoginActivity.class);
    }

    /**
     * dbinit:(???????????????). <br/>
     * date: 2014???9???16??? <br/>
     *
     * @author lxf
     */
    private void initDBConfig() {
        DBConfig.getConfig().setType(DBType.Sqlite);
        DBConfig.getConfig().setUrl(
                "jdbc:sqldroid:"
                        + SystemProperty.getSystemProperty().getRootDBpath()
                        + File.separator + DB_FILE);
    }

    /**
     * updateDB:(???????????????). <br/>
     * date: 2014???9???19??? <br/>
     *
     * @throws NameNotFoundException
     * @throws HDException
     * @author lxf
     */
    private void updateDB() throws HDException {
        List<String> versionList = new ArrayList<>();
        Collections.addAll(versionList, getResources().getStringArray(R.array.hd_hse_version_list));
        int ovIndex = versionList.indexOf(sysInitSrv.getCurVersion());
        int cvIndex = versionList.indexOf(currVersion);
        for (int i = ovIndex + 1; i <= cvIndex; i++) {
            System.out.println(versionList.get(i));
            List<String> listcodesql = getListSql(versionList.get(i));
            // ???????????????
            sysInitSrv.upgradeDB(listcodesql, versionList.get(i));
        }

        if (null != preferences) {
            // ???????????????????????????
            Editor editor = preferences.edit();
            // ????????????
            editor.putBoolean(HSE_FIRST_RUN, false);
            // ????????????
            editor.commit();
        }
    }

    /**
     * getListSql:(???????????????????????????????????????). <br/>
     * date: 2014???9???16??? <br/>
     *
     * @param code
     * @return
     * @author lxf
     */
    public List<String> getListSql(String code) {

        StringBuilder sbsql = new StringBuilder();
        // ???????????????
        sbsql.append(readfilevalue(code + File.separator + "altertable.sql"));
        // ?????????
        sbsql.append(readfilevalue(code + File.separator + "createtable.sql"));
        // ?????????????????????
        sbsql.append(readfilevalue(code + File.separator + "updatedate.sql"));
        // ????????????
        sbsql.append(readfilevalue(code + File.separator + "insertdate.sql"));
        List<String> listsql = null;
        if (sbsql.length() > 0) {
            listsql = new ArrayList<String>();
            String[] str = sbsql.toString().split(";");
            for (String sql : str) {
                if (sql.length() > 0 && sql.replace(" ", "").length() > 0) {
                    listsql.add(sql);
                }
            }
        }
        return listsql;
    }

    /**
     * readfilevalue:(??????????????????). <br/>
     * date: 2014???9???16??? <br/>
     *
     * @author lxf
     */
    private String readfilevalue(String srcFile) {

        StringBuilder content = new StringBuilder(); // ?????????????????????
        InputStream instream;
        try {
            // srcFile = File.separator + srcFile;
            instream = this.getAssets().open(srcFile);
            if (instream != null) {
                InputStreamReader inputreader = new InputStreamReader(instream);
                BufferedReader buffreader = new BufferedReader(inputreader);
                String line;
                // ????????????
                while ((line = buffreader.readLine()) != null) {
                    content.append(line);// + "\n";
                }
                instream.close();
            }
        } catch (IOException e) {
            // logger.error("????????????????????????", e);
        }
        if (content.length() > 0 && !content.toString().endsWith(";")) {
            content.append(";");
        }
        return content.toString();

    }

    /**
     * getDBVersion:(???????????????????????????). <br/>
     * date: 2014???9???16??? <br/>
     *
     * @return
     * @throws DaoException
     * @author lxf
     */
    private int getDBVersion() throws HDException {
        String oldcode;
        try {
            oldcode = sysInitSrv.getCurVersion();
            return resloveVersionFormat(oldcode);
        } catch (HDException e) {
            // TODO Auto-generated catch block
            throw e;
        }
    }

    /**
     * getVersionName:(?????????????????????). <br/>
     * date: 2014???9???16??? <br/>
     *
     * @return
     * @throws NameNotFoundException
     * @author lxf
     */
    private String getVersionCode() throws NameNotFoundException {
        PackageManager packageManger = getPackageManager();
        PackageInfo packinfo = packageManger
                .getPackageInfo(getPackageName(), 0);
        return packinfo.versionName;
    }

    /**
     * resloveVersionFormat:(??????????????????). <br/>
     * date: 2014???9???19??? <br/>
     *
     * @param code
     * @return
     * @author lxf
     */
    private int resloveVersionFormat(String code) {
        int intcode = Integer.parseInt(code.replace(".", ""));
        return intcode;
    }

    /**
     * createVersionFormat:(??????????????????). <br/>
     * date: 2014???9???19??? <br/>
     *
     * @param code
     * @return
     * @author lxf
     */
    private String createVersionFormat(int code) {
        String str = "" + code;
        String ret = "";
        for (int i = 0; i <= (str.length() - 1); i++) {
            ret += str.substring(i, i + 1);
            if (i != (str.length() - 1)) {
                ret += ".";
            }
        }

        return ret;
    }

    /**
     * logInitConfig:(????????????). <br/>
     * date: 2014???9???16??? <br/>
     *
     * @author lxf
     */
    private void initLogConfig() {
        //lxf ??????
        //LogUtils.initConfigDefault);
        LogUtils.initConfig(Level.ERROR, SystemProperty.getSystemProperty()
                .getRootDBpath() + File.separator + LOG_FILE);
    }

    /**
     * copyDBFileToSD:(???????????????). <br/>
     * date: 2014???9???16??? <br/>
     *
     * @throws HDException
     * @author lxf
     */
    @SuppressWarnings("resource")
    private void copyDBFileToSD() throws HDException {
        String desDir = SystemProperty.getSystemProperty().getRootDBpath()
                + File.separator + DB_FILE;
        File dbFile = new File(desDir);
        if (!dbFile.exists()) {
            dbFile.getParentFile().mkdirs();
            InputStream inStream = null;
            OutputStream outStream = null;
            try {
                outStream = new FileOutputStream(desDir);
                try {
                    // ?????????????????????????????????????????????????????????????????????????????????????????????
                    String srcFile = currVersion + File.separator + DB_FILE;
                    inStream = this.getAssets().open(srcFile);
                } catch (Exception e) {
                    inStream = this.getAssets().open(DB_FILE);
                }
                byte[] buffer = new byte[1024];
                int length = inStream.read(buffer);
                while (length > 0) {
                    outStream.write(buffer, 0, length);
                    length = inStream.read(buffer);
                }
                outStream.flush();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                LogUtils.getLogger(LoginActivity.class)
                        .error(e.getMessage(), e);
                throw new HDException(e.getMessage(), e);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                LogUtils.getLogger(LoginActivity.class)
                        .error(e.getMessage(), e);
                throw new HDException(e.getMessage(), e);
            } finally {
                if (inStream != null) {
                    try {
                        inStream.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block

                    }
                }
                if (outStream != null) {
                    try {
                        outStream.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block

                    }
                }
            }
        }
    }

    @SuppressLint("HandlerLeak")
    class DBUpdateHandler extends Handler {
        public DBUpdateHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bd = new Bundle();
            bd = msg.getData();
            int result = bd.getInt(UPGRADE_MSG);
            if (result != UPGRADE_SUCESS) {
                // ??????????????????,??????
                ToastUtils
                        .toast(StartWelcomeActivity.this, "???????????????????????????????????????????????????");
            }
            startLoginActivity();
        }
    }

    private DBUpdateHandler handler;

    class DBUpdateThread extends Thread {
        public void run() {
            // 4.??????????????????
            int result = 0;
            try {
                updateDB();
                result = UPGRADE_SUCESS;
            } catch (Exception e) {
                LogUtils.getLogger(StartWelcomeActivity.class).error(
                        e.getMessage(), e);
            }
            Looper looper = Looper.getMainLooper();
            handler = new DBUpdateHandler(looper);
            Bundle bd = new Bundle();
            bd.putInt(UPGRADE_MSG, result);
            Message msg = new Message();
            msg.setData(bd);
            handler.sendMessage(msg);
        }
    }

    @Override
    protected void saveUserToSys(LoginUser loginUser) {
        PersonCard loginPsn = new PersonCard();
        loginPsn.setLoginid(loginUser.getUserid() + "");
        loginPsn.setPersonid_desc(loginUser.getUserName());
        SystemProperty.getSystemProperty().setLoginPerson(loginPsn);
    }
}
