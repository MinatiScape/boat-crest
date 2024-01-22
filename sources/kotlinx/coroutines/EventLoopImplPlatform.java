package kotlinx.coroutines;

import java.util.concurrent.locks.LockSupport;
import kotlin.Unit;
import kotlinx.coroutines.EventLoopImplBase;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public abstract class EventLoopImplPlatform extends EventLoop {
    @NotNull
    public abstract Thread getThread();

    public void reschedule(long j, @NotNull EventLoopImplBase.DelayedTask delayedTask) {
        DefaultExecutor.INSTANCE.schedule(j, delayedTask);
    }

    public final void unpark() {
        Unit unit;
        Thread thread = getThread();
        if (Thread.currentThread() != thread) {
            AbstractTimeSource timeSource = AbstractTimeSourceKt.getTimeSource();
            if (timeSource == null) {
                unit = null;
            } else {
                timeSource.unpark(thread);
                unit = Unit.INSTANCE;
            }
            if (unit == null) {
                LockSupport.unpark(thread);
            }
        }
    }
}
