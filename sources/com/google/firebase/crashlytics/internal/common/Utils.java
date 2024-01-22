package com.google.firebase.crashlytics.internal.common;

import android.os.Looper;
import androidx.annotation.NonNull;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
/* loaded from: classes10.dex */
public final class Utils {

    /* renamed from: a  reason: collision with root package name */
    public static final ExecutorService f11141a = ExecutorUtils.buildSingleThreadExecutorService("awaitEvenIfOnMainThread task continuation executor");

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes10.dex */
    public class a<T> implements Continuation<T, Void> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ TaskCompletionSource f11142a;

        public a(TaskCompletionSource taskCompletionSource) {
            this.f11142a = taskCompletionSource;
        }

        @Override // com.google.android.gms.tasks.Continuation
        /* renamed from: a */
        public Void then(@NonNull Task<T> task) throws Exception {
            if (task.isSuccessful()) {
                this.f11142a.trySetResult(task.getResult());
                return null;
            }
            this.f11142a.trySetException(task.getException());
            return null;
        }
    }

    /* loaded from: classes10.dex */
    public class b implements Runnable {
        public final /* synthetic */ Callable h;
        public final /* synthetic */ TaskCompletionSource i;

        /* JADX INFO: Add missing generic type declarations: [T] */
        /* loaded from: classes10.dex */
        public class a<T> implements Continuation<T, Void> {
            public a() {
            }

            @Override // com.google.android.gms.tasks.Continuation
            /* renamed from: a */
            public Void then(@NonNull Task<T> task) throws Exception {
                if (task.isSuccessful()) {
                    b.this.i.setResult(task.getResult());
                    return null;
                }
                b.this.i.setException(task.getException());
                return null;
            }
        }

        public b(Callable callable, TaskCompletionSource taskCompletionSource) {
            this.h = callable;
            this.i = taskCompletionSource;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                ((Task) this.h.call()).continueWith(new a());
            } catch (Exception e) {
                this.i.setException(e);
            }
        }
    }

    public static <T> T awaitEvenIfOnMainThread(Task<T> task) throws InterruptedException, TimeoutException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        task.continueWith(f11141a, new Continuation() { // from class: com.google.firebase.crashlytics.internal.common.r
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task2) {
                Object b2;
                b2 = Utils.b(countDownLatch, task2);
                return b2;
            }
        });
        if (Looper.getMainLooper() == Looper.myLooper()) {
            countDownLatch.await(4L, TimeUnit.SECONDS);
        } else {
            countDownLatch.await();
        }
        if (task.isSuccessful()) {
            return task.getResult();
        }
        if (!task.isCanceled()) {
            if (task.isComplete()) {
                throw new IllegalStateException(task.getException());
            }
            throw new TimeoutException();
        }
        throw new CancellationException("Task is already canceled");
    }

    public static /* synthetic */ Object b(CountDownLatch countDownLatch, Task task) throws Exception {
        countDownLatch.countDown();
        return null;
    }

    public static <T> Task<T> callTask(Executor executor, Callable<Task<T>> callable) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        executor.execute(new b(callable, taskCompletionSource));
        return taskCompletionSource.getTask();
    }

    public static <T> Task<T> race(Task<T> task, Task<T> task2) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        a aVar = new a(taskCompletionSource);
        task.continueWith(aVar);
        task2.continueWith(aVar);
        return taskCompletionSource.getTask();
    }
}
