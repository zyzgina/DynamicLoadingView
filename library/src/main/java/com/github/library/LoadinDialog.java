package com.github.library;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.WindowManager;

/**
 * Describe: 加载对话框
 * Created Author: Gina
 * Created Date: 2017/5/31.
 */

public class LoadinDialog extends Dialog {
    LoadingView loadingView;
    WindowManager m;
    int[] color = {android.R.color.holo_orange_light, android.R.color.holo_red_light, android.R.color.holo_green_light, android.R.color.holo_purple,
            android.R.color.holo_orange_light, android.R.color.holo_red_light, android.R.color.holo_green_light, android.R.color.holo_purple};
    private Handler handler=new Handler();
    private int num=5;
    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            num--;
            handler.post(new Runnable() {
                @Override
                public void run() {
                    if(num==0){
                        handler.removeCallbacks(runnable);
                        dismiss();
                    }
                }
            });
            //要做的事情
            handler.postDelayed(this, 1000);
        }
    };

    public LoadinDialog(Context context) {
        super(context);
        m = ((Activity)context).getWindowManager();
        //点击提示框外面是否取消提示框
        setCanceledOnTouchOutside(false);
        //点击返回键是否取消提示框
        setCancelable(false);
        runnable.run();
    }

    public LoadinDialog(Context context, int themeResId) {
        super(context, themeResId);
        m = ((Activity)context).getWindowManager();
        //点击提示框外面是否取消提示框
        setCanceledOnTouchOutside(false);
        //点击返回键是否取消提示框
        setCancelable(false);
        runnable.run();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loading);
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = this.getWindow().getAttributes(); // 获取对话框当前的参数值
        p.width = (int) (d.getWidth() * 0.9); // 宽度设置为屏幕的0.95
        this.getWindow().setAttributes(p);

        initView();
    }

    private void initView() {
        loadingView = (LoadingView) findViewById(R.id.loadingView);
        loadingView.setLoading(color);
        loadingView.show();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        loadingView.stopAnimator();
    }

    public void setShowTime(int time){
        this.num=time;
    }
    public void setColor(int[] color){
        this.color=color;
    }
}
