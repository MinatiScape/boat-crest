package com.clevertap.android.sdk.task;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.Logger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
/* loaded from: classes2.dex */
public class Task<TResult> {

    /* renamed from: a  reason: collision with root package name */
    public final String f2685a;
    public final CleverTapInstanceConfig config;
    public final Executor defaultCallbackExecutor;
    public final Executor executor;
    public TResult result;
    public final List<b<Exception>> failureExecutables = new ArrayList();
    public final List<e<TResult>> successExecutables = new ArrayList();
    public STATE taskState = STATE.READY_TO_RUN;

    /* loaded from: classes2.dex */
    public enum STATE {
        FAILED,
        SUCCESS,
        READY_TO_RUN,
        RUNNING
    }

    /* loaded from: classes2.dex */
    public class a implements Runnable {
        public final /* synthetic */ String h;
        public final /* synthetic */ Callable i;

        public a(String str, Callable callable) {
            this.h = str;
            this.i = callable;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Runnable
        public void run() {
            try {
                Logger logger = Task.this.config.getLogger();
                logger.verbose(Task.this.f2685a + " Task: " + this.h + " starting on..." + Thread.currentThread().getName());
                Object call = this.i.call();
                Logger logger2 = Task.this.config.getLogger();
                logger2.verbose(Task.this.f2685a + " Task: " + this.h + " executed successfully on..." + Thread.currentThread().getName());
                Task.this.d(call);
            } catch (Exception e) {
                Task.this.c(e);
                Logger logger3 = Task.this.config.getLogger();
                logger3.verbose(Task.this.f2685a + " Task: " + this.h + " failed to execute on..." + Thread.currentThread().getName(), e);
                e.printStackTrace();
            }
        }
    }

    public Task(CleverTapInstanceConfig cleverTapInstanceConfig, Executor executor, Executor executor2, String str) {
        this.executor = executor;
        this.defaultCallbackExecutor = executor2;
        this.config = cleverTapInstanceConfig;
        this.f2685a = str;
    }

    @NonNull
    public synchronized Task<TResult> addOnFailureListener(@NonNull Executor executor, OnFailureListener<Exception> onFailureListener) {
        if (onFailureListener != null) {
            this.failureExecutables.add(new b<>(executor, onFailureListener));
        }
        return this;
    }

    @NonNull
    public Task<TResult> addOnSuccessListener(@NonNull Executor executor, OnSuccessListener<TResult> onSuccessListener) {
        if (onSuccessListener != null) {
            this.successExecutables.add(new e<>(executor, onSuccessListener, this.config));
        }
        return this;
    }

    public final Runnable b(String str, Callable<TResult> callable) {
        return new a(str, callable);
    }

    public void c(Exception exc) {
        f(STATE.FAILED);
        for (b<Exception> bVar : this.failureExecutables) {
            bVar.a(exc);
        }
    }

    public void d(TResult tresult) {
        f(STATE.SUCCESS);
        e(tresult);
        for (e<TResult> eVar : this.successExecutables) {
            eVar.a(this.result);
        }
    }

    public void e(TResult tresult) {
        this.result = tresult;
    }

    public void execute(String str, Callable<TResult> callable) {
        this.executor.execute(b(str, callable));
    }

    public void f(STATE state) {
        this.taskState = state;
    }

    public boolean isSuccess() {
        return this.taskState == STATE.SUCCESS;
    }

    @NonNull
    public Task<TResult> removeOnFailureListener(@NonNull OnFailureListener<Exception> onFailureListener) {
        Iterator<b<Exception>> it = this.failureExecutables.iterator();
        while (it.hasNext()) {
            if (it.next().c() == onFailureListener) {
                it.remove();
            }
        }
        return this;
    }

    @NonNull
    public Task<TResult> removeOnSuccessListener(@NonNull OnSuccessListener<TResult> onSuccessListener) {
        Iterator<e<TResult>> it = this.successExecutables.iterator();
        while (it.hasNext()) {
            if (it.next().c() == onSuccessListener) {
                it.remove();
            }
        }
        return this;
    }

    public Future<?> submit(String str, Callable<TResult> callable) {
        Executor executor = this.executor;
        if (executor instanceof ExecutorService) {
            return ((ExecutorService) executor).submit(b(str, callable));
        }
        throw new UnsupportedOperationException("Can't use this method without ExecutorService, Use Execute alternatively ");
    }

    @Nullable
    public TResult submitAndGetResult(String str, Callable<TResult> callable, long j) {
        Future future;
        Executor executor = this.executor;
        if (executor instanceof ExecutorService) {
            try {
                future = ((ExecutorService) executor).submit(callable);
            } catch (Exception e) {
                e = e;
                future = null;
            }
            try {
                return (TResult) future.get(j, TimeUnit.MILLISECONDS);
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                if (future != null && !future.isCancelled()) {
                    future.cancel(true);
                }
                Logger.v("submitAndGetResult :: " + str + " task timed out");
                return null;
            }
        }
        throw new UnsupportedOperationException("Can't use this method without ExecutorService, Use Execute alternatively ");
    }

    @NonNull
    public Task<TResult> addOnSuccessListener(@NonNull OnSuccessListener<TResult> onSuccessListener) {
        return addOnSuccessListener(this.defaultCallbackExecutor, onSuccessListener);
    }

    @NonNull
    public Task<TResult> addOnFailureListener(@NonNull OnFailureListener<Exception> onFailureListener) {
        return addOnFailureListener(this.defaultCallbackExecutor, onFailureListener);
    }
}
