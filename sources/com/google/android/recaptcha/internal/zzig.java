package com.google.android.recaptcha.internal;
/* loaded from: classes10.dex */
final class zzig implements zzin {
    private final zzin[] zza;

    public zzig(zzin... zzinVarArr) {
        this.zza = zzinVarArr;
    }

    @Override // com.google.android.recaptcha.internal.zzin
    public final zzim zzb(Class cls) {
        zzin[] zzinVarArr = this.zza;
        for (int i = 0; i < 2; i++) {
            zzin zzinVar = zzinVarArr[i];
            if (zzinVar.zzc(cls)) {
                return zzinVar.zzb(cls);
            }
        }
        throw new UnsupportedOperationException("No factory is available for message type: ".concat(cls.getName()));
    }

    @Override // com.google.android.recaptcha.internal.zzin
    public final boolean zzc(Class cls) {
        zzin[] zzinVarArr = this.zza;
        for (int i = 0; i < 2; i++) {
            if (zzinVarArr[i].zzc(cls)) {
                return true;
            }
        }
        return false;
    }
}
