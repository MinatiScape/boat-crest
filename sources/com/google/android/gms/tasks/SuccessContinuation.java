package com.google.android.gms.tasks;

import androidx.annotation.NonNull;
/* loaded from: classes10.dex */
public interface SuccessContinuation<TResult, TContinuationResult> {
    @NonNull
    Task<TContinuationResult> then(TResult tresult) throws Exception;
}
