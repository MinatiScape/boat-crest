package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.StrictMode;
import androidx.annotation.GuardedBy;
import androidx.collection.ArrayMap;
import java.util.Iterator;
import java.util.Map;
/* loaded from: classes8.dex */
public final class zzhv implements r1 {
    @GuardedBy("SharedPreferencesLoader.class")
    public static final Map<String, zzhv> b = new ArrayMap();

    /* renamed from: a  reason: collision with root package name */
    public final SharedPreferences f8959a;

    public static zzhv a(Context context, String str) {
        zzhv zzhvVar;
        if (!zzgw.zza()) {
            synchronized (zzhv.class) {
                zzhvVar = b.get(null);
                if (zzhvVar == null) {
                    StrictMode.allowThreadDiskReads();
                    throw null;
                }
            }
            return zzhvVar;
        }
        throw null;
    }

    public static synchronized void b() {
        synchronized (zzhv.class) {
            Map<String, zzhv> map = b;
            Iterator<zzhv> it = map.values().iterator();
            if (!it.hasNext()) {
                map.clear();
            } else {
                SharedPreferences sharedPreferences = it.next().f8959a;
                throw null;
            }
        }
    }

    @Override // com.google.android.gms.internal.measurement.r1
    public final Object zzb(String str) {
        throw null;
    }
}
