package com.google.android.gms.internal.firebase_ml;

import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Task;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.Dependency;
import java.util.concurrent.Callable;
/* loaded from: classes7.dex */
public class zzqb {
    public static final GmsLogger b = new GmsLogger("MLTaskManager", "");
    public static final Component<?> zzbja = Component.builder(zzqb.class).add(Dependency.required(zzqr.class)).factory(m4.f8705a).build();

    /* renamed from: a  reason: collision with root package name */
    public final zzqr f8793a;

    public zzqb(zzqr zzqrVar) {
        this.f8793a = zzqrVar;
    }

    public static final /* synthetic */ zzqb a(ComponentContainer componentContainer) {
        return new zzqb((zzqr) componentContainer.get(zzqr.class));
    }

    public static synchronized zzqb zza(zzqf zzqfVar) {
        zzqb zzqbVar;
        synchronized (zzqb.class) {
            zzqbVar = (zzqb) zzqfVar.get(zzqb.class);
        }
        return zzqbVar;
    }

    public final /* synthetic */ Object b(zzqp zzqpVar, zzpu zzpuVar, zzpy zzpyVar) throws Exception {
        if (zzqpVar != null) {
            this.f8793a.g(zzqpVar);
        }
        return zzpuVar.zza(zzpyVar);
    }

    public final /* synthetic */ Object c(zzqp zzqpVar, Callable callable) throws Exception {
        this.f8793a.g(zzqpVar);
        return callable.call();
    }

    public final <T, S extends zzpy> void zzb(zzpu<T, S> zzpuVar) {
        zzqp zzoc = zzpuVar.zzoc();
        if (zzoc != null) {
            this.f8793a.zzd(zzoc);
        }
    }

    public final <T, S extends zzpy> void zza(zzpu<T, S> zzpuVar) {
        zzqp zzoc = zzpuVar.zzoc();
        if (zzoc != null) {
            this.f8793a.zza(zzoc);
        }
    }

    public final synchronized <TResult> Task<TResult> zza(@NonNull final zzqp zzqpVar, @NonNull final Callable<TResult> callable) {
        Preconditions.checkNotNull(callable, "Operation can not be null");
        Preconditions.checkNotNull(zzqpVar, "Model resource can not be null");
        b.d("MLTaskManager", "Execute task");
        this.f8793a.c(zzqpVar);
        return zzpx.zzof().zza(new Callable(this, zzqpVar, callable) { // from class: com.google.android.gms.internal.firebase_ml.l4
            public final zzqb h;
            public final zzqp i;
            public final Callable j;

            {
                this.h = this;
                this.i = zzqpVar;
                this.j = callable;
            }

            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.h.c(this.i, this.j);
            }
        });
    }

    public final synchronized <T, S extends zzpy> Task<T> zza(@NonNull final zzpu<T, S> zzpuVar, @NonNull final S s) {
        final zzqp zzoc;
        Preconditions.checkNotNull(zzpuVar, "Operation can not be null");
        Preconditions.checkNotNull(s, "Input can not be null");
        b.d("MLTaskManager", "Execute task");
        zzoc = zzpuVar.zzoc();
        if (zzoc != null) {
            this.f8793a.c(zzoc);
        }
        return zzpx.zzof().zza(new Callable(this, zzoc, zzpuVar, s) { // from class: com.google.android.gms.internal.firebase_ml.n4
            public final zzqb h;
            public final zzqp i;
            public final zzpu j;
            public final zzpy k;

            {
                this.h = this;
                this.i = zzoc;
                this.j = zzpuVar;
                this.k = s;
            }

            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.h.b(this.i, this.j, this.k);
            }
        });
    }
}
