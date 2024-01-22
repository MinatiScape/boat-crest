package com.google.android.libraries.places.internal;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;
/* loaded from: classes10.dex */
public final class zzsw extends zzra<String> implements zzsz, RandomAccess {
    private static final zzsw zza;
    private static final zzsz zzb;
    private final List<Object> zzc;

    static {
        zzsw zzswVar = new zzsw();
        zza = zzswVar;
        zzswVar.zzb();
        zzb = zzswVar;
    }

    public zzsw() {
        this(10);
    }

    @Override // com.google.android.libraries.places.internal.zzra, java.util.AbstractList, java.util.List
    public final /* synthetic */ void add(int i, Object obj) {
        zzc();
        this.zzc.add(i, (String) obj);
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.libraries.places.internal.zzra, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends String> collection) {
        return addAll(size(), collection);
    }

    @Override // com.google.android.libraries.places.internal.zzra, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        zzc();
        this.zzc.clear();
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.libraries.places.internal.zzra, java.util.AbstractList, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int i) {
        Object obj = this.zzc.get(i);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzrb) {
            zzrb zzrbVar = (zzrb) obj;
            String zzb2 = zzrbVar.zzb();
            if (zzrbVar.zzc()) {
                this.zzc.set(i, zzb2);
            }
            return zzb2;
        }
        byte[] bArr = (byte[]) obj;
        String zzb3 = zzsg.zzb(bArr);
        if (zzsg.zza(bArr)) {
            this.zzc.set(i, zzb3);
        }
        return zzb3;
    }

    @Override // com.google.android.libraries.places.internal.zzra, java.util.AbstractList, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    @Override // com.google.android.libraries.places.internal.zzra, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean remove(Object obj) {
        return super.remove(obj);
    }

    @Override // com.google.android.libraries.places.internal.zzra, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean removeAll(Collection collection) {
        return super.removeAll(collection);
    }

    @Override // com.google.android.libraries.places.internal.zzra, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean retainAll(Collection collection) {
        return super.retainAll(collection);
    }

    @Override // com.google.android.libraries.places.internal.zzra, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object set(int i, Object obj) {
        zzc();
        return zza(this.zzc.set(i, (String) obj));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc.size();
    }

    @Override // com.google.android.libraries.places.internal.zzsz
    public final void zza(zzrb zzrbVar) {
        zzc();
        this.zzc.add(zzrbVar);
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.libraries.places.internal.zzsp
    public final /* synthetic */ zzsp zzb(int i) {
        if (i >= size()) {
            ArrayList arrayList = new ArrayList(i);
            arrayList.addAll(this.zzc);
            return new zzsw(arrayList);
        }
        throw new IllegalArgumentException();
    }

    @Override // com.google.android.libraries.places.internal.zzsz
    public final List<?> zzd() {
        return Collections.unmodifiableList(this.zzc);
    }

    @Override // com.google.android.libraries.places.internal.zzsz
    public final zzsz zze() {
        return zza() ? new zzvb(this) : this;
    }

    public zzsw(int i) {
        this(new ArrayList(i));
    }

    @Override // com.google.android.libraries.places.internal.zzra, java.util.AbstractList, java.util.List
    public final boolean addAll(int i, Collection<? extends String> collection) {
        zzc();
        if (collection instanceof zzsz) {
            collection = ((zzsz) collection).zzd();
        }
        boolean addAll = this.zzc.addAll(i, collection);
        ((AbstractList) this).modCount++;
        return addAll;
    }

    @Override // com.google.android.libraries.places.internal.zzra, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object remove(int i) {
        zzc();
        Object remove = this.zzc.remove(i);
        ((AbstractList) this).modCount++;
        return zza(remove);
    }

    private zzsw(ArrayList<Object> arrayList) {
        this.zzc = arrayList;
    }

    @Override // com.google.android.libraries.places.internal.zzsz
    public final Object zza(int i) {
        return this.zzc.get(i);
    }

    private static String zza(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzrb) {
            return ((zzrb) obj).zzb();
        }
        return zzsg.zzb((byte[]) obj);
    }

    @Override // com.google.android.libraries.places.internal.zzra, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean add(Object obj) {
        return super.add(obj);
    }

    @Override // com.google.android.libraries.places.internal.zzra, com.google.android.libraries.places.internal.zzsp
    public final /* bridge */ /* synthetic */ boolean zza() {
        return super.zza();
    }
}
