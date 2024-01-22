package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.FluentFuture;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.RunnableFuture;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible
/* loaded from: classes10.dex */
public class w<V> extends FluentFuture.a<V> implements RunnableFuture<V> {
    public volatile o<?> o;

    /* loaded from: classes10.dex */
    public final class a extends o<ListenableFuture<V>> {
        private final AsyncCallable<V> callable;

        public a(AsyncCallable<V> asyncCallable) {
            this.callable = (AsyncCallable) Preconditions.checkNotNull(asyncCallable);
        }

        @Override // com.google.common.util.concurrent.o
        public /* bridge */ /* synthetic */ void afterRanInterruptibly(Object obj, Throwable th) {
            afterRanInterruptibly((ListenableFuture) ((ListenableFuture) obj), th);
        }

        @Override // com.google.common.util.concurrent.o
        public final boolean isDone() {
            return w.this.isDone();
        }

        @Override // com.google.common.util.concurrent.o
        public String toPendingString() {
            return this.callable.toString();
        }

        public void afterRanInterruptibly(ListenableFuture<V> listenableFuture, Throwable th) {
            if (th == null) {
                w.this.setFuture(listenableFuture);
            } else {
                w.this.setException(th);
            }
        }

        @Override // com.google.common.util.concurrent.o
        public ListenableFuture<V> runInterruptibly() throws Exception {
            return (ListenableFuture) Preconditions.checkNotNull(this.callable.call(), "AsyncCallable.call returned null instead of a Future. Did you mean to return immediateFuture(null)? %s", this.callable);
        }
    }

    /* loaded from: classes10.dex */
    public final class b extends o<V> {
        private final Callable<V> callable;

        public b(Callable<V> callable) {
            this.callable = (Callable) Preconditions.checkNotNull(callable);
        }

        @Override // com.google.common.util.concurrent.o
        public void afterRanInterruptibly(V v, Throwable th) {
            if (th == null) {
                w.this.set(v);
            } else {
                w.this.setException(th);
            }
        }

        @Override // com.google.common.util.concurrent.o
        public final boolean isDone() {
            return w.this.isDone();
        }

        @Override // com.google.common.util.concurrent.o
        public V runInterruptibly() throws Exception {
            return this.callable.call();
        }

        @Override // com.google.common.util.concurrent.o
        public String toPendingString() {
            return this.callable.toString();
        }
    }

    public w(Callable<V> callable) {
        this.o = new b(callable);
    }

    public static <V> w<V> A(Callable<V> callable) {
        return new w<>(callable);
    }

    public static <V> w<V> y(AsyncCallable<V> asyncCallable) {
        return new w<>(asyncCallable);
    }

    public static <V> w<V> z(Runnable runnable, @NullableDecl V v) {
        return new w<>(Executors.callable(runnable, v));
    }

    @Override // com.google.common.util.concurrent.AbstractFuture
    public void afterDone() {
        o<?> oVar;
        super.afterDone();
        if (wasInterrupted() && (oVar = this.o) != null) {
            oVar.interruptTask();
        }
        this.o = null;
    }

    @Override // com.google.common.util.concurrent.AbstractFuture
    public String pendingToString() {
        o<?> oVar = this.o;
        if (oVar != null) {
            String valueOf = String.valueOf(oVar);
            StringBuilder sb = new StringBuilder(valueOf.length() + 7);
            sb.append("task=[");
            sb.append(valueOf);
            sb.append("]");
            return sb.toString();
        }
        return super.pendingToString();
    }

    @Override // java.util.concurrent.RunnableFuture, java.lang.Runnable
    public void run() {
        o<?> oVar = this.o;
        if (oVar != null) {
            oVar.run();
        }
        this.o = null;
    }

    public w(AsyncCallable<V> asyncCallable) {
        this.o = new a(asyncCallable);
    }
}
