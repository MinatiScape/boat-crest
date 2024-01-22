package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;
/* loaded from: classes8.dex */
public final class zzev extends k implements RandomAccess, zzew {
    public static final zzev j;
    @Deprecated
    public static final zzew zza;
    public final List i;

    static {
        zzev zzevVar = new zzev(false);
        j = zzevVar;
        zza = zzevVar;
    }

    public zzev() {
        this(10);
    }

    public static String a(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzdb) {
            return ((zzdb) obj).zzt(zzem.f9632a);
        }
        return zzem.zzd((byte[]) obj);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.k, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ void add(int i, Object obj) {
        zza();
        this.i.add(i, (String) obj);
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.k, java.util.AbstractList, java.util.List
    public final boolean addAll(int i, Collection collection) {
        zza();
        if (collection instanceof zzew) {
            collection = ((zzew) collection).zzh();
        }
        boolean addAll = this.i.addAll(i, collection);
        ((AbstractList) this).modCount++;
        return addAll;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.k, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        zza();
        this.i.clear();
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.k, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object remove(int i) {
        zza();
        Object remove = this.i.remove(i);
        ((AbstractList) this).modCount++;
        return a(remove);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.k, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object set(int i, Object obj) {
        zza();
        return a(this.i.set(i, (String) obj));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.i.size();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzel
    public final /* bridge */ /* synthetic */ zzel zzd(int i) {
        if (i >= size()) {
            ArrayList arrayList = new ArrayList(i);
            arrayList.addAll(this.i);
            return new zzev(arrayList);
        }
        throw new IllegalArgumentException();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzew
    public final zzew zze() {
        return zzc() ? new zzhd(this) : this;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzew
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
        if (obj instanceof zzdb) {
            zzdb zzdbVar = (zzdb) obj;
            String zzt = zzdbVar.zzt(zzem.f9632a);
            if (zzdbVar.zzn()) {
                this.i.set(i, zzt);
            }
            return zzt;
        }
        byte[] bArr = (byte[]) obj;
        String zzd = zzem.zzd(bArr);
        if (l2.g(bArr)) {
            this.i.set(i, zzd);
        }
        return zzd;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzew
    public final List zzh() {
        return Collections.unmodifiableList(this.i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzev(int i) {
        super(true);
        ArrayList arrayList = new ArrayList(i);
        this.i = arrayList;
    }

    public zzev(ArrayList arrayList) {
        super(true);
        this.i = arrayList;
    }

    public zzev(boolean z) {
        super(false);
        this.i = Collections.emptyList();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.k, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        return addAll(size(), collection);
    }
}
