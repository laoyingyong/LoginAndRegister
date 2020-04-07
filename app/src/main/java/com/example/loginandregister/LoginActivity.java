package com.example.loginandregister;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 登录界面
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener
{
    private EditText editPerson, editCode;
    private TextView textViewR;
    private Button btn;
    private boolean autoLogin = false;
    public static String currentUsername;
    private String currentPassword;
    private boolean progressShow;
    private ImageView qq, weixin, weibo;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }


    private void init()
    {
        btn =findViewById(R.id.bn_common_login);
        btn.setOnClickListener(this);
        editCode =findViewById(R.id.et_password);
        editPerson =findViewById(R.id.et_username);
        textViewR = findViewById(R.id.tv_register);
        qq =  findViewById(R.id.iv_qq_login);
        weixin =  findViewById(R.id.iv_weixin_login);
        weibo = findViewById(R.id.iv_sina_login);
        qq.setOnClickListener(this);
        weixin.setOnClickListener(this);
        weibo.setOnClickListener(this);
        textViewR.setOnClickListener(this);
    }

    /**
     * 点击事件
     * */
    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.bn_common_login:  //登录按钮
                login(v);
                break;
            case R.id.tv_register:  //注册按钮
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.iv_qq_login:  //QQ登录
                Toast.makeText(this, "QQ登录", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_weixin_login:  //微信登录
                Toast.makeText(this, "微信登录", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_sina_login:    //微博登录
                Toast.makeText(this, "微博登录", Toast.LENGTH_SHORT).show();
                break;
                default:

        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        if (autoLogin)
        {
            return;
        }
    }

    /**
     * 登录
     *
     * @param view
     */
    public void login(View view)
    {

        currentUsername = editPerson.getText().toString().trim(); //去除空格，获取手机号
        currentPassword = editCode.getText().toString().trim();  //去除空格，获取密码

        if (TextUtils.isEmpty(currentUsername))
        { //判断手机号是不是为空
            Toast.makeText(this, "手机号不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(currentPassword))
        {  //判断密码是不是空
            Toast.makeText(this, "密码不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }

        progressShow = true;
        final ProgressDialog pd = new ProgressDialog(LoginActivity.this);  //初始化等待动画
        /**
         * 设置监听
         * */
        pd.setCanceledOnTouchOutside(false);
        pd.setOnCancelListener(new DialogInterface.OnCancelListener()
        {

            @Override
            public void onCancel(DialogInterface dialog)
            {
                progressShow = false;   //设置Boolean值为false
            }
        });
        pd.setMessage("正在登录....");  //等待动画的标题
        pd.show();  //显示等待动画

        new Thread(new Runnable()
        {
            public void run()
            {
                //在此处睡眠两秒
                try
                {
                    Thread.sleep(2000);  //在此处睡眠两秒
                } catch (InterruptedException e)
                {
                }

                /**
                 * 两秒之后
                 * */
                pd.dismiss();    //等待条消失
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);  //进入主界面
                startActivity(intent);  //开始跳转
                finish();  //finish掉此界面
            }
        }).start();  //开始线程

    }
}
