package kotlinx.coroutines.debug.internal;

import java.util.List;
import kotlin.PublishedApi;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.JvmName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@PublishedApi
/* loaded from: classes12.dex */
public final class DebugCoroutineInfo {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final CoroutineContext f14154a;
    @Nullable
    public final CoroutineStackFrame b;
    public final long c;
    @NotNull
    public final List<StackTraceElement> d;
    @NotNull
    public final String e;
    @Nullable
    public final Thread f;
    @Nullable
    public final CoroutineStackFrame g;
    @NotNull
    public final List<StackTraceElement> h;

    public DebugCoroutineInfo(@NotNull DebugCoroutineInfoImpl debugCoroutineInfoImpl, @NotNull CoroutineContext coroutineContext) {
        this.f14154a = coroutineContext;
        this.b = debugCoroutineInfoImpl.getCreationStackBottom();
        this.c = debugCoroutineInfoImpl.sequenceNumber;
        this.d = debugCoroutineInfoImpl.getCreationStackTrace();
        this.e = debugCoroutineInfoImpl.getState();
        this.f = debugCoroutineInfoImpl.lastObservedThread;
        this.g = debugCoroutineInfoImpl.getLastObservedFrame$kotlinx_coroutines_core();
        this.h = debugCoroutineInfoImpl.lastObservedStackTrace();
    }

    @NotNull
    public final CoroutineContext getContext() {
        return this.f14154a;
    }

    @Nullable
    public final CoroutineStackFrame getCreationStackBottom() {
        return this.b;
    }

    @NotNull
    public final List<StackTraceElement> getCreationStackTrace() {
        return this.d;
    }

    @Nullable
    public final CoroutineStackFrame getLastObservedFrame() {
        return this.g;
    }

    @Nullable
    public final Thread getLastObservedThread() {
        return this.f;
    }

    public final long getSequenceNumber() {
        return this.c;
    }

    @NotNull
    public final String getState() {
        return this.e;
    }

    @JvmName(name = "lastObservedStackTrace")
    @NotNull
    public final List<StackTraceElement> lastObservedStackTrace() {
        return this.h;
    }
}
