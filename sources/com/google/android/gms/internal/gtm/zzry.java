package com.google.android.gms.internal.gtm;

import java.util.ArrayList;
import java.util.List;
/* loaded from: classes8.dex */
public final class zzry {
    public final List<zzro> zza = new ArrayList();
    public final List<zzro> zzb = new ArrayList();
    public final List<zzro> zzc = new ArrayList();
    public final List<zzro> zzd = new ArrayList();
    public final List<zzro> zze = new ArrayList();
    public final List<zzro> zzf = new ArrayList();
    public final List<String> zzg = new ArrayList();
    public final List<String> zzh = new ArrayList();
    public final List<String> zzi = new ArrayList();
    public final List<String> zzj = new ArrayList();

    public zzry() {
    }

    public final zzrw zza() {
        return new zzrw(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, this.zzg, this.zzh, this.zzi, this.zzj, null);
    }

    public final zzry zzb(zzro zzroVar) {
        this.zze.add(zzroVar);
        return this;
    }

    public final zzry zzc(String str) {
        this.zzg.add(str);
        return this;
    }

    public final zzry zzd(zzro zzroVar) {
        this.zzc.add(zzroVar);
        return this;
    }

    public final zzry zze(String str) {
        this.zzi.add(str);
        return this;
    }

    public final zzry zzf(zzro zzroVar) {
        this.zzb.add(zzroVar);
        return this;
    }

    public final zzry zzg(zzro zzroVar) {
        this.zza.add(zzroVar);
        return this;
    }

    public final zzry zzh(zzro zzroVar) {
        this.zzf.add(zzroVar);
        return this;
    }

    public final zzry zzi(String str) {
        this.zzh.add(str);
        return this;
    }

    public final zzry zzj(zzro zzroVar) {
        this.zzd.add(zzroVar);
        return this;
    }

    public final zzry zzk(String str) {
        this.zzj.add(str);
        return this;
    }

    public /* synthetic */ zzry(zzrx zzrxVar) {
    }
}
