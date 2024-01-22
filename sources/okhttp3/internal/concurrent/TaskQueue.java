package okhttp3.internal.concurrent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.RejectedExecutionException;
import java.util.logging.Level;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.Util;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class TaskQueue {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final TaskRunner f14257a;
    @NotNull
    public final String b;
    public boolean c;
    @Nullable
    public Task d;
    @NotNull
    public final List<Task> e;
    public boolean f;

    /* loaded from: classes12.dex */
    public static final class a extends Task {
        @NotNull
        public final CountDownLatch e;

        public a() {
            super(Intrinsics.stringPlus(Util.okHttpName, " awaitIdle"), false);
            this.e = new CountDownLatch(1);
        }

        @NotNull
        public final CountDownLatch a() {
            return this.e;
        }

        @Override // okhttp3.internal.concurrent.Task
        public long runOnce() {
            this.e.countDown();
            return -1L;
        }
    }

    public TaskQueue(@NotNull TaskRunner taskRunner, @NotNull String name) {
        Intrinsics.checkNotNullParameter(taskRunner, "taskRunner");
        Intrinsics.checkNotNullParameter(name, "name");
        this.f14257a = taskRunner;
        this.b = name;
        this.e = new ArrayList();
    }

    public static /* synthetic */ void execute$default(TaskQueue taskQueue, String name, long j, boolean z, Function0 block, int i, Object obj) {
        if ((i & 2) != 0) {
            j = 0;
        }
        if ((i & 4) != 0) {
            z = true;
        }
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(block, "block");
        taskQueue.schedule(new TaskQueue$execute$1(name, z, block), j);
    }

    public static /* synthetic */ void schedule$default(TaskQueue taskQueue, Task task, long j, int i, Object obj) {
        if ((i & 2) != 0) {
            j = 0;
        }
        taskQueue.schedule(task, j);
    }

    public final void cancelAll() {
        if (Util.assertionsEnabled && Thread.holdsLock(this)) {
            throw new AssertionError("Thread " + ((Object) Thread.currentThread().getName()) + " MUST NOT hold lock on " + this);
        }
        synchronized (this.f14257a) {
            if (cancelAllAndDecide$okhttp()) {
                getTaskRunner$okhttp().kickCoordinator$okhttp(this);
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    public final boolean cancelAllAndDecide$okhttp() {
        Task task = this.d;
        if (task != null) {
            Intrinsics.checkNotNull(task);
            if (task.getCancelable()) {
                this.f = true;
            }
        }
        boolean z = false;
        int size = this.e.size() - 1;
        if (size >= 0) {
            while (true) {
                int i = size - 1;
                if (this.e.get(size).getCancelable()) {
                    Task task2 = this.e.get(size);
                    if (TaskRunner.Companion.getLogger().isLoggable(Level.FINE)) {
                        TaskLoggerKt.access$log(task2, this, "canceled");
                    }
                    this.e.remove(size);
                    z = true;
                }
                if (i < 0) {
                    break;
                }
                size = i;
            }
        }
        return z;
    }

    public final void execute(@NotNull String name, long j, boolean z, @NotNull Function0<Unit> block) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(block, "block");
        schedule(new TaskQueue$execute$1(name, z, block), j);
    }

    @Nullable
    public final Task getActiveTask$okhttp() {
        return this.d;
    }

    public final boolean getCancelActiveTask$okhttp() {
        return this.f;
    }

    @NotNull
    public final List<Task> getFutureTasks$okhttp() {
        return this.e;
    }

    @NotNull
    public final String getName$okhttp() {
        return this.b;
    }

    @NotNull
    public final List<Task> getScheduledTasks() {
        List<Task> list;
        synchronized (this.f14257a) {
            list = CollectionsKt___CollectionsKt.toList(getFutureTasks$okhttp());
        }
        return list;
    }

    public final boolean getShutdown$okhttp() {
        return this.c;
    }

    @NotNull
    public final TaskRunner getTaskRunner$okhttp() {
        return this.f14257a;
    }

    @NotNull
    public final CountDownLatch idleLatch() {
        synchronized (this.f14257a) {
            if (getActiveTask$okhttp() == null && getFutureTasks$okhttp().isEmpty()) {
                return new CountDownLatch(0);
            }
            Task activeTask$okhttp = getActiveTask$okhttp();
            if (activeTask$okhttp instanceof a) {
                return ((a) activeTask$okhttp).a();
            }
            for (Task task : getFutureTasks$okhttp()) {
                if (task instanceof a) {
                    return ((a) task).a();
                }
            }
            a aVar = new a();
            if (scheduleAndDecide$okhttp(aVar, 0L, false)) {
                getTaskRunner$okhttp().kickCoordinator$okhttp(this);
            }
            return aVar.a();
        }
    }

    public final void schedule(@NotNull Task task, long j) {
        Intrinsics.checkNotNullParameter(task, "task");
        synchronized (this.f14257a) {
            if (getShutdown$okhttp()) {
                if (task.getCancelable()) {
                    if (TaskRunner.Companion.getLogger().isLoggable(Level.FINE)) {
                        TaskLoggerKt.access$log(task, this, "schedule canceled (queue is shutdown)");
                    }
                    return;
                }
                if (TaskRunner.Companion.getLogger().isLoggable(Level.FINE)) {
                    TaskLoggerKt.access$log(task, this, "schedule failed (queue is shutdown)");
                }
                throw new RejectedExecutionException();
            }
            if (scheduleAndDecide$okhttp(task, j, false)) {
                getTaskRunner$okhttp().kickCoordinator$okhttp(this);
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    public final boolean scheduleAndDecide$okhttp(@NotNull Task task, long j, boolean z) {
        String stringPlus;
        Intrinsics.checkNotNullParameter(task, "task");
        task.initQueue$okhttp(this);
        long nanoTime = this.f14257a.getBackend().nanoTime();
        long j2 = nanoTime + j;
        int indexOf = this.e.indexOf(task);
        if (indexOf != -1) {
            if (task.getNextExecuteNanoTime$okhttp() <= j2) {
                if (TaskRunner.Companion.getLogger().isLoggable(Level.FINE)) {
                    TaskLoggerKt.access$log(task, this, "already scheduled");
                }
                return false;
            }
            this.e.remove(indexOf);
        }
        task.setNextExecuteNanoTime$okhttp(j2);
        if (TaskRunner.Companion.getLogger().isLoggable(Level.FINE)) {
            if (z) {
                stringPlus = Intrinsics.stringPlus("run again after ", TaskLoggerKt.formatDuration(j2 - nanoTime));
            } else {
                stringPlus = Intrinsics.stringPlus("scheduled after ", TaskLoggerKt.formatDuration(j2 - nanoTime));
            }
            TaskLoggerKt.access$log(task, this, stringPlus);
        }
        Iterator<Task> it = this.e.iterator();
        int i = 0;
        while (true) {
            if (!it.hasNext()) {
                i = -1;
                break;
            }
            if (it.next().getNextExecuteNanoTime$okhttp() - nanoTime > j) {
                break;
            }
            i++;
        }
        if (i == -1) {
            i = this.e.size();
        }
        this.e.add(i, task);
        return i == 0;
    }

    public final void setActiveTask$okhttp(@Nullable Task task) {
        this.d = task;
    }

    public final void setCancelActiveTask$okhttp(boolean z) {
        this.f = z;
    }

    public final void setShutdown$okhttp(boolean z) {
        this.c = z;
    }

    public final void shutdown() {
        if (Util.assertionsEnabled && Thread.holdsLock(this)) {
            throw new AssertionError("Thread " + ((Object) Thread.currentThread().getName()) + " MUST NOT hold lock on " + this);
        }
        synchronized (this.f14257a) {
            setShutdown$okhttp(true);
            if (cancelAllAndDecide$okhttp()) {
                getTaskRunner$okhttp().kickCoordinator$okhttp(this);
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    @NotNull
    public String toString() {
        return this.b;
    }

    public static /* synthetic */ void schedule$default(TaskQueue taskQueue, String name, long j, Function0 block, int i, Object obj) {
        if ((i & 2) != 0) {
            j = 0;
        }
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(block, "block");
        taskQueue.schedule(new TaskQueue$schedule$2(name, block), j);
    }

    public final void schedule(@NotNull String name, long j, @NotNull Function0<Long> block) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(block, "block");
        schedule(new TaskQueue$schedule$2(name, block), j);
    }
}
