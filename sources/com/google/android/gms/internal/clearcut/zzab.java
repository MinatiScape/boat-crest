package com.google.android.gms.internal.clearcut;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.util.Log;
import androidx.annotation.GuardedBy;
import com.clevertap.android.sdk.Constants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes7.dex */
public final class zzab {
    public static final ConcurrentHashMap<Uri, zzab> h = new ConcurrentHashMap<>();
    public static final String[] i = {Constants.KEY_KEY, "value"};

    /* renamed from: a  reason: collision with root package name */
    public final ContentResolver f8611a;
    public final Uri b;
    public volatile Map<String, String> e;
    public final Object d = new Object();
    public final Object f = new Object();
    @GuardedBy("listenersLock")
    public final List<zzad> g = new ArrayList();
    public final ContentObserver c = new a(this, null);

    public zzab(ContentResolver contentResolver, Uri uri) {
        this.f8611a = contentResolver;
        this.b = uri;
    }

    public static zzab zza(ContentResolver contentResolver, Uri uri) {
        ConcurrentHashMap<Uri, zzab> concurrentHashMap = h;
        zzab zzabVar = concurrentHashMap.get(uri);
        if (zzabVar == null) {
            zzab zzabVar2 = new zzab(contentResolver, uri);
            zzab putIfAbsent = concurrentHashMap.putIfAbsent(uri, zzabVar2);
            if (putIfAbsent == null) {
                zzabVar2.f8611a.registerContentObserver(zzabVar2.b, false, zzabVar2.c);
                return zzabVar2;
            }
            return putIfAbsent;
        }
        return zzabVar;
    }

    public final Map<String, String> b() {
        try {
            HashMap hashMap = new HashMap();
            Cursor query = this.f8611a.query(this.b, i, null, null, null);
            if (query != null) {
                while (query.moveToNext()) {
                    hashMap.put(query.getString(0), query.getString(1));
                }
                query.close();
            }
            return hashMap;
        } catch (SQLiteException | SecurityException unused) {
            Log.e("ConfigurationContentLoader", "PhenotypeFlag unable to load ContentProvider, using default values");
            return null;
        }
    }

    public final void c() {
        synchronized (this.f) {
            for (zzad zzadVar : this.g) {
                zzadVar.zzk();
            }
        }
    }

    public final Map<String, String> zzg() {
        Map<String, String> b = zzae.e("gms:phenotype:phenotype_flag:debug_disable_caching", false) ? b() : this.e;
        if (b == null) {
            synchronized (this.d) {
                b = this.e;
                if (b == null) {
                    b = b();
                    this.e = b;
                }
            }
        }
        return b != null ? b : Collections.emptyMap();
    }

    public final void zzh() {
        synchronized (this.d) {
            this.e = null;
        }
    }
}
