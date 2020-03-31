package com.news.extra;

import android.content.Context;
import android.content.Intent;
import com.news.activity.Notification;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;
import org.json.JSONObject;

public class NotificationHandler implements OneSignal.NotificationOpenedHandler {

    private Context context;

    NotificationHandler(Context context) {
        this.context = context;
    }

    @Override
    public void notificationOpened(OSNotificationOpenResult result) {
        JSONObject data = result.notification.payload.additionalData;
        if (data != null){
            String url = data.optString("url");
            Intent intent = new Intent(context, Notification.class);
            intent.putExtra("url",url);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
}
