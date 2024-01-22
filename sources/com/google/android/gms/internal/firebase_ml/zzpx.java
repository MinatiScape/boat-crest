package com.google.android.gms.internal.firebase_ml;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.annotation.WorkerThread;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.ml.common.FirebaseMLException;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
/* loaded from: classes7.dex */
public final class zzpx implements Handler.Callback {
    public static final Object i = new Object();
    @GuardedBy("lock")
    public static zzpx j;
    public Handler h;

    public zzpx(Looper looper) {
        this.h = new zze(looper, this);
    }

    public static final /* synthetic */ void b(Callable callable, TaskCompletionSource taskCompletionSource) {
        try {
            taskCompletionSource.setResult(callable.call());
        } catch (FirebaseMLException e) {
            taskCompletionSource.setException(e);
        } catch (Exception e2) {
            taskCompletionSource.setException(new FirebaseMLException("Internal error has occurred when executing Firebase ML tasks", 13, e2));
        }
    }

    public static Executor zzoe() {
        return k4.zzbit;
    }

    public static zzpx zzof() {
        zzpx zzpxVar;
        synchronized (i) {
            if (j == null) {
                HandlerThread handlerThread = new HandlerThread("FirebaseMLHandler", 9);
                handlerThread.start();
                j = new zzpx(handlerThread.getLooper());
            }
            zzpxVar = j;
        }
        return zzpxVar;
    }

    public final Handler getHandler() {
        return this.h;
    }

    @Override // android.os.Handler.Callback
    @WorkerThread
    public final boolean handleMessage(Message message) {
        if (message.what == 1) {
            try {
                ((Callable) message.obj).call();
            } catch (Exception unused) {
                Log.e("MLTaskExecutor", "Exception when executing the delayed Callable");
            }
        }
        return true;
    }

    public final <ResultT> Task<ResultT> zza(final Callable<ResultT> callable) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.h.post(new Runnable(callable, taskCompletionSource) { // from class: com.google.android.gms.internal.firebase_ml.j4
            public final Callable h;
            public final TaskCompletionSource i;

            {
                this.h = callable;
                this.i = taskCompletionSource;
            }

            @Override // java.lang.Runnable
            public final void run() {
                zzpx.b(this.h, this.i);
            }
        });
        return taskCompletionSource.getTask();
    }

    public final <ResultT> void zzb(Callable<ResultT> callable) {
        this.h.removeMessages(1, callable);
    }

    public final <ResultT> void zza(Callable<ResultT> callable, long j2) {
        Handler handler = this.h;
        handler.sendMessageDelayed(handler.obtainMessage(1, callable), j2);
    }
}
