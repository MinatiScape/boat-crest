package com.google.android.gms.internal.firebase_ml;

import java.io.IOException;
/* loaded from: classes7.dex */
public final class zzgb implements zzgy, zzhe {
    public zzgb() {
        this(false);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzhe
    public final void zza(zzhc zzhcVar) {
        zzhcVar.zza(this);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzgy
    public final void zzb(zzhc zzhcVar) throws IOException {
        String requestMethod = zzhcVar.getRequestMethod();
        boolean z = true;
        if (requestMethod.equals("POST") || ((!requestMethod.equals("GET") || zzhcVar.zzfw().zzft().length() <= 2048) && zzhcVar.zzfv().zzaj(requestMethod))) {
            z = false;
        }
        if (z) {
            String requestMethod2 = zzhcVar.getRequestMethod();
            zzhcVar.zzaf("POST");
            zzgx zzgxVar = (zzgx) zzhcVar.zzga().zzb("X-HTTP-Method-Override", requestMethod2);
            if (requestMethod2.equals("GET")) {
                zzhcVar.zza(new zzho((zzgu) zzhcVar.zzfw().clone()));
                zzhcVar.zzfw().clear();
            } else if (zzhcVar.zzfx() == null) {
                zzhcVar.zza(new zzgp());
            }
        }
    }

    public zzgb(boolean z) {
    }
}
