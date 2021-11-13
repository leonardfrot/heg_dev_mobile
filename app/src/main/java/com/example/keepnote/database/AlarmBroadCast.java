package com.example.keepnote.database;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;

import com.example.keepnote.notifications.NotificationHelper;


public class AlarmBroadCast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle b = intent.getExtras();

        NotificationHelper helper = new NotificationHelper(context);

        NotificationCompat.Builder nb = helper.getChannel1Notification(b.getString("titre"), b.getString("message"));

        helper.getManager().notify(0, nb.build());

    }
}
