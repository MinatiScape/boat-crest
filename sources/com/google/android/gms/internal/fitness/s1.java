package com.google.android.gms.internal.fitness;
/* loaded from: classes8.dex */
public final class s1<E> extends zzfc<E> {
    public static final zzfc<Object> zztm = new s1(new Object[0], 0);
    private final transient int size;
    private final transient Object[] zztn;

    public s1(Object[] objArr, int i) {
        this.zztn = objArr;
        this.size = i;
    }

    @Override // java.util.List
    public final E get(int i) {
        zzez.zza(i, this.size);
        return (E) this.zztn[i];
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.size;
    }

    @Override // com.google.android.gms.internal.fitness.zzfd
    public final Object[] zzag() {
        return this.zztn;
    }

    @Override // com.google.android.gms.internal.fitness.zzfd
    public final int zzah() {
        return 0;
    }

    @Override // com.google.android.gms.internal.fitness.zzfd
    public final int zzai() {
        return this.size;
    }

    @Override // com.google.android.gms.internal.fitness.zzfd
    public final boolean zzaj() {
        return false;
    }

    @Override // com.google.android.gms.internal.fitness.zzfc, com.google.android.gms.internal.fitness.zzfd
    public final int zzb(Object[] objArr, int i) {
        System.arraycopy(this.zztn, 0, objArr, 0, this.size);
        return this.size + 0;
    }
}
