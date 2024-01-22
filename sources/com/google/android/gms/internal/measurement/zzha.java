package com.google.android.gms.internal.measurement;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.StrictMode;
import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.collection.ArrayMap;
import com.clevertap.android.sdk.Constants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
/* loaded from: classes8.dex */
public final class zzha implements r1 {
    @GuardedBy("ConfigurationContentLoader.class")
    public static final Map<Uri, zzha> g = new ArrayMap();
    public static final String[] zza = {Constants.KEY_KEY, "value"};

    /* renamed from: a  reason: collision with root package name */
    public final ContentResolver f8953a;
    public final Uri b;
    public final ContentObserver c;
    public final Object d;
    public volatile Map<String, String> e;
    @GuardedBy("this")
    public final List<zzhb> f;

    public zzha(ContentResolver contentResolver, Uri uri) {
        q1 q1Var = new q1(this, null);
        this.c = q1Var;
        this.d = new Object();
        this.f = new ArrayList();
        Objects.requireNonNull(contentResolver);
        Objects.requireNonNull(uri);
        this.f8953a = contentResolver;
        this.b = uri;
        contentResolver.registerContentObserver(uri, false, q1Var);
    }

    public static synchronized void a() {
        synchronized (zzha.class) {
            for (zzha zzhaVar : g.values()) {
                zzhaVar.f8953a.unregisterContentObserver(zzhaVar.c);
            }
            g.clear();
        }
    }

    public static zzha zza(ContentResolver contentResolver, Uri uri) {
        zzha zzhaVar;
        synchronized (zzha.class) {
            Map<Uri, zzha> map = g;
            zzhaVar = map.get(uri);
            if (zzhaVar == null) {
                try {
                    zzha zzhaVar2 = new zzha(contentResolver, uri);
                    try {
                        map.put(uri, zzhaVar2);
                    } catch (SecurityException unused) {
                    }
                    zzhaVar = zzhaVar2;
                } catch (SecurityException unused2) {
                }
            }
        }
        return zzhaVar;
    }

    @Override // com.google.android.gms.internal.measurement.r1
    public final /* bridge */ /* synthetic */ Object zzb(String str) {
        return zzc().get(str);
    }

    public final Map<String, String> zzc() {
        Map<String, String> map;
        Map<String, String> map2 = this.e;
        if (map2 == null) {
            synchronized (this.d) {
                map2 = this.e;
                if (map2 == null) {
                    StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
                    try {
                        map = (Map) zzhc.zza(new zzhd() { // from class: com.google.android.gms.internal.measurement.zzgy
                            @Override // com.google.android.gms.internal.measurement.zzhd
                            public final Object zza() {
                                return zzha.this.zzd();
                            }
                        });
                        StrictMode.setThreadPolicy(allowThreadDiskReads);
                    } catch (SQLiteException | IllegalStateException | SecurityException unused) {
                        Log.e("ConfigurationContentLoader", "PhenotypeFlag unable to load ContentProvider, using default values");
                        StrictMode.setThreadPolicy(allowThreadDiskReads);
                        map = null;
                    }
                    this.e = map;
                    map2 = map;
                }
            }
        }
        return map2 != null ? map2 : Collections.emptyMap();
    }

    public final /* synthetic */ Map zzd() {
        Map hashMap;
        Cursor query = this.f8953a.query(this.b, zza, null, null, null);
        if (query == null) {
            return Collections.emptyMap();
        }
        try {
            int count = query.getCount();
            if (count == 0) {
                return Collections.emptyMap();
            }
            if (count <= 256) {
                hashMap = new ArrayMap(count);
            } else {
                hashMap = new HashMap(count, 1.0f);
            }
            while (query.moveToNext()) {
                hashMap.put(query.getString(0), query.getString(1));
            }
            return hashMap;
        } finally {
            query.close();
        }
    }

    public final void zzf() {
        synchronized (this.d) {
            this.e = null;
            zzhu.b();
        }
        synchronized (this) {
            for (zzhb zzhbVar : this.f) {
                zzhbVar.zza();
            }
        }
    }
}
