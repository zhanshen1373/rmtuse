package rmt.zsc.phone.app.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.hayden.hap.common.utils.RetrofitUtil;
import com.hayden.hap.common.utils.ToastUtil;
import com.hd.hse.entity.resultdata.ZscBean;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import rmt.zsc.phone.app.R;

public class ZscDetailActivity extends AppCompatActivity {


    protected Toolbar toolbar;
    protected GridView gridView;
    protected TextView tv;
    private ZscBean.MainvoBean mainvoBean;
    private List<ZscBean.MainvoBean.HeadVOBean.PHDKNOWLEDGEWINMBean.AttachAttachShowListBean> attach_attachShowList;
    private String stokenkey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zsc_detail);

        gridView = (GridView) findViewById(R.id.zsc_detail_gridview);
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        toolbar.setTitle("");
        tv = (TextView) findViewById(R.id.tv_title);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);//这2行是设置返回按钮的
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        stokenkey = RetrofitUtil.getInstance().getStokenkey();
        final Intent intent = getIntent();
        if (intent != null) {
            mainvoBean = (ZscBean.MainvoBean) intent.getSerializableExtra("data");
            attach_attachShowList = mainvoBean.getHeadVO().getPHD_KNOWLEDGEWIN_M().getAttach_attachShowList();
        }

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String address = Environment.getExternalStorageDirectory() + File.separator + "rmtapp" + File.separator + "download";
                File file = new File(address);
                if (!file.exists()) {
                    file.mkdirs();
                }
                ZscBean.MainvoBean.HeadVOBean.PHDKNOWLEDGEWINMBean.AttachAttachShowListBean attachAttachShowListBean = attach_attachShowList.get(position);



                String baseUrl = RetrofitUtil.getInstance().getBaseUrl();

                String[] s1 = attachAttachShowListBean.getRecord_created_dt().split(" ");
                String date = s1[0] + "%20" + s1[1];
                String url = baseUrl + "m/phd_m/PHD_KNOWLEDGEWIN_M/downloadPreviewM.json?act=downloadPic&att_d_uuid="
                        + attachAttachShowListBean.getAtt_d_uuid() + "&record_created_dt=" + date +
                        "&attachdataid=" + attachAttachShowListBean.getAttachdataid();

//                downloadAttachM

