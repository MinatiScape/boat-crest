package com.google.android.gms.internal.vision;

import java.util.List;
import java.util.Objects;
/* loaded from: classes10.dex */
public final class h0 extends g0 {

    /* renamed from: a  reason: collision with root package name */
    public final f0 f9979a = new f0();

    @Override // com.google.android.gms.internal.vision.g0
    public final void a(Throwable th) {
        th.printStackTrace();
        List<Throwable> a2 = this.f9979a.a(th, false);
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

    @Override // com.google.android.gms.internal.vision.g0
    public final void b(Throwable th, Throwable th2) {
        if (th2 != th) {
            Objects.requireNonNull(th2, "The suppressed exception cannot be null.");
            this.f9979a.a(th, true).add(th2);
            return;
        }
        throw new IllegalArgumentException("Self suppression is not allowed.", th2);
    }
}
