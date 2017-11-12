package com.example.vipshah.springanimations;

import android.os.Bundle;
import android.support.animation.DynamicAnimation;
import android.support.animation.FlingAnimation;
import android.support.animation.FloatPropertyCompat;
import android.support.animation.SpringAnimation;
import android.support.animation.SpringForce;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    int value = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.flingAnimation)
    public void flingIt(View view) {
        ImageView emoji = findViewById(R.id.emojiView);

        FlingAnimation flingAnimation
                = new FlingAnimation(emoji, DynamicAnimation.Y);
        flingAnimation.setStartVelocity(1500);
        flingAnimation.setFriction(0.5f);
        flingAnimation.start();
    }

    @OnClick(R.id.bounceAnimation)
    public void bounce(View view) {
        final ImageView emoji = findViewById(R.id.emojiView);

        emoji.setImageResource(R.drawable.ic_svg_sad);
        SpringAnimation springAnimation
                = new SpringAnimation(emoji, DynamicAnimation.Y);

        SpringForce springForce = new SpringForce();
        springForce.setFinalPosition(emoji.getY());
        springForce.setDampingRatio(SpringForce.DAMPING_RATIO_HIGH_BOUNCY); // Bounce Factor
        springForce.setStiffness(SpringForce.STIFFNESS_LOW);

        springAnimation.setSpring(springForce);

        springAnimation.setStartVelocity(2000);
        springAnimation.start();

        emoji.setImageResource(R.drawable.ic_svg_smile);
        springAnimation.addEndListener(new DynamicAnimation.OnAnimationEndListener() {
            @Override
            public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value,
                                       float velocity) {
                emoji.setImageResource(R.drawable.ic_svg_sad);
            }
        });
    }

    @OnClick(R.id.stretchAnimation)
    public void stretch(View view) {
        final ImageView emoji = findViewById(R.id.emojiView);

        SpringAnimation rotateAnimation =
                new SpringAnimation(emoji, new FloatPropertyCompat<ImageView>("rotate") {
                    @Override
                    public float getValue(ImageView imageView) {
                        return imageView.getRotationX();
                    }

                    @Override
                    public void setValue(ImageView imageView, float value) {
                        imageView.setRotationX(value);
                        //  imageView.setRotationY(value);
                    }
                });

        rotateAnimation.setMinimumVisibleChange(
                DynamicAnimation.MIN_VISIBLE_CHANGE_ROTATION_DEGREES);

        value = value == 0 ? 360 : 0;
        final SpringForce force = new SpringForce(value);
        force.setDampingRatio(SpringForce.DAMPING_RATIO_NO_BOUNCY)
                .setStiffness(SpringForce.STIFFNESS_VERY_LOW);

        rotateAnimation.setSpring(force)
                .setStartVelocity(300);

        rotateAnimation.start();
    }
}
