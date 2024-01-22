package com.google.android.gms.common.api.internal;

import android.app.Activity;
import androidx.annotation.MainThread;
import androidx.annotation.VisibleForTesting;
import java.util.ArrayList;
import java.util.List;
@VisibleForTesting(otherwise = 2)
/* loaded from: classes6.dex */
public final class a extends LifecycleCallback {

    /* renamed from: a  reason: collision with root package name */
    public List f8267a;

    public a(LifecycleFragment lifecycleFragment) {
        super(lifecycleFragment);
        this.f8267a = new ArrayList();
        this.mLifecycleFragment.addCallback("LifecycleObserverOnStop", this);
    }

    public static /* bridge */ /* synthetic */ a a(Activity activity) {
        a aVar;
        synchronized (activity) {
            LifecycleFragment fragment = LifecycleCallback.getFragment(activity);
            aVar = (a) fragment.getCallbackOrNull("LifecycleObserverOnStop", a.class);
            if (aVar == null) {
                aVar = new a(fragment);
            }
        }
        return aVar;
    }

    public final synchronized void c(Runnable runnable) {
        this.f8267a.add(runnable);
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    @MainThread
    public final void onStop() {
        List<Runnable> list;
        synchronized (this) {
            list = this.f8267a;
            this.f8267a = new ArrayList();
        }
        for (Runnable runnable : list) {
            runnable.run();
        }
    }
}
