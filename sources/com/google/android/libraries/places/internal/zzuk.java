package com.google.android.libraries.places.internal;

import java.util.Iterator;
import java.util.Map;
/* loaded from: classes10.dex */
final class zzuk extends zzuq {
    private final /* synthetic */ zzuj zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    private zzuk(zzuj zzujVar) {
        super(zzujVar, null);
        this.zza = zzujVar;
    }

    @Override // com.google.android.libraries.places.internal.zzuq, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator<Map.Entry<K, V>> iterator() {
        return new zzul(this.zza, null);
    }

    public /* synthetic */ zzuk(zzuj zzujVar, zzui zzuiVar) {
        this(zzujVar);
    }
}
