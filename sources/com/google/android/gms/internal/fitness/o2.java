package com.google.android.gms.internal.fitness;

import com.google.android.gms.internal.fitness.zzgy;
import java.io.IOException;
import java.util.Map;
/* loaded from: classes8.dex */
public final class o2 extends m2<zzgy.a> {
    @Override // com.google.android.gms.internal.fitness.m2
    public final int a(Map.Entry<?, ?> entry) {
        zzgy.a aVar = (zzgy.a) entry.getKey();
        throw new NoSuchMethodError();
    }

    @Override // com.google.android.gms.internal.fitness.m2
    public final void b(d5 d5Var, Map.Entry<?, ?> entry) throws IOException {
        zzgy.a aVar = (zzgy.a) entry.getKey();
        throw new NoSuchMethodError();
    }

    @Override // com.google.android.gms.internal.fitness.m2
    public final q2<zzgy.a> c(Object obj) {
        return ((zzgy.zzd) obj).zzya;
    }

    @Override // com.google.android.gms.internal.fitness.m2
    public final q2<zzgy.a> d(Object obj) {
        zzgy.zzd zzdVar = (zzgy.zzd) obj;
        if (zzdVar.zzya.b()) {
            zzdVar.zzya = (q2) zzdVar.zzya.clone();
        }
        return zzdVar.zzya;
    }

    @Override // com.google.android.gms.internal.fitness.m2
    public final void e(Object obj) {
        c(obj).j();
    }

    @Override // com.google.android.gms.internal.fitness.m2
    public final boolean f(zzik zzikVar) {
        return zzikVar instanceof zzgy.zzd;
    }
}
