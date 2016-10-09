package com.timaimee.heartviews;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * Created by TimAimee on 2016/10/7.
 */
public class HeartDetectView extends View {
    Context mContext;
    Paint mInnerPaint, mOuterPaint;
    private float mInnerProgress = 1f;
    private float mOutBarProgress = 0f;

    private float mInnerRadius=180;
    private float mOutterRadius=220;
    int mWidth;
    int mHeight;

    public HeartDetectView(Context context) {
        super(context);
    }

    public HeartDetectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
        this.mContext = context;
    }


    public void initPaint() {
        mInnerPaint = new Paint();
        mInnerPaint.setStrokeWidth(5);
        mInnerPaint.setColor(Color.RED);
        mInnerPaint.setStrokeCap(Paint.Cap.ROUND);
        mInnerPaint.setStyle(Paint.Style.FILL);
        mInnerPaint.setAntiAlias(true);
//        mInnerPaint.setXfermode(new
//                PorterDuffXfermode(PorterDuff.Mode.DST_OVER));

        mOuterPaint = new Paint();
        mOuterPaint.setStrokeWidth(50);
        mOuterPaint.setColor(Color.WHITE);
        mOuterPaint.setStrokeCap(Paint.Cap.ROUND);
        mOuterPaint.setStyle(Paint.Style.FILL);
        mOuterPaint.setAntiAlias(true);
//        mOuterPaint.setXfermode(new
//                PorterDuffXfermode(PorterDuff.Mode.XOR));

    }

    public void setInnerProgress(float innerProgress) {
        mInnerProgress = (float) Math.sin(Math.toRadians(innerProgress));
        invalidate();
    }

    public void setOutBarProgress(float outBarProgress) {
        mOutBarProgress = (float) Math.sin(Math.toRadians(outBarProgress));
        //正常是255，但是到外圆分辨率到外面不能太小，所以写了500
        mOuterPaint.setColor(Color.argb((int) (500 * (1 - mOutBarProgress)), 255, 255, 255));
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //外圆
        canvas.drawCircle(mWidth / 2, mHeight / 2, convertPixelsToDp(mOutterRadius/5) + convertPixelsToDp(mOutterRadius) * mOutBarProgress, mOuterPaint);

        //内圆
        canvas.drawCircle(mWidth / 2, mHeight / 2, convertPixelsToDp(mInnerRadius) * mInnerProgress, mInnerPaint);
    }


    public float convertDpToPixel(float dp) {
        Resources resources = mContext.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }

    public float convertPixelsToDp(float px) {
        Resources resources = mContext.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return dp;
    }
}
