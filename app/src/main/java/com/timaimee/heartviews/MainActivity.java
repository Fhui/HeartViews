package com.timaimee.heartviews;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.animation.AccelerateDecelerateInterpolator;


public class MainActivity extends ActionBarActivity {

    HeartDetectView heartDetectView;
    ObjectAnimator objectinner, objectOutter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        heartDetectView = (HeartDetectView) findViewById(R.id.rate_detect_view);
        startAnimation();
    }


    private void startAnimation() {
        userObjectAnimatorOuter();
        userObjectAnimatorInner();
    }

    /**
     * 内圆动画
     */
    private void userObjectAnimatorInner() {
        objectinner = ObjectAnimator.ofFloat(heartDetectView, "innerProgress", 60f, 120f).setDuration(3000);
        objectinner.setRepeatMode(ValueAnimator.RESTART);
        objectinner.setRepeatCount(ValueAnimator.INFINITE);
        objectinner.setInterpolator(new AccelerateDecelerateInterpolator());
        objectinner.start();
    }

    /**
     * 外圆动画
     */
    private void userObjectAnimatorOuter() {
        objectOutter = ObjectAnimator.ofFloat(heartDetectView, "outBarProgress", 0f, 90f).setDuration(3000);
        objectOutter.setRepeatMode(ValueAnimator.RESTART);
        objectOutter.setRepeatCount(ValueAnimator.INFINITE);
        objectOutter.setInterpolator(new AccelerateDecelerateInterpolator());
        objectOutter.start();
    }

}
