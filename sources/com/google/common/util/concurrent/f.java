package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import com.google.common.util.concurrent.c;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible
/* loaded from: classes10.dex */
public final class f<V> extends com.google.common.util.concurrent.c<Object, V> {
    public f<V>.c<?> w;

    /* loaded from: classes10.dex */
    public final class a extends f<V>.c<ListenableFuture<V>> {
        private final AsyncCallable<V> callable;

        public a(AsyncCallable<V> asyncCallable, Executor executor) {
            super(executor);
            this.callable = (AsyncCallable) Preconditions.checkNotNull(asyncCallable);
        }

        @Override // com.google.common.util.concurrent.f.c
        public /* bridge */ /* synthetic */ void setValue(Object obj) {
            setValue((ListenableFuture) ((ListenableFuture) obj));
        }

        @Override // com.google.common.util.concurrent.o
        public String toPendingString() {
            return this.callable.toString();
        }

        @Override // com.google.common.util.concurrent.o
        public ListenableFuture<V> runInterruptibly() throws Exception {
            return (ListenableFuture) Preconditions.checkNotNull(this.callable.call(), "AsyncCallable.call returned null instead of a Future. Did you mean to return immediateFuture(null)? %s", this.callable);
        }

        public void setValue(ListenableFuture<V> listenableFuture) {
            f.this.setFuture(listenableFuture);
        }
    }

    /* loaded from: classes10.dex */
    public final class b extends f<V>.c<V> {
        private final Callable<V> callable;

        public b(Callable<V> callable, Executor executor) {
            super(executor);
            this.callable = (Callable) Preconditions.checkNotNull(callable);
        }

        @Override // com.google.common.util.concurrent.o
        public V runInterruptibly() throws Exception {
            return this.callable.call();
        }

        @Override // com.google.common.util.concurrent.f.c
        public void setValue(V v) {
            f.this.set(v);
        }

        @Override // com.google.common.util.concurrent.o
        public String toPendingString() {
            return this.callable.toString();
        }
    }

    /* loaded from: classes10.dex */
    public abstract class c<T> extends o<T> {
        private final Executor listenerExecutor;

        public c(Executor executor) {
            this.listenerExecutor = (Executor) Preconditions.checkNotNull(executor);
        }

        @Override // com.google.common.util.concurrent.o
        public final void afterRanInterruptibly(T t, Throwable th) {
            f.this.w = null;
            if (th != null) {
                if (th instanceof ExecutionException) {
                    f.this.setException(th.getCause());
                    return;
                } else if (th instanceof CancellationException) {
                    f.this.cancel(false);
                    return;
                } else {
                    f.this.setException(th);
                    return;
                }
            }
            setValue(t);
        }

        public final void execute() {
            try {
                this.listenerExecutor.execute(this);
            } catch (RejectedExecutionException e) {
                f.this.setException(e);
            }
        }

        @Override // com.google.common.util.concurrent.o
        public final boolean isDone() {
            return f.this.isDone();
        }

        public abstract void setValue(T t);
    }

    public f(ImmutableCollection<? extends ListenableFuture<?>> immutableCollection, boolean z, Executor executor, AsyncCallable<V> asyncCallable) {
        super(immutableCollection, z, false);
        this.w = new a(asyncCallable, executor);
        O();
    }

    @Override // com.google.common.util.concurrent.c
    public void J(int i, @NullableDecl Object obj) {
    }

    @Override // com.google.common.util.concurrent.c
    public void M() {
        f<V>.c<?> cVar = this.w;
        if (cVar != null) {
            cVar.execute();
        }
    }

    @Override // com.google.common.util.concurrent.c
    public void R(c.EnumC0529c enumC0529c) {
        super.R(enumC0529c);
        if (enumC0529c == c.EnumC0529c.OUTPUT_FUTURE_DONE) {
            this.w = null;
        }
    }

    @Override // com.google.common.util.concurrent.AbstractFuture
    public void interruptTask() {
        f<V>.c<?> cVar = this.w;
        if (cVar != null) {
            cVar.interruptTask();
        }
    }

    public f(ImmutableCollection<? extends ListenableFuture<?>> immutableCollection, boolean z, Executor executor, Callable<V> callable) {
        super(immutableCollection, z, false);
        this.w = new b(callable, executor);
        O();
    }
}
