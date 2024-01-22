package com.google.android.gms.internal.vision;

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
/* loaded from: classes10.dex */
public final class zzaq implements d {
    @GuardedBy("ConfigurationContentLoader.class")
    public static final Map<Uri, zzaq> g = new ArrayMap();
    public static final String[] h = {Constants.KEY_KEY, "value"};

    /* renamed from: a  reason: collision with root package name */
    public final ContentResolver f10009a;
    public final Uri b;
    public final ContentObserver c;
    public final Object d;
    public volatile Map<String, String> e;
    @GuardedBy("this")
    public final List<zzar> f;

    public zzaq(ContentResolver contentResolver, Uri uri) {
        c cVar = new c(this, null);
        this.c = cVar;
        this.d = new Object();
        this.f = new ArrayList();
        this.f10009a = contentResolver;
        this.b = uri;
        contentResolver.registerContentObserver(uri, false, cVar);
    }

    public static synchronized void c() {
        synchronized (zzaq.class) {
            for (zzaq zzaqVar : g.values()) {
                zzaqVar.f10009a.unregisterContentObserver(zzaqVar.c);
            }
            g.clear();
        }
    }

    public static zzaq zza(ContentResolver contentResolver, Uri uri) {
        zzaq zzaqVar;
        synchronized (zzaq.class) {
            Map<Uri, zzaq> map = g;
            zzaqVar = map.get(uri);
            if (zzaqVar == null) {
                try {
                    zzaq zzaqVar2 = new zzaq(contentResolver, uri);
                    try {
                        map.put(uri, zzaqVar2);
                    } catch (SecurityException unused) {
                    }
                    zzaqVar = zzaqVar2;
                } catch (SecurityException unused2) {
                }
            }
        }
        return zzaqVar;
    }

    public final Map<String, String> a() {
        Map<String, String> map = this.e;
        if (map == null) {
            synchronized (this.d) {
                map = this.e;
                if (map == null) {
                    map = b();
                    this.e = map;
                }
            }
        }
        return map != null ? map : Collections.emptyMap();
    }

    public final Map<String, String> b() {
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            try {
                return (Map) zzat.zza(new zzaw(this) { // from class: com.google.android.gms.internal.vision.b

                    /* renamed from: a  reason: collision with root package name */
                    public final zzaq f9963a;

                    {
                        this.f9963a = this;
                    }

                    @Override // com.google.android.gms.internal.vision.zzaw
                    public final Object zzt() {
                        return this.f9963a.d();
                    }
                });
            } catch (SQLiteException | IllegalStateException | SecurityException unused) {
                Log.e("ConfigurationContentLoader", "PhenotypeFlag unable to load ContentProvider, using default values");
                StrictMode.setThreadPolicy(allowThreadDiskReads);
                return null;
            }
        } finally {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
        }
    }

    public final /* synthetic */ Map d() {
        Map hashMap;
        Cursor query = this.f10009a.query(this.b, h, null, null, null);
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

    @Override // com.google.android.gms.internal.vision.d
    public final /* synthetic */ Object zzb(String str) {
        return a().get(str);
    }

    public final void zzv() {
        synchronized (this.d) {
            this.e = null;
            zzbe.e();
        }
        synchronized (this) {
            for (zzar zzarVar : this.f) {
                zzarVar.zzz();
            }
        }
    }
}
