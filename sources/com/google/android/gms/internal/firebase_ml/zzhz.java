package com.google.android.gms.internal.firebase_ml;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes7.dex */
public final class zzhz implements zzjm {

    /* renamed from: a  reason: collision with root package name */
    public final zzhx f8774a;
    public final Set<String> b;

    public zzhz(zzic zzicVar) {
        this.f8774a = zzicVar.f8776a;
        this.b = new HashSet(zzicVar.b);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzjm
    public final <T> T zza(InputStream inputStream, Charset charset, Class<T> cls) throws IOException {
        zzib zza = this.f8774a.zza(inputStream, charset);
        if (!this.b.isEmpty()) {
            try {
                boolean z = (zza.zza(this.b) == null || zza.zzhd() == zzih.END_OBJECT) ? false : true;
                Object[] objArr = {this.b};
                if (!z) {
                    throw new IllegalArgumentException(zzms.zzb("wrapper key(s) not found: %s", objArr));
                }
            } catch (Throwable th) {
                zza.close();
                throw th;
            }
        }
        return (T) zza.zza(cls, true, null);
    }

    public final zzhx zzfp() {
        return this.f8774a;
    }

    public final Set<String> zzgu() {
        return Collections.unmodifiableSet(this.b);
    }
}
