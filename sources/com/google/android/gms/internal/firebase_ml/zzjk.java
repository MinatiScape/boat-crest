package com.google.android.gms.internal.firebase_ml;

import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
/* loaded from: classes7.dex */
public final class zzjk implements zzjq {

    /* renamed from: a  reason: collision with root package name */
    public final zzjq f8782a;
    public final int b;
    public final Level c;
    public final Logger d;

    public zzjk(zzjq zzjqVar, Logger logger, Level level, int i) {
        this.f8782a = zzjqVar;
        this.d = logger;
        this.c = level;
        this.b = i;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzjq
    public final void writeTo(OutputStream outputStream) throws IOException {
        zzjl zzjlVar = new zzjl(outputStream, this.d, this.c, this.b);
        try {
            this.f8782a.writeTo(zzjlVar);
            zzjlVar.zzif().close();
            outputStream.flush();
        } catch (Throwable th) {
            zzjlVar.zzif().close();
            throw th;
        }
    }
}
