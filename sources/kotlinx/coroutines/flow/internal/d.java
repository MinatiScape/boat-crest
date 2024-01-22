package kotlinx.coroutines.flow.internal;

import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.SharedFlowImpl;
import kotlinx.coroutines.flow.StateFlow;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class d extends SharedFlowImpl<Integer> implements StateFlow<Integer> {
    public d(int i) {
        super(1, Integer.MAX_VALUE, BufferOverflow.DROP_OLDEST);
        tryEmit(Integer.valueOf(i));
    }

    @Override // kotlinx.coroutines.flow.StateFlow
    @NotNull
    /* renamed from: w */
    public Integer getValue() {
        Integer valueOf;
        synchronized (this) {
            valueOf = Integer.valueOf(getLastReplayedLocked().intValue());
        }
        return valueOf;
    }

    public final boolean x(int i) {
        boolean tryEmit;
        synchronized (this) {
            tryEmit = tryEmit(Integer.valueOf(getLastReplayedLocked().intValue() + i));
        }
        return tryEmit;
    }
}
