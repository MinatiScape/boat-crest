package com.google.android.gms.internal.common;
/* loaded from: classes7.dex */
public final class b extends p {
    public final zzag j;

    public b(zzag zzagVar, int i) {
        super(zzagVar.size(), i);
        this.j = zzagVar;
    }

    @Override // com.google.android.gms.internal.common.p
    public final Object a(int i) {
        return this.j.get(i);
    }
}
