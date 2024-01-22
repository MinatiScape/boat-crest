package com.google.firebase.messaging;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.util.Log;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.ArrayDeque;
import java.util.Queue;
import javax.annotation.concurrent.GuardedBy;
import org.eclipse.paho.client.mqttv3.MqttTopic;
@KeepForSdk
/* loaded from: classes10.dex */
public class ServiceStarter {
    @KeepForSdk
    public static final int ERROR_UNKNOWN = 500;
    public static final int SUCCESS = -1;
    public static ServiceStarter e;
    @Nullable
    @GuardedBy("this")

    /* renamed from: a  reason: collision with root package name */
    public String f11335a = null;
    public Boolean b = null;
    public Boolean c = null;
    public final Queue<Intent> d = new ArrayDeque();

    public static synchronized ServiceStarter b() {
        ServiceStarter serviceStarter;
        synchronized (ServiceStarter.class) {
            if (e == null) {
                e = new ServiceStarter();
            }
            serviceStarter = e;
        }
        return serviceStarter;
    }

    @VisibleForTesting
    public static void setForTesting(@NonNull ServiceStarter serviceStarter) {
        e = serviceStarter;
    }

    public final int a(Context context, Intent intent) {
        ComponentName startService;
        String f = f(context, intent);
        if (f != null) {
            if (Log.isLoggable(Constants.TAG, 3)) {
                Log.d(Constants.TAG, f.length() != 0 ? "Restricting intent to a specific service: ".concat(f) : new String("Restricting intent to a specific service: "));
            }
            intent.setClassName(context.getPackageName(), f);
        }
        try {
            if (e(context)) {
                startService = t0.e(context, intent);
            } else {
                startService = context.startService(intent);
                Log.d(Constants.TAG, "Missing wake lock permission, service start may be delayed");
            }
            if (startService == null) {
                Log.e(Constants.TAG, "Error while delivering the message: ServiceIntent not found.");
                return 404;
            }
            return -1;
        } catch (IllegalStateException e2) {
            String valueOf = String.valueOf(e2);
            StringBuilder sb = new StringBuilder(valueOf.length() + 45);
            sb.append("Failed to start service while in background: ");
            sb.append(valueOf);
            Log.e(Constants.TAG, sb.toString());
            return 402;
        } catch (SecurityException e3) {
            Log.e(Constants.TAG, "Error while delivering the message to the serviceIntent", e3);
            return 401;
        }
    }

    @MainThread
    public Intent c() {
        return this.d.poll();
    }

    public boolean d(Context context) {
        if (this.c == null) {
            this.c = Boolean.valueOf(context.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") == 0);
        }
        if (!this.b.booleanValue() && Log.isLoggable(Constants.TAG, 3)) {
            Log.d(Constants.TAG, "Missing Permission: android.permission.ACCESS_NETWORK_STATE this should normally be included by the manifest merger, but may needed to be manually added to your manifest");
        }
        return this.c.booleanValue();
    }

    public boolean e(Context context) {
        if (this.b == null) {
            this.b = Boolean.valueOf(context.checkCallingOrSelfPermission("android.permission.WAKE_LOCK") == 0);
        }
        if (!this.b.booleanValue() && Log.isLoggable(Constants.TAG, 3)) {
            Log.d(Constants.TAG, "Missing Permission: android.permission.WAKE_LOCK this should normally be included by the manifest merger, but may needed to be manually added to your manifest");
        }
        return this.b.booleanValue();
    }

    @Nullable
    public final synchronized String f(Context context, Intent intent) {
        ServiceInfo serviceInfo;
        String str;
        String str2 = this.f11335a;
        if (str2 != null) {
            return str2;
        }
        ResolveInfo resolveService = context.getPackageManager().resolveService(intent, 0);
        if (resolveService != null && (serviceInfo = resolveService.serviceInfo) != null) {
            if (context.getPackageName().equals(serviceInfo.packageName) && (str = serviceInfo.name) != null) {
                if (str.startsWith(".")) {
                    String valueOf = String.valueOf(context.getPackageName());
                    String valueOf2 = String.valueOf(serviceInfo.name);
                    this.f11335a = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
                } else {
                    this.f11335a = serviceInfo.name;
                }
                return this.f11335a;
            }
            String str3 = serviceInfo.packageName;
            String str4 = serviceInfo.name;
            StringBuilder sb = new StringBuilder(String.valueOf(str3).length() + 94 + String.valueOf(str4).length());
            sb.append("Error resolving target intent service, skipping classname enforcement. Resolved service was: ");
            sb.append(str3);
            sb.append(MqttTopic.TOPIC_LEVEL_SEPARATOR);
            sb.append(str4);
            Log.e(Constants.TAG, sb.toString());
            return null;
        }
        Log.e(Constants.TAG, "Failed to resolve target intent service, skipping classname enforcement");
        return null;
    }

    @MainThread
    public int startMessagingService(@NonNull Context context, @NonNull Intent intent) {
        if (Log.isLoggable(Constants.TAG, 3)) {
            Log.d(Constants.TAG, "Starting service");
        }
        this.d.offer(intent);
        Intent intent2 = new Intent(com.google.firebase.iid.ServiceStarter.ACTION_MESSAGING_EVENT);
        intent2.setPackage(context.getPackageName());
        return a(context, intent2);
    }
}
