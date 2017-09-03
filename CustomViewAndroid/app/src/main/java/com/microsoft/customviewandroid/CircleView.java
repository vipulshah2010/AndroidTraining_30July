package com.microsoft.customviewandroid;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

public class CircleView extends View {

    private Paint paint;
    private float x;
    private float y;
    private int r;
    private int g;
    private int b;
    private int radius;

    public CircleView(Context context) {
        super(context);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    public void setCenter(float x, float y) {
        Random random = new Random();

        this.x = random.nextInt((int) x);
        this.y = random.nextInt((int) y);

        r = random.nextInt(255);
        g = random.nextInt(255);
        b = random.nextInt(255);
        invalidate();
    }

    private void init(AttributeSet attrs) {

        TypedArray array = getContext().obtainStyledAttributes(attrs,
                R.styleable.CircleView, 0, 0);

        try {
            int rColor = array.getInt(R.styleable.CircleView_circle_color_r, 0);
            int gColor = array.getInt(R.styleable.CircleView_circle_color_g, 0);
            int bColor = array.getInt(R.styleable.CircleView_circle_color_b, 0);
            int type = array.getInt(R.styleable.CircleView_circle_type, 0);
            int radiusRes = array.getResourceId(R.styleable.CircleView_circle_radius,
                    R.dimen.default_radius);

            radius = getContext().getResources().getDimensionPixelSize(radiusRes);

            r = rColor;
            g = gColor;
            b = bColor;
            paint = new Paint();
            paint.setStrokeWidth(10);
            paint.setStyle(type == 0 ? Paint.Style.FILL : Paint.Style.STROKE);
            paint.setAntiAlias(true);
        } finally {
            array.recycle();
        }
    }

    private String getHexColor(int r, int g, int b) {
        return String.format("#%02x%02x%02x", r, g, b);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        String color = getHexColor(r, g, b);
        paint.setColor(Color.parseColor(color));
        canvas.drawCircle(x, y, radius, paint);
    }
}
