package com.google.android.libraries.places.internal;
/* loaded from: classes10.dex */
final class zzth implements zztp {
    private zztp[] zza;

    public zzth(zztp... zztpVarArr) {
        this.zza = zztpVarArr;
    }

    @Override // com.google.android.libraries.places.internal.zztp
    public final boolean zza(Class<?> cls) {
        for (zztp zztpVar : this.zza) {
            if (zztpVar.zza(cls)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.libraries.places.internal.zztp
    public final zztm zzb(Class<?> cls) {
        zztp[] zztpVarArr;
        for (zztp zztpVar : this.zza) {
            if (zztpVar.zza(cls)) {
                return zztpVar.zzb(cls);
            }
        }
        String name = cls.getName();
        throw new UnsupportedOperationException(name.length() != 0 ? "No factory is available for message type: ".concat(name) : new String("No factory is available for message type: "));
    }
}
