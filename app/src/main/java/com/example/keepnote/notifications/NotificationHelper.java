package com.example.keepnote.notifications;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;


import com.example.keepnote.R;
import com.example.keepnote.activities.CreateNoteActivity;
import com.example.keepnote.entities.Note;

public class NotificationHelper extends ContextWrapper {

    public static final String channelID = "Channel1ID";
    public static final String channelName = "Channel";

    private NotificationManager manager;

    public NotificationHelper(Context base) {
        super(base);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createChannel();
        }


    }
    @TargetApi(Build.VERSION_CODES.O)
    private void createChannel() {
        NotificationChannel channel = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_DEFAULT);
        channel.enableLights(true);
        channel.enableVibration(true);
        channel.setLightColor(R.color.colorPrimary);
        channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        getManager().createNotificationChannel(channel);
    }

    public NotificationManager getManager(){
        if (manager == null){
            manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }

        return manager;
    }

    // construction de la notification de la chaine 1, ici nous n'avons qu'une seule chaine
    public NotificationCompat.Builder getChannel1Notification(String title, String message, Note note){

        // c'est la qu'on décide quelle activité on doit ourrir, on remet la note en paramètre qui arrive jusqu'ici depuis saveNote au début
        Intent intent = new Intent(this, CreateNoteActivity.class);
        intent.putExtra("isViewOrUpdate", true);
        intent.putExtra("note", note);

        System.out.println(note);


        PendingIntent pd = PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        return new NotificationCompat.Builder(getApplicationContext(), channelID)
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.drawable.ic_image)
                .setAutoCancel(true)
                .setContentIntent(pd);

    }

}
