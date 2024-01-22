package com.google.android.gms.internal.firebase_ml;

import java.io.IOException;
/* loaded from: classes7.dex */
public final class zzkc extends zzgm {
    static {
        boolean z = zzgc.zzaaj.intValue() == 1 && zzgc.zzaak.intValue() >= 15;
        Object[] objArr = {zzgc.VERSION};
        if (!z) {
            throw new IllegalStateException(zzms.zzb("You are currently running with version %s of google-api-client. You need at least version 1.15 of google-api-client to run version 1.25.0-SNAPSHOT of the Cloud Vision API library.", objArr));
        }
    }

    public zzkc(zzkf zzkfVar) {
        super(zzkfVar);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzge
    public final void zza(zzgg<?> zzggVar) throws IOException {
        super.zza(zzggVar);
    }
}
