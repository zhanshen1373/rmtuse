package com.hayden.hap.common.login;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hayden.hap.common.base.activity.BaseActivity;
import com.hayden.hap.common.common.bussiess.HttpBusiness;
import com.hayden.hap.common.common.bussiess.HttpCallBack;
import com.hayden.hap.common.common.bussiess.ResultData;
import com.hayden.hap.common.dao.GreenDaoManager;
import com.hayden.hap.common.dao.LoginHistoryDao;
import com.hayden.hap.common.dao.dBEntity.LoginHistory;
import com.hayden.hap.common.login.business.AppVersion;
import com.hayden.hap.common.login.business.LoginInterface;
import com.hayden.hap.common.login.business.LoginKey;
import com.hayden.hap.common.login.business.LoginUser;
import com.hayden.hap.common.login.business.LoginUserRecord;
import com.hayden.hap.common.utils.PropertiesUtils;
import com.hayden.hap.common.utils.RSAUtils;
import com.hayden.hap.common.utils.RetrofitUtil;
import com.hayden.hap.common.utils.SHAUtils;
import com.hayden.hap.common.utils.SysUtil;
import com.hayden.hap.common.utils.ToastUtil;
import com.hayden.hap.common.version.VersionUp;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import test.demo.mylibrary.R;

/**
 * 登录页
 * Created by liuyang on 2017/02/15
 */
public class LoginActivity extends BaseActivity {

    protected EditText nameEdit;
    private EditText passEdit;
    private Button loginBtn;
    //private CheckBox nameHistoryBtn;
    //private CheckBox lookPassBtn;
    private CheckBox autoLoginCheckBox;
    private TextView registerBtn;
    //private ListView nameHistoryList;
    private String pass;
    private List<LoginHistory> histories;
    //private ListAdapter adapter;

    private CheckBox adressBtn;
    private EditText adressEdit;
    private RelativeLayout layout_adress;
//    正式环境 http://10.200.1.55:9020/
    //测试环境 http://m-rmt.test-win.proj-nx.hd-intra-local.com/
//    http://192.168.6.125:8081/


