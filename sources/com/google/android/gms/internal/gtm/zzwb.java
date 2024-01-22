package com.google.android.gms.internal.gtm;
/* loaded from: classes8.dex */
public final class zzwb implements zzwi {
    public final zzwi[] zza;

    public zzwb(zzwi... zzwiVarArr) {
        this.zza = zzwiVarArr;
    }

    @Override // com.google.android.gms.internal.gtm.zzwi
    public final zzwh zzb(Class<?> cls) {
        zzwi[] zzwiVarArr = this.zza;
        for (int i = 0; i < 2; i++) {
            zzwi zzwiVar = zzwiVarArr[i];
            if (zzwiVar.zzc(cls)) {
                return zzwiVar.zzb(cls);
            }
        }
        String name = cls.getName();
        throw new UnsupportedOperationException(name.length() != 0 ? "No factory is available for message type: ".concat(name) : new String("No factory is available for message type: "));
    }

    @Override // com.google.android.gms.internal.gtm.zzwi
    public final boolean zzc(Class<?> cls) {
        zzwi[] zzwiVarArr = this.zza;
        for (int i = 0; i < 2; i++) {
            if (zzwiVarArr[i].zzc(cls)) {
                return true;
            }
        }
        return false;
    }
}
