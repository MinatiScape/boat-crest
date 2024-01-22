package com.google.android.gms.internal.measurement;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;
/* loaded from: classes8.dex */
public final class zzkn extends e2<String> implements RandomAccess, zzko {
    public static final zzkn j;
    public static final zzko zza;
    public final List<Object> i;

    static {
        zzkn zzknVar = new zzkn(10);
        j = zzknVar;
        zzknVar.zzb();
        zza = zzknVar;
    }

    public zzkn() {
        this(10);
    }

    public static String a(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zziy) {
            return ((zziy) obj).zzn(zzkh.f8963a);
        }
        return zzkh.zzh((byte[]) obj);
    }

    @Override // com.google.android.gms.internal.measurement.e2, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ void add(int i, Object obj) {
        zzbM();
        this.i.add(i, (String) obj);
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.measurement.e2, java.util.AbstractList, java.util.List
    public final boolean addAll(int i, Collection<? extends String> collection) {
        zzbM();
        if (collection instanceof zzko) {
            collection = ((zzko) collection).zzh();
        }
        boolean addAll = this.i.addAll(i, collection);
        ((AbstractList) this).modCount++;
        return addAll;
    }

    @Override // com.google.android.gms.internal.measurement.e2, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        zzbM();
        this.i.clear();
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.measurement.e2, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object remove(int i) {
        zzbM();
        Object remove = this.i.remove(i);
        ((AbstractList) this).modCount++;
        return a(remove);
    }

    @Override // com.google.android.gms.internal.measurement.e2, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object set(int i, Object obj) {
        zzbM();
        return a(this.i.set(i, (String) obj));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.i.size();
    }

    @Override // com.google.android.gms.internal.measurement.zzkg
    public final /* bridge */ /* synthetic */ zzkg zzd(int i) {
        if (i >= size()) {
            ArrayList arrayList = new ArrayList(i);
            arrayList.addAll(this.i);
            return new zzkn(arrayList);
        }
        throw new IllegalArgumentException();
    }

    @Override // com.google.android.gms.internal.measurement.zzko
    public final zzko zze() {
        return zzc() ? new zzmn(this) : this;
    }

    @Override // com.google.android.gms.internal.measurement.zzko
    public final Object zzf(int i) {
        return this.i.get(i);
    }

    @Override // java.util.AbstractList, java.util.List
    /* renamed from: zzg */
    public final String get(int i) {
        Object obj = this.i.get(i);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zziy) {
            zziy zziyVar = (zziy) obj;
            String zzn = zziyVar.zzn(zzkh.f8963a);
            if (zziyVar.zzi()) {
                this.i.set(i, zzn);
            }
            return zzn;
        }
        byte[] bArr = (byte[]) obj;
        String zzh = zzkh.zzh(bArr);
        if (zzkh.zzi(bArr)) {
            this.i.set(i, zzh);
        }
        return zzh;
    }

    @Override // com.google.android.gms.internal.measurement.zzko
    public final List<?> zzh() {
        return Collections.unmodifiableList(this.i);
    }

    @Override // com.google.android.gms.internal.measurement.zzko
    public final void zzi(zziy zziyVar) {
        zzbM();
        this.i.add(zziyVar);
        ((AbstractList) this).modCount++;
    }

    public zzkn(int i) {
        this.i = new ArrayList(i);
    }

    public zzkn(ArrayList<Object> arrayList) {
        this.i = arrayList;
    }

    @Override // com.google.android.gms.internal.measurement.e2, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends String> collection) {
        return addAll(size(), collection);
    }
}
