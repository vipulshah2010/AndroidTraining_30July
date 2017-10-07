package com.vipshah.httpurlconnectiondemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ImageDownloadingActivity extends AppCompatActivity {

    private final String IMAGE_URL_1 = "http://images.indianexpress.com/2017/02/msdhoni-m.jpg";
    private final String IMAGE_URL_2 = "https://i.pinimg.com/736x/cc/8a/11/cc8a11ec3932ceba7030f2e0a2c02dc4--picasso-paintings-picasso-cubism.jpg";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_downloading);

        ImageView imageView1 = findViewById(R.id.imageView1);
        ImageView imageView2 = findViewById(R.id.imageView2);

        Picasso
                .with(this)
                .load(IMAGE_URL_1)
                .fit()
                .into(imageView1);

        Picasso
                .with(this)
                .load(IMAGE_URL_2)
                .fit()
                .into(imageView2);

    }
}
