package com.google.android.gms.internal.firebase_ml;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;
/* loaded from: classes7.dex */
public final class zzxs extends u5<String> implements zzxv, RandomAccess {
    public static final zzxs j;
    public final List<Object> i;

    static {
        zzxs zzxsVar = new zzxs();
        j = zzxsVar;
        zzxsVar.zztm();
    }

    public zzxs() {
        this(10);
    }

    public static String a(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzvv) {
            return ((zzvv) obj).zztp();
        }
        return zzxd.zzj((byte[]) obj);
    }

    @Override // com.google.android.gms.internal.firebase_ml.u5, java.util.AbstractList, java.util.List
    public final /* synthetic */ void add(int i, Object obj) {
        zztn();
        this.i.add(i, (String) obj);
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.firebase_ml.u5, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends String> collection) {
        return addAll(size(), collection);
    }

    @Override // com.google.android.gms.internal.firebase_ml.u5, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        zztn();
        this.i.clear();
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.firebase_ml.u5, java.util.AbstractList, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int i) {
        Object obj = this.i.get(i);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzvv) {
            zzvv zzvvVar = (zzvv) obj;
            String zztp = zzvvVar.zztp();
            if (zzvvVar.zztq()) {
                this.i.set(i, zztp);
            }
            return zztp;
        }
        byte[] bArr = (byte[]) obj;
        String zzj = zzxd.zzj(bArr);
        if (zzxd.zzi(bArr)) {
            this.i.set(i, zzj);
        }
        return zzj;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzxv
    public final Object getRaw(int i) {
        return this.i.get(i);
    }

    @Override // com.google.android.gms.internal.firebase_ml.u5, java.util.AbstractList, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    @Override // com.google.android.gms.internal.firebase_ml.u5, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean remove(Object obj) {
        return super.remove(obj);
    }

    @Override // com.google.android.gms.internal.firebase_ml.u5, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean removeAll(Collection collection) {
        return super.removeAll(collection);
    }

    @Override // com.google.android.gms.internal.firebase_ml.u5, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean retainAll(Collection collection) {
        return super.retainAll(collection);
    }

    @Override // com.google.android.gms.internal.firebase_ml.u5, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object set(int i, Object obj) {
        zztn();
        return a(this.i.set(i, (String) obj));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.i.size();
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzxl
    public final /* synthetic */ zzxl zzcv(int i) {
        if (i >= size()) {
            ArrayList arrayList = new ArrayList(i);
            arrayList.addAll(this.i);
            return new zzxs(arrayList);
        }
        throw new IllegalArgumentException();
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzxv
    public final void zze(zzvv zzvvVar) {
        zztn();
        this.i.add(zzvvVar);
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.firebase_ml.u5, com.google.android.gms.internal.firebase_ml.zzxl
    public final /* bridge */ /* synthetic */ boolean zztl() {
        return super.zztl();
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzxv
    public final List<?> zzvn() {
        return Collections.unmodifiableList(this.i);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzxv
    public final zzxv zzvo() {
        return zztl() ? new zzaab(this) : this;
    }

    public zzxs(int i) {
        this(new ArrayList(i));
    }

    @Override // com.google.android.gms.internal.firebase_ml.u5, java.util.AbstractList, java.util.List
    public final boolean addAll(int i, Collection<? extends String> collection) {
        zztn();
        if (collection instanceof zzxv) {
            collection = ((zzxv) collection).zzvn();
        }
        boolean addAll = this.i.addAll(i, collection);
        ((AbstractList) this).modCount++;
        return addAll;
    }

    @Override // com.google.android.gms.internal.firebase_ml.u5, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object remove(int i) {
        zztn();
        Object remove = this.i.remove(i);
        ((AbstractList) this).modCount++;
        return a(remove);
    }

    public zzxs(ArrayList<Object> arrayList) {
        this.i = arrayList;
    }

    @Override // com.google.android.gms.internal.firebase_ml.u5, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean add(Object obj) {
        return super.add(obj);
    }
}
