package com.google.android.gms.internal.firebase_ml;

import java.util.List;
/* JADX INFO: Add missing generic type declarations: [E] */
/* loaded from: classes7.dex */
public final class d2<E> extends zzmw<E> {
    private final transient int length;
    private final transient int offset;
    private final /* synthetic */ zzmw zzalr;

    public d2(zzmw zzmwVar, int i, int i2) {
        this.zzalr = zzmwVar;
        this.offset = i;
        this.length = i2;
    }

    @Override // java.util.List
    public final E get(int i) {
        zzml.zzb(i, this.length);
        return this.zzalr.get(i + this.offset);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.length;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzmw, java.util.List
    public final /* synthetic */ List subList(int i, int i2) {
        return subList(i, i2);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzmw
    public final zzmw<E> zzd(int i, int i2) {
        zzml.zza(i, i2, this.length);
        zzmw zzmwVar = this.zzalr;
        int i3 = this.offset;
        return (zzmw) zzmwVar.subList(i + i3, i2 + i3);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzmx
    public final Object[] zzjl() {
        return this.zzalr.zzjl();
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzmx
    public final int zzjm() {
        return this.zzalr.zzjm() + this.offset;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzmx
    public final int zzjn() {
        return this.zzalr.zzjm() + this.offset + this.length;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzmx
    public final boolean zzjo() {
        return true;
    }
}
