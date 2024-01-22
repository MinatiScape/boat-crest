package com.mappls.sdk.navigation;

import android.app.Notification;
import com.mappls.sdk.navigation.apis.NavigationLogger;
import com.mappls.sdk.navigation.notifications.NavigationNotification;
import com.mappls.sdk.navigation.notifications.a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes11.dex */
public class NotificationHelper {

    /* renamed from: a  reason: collision with root package name */
    public NavigationApplication f12873a;
    public NavigationNotification b;
    public List<com.mappls.sdk.navigation.notifications.a> c = new ArrayList();

    public NotificationHelper(NavigationApplication navigationApplication) {
        this.f12873a = navigationApplication;
        b();
    }

    public final com.mappls.sdk.navigation.notifications.a a() {
        if (this.b.isEnabled()) {
            return this.b;
        }
        return null;
    }

    public final void b() {
        NavigationNotification navigationNotification = new NavigationNotification(this.f12873a);
        this.b = navigationNotification;
        this.c.add(navigationNotification);
    }

    public Notification buildTopNotification() {
        com.mappls.sdk.navigation.notifications.a a2 = a();
        if (a2 != null) {
            removeNotification(a2.getType());
            c(a2);
            return a2.buildNotification(false).build();
        }
        return null;
    }

    public final void c(com.mappls.sdk.navigation.notifications.a aVar) {
        Iterator<com.mappls.sdk.navigation.notifications.a> it = this.c.iterator();
        while (it.hasNext()) {
            com.mappls.sdk.navigation.notifications.a next = it.next();
            next.setTop(next == aVar);
        }
    }

    public void onNotificationDismissed(a.EnumC0640a enumC0640a) {
        for (com.mappls.sdk.navigation.notifications.a aVar : this.c) {
            if (aVar.getType() == enumC0640a) {
                aVar.onNotificationDismissed();
                return;
            }
        }
    }

    public void refreshNotification(a.EnumC0640a enumC0640a) {
        for (com.mappls.sdk.navigation.notifications.a aVar : this.c) {
            if (aVar.getType() == enumC0640a) {
                aVar.refreshNotification();
                return;
            }
        }
    }

    public void refreshNotifications() {
        this.b.refreshNotification();
    }

    public void removeNotification(a.EnumC0640a enumC0640a) {
        NavigationLogger.d("removeNotification(NotificationType notificationType)", new Object[0]);
        for (com.mappls.sdk.navigation.notifications.a aVar : this.c) {
            if (aVar.getType() == enumC0640a) {
                aVar.removeNotification();
                return;
            }
        }
    }

    public void removeNotifications() {
        NavigationLogger.d("removeNotifications", new Object[0]);
        for (com.mappls.sdk.navigation.notifications.a aVar : this.c) {
            aVar.removeNotification();
        }
    }

    public void showNotifications() {
        this.b.showNotification();
    }

    public void updateTopNotification() {
        c(a());
    }
}
