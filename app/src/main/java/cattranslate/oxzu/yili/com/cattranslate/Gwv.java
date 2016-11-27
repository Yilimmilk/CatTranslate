package cattranslate.oxzu.yili.com.cattranslate;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import static cattranslate.oxzu.yili.com.cattranslate.R.id.gwv_g;
import static cattranslate.oxzu.yili.com.cattranslate.R.id.gwv_reload;

public class Gwv extends Activity {

    private WebView webView;
    private Button gwvre;
    private Button gwvg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gwv_layout);
        webView = (WebView) findViewById(R.id.gwv_webview);
        webView.loadUrl("http://translate.google.cn/");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        webView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
                        webView.goBack();
                        return true;
                    }
                }
                return false;
            }
        });

        gwvre = (Button) findViewById(gwv_reload);
        gwvre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.loadUrl("http://translate.google.cn/");
                Snackbar.make(view, "已重新加载此页面", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        gwvg = (Button) findViewById(gwv_g);
        gwvg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(Gwv.this)
                        .setTitle("类似于彩蛋的东东")
                        .setMessage("正在加载GOOGLE代理搜索网页....")
                        .setPositiveButton("确定", null).create().show();
                webView.loadUrl("http://so.biochen.com/");
            }
        });

    }
    }
