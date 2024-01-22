package com.google.android.gms.internal.firebase_ml;

import java.io.IOException;
import java.io.OutputStream;
/* loaded from: classes7.dex */
public final class zzhw extends zzgq {
    public final Object c;
    public final zzhx d;
    public String e;

    public zzhw(zzhx zzhxVar, Object obj) {
        super("application/json; charset=UTF-8");
        this.d = (zzhx) zzml.checkNotNull(zzhxVar);
        this.c = zzml.checkNotNull(obj);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzjq
    public final void writeTo(OutputStream outputStream) throws IOException {
        zzia zza = this.d.zza(outputStream, zzfs());
        if (this.e != null) {
            zza.zzgx();
            zza.zzan(this.e);
        }
        zza.zzd(this.c);
        if (this.e != null) {
            zza.zzgy();
        }
        zza.flush();
    }

    public final zzhw zzal(String str) {
        this.e = str;
        return this;
    }
}
