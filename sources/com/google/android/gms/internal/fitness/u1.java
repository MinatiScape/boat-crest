package com.google.android.gms.internal.fitness;

import java.util.Iterator;
/* loaded from: classes8.dex */
public final class u1<E> extends zzfh<E> {
    private final transient E zzts;
    private transient int zztt;

    public u1(E e) {
        this.zzts = (E) zzez.checkNotNull(e);
    }

    @Override // com.google.android.gms.internal.fitness.zzfd, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        return this.zzts.equals(obj);
    }

    @Override // com.google.android.gms.internal.fitness.zzfh, java.util.Collection, java.util.Set
    public final int hashCode() {
        int i = this.zztt;
        if (i == 0) {
            int hashCode = this.zzts.hashCode();
            this.zztt = hashCode;
            return hashCode;
        }
        return i;
    }

    @Override // com.google.android.gms.internal.fitness.zzfh, com.google.android.gms.internal.fitness.zzfd, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final /* synthetic */ Iterator iterator() {
        return iterator();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return 1;
    }

    @Override // java.util.AbstractCollection
    public final String toString() {
        String obj = this.zzts.toString();
        StringBuilder sb = new StringBuilder(String.valueOf(obj).length() + 2);
        sb.append('[');
        sb.append(obj);
        sb.append(']');
        return sb.toString();
    }

    @Override // com.google.android.gms.internal.fitness.zzfd
    public final zzfm<E> zzae() {
        return new r1(this.zzts);
    }

    @Override // com.google.android.gms.internal.fitness.zzfd
    public final boolean zzaj() {
        return false;
    }

    @Override // com.google.android.gms.internal.fitness.zzfh
    public final boolean zzak() {
        return this.zztt != 0;
    }

    @Override // com.google.android.gms.internal.fitness.zzfh
    public final zzfc<E> zzal() {
        return zzfc.zzb(this.zzts);
    }

    @Override // com.google.android.gms.internal.fitness.zzfd
    public final int zzb(Object[] objArr, int i) {
        objArr[0] = this.zzts;
        return 1;
    }

    public u1(E e, int i) {
        this.zzts = e;
        this.zztt = i;
    }
}
