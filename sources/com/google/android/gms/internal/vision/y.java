package com.google.android.gms.internal.vision;

import java.util.List;
/* JADX INFO: Add missing generic type declarations: [E] */
/* loaded from: classes10.dex */
public final class y<E> extends zzdf<E> {
    private final transient int length;
    private final transient int offset;
    private final /* synthetic */ zzdf zzlw;

    public y(zzdf zzdfVar, int i, int i2) {
        this.zzlw = zzdfVar;
        this.offset = i;
        this.length = i2;
    }

    @Override // java.util.List
    public final E get(int i) {
        zzct.zzc(i, this.length);
        return this.zzlw.get(i + this.offset);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.length;
    }

    @Override // com.google.android.gms.internal.vision.zzdf, java.util.List
    public final /* synthetic */ List subList(int i, int i2) {
        return subList(i, i2);
    }

    @Override // com.google.android.gms.internal.vision.zzdc
    public final Object[] zzbz() {
        return this.zzlw.zzbz();
    }

    @Override // com.google.android.gms.internal.vision.zzdc
    public final int zzca() {
        return this.zzlw.zzca() + this.offset;
    }

    @Override // com.google.android.gms.internal.vision.zzdc
    public final int zzcb() {
        return this.zzlw.zzca() + this.offset + this.length;
    }

    @Override // com.google.android.gms.internal.vision.zzdf
    public final zzdf<E> zze(int i, int i2) {
        zzct.zza(i, i2, this.length);
        zzdf zzdfVar = this.zzlw;
        int i3 = this.offset;
        return (zzdf) zzdfVar.subList(i + i3, i2 + i3);
    }
}
