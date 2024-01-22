package com.airbnb.lottie;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import com.airbnb.lottie.utils.Logger;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
/* loaded from: classes.dex */
public class LottieTask<T> {
    public static Executor EXECUTOR = Executors.newCachedThreadPool();

    /* renamed from: a  reason: collision with root package name */
    public final Set<LottieListener<T>> f1985a;
    public final Set<LottieListener<Throwable>> b;
    public final Handler c;
    @Nullable
    public volatile LottieResult<T> d;

    /* loaded from: classes.dex */
    public class a extends FutureTask<LottieResult<T>> {
        public a(Callable<LottieResult<T>> callable) {
            super(callable);
        }

        @Override // java.util.concurrent.FutureTask
        public void done() {
            if (isCancelled()) {
                return;
            }
            try {
                LottieTask.this.g(get());
            } catch (InterruptedException | ExecutionException e) {
                LottieTask.this.g(new LottieResult(e));
            }
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public LottieTask(Callable<LottieResult<T>> callable) {
        this(callable, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c() {
        LottieResult<T> lottieResult = this.d;
        if (lottieResult == null) {
            return;
        }
        if (lottieResult.getValue() != null) {
            f(lottieResult.getValue());
        } else {
            d(lottieResult.getException());
        }
    }

    public synchronized LottieTask<T> addFailureListener(LottieListener<Throwable> lottieListener) {
        LottieResult<T> lottieResult = this.d;
        if (lottieResult != null && lottieResult.getException() != null) {
            lottieListener.onResult(lottieResult.getException());
        }
        this.b.add(lottieListener);
        return this;
    }

    public synchronized LottieTask<T> addListener(LottieListener<T> lottieListener) {
        LottieResult<T> lottieResult = this.d;
        if (lottieResult != null && lottieResult.getValue() != null) {
            lottieListener.onResult(lottieResult.getValue());
        }
        this.f1985a.add(lottieListener);
        return this;
    }

    public final synchronized void d(Throwable th) {
        ArrayList<LottieListener> arrayList = new ArrayList(this.b);
        if (arrayList.isEmpty()) {
            Logger.warning("Lottie encountered an error but no failure listener was added:", th);
            return;
        }
        for (LottieListener lottieListener : arrayList) {
            lottieListener.onResult(th);
        }
    }

    public final void e() {
        this.c.post(new Runnable() { // from class: com.airbnb.lottie.g0
            @Override // java.lang.Runnable
            public final void run() {
                LottieTask.this.c();
            }
        });
    }

    public final synchronized void f(T t) {
        for (LottieListener lottieListener : new ArrayList(this.f1985a)) {
            lottieListener.onResult(t);
        }
    }

    public final void g(@Nullable LottieResult<T> lottieResult) {
        if (this.d == null) {
            this.d = lottieResult;
            e();
            return;
        }
        throw new IllegalStateException("A task may only be set once.");
    }

    public synchronized LottieTask<T> removeFailureListener(LottieListener<Throwable> lottieListener) {
        this.b.remove(lottieListener);
        return this;
    }

    public synchronized LottieTask<T> removeListener(LottieListener<T> lottieListener) {
        this.f1985a.remove(lottieListener);
        return this;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public LottieTask(Callable<LottieResult<T>> callable, boolean z) {
        this.f1985a = new LinkedHashSet(1);
        this.b = new LinkedHashSet(1);
        this.c = new Handler(Looper.getMainLooper());
        this.d = null;
        if (z) {
            try {
                g(callable.call());
                return;
            } catch (Throwable th) {
                g(new LottieResult<>(th));
                return;
            }
        }
        EXECUTOR.execute(new a(callable));
    }
}
