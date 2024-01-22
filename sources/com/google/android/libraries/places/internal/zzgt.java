package com.google.android.libraries.places.internal;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public final class zzgt<E> extends zzgi<E> {
    public static final zzgi<Object> zza = new zzgt(new Object[0], 0);
    private final transient Object[] zzb;
    private final transient int zzc;

    public zzgt(Object[] objArr, int i) {
        this.zzb = objArr;
        this.zzc = i;
    }

    @Override // java.util.List
    public final E get(int i) {
        zzft.zza(i, this.zzc);
        return (E) this.zzb[i];
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc;
    }

    @Override // com.google.android.libraries.places.internal.zzgi, com.google.android.libraries.places.internal.zzgj
    public final int zza(Object[] objArr, int i) {
        System.arraycopy(this.zzb, 0, objArr, i, this.zzc);
        return i + this.zzc;
    }

    @Override // com.google.android.libraries.places.internal.zzgj
    public final Object[] zzd() {
        return this.zzb;
    }

    @Override // com.google.android.libraries.places.internal.zzgj
    public final int zze() {
        return 0;
    }

    @Override // com.google.android.libraries.places.internal.zzgj
    public final int zzf() {
        return this.zzc;
    }

    @Override // com.google.android.libraries.places.internal.zzgj
    public final boolean zzg() {
        return false;
    }
}
