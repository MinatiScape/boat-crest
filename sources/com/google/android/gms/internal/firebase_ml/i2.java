package com.google.android.gms.internal.firebase_ml;

import java.util.List;
import java.util.Objects;
/* loaded from: classes7.dex */
public final class i2 extends h2 {

    /* renamed from: a  reason: collision with root package name */
    public final g2 f8688a = new g2();

    @Override // com.google.android.gms.internal.firebase_ml.h2
    public final void a(Throwable th, Throwable th2) {
        if (th2 != th) {
            Objects.requireNonNull(th2, "The suppressed exception cannot be null.");
            this.f8688a.a(th, true).add(th2);
            return;
        }
        throw new IllegalArgumentException("Self suppression is not allowed.", th2);
    }

    @Override // com.google.android.gms.internal.firebase_ml.h2
    public final void b(Throwable th) {
        th.printStackTrace();
        List<Throwable> a2 = this.f8688a.a(th, false);
        if (a2 == null) {
            return;
        }
        synchronized (a2) {
            for (Throwable th2 : a2) {
                System.err.print("Suppressed: ");
                th2.printStackTrace();
            }
        }
    }
}
