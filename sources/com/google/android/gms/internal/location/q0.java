package com.google.android.gms.internal.location;
/* loaded from: classes8.dex */
public final class q0 extends p0 {
    public final zzds j;

    public q0(zzds zzdsVar, int i) {
        super(zzdsVar.size(), i);
        this.j = zzdsVar;
    }

    @Override // com.google.android.gms.internal.location.p0
    public final Object a(int i) {
        return this.j.get(i);
    }
}
