package com.google.android.gms.internal.auth;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.StrictMode;
import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import androidx.collection.ArrayMap;
import java.util.Iterator;
import java.util.Map;
/* loaded from: classes6.dex */
public final class zzdd implements x {
    @GuardedBy("SharedPreferencesLoader.class")
    public static final Map b = new ArrayMap();

    /* renamed from: a  reason: collision with root package name */
    public final SharedPreferences f8560a;

    @Nullable
    public static zzdd a(Context context, String str, Runnable runnable) {
        zzdd zzddVar;
        if (!zzcc.zzb()) {
            synchronized (zzdd.class) {
                zzddVar = (zzdd) b.get(null);
                if (zzddVar == null) {
                    StrictMode.allowThreadDiskReads();
                    throw null;
                }
            }
            return zzddVar;
        }
        throw null;
    }

    public static synchronized void b() {
        synchronized (zzdd.class) {
            Map map = b;
            Iterator it = map.values().iterator();
            if (!it.hasNext()) {
                map.clear();
            } else {
                SharedPreferences sharedPreferences = ((zzdd) it.next()).f8560a;
                throw null;
            }
        }
    }

    @Override // com.google.android.gms.internal.auth.x
    @Nullable
    public final Object zzb(String str) {
        throw null;
    }
}
