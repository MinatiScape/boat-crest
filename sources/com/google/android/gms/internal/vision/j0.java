package com.google.android.gms.internal.vision;
/* loaded from: classes10.dex */
public final class j0 extends g0 {
    @Override // com.google.android.gms.internal.vision.g0
    public final void a(Throwable th) {
        th.printStackTrace();
    }

    @Override // com.google.android.gms.internal.vision.g0
    public final void b(Throwable th, Throwable th2) {
        th.addSuppressed(th2);
    }
}
