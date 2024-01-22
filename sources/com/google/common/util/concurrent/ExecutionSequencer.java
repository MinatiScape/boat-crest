package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;
@Beta
/* loaded from: classes10.dex */
public final class ExecutionSequencer {

    /* renamed from: a  reason: collision with root package name */
    public final AtomicReference<ListenableFuture<Void>> f10786a = new AtomicReference<>(Futures.immediateVoidFuture());
    public f b = new f(null);

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes10.dex */
    public class a<T> implements AsyncCallable<T> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Callable f10787a;

        public a(ExecutionSequencer executionSequencer, Callable callable) {
            this.f10787a = callable;
        }

        @Override // com.google.common.util.concurrent.AsyncCallable
        public ListenableFuture<T> call() throws Exception {
            return Futures.immediateFuture(this.f10787a.call());
        }

        public String toString() {
            return this.f10787a.toString();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes10.dex */
    public class b<T> implements AsyncCallable<T> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ e f10788a;
        public final /* synthetic */ AsyncCallable b;

        public b(ExecutionSequencer executionSequencer, e eVar, AsyncCallable asyncCallable) {
            this.f10788a = eVar;
            this.b = asyncCallable;
        }

        @Override // com.google.common.util.concurrent.AsyncCallable
        public ListenableFuture<T> call() throws Exception {
            if (!this.f10788a.trySetStarted()) {
                return Futures.immediateCancelledFuture();
            }
            return this.b.call();
        }

        public String toString() {
            return this.b.toString();
        }
    }

    /* loaded from: classes10.dex */
    public class c implements Runnable {
        public final /* synthetic */ w h;
        public final /* synthetic */ SettableFuture i;
        public final /* synthetic */ ListenableFuture j;
        public final /* synthetic */ ListenableFuture k;
        public final /* synthetic */ e l;

        public c(ExecutionSequencer executionSequencer, w wVar, SettableFuture settableFuture, ListenableFuture listenableFuture, ListenableFuture listenableFuture2, e eVar) {
            this.h = wVar;
            this.i = settableFuture;
            this.j = listenableFuture;
            this.k = listenableFuture2;
            this.l = eVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.h.isDone()) {
                this.i.setFuture(this.j);
            } else if (this.k.isCancelled() && this.l.trySetCancelled()) {
                this.h.cancel(false);
            }
        }
    }

    /* loaded from: classes10.dex */
    public enum d {
        NOT_RUN,
        CANCELLED,
        STARTED
    }

    /* loaded from: classes10.dex */
    public static final class e extends AtomicReference<d> implements Executor, Runnable {
        public Executor delegate;
        public ExecutionSequencer sequencer;
        public Thread submitting;
        public Runnable task;

        public /* synthetic */ e(Executor executor, ExecutionSequencer executionSequencer, a aVar) {
            this(executor, executionSequencer);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean trySetCancelled() {
            return compareAndSet(d.NOT_RUN, d.CANCELLED);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean trySetStarted() {
            return compareAndSet(d.NOT_RUN, d.STARTED);
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            if (get() == d.CANCELLED) {
                this.delegate = null;
                this.sequencer = null;
                return;
            }
            this.submitting = Thread.currentThread();
            try {
                f fVar = this.sequencer.b;
                if (fVar.f10789a == this.submitting) {
                    this.sequencer = null;
                    Preconditions.checkState(fVar.b == null);
                    fVar.b = runnable;
                    fVar.c = this.delegate;
                    this.delegate = null;
                } else {
                    Executor executor = this.delegate;
                    this.delegate = null;
                    this.task = runnable;
                    executor.execute(this);
                }
            } finally {
                this.submitting = null;
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            Thread currentThread = Thread.currentThread();
            if (currentThread != this.submitting) {
                Runnable runnable = this.task;
                this.task = null;
                runnable.run();
                return;
            }
            f fVar = new f(null);
            fVar.f10789a = currentThread;
            this.sequencer.b = fVar;
            this.sequencer = null;
            try {
                Runnable runnable2 = this.task;
                this.task = null;
                runnable2.run();
                while (true) {
                    Runnable runnable3 = fVar.b;
                    boolean z = true;
                    boolean z2 = runnable3 != null;
                    Executor executor = fVar.c;
                    if (executor == null) {
                        z = false;
                    }
                    if (!z || !z2) {
                        return;
                    }
                    fVar.b = null;
                    fVar.c = null;
                    executor.execute(runnable3);
                }
            } finally {
                fVar.f10789a = null;
            }
        }

        private e(Executor executor, ExecutionSequencer executionSequencer) {
            super(d.NOT_RUN);
            this.delegate = executor;
            this.sequencer = executionSequencer;
        }
    }

    /* loaded from: classes10.dex */
    public static final class f {

        /* renamed from: a  reason: collision with root package name */
        public Thread f10789a;
        public Runnable b;
        public Executor c;

        public f() {
        }

        public /* synthetic */ f(a aVar) {
            this();
        }
    }

    public static ExecutionSequencer create() {
        return new ExecutionSequencer();
    }

    public <T> ListenableFuture<T> submit(Callable<T> callable, Executor executor) {
        Preconditions.checkNotNull(callable);
        Preconditions.checkNotNull(executor);
        return submitAsync(new a(this, callable), executor);
    }

    public <T> ListenableFuture<T> submitAsync(AsyncCallable<T> asyncCallable, Executor executor) {
        Preconditions.checkNotNull(asyncCallable);
        Preconditions.checkNotNull(executor);
        e eVar = new e(executor, this, null);
        b bVar = new b(this, eVar, asyncCallable);
        SettableFuture create = SettableFuture.create();
        ListenableFuture<Void> andSet = this.f10786a.getAndSet(create);
        w y = w.y(bVar);
        andSet.addListener(y, eVar);
        ListenableFuture<T> nonCancellationPropagating = Futures.nonCancellationPropagating(y);
        c cVar = new c(this, y, create, andSet, nonCancellationPropagating, eVar);
        nonCancellationPropagating.addListener(cVar, MoreExecutors.directExecutor());
        y.addListener(cVar, MoreExecutors.directExecutor());
        return nonCancellationPropagating;
    }
}
