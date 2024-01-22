package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicBoolean;
@Beta
@GwtIncompatible
/* loaded from: classes10.dex */
public final class JdkFutureAdapters {

    /* loaded from: classes10.dex */
    public static class a<V> extends ForwardingFuture<V> implements ListenableFuture<V> {
        public static final ThreadFactory l;
        public static final Executor m;
        public final Executor h;
        public final ExecutionList i;
        public final AtomicBoolean j;
        public final Future<V> k;

        /* renamed from: com.google.common.util.concurrent.JdkFutureAdapters$a$a  reason: collision with other inner class name */
        /* loaded from: classes10.dex */
        public class RunnableC0524a implements Runnable {
            public RunnableC0524a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    Uninterruptibles.getUninterruptibly(a.this.k);
                } catch (Throwable unused) {
                }
                a.this.i.execute();
            }
        }

        static {
            ThreadFactory build = new ThreadFactoryBuilder().setDaemon(true).setNameFormat("ListenableFutureAdapter-thread-%d").build();
            l = build;
            m = Executors.newCachedThreadPool(build);
        }

        public a(Future<V> future) {
            this(future, m);
        }

        @Override // com.google.common.util.concurrent.ListenableFuture
        public void addListener(Runnable runnable, Executor executor) {
            this.i.add(runnable, executor);
            if (this.j.compareAndSet(false, true)) {
                if (this.k.isDone()) {
                    this.i.execute();
                } else {
                    this.h.execute(new RunnableC0524a());
                }
            }
        }

        public a(Future<V> future, Executor executor) {
            this.i = new ExecutionList();
            this.j = new AtomicBoolean(false);
            this.k = (Future) Preconditions.checkNotNull(future);
            this.h = (Executor) Preconditions.checkNotNull(executor);
        }

        @Override // com.google.common.util.concurrent.ForwardingFuture, com.google.common.collect.ForwardingObject
        public Future<V> delegate() {
            return this.k;
        }
    }

    public static <V> ListenableFuture<V> listenInPoolThread(Future<V> future) {
        if (future instanceof ListenableFuture) {
            return (ListenableFuture) future;
        }
        return new a(future);
    }

    public static <V> ListenableFuture<V> listenInPoolThread(Future<V> future, Executor executor) {
        Preconditions.checkNotNull(executor);
        if (future instanceof ListenableFuture) {
            return (ListenableFuture) future;
        }
        return new a(future, executor);
    }
}
