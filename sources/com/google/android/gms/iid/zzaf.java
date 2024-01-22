package com.google.android.gms.iid;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.ConditionVariable;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;
import android.os.Process;
import android.os.RemoteException;
import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.tasks.Tasks;
import java.io.IOException;
import java.security.KeyPair;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@ShowFirstParty
/* loaded from: classes6.dex */
public class zzaf {
    public static final zzaj<Boolean> g = zzai.zzy().zzd("gcm_iid_use_messenger_ipc", true);
    public static String h = null;
    public static boolean i = false;
    public static int j = 0;
    @GuardedBy("Rpc.class")
    public static BroadcastReceiver k = null;

    /* renamed from: a  reason: collision with root package name */
    public Context f8492a;
    public Map<String, Object> b = new ArrayMap();
    public Messenger c;
    public Messenger d;
    public MessengerCompat e;
    public PendingIntent f;

    public zzaf(Context context) {
        this.f8492a = context;
    }

    public static void b(Object obj, Object obj2) {
        if (obj instanceof ConditionVariable) {
            ((ConditionVariable) obj).open();
        }
        if (obj instanceof Messenger) {
            Messenger messenger = (Messenger) obj;
            Message obtain = Message.obtain();
            obtain.obj = obj2;
            try {
                messenger.send(obtain);
            } catch (RemoteException e) {
                String valueOf = String.valueOf(e);
                StringBuilder sb = new StringBuilder(valueOf.length() + 24);
                sb.append("Failed to send response ");
                sb.append(valueOf);
                Log.w("InstanceID", sb.toString());
            }
        }
    }

    public static boolean d(PackageManager packageManager, String str) {
        try {
            h = packageManager.getApplicationInfo(str, 0).packageName;
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public static boolean e(PackageManager packageManager, String str, String str2) {
        if (packageManager.checkPermission("com.google.android.c2dm.permission.SEND", str) == 0) {
            return d(packageManager, str);
        }
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 56 + String.valueOf(str2).length());
        sb.append("Possible malicious package ");
        sb.append(str);
        sb.append(" declares ");
        sb.append(str2);
        sb.append(" without permission");
        Log.w("InstanceID", sb.toString());
        return false;
    }

    public static String g(Bundle bundle) throws IOException {
        if (bundle != null) {
            String string = bundle.getString("registration_id");
            if (string == null) {
                string = bundle.getString("unregistered");
            }
            if (string == null) {
                String string2 = bundle.getString("error");
                if (string2 != null) {
                    throw new IOException(string2);
                }
                String valueOf = String.valueOf(bundle);
                StringBuilder sb = new StringBuilder(valueOf.length() + 29);
                sb.append("Unexpected response from GCM ");
                sb.append(valueOf);
                Log.w("InstanceID", sb.toString(), new Throwable());
                throw new IOException("SERVICE_NOT_AVAILABLE");
            }
            return string;
        }
        throw new IOException("SERVICE_NOT_AVAILABLE");
    }

    public static int j(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(zzl(context), 0).versionCode;
        } catch (PackageManager.NameNotFoundException unused) {
            return -1;
        }
    }

    public static synchronized String k() {
        String num;
        synchronized (zzaf.class) {
            int i2 = j;
            j = i2 + 1;
            num = Integer.toString(i2);
        }
        return num;
    }

    @ShowFirstParty
    public static boolean zzk(Context context) {
        if (h != null) {
            zzl(context);
        }
        return i;
    }

