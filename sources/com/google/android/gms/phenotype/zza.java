package com.google.android.gms.phenotype;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import com.clevertap.android.sdk.Constants;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes10.dex */
public final class zza {
    public static final ConcurrentHashMap<Uri, zza> f = new ConcurrentHashMap<>();
    public static final String[] g = {Constants.KEY_KEY, "value"};

    /* renamed from: a  reason: collision with root package name */
    public final ContentResolver f10169a;
    public final Uri b;
    public volatile Map<String, String> e;
    public final Object d = new Object();
    public final ContentObserver c = new a(this, null);

    public zza(ContentResolver contentResolver, Uri uri) {
        this.f10169a = contentResolver;
        this.b = uri;
    }

    public static zza zza(ContentResolver contentResolver, Uri uri) {
        ConcurrentHashMap<Uri, zza> concurrentHashMap = f;
        zza zzaVar = concurrentHashMap.get(uri);
        if (zzaVar == null) {
            zza zzaVar2 = new zza(contentResolver, uri);
            zza putIfAbsent = concurrentHashMap.putIfAbsent(uri, zzaVar2);
            if (putIfAbsent == null) {
                zzaVar2.f10169a.registerContentObserver(zzaVar2.b, false, zzaVar2.c);
                return zzaVar2;
            }
            return putIfAbsent;
        }
        return zzaVar;
    }

    public final Map<String, String> a() {
        HashMap hashMap = new HashMap();
        Cursor query = this.f10169a.query(this.b, g, null, null, null);
        if (query != null) {
            while (query.moveToNext()) {
                try {
                    hashMap.put(query.getString(0), query.getString(1));
                } finally {
                    query.close();
                }
            }
        }
        return hashMap;
    }

    public final Map<String, String> zza() {
        Map<String, String> a2 = PhenotypeFlag.c("gms:phenotype:phenotype_flag:debug_disable_caching", false) ? a() : this.e;
        if (a2 == null) {
            synchronized (this.d) {
                a2 = this.e;
                if (a2 == null) {
                    a2 = a();
                    this.e = a2;
                }
            }
        }
        return a2;
    }

    public final void zzb() {
        synchronized (this.d) {
            this.e = null;
        }
    }
}
