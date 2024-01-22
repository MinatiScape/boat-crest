package com.google.android.gms.measurement.internal;

import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicLong;
/* loaded from: classes10.dex */
public final class c0<V> extends FutureTask<V> implements Comparable<c0<V>> {
    public final long h;
    public final boolean i;
    public final String j;
    public final /* synthetic */ zzfp k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c0(zzfp zzfpVar, Runnable runnable, boolean z, String str) {
        super(runnable, null);
        AtomicLong atomicLong;
        this.k = zzfpVar;
        Preconditions.checkNotNull(str);
        atomicLong = zzfp.k;
        long andIncrement = atomicLong.getAndIncrement();
        this.h = andIncrement;
        this.j = str;
        this.i = z;
        if (andIncrement == Long.MAX_VALUE) {
            zzfpVar.zzs.zzay().zzd().zza("Tasks index overflow");
        }
    }

    @Override // java.lang.Comparable
    public final /* bridge */ /* synthetic */ int compareTo(@NonNull Object obj) {
        c0 c0Var = (c0) obj;
        boolean z = this.i;
        if (z != c0Var.i) {
            return !z ? 1 : -1;
        }
        int i = (this.h > c0Var.h ? 1 : (this.h == c0Var.h ? 0 : -1));
        if (i < 0) {
            return -1;
        }
        if (i > 0) {
            return 1;
        }
        this.k.zzs.zzay().zzh().zzb("Two tasks share the same index. index", Long.valueOf(this.h));
        return 0;
    }

    @Override // java.util.concurrent.FutureTask
    public final void setException(Throwable th) {
        this.k.zzs.zzay().zzd().zzb(this.j, th);
        super.setException(th);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c0(zzfp zzfpVar, Callable<V> callable, boolean z, String str) {
        super(callable);
        AtomicLong atomicLong;
        this.k = zzfpVar;
        Preconditions.checkNotNull("Task exception on worker thread");
        atomicLong = zzfp.k;
        long andIncrement = atomicLong.getAndIncrement();
        this.h = andIncrement;
        this.j = "Task exception on worker thread";
        this.i = z;
        if (andIncrement == Long.MAX_VALUE) {
            zzfpVar.zzs.zzay().zzd().zza("Tasks index overflow");
        }
    }
}
