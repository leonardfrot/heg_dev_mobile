package com.example.keepnote.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;

import com.example.keepnote.entities.Note;
import com.example.keepnote.notifications.NotificationHelper;


public class AlarmBroadCast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle b = intent.getExtras();
        Bundle b2 = intent.getBundleExtra("bundle");

        Note note = (Note)b2.getSerializable("note");

        NotificationHelper helper = new NotificationHelper(context);

        NotificationCompat.Builder nb = helper.getChannel1Notification(b.getString("titre"), b.getString("message"), note);
        helper.getManager().notify(0, nb.build());







    }
}
