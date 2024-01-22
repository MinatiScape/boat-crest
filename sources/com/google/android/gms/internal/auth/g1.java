package com.google.android.gms.internal.auth;
/* loaded from: classes6.dex */
public final class g1 extends h1 {
    public /* synthetic */ g1(zzfh zzfhVar) {
        super(null);
    }

    @Override // com.google.android.gms.internal.auth.h1
    public final void a(Object obj, long j) {
        ((zzey) u2.f(obj, j)).zzb();
    }

    @Override // com.google.android.gms.internal.auth.h1
    public final void b(Object obj, Object obj2, long j) {
        zzey zzeyVar = (zzey) u2.f(obj, j);
        zzey zzeyVar2 = (zzey) u2.f(obj2, j);
        int size = zzeyVar.size();
        int size2 = zzeyVar2.size();
        if (size > 0 && size2 > 0) {
            if (!zzeyVar.zzc()) {
                zzeyVar = zzeyVar.zzd(size2 + size);
            }
            zzeyVar.addAll(zzeyVar2);
        }
        if (size > 0) {
            zzeyVar2 = zzeyVar;
        }
        u2.p(obj, j, zzeyVar2);
    }
}
