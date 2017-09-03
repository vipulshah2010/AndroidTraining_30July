package com.microsoft.customviewandroid;

import android.content.Context;
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

    public CircleView(Context context) {
        super(context);
        init();
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
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

    private void init() {
        r = 255;
        g = 0;
        b = 0;
        paint = new Paint();
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setAntiAlias(true);
    }

    private String getHexColor(int r, int g, int b) {
        return String.format("#%02x%02x%02x", r, g, b);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        String color = getHexColor(r, g, b);
        paint.setColor(Color.parseColor(color));
        canvas.drawCircle(x, y, 50, paint);
    }
}
