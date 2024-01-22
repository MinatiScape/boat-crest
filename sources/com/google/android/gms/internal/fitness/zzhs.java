package com.google.android.gms.internal.fitness;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;
/* loaded from: classes8.dex */
public final class zzhs extends v1<String> implements zzhr, RandomAccess {
    public static final zzhs j;
    public final List<Object> i;

    static {
        zzhs zzhsVar = new zzhs();
        j = zzhsVar;
        zzhsVar.zzar();
    }

    public zzhs() {
        this(10);
    }

    public static String a(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzfx) {
            return ((zzfx) obj).zzav();
        }
        return zzhc.zzd((byte[]) obj);
    }

    @Override // com.google.android.gms.internal.fitness.v1, java.util.AbstractList, java.util.List
    public final /* synthetic */ void add(int i, Object obj) {
        zzas();
        this.i.add(i, (String) obj);
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.fitness.v1, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends String> collection) {
        return addAll(size(), collection);
    }

    @Override // com.google.android.gms.internal.fitness.v1, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        zzas();
        this.i.clear();
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.fitness.v1, java.util.AbstractList, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int i) {
        Object obj = this.i.get(i);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzfx) {
            zzfx zzfxVar = (zzfx) obj;
            String zzav = zzfxVar.zzav();
            if (zzfxVar.zzaw()) {
                this.i.set(i, zzav);
            }
            return zzav;
        }
        byte[] bArr = (byte[]) obj;
        String zzd = zzhc.zzd(bArr);
        if (zzhc.zzc(bArr)) {
            this.i.set(i, zzd);
        }
        return zzd;
    }

    @Override // com.google.android.gms.internal.fitness.v1, java.util.AbstractList, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    @Override // com.google.android.gms.internal.fitness.v1, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean remove(Object obj) {
        return super.remove(obj);
    }

    @Override // com.google.android.gms.internal.fitness.v1, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean removeAll(Collection collection) {
        return super.removeAll(collection);
    }

    @Override // com.google.android.gms.internal.fitness.v1, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean retainAll(Collection collection) {
        return super.retainAll(collection);
    }

    @Override // com.google.android.gms.internal.fitness.v1, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object set(int i, Object obj) {
        zzas();
        return a(this.i.set(i, (String) obj));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.i.size();
    }

    @Override // com.google.android.gms.internal.fitness.zzhh
    public final /* synthetic */ zzhh zzae(int i) {
        if (i >= size()) {
            ArrayList arrayList = new ArrayList(i);
            arrayList.addAll(this.i);
            return new zzhs(arrayList);
        }
        throw new IllegalArgumentException();
    }

    @Override // com.google.android.gms.internal.fitness.zzhr
    public final Object zzaf(int i) {
        return this.i.get(i);
    }

    @Override // com.google.android.gms.internal.fitness.v1, com.google.android.gms.internal.fitness.zzhh
    public final /* bridge */ /* synthetic */ boolean zzaq() {
        return super.zzaq();
    }

    @Override // com.google.android.gms.internal.fitness.zzhr
    public final List<?> zzch() {
        return Collections.unmodifiableList(this.i);
    }

    @Override // com.google.android.gms.internal.fitness.zzhr
    public final zzhr zzci() {
        return zzaq() ? new zzjt(this) : this;
    }

    public zzhs(int i) {
        this(new ArrayList(i));
    }

    @Override // com.google.android.gms.internal.fitness.v1, java.util.AbstractList, java.util.List
    public final boolean addAll(int i, Collection<? extends String> collection) {
        zzas();
        if (collection instanceof zzhr) {
            collection = ((zzhr) collection).zzch();
        }
        boolean addAll = this.i.addAll(i, collection);
        ((AbstractList) this).modCount++;
        return addAll;
    }

    @Override // com.google.android.gms.internal.fitness.v1, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object remove(int i) {
        zzas();
        Object remove = this.i.remove(i);
        ((AbstractList) this).modCount++;
        return a(remove);
    }

    public zzhs(ArrayList<Object> arrayList) {
        this.i = arrayList;
    }

    @Override // com.google.android.gms.internal.fitness.v1, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean add(Object obj) {
        return super.add(obj);
    }
}
