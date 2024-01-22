package com.google.firebase.iid;

import android.content.Context;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.annotation.KeepForSdk;
@KeepForSdk
/* loaded from: classes10.dex */
public class ServiceStarter {
    public static final String ACTION_MESSAGING_EVENT = "com.google.firebase.MESSAGING_EVENT";
    @KeepForSdk
    public static final int ERROR_UNKNOWN = 500;
    public static ServiceStarter c;

    /* renamed from: a  reason: collision with root package name */
    public Boolean f11287a = null;
    public Boolean b = null;

    @KeepForSdk
    public static synchronized ServiceStarter getInstance() {
        ServiceStarter serviceStarter;
        synchronized (ServiceStarter.class) {
            if (c == null) {
                c = new ServiceStarter();
            }
            serviceStarter = c;
        }
        return serviceStarter;
    }

    @VisibleForTesting
    public static void setForTesting(ServiceStarter serviceStarter) {
        c = serviceStarter;
    }

    public boolean a(Context context) {
        if (this.b == null) {
            this.b = Boolean.valueOf(context.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") == 0);
        }
        if (!this.f11287a.booleanValue() && Log.isLoggable("FirebaseInstanceId", 3)) {
            Log.d("FirebaseInstanceId", "Missing Permission: android.permission.ACCESS_NETWORK_STATE this should normally be included by the manifest merger, but may needed to be manually added to your manifest");
        }
        return this.b.booleanValue();
    }

    public boolean b(Context context) {
        if (this.f11287a == null) {
            this.f11287a = Boolean.valueOf(context.checkCallingOrSelfPermission("android.permission.WAKE_LOCK") == 0);
        }
        if (!this.f11287a.booleanValue() && Log.isLoggable("FirebaseInstanceId", 3)) {
            Log.d("FirebaseInstanceId", "Missing Permission: android.permission.WAKE_LOCK this should normally be included by the manifest merger, but may needed to be manually added to your manifest");
        }
        return this.f11287a.booleanValue();
    }
}
