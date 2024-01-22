package com.google.android.gms.internal.firebase_ml;

import java.io.IOException;
/* loaded from: classes7.dex */
public final class y4 extends zzkj {
    public final /* synthetic */ zzrt b;

    public y4(zzrt zzrtVar) {
        this.b = zzrtVar;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzkj
    public final void zza(zzkg<?> zzkgVar) throws IOException {
        String str;
        super.zza(zzkgVar);
        zzgx zzfl = zzkgVar.zzfl();
        str = this.b.f8800a;
        zzfl.put("X-Goog-Spatula", str);
    }
}
