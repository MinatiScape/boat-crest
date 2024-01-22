package kotlinx.coroutines.scheduling;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlinx.coroutines.DebugKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class WorkQueue {
    public static final /* synthetic */ AtomicReferenceFieldUpdater b = AtomicReferenceFieldUpdater.newUpdater(WorkQueue.class, Object.class, "lastScheduledTask");
    public static final /* synthetic */ AtomicIntegerFieldUpdater c = AtomicIntegerFieldUpdater.newUpdater(WorkQueue.class, "producerIndex");
    public static final /* synthetic */ AtomicIntegerFieldUpdater d = AtomicIntegerFieldUpdater.newUpdater(WorkQueue.class, "consumerIndex");
    public static final /* synthetic */ AtomicIntegerFieldUpdater e = AtomicIntegerFieldUpdater.newUpdater(WorkQueue.class, "blockingTasksInBuffer");
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final AtomicReferenceArray<Task> f14193a = new AtomicReferenceArray<>(128);
    @NotNull
    private volatile /* synthetic */ Object lastScheduledTask = null;
    @NotNull
    private volatile /* synthetic */ int producerIndex = 0;
    @NotNull
    private volatile /* synthetic */ int consumerIndex = 0;
    @NotNull
    private volatile /* synthetic */ int blockingTasksInBuffer = 0;

    public static /* synthetic */ Task add$default(WorkQueue workQueue, Task task, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return workQueue.add(task, z);
    }

    public final Task a(Task task) {
        if (task.taskContext.getTaskMode() == 1) {
            e.incrementAndGet(this);
        }
        if (getBufferSize$kotlinx_coroutines_core() == 127) {
            return task;
        }
        int i = this.producerIndex & 127;
        while (this.f14193a.get(i) != null) {
            Thread.yield();
        }
        this.f14193a.lazySet(i, task);
        c.incrementAndGet(this);
        return null;
    }

    @Nullable
    public final Task add(@NotNull Task task, boolean z) {
        if (z) {
            return a(task);
        }
        Task task2 = (Task) b.getAndSet(this, task);
        if (task2 == null) {
            return null;
        }
        return a(task2);
    }

    public final void b(Task task) {
        if (task != null) {
            if (task.taskContext.getTaskMode() == 1) {
                int decrementAndGet = e.decrementAndGet(this);
                if (DebugKt.getASSERTIONS_ENABLED()) {
                    if (!(decrementAndGet >= 0)) {
                        throw new AssertionError();
                    }
                }
            }
        }
    }

    public final Task c() {
        Task andSet;
        while (true) {
            int i = this.consumerIndex;
            if (i - this.producerIndex == 0) {
                return null;
            }
            int i2 = i & 127;
            if (d.compareAndSet(this, i, i + 1) && (andSet = this.f14193a.getAndSet(i2, null)) != null) {
                b(andSet);
                return andSet;
            }
        }
    }

    public final boolean d(GlobalQueue globalQueue) {
        Task c2 = c();
        if (c2 == null) {
            return false;
        }
        globalQueue.addLast(c2);
        return true;
    }

    public final long e(WorkQueue workQueue, boolean z) {
        Task task;
        do {
            task = (Task) workQueue.lastScheduledTask;
            if (task == null) {
                return -2L;
            }
            if (z) {
                if (!(task.taskContext.getTaskMode() == 1)) {
                    return -2L;
                }
            }
            long nanoTime = TasksKt.schedulerTimeSource.nanoTime() - task.submissionTime;
            long j = TasksKt.WORK_STEALING_TIME_RESOLUTION_NS;
            if (nanoTime < j) {
                return j - nanoTime;
            }
        } while (!b.compareAndSet(workQueue, task, null));
        add$default(this, task, false, 2, null);
        return -1L;
    }

    public final int getBufferSize$kotlinx_coroutines_core() {
        return this.producerIndex - this.consumerIndex;
    }

    public final int getSize$kotlinx_coroutines_core() {
        return this.lastScheduledTask != null ? getBufferSize$kotlinx_coroutines_core() + 1 : getBufferSize$kotlinx_coroutines_core();
    }

    public final void offloadAllWorkTo(@NotNull GlobalQueue globalQueue) {
        Task task = (Task) b.getAndSet(this, null);
        if (task != null) {
            globalQueue.addLast(task);
        }
        do {
        } while (d(globalQueue));
    }

    @Nullable
    public final Task poll() {
        Task task = (Task) b.getAndSet(this, null);
        return task == null ? c() : task;
    }

    public final long tryStealBlockingFrom(@NotNull WorkQueue workQueue) {
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(getBufferSize$kotlinx_coroutines_core() == 0)) {
                throw new AssertionError();
            }
        }
        int i = workQueue.producerIndex;
        AtomicReferenceArray<Task> atomicReferenceArray = workQueue.f14193a;
        for (int i2 = workQueue.consumerIndex; i2 != i; i2++) {
            int i3 = i2 & 127;
            if (workQueue.blockingTasksInBuffer == 0) {
                break;
            }
            Task task = atomicReferenceArray.get(i3);
            if (task != null) {
                if ((task.taskContext.getTaskMode() == 1) && atomicReferenceArray.compareAndSet(i3, task, null)) {
                    e.decrementAndGet(workQueue);
                    add$default(this, task, false, 2, null);
                    return -1L;
                }
            }
        }
        return e(workQueue, true);
    }

    public final long tryStealFrom(@NotNull WorkQueue workQueue) {
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(getBufferSize$kotlinx_coroutines_core() == 0)) {
                throw new AssertionError();
            }
        }
        Task c2 = workQueue.c();
        if (c2 != null) {
            Task add$default = add$default(this, c2, false, 2, null);
            if (DebugKt.getASSERTIONS_ENABLED()) {
                if (add$default == null) {
                    return -1L;
                }
                throw new AssertionError();
            }
            return -1L;
        }
        return e(workQueue, false);
    }
}
