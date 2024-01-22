package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.gtm.zzro;
import com.google.android.gms.internal.gtm.zzrw;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
/* loaded from: classes10.dex */
public final class zzet {
    public zzro zzf;
    public final Set<zzrw> zza = new HashSet();
    public final Map<zzrw, List<zzro>> zzb = new HashMap();
    public final Map<zzrw, List<String>> zzd = new HashMap();
    public final Map<zzrw, List<zzro>> zzc = new HashMap();
    public final Map<zzrw, List<String>> zze = new HashMap();

    public final zzro zza() {
        return this.zzf;
    }

    public final Map<zzrw, List<String>> zzb() {
        return this.zzd;
    }

    public final Map<zzrw, List<zzro>> zzc() {
        return this.zzb;
    }

    public final Map<zzrw, List<String>> zzd() {
        return this.zze;
    }

    public final Map<zzrw, List<zzro>> zze() {
        return this.zzc;
    }

    public final Set<zzrw> zzf() {
        return this.zza;
    }

    public final void zzg(zzrw zzrwVar, zzro zzroVar) {
        List<zzro> list = this.zzb.get(zzrwVar);
        if (list == null) {
            list = new ArrayList<>();
            this.zzb.put(zzrwVar, list);
        }
        list.add(zzroVar);
    }

    public final void zzh(zzrw zzrwVar, String str) {
        List<String> list = this.zzd.get(zzrwVar);
        if (list == null) {
            list = new ArrayList<>();
            this.zzd.put(zzrwVar, list);
        }
        list.add("Unknown");
    }

    public final void zzi(zzrw zzrwVar, zzro zzroVar) {
        List<zzro> list = this.zzc.get(zzrwVar);
        if (list == null) {
            list = new ArrayList<>();
            this.zzc.put(zzrwVar, list);
        }
        list.add(zzroVar);
    }

    public final void zzj(zzrw zzrwVar, String str) {
        List<String> list = this.zze.get(zzrwVar);
        if (list == null) {
            list = new ArrayList<>();
            this.zze.put(zzrwVar, list);
        }
        list.add("Unknown");
    }

    public final void zzk(zzrw zzrwVar) {
        this.zza.add(zzrwVar);
    }

    public final void zzl(zzro zzroVar) {
        this.zzf = zzroVar;
    }
}
