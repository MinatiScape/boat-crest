package com.google.android.gms.internal.fitness;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
/* loaded from: classes8.dex */
public final class n3<T> implements x3<T> {

    /* renamed from: a  reason: collision with root package name */
    public final zzik f8839a;
    public final n4<?, ?> b;
    public final boolean c;
    public final m2<?> d;

    public n3(n4<?, ?> n4Var, m2<?> m2Var, zzik zzikVar) {
        this.b = n4Var;
        this.c = m2Var.f(zzikVar);
        this.d = m2Var;
        this.f8839a = zzikVar;
    }

    public static <T> n3<T> f(n4<?, ?> n4Var, m2<?> m2Var, zzik zzikVar) {
        return new n3<>(n4Var, m2Var, zzikVar);
    }

    @Override // com.google.android.gms.internal.fitness.x3
    public final int a(T t) {
        int hashCode = this.b.g(t).hashCode();
        return this.c ? (hashCode * 53) + this.d.c(t).hashCode() : hashCode;
    }

    @Override // com.google.android.gms.internal.fitness.x3
    public final boolean b(T t, T t2) {
        if (this.b.g(t).equals(this.b.g(t2))) {
            if (this.c) {
                return this.d.c(t).equals(this.d.c(t2));
            }
            return true;
        }
        return false;
    }

    @Override // com.google.android.gms.internal.fitness.x3
    public final void c(T t, T t2) {
        z3.f(this.b, t, t2);
        if (this.c) {
            z3.d(this.d, t, t2);
        }
    }

    @Override // com.google.android.gms.internal.fitness.x3
    public final int d(T t) {
        n4<?, ?> n4Var = this.b;
        int h = n4Var.h(n4Var.g(t)) + 0;
        return this.c ? h + this.d.c(t).o() : h;
    }

    @Override // com.google.android.gms.internal.fitness.x3
    public final void e(T t, d5 d5Var) throws IOException {
        Iterator<Map.Entry<?, Object>> d = this.d.c(t).d();
        while (d.hasNext()) {
            Map.Entry<?, Object> next = d.next();
            zzgv zzgvVar = (zzgv) next.getKey();
            if (zzgvVar.zzbm() == zzkj.MESSAGE && !zzgvVar.zzbn() && !zzgvVar.zzbo()) {
                if (next instanceof v2) {
                    d5Var.zza(zzgvVar.zzc(), ((v2) next).a().zzam());
                } else {
                    d5Var.zza(zzgvVar.zzc(), next.getValue());
                }
            } else {
                throw new IllegalStateException("Found invalid MessageSet item.");
            }
        }
        n4<?, ?> n4Var = this.b;
        n4Var.b(n4Var.g(t), d5Var);
    }

    @Override // com.google.android.gms.internal.fitness.x3
    public final void zze(T t) {
        this.b.c(t);
        this.d.e(t);
    }

    @Override // com.google.android.gms.internal.fitness.x3
    public final boolean zzl(T t) {
        return this.d.c(t).c();
    }
}
