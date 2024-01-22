package com.google.android.gms.internal.vision;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;
/* loaded from: classes10.dex */
public final class zzhk extends c1<String> implements zzhj, RandomAccess {
    public static final zzhk j;
    public final List<Object> i;

    static {
        zzhk zzhkVar = new zzhk();
        j = zzhkVar;
        zzhkVar.zzdp();
    }

    public zzhk() {
        this(10);
    }

    public static String a(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzfh) {
            return ((zzfh) obj).zzer();
        }
        return zzgt.zzh((byte[]) obj);
    }

    @Override // com.google.android.gms.internal.vision.c1, java.util.AbstractList, java.util.List
    public final /* synthetic */ void add(int i, Object obj) {
        zzdq();
        this.i.add(i, (String) obj);
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.vision.c1, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends String> collection) {
        return addAll(size(), collection);
    }

    @Override // com.google.android.gms.internal.vision.c1, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        zzdq();
        this.i.clear();
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.vision.c1, java.util.AbstractList, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int i) {
        Object obj = this.i.get(i);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzfh) {
            zzfh zzfhVar = (zzfh) obj;
            String zzer = zzfhVar.zzer();
            if (zzfhVar.zzes()) {
                this.i.set(i, zzer);
            }
            return zzer;
        }
        byte[] bArr = (byte[]) obj;
        String zzh = zzgt.zzh(bArr);
        if (zzgt.zzg(bArr)) {
            this.i.set(i, zzh);
        }
        return zzh;
    }

    @Override // com.google.android.gms.internal.vision.zzhj
    public final Object getRaw(int i) {
        return this.i.get(i);
    }

    @Override // com.google.android.gms.internal.vision.c1, java.util.AbstractList, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    @Override // com.google.android.gms.internal.vision.c1, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean remove(Object obj) {
        return super.remove(obj);
    }

    @Override // com.google.android.gms.internal.vision.c1, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean removeAll(Collection collection) {
        return super.removeAll(collection);
    }

    @Override // com.google.android.gms.internal.vision.c1, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean retainAll(Collection collection) {
        return super.retainAll(collection);
    }

    @Override // com.google.android.gms.internal.vision.c1, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object set(int i, Object obj) {
        zzdq();
        return a(this.i.set(i, (String) obj));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.i.size();
    }

    @Override // com.google.android.gms.internal.vision.zzgz
    public final /* synthetic */ zzgz zzah(int i) {
        if (i >= size()) {
            ArrayList arrayList = new ArrayList(i);
            arrayList.addAll(this.i);
            return new zzhk(arrayList);
        }
        throw new IllegalArgumentException();
    }

    @Override // com.google.android.gms.internal.vision.zzhj
    public final void zzc(zzfh zzfhVar) {
        zzdq();
        this.i.add(zzfhVar);
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.vision.c1, com.google.android.gms.internal.vision.zzgz
    public final /* bridge */ /* synthetic */ boolean zzdo() {
        return super.zzdo();
    }

    @Override // com.google.android.gms.internal.vision.zzhj
    public final List<?> zzgx() {
        return Collections.unmodifiableList(this.i);
    }

    @Override // com.google.android.gms.internal.vision.zzhj
    public final zzhj zzgy() {
        return zzdo() ? new zzjo(this) : this;
    }

    public zzhk(int i) {
        this(new ArrayList(i));
    }

    @Override // com.google.android.gms.internal.vision.c1, java.util.AbstractList, java.util.List
    public final boolean addAll(int i, Collection<? extends String> collection) {
        zzdq();
        if (collection instanceof zzhj) {
            collection = ((zzhj) collection).zzgx();
        }
        boolean addAll = this.i.addAll(i, collection);
        ((AbstractList) this).modCount++;
        return addAll;
    }

    @Override // com.google.android.gms.internal.vision.c1, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object remove(int i) {
        zzdq();
        Object remove = this.i.remove(i);
        ((AbstractList) this).modCount++;
        return a(remove);
    }

    public zzhk(ArrayList<Object> arrayList) {
        this.i = arrayList;
    }

    @Override // com.google.android.gms.internal.vision.c1, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean add(Object obj) {
        return super.add(obj);
    }
}
