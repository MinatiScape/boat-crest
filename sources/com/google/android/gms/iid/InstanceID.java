package com.google.android.gms.iid;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Looper;
import android.util.Base64;
import android.util.Log;
import androidx.collection.ArrayMap;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.io.IOException;
import java.security.KeyPair;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.slf4j.Marker;
@Deprecated
/* loaded from: classes6.dex */
public class InstanceID {
    public static final String ERROR_MAIN_THREAD = "MAIN_THREAD";
    public static final String ERROR_MISSING_INSTANCEID_SERVICE = "MISSING_INSTANCEID_SERVICE";
    public static final String ERROR_SERVICE_NOT_AVAILABLE = "SERVICE_NOT_AVAILABLE";
    public static final String ERROR_TIMEOUT = "TIMEOUT";
    public static final zzaj<Boolean> c = zzai.zzy().zzd("gcm_check_for_different_iid_in_token", true);
    public static Map<String, InstanceID> d = new ArrayMap();
    public static final long e = TimeUnit.DAYS.toMillis(7);
    public static zzak f;
    public static zzaf g;
    public static String h;

    /* renamed from: a  reason: collision with root package name */
    public Context f8484a;
    public String b;

    @ShowFirstParty
    public InstanceID(Context context, String str) {
        this.b = "";
        this.f8484a = context.getApplicationContext();
        this.b = str;
    }

    public static String b(KeyPair keyPair) {
        try {
            byte[] digest = MessageDigest.getInstance("SHA1").digest(keyPair.getPublic().getEncoded());
            digest[0] = (byte) ((digest[0] & 15) + 112);
            return Base64.encodeToString(digest, 0, 8, 11);
        } catch (NoSuchAlgorithmException unused) {
            Log.w("InstanceID", "Unexpected error, device missing required algorithms");
            return null;
        }
    }

    public static int c(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e2) {
            String valueOf = String.valueOf(e2);
            StringBuilder sb = new StringBuilder(valueOf.length() + 38);
            sb.append("Never happens: can't find own package ");
            sb.append(valueOf);
            Log.w("InstanceID", sb.toString());
            return 0;
        }
    }

    public static String d(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e2) {
            String valueOf = String.valueOf(e2);
            StringBuilder sb = new StringBuilder(valueOf.length() + 38);
            sb.append("Never happens: can't find own package ");
            sb.append(valueOf);
            Log.w("InstanceID", sb.toString());
            return null;
        }
    }

    @Deprecated
    public static InstanceID getInstance(Context context) {
        return getInstance(context, null);
    }

    public static zzak zzp() {
        return f;
    }

    public final KeyPair a() {
        return f.zzj(this.b).b();
    }

    @Deprecated
    public void deleteInstanceID() throws IOException {
        zzd(Marker.ANY_MARKER, Marker.ANY_MARKER, null);
        e();
    }

    @Deprecated
    public void deleteToken(String str, String str2) throws IOException {
        zzd(str, str2, null);
    }

    public final void e() {
        f.f(this.b);
    }

    @Deprecated
    public long getCreationTime() {
        return f.zzj(this.b).a();
    }

    @Deprecated
    public String getId() {
        return b(a());
    }

    @KeepForSdk
    public String getSubtype() {
        return this.b;
    }

    @Deprecated
    public String getToken(String str, String str2) throws IOException {
        return getToken(str, str2, null);
    }

    @ShowFirstParty
    public final void zzd(String str, String str2, Bundle bundle) throws IOException {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            f.zzh(this.b, str, str2);
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putString(NotificationCompat.MessagingStyle.Message.KEY_SENDER, str);
            if (str2 != null) {
                bundle.putString("scope", str2);
            }
            bundle.putString("subscription", str);
            bundle.putString("delete", "1");
            bundle.putString("X-delete", "1");
            bundle.putString("subtype", "".equals(this.b) ? str : this.b);
            if (!"".equals(this.b)) {
                str = this.b;
            }
            bundle.putString("X-subtype", str);
            zzaf.g(g.a(bundle, a()));
            return;
        }
        throw new IOException("MAIN_THREAD");
    }

    public final String zze(String str, String str2, Bundle bundle) throws IOException {
        if (str2 != null) {
            bundle.putString("scope", str2);
        }
        bundle.putString(NotificationCompat.MessagingStyle.Message.KEY_SENDER, str);
        String str3 = "".equals(this.b) ? str : this.b;
        if (!bundle.containsKey("legacy.register")) {
            bundle.putString("subscription", str);
            bundle.putString("subtype", str3);
            bundle.putString("X-subscription", str);
            bundle.putString("X-subtype", str3);
        }
        String g2 = zzaf.g(g.a(bundle, a()));
        if ("RST".equals(g2) || g2.startsWith("RST|")) {
            InstanceIDListenerService.c(this.f8484a, f);
            throw new IOException("SERVICE_NOT_AVAILABLE");
        }
        return g2;
    }

    @KeepForSdk
    public static synchronized InstanceID getInstance(Context context, Bundle bundle) {
        InstanceID instanceID;
        synchronized (InstanceID.class) {
            String string = bundle == null ? "" : bundle.getString("subtype");
            if (string == null) {
                string = "";
            }
            Context applicationContext = context.getApplicationContext();
            if (f == null) {
                String packageName = applicationContext.getPackageName();
                StringBuilder sb = new StringBuilder(String.valueOf(packageName).length() + 73);
                sb.append("Instance ID SDK is deprecated, ");
                sb.append(packageName);
                sb.append(" should update to use Firebase Instance ID");
                Log.w("InstanceID", sb.toString());
                f = new zzak(applicationContext);
                g = new zzaf(applicationContext);
            }
            h = Integer.toString(c(applicationContext));
            instanceID = d.get(string);
            if (instanceID == null) {
                instanceID = new InstanceID(applicationContext, string);
                d.put(string, instanceID);
            }
        }
        return instanceID;
    }

    @Deprecated
    public String getToken(String str, String str2, Bundle bundle) throws IOException {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            String a2 = f.a(RemoteConfigConstants.RequestFieldKey.APP_VERSION);
            boolean z = true;
            if (a2 != null && a2.equals(h)) {
                long d2 = f.d(this.b, str, str2);
                if (d2 >= 0 && System.currentTimeMillis() - d2 < e) {
                    z = false;
                }
            }
            String zzf = z ? null : f.zzf(this.b, str, str2);
            if (zzf == null) {
                if (bundle == null) {
                    bundle = new Bundle();
                }
                String zze = zze(str, str2, bundle);
                if (c.get().booleanValue() && zze.contains(":") && !zze.startsWith(String.valueOf(getId()).concat(":"))) {
                    InstanceIDListenerService.c(this.f8484a, f);
                    throw new IOException("SERVICE_NOT_AVAILABLE");
                }
                if (zze != null) {
                    f.zzd(this.b, str, str2, zze, h);
                }
                return zze;
            }
            return zzf;
        }
        throw new IOException("MAIN_THREAD");
    }
}
