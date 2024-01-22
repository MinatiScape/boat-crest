package com.google.android.gms.measurement.internal;

import androidx.annotation.GuardedBy;
import com.google.android.gms.common.util.VisibleForTesting;
@VisibleForTesting
/* loaded from: classes10.dex */
public final class zzdv<V> {
    public static final Object h = new Object();

    /* renamed from: a  reason: collision with root package name */
    public final String f10144a;
    public final k<V> b;
    public final V c;
    public final V d;
    public final Object e = new Object();
    @GuardedBy("overrideLock")
    public volatile V f = null;
    @GuardedBy("cachingLock")
    public volatile V g = null;

    /* JADX WARN: Multi-variable type inference failed */
    public /* synthetic */ zzdv(String str, Object obj, Object obj2, k kVar, zzdu zzduVar) {
        this.f10144a = str;
        this.c = obj;
        this.d = obj2;
        this.b = kVar;
    }

    public final V zza(V v) {
        synchronized (this.e) {
        }
        if (v != null) {
            return v;
        }
        if (l.f10122a != null) {
            synchronized (h) {
                if (zzaa.zza()) {
                    return this.g == null ? this.c : this.g;
                }
                try {
                    for (zzdv zzdvVar : zzdw.b()) {
                        if (!zzaa.zza()) {
                            V v2 = null;
                            try {
                                k<V> kVar = zzdvVar.b;
                                if (kVar != null) {
                                    v2 = kVar.zza();
                                }
                            } catch (IllegalStateException unused) {
                            }
                            synchronized (h) {
                                zzdvVar.g = v2;
                            }
                        } else {
                            throw new IllegalStateException("Refreshing flag cache must be done on a worker thread.");
                        }
                    }
                } catch (SecurityException unused2) {
                }
                k<V> kVar2 = this.b;
                if (kVar2 == null) {
                    return this.c;
                }
                try {
                    return kVar2.zza();
                } catch (IllegalStateException unused3) {
                    return this.c;
                } catch (SecurityException unused4) {
                    return this.c;
                }
            }
        }
        return this.c;
    }

    public final String zzb() {
        return this.f10144a;
    }
}
