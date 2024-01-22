package com.google.android.libraries.places.internal;

import android.os.Handler;
import android.os.HandlerThread;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes10.dex */
public final class zzcr {
    private final zzco zza;
    private final Map<TaskCompletionSource<?>, HandlerThread> zzb = new HashMap();

    public zzcr(zzco zzcoVar) {
        this.zza = zzcoVar;
    }

    public final <T> boolean zza(final TaskCompletionSource<T> taskCompletionSource, long j, final String str) {
        if (this.zzb.containsKey(taskCompletionSource)) {
            return false;
        }
        HandlerThread handlerThread = new HandlerThread("timeoutHandlerThread");
        handlerThread.start();
        this.zzb.put(taskCompletionSource, handlerThread);
        return new Handler(handlerThread.getLooper()).postDelayed(new Runnable(taskCompletionSource, str) { // from class: com.google.android.libraries.places.internal.zzcq
            private final TaskCompletionSource zza;
            private final String zzb;

            {
                this.zza = taskCompletionSource;
                this.zzb = str;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.zza.trySetException(new ApiException(new Status(15, this.zzb)));
            }
        }, j);
    }

    public final <T> Task<T> zza(Task<T> task, @Nullable CancellationToken cancellationToken, long j, String str) {
        final TaskCompletionSource<T> taskCompletionSource;
        if (cancellationToken == null) {
            taskCompletionSource = new TaskCompletionSource<>();
        } else {
            taskCompletionSource = new TaskCompletionSource<>(cancellationToken);
        }
        zza(taskCompletionSource, j, str);
        task.continueWithTask(new Continuation(this, taskCompletionSource) { // from class: com.google.android.libraries.places.internal.zzct
            private final zzcr zza;
            private final TaskCompletionSource zzb;

            {
                this.zza = this;
                this.zzb = taskCompletionSource;
            }

            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task2) {
                TaskCompletionSource taskCompletionSource2 = this.zzb;
                if (task2.isSuccessful()) {
                    taskCompletionSource2.setResult(task2.getResult());
                } else if (!task2.isCanceled() && task2.getException() != null) {
                    taskCompletionSource2.setException(task2.getException());
                }
                return taskCompletionSource2.getTask();
            }
        });
        taskCompletionSource.getTask().addOnCompleteListener(new OnCompleteListener(this, taskCompletionSource) { // from class: com.google.android.libraries.places.internal.zzcs
            private final zzcr zza;
            private final TaskCompletionSource zzb;

            {
                this.zza = this;
                this.zzb = taskCompletionSource;
            }

            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task2) {
                this.zza.zza(this.zzb, task2);
            }
        });
        return taskCompletionSource.getTask();
    }

    public final boolean zza(TaskCompletionSource<?> taskCompletionSource) {
        HandlerThread remove = this.zzb.remove(taskCompletionSource);
        if (remove == null) {
            return false;
        }
        return remove.quit();
    }

    public final /* synthetic */ void zza(TaskCompletionSource taskCompletionSource, Task task) {
        zza(taskCompletionSource);
    }
}
