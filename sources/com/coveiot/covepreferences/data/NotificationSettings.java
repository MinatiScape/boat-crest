package com.coveiot.covepreferences.data;
/* loaded from: classes8.dex */
public class NotificationSettings {
    public static NotificationSettings d;

    /* renamed from: a  reason: collision with root package name */
    public boolean f7033a;
    public boolean b;
    public boolean c;

    public static NotificationSettings getInstance() {
        NotificationSettings notificationSettings = new NotificationSettings();
        d = notificationSettings;
        return notificationSettings;
    }

    public boolean isApp_notifications() {
        return this.c;
    }

    public boolean isCall_notifications() {
        return this.f7033a;
    }

    public boolean isSms_notifications() {
        return this.b;
    }

    public void setApp_notifications(boolean z) {
        this.c = z;
    }

    public void setCall_notifications(boolean z) {
        this.f7033a = z;
    }

    public void setSms_notifications(boolean z) {
        this.b = z;
    }
}