    @ShowFirstParty
    public static String zzl(Context context) {
        boolean z;
        String str = h;
        if (str != null) {
            return str;
        }
        Process.myUid();
        PackageManager packageManager = context.getPackageManager();
        boolean z2 = true;
        if (!PlatformVersion.isAtLeastO()) {
            Iterator<ResolveInfo> it = packageManager.queryIntentServices(new Intent("com.google.android.c2dm.intent.REGISTER"), 0).iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                } else if (e(packageManager, it.next().serviceInfo.packageName, "com.google.android.c2dm.intent.REGISTER")) {
                    i = false;
                    z = true;
                    break;
                }
            }
            if (z) {
                return h;
            }
        }
        Iterator<ResolveInfo> it2 = packageManager.queryBroadcastReceivers(new Intent("com.google.iid.TOKEN_REQUEST"), 0).iterator();
        while (true) {
            if (!it2.hasNext()) {
                z2 = false;
                break;
            } else if (e(packageManager, it2.next().activityInfo.packageName, "com.google.iid.TOKEN_REQUEST")) {
                i = true;
                break;
            }
        }
        if (z2) {
            return h;
        }
        Log.w("InstanceID", "Failed to resolve IID implementation package, falling back");
        if (d(packageManager, "com.google.android.gms")) {
            i = PlatformVersion.isAtLeastO();
            return h;
        } else if (!PlatformVersion.isAtLeastLollipop() && d(packageManager, "com.google.android.gsf")) {
            i = false;
            return h;
        } else {
            Log.w("InstanceID", "Google Play services is missing, unable to get tokens");
            return null;
        }
    }

    public final Bundle a(Bundle bundle, KeyPair keyPair) throws IOException {
        int j2 = j(this.f8492a);
        bundle.putString("gmsv", Integer.toString(j2));
        bundle.putString("osv", Integer.toString(Build.VERSION.SDK_INT));
        bundle.putString("app_ver", Integer.toString(InstanceID.c(this.f8492a)));
        bundle.putString("app_ver_name", InstanceID.d(this.f8492a));
        bundle.putString("cliv", "iid-12451000");
        bundle.putString("appid", InstanceID.b(keyPair));
        if (j2 >= 12000000 && g.get().booleanValue()) {
            try {
                return (Bundle) Tasks.await(new zzr(this.f8492a).zzd(1, bundle));
            } catch (InterruptedException | ExecutionException e) {
                if (Log.isLoggable("InstanceID", 3)) {
                    String valueOf = String.valueOf(e);
                    StringBuilder sb = new StringBuilder(valueOf.length() + 22);
                    sb.append("Error making request: ");
                    sb.append(valueOf);
                    Log.d("InstanceID", sb.toString());
                }
                if ((e.getCause() instanceof zzaa) && ((zzaa) e.getCause()).getErrorCode() == 4) {
                    return h(bundle);
                }
                return null;
            }
        }
        return h(bundle);
    }

    public final void c(String str, Object obj) {
        synchronized (getClass()) {
            Object obj2 = this.b.get(str);
            this.b.put(str, obj);
            b(obj2, obj);
        }
    }

    public final synchronized void f(Intent intent) {
        if (this.f == null) {
            Intent intent2 = new Intent();
            intent2.setPackage("com.google.example.invalidpackage");
            this.f = PendingIntent.getBroadcast(this.f8492a, 0, intent2, 0);
        }
        intent.putExtra("app", this.f);
    }

    public final Bundle h(Bundle bundle) throws IOException {
        Bundle i2 = i(bundle);
        if (i2 == null || !i2.containsKey("google.messenger")) {
            return i2;
        }
        Bundle i3 = i(bundle);
        if (i3 == null || !i3.containsKey("google.messenger")) {
            return i3;
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:82:0x0187 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.os.Bundle i(android.os.Bundle r9) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 471
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.iid.zzaf.i(android.os.Bundle):android.os.Bundle");
    }

    public final void zze(Message message) {
        if (message == null) {
            return;
        }
        Object obj = message.obj;
        if (obj instanceof Intent) {
            Intent intent = (Intent) obj;
            intent.setExtrasClassLoader(MessengerCompat.class.getClassLoader());
            if (intent.hasExtra("google.messenger")) {
                Parcelable parcelableExtra = intent.getParcelableExtra("google.messenger");
                if (parcelableExtra instanceof MessengerCompat) {
                    this.e = (MessengerCompat) parcelableExtra;
                }
                if (parcelableExtra instanceof Messenger) {
                    this.d = (Messenger) parcelableExtra;
                }
            }
            zzh((Intent) message.obj);
            return;
        }
        Log.w("InstanceID", "Dropping invalid message");
    }

    public final void zzh(Intent intent) {
        String str;
        if (intent == null) {
            if (Log.isLoggable("InstanceID", 3)) {
                Log.d("InstanceID", "Unexpected response: null");
                return;
            }
            return;
        }
        String action = intent.getAction();
        if (!"com.google.android.c2dm.intent.REGISTRATION".equals(action) && !"com.google.android.gms.iid.InstanceID".equals(action)) {
            if (Log.isLoggable("InstanceID", 3)) {
                String valueOf = String.valueOf(intent.getAction());
                Log.d("InstanceID", valueOf.length() != 0 ? "Unexpected response ".concat(valueOf) : new String("Unexpected response "));
                return;
            }
            return;
        }
        String stringExtra = intent.getStringExtra("registration_id");
        if (stringExtra == null) {
            stringExtra = intent.getStringExtra("unregistered");
        }
        if (stringExtra == null) {
            String stringExtra2 = intent.getStringExtra("error");
            if (stringExtra2 == null) {
                String valueOf2 = String.valueOf(intent.getExtras());
                StringBuilder sb = new StringBuilder(valueOf2.length() + 49);
                sb.append("Unexpected response, no error or registration id ");
                sb.append(valueOf2);
                Log.w("InstanceID", sb.toString());
                return;
            }
            if (Log.isLoggable("InstanceID", 3)) {
                Log.d("InstanceID", stringExtra2.length() != 0 ? "Received InstanceID error ".concat(stringExtra2) : new String("Received InstanceID error "));
            }
            String str2 = null;
            if (stringExtra2.startsWith("|")) {
                String[] split = stringExtra2.split("\\|");
                if (!"ID".equals(split[1])) {
                    Log.w("InstanceID", stringExtra2.length() != 0 ? "Unexpected structured response ".concat(stringExtra2) : new String("Unexpected structured response "));
                }
                if (split.length > 2) {
                    String str3 = split[2];
                    str = split[3];
                    if (str.startsWith(":")) {
                        str = str.substring(1);
                    }
                    str2 = str3;
                } else {
                    str = "UNKNOWN";
                }
                stringExtra2 = str;
                intent.putExtra("error", stringExtra2);
            }
            if (str2 == null) {
                synchronized (getClass()) {
                    for (String str4 : this.b.keySet()) {
                        Object obj = this.b.get(str4);
                        this.b.put(str4, stringExtra2);
                        b(obj, stringExtra2);
                    }
                }
                return;
            }
            c(str2, stringExtra2);
            return;
        }
        Matcher matcher = Pattern.compile("\\|ID\\|([^|]+)\\|:?+(.*)").matcher(stringExtra);
        if (!matcher.matches()) {
            if (Log.isLoggable("InstanceID", 3)) {
                Log.d("InstanceID", stringExtra.length() != 0 ? "Unexpected response string: ".concat(stringExtra) : new String("Unexpected response string: "));
                return;
            }
            return;
        }
        String group = matcher.group(1);
        String group2 = matcher.group(2);
        Bundle extras = intent.getExtras();
        extras.putString("registration_id", group2);
        c(group, extras);
    }
}
