package com.google.android.gms.internal.mlkit_common;
/* loaded from: classes8.dex */
public final class g extends c {
    public final zzar j;

    public g(zzar zzarVar, int i) {
        super(zzarVar.size(), i);
        this.j = zzarVar;
    }

    @Override // com.google.android.gms.internal.mlkit_common.c
    public final Object a(int i) {
        return this.j.get(i);
    }
}
