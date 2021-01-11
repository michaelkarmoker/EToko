package com.group.etoko.Service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.group.etoko.Activity.splashactivity.ui.SplashActivity;
import com.group.etoko.R;
import com.group.etoko.util.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

public class NotificationService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        JSONObject jsonObject=new JSONObject(remoteMessage.getData());
        try {
            JSONObject object=new JSONObject(jsonObject.getString("data"));
            Log.e("NotificationService","Error :"+object.toString());
            String title = object.getString("title");
            String msg = object.getString("message");
            String image =  object.getString("image");
            showNotification(title,msg,image);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("NotificationService","Error :"+e.getMessage());
        }
    }

    private void showNotification(String title,String message,String image) {

        if (title==null){
            title="Cholo Shop";
        }
        if (message==null){
            message="Message";
        }

        Uri uri=Uri.parse("android.resource://"+getApplicationContext().getPackageName()+"/"+R.raw.sound);
        PendingIntent intent=PendingIntent.getActivity(this,0,new Intent(this, SplashActivity.class),0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, Constants.NOTIFICATION_CHANEL)
                .setAutoCancel(true)
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setSound(uri)
                .setContentIntent(intent)
                ;

        if(image != null && !image.equals("")){
            try {
                URL url = new URL(image);
                Bitmap img = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                builder.setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(img)).build();
            } catch(IOException e) {
                Log.e("Image",e.getMessage()+"");
            }
        }

        int defaults = 0;
        defaults = defaults | Notification.DEFAULT_LIGHTS;
        defaults = defaults | Notification.DEFAULT_VIBRATE;

        builder.setDefaults(defaults);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel channel = new NotificationChannel(
                    Constants.NOTIFICATION_CHANEL,
                    "Notification",
                    NotificationManager.IMPORTANCE_HIGH);
            if(manager != null) {
                manager.createNotificationChannel(channel);
            }
            builder.setChannelId(Constants.NOTIFICATION_CHANEL);
        }

        if(manager != null) {
            manager.notify(0, builder.build());
        }
    }

}