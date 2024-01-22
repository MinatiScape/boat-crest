package com.google.android.gms.gcm;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import androidx.annotation.RequiresPermission;
import androidx.collection.ArrayMap;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.iid.InstanceID;
import com.google.android.gms.iid.zzaf;
import com.google.firebase.messaging.Constants;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
@Deprecated
/* loaded from: classes6.dex */
public class GoogleCloudMessaging {
    public static final String ERROR_MAIN_THREAD = "MAIN_THREAD";
    public static final String ERROR_SERVICE_NOT_AVAILABLE = "SERVICE_NOT_AVAILABLE";
    public static final String INSTANCE_ID_SCOPE = "GCM";
    @Deprecated
    public static final String MESSAGE_TYPE_DELETED = "deleted_messages";
    @Deprecated
    public static final String MESSAGE_TYPE_MESSAGE = "gcm";
    @Deprecated
    public static final String MESSAGE_TYPE_SEND_ERROR = "send_error";
    @Deprecated
    public static final String MESSAGE_TYPE_SEND_EVENT = "send_event";
    public static GoogleCloudMessaging f;
    public static final AtomicInteger g = new AtomicInteger(1);

    /* renamed from: a  reason: collision with root package name */
    public Context f8476a;
    public PendingIntent b;
    public final Map<String, Handler> c = Collections.synchronizedMap(new ArrayMap());
    public final BlockingQueue<Intent> d = new LinkedBlockingQueue();
    public final Messenger e = new Messenger(new c(this, Looper.getMainLooper()));

    public static void g(Context context) {
        String packageName = context.getPackageName();
        StringBuilder sb = new StringBuilder(String.valueOf(packageName).length() + 48);
        sb.append("GCM SDK is deprecated, ");
        sb.append(packageName);
        sb.append(" should update to use FCM");
        Log.w("GCM", sb.toString());
    }

    @Deprecated
    public static synchronized GoogleCloudMessaging getInstance(Context context) {
        GoogleCloudMessaging googleCloudMessaging;
        synchronized (GoogleCloudMessaging.class) {
            if (f == null) {
                g(context);
                GoogleCloudMessaging googleCloudMessaging2 = new GoogleCloudMessaging();
                f = googleCloudMessaging2;
                googleCloudMessaging2.f8476a = context.getApplicationContext();
            }
            googleCloudMessaging = f;
        }
        return googleCloudMessaging;
    }

