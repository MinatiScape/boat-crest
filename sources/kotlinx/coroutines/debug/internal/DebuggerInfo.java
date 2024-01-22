package kotlinx.coroutines.debug.internal;

import java.io.Serializable;
import java.lang.Thread;
import java.util.List;
import kotlin.PublishedApi;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineId;
import kotlinx.coroutines.CoroutineName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@PublishedApi
/* loaded from: classes12.dex */
public final class DebuggerInfo implements Serializable {
    @Nullable
    private final Long coroutineId;
    @Nullable
    private final String dispatcher;
    @NotNull
    private final List<StackTraceElement> lastObservedStackTrace;
    @Nullable
    private final String lastObservedThreadName;
    @Nullable
    private final String lastObservedThreadState;
    @Nullable
    private final String name;
    private final long sequenceNumber;
    @NotNull
    private final String state;

    public DebuggerInfo(@NotNull DebugCoroutineInfoImpl debugCoroutineInfoImpl, @NotNull CoroutineContext coroutineContext) {
        Thread.State state;
        CoroutineId coroutineId = (CoroutineId) coroutineContext.get(CoroutineId.Key);
        this.coroutineId = coroutineId == null ? null : Long.valueOf(coroutineId.getId());
        ContinuationInterceptor continuationInterceptor = (ContinuationInterceptor) coroutineContext.get(ContinuationInterceptor.Key);
        this.dispatcher = continuationInterceptor == null ? null : continuationInterceptor.toString();
        CoroutineName coroutineName = (CoroutineName) coroutineContext.get(CoroutineName.Key);
        this.name = coroutineName == null ? null : coroutineName.getName();
        this.state = debugCoroutineInfoImpl.getState();
        Thread thread = debugCoroutineInfoImpl.lastObservedThread;
        this.lastObservedThreadState = (thread == null || (state = thread.getState()) == null) ? null : state.toString();
        Thread thread2 = debugCoroutineInfoImpl.lastObservedThread;
        this.lastObservedThreadName = thread2 != null ? thread2.getName() : null;
        this.lastObservedStackTrace = debugCoroutineInfoImpl.lastObservedStackTrace();
        this.sequenceNumber = debugCoroutineInfoImpl.sequenceNumber;
    }

    @Nullable
    public final Long getCoroutineId() {
        return this.coroutineId;
    }

    @Nullable
    public final String getDispatcher() {
        return this.dispatcher;
    }

    @NotNull
    public final List<StackTraceElement> getLastObservedStackTrace() {
        return this.lastObservedStackTrace;
    }

    @Nullable
    public final String getLastObservedThreadName() {
        return this.lastObservedThreadName;
    }

    @Nullable
    public final String getLastObservedThreadState() {
        return this.lastObservedThreadState;
    }

    @Nullable
    public final String getName() {
        return this.name;
    }

    public final long getSequenceNumber() {
        return this.sequenceNumber;
    }

    @NotNull
    public final String getState() {
        return this.state;
    }
}