//                downloadPreviewM
                String att_d_type = attachAttachShowListBean.getAtt_d_type();
                if (att_d_type.equalsIgnoreCase(".txt")||att_d_type.equalsIgnoreCase(".docx")||att_d_type.equalsIgnoreCase(".pptx")||
                att_d_type.equalsIgnoreCase(".doc")||att_d_type.equalsIgnoreCase(".ppt")){
                    att_d_type=".pdf";
                }else if (att_d_type.equalsIgnoreCase(".xlsx")||att_d_type.equalsIgnoreCase(".xls")){
                    att_d_type=".html";
                }
                String att_d_uuid = attachAttachShowListBean.getAtt_d_uuid() + att_d_type;
                String att_d_name = attachAttachShowListBean.getAtt_d_name();
                if (attachAttachShowListBean.getAtt_d_type().equalsIgnoreCase(".mp3") ||
                        attachAttachShowListBean.getAtt_d_type().equalsIgnoreCase(".mp4")) {

                    url=baseUrl + "m/phd_m/PHD_KNOWLEDGEWIN_M/viewVideoMp4M?att_d_uuid="
                            + attachAttachShowListBean.getAtt_d_uuid() + "&record_created_dt=" + date +
                            "&attachdataid=" + attachAttachShowListBean.getAttachdataid()+"&stokenKey="+stokenkey;
                    Intent intent=new Intent(ZscDetailActivity.this,FjActivity.class);
                    intent.putExtra("path",url);
                    intent.putExtra("showName",att_d_name);
                    startActivity(intent);
                } else {

                    File fj = new File(file, att_d_uuid);
                    long length = fj.length();
                    if (length == 0) {

                        if (!fj.exists()) {
                            try {
                                fj.createNewFile();
                                if (progressDialog == null) {
                                    progressDialog = new ProgressDialog(ZscDetailActivity.this);
                                    progressDialog.setTitle("附件下载");
                                    progressDialog.setMessage("附件下载中...");
                                    progressDialog.setCanceledOnTouchOutside(false);
                                    progressDialog.setIndeterminate(false);
                                }
                                progressDialog.show();
//                                downLoad(url, fj);
                                fileDownload(url,file,att_d_uuid);
//                                downFile(url, attachAttachShowListBean.getAtt_d_uuid(), fj);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                    } else {
                        Intent intent=new Intent(ZscDetailActivity.this,FjActivity.class);
                        intent.putExtra("path",fj.getAbsolutePath());
                        intent.putExtra("showName",att_d_name);
                        startActivity(intent);

//                        QbSdk.openFileReader(ZscDetailActivity.this, fj.getAbsolutePath(), null, new ValueCallback<String>() {
//                            @Override
//                            public void onReceiveValue(String s) {
//                                Log.e("qq", s);
//                            }
//                        });


                    }

                }


            }
        });


        gridView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return attach_attachShowList != null && attach_attachShowList.size() > 0 ? attach_attachShowList.size() : 0;
            }

            @Override
            public Object getItem(int position) {
                return attach_attachShowList.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                ViewHolder holder = new ViewHolder();
                if (convertView == null) {
                    convertView = View.inflate(ZscDetailActivity.this, R.layout.zscdetail, null);

                    holder.zscDetailImage = (ImageView) convertView.findViewById(R.id.zsc_detail_image);
                    holder.zscDetailName = (TextView) convertView.findViewById(R.id.zsc_detail_name);
                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder) convertView.getTag();
                }
                ZscBean.MainvoBean.HeadVOBean.PHDKNOWLEDGEWINMBean.AttachAttachShowListBean attachAttachShowListBean = attach_attachShowList.get(position);
                if (attachAttachShowListBean != null) {
                    String att_d_type = attachAttachShowListBean.getAtt_d_type();
                    if (att_d_type.equalsIgnoreCase(".png")) {
                        holder.zscDetailImage.setImageResource(R.drawable.jpg);
                    } else if (att_d_type.equalsIgnoreCase(".xlsx")||att_d_type.equalsIgnoreCase(".xls")) {
                        holder.zscDetailImage.setImageResource(R.drawable.excel);
                    } else if (att_d_type.equalsIgnoreCase(".mp3")) {
                        holder.zscDetailImage.setImageResource(R.drawable.mp3);
                    } else if (att_d_type.equalsIgnoreCase(".mp4")) {
                        holder.zscDetailImage.setImageResource(R.drawable.mp4);
                    } else if (att_d_type.equalsIgnoreCase(".pdf")) {
                        holder.zscDetailImage.setImageResource(R.drawable.pdf);
                    } else if (att_d_type.equalsIgnoreCase(".docx") || att_d_type.equalsIgnoreCase(".doc")) {
                        holder.zscDetailImage.setImageResource(R.drawable.word);
                    } else if (att_d_type.equalsIgnoreCase(".txt")){
                        holder.zscDetailImage.setImageResource(R.drawable.txt);
                    }
                    holder.zscDetailName.setText(attachAttachShowListBean.getAtt_d_name());
                }


                return convertView;
            }


        });

    }


    private Handler handler = new Handler();
    private ProgressDialog progressDialog;

    private HttpURLConnection getConnection(String httpUrl) throws Exception {
        URL url = new URL(httpUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/octet-stream");
//        connection.setDoOutput(true);
//        connection.setDoInput(true);
        connection.setRequestProperty("Accept-Encoding", "identity"); // 添加这行代码
        connection.setRequestProperty("Charset", "UTF-8");
        connection.setRequestProperty("Connection", "Keep-Alive");
        connection.connect();
        return connection;


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Activity销毁时，取消网络请求
        OkGo.getInstance().cancelTag(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            OkGo.getInstance().cancelTag(this);
            return false;
        }else {
            return super.onKeyDown(keyCode, event);
        }
    }


    public void fileDownload(String url, File floder, String name) {
        OkGo.<File>get(url)//
                .tag(this)//
                .headers("stokenkey", stokenkey)//
//                .params("param1", "paramValue1")//
                .execute(new FileCallback(floder.getAbsolutePath(),name) {

                    @Override
                    public void onStart(Request<File, ? extends Request> request) {
//                        btnFileDownload.setText("正在下载中");
                    }

                    @Override
                    public void onSuccess(Response<File> response) {

                        if (progressDialog != null && progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        ToastUtil.toast(ZscDetailActivity.this, "下载附件成功");
                    }

                    @Override
                    public void onError(Response<File> response) {
                        if (progressDialog != null && progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        ToastUtil.toast(ZscDetailActivity.this, "下载附件失败，请检查网络连接");
                    }

                    @Override
                    public void downloadProgress(Progress progress) {

                        if (progressDialog != null && progressDialog.isShowing()) {
                            progressDialog.setProgress((int) (progress.fraction * 100));
                        }
                    }
                });
    }


    private void downFile(final String apkurl, final String parameter, final File file) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                FileOutputStream output = null;
                try {

                    HttpURLConnection connection = null;
                    try {
                        connection = getConnection(apkurl);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    int totalSize = connection.getContentLength();

//                    conn.setConnectTimeout(8000);
//                    conn.setReadTimeout(8000);

                    InputStream input = connection.getInputStream();
                    int len = 0;
                    int readLength = 0;
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
                            ToastUtil.toast(ZscDetailActivity.this, "下载附件成功");
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
                            ToastUtil.toast(ZscDetailActivity.this, "下载附件失败，请检查网络连接");
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
                            ToastUtil.toast(ZscDetailActivity.this, "下载附件失败，请检查网络连接");
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

    static class ViewHolder {
        private ImageView zscDetailImage;
        private TextView zscDetailName;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
