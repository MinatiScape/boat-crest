package com.google.android.libraries.places.internal;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public final class zzgl<E> extends zzfy<E> {
    private final zzgi<E> zza;

    public zzgl(zzgi<E> zzgiVar, int i) {
        super(zzgiVar.size(), i);
        this.zza = zzgiVar;
    }

    @Override // com.google.android.libraries.places.internal.zzfy
    public final E zza(int i) {
        return this.zza.get(i);
    }
}
