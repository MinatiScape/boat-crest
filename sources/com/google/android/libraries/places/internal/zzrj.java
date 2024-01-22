package com.google.android.libraries.places.internal;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public final class zzrj {
    private final zzrs zza;
    private final byte[] zzb;

    private zzrj(int i) {
        byte[] bArr = new byte[i];
        this.zzb = bArr;
        this.zza = zzrs.zza(bArr);
    }

    public final zzrb zza() {
        this.zza.zzb();
        return new zzrl(this.zzb);
    }

    public final zzrs zzb() {
        return this.zza;
    }

    public /* synthetic */ zzrj(int i, zzre zzreVar) {
        this(i);
    }
}
