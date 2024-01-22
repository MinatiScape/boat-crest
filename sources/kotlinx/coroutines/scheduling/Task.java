package kotlinx.coroutines.scheduling;

import kotlin.jvm.JvmField;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public abstract class Task implements Runnable {
    @JvmField
    public long submissionTime;
    @JvmField
    @NotNull
    public TaskContext taskContext;

    public Task(long j, @NotNull TaskContext taskContext) {
        this.submissionTime = j;
        this.taskContext = taskContext;
    }

    public final int getMode() {
        return this.taskContext.getTaskMode();
    }

    public Task() {
        this(0L, TasksKt.NonBlockingContext);
    }
}
