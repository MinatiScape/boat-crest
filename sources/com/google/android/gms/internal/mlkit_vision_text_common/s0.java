package com.google.android.gms.internal.mlkit_vision_text_common;

import java.util.Iterator;
import javax.annotation.CheckForNull;
/* loaded from: classes6.dex */
public final class s0 extends zzbp {
    private final transient zzbo zza;
    private final transient zzbm zzb;

    public s0(zzbo zzboVar, zzbm zzbmVar) {
        this.zza = zzboVar;
        this.zzb = zzbmVar;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzbh, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(@CheckForNull Object obj) {
        return this.zza.get(obj) != null;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzbp, com.google.android.gms.internal.mlkit_vision_text_common.zzbh, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final /* synthetic */ Iterator iterator() {
        return this.zzb.listIterator(0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zza.size();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzbh
    public final int zza(Object[] objArr, int i) {
        return this.zzb.zza(objArr, 0);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzbp, com.google.android.gms.internal.mlkit_vision_text_common.zzbh
    public final zzcq zzd() {
        return this.zzb.listIterator(0);
    }
}
