package com.google.android.gms.internal.vision;
/* loaded from: classes10.dex */
public final class x<E> extends v<E> {
    public final zzdf<E> j;

    public x(zzdf<E> zzdfVar, int i) {
        super(zzdfVar.size(), i);
        this.j = zzdfVar;
    }

    @Override // com.google.android.gms.internal.vision.v
    public final E a(int i) {
        return this.j.get(i);
    }
}
