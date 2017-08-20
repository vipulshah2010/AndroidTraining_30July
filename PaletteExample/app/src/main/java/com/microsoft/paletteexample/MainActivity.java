package com.microsoft.paletteexample;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.widget.ImageView;

import static android.support.v7.graphics.Palette.PaletteAsyncListener;
import static android.support.v7.graphics.Palette.Swatch;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = findViewById(R.id.imageView);

        final View view1 = findViewById(R.id.view1);
        final View view2 = findViewById(R.id.view2);
        final View view3 = findViewById(R.id.view3);
        final View view4 = findViewById(R.id.view4);
        final View view5 = findViewById(R.id.view5);
        final View view6 = findViewById(R.id.view6);

        BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();

        Palette.from(bitmap).generate(new PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                setBackground(view1, palette.getDarkMutedSwatch());
                setBackground(view2, palette.getLightMutedSwatch());
                setBackground(view3, palette.getMutedSwatch());
                setBackground(view4, palette.getDarkVibrantSwatch());
                setBackground(view5, palette.getLightVibrantSwatch());
                setBackground(view6, palette.getVibrantSwatch());
            }
        });
    }

    void setBackground(View view, Swatch swatch) {
        if (swatch != null) {
            view.setBackgroundColor(swatch.getRgb());
        } else {
            view.setVisibility(View.GONE);
        }
    }
}
