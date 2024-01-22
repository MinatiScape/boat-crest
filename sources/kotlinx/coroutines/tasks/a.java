package kotlinx.coroutines.tasks;

import java.util.concurrent.Executor;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class a implements Executor {
    @NotNull
    public static final a h = new a();

    @Override // java.util.concurrent.Executor
    public void execute(@NotNull Runnable runnable) {
        runnable.run();
    }
}
