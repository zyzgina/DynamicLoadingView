package com.github.library;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Describe: 自定义加载动画
 * Created Author: Gina
 * Created Date: 2017/5/31.
 */

public class LoadingView extends LinearLayout {
    LinearLayout loading;
    private Context mContext;
    private int[] color = {android.R.color.holo_blue_bright, android.R.color.holo_green_dark, android.R.color.holo_orange_dark, android.R.color.holo_purple};
    private List<PaintingCircularView> views = new ArrayList<>();
    private Handler handler = new Handler();
    private boolean flag = false;
    private int duration = 1000;
    private TextView mTextLoading;

    public LoadingView(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }

    private void init() {
        View view = View.inflate(mContext, R.layout.loadingview, this);
        loading = (LinearLayout) view.findViewById(R.id.loading);
        mTextLoading = (TextView) view.findViewById(R.id.textLoading);
        setLoading(color);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public void setLoading(int[] color) {
        this.color = color;
        loading.removeAllViews();
        views.clear();
        for (int i = 0; i < color.length; i++) {
            PaintingCircularView circularView = new PaintingCircularView(mContext);
            circularView.setPaintColor(getResources().getColor(color[i]));
            loading.addView(circularView);
            views.add(circularView);
        }
    }

    public void setViews() {
        List<PaintingCircularView> paintingCircularViews = new ArrayList<>();
        for (int i = 1; i < views.size(); i++) {
            paintingCircularViews.add(views.get(i));
        }
        paintingCircularViews.add(views.get(0));
        views.clear();
        views.addAll(paintingCircularViews);
        loading.removeAllViews();
        for (PaintingCircularView view : views) {
            loading.addView(view);
        }
    }

    public void startAnimator() {
        flag = true;
        new Thread() {
            @Override
            public void run() {
                super.run();
                while (true) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            setViews();
                        }
                    });
                    try {
                        sleep(duration);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (!flag) {
                        break;
                    }
                }
            }
        }.start();
    }

    public void stopAnimator() {
        flag = false;
    }

    /* 设置动画改变的时间 */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void dismiss() {
        this.setVisibility(View.GONE);
    }

    public void show() {
        this.setVisibility(View.VISIBLE);
        if (!flag)
            startAnimator();
    }

    public void setText(String text) {
        this.mTextLoading.setText(text);
    }
}
