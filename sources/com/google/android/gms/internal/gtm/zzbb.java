package com.google.android.gms.internal.gtm;

import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.google.android.gms.common.internal.ShowFirstParty;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@ShowFirstParty
/* loaded from: classes8.dex */
public final class zzbb extends com.google.android.gms.analytics.zzj<zzbb> {
    public final List<Product> zza = new ArrayList();
    public final List<Promotion> zzb = new ArrayList();
    public final Map<String, List<Product>> zzc = new HashMap();

    public final String toString() {
        HashMap hashMap = new HashMap();
        if (!this.zza.isEmpty()) {
            hashMap.put("products", this.zza);
        }
        if (!this.zzb.isEmpty()) {
            hashMap.put("promotions", this.zzb);
        }
        if (!this.zzc.isEmpty()) {
            hashMap.put("impressions", this.zzc);
        }
        hashMap.put("productAction", null);
        return com.google.android.gms.analytics.zzj.zza(hashMap);
    }

    @Override // com.google.android.gms.analytics.zzj
    public final /* bridge */ /* synthetic */ void zzc(zzbb zzbbVar) {
        zzbb zzbbVar2 = zzbbVar;
        zzbbVar2.zza.addAll(this.zza);
        zzbbVar2.zzb.addAll(this.zzb);
        for (Map.Entry<String, List<Product>> entry : this.zzc.entrySet()) {
            String key = entry.getKey();
            for (Product product : entry.getValue()) {
                if (product != null) {
                    String str = key == null ? "" : key;
                    if (!zzbbVar2.zzc.containsKey(str)) {
                        zzbbVar2.zzc.put(str, new ArrayList());
                    }
                    zzbbVar2.zzc.get(str).add(product);
                }
            }
        }
    }

    public final List<Product> zzd() {
        return Collections.unmodifiableList(this.zza);
    }

    public final List<Promotion> zze() {
        return Collections.unmodifiableList(this.zzb);
    }

    public final Map<String, List<Product>> zzf() {
        return this.zzc;
    }
}
