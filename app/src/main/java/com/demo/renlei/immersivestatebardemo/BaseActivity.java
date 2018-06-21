package com.demo.renlei.immersivestatebardemo;


import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * rl 2018/06/20
 * 封装一个BaseActivity
 */
public abstract class BaseActivity extends AppCompatActivity {
    public  Context context ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        hideStatuBar();
        setActionBar();
    }

    /**
     * 顶部导航栏
     */
    public  void setActionBar() {
    }

    /**
     * 获取用户信息
     *
     * @return 用户ID
     */
    public int getUserId() {
        return 0;
    }

    /**
     * 状态栏透明
     */
    private void hideStatuBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            ActionBar actionBar = getSupportActionBar();
            actionBar.hide();
        }
    }

    /**
     * 弹出Toast
     * @param msg 消息内容
     * @param code 代表Toast长短的参数
     */
    public void toast(String msg,int code){
        if(code == Toast.LENGTH_LONG){
                Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
        }
        if(code == Toast.LENGTH_SHORT){
            Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 弹出Toast
     * @param resId 资源ID
     * @param code 状态码，代表Toast长短的参数
     */
    public void toast(int resId,int code){
        if(code == Toast.LENGTH_LONG){
            Toast.makeText(context,(getString(resId)),Toast.LENGTH_LONG).show();
        }
        if(code == Toast.LENGTH_SHORT){
            Toast.makeText(context,getString(resId),Toast.LENGTH_SHORT).show();
        }
    }
}
