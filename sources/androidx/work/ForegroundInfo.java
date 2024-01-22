package androidx.work;

import android.app.Notification;
import androidx.annotation.NonNull;
/* loaded from: classes.dex */
public final class ForegroundInfo {

    /* renamed from: a  reason: collision with root package name */
    public final int f1777a;
    public final int b;
    public final Notification c;

    public ForegroundInfo(int i, @NonNull Notification notification) {
        this(i, notification, 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ForegroundInfo.class != obj.getClass()) {
            return false;
        }
        ForegroundInfo foregroundInfo = (ForegroundInfo) obj;
        if (this.f1777a == foregroundInfo.f1777a && this.b == foregroundInfo.b) {
            return this.c.equals(foregroundInfo.c);
        }
        return false;
    }

    public int getForegroundServiceType() {
        return this.b;
    }

    @NonNull
    public Notification getNotification() {
        return this.c;
    }

    public int getNotificationId() {
        return this.f1777a;
    }

    public int hashCode() {
        return (((this.f1777a * 31) + this.b) * 31) + this.c.hashCode();
    }

    public String toString() {
        return "ForegroundInfo{mNotificationId=" + this.f1777a + ", mForegroundServiceType=" + this.b + ", mNotification=" + this.c + '}';
    }

    public ForegroundInfo(int i, @NonNull Notification notification, int i2) {
        this.f1777a = i;
        this.c = notification;
        this.b = i2;
    }
}
