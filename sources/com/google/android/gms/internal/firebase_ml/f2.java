package com.google.android.gms.internal.firebase_ml;
/* loaded from: classes7.dex */
public final class f2<E> extends zzmw<E> {
    public static final zzmw<Object> zzalt = new f2(new Object[0], 0);
    private final transient int size;
    private final transient Object[] zzalu;

    public f2(Object[] objArr, int i) {
        this.zzalu = objArr;
        this.size = i;
    }

    @Override // java.util.List
    public final E get(int i) {
        zzml.zzb(i, this.size);
        return (E) this.zzalu[i];
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.size;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzmw, com.google.android.gms.internal.firebase_ml.zzmx
    public final int zza(Object[] objArr, int i) {
        System.arraycopy(this.zzalu, 0, objArr, 0, this.size);
        return this.size + 0;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzmx
    public final Object[] zzjl() {
        return this.zzalu;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzmx
    public final int zzjm() {
        return 0;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzmx
    public final int zzjn() {
        return this.size;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzmx
    public final boolean zzjo() {
        return false;
    }
}
