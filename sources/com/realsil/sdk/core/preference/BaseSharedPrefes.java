package com.realsil.sdk.core.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.PowerManager;
import android.preference.PreferenceManager;
import com.realsil.sdk.core.logger.ZLogger;
/* loaded from: classes12.dex */
public abstract class BaseSharedPrefes {

    /* renamed from: a  reason: collision with root package name */
    public static PowerManager.WakeLock f13588a;
    public Context b;
    public SharedPreferences c;

    public BaseSharedPrefes(Context context) {
        this.b = context;
        this.c = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public final SharedPreferences.Editor a() {
        if (this.c == null) {
            this.c = PreferenceManager.getDefaultSharedPreferences(this.b);
        }
        return this.c.edit();
    }

    public void acquireWakeLock() {
        if (f13588a == null) {
            ZLogger.v(this.b.getClass().getCanonicalName());
            PowerManager powerManager = (PowerManager) this.b.getSystemService("power");
            if (powerManager != null) {
                PowerManager.WakeLock newWakeLock = powerManager.newWakeLock(6, this.b.getClass().getCanonicalName());
                f13588a = newWakeLock;
                newWakeLock.acquire();
            }
        }
    }

    public void clear() {
        SharedPreferences.Editor a2 = a();
        a2.clear();
        a2.apply();
    }

    public boolean contains(String str) {
        SharedPreferences sharedPreferences = this.c;
        return sharedPreferences != null && sharedPreferences.contains(str);
    }

    public boolean getBoolean(String str, boolean z) {
        SharedPreferences sharedPreferences = this.c;
        return sharedPreferences == null ? z : sharedPreferences.getBoolean(str, z);
    }

    public int getInt(String str, int i) {
        SharedPreferences sharedPreferences = this.c;
        return sharedPreferences == null ? i : sharedPreferences.getInt(str, i);
    }

    public Long getLong(String str, Long l) {
        SharedPreferences sharedPreferences = this.c;
        return sharedPreferences == null ? l : Long.valueOf(sharedPreferences.getLong(str, l.longValue()));
    }

    public SharedPreferences getSharedPreferences() {
        return this.c;
    }

    public String getString(String str, String str2) {
        SharedPreferences sharedPreferences = this.c;
        return sharedPreferences == null ? str2 : sharedPreferences.getString(str, str2);
    }

    public void releaseWakeLock() {
        PowerManager.WakeLock wakeLock = f13588a;
        if (wakeLock == null || !wakeLock.isHeld()) {
            return;
        }
        f13588a.release();
        f13588a = null;
    }

    public void set(String str, boolean z) {
        SharedPreferences.Editor a2 = a();
        a2.putBoolean(str, z);
        a2.apply();
    }

    public BaseSharedPrefes(Context context, String str) {
        this.b = context;
        this.c = context.getSharedPreferences(str, 0);
    }

    public void set(String str, String str2) {
        SharedPreferences.Editor a2 = a();
        a2.putString(str, str2);
        a2.apply();
    }

    public void set(String str, int i) {
        SharedPreferences.Editor a2 = a();
        a2.putInt(str, i);
        a2.commit();
    }

    public void set(String str, long j) {
        SharedPreferences.Editor a2 = a();
        a2.putLong(str, j);
        a2.apply();
    }
}
