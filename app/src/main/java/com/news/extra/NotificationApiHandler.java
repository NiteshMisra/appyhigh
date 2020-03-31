package com.news.extra;

import android.content.Context;
import com.onesignal.OSNotification;
import com.onesignal.OneSignal;
import org.json.JSONObject;

public class NotificationApiHandler implements OneSignal.NotificationReceivedHandler {

    private Context context;

    NotificationApiHandler(Context context) {
        this.context = context;
    }

    @Override
    public void notificationReceived(OSNotification notification) {
        JSONObject data = notification.payload.additionalData;
        if (data != null){
            String url = data.optString("url");
        }
    }
}
