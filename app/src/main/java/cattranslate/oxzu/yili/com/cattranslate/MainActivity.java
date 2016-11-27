package cattranslate.oxzu.yili.com.cattranslate;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import static cattranslate.oxzu.yili.com.cattranslate.R.id.btn_google;
import static cattranslate.oxzu.yili.com.cattranslate.R.id.btn_translate;
import static cattranslate.oxzu.yili.com.cattranslate.R.id.et_result;

//import static cattranslate.oxzu.yili.com.cattranslate.R.id.btn_Boom;


public class MainActivity extends Activity {

    private EditText etInput;
    private android.support.design.widget.FloatingActionButton btnTranslate;
    private EditText etResult;
    private Spinner spinnerFrom;
    private Spinner spinnerTo;
    private String from = "auto";
    private String to = "auto";
    private ClipboardManager clip;
    private Button btnAlert;
    private Button btnBoom;
    private Button btnGoogle;
    private CardView cardView;

    String[] language = {"auto", "zh", "en", "yue", "wyw", "jp", "kor", "fra", "spa", "th", "ara", "ru", "pt", "de", "it", "el", "nl", "pl", "bul", "est", "dan", "fin", "cs", "rom"
            , "slo", "swe", "hu", "cht"};

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAlert = (Button) findViewById(R.id.btnAlert);
        btnGoogle = (Button) findViewById(btn_google);
        //btnBoom = (Button) findViewById(btn_Boom);

        btnAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("关于   Jelly翻译")
                        .setMessage("果冻翻译\n" +
                                "作者：yili\n" +
                                "版本号：4.2\n" +
                                "编译日期：2016 11.27 22;00\n" +
                                "联系方式(QQ)：2510355993\n" +
                                "更新日志:\n" +
                                "修复一个很奇葩的Bug\n" +
                                "*采用了百度翻译API接口*\n")
                        .setNegativeButton("取消", null)
                        .setPositiveButton("确定", null).create().show();
            }
        });


        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick (View v) {
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this,Gwv.class);
                    startActivity(intent);

            }
        });

        etInput = (EditText) findViewById(R.id.et_input);
        btnTranslate = (android.support.design.widget.FloatingActionButton) findViewById(btn_translate);
        etResult = (EditText) findViewById(et_result);
        spinnerFrom = (Spinner) findViewById(R.id.spinner_from);
        spinnerTo = (Spinner) findViewById(R.id.spinner_to);

        btnTranslate.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                final String request = etInput.getText().toString();
                RequestUtils requestUtils = new RequestUtils();
                if (!request.isEmpty()) {
                    try {
                        requestUtils.translate(request, from, to, new HttpCallBack() {
                            @Override
                            public void onSuccess(String result) {
                                etResult.setText(result);
                            }
                            @Override
                            public void onFailure(String exception) {
                                etResult.setText(exception);
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Snackbar.make(btnTranslate, "已获取翻译结果", Snackbar.LENGTH_LONG)
                            .setAction("复制翻译结果", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ClipboardManager clip = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                                    clip.setText(etResult.getText());
                                    Snackbar.make(btnTranslate, "已复制翻译结果", Snackbar.LENGTH_LONG)
                                            .show();
                                }
                            })
                            .setActionTextColor(Color.GREEN)
                            .setDuration(4000).show();
                } else {
                    Snackbar.make(btnTranslate, "请输入要翻译的内容", Snackbar.LENGTH_LONG)
                            .show();

                }
            }
        });

        spinnerFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                from = language[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                to = language[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

}
