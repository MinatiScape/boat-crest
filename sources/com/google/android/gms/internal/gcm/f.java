package com.google.android.gms.internal.gcm;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Objects;
/* loaded from: classes8.dex */
public final class f extends WeakReference<Throwable> {

    /* renamed from: a  reason: collision with root package name */
    public final int f8871a;

    public f(Throwable th, ReferenceQueue<Throwable> referenceQueue) {
        super(th, referenceQueue);
        Objects.requireNonNull(th, "The referent cannot be null");
        this.f8871a = System.identityHashCode(th);
    }

    public final boolean equals(Object obj) {
        if (obj != null && obj.getClass() == f.class) {
            if (this == obj) {
                return true;
            }
            f fVar = (f) obj;
            if (this.f8871a == fVar.f8871a && get() == fVar.get()) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.f8871a;
    }
}
