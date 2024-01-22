package com.google.android.libraries.places.internal;

import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: Add missing generic type declarations: [E] */
/* loaded from: classes10.dex */
public final class zzgk<E> extends zzgi<E> {
    private final transient int zza;
    private final transient int zzb;
    private final /* synthetic */ zzgi zzc;

    public zzgk(zzgi zzgiVar, int i, int i2) {
        this.zzc = zzgiVar;
        this.zza = i;
        this.zzb = i2;
    }

    @Override // java.util.List
    public final E get(int i) {
        zzft.zza(i, this.zzb);
        return this.zzc.get(i + this.zza);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzb;
    }

    @Override // com.google.android.libraries.places.internal.zzgi, java.util.List
    public final /* synthetic */ List subList(int i, int i2) {
        return subList(i, i2);
    }

    @Override // com.google.android.libraries.places.internal.zzgi
    public final zzgi<E> zza(int i, int i2) {
        zzft.zza(i, i2, this.zzb);
        zzgi zzgiVar = this.zzc;
        int i3 = this.zza;
        return (zzgi) zzgiVar.subList(i + i3, i2 + i3);
    }

    @Override // com.google.android.libraries.places.internal.zzgj
    public final Object[] zzd() {
        return this.zzc.zzd();
    }

    @Override // com.google.android.libraries.places.internal.zzgj
    public final int zze() {
        return this.zzc.zze() + this.zza;
    }

    @Override // com.google.android.libraries.places.internal.zzgj
    public final int zzf() {
        return this.zzc.zze() + this.zza + this.zzb;
    }

    @Override // com.google.android.libraries.places.internal.zzgj
    public final boolean zzg() {
        return true;
    }
}
