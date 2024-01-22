package kotlinx.coroutines;

import kotlin.Unit;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class a0 implements Runnable {
    @NotNull
    public final CoroutineDispatcher h;
    @NotNull
    public final CancellableContinuation<Unit> i;

    /* JADX WARN: Multi-variable type inference failed */
    public a0(@NotNull CoroutineDispatcher coroutineDispatcher, @NotNull CancellableContinuation<? super Unit> cancellableContinuation) {
        this.h = coroutineDispatcher;
        this.i = cancellableContinuation;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.i.resumeUndispatched(this.h, Unit.INSTANCE);
    }
}
