package kotlinx.coroutines;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class ThreadLocalEventLoop {
    @NotNull
    public static final ThreadLocalEventLoop INSTANCE = new ThreadLocalEventLoop();
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public static final ThreadLocal<EventLoop> f14142a = new ThreadLocal<>();

    @Nullable
    public final EventLoop currentOrNull$kotlinx_coroutines_core() {
        return f14142a.get();
    }

    @NotNull
    public final EventLoop getEventLoop$kotlinx_coroutines_core() {
        ThreadLocal<EventLoop> threadLocal = f14142a;
        EventLoop eventLoop = threadLocal.get();
        if (eventLoop == null) {
            EventLoop createEventLoop = EventLoopKt.createEventLoop();
            threadLocal.set(createEventLoop);
            return createEventLoop;
        }
        return eventLoop;
    }

    public final void resetEventLoop$kotlinx_coroutines_core() {
        f14142a.set(null);
    }

    public final void setEventLoop$kotlinx_coroutines_core(@NotNull EventLoop eventLoop) {
        f14142a.set(eventLoop);
    }
}
