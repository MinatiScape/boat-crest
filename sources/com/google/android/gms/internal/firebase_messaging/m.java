package com.google.android.gms.internal.firebase_messaging;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
/* loaded from: classes7.dex */
public final class m extends WeakReference<Throwable> {

    /* renamed from: a  reason: collision with root package name */
    public final int f8648a;

    public m(Throwable th, ReferenceQueue<Throwable> referenceQueue) {
        super(th, referenceQueue);
        this.f8648a = System.identityHashCode(th);
    }

    public final boolean equals(Object obj) {
        if (obj != null && obj.getClass() == m.class) {
            if (this == obj) {
                return true;
            }
            m mVar = (m) obj;
            if (this.f8648a == mVar.f8648a && get() == mVar.get()) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.f8648a;
    }
}
