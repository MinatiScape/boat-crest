package com.google.android.gms.iid;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import androidx.collection.ArrayMap;
import androidx.core.content.ContextCompat;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.io.File;
import java.io.IOException;
import java.util.Map;
/* loaded from: classes6.dex */
public final class zzak {

    /* renamed from: a  reason: collision with root package name */
    public SharedPreferences f8494a;
    public Context b;
    public final k c;
    @GuardedBy("this")
    public final Map<String, l> d;

    public zzak(Context context) {
        this(context, new k());
    }

    public static String b(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 4 + String.valueOf(str2).length() + String.valueOf(str3).length());
        sb.append(str);
        sb.append("|T|");
        sb.append(str2);
        sb.append("|");
        sb.append(str3);
        return sb.toString();
    }

    public static String c(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 14 + String.valueOf(str2).length() + String.valueOf(str3).length());
        sb.append(str);
        sb.append("|T-timestamp|");
        sb.append(str2);
        sb.append("|");
        sb.append(str3);
        return sb.toString();
    }

    public static String e(String str, String str2) {
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 3 + String.valueOf(str2).length());
        sb.append(str);
        sb.append("|S|");
        sb.append(str2);
        return sb.toString();
    }

    @Nullable
    public final synchronized String a(String str) {
        return this.f8494a.getString(str, null);
    }

    public final synchronized long d(String str, String str2, String str3) {
        return this.f8494a.getLong(c(str, str2, str3), -1L);
    }

    public final void f(String str) {
        synchronized (this) {
            this.d.remove(str);
        }
        k.k(this.b, str);
        zzi(String.valueOf(str).concat("|"));
    }

    public final boolean isEmpty() {
        return this.f8494a.getAll().isEmpty();
    }

    public final synchronized void zzd(String str, String str2, String str3, String str4, String str5) {
        String b = b(str, str2, str3);
        String c = c(str, str2, str3);
        SharedPreferences.Editor edit = this.f8494a.edit();
        edit.putString(b, str4);
        edit.putLong(c, System.currentTimeMillis());
        edit.putString(RemoteConfigConstants.RequestFieldKey.APP_VERSION, str5);
        edit.commit();
    }

    @Nullable
    public final synchronized String zzf(String str, String str2, String str3) {
        return this.f8494a.getString(b(str, str2, str3), null);
    }

    public final synchronized void zzh(String str, String str2, String str3) {
        SharedPreferences.Editor edit = this.f8494a.edit();
        edit.remove(b(str, str2, str3));
        edit.remove(c(str, str2, str3));
        edit.commit();
    }

    public final synchronized void zzi(String str) {
        SharedPreferences.Editor edit = this.f8494a.edit();
        for (String str2 : this.f8494a.getAll().keySet()) {
            if (str2.startsWith(str)) {
                edit.remove(str2);
            }
        }
        edit.commit();
    }

    public final synchronized l zzj(String str) {
        l i;
        l lVar = this.d.get(str);
        if (lVar != null) {
            return lVar;
        }
        try {
            i = this.c.g(this.b, str);
        } catch (m unused) {
            Log.w("InstanceID/Store", "Stored data is corrupt, generating new identity");
            InstanceIDListenerService.c(this.b, this);
            i = this.c.i(this.b, str);
        }
        this.d.put(str, i);
        return i;
    }

    public final synchronized void zzz() {
        this.d.clear();
        k.n(this.b);
        this.f8494a.edit().clear().commit();
    }

    @VisibleForTesting
    public zzak(Context context, k kVar) {
        this.d = new ArrayMap();
        this.b = context;
        this.f8494a = context.getSharedPreferences("com.google.android.gms.appid", 0);
        this.c = kVar;
        File file = new File(ContextCompat.getNoBackupFilesDir(this.b), "com.google.android.gms.appid-no-backup");
        if (file.exists()) {
            return;
        }
        try {
            if (!file.createNewFile() || isEmpty()) {
                return;
            }
            Log.i("InstanceID/Store", "App restored, clearing state");
            InstanceIDListenerService.c(this.b, this);
        } catch (IOException e) {
            if (Log.isLoggable("InstanceID/Store", 3)) {
                String valueOf = String.valueOf(e.getMessage());
                Log.d("InstanceID/Store", valueOf.length() != 0 ? "Error creating file in no backup dir: ".concat(valueOf) : new String("Error creating file in no backup dir: "));
            }
        }
    }
}
