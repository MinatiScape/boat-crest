package com.android.volley.toolbox;

import android.os.SystemClock;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
/* loaded from: classes.dex */
public class RequestFuture<T> implements Future<T>, Response.Listener<T>, Response.ErrorListener {
    public Request<?> h;
    public boolean i = false;
    public T j;
    public VolleyError k;

    public static <E> RequestFuture<E> newFuture() {
        return new RequestFuture<>();
    }

    public final synchronized T a(Long l) throws InterruptedException, ExecutionException, TimeoutException {
        if (this.k == null) {
            if (this.i) {
                return this.j;
            }
            if (l == null) {
                while (!isDone()) {
                    wait(0L);
                }
            } else if (l.longValue() > 0) {
                long uptimeMillis = SystemClock.uptimeMillis();
                long longValue = l.longValue() + uptimeMillis;
                while (!isDone() && uptimeMillis < longValue) {
                    wait(longValue - uptimeMillis);
                    uptimeMillis = SystemClock.uptimeMillis();
                }
            }
            if (this.k == null) {
                if (this.i) {
                    return this.j;
                }
                throw new TimeoutException();
            }
            throw new ExecutionException(this.k);
        }
        throw new ExecutionException(this.k);
    }

    @Override // java.util.concurrent.Future
    public synchronized boolean cancel(boolean z) {
        if (this.h == null) {
            return false;
        }
        if (isDone()) {
            return false;
        }
        this.h.cancel();
        return true;
    }

    @Override // java.util.concurrent.Future
    public T get() throws InterruptedException, ExecutionException {
        try {
            return a(null);
        } catch (TimeoutException e) {
            throw new AssertionError(e);
        }
    }

    @Override // java.util.concurrent.Future
    public boolean isCancelled() {
        Request<?> request = this.h;
        if (request == null) {
            return false;
        }
        return request.isCanceled();
    }

    @Override // java.util.concurrent.Future
    public synchronized boolean isDone() {
        boolean z;
        if (!this.i && this.k == null) {
            z = isCancelled();
        }
        return z;
    }

    @Override // com.android.volley.Response.ErrorListener
    public synchronized void onErrorResponse(VolleyError volleyError) {
        this.k = volleyError;
        notifyAll();
    }

    @Override // com.android.volley.Response.Listener
    public synchronized void onResponse(T t) {
        this.i = true;
        this.j = t;
        notifyAll();
    }

    public void setRequest(Request<?> request) {
        this.h = request;
    }

    @Override // java.util.concurrent.Future
    public T get(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return a(Long.valueOf(TimeUnit.MILLISECONDS.convert(j, timeUnit)));
    }
}
