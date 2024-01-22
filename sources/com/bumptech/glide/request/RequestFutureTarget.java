package com.bumptech.glide.request;

import android.graphics.drawable.Drawable;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.util.Util;
import com.coveiot.coveaccess.healthbuddies.RequestStatus;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
/* loaded from: classes2.dex */
public class RequestFutureTarget<R> implements FutureTarget<R>, RequestListener<R> {
    public static final a r = new a();
    public final int h;
    public final int i;
    public final boolean j;
    public final a k;
    @Nullable
    @GuardedBy("this")
    public R l;
    @Nullable
    @GuardedBy("this")
    public Request m;
    @GuardedBy("this")
    public boolean n;
    @GuardedBy("this")
    public boolean o;
    @GuardedBy("this")
    public boolean p;
    @Nullable
    @GuardedBy("this")
    public GlideException q;

    @VisibleForTesting
    /* loaded from: classes2.dex */
    public static class a {
        public void a(Object obj) {
            obj.notifyAll();
        }

        public void b(Object obj, long j) throws InterruptedException {
            obj.wait(j);
        }
    }

    public RequestFutureTarget(int i, int i2) {
        this(i, i2, true, r);
    }

    public final synchronized R a(Long l) throws ExecutionException, InterruptedException, TimeoutException {
        if (this.j && !isDone()) {
            Util.assertBackgroundThread();
        }
        if (!this.n) {
            if (!this.p) {
                if (this.o) {
                    return this.l;
                }
                if (l == null) {
                    this.k.b(this, 0L);
                } else if (l.longValue() > 0) {
                    long currentTimeMillis = System.currentTimeMillis();
                    long longValue = l.longValue() + currentTimeMillis;
                    while (!isDone() && currentTimeMillis < longValue) {
                        this.k.b(this, longValue - currentTimeMillis);
                        currentTimeMillis = System.currentTimeMillis();
                    }
                }
                if (!Thread.interrupted()) {
                    if (!this.p) {
                        if (!this.n) {
                            if (this.o) {
                                return this.l;
                            }
                            throw new TimeoutException();
                        }
                        throw new CancellationException();
                    }
                    throw new ExecutionException(this.q);
                }
                throw new InterruptedException();
            }
            throw new ExecutionException(this.q);
        }
        throw new CancellationException();
    }

    @Override // java.util.concurrent.Future
    public boolean cancel(boolean z) {
        synchronized (this) {
            if (isDone()) {
                return false;
            }
            this.n = true;
            this.k.a(this);
            Request request = null;
            if (z) {
                Request request2 = this.m;
                this.m = null;
                request = request2;
            }
            if (request != null) {
                request.clear();
            }
            return true;
        }
    }

    @Override // java.util.concurrent.Future
    public R get() throws InterruptedException, ExecutionException {
        try {
            return a(null);
        } catch (TimeoutException e) {
            throw new AssertionError(e);
        }
    }

    @Override // com.bumptech.glide.request.target.Target
    @Nullable
    public synchronized Request getRequest() {
        return this.m;
    }

    @Override // com.bumptech.glide.request.target.Target
    public void getSize(@NonNull SizeReadyCallback sizeReadyCallback) {
        sizeReadyCallback.onSizeReady(this.h, this.i);
    }

    @Override // java.util.concurrent.Future
    public synchronized boolean isCancelled() {
        return this.n;
    }

    @Override // java.util.concurrent.Future
    public synchronized boolean isDone() {
        boolean z;
        if (!this.n && !this.o) {
            z = this.p;
        }
        return z;
    }

    @Override // com.bumptech.glide.manager.LifecycleListener
    public void onDestroy() {
    }

    @Override // com.bumptech.glide.request.target.Target
    public void onLoadCleared(@Nullable Drawable drawable) {
    }

    @Override // com.bumptech.glide.request.target.Target
    public synchronized void onLoadFailed(@Nullable Drawable drawable) {
    }

    @Override // com.bumptech.glide.request.target.Target
    public void onLoadStarted(@Nullable Drawable drawable) {
    }

    @Override // com.bumptech.glide.request.target.Target
    public synchronized void onResourceReady(@NonNull R r2, @Nullable Transition<? super R> transition) {
    }

    @Override // com.bumptech.glide.manager.LifecycleListener
    public void onStart() {
    }

    @Override // com.bumptech.glide.manager.LifecycleListener
    public void onStop() {
    }

    @Override // com.bumptech.glide.request.target.Target
    public void removeCallback(@NonNull SizeReadyCallback sizeReadyCallback) {
    }

    @Override // com.bumptech.glide.request.target.Target
    public synchronized void setRequest(@Nullable Request request) {
        this.m = request;
    }

    public String toString() {
        Request request;
        String str;
        String str2 = super.toString() + "[status=";
        synchronized (this) {
            request = null;
            if (this.n) {
                str = RequestStatus.CANCELLED;
            } else if (this.p) {
                str = "FAILURE";
            } else if (this.o) {
                str = "SUCCESS";
            } else {
                str = "PENDING";
                request = this.m;
            }
        }
        if (request != null) {
            return str2 + str + ", request=[" + request + "]]";
        }
        return str2 + str + "]";
    }

    public RequestFutureTarget(int i, int i2, boolean z, a aVar) {
        this.h = i;
        this.i = i2;
        this.j = z;
        this.k = aVar;
    }

    @Override // com.bumptech.glide.request.RequestListener
    public synchronized boolean onLoadFailed(@Nullable GlideException glideException, Object obj, Target<R> target, boolean z) {
        this.p = true;
        this.q = glideException;
        this.k.a(this);
        return false;
    }

    @Override // com.bumptech.glide.request.RequestListener
    public synchronized boolean onResourceReady(R r2, Object obj, Target<R> target, DataSource dataSource, boolean z) {
        this.o = true;
        this.l = r2;
        this.k.a(this);
        return false;
    }

    @Override // java.util.concurrent.Future
    public R get(long j, @NonNull TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return a(Long.valueOf(timeUnit.toMillis(j)));
    }
}
