package kotlinx.coroutines;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class BlockingEventLoop extends EventLoopImplBase {
    @NotNull
    public final Thread m;

    public BlockingEventLoop(@NotNull Thread thread) {
        this.m = thread;
    }

    @Override // kotlinx.coroutines.EventLoopImplPlatform
    @NotNull
    public Thread getThread() {
        return this.m;
    }
}
