package kotlinx.coroutines;

import java.util.concurrent.Executor;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.JvmField;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class l implements Executor {
    @JvmField
    @NotNull
    public final CoroutineDispatcher h;

    public l(@NotNull CoroutineDispatcher coroutineDispatcher) {
        this.h = coroutineDispatcher;
    }

    @Override // java.util.concurrent.Executor
    public void execute(@NotNull Runnable runnable) {
        this.h.dispatch(EmptyCoroutineContext.INSTANCE, runnable);
    }

    @NotNull
    public String toString() {
        return this.h.toString();
    }
}
