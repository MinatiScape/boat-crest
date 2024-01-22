package com.google.android.gms.internal.gcm;

import java.util.Objects;
/* loaded from: classes8.dex */
public final class g extends d {

    /* renamed from: a  reason: collision with root package name */
    public final e f8872a = new e();

    @Override // com.google.android.gms.internal.gcm.d
    public final void a(Throwable th, Throwable th2) {
        if (th2 != th) {
            Objects.requireNonNull(th2, "The suppressed exception cannot be null.");
            this.f8872a.a(th, true).add(th2);
            return;
        }
        throw new IllegalArgumentException("Self suppression is not allowed.", th2);
    }
}
