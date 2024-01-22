package com.google.android.gms.internal.firebase_ml;

import java.io.IOException;
import java.nio.charset.Charset;
/* loaded from: classes7.dex */
public abstract class zzgq implements zzgt {

    /* renamed from: a  reason: collision with root package name */
    public zzgz f8763a;
    public long b;

    public zzgq(String str) {
        this(str == null ? null : new zzgz(str));
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzgt
    public final long getLength() throws IOException {
        if (this.b == -1) {
            this.b = zzjh.zzb(this);
        }
        return this.b;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzgt
    public final String getType() {
        zzgz zzgzVar = this.f8763a;
        if (zzgzVar == null) {
            return null;
        }
        return zzgzVar.zzft();
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzgt
    public final boolean zzfr() {
        return true;
    }

    public final Charset zzfs() {
        zzgz zzgzVar = this.f8763a;
        if (zzgzVar != null && zzgzVar.zzfu() != null) {
            return this.f8763a.zzfu();
        }
        return zziw.UTF_8;
    }

    public zzgq(zzgz zzgzVar) {
        this.b = -1L;
        this.f8763a = zzgzVar;
    }
}
