package okhttp3.internal.concurrent;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public abstract class Task {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final String f14256a;
    public final boolean b;
    @Nullable
    public TaskQueue c;
    public long d;

    public Task(@NotNull String name, boolean z) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.f14256a = name;
        this.b = z;
        this.d = -1L;
    }

    public final boolean getCancelable() {
        return this.b;
    }

    @NotNull
    public final String getName() {
        return this.f14256a;
    }

    public final long getNextExecuteNanoTime$okhttp() {
        return this.d;
    }

    @Nullable
    public final TaskQueue getQueue$okhttp() {
        return this.c;
    }

    public final void initQueue$okhttp(@NotNull TaskQueue queue) {
        Intrinsics.checkNotNullParameter(queue, "queue");
        TaskQueue taskQueue = this.c;
        if (taskQueue == queue) {
            return;
        }
        if (taskQueue == null) {
            this.c = queue;
            return;
        }
        throw new IllegalStateException("task is in multiple queues".toString());
    }

    public abstract long runOnce();

    public final void setNextExecuteNanoTime$okhttp(long j) {
        this.d = j;
    }

    public final void setQueue$okhttp(@Nullable TaskQueue taskQueue) {
        this.c = taskQueue;
    }

    @NotNull
    public String toString() {
        return this.f14256a;
    }

    public /* synthetic */ Task(String str, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? true : z);
    }
}
