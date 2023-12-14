package com.proj.padoapp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;

public class NotificationHelper {
    private static final String CHANNEL_ID = "1";
    private static final String CHANNEL_NAME = "notification";

    public static void createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_HIGH);
            NotificationManager manager = context.getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }

    public static void showNotification(Context context, String title, String message, String bigmessage, String bigtitle) {
        Notification.Builder builder;
        builder = new Notification.Builder(context, CHANNEL_ID);

        Bitmap largeicon = BitmapFactory.decodeResource(context.getResources(), R.drawable.pado_logo_white);

        builder.setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.drawable.black_notify)
                .setStyle(new Notification.BigTextStyle()
                        .setBigContentTitle(bigtitle)
                        .bigText(bigmessage))
                .setLargeIcon(largeicon);

        Intent signInIntent = new Intent(context, MainActivity.class);
        signInIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent signInPendingIntent = PendingIntent.getActivity(context, 0, signInIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        builder.setContentIntent(signInPendingIntent);

        NotificationManager manager = context.getSystemService(NotificationManager.class);
        manager.notify(1, builder.build());
    }

}