    private String items[] = {"http://10.200.1.55:9020/"};
//    , "http://m-rmt.test-win.proj-nx.hd-intra-local.com/", "http://192.168.6.92:9020/"
    private AlertDialog.Builder builder;
    private AlertDialog dialog;
    private int index = 0;
    public static String APPVERSION = "AppVersion";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hd_hse_main_app_layout_login);
        checkPermission();
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 3);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 3) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                // 未授权
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this)
                        .setMessage("如果禁止该权限，您将无法登录")
                        .setNegativeButton("退出", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                checkPermission();
                            }
                        });
                builder.show();
                //Toast.makeText(this, "未授权", Toast.LENGTH_LONG).show();
            }
        }
    }

    protected void initView() {
        nameEdit = (EditText) findViewById(R.id.nameEdit);
        passEdit = (EditText) findViewById(R.id.passEdit);
        //nameHistoryBtn = (CheckBox) findViewById(nameHistoryBtn);
        //lookPassBtn = (CheckBox) findViewById(lookPassBtn);
        autoLoginCheckBox = (CheckBox) findViewById(R.id.autoLoginCheckBox);
        registerBtn = (TextView) findViewById(R.id.registerBtn);
       /* // 显示与隐藏密码
        lookPassBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    passEdit.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    passEdit.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });*/
        /*nameHistoryBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    nameHistoryList.setVisibility(View.VISIBLE);
                    SysUtil.packUpImm(LoginActivity.this);
                } else
                    nameHistoryList.setVisibility(View.GONE);
            }
        });*/
        // nameHistoryList = (ListView) findViewById(nameHistoryList);
//        nameHistoryList.setAdapter(new HistoryListAdapter(realm.where(LoginHistory.class).findAllAsync().sort("loginTime", Sort.DESCENDING)));

      /*  adapter = new ListAdapter();
        nameHistoryList.setAdapter(adapter);
        setListViewHeightBasedOnChildren(nameHistoryList);
        nameHistoryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                nameEdit.setText(histories.get(i).getUsercode());
            }
        });*/
        loginBtn = (Button) findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = nameEdit.getText().toString();
                String password = passEdit.getText().toString();
                if (userName.equals("")) {
                    showToast("用户名不能为空");
                    return;
                }
                if (password.equals("")) {
                    showToast("密码不能为空");
                    return;
                }
                setBaseUrl(adressEdit.getText().toString().trim());
                if (PropertiesUtils.getBaseUrl() == null || "".equals(PropertiesUtils.getBaseUrl())) {
                    ToastUtil.toast(LoginActivity.this, "baseurl不能为空");
                    return;
                }
                RetrofitUtil.getInstance().setBaseUrl(adressEdit.getText().toString().trim());
                SysUtil.packUpImm(LoginActivity.this);
                login(userName, password);


            }
        });

        adressBtn = (CheckBox) findViewById(R.id.adressBtn);
        adressEdit = (EditText) findViewById(R.id.adressEdit);
        adressEdit.setText(items[index]);
        layout_adress = (RelativeLayout) findViewById(R.id.layout_adress);


        builder = new AlertDialog.Builder(LoginActivity.this);

        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                adressBtn.setChecked(false);
            }
        });
        builder.create();

        adressBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //打开地址列表
                    builder.setSingleChoiceItems(items, index,
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface arg0,
                                                    int arg1) {
                                    adressEdit.setText(items[arg1]);
                                    index = arg1;
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            adressBtn.setChecked(false);
                                        }
                                    }, 300);

                                }
                            });
                    dialog = builder.show();

                } else {
                    //关闭地址列表
                    dialog.dismiss();
                }

            }
        });

    }

    protected List<LoginHistory> getLoginHistory() {
        if (histories == null) {
            histories = getLoginHistoryDao().queryBuilder().orderDesc(LoginHistoryDao.Properties.LoginTime).list();
        }

        return histories;

    }

    private void setBaseUrl(String url) {
        if (url != null && !"".equals(url)) {
            PropertiesUtils.setBaseUrl(url);
        } else {
            ToastUtil.toast(LoginActivity.this, "baseurl不能为空");
        }

    }

    private void login(final String userName, final String password) {

        final ProgressDialog dialog = new ProgressDialog(LoginActivity.this);
        dialog.setMessage("正在登录。。。");
        dialog.show();
        final LoginInterface loginInterface = RetrofitUtil.createInterface(LoginInterface.class);
        // 获取公钥
        Call<ResultData<LoginKey>> getKeyCall = loginInterface.getStokenkey();
        HttpBusiness.action(LoginActivity.this, false, getKeyCall, new HttpCallBack<LoginKey>() {
            @Override
            public void success(LoginKey loginKey) {
                String encrypt = loginKey.getEncryptType();
                String passRSA = null;
                if ("r".equals(encrypt)) {
                    passRSA = encryptRPassword(loginKey, password);
                } else if ("sr".equals(encrypt)) {
                    passRSA = encryptPassword(loginKey, password);
                } else {
                    passRSA = encryptPassword(loginKey, password);
                }
                // 登录
                LoginInterface.LoginRequestBody body = new LoginInterface.LoginRequestBody();
                body.username = userName;
                body.password = passRSA;
                body.appVersion = SysUtil.getVersionName(LoginActivity.this);
                body.stokenKey = loginKey.getStokenkey();
                body.equipmentCode = SysUtil.getIMEI(LoginActivity.this);
                Call<ResultData<LoginUser>> loginCall = loginInterface.login(body);
                HttpBusiness.action(LoginActivity.this, false, loginCall, new HttpCallBack<LoginUser>() {
                    @Override
                    public void success(LoginUser loginUser) {
                        dialog.dismiss();
                        // showToast("登录成功");
                        //老版本逻辑
                        saveUserToSys(loginUser);
                        //版本更新检测
                        VersionUp versionUp = new VersionUp(LoginActivity.this);
                        //判断是否强制升级
                        if (loginUser.getNewAppVer() != null
                                && loginUser.getNewAppVer().getForceUpgradeVer() != null
                                && versionUp.isForceUp(loginUser.getNewAppVer().getForceUpgradeVer())) {
                            versionUp.showUpdateDialog(loginUser.getNewAppVer().getUpgradeUrl(),
                                    loginUser.getNewAppVer().getCurrentVer(), true);
                            return;
                        }
                        saveUserInfo(loginUser);
                        RetrofitUtil.getInstance().setStokenkey(loginUser.getStokenKey());
                        //adapter.notifyDataSetChanged();
                        goNextPage(loginUser.getNewAppVer());
                        Intent intent = new Intent();
                        intent.setAction(getPackageName() + ".LOGIN");
                        sendBroadcast(intent);
                        finish();
                    }

                    @Override
                    public void warning(String msg) {
                        dialog.dismiss();
                        showToast(msg);
                    }

                    @Override
                    public void error(Throwable t) {
                        dialog.dismiss();
                        showToast("登录失败，请重试！");
                    }
                });
            }

            @Override
            public void warning(String msg) {
                dialog.dismiss();
                showToast(msg);
            }

            @Override
            public void error(Throwable t) {
                dialog.dismiss();
                showToast("网络异常!");
            }
        });
    }

    private String encryptRPassword(LoginKey loginKey, String password) {
        String modulus = loginKey.getModulus();
        String publicExponent = loginKey.getPublicExponent();
        return RSAUtils.encryptString(RSAUtils.getRSAPublidKey(modulus, publicExponent), password);
    }

    private String encryptPassword(LoginKey key, String password) {
        String modulus = key.getModulus();
        String publicExponent = key.getPublicExponent();
        String passSHA = SHAUtils.sha256Encrypt(password);
        pass = passSHA;
        return RSAUtils.encryptString(RSAUtils.getRSAPublidKey(modulus, publicExponent), passSHA);
    }

    private void saveUserInfo(LoginUser user) {
        LoginUserRecord.getInstance().setUser(user);
        LoginHistory history = new LoginHistory();
        history.setUsercode(user.getUsercode());
        if (autoLoginCheckBox.isChecked()) {
            history.setPassword(pass);
        } else
            history.setPassword("");
        history.setLoginTime(new Date());
        history.setAuto(autoLoginCheckBox.isChecked());
        getLoginHistoryDao().insertOrReplace(history);
    }

    public void goNextPage(AppVersion appVersion) {
        String mainActivityClass = PropertiesUtils.getProperties("page.properties").getProperty("MAIN_PAGE");
        try {
            Intent intent = new Intent(this, Class.forName(mainActivityClass));
            intent.putExtra(APPVERSION, appVersion);
            startActivity(intent);
        } catch (ClassNotFoundException e) {
            showToast("请配置主页面地址");
        }
    }

    private LoginHistoryDao getLoginHistoryDao() {
        return GreenDaoManager.getInstance().getmDaoSession().getLoginHistoryDao();
    }

    /**
     * 设置登录历史的高度
     *
     * @param listView
     */
    private void setListViewHeightBasedOnChildren(ListView listView) {
        android.widget.ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        int count = listAdapter.getCount() >= 4 ? 4 : listAdapter.getCount();
        for (int i = 0; i < count; i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        if (params.height < 0) {
            params.height = 0;
        }
//        ((ViewGroup.MarginLayoutParams) params).setMargins(10, 10, 10, 10); // 可删除
        listView.setLayoutParams(params);
    }

    private class ListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return histories.size();
        }

        @Override
        public Object getItem(int i) {
            return histories.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder holder;
            if (view == null) {
                view = LayoutInflater.from(LoginActivity.this).inflate(android.R.layout.simple_list_item_1, viewGroup, false);
                holder = new ViewHolder();
                holder.textView = (TextView) view.findViewById(android.R.id.text1);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }
//            holder.textView.setText(histories.get(i).getUsercode() + " / " + histories.get(i).getPassword());
            holder.textView.setText(histories.get(i).getUsercode());
            return view;
        }

        private class ViewHolder {
            TextView textView;
        }
    }

    protected void saveUserToSys(LoginUser loginUser) {
    }
}
