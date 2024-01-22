package androidx.work.impl;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.core.os.HandlerCompat;
import androidx.work.RunnableScheduler;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class DefaultRunnableScheduler implements RunnableScheduler {

    /* renamed from: a  reason: collision with root package name */
    public final Handler f1791a;

    public DefaultRunnableScheduler() {
        this.f1791a = HandlerCompat.createAsync(Looper.getMainLooper());
    }

    @Override // androidx.work.RunnableScheduler
    public void cancel(@NonNull Runnable runnable) {
        this.f1791a.removeCallbacks(runnable);
    }

    @NonNull
    public Handler getHandler() {
        return this.f1791a;
    }

    @Override // androidx.work.RunnableScheduler
    public void scheduleWithDelay(long j, @NonNull Runnable runnable) {
        this.f1791a.postDelayed(runnable, j);
    }

    @VisibleForTesting
    public DefaultRunnableScheduler(@NonNull Handler handler) {
        this.f1791a = handler;
    }
}
