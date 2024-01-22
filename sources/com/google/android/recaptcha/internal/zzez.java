package com.google.android.recaptcha.internal;

import java.util.NoSuchElementException;
/* loaded from: classes10.dex */
final class zzez extends zzfb {
    public final /* synthetic */ zzfi zza;
    private int zzb = 0;
    private final int zzc;

    public zzez(zzfi zzfiVar) {
        this.zza = zzfiVar;
        this.zzc = zzfiVar.zzd();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zzb < this.zzc;
    }

    @Override // com.google.android.recaptcha.internal.zzfd
    public final byte zza() {
        int i = this.zzb;
        if (i < this.zzc) {
            this.zzb = i + 1;
            return this.zza.zzb(i);
        }
        throw new NoSuchElementException();
    }
}
