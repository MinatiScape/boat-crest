package com.google.android.gms.internal.fitness;

import java.util.Iterator;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* loaded from: classes8.dex */
public final class t1<E> extends zzfh<E> {
    public static final t1<Object> zzto = new t1<>(new Object[0], 0, null, 0, 0);
    private final transient int mask;
    private final transient int size;
    private final transient Object[] zztp;
    private final transient Object[] zztq;
    private final transient int zztr;

    public t1(Object[] objArr, int i, Object[] objArr2, int i2, int i3) {
        this.zztp = objArr;
        this.zztq = objArr2;
        this.mask = i2;
        this.zztr = i;
        this.size = i3;
    }

    @Override // com.google.android.gms.internal.fitness.zzfd, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(@NullableDecl Object obj) {
        Object[] objArr = this.zztq;
        if (obj == null || objArr == null) {
            return false;
        }
        int a2 = n1.a(obj.hashCode());
        while (true) {
            int i = a2 & this.mask;
            Object obj2 = objArr[i];
            if (obj2 == null) {
                return false;
            }
            if (obj2.equals(obj)) {
                return true;
            }
            a2 = i + 1;
        }
    }

    @Override // com.google.android.gms.internal.fitness.zzfh, java.util.Collection, java.util.Set
    public final int hashCode() {
        return this.zztr;
    }

    @Override // com.google.android.gms.internal.fitness.zzfh, com.google.android.gms.internal.fitness.zzfd, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final /* synthetic */ Iterator iterator() {
        return iterator();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.size;
    }

    @Override // com.google.android.gms.internal.fitness.zzfd
    public final zzfm<E> zzae() {
        return (zzfm) zzaf().iterator();
    }

    @Override // com.google.android.gms.internal.fitness.zzfd
    public final Object[] zzag() {
        return this.zztp;
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

    @Override // com.google.android.gms.internal.fitness.zzfh
    public final boolean zzak() {
        return true;
    }

    @Override // com.google.android.gms.internal.fitness.zzfh
    public final zzfc<E> zzal() {
        return zzfc.zza(this.zztp, this.size);
    }

    @Override // com.google.android.gms.internal.fitness.zzfd
    public final int zzb(Object[] objArr, int i) {
        System.arraycopy(this.zztp, 0, objArr, 0, this.size);
        return this.size + 0;
    }
}
