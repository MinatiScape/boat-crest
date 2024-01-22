package com.google.android.gms.internal.gtm;
/* loaded from: classes8.dex */
public final class zzwv implements zzwh {
    public final zzwk zza;
    public final String zzb;
    public final Object[] zzc;
    public final int zzd;

    public zzwv(zzwk zzwkVar, String str, Object[] objArr) {
        this.zza = zzwkVar;
        this.zzb = str;
        this.zzc = objArr;
        char charAt = str.charAt(0);
        if (charAt < 55296) {
            this.zzd = charAt;
            return;
        }
        int i = charAt & 8191;
        int i2 = 13;
        int i3 = 1;
        while (true) {
            int i4 = i3 + 1;
            char charAt2 = str.charAt(i3);
            if (charAt2 < 55296) {
                this.zzd = i | (charAt2 << i2);
                return;
            }
            i |= (charAt2 & 8191) << i2;
            i2 += 13;
            i3 = i4;
        }
    }

    @Override // com.google.android.gms.internal.gtm.zzwh
    public final zzwk zza() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.gtm.zzwh
    public final boolean zzb() {
        return (this.zzd & 2) == 2;
    }

    @Override // com.google.android.gms.internal.gtm.zzwh
    public final int zzc() {
        return (this.zzd & 1) == 1 ? 1 : 2;
    }

    public final String zzd() {
        return this.zzb;
    }

    public final Object[] zze() {
        return this.zzc;
    }
}
