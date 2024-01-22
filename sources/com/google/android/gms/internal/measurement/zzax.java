package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes8.dex */
public final class zzax {

    /* renamed from: a  reason: collision with root package name */
    public final Map<String, zzaw> f8942a = new HashMap();
    public final zzbj b = new zzbj();

    public zzax() {
        a(new zzav());
        a(new zzay());
        a(new zzaz());
        a(new zzbc());
        a(new zzbh());
        a(new zzbi());
        a(new zzbk());
    }

    public final void a(zzaw zzawVar) {
        for (zzbl zzblVar : zzawVar.f8941a) {
            this.f8942a.put(zzblVar.zzb().toString(), zzawVar);
        }
    }

    public final zzap zza(zzg zzgVar, zzap zzapVar) {
        zzaw zzawVar;
        zzh.zzc(zzgVar);
        if (zzapVar instanceof zzaq) {
            zzaq zzaqVar = (zzaq) zzapVar;
            ArrayList<zzap> zzc = zzaqVar.zzc();
            String zzb = zzaqVar.zzb();
            if (this.f8942a.containsKey(zzb)) {
                zzawVar = this.f8942a.get(zzb);
            } else {
                zzawVar = this.b;
            }
            return zzawVar.zza(zzb, zzgVar, zzc);
        }
        return zzapVar;
    }
}
