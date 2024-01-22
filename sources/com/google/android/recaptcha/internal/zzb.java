package com.google.android.recaptcha.internal;

import com.google.android.gms.tasks.CancellationTokenSource;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import kotlinx.coroutines.Deferred;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes10.dex */
public final class zzb {
    @NotNull
    public static final Task zza(@NotNull Deferred deferred) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource(new CancellationTokenSource().getToken());
        deferred.invokeOnCompletion(new zza(taskCompletionSource, deferred));
        return taskCompletionSource.getTask();
    }
}
