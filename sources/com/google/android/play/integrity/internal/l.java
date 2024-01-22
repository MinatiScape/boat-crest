package com.google.android.play.integrity.internal;

import androidx.annotation.Nullable;
import com.google.android.gms.tasks.TaskCompletionSource;
/* loaded from: classes10.dex */
public abstract class l implements Runnable {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    private final TaskCompletionSource f10471a;

    public l() {
        this.f10471a = null;
    }

    public l(@Nullable TaskCompletionSource taskCompletionSource) {
        this.f10471a = taskCompletionSource;
    }

    public void a(Exception exc) {
        TaskCompletionSource taskCompletionSource = this.f10471a;
        if (taskCompletionSource != null) {
            taskCompletionSource.trySetException(exc);
        }
    }

    public abstract void b();

    @Nullable
    public final TaskCompletionSource c() {
        return this.f10471a;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            b();
        } catch (Exception e) {
            a(e);
        }
    }
}
