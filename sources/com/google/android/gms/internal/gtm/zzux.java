package com.google.android.gms.internal.gtm;

import com.google.android.gms.internal.gtm.zzwk;
/* loaded from: classes8.dex */
public final class zzux<ContainingType extends zzwk, Type> extends zzuh<ContainingType, Type> {
    public final ContainingType zza;
    public final Type zzb;
    public final zzwk zzc;
    public final zzuw zzd;

    public zzux(ContainingType containingtype, Type type, zzwk zzwkVar, zzuw zzuwVar, Class cls) {
        if (containingtype != null) {
            if (zzuwVar.zzc == zzye.zzk && zzwkVar == null) {
                throw new IllegalArgumentException("Null messageDefaultInstance");
            }
            this.zza = containingtype;
            this.zzb = type;
            this.zzc = zzwkVar;
            this.zzd = zzuwVar;
            return;
        }
        throw new IllegalArgumentException("Null containingTypeDefaultInstance");
    }

    public final boolean zza() {
        boolean z = this.zzd.zzd;
        return false;
    }
}
