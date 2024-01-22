package com.google.android.gms.internal.mlkit_code_scanner;

import java.util.Iterator;
import javax.annotation.CheckForNull;
/* loaded from: classes8.dex */
public final class a6 extends zzs {
    private final transient zzr zza;
    private final transient zzp zzb;

    public a6(zzr zzrVar, zzp zzpVar) {
        this.zza = zzrVar;
        this.zzb = zzpVar;
    }

    @Override // com.google.android.gms.internal.mlkit_code_scanner.zzl, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(@CheckForNull Object obj) {
        return this.zza.get(obj) != null;
    }

    @Override // com.google.android.gms.internal.mlkit_code_scanner.zzs, com.google.android.gms.internal.mlkit_code_scanner.zzl, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final /* synthetic */ Iterator iterator() {
        return this.zzb.listIterator(0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zza.size();
    }

    @Override // com.google.android.gms.internal.mlkit_code_scanner.zzl
    public final int zza(Object[] objArr, int i) {
        return this.zzb.zza(objArr, 0);
    }

    @Override // com.google.android.gms.internal.mlkit_code_scanner.zzs, com.google.android.gms.internal.mlkit_code_scanner.zzl
    public final zzaa zzd() {
        return this.zzb.listIterator(0);
    }
}
