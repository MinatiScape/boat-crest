package com.google.android.gms.internal.vision;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Objects;
/* loaded from: classes10.dex */
public final class i0 extends WeakReference<Throwable> {

    /* renamed from: a  reason: collision with root package name */
    public final int f9981a;

    public i0(Throwable th, ReferenceQueue<Throwable> referenceQueue) {
        super(th, referenceQueue);
        Objects.requireNonNull(th, "The referent cannot be null");
        this.f9981a = System.identityHashCode(th);
    }

    public final boolean equals(Object obj) {
        if (obj != null && obj.getClass() == i0.class) {
            if (this == obj) {
                return true;
            }
            i0 i0Var = (i0) obj;
            if (this.f9981a == i0Var.f9981a && get() == i0Var.get()) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.f9981a;
    }
}
