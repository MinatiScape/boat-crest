package com.clevertap.android.sdk.task;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.RestrictTo;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes2.dex */
public class MainLooperHandler extends Handler {

    /* renamed from: a  reason: collision with root package name */
    public Runnable f2684a;

    public MainLooperHandler() {
        super(Looper.getMainLooper());
        this.f2684a = null;
    }

    public Runnable getPendingRunnable() {
        return this.f2684a;
    }

    public void setPendingRunnable(Runnable runnable) {
        this.f2684a = runnable;
    }
}
