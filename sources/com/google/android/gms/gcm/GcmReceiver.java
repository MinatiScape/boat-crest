package com.google.android.gms.gcm;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Build;
import android.util.Base64;
import android.util.Log;
import androidx.legacy.content.WakefulBroadcastReceiver;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.iid.zzk;
import com.google.firebase.messaging.Constants;
import org.eclipse.paho.client.mqttv3.MqttTopic;
@Deprecated
/* loaded from: classes6.dex */
public class GcmReceiver extends WakefulBroadcastReceiver {
    public static zzk j;
    public static zzk k;

    public static int c(Context context, Intent intent) {
        ComponentName startService;
        ServiceInfo serviceInfo;
        String str;
        if (Log.isLoggable("GcmReceiver", 3)) {
            Log.d("GcmReceiver", "Starting service");
        }
        ResolveInfo resolveService = context.getPackageManager().resolveService(intent, 0);
        if (resolveService != null && (serviceInfo = resolveService.serviceInfo) != null) {
            if (context.getPackageName().equals(serviceInfo.packageName) && (str = serviceInfo.name) != null) {
                if (str.startsWith(".")) {
                    String valueOf = String.valueOf(context.getPackageName());
                    str = str.length() != 0 ? valueOf.concat(str) : new String(valueOf);
                }
                if (Log.isLoggable("GcmReceiver", 3)) {
                    String valueOf2 = String.valueOf(str);
                    Log.d("GcmReceiver", valueOf2.length() != 0 ? "Restricting intent to a specific service: ".concat(valueOf2) : new String("Restricting intent to a specific service: "));
                }
                intent.setClassName(context.getPackageName(), str);
            } else {
                String str2 = serviceInfo.packageName;
                String str3 = serviceInfo.name;
                StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 94 + String.valueOf(str3).length());
                sb.append("Error resolving target intent service, skipping classname enforcement. Resolved service was: ");
                sb.append(str2);
                sb.append(MqttTopic.TOPIC_LEVEL_SEPARATOR);
                sb.append(str3);
                Log.e("GcmReceiver", sb.toString());
            }
        } else {
            Log.e("GcmReceiver", "Failed to resolve target intent service, skipping classname enforcement");
        }
        try {
            if (context.checkCallingOrSelfPermission("android.permission.WAKE_LOCK") == 0) {
                startService = WakefulBroadcastReceiver.startWakefulService(context, intent);
            } else {
                startService = context.startService(intent);
                Log.d("GcmReceiver", "Missing wake lock permission, service start may be delayed");
            }
            if (startService == null) {
                Log.e("GcmReceiver", "Error while delivering the message: ServiceIntent not found.");
                return 404;
            }
            return -1;
        } catch (IllegalStateException e) {
            String valueOf3 = String.valueOf(e);
            StringBuilder sb2 = new StringBuilder(valueOf3.length() + 45);
            sb2.append("Failed to start service while in background: ");
            sb2.append(valueOf3);
            Log.e("GcmReceiver", sb2.toString());
            return 402;
        } catch (SecurityException e2) {
            Log.e("GcmReceiver", "Error while delivering the message to the serviceIntent", e2);
            return 401;
        }
    }

    public final int a(Context context, Intent intent) {
        if (Log.isLoggable("GcmReceiver", 3)) {
            Log.d("GcmReceiver", "Binding to service");
        }
        if (isOrderedBroadcast()) {
            setResultCode(-1);
        }
        b(context, intent.getAction()).zzd(intent, goAsync());
        return -1;
    }

    public final synchronized zzk b(Context context, String str) {
        if ("com.google.android.c2dm.intent.RECEIVE".equals(str)) {
            if (k == null) {
                k = new zzk(context, str);
            }
            return k;
        }
        if (j == null) {
            j = new zzk(context, str);
        }
        return j;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        int c;
        int i;
        if (Log.isLoggable("GcmReceiver", 3)) {
            Log.d("GcmReceiver", "received new intent");
        }
        intent.setComponent(null);
        intent.setPackage(context.getPackageName());
        if (Build.VERSION.SDK_INT <= 18) {
            intent.removeCategory(context.getPackageName());
        }
        if ("google.com/iid".equals(intent.getStringExtra("from"))) {
            intent.setAction("com.google.android.gms.iid.InstanceID");
        }
        String stringExtra = intent.getStringExtra("gcm.rawData64");
        if (stringExtra != null) {
            intent.putExtra(Constants.MessagePayloadKeys.RAW_DATA, Base64.decode(stringExtra, 0));
            intent.removeExtra("gcm.rawData64");
        }
        if (isOrderedBroadcast()) {
            setResultCode(500);
        }
        boolean z = PlatformVersion.isAtLeastO() && context.getApplicationInfo().targetSdkVersion >= 26;
        boolean z2 = (intent.getFlags() & 268435456) != 0;
        if (z && !z2) {
            i = a(context, intent);
        } else {
            if ("com.google.android.c2dm.intent.RECEIVE".equals(intent.getAction())) {
                c = c(context, intent);
            } else {
                c = c(context, intent);
            }
            if (PlatformVersion.isAtLeastO() && c == 402) {
                a(context, intent);
                i = 403;
            } else {
                i = c;
            }
        }
        if (isOrderedBroadcast()) {
            setResultCode(i);
        }
    }
}
