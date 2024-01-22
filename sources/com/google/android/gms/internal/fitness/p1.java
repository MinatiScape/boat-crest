package com.google.android.gms.internal.fitness;

import java.util.List;
/* JADX INFO: Add missing generic type declarations: [E] */
/* loaded from: classes8.dex */
public final class p1<E> extends zzfc<E> {
    private final transient int length;
    private final transient int offset;
    private final /* synthetic */ zzfc zzth;

    public p1(zzfc zzfcVar, int i, int i2) {
        this.zzth = zzfcVar;
        this.offset = i;
        this.length = i2;
    }

    @Override // java.util.List
    public final E get(int i) {
        zzez.zza(i, this.length);
        return this.zzth.get(i + this.offset);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.length;
    }

    @Override // com.google.android.gms.internal.fitness.zzfc, java.util.List
    public final /* synthetic */ List subList(int i, int i2) {
        return subList(i, i2);
    }

    @Override // com.google.android.gms.internal.fitness.zzfd
    public final Object[] zzag() {
        return this.zzth.zzag();
    }

    @Override // com.google.android.gms.internal.fitness.zzfd
    public final int zzah() {
        return this.zzth.zzah() + this.offset;
    }

    @Override // com.google.android.gms.internal.fitness.zzfd
    public final int zzai() {
        return this.zzth.zzah() + this.offset + this.length;
    }

    @Override // com.google.android.gms.internal.fitness.zzfd
    public final boolean zzaj() {
        return true;
    }

    @Override // com.google.android.gms.internal.fitness.zzfc
    public final zzfc<E> zzc(int i, int i2) {
        zzez.zza(i, i2, this.length);
        zzfc zzfcVar = this.zzth;
        int i3 = this.offset;
        return (zzfc) zzfcVar.subList(i + i3, i2 + i3);
    }
}
