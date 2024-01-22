package com.google.android.gms.internal.measurement;

import java.io.IOException;
/* loaded from: classes8.dex */
public final class q3<T> implements x3<T> {

    /* renamed from: a  reason: collision with root package name */
    public final zzlg f8924a;
    public final l4<?, ?> b;
    public final boolean c;
    public final v2<?> d;

    public q3(l4<?, ?> l4Var, v2<?> v2Var, zzlg zzlgVar) {
        this.b = l4Var;
        this.c = v2Var.c(zzlgVar);
        this.d = v2Var;
        this.f8924a = zzlgVar;
    }

    public static <T> q3<T> e(l4<?, ?> l4Var, v2<?> v2Var, zzlg zzlgVar) {
        return new q3<>(l4Var, v2Var, zzlgVar);
    }

    @Override // com.google.android.gms.internal.measurement.x3
    public final void a(T t, byte[] bArr, int i, int i2, g2 g2Var) throws IOException {
        zzjz zzjzVar = (zzjz) t;
        if (zzjzVar.zzc == zzmj.zzc()) {
            zzjzVar.zzc = zzmj.b();
        }
        zzjw zzjwVar = (zzjw) t;
        throw null;
    }

    @Override // com.google.android.gms.internal.measurement.x3
    public final boolean b(T t) {
        this.d.a(t);
        throw null;
    }

    @Override // com.google.android.gms.internal.measurement.x3
    public final void c(T t, s2 s2Var) throws IOException {
        this.d.a(t);
        throw null;
    }

    @Override // com.google.android.gms.internal.measurement.x3
    public final boolean d(T t, T t2) {
        if (this.b.c(t).equals(this.b.c(t2))) {
            if (this.c) {
                this.d.a(t);
                this.d.a(t2);
                throw null;
            }
            return true;
        }
        return false;
    }

    @Override // com.google.android.gms.internal.measurement.x3
    public final int zza(T t) {
        l4<?, ?> l4Var = this.b;
        int b = l4Var.b(l4Var.c(t));
        if (this.c) {
            this.d.a(t);
            throw null;
        }
        return b;
    }

    @Override // com.google.android.gms.internal.measurement.x3
    public final int zzb(T t) {
        int hashCode = this.b.c(t).hashCode();
        if (this.c) {
            this.d.a(t);
            throw null;
        }
        return hashCode;
    }

    @Override // com.google.android.gms.internal.measurement.x3
    public final T zze() {
        return (T) this.f8924a.zzbC().zzaC();
    }

    @Override // com.google.android.gms.internal.measurement.x3
    public final void zzf(T t) {
        this.b.g(t);
        this.d.b(t);
    }

    @Override // com.google.android.gms.internal.measurement.x3
    public final void zzg(T t, T t2) {
        z3.f(this.b, t, t2);
        if (this.c) {
            z3.e(this.d, t, t2);
        }
    }
}
