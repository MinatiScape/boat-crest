package kotlinx.coroutines;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public abstract class AbstractTimeSource {
    public abstract long currentTimeMillis();

    public abstract long nanoTime();

    public abstract void parkNanos(@NotNull Object obj, long j);

    public abstract void registerTimeLoopThread();

    public abstract void trackTask();

    public abstract void unTrackTask();

    public abstract void unpark(@NotNull Thread thread);

    public abstract void unregisterTimeLoopThread();

    @NotNull
    public abstract Runnable wrapTask(@NotNull Runnable runnable);
}
