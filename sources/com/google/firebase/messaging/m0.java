package com.google.firebase.messaging;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.content.ContextCompat;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.mappls.android.lms.MapplsLMSDbAdapter;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes10.dex */
public class m0 {

    /* renamed from: a  reason: collision with root package name */
    public final SharedPreferences f11349a;
    public final Context b;

    /* loaded from: classes10.dex */
    public static class a {
        public static final long d = TimeUnit.DAYS.toMillis(7);

        /* renamed from: a  reason: collision with root package name */
        public final String f11350a;
        public final String b;
        public final long c;

        public a(String str, String str2, long j) {
            this.f11350a = str;
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
                Log.w(Constants.TAG, sb.toString());
                return null;
            }
        }

        public static a c(String str) {
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
                    Log.w(Constants.TAG, sb.toString());
                    return null;
                }
            }
            return new a(str, null, 0L);
        }

        public boolean b(String str) {
            return System.currentTimeMillis() > this.c + d || !str.equals(this.b);
        }
    }

    public m0(Context context) {
        this.b = context;
        this.f11349a = context.getSharedPreferences("com.google.android.gms.appid", 0);
        a("com.google.android.gms.appid-no-backup");
    }

    public final void a(String str) {
        File file = new File(ContextCompat.getNoBackupFilesDir(this.b), "com.google.android.gms.appid-no-backup");
        if (file.exists()) {
            return;
        }
        try {
            if (!file.createNewFile() || f()) {
                return;
            }
            Log.i(Constants.TAG, "App restored, clearing state");
            c();
        } catch (IOException e) {
            if (Log.isLoggable(Constants.TAG, 3)) {
                String valueOf = String.valueOf(e.getMessage());
                Log.d(Constants.TAG, valueOf.length() != 0 ? "Error creating file in no backup dir: ".concat(valueOf) : new String("Error creating file in no backup dir: "));
            }
        }
    }

    public final String b(String str, String str2) {
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 5 + String.valueOf(str2).length());
        sb.append(str);
        sb.append("|T|");
        sb.append(str2);
        sb.append("|*");
        return sb.toString();
    }

    public synchronized void c() {
        this.f11349a.edit().clear().commit();
    }

    public synchronized void d(String str, String str2) {
        String b = b(str, str2);
        SharedPreferences.Editor edit = this.f11349a.edit();
        edit.remove(b);
        edit.commit();
    }

    public synchronized a e(String str, String str2) {
        return a.c(this.f11349a.getString(b(str, str2), null));
    }

    public synchronized boolean f() {
        return this.f11349a.getAll().isEmpty();
    }

    public synchronized void g(String str, String str2, String str3, String str4) {
        String a2 = a.a(str3, str4, System.currentTimeMillis());
        if (a2 == null) {
            return;
        }
        SharedPreferences.Editor edit = this.f11349a.edit();
        edit.putString(b(str, str2), a2);
        edit.commit();
    }
}
