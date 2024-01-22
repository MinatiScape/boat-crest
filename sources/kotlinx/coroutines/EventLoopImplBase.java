package kotlinx.coroutines;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.DurationKt;
import kotlinx.coroutines.Delay;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.internal.ThreadSafeHeap;
import kotlinx.coroutines.internal.ThreadSafeHeapNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public abstract class EventLoopImplBase extends EventLoopImplPlatform implements Delay {
    public static final /* synthetic */ AtomicReferenceFieldUpdater k = AtomicReferenceFieldUpdater.newUpdater(EventLoopImplBase.class, Object.class, "_queue");
    public static final /* synthetic */ AtomicReferenceFieldUpdater l = AtomicReferenceFieldUpdater.newUpdater(EventLoopImplBase.class, Object.class, "_delayed");
    @NotNull
    private volatile /* synthetic */ Object _queue = null;
    @NotNull
    private volatile /* synthetic */ Object _delayed = null;
    @NotNull
    private volatile /* synthetic */ int _isCompleted = 0;

    /* loaded from: classes12.dex */
    public static abstract class DelayedTask implements Runnable, Comparable<DelayedTask>, DisposableHandle, ThreadSafeHeapNode {
        @Nullable
        public Object h;
        public int i = -1;
        @JvmField
        public long nanoTime;

        public DelayedTask(long j) {
            this.nanoTime = j;
        }

        @Override // kotlinx.coroutines.DisposableHandle
        public final synchronized void dispose() {
            Symbol symbol;
            Symbol symbol2;
            Object obj = this.h;
            symbol = EventLoop_commonKt.f14140a;
            if (obj == symbol) {
                return;
            }
            DelayedTaskQueue delayedTaskQueue = obj instanceof DelayedTaskQueue ? (DelayedTaskQueue) obj : null;
            if (delayedTaskQueue != null) {
                delayedTaskQueue.remove(this);
            }
            symbol2 = EventLoop_commonKt.f14140a;
            this.h = symbol2;
        }

        @Override // kotlinx.coroutines.internal.ThreadSafeHeapNode
        @Nullable
        public ThreadSafeHeap<?> getHeap() {
            Object obj = this.h;
            if (obj instanceof ThreadSafeHeap) {
                return (ThreadSafeHeap) obj;
            }
            return null;
        }

        @Override // kotlinx.coroutines.internal.ThreadSafeHeapNode
        public int getIndex() {
            return this.i;
        }

        public final synchronized int scheduleTask(long j, @NotNull DelayedTaskQueue delayedTaskQueue, @NotNull EventLoopImplBase eventLoopImplBase) {
            Symbol symbol;
            Object obj = this.h;
            symbol = EventLoop_commonKt.f14140a;
            if (obj == symbol) {
                return 2;
            }
            synchronized (delayedTaskQueue) {
                DelayedTask firstImpl = delayedTaskQueue.firstImpl();
                if (eventLoopImplBase.isCompleted()) {
                    return 1;
                }
                if (firstImpl == null) {
                    delayedTaskQueue.timeNow = j;
                } else {
                    long j2 = firstImpl.nanoTime;
                    if (j2 - j < 0) {
                        j = j2;
                    }
                    if (j - delayedTaskQueue.timeNow > 0) {
                        delayedTaskQueue.timeNow = j;
                    }
                }
                long j3 = this.nanoTime;
                long j4 = delayedTaskQueue.timeNow;
                if (j3 - j4 < 0) {
                    this.nanoTime = j4;
                }
                delayedTaskQueue.addImpl(this);
                return 0;
            }
        }

        @Override // kotlinx.coroutines.internal.ThreadSafeHeapNode
        public void setHeap(@Nullable ThreadSafeHeap<?> threadSafeHeap) {
            Symbol symbol;
            Object obj = this.h;
            symbol = EventLoop_commonKt.f14140a;
            if (obj != symbol) {
                this.h = threadSafeHeap;
                return;
            }
            throw new IllegalArgumentException("Failed requirement.".toString());
        }

        @Override // kotlinx.coroutines.internal.ThreadSafeHeapNode
        public void setIndex(int i) {
            this.i = i;
        }

        public final boolean timeToExecute(long j) {
            return j - this.nanoTime >= 0;
        }

        @NotNull
        public String toString() {
            return "Delayed[nanos=" + this.nanoTime + ']';
        }

        @Override // java.lang.Comparable
        public int compareTo(@NotNull DelayedTask delayedTask) {
            int i = ((this.nanoTime - delayedTask.nanoTime) > 0L ? 1 : ((this.nanoTime - delayedTask.nanoTime) == 0L ? 0 : -1));
            if (i > 0) {
                return 1;
            }
            return i < 0 ? -1 : 0;
        }
    }

    /* loaded from: classes12.dex */
    public static final class DelayedTaskQueue extends ThreadSafeHeap<DelayedTask> {
        @JvmField
        public long timeNow;

        public DelayedTaskQueue(long j) {
            this.timeNow = j;
        }
    }

    /* loaded from: classes12.dex */
    public final class a extends DelayedTask {
        @NotNull
        public final CancellableContinuation<Unit> j;

        /* JADX WARN: Multi-variable type inference failed */
        public a(long j, @NotNull CancellableContinuation<? super Unit> cancellableContinuation) {
            super(j);
            this.j = cancellableContinuation;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.j.resumeUndispatched(EventLoopImplBase.this, Unit.INSTANCE);
        }

        @Override // kotlinx.coroutines.EventLoopImplBase.DelayedTask
        @NotNull
        public String toString() {
            return Intrinsics.stringPlus(super.toString(), this.j);
        }
    }

    /* loaded from: classes12.dex */
    public static final class b extends DelayedTask {
        @NotNull
        public final Runnable j;

        public b(long j, @NotNull Runnable runnable) {
            super(j);
            this.j = runnable;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.j.run();
        }

        @Override // kotlinx.coroutines.EventLoopImplBase.DelayedTask
        @NotNull
        public String toString() {
            return Intrinsics.stringPlus(super.toString(), this.j);
        }
    }

    private final void g(boolean z) {
        this._isCompleted = z ? 1 : 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r0v0, types: [boolean, int] */
    public final boolean isCompleted() {
        return this._isCompleted;
    }

    public final void b() {
        Symbol symbol;
        Symbol symbol2;
        if (DebugKt.getASSERTIONS_ENABLED() && !isCompleted()) {
            throw new AssertionError();
        }
        while (true) {
            Object obj = this._queue;
            if (obj == null) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = k;
                symbol = EventLoop_commonKt.b;
                if (atomicReferenceFieldUpdater.compareAndSet(this, null, symbol)) {
                    return;
                }
            } else if (!(obj instanceof LockFreeTaskQueueCore)) {
                symbol2 = EventLoop_commonKt.b;
                if (obj == symbol2) {
                    return;
                }
                LockFreeTaskQueueCore lockFreeTaskQueueCore = new LockFreeTaskQueueCore(8, true);
                lockFreeTaskQueueCore.addLast((Runnable) obj);
                if (k.compareAndSet(this, obj, lockFreeTaskQueueCore)) {
                    return;
                }
            } else {
                ((LockFreeTaskQueueCore) obj).close();
                return;
            }
        }
    }

    public final Runnable c() {
        Symbol symbol;
        while (true) {
            Object obj = this._queue;
            if (obj == null) {
                return null;
            }
            if (!(obj instanceof LockFreeTaskQueueCore)) {
                symbol = EventLoop_commonKt.b;
                if (obj == symbol) {
                    return null;
                }
                if (k.compareAndSet(this, obj, null)) {
                    return (Runnable) obj;
                }
            } else {
                LockFreeTaskQueueCore lockFreeTaskQueueCore = (LockFreeTaskQueueCore) obj;
                Object removeFirstOrNull = lockFreeTaskQueueCore.removeFirstOrNull();
                if (removeFirstOrNull != LockFreeTaskQueueCore.REMOVE_FROZEN) {
                    return (Runnable) removeFirstOrNull;
                }
                k.compareAndSet(this, obj, lockFreeTaskQueueCore.next());
            }
        }
    }

    public final boolean d(Runnable runnable) {
        Symbol symbol;
        while (true) {
            Object obj = this._queue;
            if (isCompleted()) {
                return false;
            }
            if (obj == null) {
                if (k.compareAndSet(this, null, runnable)) {
                    return true;
                }
            } else if (!(obj instanceof LockFreeTaskQueueCore)) {
                symbol = EventLoop_commonKt.b;
                if (obj == symbol) {
                    return false;
                }
                LockFreeTaskQueueCore lockFreeTaskQueueCore = new LockFreeTaskQueueCore(8, true);
                lockFreeTaskQueueCore.addLast((Runnable) obj);
                lockFreeTaskQueueCore.addLast(runnable);
                if (k.compareAndSet(this, obj, lockFreeTaskQueueCore)) {
                    return true;
                }
            } else {
                LockFreeTaskQueueCore lockFreeTaskQueueCore2 = (LockFreeTaskQueueCore) obj;
                int addLast = lockFreeTaskQueueCore2.addLast(runnable);
                if (addLast == 0) {
                    return true;
                }
                if (addLast == 1) {
                    k.compareAndSet(this, obj, lockFreeTaskQueueCore2.next());
                } else if (addLast == 2) {
                    return false;
                }
            }
        }
    }

    @Override // kotlinx.coroutines.Delay
    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated without replacement as an internal method never intended for public use")
    @Nullable
    public Object delay(long j, @NotNull Continuation<? super Unit> continuation) {
        return Delay.DefaultImpls.delay(this, j, continuation);
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public final void dispatch(@NotNull CoroutineContext coroutineContext, @NotNull Runnable runnable) {
        enqueue(runnable);
    }

    public final void e() {
        AbstractTimeSource timeSource = AbstractTimeSourceKt.getTimeSource();
        Long valueOf = timeSource == null ? null : Long.valueOf(timeSource.nanoTime());
        long nanoTime = valueOf == null ? System.nanoTime() : valueOf.longValue();
        while (true) {
            DelayedTaskQueue delayedTaskQueue = (DelayedTaskQueue) this._delayed;
            DelayedTask removeFirstOrNull = delayedTaskQueue == null ? null : delayedTaskQueue.removeFirstOrNull();
            if (removeFirstOrNull == null) {
                return;
            }
            reschedule(nanoTime, removeFirstOrNull);
        }
    }

    public void enqueue(@NotNull Runnable runnable) {
        if (d(runnable)) {
            unpark();
        } else {
            DefaultExecutor.INSTANCE.enqueue(runnable);
        }
    }

    public final int f(long j, DelayedTask delayedTask) {
        if (isCompleted()) {
            return 1;
        }
        DelayedTaskQueue delayedTaskQueue = (DelayedTaskQueue) this._delayed;
        if (delayedTaskQueue == null) {
            l.compareAndSet(this, null, new DelayedTaskQueue(j));
            Object obj = this._delayed;
            Intrinsics.checkNotNull(obj);
            delayedTaskQueue = (DelayedTaskQueue) obj;
        }
        return delayedTask.scheduleTask(j, delayedTaskQueue, this);
    }

    @Override // kotlinx.coroutines.EventLoop
    public long getNextTime() {
        Symbol symbol;
        if (super.getNextTime() == 0) {
            return 0L;
        }
        Object obj = this._queue;
        if (obj != null) {
            if (!(obj instanceof LockFreeTaskQueueCore)) {
                symbol = EventLoop_commonKt.b;
                return obj == symbol ? Long.MAX_VALUE : 0L;
            } else if (!((LockFreeTaskQueueCore) obj).isEmpty()) {
                return 0L;
            }
        }
        DelayedTaskQueue delayedTaskQueue = (DelayedTaskQueue) this._delayed;
        DelayedTask peek = delayedTaskQueue == null ? null : delayedTaskQueue.peek();
        if (peek == null) {
            return Long.MAX_VALUE;
        }
        long j = peek.nanoTime;
        AbstractTimeSource timeSource = AbstractTimeSourceKt.getTimeSource();
        Long valueOf = timeSource != null ? Long.valueOf(timeSource.nanoTime()) : null;
        return kotlin.ranges.h.coerceAtLeast(j - (valueOf == null ? System.nanoTime() : valueOf.longValue()), 0L);
    }

    public final boolean h(DelayedTask delayedTask) {
        DelayedTaskQueue delayedTaskQueue = (DelayedTaskQueue) this._delayed;
        return (delayedTaskQueue == null ? null : delayedTaskQueue.peek()) == delayedTask;
    }

    @NotNull
    public DisposableHandle invokeOnTimeout(long j, @NotNull Runnable runnable, @NotNull CoroutineContext coroutineContext) {
        return Delay.DefaultImpls.invokeOnTimeout(this, j, runnable, coroutineContext);
    }

    @Override // kotlinx.coroutines.EventLoop
    public boolean isEmpty() {
        Symbol symbol;
        if (isUnconfinedQueueEmpty()) {
            DelayedTaskQueue delayedTaskQueue = (DelayedTaskQueue) this._delayed;
            if (delayedTaskQueue == null || delayedTaskQueue.isEmpty()) {
                Object obj = this._queue;
                if (obj != null) {
                    if (obj instanceof LockFreeTaskQueueCore) {
                        return ((LockFreeTaskQueueCore) obj).isEmpty();
                    }
                    symbol = EventLoop_commonKt.b;
                    if (obj != symbol) {
                        return false;
                    }
                }
                return true;
            }
            return false;
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0065  */
    @Override // kotlinx.coroutines.EventLoop
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public long processNextEvent() {
        /*
            r9 = this;
            boolean r0 = r9.processUnconfinedEvent()
            r1 = 0
            if (r0 == 0) goto L9
            return r1
        L9:
            java.lang.Object r0 = r9._delayed
            kotlinx.coroutines.EventLoopImplBase$DelayedTaskQueue r0 = (kotlinx.coroutines.EventLoopImplBase.DelayedTaskQueue) r0
            if (r0 == 0) goto L5b
            boolean r3 = r0.isEmpty()
            if (r3 != 0) goto L5b
            kotlinx.coroutines.AbstractTimeSource r3 = kotlinx.coroutines.AbstractTimeSourceKt.getTimeSource()
            r4 = 0
            if (r3 != 0) goto L1e
            r3 = r4
            goto L26
        L1e:
            long r5 = r3.nanoTime()
            java.lang.Long r3 = java.lang.Long.valueOf(r5)
        L26:
            if (r3 != 0) goto L2d
            long r5 = java.lang.System.nanoTime()
            goto L31
        L2d:
            long r5 = r3.longValue()
        L31:
            monitor-enter(r0)
            kotlinx.coroutines.internal.ThreadSafeHeapNode r3 = r0.firstImpl()     // Catch: java.lang.Throwable -> L58
            if (r3 != 0) goto L3b
            monitor-exit(r0)
            r3 = r4
            goto L53
        L3b:
            kotlinx.coroutines.EventLoopImplBase$DelayedTask r3 = (kotlinx.coroutines.EventLoopImplBase.DelayedTask) r3     // Catch: java.lang.Throwable -> L58
            boolean r7 = r3.timeToExecute(r5)     // Catch: java.lang.Throwable -> L58
            r8 = 0
            if (r7 == 0) goto L49
            boolean r3 = r9.d(r3)     // Catch: java.lang.Throwable -> L58
            goto L4a
        L49:
            r3 = r8
        L4a:
            if (r3 == 0) goto L51
            kotlinx.coroutines.internal.ThreadSafeHeapNode r3 = r0.removeAtImpl(r8)     // Catch: java.lang.Throwable -> L58
            goto L52
        L51:
            r3 = r4
        L52:
            monitor-exit(r0)
        L53:
            kotlinx.coroutines.EventLoopImplBase$DelayedTask r3 = (kotlinx.coroutines.EventLoopImplBase.DelayedTask) r3
            if (r3 != 0) goto L31
            goto L5b
        L58:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        L5b:
            java.lang.Runnable r0 = r9.c()
            if (r0 == 0) goto L65
            r0.run()
            return r1
        L65:
            long r0 = r9.getNextTime()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.EventLoopImplBase.processNextEvent():long");
    }

    public final void resetAll() {
        this._queue = null;
        this._delayed = null;
    }

    public final void schedule(long j, @NotNull DelayedTask delayedTask) {
        int f = f(j, delayedTask);
        if (f == 0) {
            if (h(delayedTask)) {
                unpark();
            }
        } else if (f == 1) {
            reschedule(j, delayedTask);
        } else if (f != 2) {
            throw new IllegalStateException("unexpected result".toString());
        }
    }

    @NotNull
    public final DisposableHandle scheduleInvokeOnTimeout(long j, @NotNull Runnable runnable) {
        long delayToNanos = EventLoop_commonKt.delayToNanos(j);
        if (delayToNanos < DurationKt.MAX_MILLIS) {
            AbstractTimeSource timeSource = AbstractTimeSourceKt.getTimeSource();
            Long valueOf = timeSource == null ? null : Long.valueOf(timeSource.nanoTime());
            long nanoTime = valueOf == null ? System.nanoTime() : valueOf.longValue();
            b bVar = new b(delayToNanos + nanoTime, runnable);
            schedule(nanoTime, bVar);
            return bVar;
        }
        return NonDisposableHandle.INSTANCE;
    }

    @Override // kotlinx.coroutines.Delay
    public void scheduleResumeAfterDelay(long j, @NotNull CancellableContinuation<? super Unit> cancellableContinuation) {
        long delayToNanos = EventLoop_commonKt.delayToNanos(j);
        if (delayToNanos < DurationKt.MAX_MILLIS) {
            AbstractTimeSource timeSource = AbstractTimeSourceKt.getTimeSource();
            Long valueOf = timeSource == null ? null : Long.valueOf(timeSource.nanoTime());
            long nanoTime = valueOf == null ? System.nanoTime() : valueOf.longValue();
            a aVar = new a(delayToNanos + nanoTime, cancellableContinuation);
            CancellableContinuationKt.disposeOnCancellation(cancellableContinuation, aVar);
            schedule(nanoTime, aVar);
        }
    }

    @Override // kotlinx.coroutines.EventLoop
    public void shutdown() {
        ThreadLocalEventLoop.INSTANCE.resetEventLoop$kotlinx_coroutines_core();
        g(true);
        b();
        do {
        } while (processNextEvent() <= 0);
        e();
    }
}
