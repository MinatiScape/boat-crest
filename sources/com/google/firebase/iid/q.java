package com.google.firebase.iid;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import androidx.collection.ArrayMap;
import androidx.core.content.ContextCompat;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.mappls.android.lms.MapplsLMSDbAdapter;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes10.dex */
public class q {

    /* renamed from: a  reason: collision with root package name */
    public final SharedPreferences f11301a;
    public final Context b;
    @GuardedBy("this")
    public final Map<String, Long> c = new ArrayMap();

    /* loaded from: classes10.dex */
    public static class a {
        public static final long d = TimeUnit.DAYS.toMillis(7);

        /* renamed from: a  reason: collision with root package name */
        public final String f11302a;
        public final String b;
        public final long c;

        public a(String str, String str2, long j) {
            this.f11302a = str;
            this.b = str2;
            this.c = j;
        }

        public static String a(String str, String str2, long j) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(MapplsLMSDbAdapter.KEY_TOKEN, str);
                jSONObject.put(RemoteConfigConstants.RequestFieldKey.APP_VERSION, str2);
                jSONObject.put("timestamp", j);
                return jSONObject.toString();
            } catch (JSONException e) {
                String valueOf = String.valueOf(e);
                StringBuilder sb = new StringBuilder(valueOf.length() + 24);
                sb.append("Failed to encode token: ");
                sb.append(valueOf);
                Log.w("FirebaseInstanceId", sb.toString());
                return null;
            }
        }

        public static String b(@Nullable a aVar) {
            if (aVar == null) {
                return null;
            }
            return aVar.f11302a;
        }

        public static a d(String str) {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            if (str.startsWith("{")) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    return new a(jSONObject.getString(MapplsLMSDbAdapter.KEY_TOKEN), jSONObject.getString(RemoteConfigConstants.RequestFieldKey.APP_VERSION), jSONObject.getLong("timestamp"));
                } catch (JSONException e) {
                    String valueOf = String.valueOf(e);
                    StringBuilder sb = new StringBuilder(valueOf.length() + 23);
                    sb.append("Failed to parse token: ");
                    sb.append(valueOf);
                    Log.w("FirebaseInstanceId", sb.toString());
                    return null;
                }
            }
            return new a(str, null, 0L);
        }

        public boolean c(String str) {
            return System.currentTimeMillis() > this.c + d || !str.equals(this.b);
        }
    }

    public q(Context context) {
        this.b = context;
        this.f11301a = context.getSharedPreferences("com.google.android.gms.appid", 0);
        a("com.google.android.gms.appid-no-backup");
    }

    public static String b(String str, String str2) {
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 6);
        sb.append(str);
        sb.append("|S|cre");
        return sb.toString();
    }

    public final void a(String str) {
        File file = new File(ContextCompat.getNoBackupFilesDir(this.b), "com.google.android.gms.appid-no-backup");
        if (file.exists()) {
            return;
        }
        try {
            if (!file.createNewFile() || i()) {
                return;
            }
            Log.i("FirebaseInstanceId", "App restored, clearing state");
            d();
        } catch (IOException e) {
            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                String valueOf = String.valueOf(e.getMessage());
                Log.d("FirebaseInstanceId", valueOf.length() != 0 ? "Error creating file in no backup dir: ".concat(valueOf) : new String("Error creating file in no backup dir: "));
            }
        }
    }

    public final String c(String str, String str2, String str3) {
        int length = String.valueOf(str).length();
        StringBuilder sb = new StringBuilder(length + 4 + String.valueOf(str2).length() + String.valueOf(str3).length());
        sb.append(str);
        sb.append("|T|");
        sb.append(str2);
        sb.append("|");
        sb.append(str3);
        return sb.toString();
    }

    public synchronized void d() {
        this.c.clear();
        this.f11301a.edit().clear().commit();
    }

    public synchronized void e(String str, String str2, String str3) {
        String c = c(str, str2, str3);
        SharedPreferences.Editor edit = this.f11301a.edit();
        edit.remove(c);
        edit.commit();
    }

    public synchronized long f(String str) {
        Long l = this.c.get(str);
        if (l != null) {
            return l.longValue();
        }
        return g(str);
    }

    public final long g(String str) {
        String string = this.f11301a.getString(b(str, "cre"), null);
        if (string != null) {
            try {
                return Long.parseLong(string);
            } catch (NumberFormatException unused) {
                return 0L;
            }
        }
        return 0L;
    }

    public synchronized a h(String str, String str2, String str3) {
        return a.d(this.f11301a.getString(c(str, str2, str3), null));
    }

    public synchronized boolean i() {
        return this.f11301a.getAll().isEmpty();
    }

    public synchronized void j(String str, String str2, String str3, String str4, String str5) {
        String a2 = a.a(str4, str5, System.currentTimeMillis());
        if (a2 == null) {
            return;
        }
        SharedPreferences.Editor edit = this.f11301a.edit();
        edit.putString(c(str, str2, str3), a2);
        edit.commit();
    }

    public synchronized long k(String str) {
        long l;
        l = l(str);
        this.c.put(str, Long.valueOf(l));
        return l;
    }

    public final long l(String str) {
        long currentTimeMillis = System.currentTimeMillis();
        if (!this.f11301a.contains(b(str, "cre"))) {
            SharedPreferences.Editor edit = this.f11301a.edit();
            edit.putString(b(str, "cre"), String.valueOf(currentTimeMillis));
            edit.commit();
            return currentTimeMillis;
        }
        return g(str);
    }
}
