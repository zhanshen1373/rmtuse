package rmt.zsc.phone.app.activity;

import android.graphics.Color;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnErrorListener;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.hayden.hap.common.utils.ToastUtil;
import com.hayden.hap.plugin.hapJzvd.lib.HapJzvdStd;
import com.shockwave.pdfium.PdfDocument;

import java.io.File;
import java.util.List;

import rmt.zsc.phone.app.R;

public class FjActivity extends AppCompatActivity implements OnPageChangeListener, OnLoadCompleteListener, OnPageErrorListener, OnErrorListener {

    private WebView webView;
    private PDFView pdfView;
    private HapJzvdStd jzvideo;
    private String path;
    Integer pageNumber = 0;
    private String showName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fj);

        webView = (WebView) findViewById(R.id.fj_webviwe);
        pdfView = (PDFView) findViewById(R.id.fj_pdfview);
        jzvideo = (HapJzvdStd) findViewById(R.id.fj_jzvideo);

        if (getIntent() != null) {
            path = getIntent().getStringExtra("path");
            showName = getIntent().getStringExtra("showName");
            if (path != null) {
                if (path.contains(".pdf")) {
                    pdfView.setVisibility(View.VISIBLE);

                    pdfView.setBackgroundColor(Color.LTGRAY);
                    if (path != null) {
                        File file = new File(path);
                        displayFromFile(file);
                    }
                    setTitle(showName);
                }
                if (path.contains(".html")) {
                    initWebview();

                    setTitle(showName);
                }
                if (showName.contains(".mp3") || showName.contains(".mp4")) {
                    jzvideo.setVisibility(View.VISIBLE);
                    jzvideo.setUp(path, showName);
                    setTitle(showName);
//                    jzvideo.setThumbnail(MainActivity.this, "https://img.ui.cn/data/file/4/5/6/2630654.png");
//                    jzvideo.setStateListener(state -> Toast.makeText(MainActivity.this, state.name(), Toast.LENGTH_SHORT).show());
//                    jzvideo.setOnProgressListener((progress, position, duration) -> Log.d(MainActivity.class.getName(), progress + " / " + position + " / " + duration));

                }
            }
        }


    }

    private void initWebview() {
        webView.setVisibility(View.VISIBLE);
        String url = "file://" + path;
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
            }

            //解决https请求手机验证的问题
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
                super.onReceivedSslError(view, handler, error);
            }
        });
        //只会webview访问历史记录里的所有记录除了当前访问记录
        webView.clearHistory();
        WebSettings webSettings = webView.getSettings();
        // 设置WebView属性，能够执行Javascript脚本
        webSettings.setJavaScriptEnabled(true);
        // 设置可以访问文件
        webSettings.setAllowFileAccess(true);
        // 设置支持缩放
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);

        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        //关闭webview中缓存
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        //设置编码格式
        webSettings.setDefaultTextEncodingName("utf-8");
        webView.loadUrl(url);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            }
        });
    }


    private void displayFromFile(File uri) {

        pdfView.fromFile(uri)
                .defaultPage(pageNumber)
                .onPageChange(this)
                .enableAnnotationRendering(true)
                .onLoad(this)
                .scrollHandle(new DefaultScrollHandle(this))
                .spacing(10) // in dp
                .onPageError(this)
                .onError(this)
                .load();
    }


    @Override
    public void loadComplete(int nbPages) {
        PdfDocument.Meta meta = pdfView.getDocumentMeta();

        printBookmarksTree(pdfView.getTableOfContents(), "-");
    }

    public void printBookmarksTree(List<PdfDocument.Bookmark> tree, String sep) {
        for (PdfDocument.Bookmark b : tree) {

            if (b.hasChildren()) {
                printBookmarksTree(b.getChildren(), sep + "-");
            }
        }
    }

    @Override
    public void onPageChanged(int page, int pageCount) {
        pageNumber = page;
        setTitle(String.format("%s %s / %s", showName, page + 1, pageCount));
    }

    @Override
    public void onPageError(int page, Throwable t) {
        ToastUtil.toast(FjActivity.this, "加载附件出错");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (webView != null) {
            webView.reload();
        }
    }


    @Override
    protected void onDestroy() {
        if (webView != null && webView.getVisibility() == View.VISIBLE) {
            webView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            webView.clearHistory();

            ((ViewGroup) webView.getParent()).removeView(webView);
            webView.destroy();
            webView = null;
        }
        super.onDestroy();
    }

    @Override
    public void onError(Throwable t) {
        ToastUtil.toast(FjActivity.this, "文件不是PDF格式或者已损坏");
    }
}
