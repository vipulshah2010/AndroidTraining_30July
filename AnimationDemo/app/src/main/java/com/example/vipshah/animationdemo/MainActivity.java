package com.example.vipshah.animationdemo;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.alphaButton)
    Button alphaButton;
    @BindView(R.id.rotateButton)
    Button rotateButton;
    @BindView(R.id.translateButton)
    Button translateButton;
    @BindView(R.id.scaleButton)
    Button scaleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.alphaButton)
    void playAlphaAnimation(View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(alphaButton, View.ALPHA, 1, 0);
        animator.setDuration(800);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.start();
    }

    @OnClick(R.id.rotateButton)
    void playRotateAnimation(View view) {
        ObjectAnimator rotateAnimator = ObjectAnimator.ofFloat(rotateButton, View.ROTATION, 0, 360);
        ObjectAnimator scaleAnimatorX = ObjectAnimator.ofFloat(rotateButton, View.SCALE_X, 1, 2);
        ObjectAnimator scaleAnimatorY = ObjectAnimator.ofFloat(rotateButton, View.SCALE_Y, 1, 2);

        rotateAnimator.setRepeatMode(ValueAnimator.REVERSE);
        rotateAnimator.setRepeatCount(ValueAnimator.INFINITE);
        scaleAnimatorX.setRepeatMode(ValueAnimator.REVERSE);
        scaleAnimatorX.setRepeatCount(ValueAnimator.INFINITE);
        scaleAnimatorY.setRepeatMode(ValueAnimator.REVERSE);
        scaleAnimatorY.setRepeatCount(ValueAnimator.INFINITE);

        AnimatorSet set = new AnimatorSet();
        set.setDuration(800);
        set.playSequentially(rotateAnimator, scaleAnimatorX, scaleAnimatorY);
        set.start();
    }

    @OnClick(R.id.translateButton)
    void playTranslateAnimation(View view) {
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(translateButton, View.TRANSLATION_X, 0, 300);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(translateButton, View.TRANSLATION_Y, 0, 300);

        animatorX.setRepeatMode(ValueAnimator.REVERSE);
        animatorX.setRepeatCount(ValueAnimator.INFINITE);

        animatorY.setRepeatMode(ValueAnimator.REVERSE);
        animatorY.setRepeatCount(ValueAnimator.INFINITE);

        AnimatorSet set = new AnimatorSet();
        set.setDuration(800);
        set.playSequentially(animatorX, animatorY);
        set.start();
    }

    @OnClick(R.id.scaleButton)
    void playScaleAnimation(View view) {
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(scaleButton, View.SCALE_X, 1, 2);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(scaleButton, View.SCALE_Y, 1, 2);

        animatorX.setRepeatMode(ValueAnimator.REVERSE);
        animatorX.setRepeatCount(ValueAnimator.INFINITE);

        animatorY.setRepeatMode(ValueAnimator.REVERSE);
        animatorY.setRepeatCount(ValueAnimator.INFINITE);

        AnimatorSet set = new AnimatorSet();
        set.setDuration(800);
        set.playSequentially(animatorX, animatorY);
        set.start();
    }
}
