package com.google.android.gms.internal.vision;
/* loaded from: classes10.dex */
public final class z<E> extends zzdf<E> {
    public static final zzdf<Object> zzlx = new z(new Object[0], 0);
    private final transient int size;
    private final transient Object[] zzly;

    public z(Object[] objArr, int i) {
        this.zzly = objArr;
        this.size = i;
    }

    @Override // java.util.List
    public final E get(int i) {
        zzct.zzc(i, this.size);
        return (E) this.zzly[i];
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.size;
    }

    @Override // com.google.android.gms.internal.vision.zzdf, com.google.android.gms.internal.vision.zzdc
    public final int zza(Object[] objArr, int i) {
        System.arraycopy(this.zzly, 0, objArr, i, this.size);
        return i + this.size;
    }

    @Override // com.google.android.gms.internal.vision.zzdc
    public final Object[] zzbz() {
        return this.zzly;
    }

    @Override // com.google.android.gms.internal.vision.zzdc
    public final int zzca() {
        return 0;
    }

    @Override // com.google.android.gms.internal.vision.zzdc
    public final int zzcb() {
        return this.size;
    }
}
