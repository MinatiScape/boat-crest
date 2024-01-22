package kotlinx.coroutines.scheduling;

import java.util.concurrent.TimeUnit;
import kotlin.jvm.JvmField;
import kotlin.ranges.h;
import kotlinx.coroutines.internal.SystemPropsKt;
import kotlinx.coroutines.internal.d;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class TasksKt {
    @JvmField
    @NotNull
    public static final TaskContext BlockingContext;
    @JvmField
    public static final int CORE_POOL_SIZE;
    @NotNull
    public static final String DEFAULT_SCHEDULER_NAME = "DefaultDispatcher";
    @JvmField
    public static final long IDLE_WORKER_KEEP_ALIVE_NS;
    @JvmField
    public static final int MAX_POOL_SIZE;
    @JvmField
    @NotNull
    public static final TaskContext NonBlockingContext;
    public static final int TASK_NON_BLOCKING = 0;
    public static final int TASK_PROBABLY_BLOCKING = 1;
    @JvmField
    public static final long WORK_STEALING_TIME_RESOLUTION_NS;
    @JvmField
    @NotNull
    public static SchedulerTimeSource schedulerTimeSource;

    static {
        long e;
        int d;
        int d2;
        long e2;
        e = d.e("kotlinx.coroutines.scheduler.resolution.ns", 100000L, 0L, 0L, 12, null);
        WORK_STEALING_TIME_RESOLUTION_NS = e;
        d = d.d("kotlinx.coroutines.scheduler.core.pool.size", h.coerceAtLeast(SystemPropsKt.getAVAILABLE_PROCESSORS(), 2), 1, 0, 8, null);
        CORE_POOL_SIZE = d;
        d2 = d.d("kotlinx.coroutines.scheduler.max.pool.size", CoroutineScheduler.MAX_SUPPORTED_POOL_SIZE, 0, CoroutineScheduler.MAX_SUPPORTED_POOL_SIZE, 4, null);
        MAX_POOL_SIZE = d2;
        TimeUnit timeUnit = TimeUnit.SECONDS;
        e2 = d.e("kotlinx.coroutines.scheduler.keep.alive.sec", 60L, 0L, 0L, 12, null);
        IDLE_WORKER_KEEP_ALIVE_NS = timeUnit.toNanos(e2);
        schedulerTimeSource = NanoTimeSource.INSTANCE;
        NonBlockingContext = new b(0);
        BlockingContext = new b(1);
    }

    public static final boolean isBlocking(@NotNull Task task) {
        return task.taskContext.getTaskMode() == 1;
    }
}
