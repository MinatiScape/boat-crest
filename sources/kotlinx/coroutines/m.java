package kotlinx.coroutines;

import java.util.concurrent.Future;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class m implements DisposableHandle {
    @NotNull
    public final Future<?> h;

    public m(@NotNull Future<?> future) {
        this.h = future;
    }

    @Override // kotlinx.coroutines.DisposableHandle
    public void dispose() {
        this.h.cancel(false);
    }

    @NotNull
    public String toString() {
        return "DisposableFutureHandle[" + this.h + ']';
    }
}
