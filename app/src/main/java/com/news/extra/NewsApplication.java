package com.news.extra;

import android.app.Application;
import com.onesignal.OneSignal;

public class NewsApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .setNotificationOpenedHandler(new NotificationHandler(this))
                .setNotificationReceivedHandler(new NotificationApiHandler(this))
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
    }


}
