package com.google.android.gms.internal.firebase_ml;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Objects;
/* loaded from: classes7.dex */
public final class j2 extends WeakReference<Throwable> {

    /* renamed from: a  reason: collision with root package name */
    public final int f8693a;

    public j2(Throwable th, ReferenceQueue<Throwable> referenceQueue) {
        super(th, referenceQueue);
        Objects.requireNonNull(th, "The referent cannot be null");
        this.f8693a = System.identityHashCode(th);
    }

    public final boolean equals(Object obj) {
        if (obj != null && obj.getClass() == j2.class) {
            if (this == obj) {
                return true;
            }
            j2 j2Var = (j2) obj;
            if (this.f8693a == j2Var.f8693a && get() == j2Var.get()) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.f8693a;
    }
}
