package com.google.android.gms.analytics;

import android.annotation.TargetApi;
import android.os.Build;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@VisibleForTesting
/* loaded from: classes6.dex */
public final class zzh {

    /* renamed from: a  reason: collision with root package name */
    public final zzk f8184a;
    public final Clock b;
    public boolean c;
    public long d;
    public long e;
    public long f;
    public long g;
    public long h;
    public boolean i;
    public final Map<Class<? extends zzj>, zzj> j;
    public final List<zzt> k;

    public zzh(zzh zzhVar) {
        this.f8184a = zzhVar.f8184a;
        this.b = zzhVar.b;
        this.d = zzhVar.d;
        this.e = zzhVar.e;
        this.f = zzhVar.f;
        this.g = zzhVar.g;
        this.h = zzhVar.h;
        this.k = new ArrayList(zzhVar.k);
        this.j = new HashMap(zzhVar.j.size());
        for (Map.Entry<Class<? extends zzj>, zzj> entry : zzhVar.j.entrySet()) {
            zzj e = e(entry.getKey());
            entry.getValue().zzc(e);
            this.j.put(entry.getKey(), e);
        }
    }

    @TargetApi(19)
    public static <T extends zzj> T e(Class<T> cls) {
        try {
            return cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception e) {
            if (!(e instanceof InstantiationException)) {
                if (!(e instanceof IllegalAccessException)) {
                    if (Build.VERSION.SDK_INT >= 19 && (e instanceof ReflectiveOperationException)) {
                        throw new IllegalArgumentException("Linkage exception", e);
                    }
                    throw new RuntimeException(e);
                }
                throw new IllegalArgumentException("dataType default constructor is not accessible", e);
            }
            throw new IllegalArgumentException("dataType doesn't have default constructor", e);
        }
    }

    public final zzk a() {
        return this.f8184a;
    }

    @VisibleForTesting
    public final void b() {
        this.i = true;
    }

    @VisibleForTesting
    public final void c() {
        this.f = this.b.elapsedRealtime();
        long j = this.e;
        if (j != 0) {
            this.d = j;
        } else {
            this.d = this.b.currentTimeMillis();
        }
        this.c = true;
    }

    @VisibleForTesting
    public final boolean d() {
        return this.i;
    }

    @VisibleForTesting
    public final long zza() {
        return this.d;
    }

    @VisibleForTesting
    public final <T extends zzj> T zzb(Class<T> cls) {
        T t = (T) this.j.get(cls);
        if (t == null) {
            T t2 = (T) e(cls);
            this.j.put(cls, t2);
            return t2;
        }
        return t;
    }

    @Nullable
    @VisibleForTesting
    public final <T extends zzj> T zzc(Class<T> cls) {
        return (T) this.j.get(cls);
    }

    @VisibleForTesting
    public final Collection<zzj> zze() {
        return this.j.values();
    }

    public final List<zzt> zzf() {
        return this.k;
    }

    @VisibleForTesting
    public final void zzg(zzj zzjVar) {
        Preconditions.checkNotNull(zzjVar);
        Class<?> cls = zzjVar.getClass();
        if (cls.getSuperclass() == zzj.class) {
            zzjVar.zzc(zzb(cls));
            return;
        }
        throw new IllegalArgumentException();
    }

    @VisibleForTesting
    public final void zzj(long j) {
        this.e = j;
    }

    @VisibleForTesting
    public final void zzk() {
        this.f8184a.zzm().c(this);
    }

    @VisibleForTesting
    public final boolean zzm() {
        return this.c;
    }

    @VisibleForTesting
    public zzh(zzk zzkVar, Clock clock) {
        Preconditions.checkNotNull(zzkVar);
        Preconditions.checkNotNull(clock);
        this.f8184a = zzkVar;
        this.b = clock;
        this.g = 1800000L;
        this.h = 3024000000L;
        this.j = new HashMap();
        this.k = new ArrayList();
    }
}
