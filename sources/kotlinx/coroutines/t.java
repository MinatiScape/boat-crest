package kotlinx.coroutines;

import java.util.concurrent.Future;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final /* synthetic */ class t {
    public static final void a(@NotNull CancellableContinuation<?> cancellableContinuation, @NotNull Future<?> future) {
        cancellableContinuation.invokeOnCancellation(new f(future));
    }

    @InternalCoroutinesApi
    @NotNull
    public static final DisposableHandle b(@NotNull Job job, @NotNull Future<?> future) {
        return job.invokeOnCompletion(new g(future));
    }
}
