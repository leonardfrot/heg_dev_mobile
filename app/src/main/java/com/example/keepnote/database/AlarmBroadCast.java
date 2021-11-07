package com.example.keepnote.database;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import com.example.keepnote.notifications.NotificationHelper;


public class AlarmBroadCast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationHelper helper = new NotificationHelper(context);

        NotificationCompat.Builder nb = helper.getChannel1Notification("titre", "message");

        helper.getManager().notify(1, nb.build());

    }
}
