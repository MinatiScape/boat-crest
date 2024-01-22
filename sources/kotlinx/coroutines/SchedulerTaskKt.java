package kotlinx.coroutines;

import kotlinx.coroutines.scheduling.Task;
import kotlinx.coroutines.scheduling.TaskContext;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class SchedulerTaskKt {
    public static final void afterTask(@NotNull TaskContext taskContext) {
        taskContext.afterTask();
    }

    @NotNull
    public static final TaskContext getTaskContext(@NotNull Task task) {
        return task.taskContext;
    }

    public static /* synthetic */ void getTaskContext$annotations(Task task) {
    }
}
