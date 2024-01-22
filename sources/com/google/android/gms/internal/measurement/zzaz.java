package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.List;
/* loaded from: classes8.dex */
public final class zzaz extends zzaw {
    public zzaz() {
        this.f8941a.add(zzbl.APPLY);
        this.f8941a.add(zzbl.BLOCK);
        this.f8941a.add(zzbl.BREAK);
        this.f8941a.add(zzbl.CASE);
        this.f8941a.add(zzbl.DEFAULT);
        this.f8941a.add(zzbl.CONTINUE);
        this.f8941a.add(zzbl.DEFINE_FUNCTION);
        this.f8941a.add(zzbl.FN);
        this.f8941a.add(zzbl.IF);
        this.f8941a.add(zzbl.QUOTE);
        this.f8941a.add(zzbl.RETURN);
        this.f8941a.add(zzbl.SWITCH);
        this.f8941a.add(zzbl.TERNARY);
    }

    public static zzap b(zzg zzgVar, List<zzap> list) {
        zzh.zzi(zzbl.FN.name(), 2, list);
        zzap zzb = zzgVar.zzb(list.get(0));
        zzap zzb2 = zzgVar.zzb(list.get(1));
        if (zzb2 instanceof zzae) {
            List<zzap> zzm = ((zzae) zzb2).zzm();
            List<zzap> arrayList = new ArrayList<>();
            if (list.size() > 2) {
                arrayList = list.subList(2, list.size());
            }
            return new zzao(zzb.zzi(), zzm, arrayList, zzgVar);
        }
        throw new IllegalArgumentException(String.format("FN requires an ArrayValue of parameter names found %s", zzb2.getClass().getCanonicalName()));
    }

    /* JADX WARN: Code restructure failed: missing block: B:61:0x0129, code lost:
        if (r8.equals("continue") == false) goto L67;
     */
    @Override // com.google.android.gms.internal.measurement.zzaw
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.google.android.gms.internal.measurement.zzap zza(java.lang.String r8, com.google.android.gms.internal.measurement.zzg r9, java.util.List<com.google.android.gms.internal.measurement.zzap> r10) {
        /*
            Method dump skipped, instructions count: 636
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzaz.zza(java.lang.String, com.google.android.gms.internal.measurement.zzg, java.util.List):com.google.android.gms.internal.measurement.zzap");
    }
}
