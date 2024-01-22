package com.google.android.datatransport.runtime.retries;

import androidx.annotation.Nullable;
/* loaded from: classes6.dex */
public interface RetryStrategy<TInput, TResult> {
    @Nullable
    TInput shouldRetry(TInput tinput, TResult tresult);
}
