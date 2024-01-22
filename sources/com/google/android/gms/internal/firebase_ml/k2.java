package com.google.android.gms.internal.firebase_ml;
/* loaded from: classes7.dex */
public final class k2 extends h2 {
    @Override // com.google.android.gms.internal.firebase_ml.h2
    public final void a(Throwable th, Throwable th2) {
        th.addSuppressed(th2);
    }

    @Override // com.google.android.gms.internal.firebase_ml.h2
    public final void b(Throwable th) {
        th.printStackTrace();
    }
}
