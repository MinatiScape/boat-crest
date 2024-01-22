package com.google.android.gms.internal.fitness;
/* loaded from: classes8.dex */
public final class q1<E> extends o1<E> {
    public final zzfc<E> j;

    public q1(zzfc<E> zzfcVar, int i) {
        super(zzfcVar.size(), i);
        this.j = zzfcVar;
    }

    @Override // com.google.android.gms.internal.fitness.o1
    public final E a(int i) {
        return this.j.get(i);
    }
}
