package com.google.android.gms.common.api.internal;

import androidx.annotation.VisibleForTesting;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.lang.ref.WeakReference;
/* loaded from: classes6.dex */
public final class zab extends ActivityLifecycleObserver {

    /* renamed from: a  reason: collision with root package name */
    public final WeakReference f8300a;

    @VisibleForTesting(otherwise = 2)
    public zab(a aVar) {
        this.f8300a = new WeakReference(aVar);
    }

    @Override // com.google.android.gms.common.api.internal.ActivityLifecycleObserver
    @CanIgnoreReturnValue
    public final ActivityLifecycleObserver onStopCallOnce(Runnable runnable) {
        a aVar = (a) this.f8300a.get();
        if (aVar != null) {
            aVar.c(runnable);
            return this;
        }
        throw new IllegalStateException("The target activity has already been GC'd");
    }
}
