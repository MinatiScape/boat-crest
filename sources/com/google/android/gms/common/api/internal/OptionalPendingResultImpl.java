package com.google.android.gms.common.api.internal;

import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.api.TransformedResult;
import java.util.concurrent.TimeUnit;
@KeepForSdk
/* loaded from: classes6.dex */
public final class OptionalPendingResultImpl<R extends Result> extends OptionalPendingResult<R> {

    /* renamed from: a  reason: collision with root package name */
    public final BasePendingResult f8260a;

    public OptionalPendingResultImpl(@NonNull PendingResult pendingResult) {
        this.f8260a = (BasePendingResult) pendingResult;
    }

    @Override // com.google.android.gms.common.api.PendingResult
    public final void addStatusListener(@NonNull PendingResult.StatusListener statusListener) {
        this.f8260a.addStatusListener(statusListener);
    }

    @Override // com.google.android.gms.common.api.PendingResult
    @NonNull
    public final R await() {
        return (R) this.f8260a.await();
    }

    @Override // com.google.android.gms.common.api.PendingResult
    public final void cancel() {
        this.f8260a.cancel();
    }

    @Override // com.google.android.gms.common.api.OptionalPendingResult
    @NonNull
    public final R get() {
        if (this.f8260a.isReady()) {
            return (R) this.f8260a.await(0L, TimeUnit.MILLISECONDS);
        }
        throw new IllegalStateException("Result is not available. Check that isDone() returns true before calling get().");
    }

    @Override // com.google.android.gms.common.api.PendingResult
    public final boolean isCanceled() {
        return this.f8260a.isCanceled();
    }

    @Override // com.google.android.gms.common.api.OptionalPendingResult
    public final boolean isDone() {
        return this.f8260a.isReady();
    }

    @Override // com.google.android.gms.common.api.PendingResult
    public final void setResultCallback(@NonNull ResultCallback<? super R> resultCallback) {
        this.f8260a.setResultCallback(resultCallback);
    }

    @Override // com.google.android.gms.common.api.PendingResult
    @NonNull
    public final <S extends Result> TransformedResult<S> then(@NonNull ResultTransform<? super R, ? extends S> resultTransform) {
        return this.f8260a.then(resultTransform);
    }

    @Override // com.google.android.gms.common.api.PendingResult
    @NonNull
    public final R await(long j, @NonNull TimeUnit timeUnit) {
        return (R) this.f8260a.await(j, timeUnit);
    }

    @Override // com.google.android.gms.common.api.PendingResult
    public final void setResultCallback(@NonNull ResultCallback<? super R> resultCallback, long j, @NonNull TimeUnit timeUnit) {
        this.f8260a.setResultCallback(resultCallback, j, timeUnit);
    }
}
