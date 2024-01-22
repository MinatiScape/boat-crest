package kotlinx.coroutines;

import kotlin.Result;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class CompletableDeferredKt {
    @NotNull
    public static final <T> CompletableDeferred<T> CompletableDeferred(@Nullable Job job) {
        return new h(job);
    }

    public static /* synthetic */ CompletableDeferred CompletableDeferred$default(Job job, int i, Object obj) {
        if ((i & 1) != 0) {
            job = null;
        }
        return CompletableDeferred(job);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <T> boolean completeWith(@NotNull CompletableDeferred<T> completableDeferred, @NotNull Object obj) {
        Throwable m126exceptionOrNullimpl = Result.m126exceptionOrNullimpl(obj);
        return m126exceptionOrNullimpl == null ? completableDeferred.complete(obj) : completableDeferred.completeExceptionally(m126exceptionOrNullimpl);
    }

    @NotNull
    public static final <T> CompletableDeferred<T> CompletableDeferred(T t) {
        h hVar = new h(null);
        hVar.complete(t);
        return hVar;
    }
}
