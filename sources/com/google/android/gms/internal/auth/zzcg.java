package com.google.android.gms.internal.auth;

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
/* loaded from: classes6.dex */
public final class zzcg implements x {
    @GuardedBy("ConfigurationContentLoader.class")
    public static final Map h = new ArrayMap();
    public static final String[] zza = {Constants.KEY_KEY, "value"};

    /* renamed from: a  reason: collision with root package name */
    public final ContentResolver f8553a;
    public final Uri b;
    public final Runnable c;
    public final ContentObserver d;
    public final Object e;
    public volatile Map f;
    @GuardedBy("this")
    public final List g;

    public zzcg(ContentResolver contentResolver, Uri uri, Runnable runnable) {
        w wVar = new w(this, null);
        this.d = wVar;
        this.e = new Object();
        this.g = new ArrayList();
        Objects.requireNonNull(contentResolver);
        Objects.requireNonNull(uri);
        this.f8553a = contentResolver;
        this.b = uri;
        this.c = runnable;
        contentResolver.registerContentObserver(uri, false, wVar);
    }

    public static synchronized void b() {
        synchronized (zzcg.class) {
            for (zzcg zzcgVar : h.values()) {
                zzcgVar.f8553a.unregisterContentObserver(zzcgVar.d);
            }
            h.clear();
        }
    }

    public static zzcg zza(ContentResolver contentResolver, Uri uri, Runnable runnable) {
        zzcg zzcgVar;
        synchronized (zzcg.class) {
            Map map = h;
            zzcgVar = (zzcg) map.get(uri);
            if (zzcgVar == null) {
                try {
                    zzcg zzcgVar2 = new zzcg(contentResolver, uri, runnable);
                    try {
                        map.put(uri, zzcgVar2);
                    } catch (SecurityException unused) {
                    }
                    zzcgVar = zzcgVar2;
                } catch (SecurityException unused2) {
                }
            }
        }
        return zzcgVar;
    }

    public final /* synthetic */ Map a() {
        Map hashMap;
        Cursor query = this.f8553a.query(this.b, zza, null, null, null);
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

    @Override // com.google.android.gms.internal.auth.x
    public final /* bridge */ /* synthetic */ Object zzb(String str) {
        Map map;
        Map map2 = this.f;
        if (map2 == null) {
            synchronized (this.e) {
                map2 = this.f;
                if (map2 == null) {
                    StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
                    try {
                        map = (Map) zzcj.zza(new zzck() { // from class: com.google.android.gms.internal.auth.zzce
                            @Override // com.google.android.gms.internal.auth.zzck
                            public final Object zza() {
                                return zzcg.this.a();
                            }
                        });
                        StrictMode.setThreadPolicy(allowThreadDiskReads);
                    } catch (SQLiteException | IllegalStateException | SecurityException unused) {
                        Log.e("ConfigurationContentLdr", "PhenotypeFlag unable to load ContentProvider, using default values");
                        StrictMode.setThreadPolicy(allowThreadDiskReads);
                        map = null;
                    }
                    this.f = map;
                    map2 = map;
                }
            }
        }
        if (map2 == null) {
            map2 = Collections.emptyMap();
        }
        return (String) map2.get(str);
    }

    public final void zze() {
        synchronized (this.e) {
            this.f = null;
            zzdc.zzd();
        }
        synchronized (this) {
            for (zzch zzchVar : this.g) {
                zzchVar.zza();
            }
        }
    }
}
