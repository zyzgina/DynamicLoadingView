package com.github.library;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Describe: 画一个圆
 * Created Author: Gina
 * Created Date: 2017/5/31.
 */

public class PaintingCircularView  extends View {
    Paint paint;
    private int color=Color.RED;

    public PaintingCircularView(Context context) {
        super(context);
        paint = new Paint();
    }

    public PaintingCircularView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
    }

    public PaintingCircularView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(70,70);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int circleRadius= 30;
        paint.setColor(color);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle( 35, 35, circleRadius, paint);
    }

    public void setPaintColor(int color){
        this.color=color;
    }
}