    public static int zzf(Context context) {
        String zzl = zzaf.zzl(context);
        if (zzl != null) {
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(zzl, 0);
                if (packageInfo != null) {
                    return packageInfo.versionCode;
                }
                return -1;
            } catch (PackageManager.NameNotFoundException unused) {
                return -1;
            }
        }
        return -1;
    }

    @Deprecated
    public final Intent a(Bundle bundle, boolean z) throws IOException {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            if (zzf(this.f8476a) >= 0) {
                Intent intent = new Intent(z ? "com.google.iid.TOKEN_REQUEST" : "com.google.android.c2dm.intent.REGISTER");
                intent.setPackage(zzaf.zzl(this.f8476a));
                h(intent);
                int andIncrement = g.getAndIncrement();
                StringBuilder sb = new StringBuilder(21);
                sb.append("google.rpc");
                sb.append(andIncrement);
                intent.putExtra(Constants.MessagePayloadKeys.MSGID, sb.toString());
                intent.putExtras(bundle);
                intent.putExtra("google.messenger", this.e);
                if (z) {
                    this.f8476a.sendBroadcast(intent);
                } else {
                    this.f8476a.startService(intent);
                }
                try {
                    return this.d.poll(30000L, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    throw new IOException(e.getMessage());
                }
            }
            throw new IOException("Google Play Services missing");
        }
        throw new IOException("MAIN_THREAD");
    }

    @ShowFirstParty
    @VisibleForTesting
    @Deprecated
    public final synchronized String b(boolean z, String... strArr) throws IOException {
        String zzl = zzaf.zzl(this.f8476a);
        if (zzl != null) {
            if (strArr != null && strArr.length != 0) {
                StringBuilder sb = new StringBuilder(strArr[0]);
                for (int i = 1; i < strArr.length; i++) {
                    sb.append(',');
                    sb.append(strArr[i]);
                }
                String sb2 = sb.toString();
                Bundle bundle = new Bundle();
                if (zzl.contains(".gsf")) {
                    bundle.putString("legacy.sender", sb2);
                    return InstanceID.getInstance(this.f8476a).getToken(sb2, "GCM", bundle);
                }
                bundle.putString(NotificationCompat.MessagingStyle.Message.KEY_SENDER, sb2);
                Intent a2 = a(bundle, z);
                if (a2 != null) {
                    String stringExtra = a2.getStringExtra("registration_id");
                    if (stringExtra != null) {
                        return stringExtra;
                    }
                    String stringExtra2 = a2.getStringExtra("error");
                    if (stringExtra2 != null) {
                        throw new IOException(stringExtra2);
                    }
                    throw new IOException("SERVICE_NOT_AVAILABLE");
                }
                throw new IOException("SERVICE_NOT_AVAILABLE");
            }
            throw new IllegalArgumentException("No senderIds");
        }
        throw new IOException("SERVICE_NOT_AVAILABLE");
    }

    @Deprecated
    public void close() {
        f = null;
        a.d = null;
        i();
    }

    public final boolean d(Intent intent) {
        Handler remove;
        String stringExtra = intent.getStringExtra("In-Reply-To");
        if (stringExtra == null && intent.hasExtra("error")) {
            stringExtra = intent.getStringExtra(Constants.MessagePayloadKeys.MSGID);
        }
        if (stringExtra == null || (remove = this.c.remove(stringExtra)) == null) {
            return false;
        }
        Message obtain = Message.obtain();
        obtain.obj = intent;
        return remove.sendMessage(obtain);
    }

    @Deprecated
    public String getMessageType(Intent intent) {
        if ("com.google.android.c2dm.intent.RECEIVE".equals(intent.getAction())) {
            String stringExtra = intent.getStringExtra(Constants.MessagePayloadKeys.MESSAGE_TYPE);
            return stringExtra != null ? stringExtra : "gcm";
        }
        return null;
    }

    public final synchronized void h(Intent intent) {
        if (this.b == null) {
            Intent intent2 = new Intent();
            intent2.setPackage("com.google.example.invalidpackage");
            this.b = PendingIntent.getBroadcast(this.f8476a, 0, intent2, 0);
        }
        intent.putExtra("app", this.b);
    }

    public final synchronized void i() {
        PendingIntent pendingIntent = this.b;
        if (pendingIntent != null) {
            pendingIntent.cancel();
            this.b = null;
        }
    }

    @RequiresPermission("com.google.android.c2dm.permission.RECEIVE")
    @Deprecated
    public synchronized String register(String... strArr) throws IOException {
        return b(zzaf.zzk(this.f8476a), strArr);
    }

    @RequiresPermission("com.google.android.c2dm.permission.RECEIVE")
    @Deprecated
    public void send(String str, String str2, Bundle bundle) throws IOException {
        send(str, str2, -1L, bundle);
    }

    @RequiresPermission("com.google.android.c2dm.permission.RECEIVE")
    @Deprecated
    public synchronized void unregister() throws IOException {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            InstanceID.getInstance(this.f8476a).deleteInstanceID();
        } else {
            throw new IOException("MAIN_THREAD");
        }
    }

    @RequiresPermission("com.google.android.c2dm.permission.RECEIVE")
    @Deprecated
    public void send(String str, String str2, long j, Bundle bundle) throws IOException {
        if (str != null) {
            String zzl = zzaf.zzl(this.f8476a);
            if (zzl != null) {
                Intent intent = new Intent("com.google.android.gcm.intent.SEND");
                if (bundle != null) {
                    intent.putExtras(bundle);
                }
                h(intent);
                intent.setPackage(zzl);
                intent.putExtra(Constants.MessagePayloadKeys.TO, str);
                intent.putExtra(Constants.MessagePayloadKeys.MSGID, str2);
                intent.putExtra(Constants.MessagePayloadKeys.TTL, Long.toString(j));
                int indexOf = str.indexOf(64);
                String substring = indexOf > 0 ? str.substring(0, indexOf) : str;
                InstanceID.getInstance(this.f8476a);
                intent.putExtra("google.from", InstanceID.zzp().zzf("", substring, "GCM"));
                if (zzl.contains(".gsf")) {
                    Bundle bundle2 = new Bundle();
                    for (String str3 : bundle.keySet()) {
                        Object obj = bundle.get(str3);
                        if (obj instanceof String) {
                            String valueOf = String.valueOf(str3);
                            bundle2.putString(valueOf.length() != 0 ? Constants.MessageNotificationKeys.RESERVED_PREFIX.concat(valueOf) : new String(Constants.MessageNotificationKeys.RESERVED_PREFIX), (String) obj);
                        }
                    }
                    bundle2.putString(Constants.MessagePayloadKeys.TO, str);
                    bundle2.putString(Constants.MessagePayloadKeys.MSGID, str2);
                    InstanceID.getInstance(this.f8476a).zze("GCM", "upstream", bundle2);
                    return;
                }
                this.f8476a.sendOrderedBroadcast(intent, "com.google.android.gtalkservice.permission.GTALK_SERVICE");
                return;
            }
            throw new IOException("SERVICE_NOT_AVAILABLE");
        }
        throw new IllegalArgumentException("Missing 'to'");
    }
}
