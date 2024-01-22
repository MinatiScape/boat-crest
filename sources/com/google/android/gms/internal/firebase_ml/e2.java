package com.google.android.gms.internal.firebase_ml;
/* loaded from: classes7.dex */
public final class e2<E> extends c2<E> {
    public final zzmw<E> j;

    public e2(zzmw<E> zzmwVar, int i) {
        super(zzmwVar.size(), i);
        this.j = zzmwVar;
    }

    @Override // com.google.android.gms.internal.firebase_ml.c2
    public final E a(int i) {
        return this.j.get(i);
    }
}
