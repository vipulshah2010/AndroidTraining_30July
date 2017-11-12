package com.example.vipshah.notificationchannelsdemo;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String PROMOTION_CHANNEL = "promotion";
    private static final String ACCOUNT_CHANNEL = "account";

    private NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        notificationManager = (NotificationManager)
                getSystemService(Context.NOTIFICATION_SERVICE);

        createChannel();
    }

    private void createChannel() {
        NotificationChannel promotionalChannel = new NotificationChannel(PROMOTION_CHANNEL, getString(R.string.promotion),
                NotificationManager.IMPORTANCE_LOW);
        promotionalChannel.setLightColor(Color.GREEN);
        promotionalChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
        notificationManager.createNotificationChannel(promotionalChannel);

        NotificationChannel accountChannel = new NotificationChannel(ACCOUNT_CHANNEL, getString(R.string.account),
                NotificationManager.IMPORTANCE_HIGH);
        accountChannel.setLightColor(Color.RED);
        accountChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        notificationManager.createNotificationChannel(accountChannel);
    }

    @OnClick(R.id.settings)
    void launchSettings(View view) {
        Intent intent = new Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
        intent.putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
        startActivity(intent);
    }

    @OnClick(R.id.promotionNotificationChannel1Button)
    void showChannel1Notification() {
        Notification notification = new Notification.Builder(getApplicationContext(), PROMOTION_CHANNEL)
                .setContentTitle("Credit Card Offer")
                .setContentText("Win credit card at no charge!")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setAutoCancel(true).build();
        notificationManager.notify(123, notification);
    }

    @OnClick(R.id.accountNotificationChannel2Button)
    void showChannel2Notification() {
        Notification notification = new Notification.Builder(getApplicationContext(), ACCOUNT_CHANNEL)
                .setContentTitle("Amount Debited")
                .setContentText("2000 INR debited from your account!")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setAutoCancel(true).build();
        notificationManager.notify(456, notification);
    }
}
