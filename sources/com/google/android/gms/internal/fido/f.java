package com.google.android.gms.internal.fido;
/* loaded from: classes7.dex */
public final class f extends d {
    public final zzat j;

    public f(zzat zzatVar, int i) {
        super(zzatVar.size(), i);
        this.j = zzatVar;
    }

    @Override // com.google.android.gms.internal.fido.d
    public final Object a(int i) {
        return this.j.get(i);
    }
}
