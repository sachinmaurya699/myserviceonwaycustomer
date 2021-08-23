package com.sossolution.serviceonway.Class;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.sossolution.serviceonway.Activity.MainActivity;
import com.sossolution.serviceonway.R;

import java.util.Map;
import java.util.Random;

public class Myservice extends FirebaseMessagingService
{

    private static final String TAG = "mymessage";
    RemoteMessage remoteMessage;
    Notification_Helper helper;
    Context context;


    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage)
    {
        super.onMessageReceived(remoteMessage);

       // Log.d("remote",remoteMessage.getNotification().getTitle());

        if (remoteMessage.getNotification() != null)
        {
            Log.d("v2_value",remoteMessage.getNotification().getBody()+"null");
            String title=remoteMessage.getNotification().getTitle();
            String body=remoteMessage.getNotification().getBody();
           // String image=remoteMessage.getNotification().getImageUrl().toString();
            helper= new Notification_Helper(this);
            helper.trigared(title,body);

        }
        else
        {
            Log.d("v1","not value");
        }


    }

    private void newnotification()
    {
       NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


        //Setting up Notification channels for android O and above
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O)
        {
            //setupChannels();
        }
        int notificationId = new Random().nextInt(60000);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this,"my_notification")
                .setSmallIcon(R.drawable.logo)  //a resource for your custom small icon
                .setContentTitle(remoteMessage.getData().get("title")) //the "title" value you sent in your notification
                .setContentText(remoteMessage.getData().get("body")) //ditto
                .setAutoCancel(true)  //dismisses the notification on click
                .setSound(defaultSoundUri);

        notificationManager.notify(notificationId /* ID of notification */, notificationBuilder.build());

    }

    private void setupChannels()
    {
        NotificationChannel adminChannel;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            adminChannel = new NotificationChannel("my_notification","my_notification", NotificationManager.IMPORTANCE_LOW);
            adminChannel.enableLights(true);
            adminChannel.setLightColor(Color.RED);
            adminChannel.enableVibration(true);


        }

    }


    private void notification1()
    {

        NotificationCompat.Builder notificationBuilder = new
                NotificationCompat.Builder(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            notificationBuilder.setSmallIcon(R.drawable.ic_add_circle_black_24dp);
        } else {
            notificationBuilder.setSmallIcon(R.mipmap.ic_launcher);
        }
        SharedPreferences preferences=getSharedPreferences("v1",MODE_PRIVATE);
        String value=preferences.getString("message","");
        notificationBuilder
                .setSmallIcon(R.drawable.logo)  //a resource for your custom small icon
                .setContentTitle(remoteMessage.getData().get("title")) //the "title" value you sent in your notification
                .setContentText(remoteMessage.getData().get("body")) //ditto
                 .setAutoCancel(true); //dismisses the notification on click


        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(1, notificationBuilder.build());

    }

    private void notification()
    {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            String name = getString(R.string.channel_name);
            int importance = NotificationManager.IMPORTANCE_HIGH; //Important for heads-up notification
            NotificationChannel channel = new NotificationChannel("My_Notification","My_Notification", importance);
            channel.setShowBadge(true);
            channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

        }

        /*Map<String, String> data = remoteMessage.getData();
        String myCustomKey = data.get("body");*/
        SharedPreferences preferences=getSharedPreferences("v1",MODE_PRIVATE);
        String value=preferences.getString("message","");
        Notification builder =
                new NotificationCompat.Builder(this,"My_Notification")
                        .setSmallIcon(R.drawable.logo)
                        .setContentTitle("ServiceOnway")
                        .setContentText("hello ji")
                        .setColor(Color.BLUE)
                        .build();


        /*Intent notificationIntent = new Intent(this, MainActivity.class);

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);*/

        NotificationManager manager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(122,builder);



    }


    @Override
    public void onNewToken(String token)
    {
        Log.d(TAG, "Refreshed token: " + token);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // FCM registration token to your app server.
        sendRegistrationToServer(token);
    }

    private void sendRegistrationToServer(String token)
    {
        Log.d("token_value",token.toString());
    }
}
