package com.google.android.gms.internal.vision;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.StrictMode;
import androidx.annotation.GuardedBy;
import androidx.collection.ArrayMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/* loaded from: classes10.dex */
public final class zzbj implements d {
    @GuardedBy("SharedPreferencesLoader.class")
    public static final Map<String, zzbj> f = new ArrayMap();

    /* renamed from: a  reason: collision with root package name */
    public final SharedPreferences f10015a;
    public final SharedPreferences.OnSharedPreferenceChangeListener b;
    public final Object c;
    public volatile Map<String, ?> d;
    @GuardedBy("this")
    public final List<zzar> e;

    public zzbj(SharedPreferences sharedPreferences) {
        SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener(this) { // from class: com.google.android.gms.internal.vision.l

            /* renamed from: a  reason: collision with root package name */
            public final zzbj f9989a;

            {
                this.f9989a = this;
            }

            @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
            public final void onSharedPreferenceChanged(SharedPreferences sharedPreferences2, String str) {
                this.f9989a.a(sharedPreferences2, str);
            }
        };
        this.b = onSharedPreferenceChangeListener;
        this.c = new Object();
        this.e = new ArrayList();
        this.f10015a = sharedPreferences;
        sharedPreferences.registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
    }

    public static zzbj b(Context context, String str) {
        zzbj zzbjVar;
        if ((!zzan.zzs() || str.startsWith("direct_boot:")) ? true : zzan.isUserUnlocked(context)) {
            synchronized (zzbj.class) {
                Map<String, zzbj> map = f;
                zzbjVar = map.get(str);
                if (zzbjVar == null) {
                    zzbjVar = new zzbj(c(context, str));
                    map.put(str, zzbjVar);
                }
            }
            return zzbjVar;
        }
        return null;
    }

    public static SharedPreferences c(Context context, String str) {
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            if (str.startsWith("direct_boot:")) {
                if (zzan.zzs()) {
                    context = context.createDeviceProtectedStorageContext();
                }
                return context.getSharedPreferences(str.substring(12), 0);
            }
            return context.getSharedPreferences(str, 0);
        } finally {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
        }
    }

    public static synchronized void d() {
        synchronized (zzbj.class) {
            for (zzbj zzbjVar : f.values()) {
                zzbjVar.f10015a.unregisterOnSharedPreferenceChangeListener(zzbjVar.b);
            }
            f.clear();
        }
    }

    public final /* synthetic */ void a(SharedPreferences sharedPreferences, String str) {
        synchronized (this.c) {
            this.d = null;
            zzbe.e();
        }
        synchronized (this) {
            for (zzar zzarVar : this.e) {
                zzarVar.zzz();
            }
        }
    }

    @Override // com.google.android.gms.internal.vision.d
    public final Object zzb(String str) {
        Map<String, ?> map = this.d;
        if (map == null) {
            synchronized (this.c) {
                map = this.d;
                if (map == null) {
                    StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
                    Map<String, ?> all = this.f10015a.getAll();
                    this.d = all;
                    StrictMode.setThreadPolicy(allowThreadDiskReads);
                    map = all;
                }
            }
        }
        if (map != null) {
            return map.get(str);
        }
        return null;
    }
}
